import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { 
  CheckCircle, 
  ArrowLeft, 
  Save, 
  ShieldCheck, 
  ExternalLink,
  FileText
} from 'lucide-react';

export const EditRightPage: React.FC = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const [activeTab, setActiveTab] = useState('Basic Information');
  const [title, setTitle] = useState('Right to Receive Salary');
  const [category, setCategory] = useState('Employment & Labour');
  const [shortDesc, setShortDesc] = useState('Employees are entitled to receive salary for work performed as per applicable labour laws.');
  const [simpleWords, setSimpleWords] = useState('Employees are entitled to receive salary for work performed as per applicable labour laws.');
  const [status, setStatus] = useState<'Under Review' | 'Verified'>('Under Review');

  const handleVerifyPublish = () => {
    setStatus('Verified');
    alert('Legal Right successfully verified and published to knowledge base & RAG index!');
  };

  return (
    <div className="max-w-6xl mx-auto space-y-6">
      {/* 1. Header Matching Screen 13 */}
      <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm flex flex-col md:flex-row md:items-center justify-between gap-4">
        <div className="flex items-center gap-4">
          <button
            onClick={() => navigate('/content-manager/rights')}
            className="p-2 rounded-xl border border-gray-200 text-gray-600 hover:bg-gray-50"
          >
            <ArrowLeft className="w-4 h-4" />
          </button>
          <div>
            <div className="flex items-center gap-2 mb-1">
              <span className="text-xs font-semibold text-gray-400">Content Editor</span>
              <span className={`text-[10px] font-bold px-2 py-0.5 rounded-full ${
                status === 'Verified' ? 'bg-green-100 text-green-700' : 'bg-amber-100 text-amber-700'
              }`}>
                {status}
              </span>
            </div>
            <h1 className="text-xl sm:text-2xl font-black text-[#123B63]">Edit Right: {title}</h1>
          </div>
        </div>

        <div className="flex items-center gap-3">
          <button
            onClick={handleVerifyPublish}
            className="flex items-center gap-2 bg-[#16A34A] hover:bg-green-700 text-white font-bold px-4 py-2.5 rounded-xl text-xs shadow-sm transition-colors"
          >
            <CheckCircle className="w-4 h-4" />
            <span>Verify & Publish</span>
          </button>
        </div>
      </div>

      {/* 2. Navigation Tabs Matching Screen 13 */}
      <div className="flex items-center gap-2 border-b border-gray-200 overflow-x-auto">
        {[
          'Basic Information',
          'Detailed Content',
          'Legal References',
          'Examples',
          'Documents',
          'Sources'
        ].map((tab) => (
          <button
            key={tab}
            onClick={() => setActiveTab(tab)}
            className={`pb-3 px-4 text-xs font-bold transition-all border-b-2 whitespace-nowrap ${
              activeTab === tab
                ? 'border-[#0D9488] text-[#0D9488]'
                : 'border-transparent text-gray-500 hover:text-gray-700'
            }`}
          >
            {tab}
          </button>
        ))}
      </div>

      {/* 3. Form Content Matching Screen 13 */}
      <div className="bg-white rounded-3xl p-6 sm:p-8 border border-gray-100 shadow-sm space-y-6">
        {activeTab === 'Basic Information' && (
          <div className="space-y-4">
            <div>
              <label className="block text-xs font-bold text-gray-700 mb-1.5">Title</label>
              <input
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                className="w-full px-3.5 py-2.5 rounded-xl border border-gray-200 text-xs text-gray-800 focus:outline-none focus:ring-2 focus:ring-teal-500"
              />
            </div>

            <div>
              <label className="block text-xs font-bold text-gray-700 mb-1.5">Category</label>
              <select
                value={category}
                onChange={(e) => setCategory(e.target.value)}
                className="w-full px-3.5 py-2.5 rounded-xl border border-gray-200 text-xs text-gray-800 focus:outline-none focus:ring-2 focus:ring-teal-500 bg-white"
              >
                <option value="Employment & Labour">Employment & Labour</option>
                <option value="Consumer Rights">Consumer Rights</option>
                <option value="Women & Gender Rights">Women & Gender Rights</option>
                <option value="Legal Aid">Legal Aid</option>
              </select>
            </div>

            <div>
              <label className="block text-xs font-bold text-gray-700 mb-1.5">Short Description</label>
              <textarea
                rows={3}
                value={shortDesc}
                onChange={(e) => setShortDesc(e.target.value)}
                className="w-full px-3.5 py-2.5 rounded-xl border border-gray-200 text-xs text-gray-800 focus:outline-none focus:ring-2 focus:ring-teal-500"
              />
            </div>

            <div>
              <label className="block text-xs font-bold text-gray-700 mb-1.5">In Simple Words</label>
              <textarea
                rows={3}
                value={simpleWords}
                onChange={(e) => setSimpleWords(e.target.value)}
                className="w-full px-3.5 py-2.5 rounded-xl border border-gray-200 text-xs text-gray-800 focus:outline-none focus:ring-2 focus:ring-teal-500"
              />
            </div>
          </div>
        )}

        {activeTab === 'Legal References' && (
          <div className="space-y-4 text-xs">
            <div className="p-4 bg-teal-50/50 rounded-2xl border border-teal-100">
              <span className="font-bold text-[#123B63] block text-sm">Primary Statute</span>
              <p className="text-gray-600 mt-1">Payment of Wages Act, 1936 (Act No. 4 of 1936)</p>
              <span className="font-bold text-teal-800 mt-2 block">Section 3 & Section 15</span>
              <p className="text-gray-500 text-[11px] mt-0.5">Responsibility for payment of wages and claims arising out of deductions from wages or delay in payment.</p>
            </div>
          </div>
        )}

        {activeTab === 'Sources' && (
          <div className="space-y-4 text-xs">
            <div className="p-4 bg-gray-50 rounded-2xl border border-gray-200 flex items-center justify-between">
              <div>
                <span className="font-bold text-[#123B63] block">India Code Digital Repository</span>
                <span className="text-gray-500 text-[11px]">https://www.indiacode.nic.in/handle/123456789/2253</span>
              </div>
              <a href="https://www.indiacode.nic.in" target="_blank" rel="noreferrer" className="text-teal-700 font-bold hover:underline flex items-center gap-1">
                <span>Verify</span>
                <ExternalLink className="w-3.5 h-3.5" />
              </a>
            </div>
          </div>
        )}

        <div className="pt-4 border-t border-gray-100 flex justify-end gap-3">
          <button
            onClick={() => navigate('/content-manager/rights')}
            className="px-4 py-2 border border-gray-200 rounded-xl text-xs font-semibold text-gray-600 hover:bg-gray-50"
          >
            Cancel
          </button>
          <button
            onClick={() => alert('Draft saved successfully.')}
            className="px-4 py-2 bg-gray-900 text-white rounded-xl text-xs font-semibold hover:bg-black"
          >
            Save Draft
          </button>
        </div>
      </div>
    </div>
  );
};

export default EditRightPage;
