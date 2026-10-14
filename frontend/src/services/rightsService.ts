import api from './api';
import { LegalCategory, LegalRightDetail, LegalRightSummary, PagedResponse } from '../types';

export const rightsService = {
  getCategories: async (): Promise<LegalCategory[]> => {
    const response = await api.get<LegalCategory[]>('/api/categories');
    return response.data;
  },

  getCategoryBySlug: async (slug: string): Promise<LegalCategory> => {
    const response = await api.get<LegalCategory>(`/api/categories/${slug}`);
    return response.data;
  },

  getRights: async (params?: {
    categoryId?: number | string;
    query?: string;
    status?: string;
    page?: number;
    size?: number;
  }): Promise<PagedResponse<LegalRightSummary>> => {
    const response = await api.get<PagedResponse<LegalRightSummary>>('/api/rights', { params });
    return response.data;
  },

  getRightBySlugOrId: async (slugOrId: string | number): Promise<LegalRightDetail> => {
    const response = await api.get<LegalRightDetail>(`/api/rights/${slugOrId}`);
    return response.data;
  },

  getBookmarks: async (): Promise<LegalRightSummary[]> => {
    const response = await api.get<LegalRightSummary[]>('/api/bookmarks');
    return response.data;
  },

  toggleBookmark: async (rightId: number | string): Promise<boolean> => {
    const response = await api.post<{ bookmarked: boolean }>(`/api/bookmarks/${rightId}`);
    return response.data.bookmarked;
  },
};

export default rightsService;
