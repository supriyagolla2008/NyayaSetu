import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Plus, Search, Briefcase, ChevronRight, Loader2, Filter, AlertCircle } from 'lucide-react';
import caseService from '../../services/caseService';
import { CaseItem, CaseStatus } from '../../types';

export const CasesPage: React.FC = () => {
  const navigate = useNavigate();
  const [cases, setCases] = useState<CaseItem[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [statusFilter, setStatusFilter] = useState<string>('ALL');
  const [searchQuery, setSearchQuery] = useState('');

  const fetchCases = async () => {
    setLoading(true);
    setError(null);
    try {
      const res = await caseService.getCases({
        status: statusFilter === 'ALL' ? undefined : statusFilter,
        query: searchQuery.trim() || undefined,
        size: 50,
      });
      setCases(res.content || []);
    } catch (err: any) {
      setError(err.message || 'Failed to fetch cases from database.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCases();
  }, [statusFilter]);

  const handleSearchSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    fetchCases();
  };

  return (
    <div className="max-w-7xl mx-auto space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">My Cases & Legal Queries</h1>
          <p className="text-xs text-gray-500 mt-1">
            Real-time case tracking, timeline progression, and action plan management stored securely in PostgreSQL.
          </p>
        </div>
        <button
          onClick={() => navigate('/citizen/problem')}
          className="flex items-center gap-2 bg-[#2563EB] hover:bg-blue-700 text-white font-bold px-4 py-2.5 rounded-xl text-xs shadow-xs shrink-0"
        >
          <Plus className="w-4 h-4" />
          <span>New Case (AI Assistance)</span>
        </button>
      </div>

      {/* Filter and Search Bar */}
      <div className="bg-white p-4 rounded-2xl border border-gray-100 shadow-sm flex flex-col md:flex-row items-center justify-between gap-3">
        <div className="flex items-center gap-1.5 overflow-x-auto w-full md:w-auto pb-1 md:pb-0">
          {['ALL', 'DOCUMENTS_PENDING', 'ACTION_TAKEN', 'UNDER_REVIEW', 'RESOLVED', 'CLOSED'].map((st) => (
            <button
              key={st}
              onClick={() => setStatusFilter(st)}
              className={`px-3 py-1.5 rounded-xl text-xs font-semibold whitespace-nowrap transition-colors ${
                statusFilter === st
                  ? 'bg-blue-600 text-white shadow-xs'
                  : 'bg-gray-50 text-gray-600 hover:bg-gray-100 border border-gray-200/60'
              }`}
            >
              {st === 'ALL' ? 'All Cases' : st.replace('_', ' ')}
            </button>
          ))}
        </div>

        <form onSubmit={handleSearchSubmit} className="relative w-full md:w-64">
          <input
            type="text"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            placeholder="Search by title or law..."
            className="w-full bg-gray-50 border border-gray-200 rounded-xl pl-9 pr-3 py-1.5 text-xs focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-800"
          />
          <Search className="w-3.5 h-3.5 text-gray-400 absolute left-3 top-1/2 -translate-y-1/2" />
        </form>
      </div>

      {error && (
        <div className="p-4 bg-red-50 border border-red-200 rounded-xl text-xs text-red-700 flex items-center gap-2">
          <AlertCircle className="w-4 h-4" />
          <span>{error}</span>
        </div>
      )}

      {/* Cases List */}
      {loading ? (
        <div className="py-20 flex flex-col items-center justify-center text-gray-400 gap-2">
          <Loader2 className="w-7 h-7 animate-spin text-blue-600" />
          <span className="text-xs">Loading case records from database...</span>
        </div>
      ) : cases.length === 0 ? (
        <div className="py-16 text-center bg-white border border-dashed border-gray-200 rounded-3xl p-8">
          <Briefcase className="w-12 h-12 text-gray-300 mx-auto mb-3" />
          <h3 className="text-base font-bold text-gray-700">No Cases Found</h3>
          <p className="text-xs text-gray-500 mt-1 max-w-sm mx-auto">
            {statusFilter !== 'ALL'
              ? `No cases currently match the filter "${statusFilter.replace('_', ' ')}".`
              : "You haven't filed or tracked any legal cases yet. Use our Problem Solver to analyze your grievance and create a case record."}
          </p>
          <button
            onClick={() => navigate('/citizen/problem')}
            className="mt-5 inline-flex items-center gap-2 bg-blue-600 text-white text-xs font-bold px-4 py-2.5 rounded-xl shadow-xs hover:bg-blue-700"
          >
            <Plus className="w-4 h-4" /> Start New Legal Grievance
          </button>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          {cases.map((item) => (
            <div
              key={item.id}
              onClick={() => navigate(`/citizen/cases/${item.id}`)}
              className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm hover:shadow-md hover:border-blue-200 transition-all cursor-pointer flex flex-col justify-between"
            >
              <div>
                <div className="flex items-center justify-between mb-2">
                  <span className="text-xs font-mono font-bold text-blue-600">{item.caseNumber}</span>
                  <span className={`text-[10px] font-bold px-2.5 py-1 rounded-full ${
                    item.status === 'RESOLVED'
                      ? 'bg-green-50 text-green-700 border border-green-200'
                      : item.status === 'DOCUMENTS_PENDING'
                      ? 'bg-amber-50 text-amber-700 border border-amber-200'
                      : item.status === 'UNDER_REVIEW'
                      ? 'bg-purple-50 text-purple-700 border border-purple-200'
                      : 'bg-teal-50 text-teal-700 border border-teal-200'
                  }`}>
                    {item.status.replace('_', ' ')}
                  </span>
                </div>

                <h2 className="text-base font-bold text-[#123B63]">{item.title}</h2>
                <p className="text-xs text-gray-500 mt-1 line-clamp-2">
                  {item.problemDescription || 'Legal issue registered for guidance.'}
                </p>

                {item.authorityName && (
                  <p className="text-xs font-medium text-blue-700 mt-2">
                    Authority: {item.authorityName}
                  </p>
                )}

                <div className="mt-4 pt-3 border-t border-gray-100 flex items-center justify-between text-xs text-gray-400">
                  <span>Created {new Date(item.createdAt).toLocaleDateString()}</span>
                  <span>{item.actions ? `${item.actions.filter(a => a.status === 'COMPLETED').length}/${item.actions.length} Steps Done` : 'Track Steps'}</span>
                </div>
              </div>

              <div className="mt-4 pt-3 border-t border-gray-100 flex justify-end">
                <span className="text-xs font-bold text-[#2563EB] flex items-center gap-1">
                  <span>View Details & Timeline</span>
                  <ChevronRight className="w-3.5 h-3.5" />
                </span>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default CasesPage;
