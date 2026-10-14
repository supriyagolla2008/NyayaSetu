import axios, { AxiosError, InternalAxiosRequestConfig } from 'axios';

// Base Axios instance for NyayaSetu API
const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '',
  headers: {
    'Content-Type': 'application/json',
  },
  timeout: 30000,
});

// Request Interceptor: Attach JWT Bearer Token if available
api.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    const token = localStorage.getItem('token');
    if (token && config.headers) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response Interceptor: Handle unauthenticated or server errors
api.interceptors.response.use(
  (response) => response,
  (error: AxiosError<{ message?: string; error?: string }>) => {
    if (error.response?.status === 401) {
      console.warn('Authentication token expired or unauthorized access.');
      // Only clear if on a protected route or if token is invalid
      if (localStorage.getItem('token')) {
        // dispatch custom event for auth listeners
        window.dispatchEvent(new Event('nyayasetu:unauthorized'));
      }
    }
    const message = error.response?.data?.message || error.response?.data?.error || error.message || 'An error occurred';
    return Promise.reject(new Error(message));
  }
);

export default api;
