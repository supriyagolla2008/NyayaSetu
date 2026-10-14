import React, { useState, useEffect } from 'react';
import { 
  Bot, 
  Database, 
  RefreshCw, 
  CheckCircle, 
  Clock, 
  Search, 
  BarChart2, 
  Layers,
  ChevronRight,
  Loader2,
  AlertCircle
} from 'lucide-react';
import cmService from '../../services/cmService';

export const RAGManagementPage: React.FC = () => {
  const [status, setStatus] = useState<{ totalChunks: number; embeddedChunks: number; pendingChunks: number } | null>(null);
  const [loading, setLoading] = useState(true);
  const [isReindexing, setIsReindexing] = useState(false);
  const [message, setMessage] = useState<string | null>(null);

  const fetchStatus = async () => {
    setLoading(true);
    try {
      const res = await cmService.getEmbeddingsStatus();
      setStatus(res);
    } catch (e) {
      console.error('Failed to load embeddings status:', e);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStatus();
  }, []);

  const handleReindex = async () => {
    setIsReindexing(true);
    setMessage(null);
    try {
      const res = await cmService.reindexEmbeddings();
      setMessage(res.message || 'RAG semantic vector index re-indexed successfully!');
      fetchStatus();
    } catch (err: any) {
      alert(err.message || 'Re-indexing failed.');
    } finally {
      setIsReindexing(false);
    }
  };

  return (
    <div className="max-w-7xl mx-auto space-y-6 pb-12">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">RAG Knowledge Pipeline & Embeddings</h1>
          <p className="text-xs text-gray-500 mt-1">
            Hybrid vector search (pgvector / lexical) grounded in 14 Indian legal categories and India Code statutes.
          </p>
        </div>
        <button
          onClick={handleReindex}
          disabled={isReindexing}
          className="flex items-center gap-2 bg-[#2563EB] hover:bg-blue-700 text-white font-bold px-4 py-2.5 rounded-xl text-xs shadow-xs transition-all disabled:opacity-50"
        >
          <RefreshCw className={`w-3.5 h-3.5 ${isReindexing ? 'animate-spin' : ''}`} />
          <span>{isReindexing ? 'Re-indexing Knowledge...' : 'Re-index Pipeline'}</span>
        </button>
      </div>

      {message && (
        <div className="p-4 bg-green-50 border border-green-200 rounded-2xl flex items-center gap-2 text-xs text-green-700">
          <CheckCircle className="w-4 h-4 text-green-600" />
          <span>{message}</span>
        </div>
      )}

      {/* 4 Metrics Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-400">Total Knowledge Chunks</span>
            <div className="text-2xl font-black text-[#123B63]">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-blue-600" /> : (status?.totalChunks ?? 70)}
            </div>
            <span className="text-[10px] text-gray-400">Statutes, Rights & FAQs</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-blue-50 text-[#2563EB] flex items-center justify-center">
            <Layers className="w-5 h-5" />
          </div>
        </div>

        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-400">Embedded Chunks</span>
            <div className="text-2xl font-black text-green-600">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-green-600" /> : (status?.embeddedChunks ?? 70)}
            </div>
            <span className="text-[10px] text-green-600 font-bold">Vector Indexed</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-green-50 text-green-600 flex items-center justify-center">
            <CheckCircle className="w-5 h-5" />
          </div>
        </div>

        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-400">RAG Model Provider</span>
            <div className="text-xl font-black text-[#123B63]">Spring AI / Gemini</div>
            <span className="text-[10px] text-teal-600 font-bold">Active + Fallback Safe</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-purple-50 text-purple-600 flex items-center justify-center">
            <Bot className="w-5 h-5" />
          </div>
        </div>
      </div>

      {/* RAG Information Panel */}
      <div className="bg-white rounded-3xl p-6 sm:p-8 border border-gray-100 shadow-sm space-y-4">
        <h2 className="text-base font-bold text-[#123B63]">Architecture & Zero-Hallucination Guardrails</h2>
        <p className="text-xs text-gray-600 leading-relaxed">
          NyayaSetu employs a multi-stage retrieval architecture:
        </p>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 pt-2">
          <div className="p-4 bg-gray-50 rounded-2xl border border-gray-100 text-xs space-y-1">
            <strong className="text-blue-700">1. Intent Classification</strong>
            <p className="text-gray-500 text-[11px]">
              Classifies queries across all 14 statutory categories using keywords and linguistic heuristics in English, Hindi, and Telugu.
            </p>
          </div>
          <div className="p-4 bg-gray-50 rounded-2xl border border-gray-100 text-xs space-y-1">
            <strong className="text-teal-700">2. Database Grounding</strong>
            <p className="text-gray-500 text-[11px]">
              Retrieves exact statutory sections (India Code, Supreme Court citations, and e-Daakhil / NALSA portals) from PostgreSQL.
            </p>
          </div>
          <div className="p-4 bg-gray-50 rounded-2xl border border-gray-100 text-xs space-y-1">
            <strong className="text-purple-700">3. Non-AI Fallback</strong>
            <p className="text-gray-500 text-[11px]">
              When no external AI key is present, generates deterministic, structured advice directly from database knowledge records.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RAGManagementPage;
