import api from './api';
import { AuditLogItem, CaseItem, PagedResponse, User } from '../types';

export const adminService = {
  getUsers: async (params?: { query?: string; role?: string; status?: string; page?: number; size?: number }): Promise<PagedResponse<User>> => {
    const response = await api.get<PagedResponse<User>>('/api/admin/users', { params });
    return response.data;
  },

  getCases: async (params?: { query?: string; status?: string; page?: number; size?: number }): Promise<PagedResponse<CaseItem>> => {
    const response = await api.get<PagedResponse<CaseItem>>('/api/admin/cases', { params });
    return response.data;
  },

  getAuditLogs: async (params?: { page?: number; size?: number }): Promise<PagedResponse<AuditLogItem>> => {
    const response = await api.get<PagedResponse<AuditLogItem>>('/api/admin/audit-logs', { params });
    return response.data;
  },
};

export default adminService;
