import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { Scale, Eye, EyeOff, ArrowLeft, AlertCircle } from 'lucide-react';
import { useAuth } from '../../contexts/AuthContext';

export const CitizenLoginPage: React.FC = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { login } = useAuth();
  const [email, setEmail] = useState('citizen@rightsflow.in');
  const [password, setPassword] = useState('Password@123');
  const [showPassword, setShowPassword] = useState(false);
  const [rememberMe, setRememberMe] = useState(true);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    setLoading(true);
    try {
      await login(email, password, 'CITIZEN');
      const destination = (location.state as any)?.from?.pathname || '/citizen/dashboard';
      navigate(destination, { replace: true });
    } catch (err: any) {
      setError(err.message || 'Invalid email or password. Please verify your credentials.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-[#EFF8FF] text-[#172033] flex flex-col justify-center items-center p-4">
      <div className="w-full max-w-md bg-white rounded-3xl border border-gray-100 shadow-md p-8">
        {/* Logo and Header */}
        <div className="flex flex-col items-center text-center mb-6">
          <div className="w-12 h-12 rounded-2xl bg-[#2563EB] flex items-center justify-center text-white mb-3 shadow-md">
            <Scale className="w-7 h-7" />
          </div>
          <span className="text-xl font-extrabold text-[#123B63] tracking-tight">NyayaSetu (न्यायसेतु)</span>
          <h2 className="text-2xl font-bold text-[#123B63] mt-2">Citizen Login</h2>
          <p className="text-xs text-gray-500 mt-1">Sign in to access verified legal rights and track your cases.</p>
        </div>

        {error && (
          <div className="mb-4 p-3 bg-red-50 border border-red-200 rounded-xl flex items-center gap-2.5 text-xs text-red-700">
            <AlertCircle className="w-4 h-4 flex-shrink-0 text-red-600" />
            <span>{error}</span>
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-xs font-semibold text-gray-700 mb-1.5">Email address</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className="w-full px-3.5 py-2.5 rounded-xl border border-gray-200 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50/50"
              placeholder="Enter your registered email"
            />
          </div>

          <div>
            <label className="block text-xs font-semibold text-gray-700 mb-1.5">Password</label>
            <div className="relative">
              <input
                type={showPassword ? 'text' : 'password'}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                className="w-full px-3.5 py-2.5 rounded-xl border border-gray-200 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50/50 pr-10"
                placeholder="Enter password"
              />
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600"
              >
                {showPassword ? <EyeOff className="w-4 h-4" /> : <Eye className="w-4 h-4" />}
              </button>
            </div>
          </div>

          <div className="flex items-center justify-between text-xs pt-1">
            <label className="flex items-center gap-2 cursor-pointer text-gray-600">
              <input 
                type="checkbox" 
                checked={rememberMe} 
                onChange={(e) => setRememberMe(e.target.checked)} 
                className="rounded border-gray-300 text-blue-600 focus:ring-blue-500"
              />
              <span>Remember me</span>
            </label>
            <span className="text-xs text-blue-600 font-medium">Demo: citizen@rightsflow.in</span>
          </div>

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-[#2563EB] hover:bg-blue-700 text-white font-semibold py-3 px-4 rounded-xl text-sm shadow-sm transition-colors mt-2 disabled:opacity-50"
          >
            {loading ? 'Authenticating with Backend...' : 'Login as Citizen'}
          </button>
        </form>

        <div className="mt-6 text-center text-xs text-gray-600">
          New to NyayaSetu?{' '}
          <button onClick={() => navigate('/register')} className="text-blue-600 font-semibold hover:underline">
            Register for Free Citizen Account
          </button>
        </div>

        <div className="mt-4 pt-3 border-t border-gray-100 text-center">
          <button
            onClick={() => navigate('/select-role')}
            className="inline-flex items-center gap-1.5 text-xs text-gray-500 hover:text-[#123B63] font-medium"
          >
            <ArrowLeft className="w-3.5 h-3.5" />
            Back to Role Selection
          </button>
        </div>
      </div>
    </div>
  );
};

export default CitizenLoginPage;
