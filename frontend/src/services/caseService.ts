import api from './api';
import { CaseActionItem, CaseCreateRequest, CaseItem, PagedResponse } from '../types';

export const caseService = {
  getCases: async (params?: {
    status?: string;
    categoryId?: number;
    query?: string;
    page?: number;
    size?: number;
  }): Promise<PagedResponse<CaseItem>> => {
    const response = await api.get<PagedResponse<CaseItem>>('/api/cases', { params });
    return response.data;
  },

  getCaseById: async (id: number | string): Promise<CaseItem> => {
    const response = await api.get<CaseItem>(`/api/cases/${id}`);
    return response.data;
  },

  createCase: async (request: CaseCreateRequest): Promise<CaseItem> => {
    const response = await api.post<CaseItem>('/api/cases', request);
    return response.data;
  },

  transitionStatus: async (id: number | string, newStatus: string, changeNote?: string): Promise<CaseItem> => {
    const response = await api.post<CaseItem>(`/api/cases/${id}/status`, {
      newStatus,
      changeNote: changeNote || `Status updated to ${newStatus}`,
    });
    return response.data;
  },

  toggleActionStatus: async (actionId: number, status: 'PENDING' | 'COMPLETED' | 'SKIPPED'): Promise<CaseActionItem> => {
    const response = await api.patch<CaseActionItem>(`/api/cases/actions/${actionId}/status`, null, {
      params: { status },
    });
    return response.data;
  },
};

export default caseService;
