import api from './api';
import { LegalRightDetail, LegalRightSummary, PagedResponse } from '../types';

export const cmService = {
  getContentRights: async (params?: {
    categoryId?: number;
    status?: string;
    verificationStatus?: string;
    query?: string;
    page?: number;
    size?: number;
  }): Promise<PagedResponse<LegalRightSummary>> => {
    const response = await api.get<PagedResponse<LegalRightSummary>>('/api/content/rights', { params });
    return response.data;
  },

  createRight: async (data: Partial<LegalRightDetail>): Promise<LegalRightDetail> => {
    const response = await api.post<LegalRightDetail>('/api/content/rights', data);
    return response.data;
  },

  updateRight: async (id: number | string, data: Partial<LegalRightDetail>): Promise<LegalRightDetail> => {
    const response = await api.put<LegalRightDetail>(`/api/content/rights/${id}`, data);
    return response.data;
  },

  deleteRight: async (id: number | string): Promise<void> => {
    await api.delete(`/api/content/rights/${id}`);
  },

  getEmbeddingsStatus: async (): Promise<{ totalChunks: number; embeddedChunks: number; pendingChunks: number }> => {
    const response = await api.get('/api/content/rag/embeddings');
    return response.data;
  },

  reindexEmbeddings: async (): Promise<{ message: string; reindexedCount: number }> => {
    const response = await api.post('/api/content/rag/reindex');
    return response.data;
  },

  getContentReports: async (): Promise<any> => {
    const response = await api.get('/api/content/reports');
    return response.data;
  },
};

export default cmService;
