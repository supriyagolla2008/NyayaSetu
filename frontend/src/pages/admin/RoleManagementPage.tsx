import React from 'react';
import { Shield, User, FileText, CheckCircle2, Lock } from 'lucide-react';

export const RoleManagementPage: React.FC = () => {
  const roles = [
    {
      name: 'CITIZEN',
      userCount: 280,
      description: 'End-user Indian citizens seeking rights awareness and guidance.',
      color: 'border-blue-200 bg-blue-50/30',
      badge: 'bg-blue-50 text-blue-700',
      icon: User,
      permissions: [
        'Submit legal problem via text or voice',
        'Receive AI grounded legal advice & citations',
        'Create and track personalized cases',
        'Upload supporting dispute documents',
        'Receive proactive legal updates and notifications'
      ]
    },
    {
      name: 'CONTENT_MANAGER',
      userCount: 25,
      description: 'Legal curators and paralegal staff managing knowledge base.',
      color: 'border-teal-200 bg-teal-50/30',
      badge: 'bg-teal-50 text-teal-700',
      icon: FileText,
      permissions: [
        'Author and curate legal rights and statutory sections',
        'Verify official government source URLs (India Code, NALSA)',
        'Publish official legal amendments & updates',
        'Manage RAG knowledge base & trigger pgvector embeddings',
        'Review citizen feedback reports on legal content'
      ]
    },
    {
      name: 'ADMINISTRATOR',
      userCount: 15,
      description: 'Platform administrators and IT security managers.',
      color: 'border-purple-200 bg-purple-50/30',
      badge: 'bg-purple-50 text-purple-700',
      icon: Shield,
      permissions: [
        'User management and account activation/deactivation',
        'RBAC enforcement and security audit logs inspection',
        'Platform-wide case oversight & system configuration',
        'AI usage monitoring, API token limits and fallback toggles',
        'Database health and pgvector analytics'
      ]
    }
  ];

  return (
    <div className="max-w-7xl mx-auto space-y-6">
      {/* Header Matching Screen 18 */}
      <div>
        <h1 className="text-2xl font-black text-[#123B63]">Roles & Permissions</h1>
        <p className="text-xs text-gray-500 mt-1">Exact Three User Roles: CITIZEN, CONTENT_MANAGER, ADMINISTRATOR.</p>
      </div>

      {/* 3 Role Cards Grid Matching Screen 18 */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        {roles.map((r, i) => {
          const Icon = r.icon;
          return (
            <div key={i} className={`bg-white rounded-3xl p-6 border ${r.color} shadow-sm space-y-4 flex flex-col justify-between`}>
              <div>
                <div className="flex items-center justify-between mb-3">
                  <div className="w-10 h-10 rounded-xl bg-gray-50 flex items-center justify-center text-[#123B63]">
                    <Icon className="w-5 h-5" />
                  </div>
                  <span className={`text-[10px] font-bold px-2 py-0.5 rounded-full ${r.badge}`}>
                    {r.userCount} Users
                  </span>
                </div>

                <h2 className="text-base font-bold text-[#123B63]">{r.name}</h2>
                <p className="text-xs text-gray-500 mt-1">{r.description}</p>

                <div className="mt-5 pt-4 border-t border-gray-100 space-y-2.5">
                  <span className="text-[10px] font-bold text-gray-400 uppercase tracking-wider block">Assigned Permissions</span>
                  {r.permissions.map((perm, idx) => (
                    <div key={idx} className="flex items-start gap-2 text-xs text-gray-700">
                      <CheckCircle2 className="w-3.5 h-3.5 text-green-600 shrink-0 mt-0.5" />
                      <span className="leading-tight">{perm}</span>
                    </div>
                  ))}
                </div>
              </div>

              <div className="pt-4 border-t border-gray-100">
                <span className="text-[11px] text-gray-400 flex items-center gap-1">
                  <Lock className="w-3 h-3" /> Enforced by Spring Security RBAC
                </span>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default RoleManagementPage;
