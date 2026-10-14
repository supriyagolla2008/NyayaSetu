import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Search, Filter, Briefcase, Eye, ChevronRight, Loader2, AlertCircle } from 'lucide-react';
import adminService from '../../services/adminService';
import { CaseItem } from '../../types';

export const CaseMonitoringPage: React.FC = () => {
  const navigate = useNavigate();
  const [cases, setCases] = useState<CaseItem[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [statusFilter, setStatusFilter] = useState('ALL');
  const [searchQuery, setSearchQuery] = useState('');

  const fetchCases = async () => {
    setLoading(true);
    setError(null);
    try {
      const res = await adminService.getCases({
        status: statusFilter === 'ALL' ? undefined : statusFilter,
        query: searchQuery.trim() || undefined,
        size: 50,
      });
      setCases(res.content || []);
    } catch (err: any) {
      setError(err.message || 'Failed to fetch platform cases.');
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
    <div className="max-w-7xl mx-auto space-y-6 pb-12">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">Case Monitoring & Oversight</h1>
          <p className="text-xs text-gray-500 mt-1">
            Platform-wide oversight of citizen disputes, filing stages, and resolution timelines stored in PostgreSQL.
          </p>
        </div>
      </div>

      {/* Filter and Search Bar */}
      <div className="bg-white rounded-2xl p-4 border border-gray-100 shadow-sm flex flex-col md:flex-row items-center justify-between gap-4">
        <form onSubmit={handleSearchSubmit} className="relative flex-1 w-full">
          <Search className="w-4 h-4 text-gray-400 absolute left-3.5 top-1/2 -translate-y-1/2" />
          <input
            type="text"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            placeholder="Search cases by #ID, title, law or authority..."
            className="w-full pl-9 pr-20 py-2 rounded-xl border border-gray-200 text-xs focus:outline-none focus:ring-2 focus:ring-purple-500 bg-gray-50/50"
          />
          <button
            type="submit"
            className="absolute right-1.5 top-1/2 -translate-y-1/2 bg-purple-600 text-white text-[11px] font-semibold px-2.5 py-1 rounded-lg"
          >
            Search
          </button>
        </form>

        <div className="flex items-center gap-2">
          {['ALL', 'DOCUMENTS_PENDING', 'ACTION_TAKEN', 'UNDER_REVIEW', 'RESOLVED', 'CLOSED'].map(st => (
            <button
              key={st}
              onClick={() => setStatusFilter(st)}
              className={`px-3 py-1.5 rounded-xl text-xs font-semibold transition-colors ${
                statusFilter === st
                  ? 'bg-purple-600 text-white shadow-xs'
                  : 'bg-gray-50 text-gray-600 hover:bg-gray-100 border border-gray-200/60'
              }`}
            >
              {st === 'ALL' ? 'All Statuses' : st.replace('_', ' ')}
            </button>
          ))}
        </div>
      </div>

      {error && (
        <div className="p-4 bg-red-50 border border-red-200 rounded-xl text-xs text-red-700 flex items-center gap-2">
          <AlertCircle className="w-4 h-4" />
          <span>{error}</span>
        </div>
      )}

      {/* Case Monitoring Table */}
      <div className="bg-white rounded-3xl border border-gray-100 shadow-sm overflow-hidden">
        {loading ? (
          <div className="py-20 flex flex-col items-center justify-center text-gray-400 gap-2">
            <Loader2 className="w-7 h-7 animate-spin text-purple-600" />
            <span className="text-xs">Fetching case records from PostgreSQL...</span>
          </div>
        ) : cases.length === 0 ? (
          <div className="py-16 text-center text-gray-400 text-xs">
            No case records found in database matching criteria.
          </div>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-left text-xs text-gray-700">
              <thead className="bg-gray-50/80 border-b border-gray-100 text-gray-500 font-bold uppercase text-[10px] tracking-wider">
                <tr>
                  <th className="py-3.5 px-6">Case Number</th>
                  <th className="py-3.5 px-6">Dispute Subject</th>
                  <th className="py-3.5 px-6">Category</th>
                  <th className="py-3.5 px-6">Status</th>
                  <th className="py-3.5 px-6">Created On</th>
                  <th className="py-3.5 px-6 text-right">Action</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-100">
                {cases.map((c) => (
                  <tr key={c.id} className="hover:bg-gray-50/60 transition-colors">
                    <td className="py-4 px-6 font-mono font-bold text-blue-600">
                      {c.caseNumber}
                    </td>
                    <td className="py-4 px-6 font-bold text-[#123B63] max-w-xs truncate">
                      {c.title}
                    </td>
                    <td className="py-4 px-6 text-gray-600">
                      {c.categoryName || 'General'}
                    </td>
                    <td className="py-4 px-6">
                      <span className={`inline-block font-bold text-[10px] px-2.5 py-1 rounded-full ${
                        c.status === 'RESOLVED'
                          ? 'bg-green-50 text-green-700 border border-green-200'
                          : c.status === 'DOCUMENTS_PENDING'
                          ? 'bg-amber-50 text-amber-700 border border-amber-200'
                          : c.status === 'UNDER_REVIEW'
                          ? 'bg-purple-50 text-purple-700 border border-purple-200'
                          : 'bg-teal-50 text-teal-700 border border-teal-200'
                      }`}>
                        {c.status.replace('_', ' ')}
                      </span>
                    </td>
                    <td className="py-4 px-6 text-gray-400 font-mono text-[11px]">
                      {new Date(c.createdAt).toLocaleDateString()}
                    </td>
                    <td className="py-4 px-6 text-right">
                      <button
                        onClick={() => navigate(`/citizen/cases/${c.id}`)}
                        className="text-xs font-bold text-purple-600 hover:underline inline-flex items-center gap-1"
                      >
                        <span>Inspect</span>
                        <ChevronRight className="w-3.5 h-3.5" />
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default CaseMonitoringPage;
