import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { Layers, ChevronRight, Sparkles } from 'lucide-react';

export const SCREENS = [
  { id: 1, name: '1. Landing Page', path: '/', role: 'public' },
  { id: 2, name: '2. Role Selection', path: '/select-role', role: 'public' },
  { id: 3, name: '3. Citizen Login', path: '/login/citizen', role: 'auth' },
  { id: 4, name: '4. Content Mgr Login', path: '/login/content-manager', role: 'auth' },
  { id: 5, name: '5. Admin Login', path: '/login/admin', role: 'auth' },
  { id: 6, name: '6. Citizen Dashboard', path: '/citizen/dashboard', role: 'citizen' },
  { id: 7, name: '7. I Have a Problem', path: '/citizen/problem', role: 'citizen' },
  { id: 8, name: '8. AI Assistant Result', path: '/citizen/ai-assistant', role: 'citizen' },
  { id: 9, name: '9. Action Plan & Checklist', path: '/citizen/action-plan/case-1', role: 'citizen' },
  { id: 10, name: '10. Case Details (#RF1024)', path: '/citizen/cases/case-1', role: 'citizen' },
  { id: 11, name: '11. Content Mgr Dashboard', path: '/content-manager/dashboard', role: 'content' },
  { id: 12, name: '12. Manage Rights', path: '/content-manager/rights', role: 'content' },
  { id: 13, name: '13. Content Editor/Verify', path: '/content-manager/rights/right-1/edit', role: 'content' },
  { id: 14, name: '14. Legal Updates Mgr', path: '/content-manager/legal-updates', role: 'content' },
  { id: 15, name: '15. RAG & Embeddings', path: '/content-manager/rag', role: 'content' },
  { id: 16, name: '16. Admin Dashboard', path: '/admin/dashboard', role: 'admin' },
  { id: 17, name: '17. User Management', path: '/admin/users', role: 'admin' },
  { id: 18, name: '18. Roles & Permissions', path: '/admin/roles', role: 'admin' },
  { id: 19, name: '19. Case Monitoring', path: '/admin/cases', role: 'admin' },
  { id: 20, name: '20. Platform Analytics', path: '/admin/analytics', role: 'admin' },
];

export const ScreenShowcaseBar: React.FC = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [collapsed, setCollapsed] = useState(false);

  const currentScreen = SCREENS.find(s => s.path === location.pathname);

  return (
    <div className="sticky top-0 z-50 bg-[#123B63] text-white border-b border-blue-900/50 shadow-md">
      <div className="max-w-7xl mx-auto px-3 py-1.5 flex items-center justify-between text-xs">
        <div className="flex items-center gap-2">
          <span className="flex items-center gap-1.5 font-bold tracking-wide text-blue-200 uppercase text-[11px] bg-blue-950/60 px-2 py-0.5 rounded border border-blue-400/20">
            <Sparkles className="w-3.5 h-3.5 text-amber-400" />
            NyayaSetu (न्यायसेतु) Live Showcase
          </span>
          <span className="text-gray-300 hidden sm:inline">|</span>
          <span className="text-gray-200 hidden md:inline">
            Active Screen: <strong className="text-white">{currentScreen?.name || 'Navigation View'}</strong>
          </span>
        </div>

        <div className="flex items-center gap-2">
          <div className="flex items-center gap-1 overflow-x-auto max-w-[600px] scrollbar-none py-0.5">
            <select
              value={location.pathname}
              onChange={(e) => navigate(e.target.value)}
              className="bg-blue-900/80 hover:bg-blue-900 border border-blue-400/40 text-white rounded px-2.5 py-1 text-xs font-medium focus:outline-none focus:ring-1 focus:ring-blue-400 cursor-pointer"
            >
              <optgroup label="Public Flow">
                {SCREENS.filter(s => s.role === 'public').map(s => (
                  <option key={s.id} value={s.path}>{s.name}</option>
                ))}
              </optgroup>
              <optgroup label="Authentication">
                {SCREENS.filter(s => s.role === 'auth').map(s => (
                  <option key={s.id} value={s.path}>{s.name}</option>
                ))}
              </optgroup>
              <optgroup label="Citizen Flow">
                {SCREENS.filter(s => s.role === 'citizen').map(s => (
                  <option key={s.id} value={s.path}>{s.name}</option>
                ))}
              </optgroup>
              <optgroup label="Content Manager Flow">
                {SCREENS.filter(s => s.role === 'content').map(s => (
                  <option key={s.id} value={s.path}>{s.name}</option>
                ))}
              </optgroup>
              <optgroup label="Administrator Flow">
                {SCREENS.filter(s => s.role === 'admin').map(s => (
                  <option key={s.id} value={s.path}>{s.name}</option>
                ))}
              </optgroup>
            </select>
          </div>

          <button
            onClick={() => {
              const curIdx = SCREENS.findIndex(s => s.path === location.pathname);
              const nextIdx = (curIdx + 1) % SCREENS.length;
              navigate(SCREENS[nextIdx].path);
            }}
            className="flex items-center gap-1 bg-[#2563EB] hover:bg-blue-600 text-white px-2.5 py-1 rounded text-xs font-semibold shadow-sm transition-colors"
            title="Next Screen in prototype journey"
          >
            <span>Next</span>
            <ChevronRight className="w-3.5 h-3.5" />
          </button>
        </div>
      </div>
    </div>
  );
};
