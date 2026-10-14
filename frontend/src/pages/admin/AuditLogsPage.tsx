import React, { useState, useEffect } from 'react';
import { Shield, Clock, Search, Loader2, AlertCircle, FileText, CheckCircle } from 'lucide-react';
import adminService from '../../services/adminService';
import { AuditLogItem } from '../../types';

export const AuditLogsPage: React.FC = () => {
  const [logs, setLogs] = useState<AuditLogItem[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const fetchLogs = async () => {
    setLoading(true);
    setError(null);
    try {
      const res = await adminService.getAuditLogs({ size: 100 });
      setLogs(res.content || []);
    } catch (err: any) {
      setError(err.message || 'Failed to fetch audit records.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchLogs();
  }, []);

  return (
    <div className="max-w-7xl mx-auto space-y-6 pb-12">
      <div>
        <h1 className="text-2xl font-black text-[#123B63]">Security & System Audit Trail</h1>
        <p className="text-xs text-gray-500 mt-1">
          Immutable logging of citizen logins, registrations, case status changes, and administrator operations.
        </p>
      </div>

      {error && (
        <div className="p-4 bg-red-50 border border-red-200 rounded-xl text-xs text-red-700 flex items-center gap-2">
          <AlertCircle className="w-4 h-4" />
          <span>{error}</span>
        </div>
      )}

      <div className="bg-white rounded-3xl border border-gray-100 shadow-sm overflow-hidden">
        {loading ? (
          <div className="py-20 flex flex-col items-center justify-center text-gray-400 gap-2">
            <Loader2 className="w-7 h-7 animate-spin text-purple-600" />
            <span className="text-xs">Fetching audit logs from PostgreSQL...</span>
          </div>
        ) : logs.length === 0 ? (
          <div className="py-16 text-center text-gray-400 text-xs">
            No audit logs currently recorded. Logins and actions will appear here automatically.
          </div>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-left text-xs text-gray-700">
              <thead className="bg-gray-50/80 border-b border-gray-100 text-gray-500 font-bold uppercase text-[10px] tracking-wider">
                <tr>
                  <th className="py-3.5 px-6">Timestamp</th>
                  <th className="py-3.5 px-6">Action</th>
                  <th className="py-3.5 px-6">Actor / User</th>
                  <th className="py-3.5 px-6">Entity</th>
                  <th className="py-3.5 px-6">Audit Details</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-100">
                {logs.map((log) => (
                  <tr key={log.id} className="hover:bg-gray-50/60 transition-colors">
                    <td className="py-4 px-6 text-gray-400 font-mono text-[11px] whitespace-nowrap">
                      {new Date(log.createdAt).toLocaleString()}
                    </td>
                    <td className="py-4 px-6">
                      <span className={`inline-block font-mono font-bold text-[10px] px-2 py-0.5 rounded-full ${
                        log.action.includes('LOGIN')
                          ? 'bg-blue-50 text-blue-700 border border-blue-200'
                          : log.action.includes('REGISTER')
                          ? 'bg-green-50 text-green-700 border border-green-200'
                          : 'bg-purple-50 text-purple-700 border border-purple-200'
                      }`}>
                        {log.action}
                      </span>
                    </td>
                    <td className="py-4 px-6 font-medium text-gray-800">
                      {log.userName || log.userEmail || 'System / Anonymous'}
                    </td>
                    <td className="py-4 px-6 font-mono text-gray-500">
                      {log.entityType} {log.entityId ? `#${log.entityId}` : ''}
                    </td>
                    <td className="py-4 px-6 text-gray-600 max-w-xs truncate">
                      {log.details || '—'}
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

export default AuditLogsPage;
