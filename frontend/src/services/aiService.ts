import api from './api';
import { AIAnalysisResponse, GlobalSearchResponse } from '../types';

export const aiService = {
  analyzeProblem: async (data: {
    problemDescription: string;
    categoryHint?: string;
    stateLocation?: string;
    preferredLanguage?: string;
  }): Promise<AIAnalysisResponse> => {
    const response = await api.post<AIAnalysisResponse>('/api/problems/analyze', data);
    return response.data;
  },

  chat: async (data: {
    problemDescription: string;
    categoryHint?: string;
    stateLocation?: string;
    preferredLanguage?: string;
  }): Promise<AIAnalysisResponse> => {
    const response = await api.post<AIAnalysisResponse>('/api/ai/chat', data);
    return response.data;
  },

  search: async (query: string, categoryId?: number, type: string = 'ALL'): Promise<GlobalSearchResponse> => {
    const response = await api.get<GlobalSearchResponse>('/api/search', {
      params: { query, categoryId, type },
    });
    return response.data;
  },
};

export default aiService;
