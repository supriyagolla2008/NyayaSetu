import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Plus, Search, Sparkles, Edit, Eye, Calendar, ExternalLink } from 'lucide-react';
import { MOCK_LEGAL_UPDATES } from '../../utils/mockData';

export const ManageLegalUpdatesPage: React.FC = () => {
  const navigate = useNavigate();

  return (
    <div className="max-w-7xl mx-auto space-y-6">
      {/* Header Matching Screen 14 */}
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">Legal Updates Management</h1>
          <p className="text-xs text-gray-500 mt-1">Publish official gazette notifications and statutory amendments to citizen feeds.</p>
        </div>
        <button
          onClick={() => alert('Add Legal Update modal opening...')}
          className="flex items-center gap-2 bg-[#2563EB] hover:bg-blue-700 text-white font-bold px-4 py-2.5 rounded-xl text-xs shadow-xs"
        >
          <Plus className="w-4 h-4" />
          <span>Add Legal Update</span>
        </button>
      </div>

      {/* Table Matching Screen 14 */}
      <div className="bg-white rounded-3xl border border-gray-100 shadow-sm overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-left text-xs text-gray-700">
            <thead className="bg-gray-50/80 border-b border-gray-100 text-gray-500 font-bold uppercase text-[10px] tracking-wider">
              <tr>
                <th className="py-3.5 px-6">Title</th>
                <th className="py-3.5 px-6">Category</th>
                <th className="py-3.5 px-6">Status</th>
                <th className="py-3.5 px-6">Last Updated</th>
                <th className="py-3.5 px-6 text-right">Actions</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-100 font-medium">
              {MOCK_LEGAL_UPDATES.map((upd) => (
                <tr key={upd.id} className="hover:bg-blue-50/20 transition-colors">
                  <td className="py-4 px-6">
                    <span className="font-bold text-[#123B63] block text-sm">{upd.title}</span>
                    <span className="text-[11px] text-gray-400 block mt-0.5">{upd.summary}</span>
                  </td>
                  <td className="py-4 px-6 font-semibold text-gray-600">{upd.category}</td>
                  <td className="py-4 px-6">
                    <span className={`inline-flex items-center px-2.5 py-1 rounded-full text-[10px] font-bold ${
                      upd.status === 'Published'
                        ? 'bg-green-50 text-green-700 border border-green-200'
                        : upd.status === 'Under Review'
                        ? 'bg-amber-50 text-amber-700 border border-amber-200'
                        : 'bg-blue-50 text-blue-700 border border-blue-200'
                    }`}>
                      {upd.status}
                    </span>
                  </td>
                  <td className="py-4 px-6 font-mono text-gray-500 text-[11px]">{upd.lastUpdated}</td>
                  <td className="py-4 px-6 text-right">
                    <div className="flex items-center justify-end gap-2">
                      <button className="p-1.5 rounded-lg border border-gray-200 hover:bg-gray-50 text-gray-700">
                        <Edit className="w-3.5 h-3.5" />
                      </button>
                      <button className="p-1.5 rounded-lg border border-gray-200 hover:bg-gray-50 text-gray-700">
                        <Eye className="w-3.5 h-3.5" />
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default ManageLegalUpdatesPage;
