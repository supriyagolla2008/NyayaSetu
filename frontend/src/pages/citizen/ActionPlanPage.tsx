import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { 
  CheckSquare, 
  Square, 
  ArrowRight, 
  Clock, 
  FileText, 
  CheckCircle2, 
  Briefcase,
  AlertCircle
} from 'lucide-react';

export const ActionPlanPage: React.FC = () => {
  const navigate = useNavigate();
  const { caseId } = useParams();

  const [docs, setDocs] = useState([
    { id: 1, name: 'Employment agreement', checked: true },
    { id: 2, name: 'Salary slips (last 3 months)', checked: false },
    { id: 3, name: 'Bank statement', checked: false },
    { id: 4, name: 'Communication with employer', checked: false },
    { id: 5, name: 'Identity proof', checked: true },
  ]);

  const [actions, setActions] = useState([
    { id: 1, name: 'Send written request to employer', checked: false },
    { id: 2, name: 'File complaint with authority (Labour Dept)', checked: false },
    { id: 3, name: 'Track complaint status online', checked: false },
  ]);

  const toggleDoc = (id: number) => {
    setDocs(docs.map(d => d.id === id ? { ...d, checked: !d.checked } : d));
  };

  const toggleAction = (id: number) => {
    setActions(actions.map(a => a.id === id ? { ...a, checked: !a.checked } : a));
  };

  return (
    <div className="max-w-6xl mx-auto space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">What Should I Do Now?</h1>
          <p className="text-xs text-gray-500 mt-1">Personalized action plan and required documentation.</p>
        </div>
        <button
          onClick={() => navigate('/citizen/cases/case-1')}
          className="flex items-center gap-2 bg-[#2563EB] hover:bg-blue-700 text-white font-bold px-4 py-2.5 rounded-xl text-xs shadow-xs"
        >
          <span>View Case Details</span>
          <ArrowRight className="w-3.5 h-3.5" />
        </button>
      </div>

      {/* Main Grid: Steps on Left, Checklists on Right Matching Screen 9 */}
      <div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
        {/* Left Column: Ordered Steps */}
        <div className="lg:col-span-7 bg-white rounded-3xl p-6 sm:p-8 border border-gray-100 shadow-sm space-y-4">
          <h2 className="text-sm font-bold text-[#123B63] mb-4">Recommended Step-by-Step Actions</h2>

          {[
            { step: 1, title: 'Collect salary slips and employment documents', status: 'Pending', color: 'text-amber-700 bg-amber-50 border-amber-200' },
            { step: 2, title: 'Send a written request to your employer', status: 'Pending', color: 'text-amber-700 bg-amber-50 border-amber-200' },
            { step: 3, title: 'Approach the Labour Department', status: 'Not Started', color: 'text-gray-500 bg-gray-50 border-gray-200' },
            { step: 4, title: 'Track your complaint', status: 'Not Started', color: 'text-gray-500 bg-gray-50 border-gray-200' },
          ].map((st) => (
            <div key={st.step} className="p-4 rounded-2xl border border-gray-100 hover:border-blue-200 transition-all flex items-start justify-between gap-4">
              <div className="flex items-start gap-3">
                <span className="w-7 h-7 rounded-full bg-blue-100 text-[#2563EB] font-bold text-xs flex items-center justify-center shrink-0">
                  {st.step}
                </span>
                <div>
                  <h3 className="text-xs font-bold text-[#123B63]">{st.title}</h3>
                  <p className="text-[11px] text-gray-500 mt-0.5">Required before formal dispute filing under Payment of Wages Act.</p>
                </div>
              </div>
              <span className={`text-[10px] font-bold px-2.5 py-1 rounded-full border shrink-0 ${st.color}`}>
                {st.status}
              </span>
            </div>
          ))}

          <div className="mt-6 p-4 rounded-xl bg-blue-50/70 border border-blue-100 flex items-center justify-between">
            <div className="text-xs text-[#123B63]">
              <span className="font-bold">Ready to take formal action?</span>
              <p className="text-gray-500 text-[11px]">You can create and link this case to your Citizen profile.</p>
            </div>
            <button
              onClick={() => navigate('/citizen/cases/case-1')}
              className="bg-[#2563EB] text-white text-xs font-bold px-3 py-1.5 rounded-lg hover:bg-blue-700"
            >
              Open Case #RF1024
            </button>
          </div>
        </div>

        {/* Right Column: Document Checklist & Action Checklist Matching Screen 9 */}
        <div className="lg:col-span-5 space-y-6">
          {/* Document Checklist */}
          <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
            <div className="flex items-center justify-between">
              <h2 className="text-sm font-bold text-[#123B63]">Document Checklist</h2>
              <span className="text-[10px] font-bold text-blue-600 bg-blue-50 px-2 py-0.5 rounded-full">
                {docs.filter(d => d.checked).length}/{docs.length} Ready
              </span>
            </div>

            <div className="space-y-2.5">
              {docs.map((doc) => (
                <div
                  key={doc.id}
                  onClick={() => toggleDoc(doc.id)}
                  className="flex items-center gap-3 p-2.5 rounded-xl hover:bg-gray-50 cursor-pointer text-xs text-gray-700 transition-colors"
                >
                  {doc.checked ? (
                    <CheckSquare className="w-4 h-4 text-[#2563EB] shrink-0" />
                  ) : (
                    <Square className="w-4 h-4 text-gray-300 shrink-0" />
                  )}
                  <span className={doc.checked ? 'line-through text-gray-400 font-medium' : 'font-medium'}>
                    {doc.name}
                  </span>
                </div>
              ))}
            </div>

            <button
              onClick={() => navigate('/citizen/documents')}
              className="w-full bg-gray-50 hover:bg-gray-100 text-[#123B63] font-semibold py-2 rounded-xl text-xs border border-gray-200 transition-colors"
            >
              Upload Pending Documents
            </button>
          </div>

          {/* Action Checklist */}
          <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
            <h2 className="text-sm font-bold text-[#123B63]">Action Checklist</h2>

            <div className="space-y-2.5">
              {actions.map((act) => (
                <div
                  key={act.id}
                  onClick={() => toggleAction(act.id)}
                  className="flex items-center gap-3 p-2.5 rounded-xl hover:bg-gray-50 cursor-pointer text-xs text-gray-700 transition-colors"
                >
                  {act.checked ? (
                    <CheckSquare className="w-4 h-4 text-[#16A34A] shrink-0" />
                  ) : (
                    <Square className="w-4 h-4 text-gray-300 shrink-0" />
                  )}
                  <span className={act.checked ? 'line-through text-gray-400 font-medium' : 'font-medium'}>
                    {act.name}
                  </span>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ActionPlanPage;
