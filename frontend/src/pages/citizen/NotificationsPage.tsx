import React from 'react';
import { Bell, Clock, Sparkles, Briefcase, CheckCircle2 } from 'lucide-react';

export const NotificationsPage: React.FC = () => {
  const notifications = [
    { id: 1, title: 'Action Reminder', message: 'Upload salary slips and employment documents for case #RF1024.', time: '1 day ago', type: 'reminder' },
    { id: 2, title: 'Legal Update Published', message: 'New Amendment in Consumer Protection Rules published by Government of India.', time: '2 days ago', type: 'update' },
    { id: 3, title: 'Case Status Changed', message: 'Case #RF1024 moved to Documents Pending.', time: '3 days ago', type: 'status' },
    { id: 4, title: 'Free Legal Aid Advisory', message: 'District Legal Services Authority (DLSA) camp scheduled this Saturday.', time: '5 days ago', type: 'update' },
  ];

  return (
    <div className="max-w-4xl mx-auto space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">Notifications</h1>
          <p className="text-xs text-gray-500 mt-1">Real-time alerts regarding your cases, document reminders, and legal updates.</p>
        </div>
        <button 
          onClick={() => alert('All notifications marked as read')}
          className="text-xs font-bold text-[#2563EB] hover:underline"
        >
          Mark all as read
        </button>
      </div>

      <div className="space-y-3">
        {notifications.map((n) => (
          <div key={n.id} className="bg-white rounded-2xl p-4 border border-gray-100 shadow-sm flex items-start gap-3.5">
            <div className={`w-9 h-9 rounded-xl flex items-center justify-center shrink-0 ${
              n.type === 'reminder' ? 'bg-amber-50 text-amber-600' :
              n.type === 'update' ? 'bg-blue-50 text-blue-600' : 'bg-teal-50 text-teal-600'
            }`}>
              {n.type === 'reminder' ? <Clock className="w-4 h-4" /> :
               n.type === 'update' ? <Sparkles className="w-4 h-4" /> : <Briefcase className="w-4 h-4" />}
            </div>

            <div className="flex-1">
              <div className="flex items-center justify-between">
                <span className="text-xs font-bold text-[#123B63]">{n.title}</span>
                <span className="text-[10px] text-gray-400">{n.time}</span>
              </div>
              <p className="text-xs text-gray-600 mt-1">{n.message}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default NotificationsPage;
