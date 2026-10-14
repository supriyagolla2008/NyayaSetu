import React from 'react';
import { NavLink, useNavigate, useLocation } from 'react-router-dom';
import {
  Scale,
  LayoutDashboard,
  HelpCircle,
  Bot,
  BookOpen,
  Search,
  Briefcase,
  Bell,
  Sparkles,
  Bookmark,
  User,
  Settings,
  Shield,
  FileText,
  Database,
  BarChart2,
  Users,
  LogOut,
  FolderOpen,
  CheckSquare
} from 'lucide-react';
import { useAuth } from '../../contexts/AuthContext';

export const Sidebar: React.FC = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  // Determine current role context based on URL or auth state
  const isCitizen = location.pathname.startsWith('/citizen') || user?.role === 'CITIZEN';
  const isContentManager = location.pathname.startsWith('/content-manager') || user?.role === 'CONTENT_MANAGER';
  const isAdmin = location.pathname.startsWith('/admin') || user?.role === 'ADMINISTRATOR';

  const citizenNav = [
    { name: 'Dashboard', path: '/citizen/dashboard', icon: LayoutDashboard },
    { name: 'I Have a Problem', path: '/citizen/problem', icon: HelpCircle, highlight: true },
    { name: 'AI Assistant', path: '/citizen/ai-assistant', icon: Bot },
    { name: 'Legal Library', path: '/citizen/rights', icon: BookOpen },
    { name: 'Search', path: '/citizen/search', icon: Search },
    { name: 'My Cases', path: '/citizen/cases', icon: Briefcase },
    { name: 'Documents', path: '/citizen/documents', icon: FolderOpen },
    { name: 'Notifications', path: '/citizen/notifications', icon: Bell },
    { name: 'Legal Updates', path: '/citizen/legal-updates', icon: Sparkles },
    { name: 'Bookmarks', path: '/citizen/bookmarks', icon: Bookmark },
    { name: 'Profile', path: '/citizen/profile', icon: User },
    { name: 'Settings', path: '/citizen/settings', icon: Settings },
  ];

  const contentNav = [
    { name: 'Dashboard', path: '/content-manager/dashboard', icon: LayoutDashboard },
    { name: 'Rights', path: '/content-manager/rights', icon: BookOpen },
    { name: 'Laws', path: '/content-manager/laws', icon: FileText },
    { name: 'Provisions', path: '/content-manager/provisions', icon: FileText },
    { name: 'Services', path: '/content-manager/services', icon: Briefcase },
    { name: 'Authorities', path: '/content-manager/authorities', icon: Shield },
    { name: 'Legal Updates', path: '/content-manager/legal-updates', icon: Sparkles },
    { name: 'Sources', path: '/content-manager/sources', icon: Database },
    { name: 'Verification', path: '/content-manager/verification', icon: CheckSquare },
    { name: 'Reports', path: '/content-manager/reports', icon: FileText },
    { name: 'RAG Knowledge', path: '/content-manager/rag', icon: Bot },
    { name: 'Analytics', path: '/content-manager/analytics', icon: BarChart2 },
  ];

  const adminNav = [
    { name: 'Dashboard', path: '/admin/dashboard', icon: LayoutDashboard },
    { name: 'Users', path: '/admin/users', icon: Users },
    { name: 'Roles', path: '/admin/roles', icon: Shield },
    { name: 'Cases', path: '/admin/cases', icon: Briefcase },
    { name: 'Notifications', path: '/admin/notifications', icon: Bell },
    { name: 'Feedback', path: '/admin/feedback', icon: CheckSquare },
    { name: 'Analytics', path: '/admin/analytics', icon: BarChart2 },
    { name: 'Audit Logs', path: '/admin/audit-logs', icon: FileText },
    { name: 'Settings', path: '/admin/settings', icon: Settings },
    { name: 'AI Monitoring', path: '/admin/ai-monitoring', icon: Bot },
  ];

  const navItems = isContentManager ? contentNav : isAdmin ? adminNav : citizenNav;
  const roleTitle = isContentManager ? 'Content Manager' : isAdmin ? 'Administrator' : 'Citizen';

  return (
    <aside className="w-64 bg-white border-r border-gray-100 flex flex-col shrink-0 min-h-screen">
      {/* Brand Logo */}
      <div className="p-4 border-b border-gray-100 flex items-center justify-between">
        <div className="flex items-center gap-2.5 cursor-pointer" onClick={() => navigate('/')}>
          <div className="w-8 h-8 rounded-xl bg-[#2563EB] flex items-center justify-center text-white shadow-xs">
            <Scale className="w-5 h-5" />
          </div>
          <div>
            <span className="font-extrabold text-[#123B63] text-base tracking-tight">NyayaSetu</span>
            <span className="block text-[9px] uppercase tracking-wider text-blue-600 font-bold">{roleTitle}</span>
          </div>
        </div>
      </div>

      {/* Navigation List */}
      <nav className="flex-1 px-3 py-4 space-y-1 overflow-y-auto">
        {navItems.map((item) => {
          const Icon = item.icon;
          const isActive = location.pathname === item.path || (item.path !== '/' && location.pathname.startsWith(item.path));
          return (
            <NavLink
              key={item.path}
              to={item.path}
              className={`flex items-center gap-3 px-3 py-2 rounded-xl text-xs font-semibold transition-all ${
                isActive
                  ? 'bg-blue-50 text-[#2563EB] shadow-xs'
                  : 'text-gray-600 hover:text-[#123B63] hover:bg-gray-50'
              }`}
            >
              <Icon className={`w-4 h-4 ${isActive ? 'text-[#2563EB]' : 'text-gray-400'}`} />
              <span>{item.name}</span>
            </NavLink>
          );
        })}
      </nav>

      {/* User info at bottom */}
      <div className="p-3 border-t border-gray-100 bg-gray-50/50">
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2.5 overflow-hidden">
            <div className="w-8 h-8 rounded-full bg-blue-100 text-[#2563EB] font-bold flex items-center justify-center text-xs shrink-0">
              {user?.fullName?.charAt(0) || 'P'}
            </div>
            <div className="truncate text-left">
              <div className="text-xs font-bold text-[#123B63] truncate">{user?.fullName || 'Priya Sharma'}</div>
              <div className="text-[10px] text-gray-500 truncate">{user?.email || 'citizen@rightsflow.com'}</div>
            </div>
          </div>
          <button
            onClick={() => {
              logout();
              navigate('/select-role');
            }}
            title="Log out"
            className="p-1.5 rounded-lg text-gray-400 hover:text-red-600 hover:bg-red-50 transition-colors"
          >
            <LogOut className="w-4 h-4" />
          </button>
        </div>
      </div>
    </aside>
  );
};

export default Sidebar;
