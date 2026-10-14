import React, { createContext, useContext, useState, useEffect } from 'react';
import authService from '../services/authService';
import { AuthResponse, LoginRequest, RegisterRequest, Role, User } from '../types';

interface AuthContextType {
  user: User | null;
  isAuthenticated: boolean;
  loading: boolean;
  login: (email: string, password: string, expectedRole?: Role) => Promise<AuthResponse>;
  register: (data: RegisterRequest) => Promise<AuthResponse>;
  logout: () => void;
  hasRole: (role: Role) => boolean;
  refreshProfile: () => Promise<void>;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<User | null>(() => {
    const storedUser = localStorage.getItem('user');
    const storedToken = localStorage.getItem('token');
    if (storedUser && storedToken) {
      try {
        return JSON.parse(storedUser);
      } catch {
        return null;
      }
    }
    return null;
  });
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const initAuth = async () => {
      const token = localStorage.getItem('token');
      if (token) {
        try {
          const profile = await authService.getCurrentProfile();
          const primaryRole = (profile.roles && profile.roles.length > 0)
            ? (profile.roles[0].replace('ROLE_', '') as Role)
            : (profile.role || 'CITIZEN');

          const syncedUser: User = {
            ...profile,
            id: profile.id,
            name: profile.fullName,
            fullName: profile.fullName,
            role: primaryRole,
          };
          setUser(syncedUser);
          localStorage.setItem('user', JSON.stringify(syncedUser));
        } catch (err) {
          console.warn('Failed to refresh user profile from backend. Clearing expired session.');
          logout();
        }
      }
      setLoading(false);
    };

    initAuth();

    const handleUnauthorized = () => {
      logout();
    };
    window.addEventListener('nyayasetu:unauthorized', handleUnauthorized);
    return () => window.removeEventListener('nyayasetu:unauthorized', handleUnauthorized);
  }, []);

  const login = async (email: string, password: string, expectedRole?: Role): Promise<AuthResponse> => {
    const loginReq: LoginRequest = { email, password, expectedRole };
    const res = await authService.login(loginReq);

    const primaryRole = (res.roles && res.roles.length > 0)
      ? (res.roles[0].replace('ROLE_', '') as Role)
      : (expectedRole || 'CITIZEN');

    const authenticatedUser: User = {
      id: res.id,
      name: res.fullName,
      fullName: res.fullName,
      email: res.email,
      phone: res.phone,
      role: primaryRole,
      roles: res.roles,
      preferredLanguage: res.preferredLanguage,
      stateLocation: res.stateLocation,
    };

    localStorage.setItem('token', res.token);
    localStorage.setItem('user', JSON.stringify(authenticatedUser));
    setUser(authenticatedUser);
    return res;
  };

  const register = async (data: RegisterRequest): Promise<AuthResponse> => {
    const res = await authService.register(data);

    const authenticatedUser: User = {
      id: res.id,
      name: res.fullName,
      fullName: res.fullName,
      email: res.email,
      phone: res.phone,
      role: 'CITIZEN',
      roles: ['CITIZEN'],
      preferredLanguage: res.preferredLanguage,
      stateLocation: res.stateLocation,
    };

    localStorage.setItem('token', res.token);
    localStorage.setItem('user', JSON.stringify(authenticatedUser));
    setUser(authenticatedUser);
    return res;
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setUser(null);
  };

  const hasRole = (role: Role): boolean => {
    if (!user) return false;
    if (user.role === role) return true;
    if (user.roles?.includes(role) || user.roles?.includes(`ROLE_${role}`)) return true;
    return false;
  };

  const refreshProfile = async () => {
    if (!localStorage.getItem('token')) return;
    try {
      const profile = await authService.getCurrentProfile();
      const primaryRole = (profile.roles && profile.roles.length > 0)
        ? (profile.roles[0].replace('ROLE_', '') as Role)
        : (profile.role || 'CITIZEN');

      const syncedUser: User = {
        ...profile,
        id: profile.id,
        name: profile.fullName,
        fullName: profile.fullName,
        role: primaryRole,
      };
      setUser(syncedUser);
      localStorage.setItem('user', JSON.stringify(syncedUser));
    } catch (e) {
      console.error('Failed to reload profile', e);
    }
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        isAuthenticated: !!user,
        loading,
        login,
        register,
        logout,
        hasRole,
        refreshProfile,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

export type { Role, User };
