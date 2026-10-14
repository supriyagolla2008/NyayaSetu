import api from './api';
import { AuthResponse, LoginRequest, RegisterRequest, User } from '../types';

export const authService = {
  login: async (request: LoginRequest): Promise<AuthResponse> => {
    const response = await api.post<AuthResponse>('/api/auth/login', request);
    return response.data;
  },

  register: async (request: RegisterRequest): Promise<AuthResponse> => {
    const response = await api.post<AuthResponse>('/api/auth/register', request);
    return response.data;
  },

  getCurrentProfile: async (): Promise<User> => {
    const response = await api.get<User>('/api/user/profile');
    return response.data;
  },

  updateProfile: async (data: Partial<User>): Promise<User> => {
    const response = await api.put<User>('/api/user/profile', data);
    return response.data;
  },

  changePassword: async (passwords: { currentPassword: string; newPassword: string; confirmPassword: string }): Promise<void> => {
    await api.post('/api/user/change-password', passwords);
  },
};

export default authService;
