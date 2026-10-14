import React from 'react';
import { Search, Bot, Briefcase, Zap, TrendingUp } from 'lucide-react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

export const AnalyticsPage: React.FC = () => {
  const aiUsageData = [
    { day: 'Mon', queries: 140 },
    { day: 'Tue', queries: 210 },
    { day: 'Wed', queries: 180 },
    { day: 'Thu', queries: 260 },
    { day: 'Fri', queries: 230 },
    { day: 'Sat', queries: 170 },
    { day: 'Sun', queries: 190 },
  ];

  return (
    <div className="max-w-7xl mx-auto space-y-6">
      {/* Header Matching Screen 20 */}
      <div>
        <h1 className="text-2xl font-black text-[#123B63]">Platform Analytics</h1>
        <p className="text-xs text-gray-500 mt-1">Real-time metrics on legal queries, AI RAG utilization, and system latencies.</p>
      </div>

      {/* 4 Stat Cards Matching Screen 20 */}
      <div className="grid grid-cols-2 lg:grid-cols-4 gap-4">
        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-400">Search Queries</span>
            <div className="text-2xl font-black text-[#123B63]">1,250</div>
            <span className="text-[10px] text-green-600 font-bold">↑ 8.4% this week</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-blue-50 text-[#2563EB] flex items-center justify-center">
            <Search className="w-5 h-5" />
          </div>
        </div>

        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-400">AI Conversations</span>
            <div className="text-2xl font-black text-[#16A34A]">830</div>
            <span className="text-[10px] text-green-600 font-bold">↑ 12.1% this week</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-green-50 text-[#16A34A] flex items-center justify-center">
            <Bot className="w-5 h-5" />
          </div>
        </div>

        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-400">Active Cases</span>
            <div className="text-2xl font-black text-[#7C3AED]">120</div>
            <span className="text-[10px] text-gray-400">Citizens guided</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-purple-50 text-[#7C3AED] flex items-center justify-center">
            <Briefcase className="w-5 h-5" />
          </div>
        </div>

        <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm flex items-center justify-between">
          <div>
            <span className="text-xs font-semibold text-gray-400">Response Time</span>
            <div className="text-2xl font-black text-[#F59E0B]">1.2s</div>
            <span className="text-[10px] text-green-600 font-bold">⚡ Optimal RAG</span>
          </div>
          <div className="w-11 h-11 rounded-xl bg-amber-50 text-[#F59E0B] flex items-center justify-center">
            <Zap className="w-5 h-5" />
          </div>
        </div>
      </div>

      {/* AI Usage Last 7 Days Bar Chart Matching Screen 20 */}
      <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm">
        <div className="flex items-center justify-between mb-6">
          <div>
            <h2 className="text-sm font-bold text-[#123B63]">AI Usage (Last 7 Days)</h2>
            <span className="text-[11px] text-gray-400">Number of citizen problem queries processed by RAG pipeline</span>
          </div>
          <span className="text-xs font-bold text-[#2563EB] bg-blue-50 px-2.5 py-1 rounded-lg">Average: 197 / day</span>
        </div>

        <div className="h-72 w-full">
          <ResponsiveContainer width="100%" height="100%">
            <BarChart data={aiUsageData}>
              <CartesianGrid strokeDasharray="3 3" vertical={false} stroke="#F1F5F9" />
              <XAxis dataKey="day" stroke="#94A3B8" fontSize={12} tickLine={false} />
              <YAxis stroke="#94A3B8" fontSize={12} tickLine={false} />
              <Tooltip cursor={{ fill: '#F0F9FF' }} />
              <Bar dataKey="queries" fill="#2563EB" radius={[6, 6, 0, 0]} />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>
    </div>
  );
};

export default AnalyticsPage;
