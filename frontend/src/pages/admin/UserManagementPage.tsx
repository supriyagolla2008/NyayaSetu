import React, { useState, useEffect } from 'react';
import { Plus, Search, UserCheck, UserX, Edit, MoreVertical, Shield, Loader2, AlertCircle } from 'lucide-react';
import adminService from '../../services/adminService';
import { User } from '../../types';

export const UserManagementPage: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [roleFilter, setRoleFilter] = useState('');

  const fetchUsers = async () => {
    setLoading(true);
    setError(null);
    try {
      const res = await adminService.getUsers({
        query: searchTerm.trim() || undefined,
        role: roleFilter || undefined,
        size: 50,
      });
      setUsers(res.content || []);
    } catch (err: any) {
      setError(err.message || 'Failed to fetch users from database.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, [roleFilter]);

  const handleSearchSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    fetchUsers();
  };

  return (
    <div className="max-w-7xl mx-auto space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">User Directory & Roles</h1>
          <p className="text-xs text-gray-500 mt-1">
            Real user records retrieved directly from PostgreSQL (`users` and `user_roles` tables).
          </p>
        </div>
      </div>

      {/* Search and Filters */}
      <div className="bg-white rounded-2xl p-4 border border-gray-100 shadow-sm flex flex-col md:flex-row items-center justify-between gap-3">
        <form onSubmit={handleSearchSubmit} className="relative flex-1 max-w-md w-full">
          <Search className="w-4 h-4 text-gray-400 absolute left-3.5 top-1/2 -translate-y-1/2" />
          <input
            type="text"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            placeholder="Search users by name or email..."
            className="w-full pl-9 pr-20 py-2 rounded-xl border border-gray-200 text-xs focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50/50"
          />
          <button
            type="submit"
            className="absolute right-1.5 top-1/2 -translate-y-1/2 bg-blue-600 text-white text-[11px] font-semibold px-2.5 py-1 rounded-lg"
          >
            Find
          </button>
        </form>

        <div className="flex items-center gap-2">
          {['', 'CITIZEN', 'CONTENT_MANAGER', 'ADMINISTRATOR'].map(r => (
            <button
              key={r}
              onClick={() => setRoleFilter(r)}
              className={`px-3 py-1.5 rounded-xl text-xs font-semibold transition-colors ${
                roleFilter === r
                  ? 'bg-purple-600 text-white shadow-xs'
                  : 'bg-gray-50 text-gray-600 hover:bg-gray-100 border border-gray-200/60'
              }`}
            >
              {r === '' ? 'All Roles' : r.replace('_', ' ')}
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

      {/* User Table */}
      <div className="bg-white rounded-3xl border border-gray-100 shadow-sm overflow-hidden">
        {loading ? (
          <div className="py-20 flex flex-col items-center justify-center text-gray-400 gap-2">
            <Loader2 className="w-7 h-7 animate-spin text-purple-600" />
            <span className="text-xs">Loading users from PostgreSQL...</span>
          </div>
        ) : users.length === 0 ? (
          <div className="py-12 text-center text-gray-400 text-xs">
            No user accounts found matching the criteria.
          </div>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-left text-xs text-gray-700">
              <thead className="bg-gray-50/80 border-b border-gray-100 text-gray-500 font-bold uppercase text-[10px] tracking-wider">
                <tr>
                  <th className="py-3.5 px-6">Name</th>
                  <th className="py-3.5 px-6">Email</th>
                  <th className="py-3.5 px-6">Assigned Roles</th>
                  <th className="py-3.5 px-6">Status</th>
                  <th className="py-3.5 px-6">Language / State</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-100">
                {users.map((u) => {
                  const roleName = (u.roles && u.roles.length > 0) ? u.roles.join(', ') : (u.role || 'CITIZEN');
                  return (
                    <tr key={u.id} className="hover:bg-gray-50/60 transition-colors">
                      <td className="py-4 px-6 font-bold text-[#123B63] flex items-center gap-2.5">
                        <div className="w-8 h-8 rounded-full bg-purple-100 text-purple-700 font-bold flex items-center justify-center text-xs">
                          {u.fullName?.charAt(0) || 'U'}
                        </div>
                        <span>{u.fullName}</span>
                      </td>
                      <td className="py-4 px-6 text-gray-600 font-mono text-[11px]">{u.email}</td>
                      <td className="py-4 px-6">
                        <span className={`inline-flex items-center gap-1 font-bold px-2.5 py-1 rounded-full text-[10px] ${
                          roleName.includes('ADMINISTRATOR')
                            ? 'bg-purple-50 text-purple-700 border border-purple-200'
                            : roleName.includes('CONTENT_MANAGER')
                            ? 'bg-teal-50 text-teal-700 border border-teal-200'
                            : 'bg-blue-50 text-blue-700 border border-blue-200'
                        }`}>
                          <Shield className="w-3 h-3" />
                          {roleName.replace(/ROLE_/g, '')}
                        </span>
                      </td>
                      <td className="py-4 px-6">
                        <span className="inline-flex items-center gap-1 text-[10px] font-bold px-2 py-0.5 rounded-full bg-green-50 text-green-700 border border-green-200">
                          <UserCheck className="w-3 h-3" />
                          {u.status || 'ACTIVE'}
                        </span>
                      </td>
                      <td className="py-4 px-6 text-gray-500">
                        {u.preferredLanguage?.toUpperCase() || 'EN'} • {u.stateLocation || 'India'}
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default UserManagementPage;
