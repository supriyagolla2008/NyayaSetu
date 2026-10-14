import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  Briefcase, 
  Clock, 
  FileText, 
  Bookmark, 
  Plus, 
  ArrowRight, 
  Bell, 
  Sparkles, 
  ShieldAlert,
  ChevronRight,
  CheckCircle,
  HelpCircle,
  Loader2
} from 'lucide-react';
import { useAuth } from '../../contexts/AuthContext';
import analyticsService from '../../services/analyticsService';
import caseService from '../../services/caseService';
import { CaseItem, CitizenDashboardStats } from '../../types';

export const CitizenDashboard: React.FC = () => {
  const navigate = useNavigate();
  const { user } = useAuth();

  const [stats, setStats] = useState<CitizenDashboardStats | null>(null);
  const [cases, setCases] = useState<CaseItem[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchDashboardData = async () => {
      setLoading(true);
      try {
        const [statsData, casesData] = await Promise.allSettled([
          analyticsService.getCitizenStats(),
          caseService.getCases({ size: 5 })
        ]);

        if (statsData.status === 'fulfilled') {
          setStats(statsData.value);
        }
        if (casesData.status === 'fulfilled') {
          setCases(casesData.value.content || []);
        }
      } catch (err) {
        console.error('Failed to load dashboard data:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchDashboardData();
  }, []);

  const activeCasesCount = stats?.activeCases ?? cases.filter(c => c.status !== 'RESOLVED' && c.status !== 'CLOSED').length;
  const pendingActionsCount = stats?.pendingActions ?? 0;
  const pendingDocsCount = stats?.pendingDocuments ?? 0;
  const savedRightsCount = stats?.savedRights ?? 0;

  return (
    <div className="max-w-7xl mx-auto space-y-6">
      {/* 1. Welcome Banner */}
      <div className="bg-white rounded-2xl p-6 border border-gray-100 shadow-sm flex flex-col md:flex-row md:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-extrabold text-[#123B63] flex items-center gap-2">
            Welcome, {user?.fullName || 'Citizen'}! <span>👋</span>
          </h1>
          <p className="text-sm text-gray-500 mt-1">
            NyayaSetu (न्यायसेतु) is your portal for Indian legal rights awareness, step-by-step guidance, and dispute navigation.
          </p>
        </div>

        <div className="flex items-center gap-2">
          <button
            onClick={() => navigate('/citizen/problem')}
            className="flex items-center gap-2 bg-[#2563EB] hover:bg-blue-700 text-white font-bold px-5 py-3 rounded-xl text-sm shadow-sm transition-colors shrink-0"
          >
            <Plus className="w-4 h-4" />
            <span>I Have a Problem</span>
          </button>
        </div>
      </div>

      {/* 2. 4 Database-Backed Stat Cards */}
      <div className="grid grid-cols-2 lg:grid-cols-4 gap-4">
        {/* Active Cases */}
        <div 
          onClick={() => navigate('/citizen/cases')}
          className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm hover:shadow-md transition-shadow cursor-pointer flex items-center justify-between"
        >
          <div className="space-y-1">
            <span className="text-xs font-semibold text-gray-500">Active Cases</span>
            <div className="text-2xl font-black text-[#123B63]">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-blue-600" /> : activeCasesCount}
            </div>
            <span className="inline-block text-[10px] text-blue-700 bg-blue-50 px-2 py-0.5 rounded-full font-bold">
              Database Synced
            </span>
          </div>
          <div className="w-12 h-12 rounded-xl bg-blue-50 text-[#2563EB] flex items-center justify-center">
            <Briefcase className="w-6 h-6" />
          </div>
        </div>

        {/* Pending Actions */}
        <div 
          onClick={() => navigate('/citizen/cases')}
          className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm hover:shadow-md transition-shadow cursor-pointer flex items-center justify-between"
        >
          <div className="space-y-1">
            <span className="text-xs font-semibold text-gray-500">Pending Actions</span>
            <div className="text-2xl font-black text-[#F59E0B]">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-amber-600" /> : pendingActionsCount}
            </div>
            <span className="inline-block text-[10px] text-amber-700 bg-amber-50 px-2 py-0.5 rounded-full font-bold">
              Tasks
            </span>
          </div>
          <div className="w-12 h-12 rounded-xl bg-amber-50 text-[#F59E0B] flex items-center justify-center">
            <Clock className="w-6 h-6" />
          </div>
        </div>

        {/* Pending Documents */}
        <div 
          onClick={() => navigate('/citizen/documents')}
          className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm hover:shadow-md transition-shadow cursor-pointer flex items-center justify-between"
        >
          <div className="space-y-1">
            <span className="text-xs font-semibold text-gray-500">Pending Documents</span>
            <div className="text-2xl font-black text-[#7C3AED]">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-purple-600" /> : pendingDocsCount}
            </div>
            <span className="inline-block text-[10px] text-purple-700 bg-purple-50 px-2 py-0.5 rounded-full font-bold">
              Checklists
            </span>
          </div>
          <div className="w-12 h-12 rounded-xl bg-purple-50 text-[#7C3AED] flex items-center justify-center">
            <FileText className="w-6 h-6" />
          </div>
        </div>

        {/* Saved Rights */}
        <div 
          onClick={() => navigate('/citizen/rights')}
          className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm hover:shadow-md transition-shadow cursor-pointer flex items-center justify-between"
        >
          <div className="space-y-1">
            <span className="text-xs font-semibold text-gray-500">Saved Rights</span>
            <div className="text-2xl font-black text-[#16A34A]">
              {loading ? <Loader2 className="w-5 h-5 animate-spin text-green-600" /> : savedRightsCount}
            </div>
            <span className="inline-block text-[10px] text-green-700 bg-green-50 px-2 py-0.5 rounded-full font-bold">
              Bookmarks
            </span>
          </div>
          <div className="w-12 h-12 rounded-xl bg-green-50 text-[#16A34A] flex items-center justify-center">
            <Bookmark className="w-6 h-6" />
          </div>
        </div>
      </div>

      {/* 3. Main Grid (Real PostgreSQL Cases + Notifications) */}
      <div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
        {/* Left Column: Recent Cases */}
        <div className="lg:col-span-7 bg-white rounded-2xl p-6 border border-gray-100 shadow-sm">
          <div className="flex items-center justify-between mb-5">
            <h2 className="text-base font-bold text-[#123B63]">Your Active Cases & Queries</h2>
            <button 
              onClick={() => navigate('/citizen/cases')} 
              className="text-xs font-semibold text-[#2563EB] hover:underline flex items-center gap-1"
            >
              <span>View All</span>
              <ChevronRight className="w-3.5 h-3.5" />
            </button>
          </div>

          {loading ? (
            <div className="py-12 flex flex-col items-center justify-center text-gray-400 gap-2">
              <Loader2 className="w-6 h-6 animate-spin text-blue-600" />
              <span className="text-xs">Fetching cases from PostgreSQL...</span>
            </div>
          ) : cases.length === 0 ? (
            <div className="py-10 text-center border-2 border-dashed border-gray-100 rounded-2xl p-6">
              <Briefcase className="w-10 h-10 text-gray-300 mx-auto mb-2" />
              <h3 className="text-sm font-bold text-gray-700">No Cases Created Yet</h3>
              <p className="text-xs text-gray-500 mt-1 max-w-sm mx-auto">
                Have you encountered delayed wages, consumer fraud, landlord harassment, or need legal aid? Click below to start.
              </p>
              <button
                onClick={() => navigate('/citizen/problem')}
                className="mt-4 inline-flex items-center gap-1.5 bg-blue-600 text-white text-xs font-semibold px-4 py-2 rounded-xl shadow-xs hover:bg-blue-700"
              >
                <Plus className="w-3.5 h-3.5" /> Start Legal Guidance
              </button>
            </div>
          ) : (
            <div className="space-y-3.5">
              {cases.slice(0, 4).map((item) => (
                <div
                  key={item.id}
                  onClick={() => navigate(`/citizen/cases/${item.id}`)}
                  className="p-4 rounded-xl border border-gray-100 hover:border-blue-200 hover:bg-blue-50/20 transition-all cursor-pointer flex flex-col sm:flex-row sm:items-center justify-between gap-3"
                >
                  <div className="space-y-1">
                    <div className="flex items-center gap-2">
                      <span className="text-xs font-black text-gray-400 font-mono">{item.caseNumber}</span>
                      <span className="text-sm font-bold text-[#123B63]">{item.title}</span>
                    </div>
                    <div className="text-xs text-gray-500 flex items-center gap-2">
                      <span>{item.categoryName || 'General Law'}</span>
                      <span>•</span>
                      <span>Created {new Date(item.createdAt).toLocaleDateString()}</span>
                    </div>
                  </div>

                  <div>
                    <span className={`inline-flex items-center text-xs font-bold px-3 py-1 rounded-full ${
                      item.status === 'RESOLVED' 
                        ? 'bg-green-50 text-green-700 border border-green-200'
                        : item.status === 'DOCUMENTS_PENDING'
                        ? 'bg-amber-50 text-amber-700 border border-amber-200' 
                        : 'bg-teal-50 text-teal-700 border border-teal-200'
                    }`}>
                      {item.status.replace('_', ' ')}
                    </span>
                  </div>
                </div>
              ))}
            </div>
          )}

          {/* Quick Guidance Promotion Banner */}
          <div className="mt-5 p-4 rounded-xl bg-gradient-to-r from-blue-50 to-indigo-50 border border-blue-100 flex items-center justify-between">
            <div className="text-xs text-gray-700">
              <span className="font-bold text-[#123B63]">Facing an issue at work, home, or online?</span>
              <p className="text-gray-500 mt-0.5">Describe your problem in plain language to get statutory citations, authority links, and action checklists.</p>
            </div>
            <button 
              onClick={() => navigate('/citizen/problem')}
              className="bg-[#2563EB] hover:bg-blue-700 text-white text-xs font-bold px-3.5 py-2 rounded-lg shrink-0 shadow-xs"
            >
              Analyze Problem
            </button>
          </div>
        </div>

        {/* Right Column: Real-Time Legal Alerts & Updates */}
        <div className="lg:col-span-5 bg-white rounded-2xl p-6 border border-gray-100 shadow-sm">
          <div className="flex items-center justify-between mb-5">
            <h2 className="text-base font-bold text-[#123B63]">Verified Legal Notifications</h2>
            <button 
              onClick={() => navigate('/citizen/legal-updates')}
              className="text-xs font-semibold text-[#2563EB] hover:underline"
            >
              View Updates
            </button>
          </div>

          <div className="space-y-3.5">
            <div className="p-3.5 rounded-xl bg-blue-50/50 border border-blue-100 space-y-1">
              <div className="flex items-center justify-between">
                <span className="text-xs font-bold text-blue-700 flex items-center gap-1.5">
                  <Sparkles className="w-3.5 h-3.5" /> 14 Legal Categories Online
                </span>
                <span className="text-[10px] text-gray-400">Live</span>
              </div>
              <p className="text-xs text-gray-600">
                Explore 70 verified statutory rights across women's safety, labour law, tenancy, cybercrime, and consumer rights.
              </p>
            </div>

            <div className="p-3.5 rounded-xl bg-gray-50/70 border border-gray-100 space-y-1">
              <div className="flex items-center justify-between">
                <span className="text-xs font-bold text-amber-700 flex items-center gap-1.5">
                  <Clock className="w-3.5 h-3.5" /> Cyber Golden Hour Reminder
                </span>
                <span className="text-[10px] text-gray-400">Statutory Notice</span>
              </div>
              <p className="text-xs text-gray-600">
                In unauthorized financial fraud or UPI scam, dial 1930 immediately within 1-2 hours for bank account freeze.
              </p>
            </div>

            <div className="p-3.5 rounded-xl bg-gray-50/70 border border-gray-100 space-y-1">
              <div className="flex items-center justify-between">
                <span className="text-xs font-bold text-teal-700 flex items-center gap-1.5">
                  <CheckCircle className="w-3.5 h-3.5" /> Free Legal Aid (NALSA)
                </span>
                <span className="text-[10px] text-gray-400">Article 39A</span>
              </div>
              <p className="text-xs text-gray-600">
                Women, children, SC/ST, and individuals earning under Rs. 3 Lakhs qualify for free DLSA advocate representation.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CitizenDashboard;
