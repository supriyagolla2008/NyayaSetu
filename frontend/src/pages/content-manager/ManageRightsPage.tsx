import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Plus, Search, Filter, Edit, CheckCircle, Eye, ChevronRight, Loader2, AlertCircle, ShieldCheck } from 'lucide-react';
import cmService from '../../services/cmService';
import { LegalRightSummary } from '../../types';

export const ManageRightsPage: React.FC = () => {
  const navigate = useNavigate();
  const [rights, setRights] = useState<LegalRightSummary[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [statusFilter, setStatusFilter] = useState('All');

  const fetchRights = async () => {
    setLoading(true);
    setError(null);
    try {
      const res = await cmService.getContentRights({
        query: searchTerm.trim() || undefined,
        status: statusFilter !== 'All' ? statusFilter : undefined,
        size: 100,
      });
      setRights(res.content || []);
    } catch (err: any) {
      setError(err.message || 'Failed to fetch content rights from database.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRights();
  }, [statusFilter]);

  const handleSearchSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    fetchRights();
  };

  return (
    <div className="max-w-7xl mx-auto space-y-6 pb-12">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">Manage Statutory Rights</h1>
          <p className="text-xs text-gray-500 mt-1">
            Curate, verify statutory citations, and publish citizen legal knowledge in PostgreSQL.
          </p>
        </div>
      </div>

      {/* Filter and Search Bar */}
      <div className="bg-white rounded-2xl p-4 border border-gray-100 shadow-sm flex flex-col md:flex-row items-center justify-between gap-4">
        <form onSubmit={handleSearchSubmit} className="relative flex-1 w-full">
          <Search className="w-4 h-4 text-gray-400 absolute left-3.5 top-1/2 -translate-y-1/2" />
          <input
            type="text"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            placeholder="Search rights across 14 categories..."
            className="w-full pl-9 pr-20 py-2 rounded-xl border border-gray-200 text-xs focus:outline-none focus:ring-2 focus:ring-teal-500 bg-gray-50/50"
          />
          <button
            type="submit"
            className="absolute right-1.5 top-1/2 -translate-y-1/2 bg-teal-600 text-white text-[11px] font-semibold px-2.5 py-1 rounded-lg"
          >
            Search
          </button>
        </form>

        <div className="flex items-center gap-2">
          {['All', 'PUBLISHED', 'UNDER_REVIEW', 'DRAFT'].map(st => (
            <button
              key={st}
              onClick={() => setStatusFilter(st)}
              className={`px-3 py-1.5 rounded-xl text-xs font-semibold transition-colors ${
                statusFilter === st
                  ? 'bg-teal-600 text-white shadow-xs'
                  : 'bg-gray-50 text-gray-600 hover:bg-gray-100 border border-gray-200/60'
              }`}
            >
              {st}
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

      {/* Rights Table */}
      <div className="bg-white rounded-3xl border border-gray-100 shadow-sm overflow-hidden">
        {loading ? (
          <div className="py-20 flex flex-col items-center justify-center text-gray-400 gap-2">
            <Loader2 className="w-7 h-7 animate-spin text-teal-600" />
            <span className="text-xs">Fetching legal rights from PostgreSQL...</span>
          </div>
        ) : rights.length === 0 ? (
          <div className="py-16 text-center text-gray-400 text-xs">
            No rights found matching the specified filters.
          </div>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-left text-xs text-gray-700">
              <thead className="bg-gray-50/80 border-b border-gray-100 text-gray-500 font-bold uppercase text-[10px] tracking-wider">
                <tr>
                  <th className="py-3.5 px-6">Title & Summary</th>
                  <th className="py-3.5 px-6">Category</th>
                  <th className="py-3.5 px-6">Applicable Law</th>
                  <th className="py-3.5 px-6">Verification</th>
                  <th className="py-3.5 px-6 text-right">Action</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-100">
                {rights.map((r) => (
                  <tr key={r.id} className="hover:bg-gray-50/60 transition-colors">
                    <td className="py-4 px-6 max-w-sm">
                      <div className="font-bold text-[#123B63] hover:text-teal-700 cursor-pointer" onClick={() => navigate(`/citizen/rights/${r.slug || r.id}`)}>
                        {r.title}
                      </div>
                      <p className="text-gray-400 text-[11px] mt-0.5 line-clamp-1">{r.shortDescription || r.simpleExplanation}</p>
                    </td>
                    <td className="py-4 px-6">
                      <span className="inline-block text-[10px] font-bold text-teal-800 bg-teal-50 px-2 py-0.5 rounded-full">
                        {r.categoryName || 'General'}
                      </span>
                    </td>
                    <td className="py-4 px-6 text-gray-600 text-[11px] font-medium">
                      {r.applicableLaw || 'India Code'}
                    </td>
                    <td className="py-4 px-6">
                      <span className="inline-flex items-center gap-1 text-[10px] font-bold px-2.5 py-0.5 rounded-full bg-green-50 text-green-700 border border-green-200">
                        <ShieldCheck className="w-3 h-3" />
                        {r.verificationStatus || 'VERIFIED'}
                      </span>
                    </td>
                    <td className="py-4 px-6 text-right">
                      <button
                        onClick={() => navigate(`/citizen/rights/${r.slug || r.id}`)}
                        className="text-xs font-bold text-teal-700 hover:underline inline-flex items-center gap-1"
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

export default ManageRightsPage;
