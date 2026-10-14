import api from './api';
import { AdminDashboardStats, CitizenDashboardStats, CMDashboardStats } from '../types';

export const analyticsService = {
  getCitizenStats: async (): Promise<CitizenDashboardStats> => {
    const response = await api.get<CitizenDashboardStats>('/api/analytics/citizen');
    return response.data;
  },

  getCMStats: async (): Promise<CMDashboardStats> => {
    const response = await api.get<CMDashboardStats>('/api/analytics/content-manager');
    return response.data;
  },

  getAdminStats: async (): Promise<AdminDashboardStats> => {
    const response = await api.get<AdminDashboardStats>('/api/analytics/admin');
    return response.data;
  },
};

export default analyticsService;
