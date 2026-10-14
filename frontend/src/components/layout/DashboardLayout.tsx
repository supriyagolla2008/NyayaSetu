import React from 'react';
import { Outlet, useNavigate, useLocation } from 'react-router-dom';
import { Sidebar } from './Sidebar';
import { Search, Bell, Globe, ChevronDown, Plus } from 'lucide-react';
import { useAuth } from '../../contexts/AuthContext';

export const DashboardLayout: React.FC = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  const isCitizen = location.pathname.startsWith('/citizen');

  return (
    <div className="flex min-h-screen bg-[#EFF8FF]">
      {/* Sidebar */}
      <Sidebar />

      {/* Main Content Area */}
      <div className="flex-1 flex flex-col min-w-0">
        {/* Top Header Bar matching Screens 6-20 */}
        <header className="h-16 bg-white border-b border-gray-100 flex items-center justify-between px-6 sticky top-[33px] z-30">
          {/* Search bar */}
          <div className="flex-1 max-w-md">
            <div className="relative">
              <Search className="w-4 h-4 text-gray-400 absolute left-3.5 top-1/2 -translate-y-1/2" />
              <input
                type="text"
                placeholder="Search rights, laws, services..."
                className="w-full bg-gray-50 border border-gray-200/80 rounded-xl pl-9 pr-4 py-2 text-xs focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-800"
              />
            </div>
          </div>

          {/* Right actions */}
          <div className="flex items-center gap-4">
            {/* Language Switcher */}
            <div className="hidden sm:flex items-center gap-1.5 text-xs text-gray-600 font-semibold bg-gray-50 px-2.5 py-1.5 rounded-lg border border-gray-200 cursor-pointer">
              <Globe className="w-3.5 h-3.5 text-gray-500" />
              <span>EN</span>
              <ChevronDown className="w-3 h-3 text-gray-400" />
            </div>

            {/* Notification Bell */}
            <button 
              onClick={() => navigate('/citizen/notifications')}
              className="relative p-2 rounded-xl text-gray-500 hover:bg-gray-100 transition-colors"
            >
              <Bell className="w-4 h-4" />
              <span className="absolute top-1.5 right-1.5 w-2 h-2 rounded-full bg-red-500"></span>
            </button>

            {/* Quick Action Button for Citizen */}
            {isCitizen && (
              <button
                onClick={() => navigate('/citizen/problem')}
                className="hidden md:flex items-center gap-1.5 bg-[#2563EB] hover:bg-blue-700 text-white px-3.5 py-1.5 rounded-xl text-xs font-semibold shadow-xs transition-colors"
              >
                <Plus className="w-3.5 h-3.5" />
                <span>I Have a Problem</span>
              </button>
            )}

            {/* Profile Avatar pill */}
            <div className="flex items-center gap-2 pl-2 border-l border-gray-100">
              <div className="w-8 h-8 rounded-full bg-gradient-to-tr from-blue-600 to-indigo-600 text-white font-bold flex items-center justify-center text-xs shadow-xs">
                {user?.fullName?.charAt(0) || 'P'}
              </div>
              <div className="hidden lg:block text-left">
                <div className="text-xs font-bold text-[#123B63] leading-tight">{user?.fullName || 'Priya Sharma'}</div>
                <div className="text-[10px] text-gray-400 capitalize">{user?.role?.toLowerCase().replace('_', ' ') || 'Citizen'}</div>
              </div>
            </div>
          </div>
        </header>

        {/* Dynamic Page Content with Ice-Blue background */}
        <main className="flex-1 p-6 overflow-y-auto">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default DashboardLayout;
