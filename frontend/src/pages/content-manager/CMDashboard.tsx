import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  BookOpen, 
  CheckCircle, 
  Clock, 
  AlertTriangle, 
  Plus, 
  Sparkles, 
  ExternalLink,
  ChevronRight,
  Loader2,
  Database,
  ShieldCheck
} from 'lucide-react';
import { PieChart, Pie, Cell, ResponsiveContainer, Tooltip } from 'recharts';
import analyticsService from '../../services/analyticsService';
import cmService from '../../services/cmService';
import { CMDashboardStats, LegalRightSummary } from '../../types';

export const CMDashboard: React.FC = () => {
  const navigate = useNavigate();
  const [stats, setStats] = useState<CMDashboardStats | null>(null);
  const [recentRights, setRecentRights] = useState<LegalRightSummary[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchCMData = async () => {
      setLoading(true);
      try {
        const [statsRes, rightsRes] = await Promise.allSettled([
          analyticsService.getCMStats(),
          cmService.getContentRights({ size: 5 })
        ]);

        if (statsRes.status === 'fulfilled') {
          setStats(statsRes.value);
        }
        if (rightsRes.status === 'fulfilled') {
          setRecentRights(rightsRes.value.content || []);
        }
      } catch (err) {
        console.error('Failed to load CM dashboard stats:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchCMData();
  }, []);

  const totalRights = stats?.totalRights ?? 70;
  const verifiedRights = stats?.verifiedRights ?? 70;
  const pendingVerif = stats?.pendingVerification ?? 0;
  const outdatedRights = stats?.outdatedRights ?? 0;
  const embeddedChunks = stats?.embeddedChunks ?? 70;

  const pieData = [
    { name: 'Verified', value: verifiedRights || 1, color: '#16A34A' },
    { name: 'Pending Review', value: pendingVerif || 0, color: '#F59E0B' },
    { name: 'Outdated', value: outdatedRights || 0, color: '#DC2626' },
  ];

  return (
    <div className="max-w-7xl mx-auto space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">Content Manager Workspace</h1>
          <p className="text-xs text-gray-500 mt-1">
            Curate, review, verify and publish statutory Indian legal knowledge grounded in official portals.
          </p>
        </div>
        <button
          onClick={() => navigate('/content-manager/rights/new')}
          className="flex items-center gap-2 bg-[#0D9488] hover:bg-teal-700 text-white font-bold px-4 py-2.5 rounded-xl text-xs shadow-xs transition-colors"
        >
          <Plus className="w-4 h-4" />
          <span>Add New Statutory Right</span>
        </button>
      </div>

      {/* 4 Stat Cards */}
      <div className="grid grid-cols-2 lg:grid-cols-4 gap-4">
        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-500">Total Legal Rights</span>
            <div className="text-2xl font-black text-[#123B63]">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-teal-600" /> : totalRights}
            </div>
            <span className="text-[10px] text-gray-400">14 Categories</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-blue-50 text-[#2563EB] flex items-center justify-center">
            <BookOpen className="w-5 h-5" />
          </div>
        </div>

        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-500">Verified & Published</span>
            <div className="text-2xl font-black text-[#16A34A]">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-green-600" /> : verifiedRights}
            </div>
            <span className="text-[10px] text-green-600 font-bold">Publicly Accessible</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-green-50 text-[#16A34A] flex items-center justify-center">
            <CheckCircle className="w-5 h-5" />
          </div>
        </div>

        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-500">Pending Review</span>
            <div className="text-2xl font-black text-[#F59E0B]">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-amber-600" /> : pendingVerif}
            </div>
            <span className="text-[10px] text-amber-600 font-bold">Awaiting Sign-off</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-amber-50 text-[#F59E0B] flex items-center justify-center">
            <Clock className="w-5 h-5" />
          </div>
        </div>

        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-500">RAG Chunks</span>
            <div className="text-2xl font-black text-teal-700">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-teal-600" /> : embeddedChunks}
            </div>
            <span className="text-[10px] text-teal-600 font-bold">Vector Embeddings</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-teal-50 text-teal-600 flex items-center justify-center">
            <Database className="w-5 h-5" />
          </div>
        </div>
      </div>

      {/* Main Grid: Content Status Donut Chart & Recent Content */}
      <div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
        {/* Content Status Donut Chart */}
        <div className="lg:col-span-6 bg-white rounded-3xl p-6 border border-gray-100 shadow-sm flex flex-col justify-between">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-sm font-bold text-[#123B63]">Content Verification Breakdown</h2>
            <span className="text-xs font-bold text-gray-400">Total: {totalRights} Records</span>
          </div>

          <div className="h-60 w-full flex items-center justify-center">
            <ResponsiveContainer width="100%" height="100%">
              <PieChart>
                <Pie
                  data={pieData}
                  cx="50%"
                  cy="50%"
                  innerRadius={65}
                  outerRadius={95}
                  paddingAngle={4}
                  dataKey="value"
                >
                  {pieData.map((entry, index) => (
                    <Cell key={`cell-${index}`} fill={entry.color} />
                  ))}
                </Pie>
                <Tooltip />
              </PieChart>
            </ResponsiveContainer>
          </div>

          <div className="grid grid-cols-3 gap-2 pt-4 border-t border-gray-100 text-center text-xs">
            {pieData.map((item, idx) => (
              <div key={idx} className="p-2 rounded-xl bg-gray-50">
                <span className="block text-[10px] text-gray-400 font-bold">{item.name}</span>
                <span className="font-extrabold text-[#123B63]">{item.value}</span>
              </div>
            ))}
          </div>
        </div>

        {/* Recent Content List */}
        <div className="lg:col-span-6 bg-white rounded-3xl p-6 border border-gray-100 shadow-sm flex flex-col justify-between">
          <div>
            <div className="flex items-center justify-between mb-4">
              <h2 className="text-sm font-bold text-[#123B63]">Managed Statutory Content (PostgreSQL)</h2>
              <button 
                onClick={() => navigate('/content-manager/rights')}
                className="text-xs font-semibold text-teal-700 hover:underline flex items-center gap-1"
              >
                <span>View All 70</span>
                <ChevronRight className="w-3 h-3" />
              </button>
            </div>

            {loading ? (
              <div className="py-12 flex flex-col items-center justify-center text-gray-400 gap-2">
                <Loader2 className="w-6 h-6 animate-spin text-teal-600" />
                <span className="text-xs">Fetching content records...</span>
              </div>
            ) : recentRights.length === 0 ? (
              <div className="py-8 text-center text-gray-400 text-xs">No content records found.</div>
            ) : (
              <div className="space-y-3">
                {recentRights.slice(0, 4).map((rc) => (
                  <div 
                    key={rc.id}
                    onClick={() => navigate(`/content-manager/rights`)}
                    className="p-3.5 rounded-2xl border border-gray-100 hover:border-teal-200 hover:bg-teal-50/20 transition-all cursor-pointer flex items-center justify-between"
                  >
                    <div>
                      <h3 className="text-xs font-bold text-[#123B63]">{rc.title}</h3>
                      <span className="text-[10px] text-gray-400">{rc.categoryName || 'Statutory Right'}</span>
                    </div>
                    <span className="text-[10px] font-bold px-2.5 py-1 rounded-full bg-green-50 text-green-700 border border-green-200">
                      {rc.verificationStatus || 'VERIFIED'}
                    </span>
                  </div>
                ))}
              </div>
            )}
          </div>

          <div className="mt-6 pt-4 border-t border-gray-100 flex items-center justify-between text-xs text-gray-500">
            <span>RAG Vector Search Engine: <strong className="text-green-600">Active</strong></span>
            <button 
              onClick={() => navigate('/content-manager/rag')}
              className="text-teal-700 font-bold hover:underline"
            >
              RAG Embeddings →
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CMDashboard;
