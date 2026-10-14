import React, { useState, useEffect } from 'react';
import { Sparkles, Calendar, ExternalLink, ShieldCheck, Loader2 } from 'lucide-react';
import miscService from '../../services/miscService';
import { LegalUpdateItem } from '../../types';

export const LegalUpdatesPage: React.FC = () => {
  const [updates, setUpdates] = useState<LegalUpdateItem[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchUpdates = async () => {
      try {
        const res = await miscService.getLegalUpdates();
        setUpdates(res || []);
      } catch (err) {
        console.error('Failed to load legal updates:', err);
      } finally {
        setLoading(false);
      }
    };
    fetchUpdates();
  }, []);

  return (
    <div className="max-w-5xl mx-auto space-y-6">
      <div>
        <h1 className="text-2xl font-black text-[#123B63]">Legal Updates & Gazette Notices</h1>
        <p className="text-xs text-gray-500 mt-1">
          Stay updated with verified statutory amendments in Indian consumer rules, labour codes, data protection, and court circulars.
        </p>
      </div>

      {loading ? (
        <div className="py-20 flex flex-col items-center justify-center text-gray-400 gap-2">
          <Loader2 className="w-7 h-7 animate-spin text-blue-600" />
          <span className="text-xs">Fetching verified legal updates...</span>
        </div>
      ) : updates.length === 0 ? (
        <div className="py-16 text-center bg-white border border-dashed border-gray-200 rounded-3xl p-8">
          <Sparkles className="w-10 h-10 text-gray-300 mx-auto mb-2" />
          <h3 className="text-sm font-bold text-gray-700">No Legal Updates Published Yet</h3>
          <p className="text-xs text-gray-500 mt-1">Check back soon for new gazette notifications and legislative alerts.</p>
        </div>
      ) : (
        <div className="space-y-4">
          {updates.map((upd) => (
            <div key={upd.id} className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-3">
              <div className="flex items-center justify-between">
                <span className="text-[10px] font-bold text-blue-700 bg-blue-50 px-2.5 py-1 rounded-full">
                  {upd.categoryName || 'Statutory Amendment'}
                </span>
                <span className="text-xs font-mono text-gray-400 flex items-center gap-1">
                  <Calendar className="w-3.5 h-3.5" /> Effective: {upd.effectiveDate}
                </span>
              </div>

              <h2 className="text-base font-bold text-[#123B63]">{upd.title}</h2>
              <p className="text-xs text-gray-600 leading-relaxed whitespace-pre-line">{upd.summary || upd.fullDetails}</p>

              <div className="pt-3 border-t border-gray-100 flex flex-col sm:flex-row sm:items-center justify-between gap-2 text-xs">
                <span className="text-gray-500">
                  Authority: <strong className="text-gray-700">{upd.sourceAuthority || 'Ministry of Law and Justice'}</strong>
                </span>

                <div className="flex items-center gap-3">
                  <span className="text-green-700 font-bold flex items-center gap-1 text-[11px]">
                    <ShieldCheck className="w-3.5 h-3.5" /> Verified by Content Manager
                  </span>

                  {upd.officialNotificationUrl && (
                    <a
                      href={upd.officialNotificationUrl}
                      target="_blank"
                      rel="noreferrer"
                      className="text-blue-600 hover:underline font-semibold flex items-center gap-1"
                    >
                      <span>Gazette Notification</span>
                      <ExternalLink className="w-3 h-3" />
                    </a>
                  )}
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default LegalUpdatesPage;
