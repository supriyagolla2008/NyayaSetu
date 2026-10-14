import api from './api';
import { AuthorityItem, DocumentItem, LegalServiceItem, LegalUpdateItem, NotificationItem, PagedResponse } from '../types';

export const miscService = {
  getAuthorities: async (params?: { categoryId?: number; query?: string }): Promise<AuthorityItem[]> => {
    const response = await api.get<AuthorityItem[]>('/api/authorities', { params });
    return response.data;
  },

  getServices: async (params?: { categoryId?: number; query?: string }): Promise<LegalServiceItem[]> => {
    const response = await api.get<LegalServiceItem[]>('/api/services', { params });
    return response.data;
  },

  getLegalUpdates: async (): Promise<LegalUpdateItem[]> => {
    const response = await api.get<LegalUpdateItem[]>('/api/legal-updates');
    return response.data;
  },

  getNotifications: async (): Promise<NotificationItem[]> => {
    const response = await api.get<NotificationItem[]>('/api/notifications');
    return response.data;
  },

  getUnreadNotificationCount: async (): Promise<number> => {
    const response = await api.get<{ count: number }>('/api/notifications/unread-count');
    return response.data.count;
  },

  getDocuments: async (params?: { caseId?: number }): Promise<DocumentItem[]> => {
    const response = await api.get<DocumentItem[]>('/api/documents', { params });
    return response.data;
  },

  uploadDocument: async (file: File, description?: string, caseId?: number): Promise<DocumentItem> => {
    const formData = new FormData();
    formData.append('file', file);
    if (description) formData.append('description', description);
    if (caseId) formData.append('caseId', caseId.toString());

    const response = await api.post<DocumentItem>('/api/documents/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  },

  deleteDocument: async (id: number): Promise<void> => {
    await api.delete(`/api/documents/${id}`);
  },
};

export default miscService;
