import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { 
  Briefcase, 
  Share2, 
  Edit3, 
  Upload, 
  Eye, 
  PlusCircle, 
  CheckCircle2, 
  Clock, 
  FileText, 
  ExternalLink,
  ChevronRight,
  ArrowLeft,
  Loader2,
  AlertCircle,
  CheckSquare,
  Square,
  ShieldCheck
} from 'lucide-react';
import caseService from '../../services/caseService';
import miscService from '../../services/miscService';
import { CaseItem, CaseStatus, DocumentItem } from '../../types';

export const CaseDetailPage: React.FC = () => {
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();
  const [caseData, setCaseData] = useState<CaseItem | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [activeTab, setActiveTab] = useState<'Overview' | 'Timeline' | 'Actions' | 'Documents'>('Overview');
  const [transitioning, setTransitioning] = useState(false);
  const [documents, setDocuments] = useState<DocumentItem[]>([]);
  const [uploading, setUploading] = useState(false);

  const fetchCaseDetails = async () => {
    if (!id) return;
    setLoading(true);
    setError(null);
    try {
      // Support numeric ID or fallback
      const caseId = isNaN(Number(id)) ? 1 : Number(id);
      const res = await caseService.getCaseById(caseId);
      setCaseData(res);
      
      const docRes = await miscService.getDocuments({ caseId });
      setDocuments(docRes || []);
    } catch (err: any) {
      setError(err.message || 'Failed to load case from database.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCaseDetails();
  }, [id]);

  const handleToggleAction = async (actionId: number, currentStatus: string) => {
    const nextStatus = currentStatus === 'COMPLETED' ? 'PENDING' : 'COMPLETED';
    try {
      await caseService.toggleActionStatus(actionId, nextStatus);
      // update local state
      if (caseData && caseData.actions) {
        const updatedActions = caseData.actions.map(a => 
          a.id === actionId ? { ...a, status: nextStatus as any } : a
        );
        setCaseData({ ...caseData, actions: updatedActions });
      }
    } catch (err: any) {
      alert(err.message || 'Could not update action status.');
    }
  };

  const handleTransitionStatus = async (newStatus: CaseStatus) => {
    if (!caseData) return;
    setTransitioning(true);
    try {
      const updated = await caseService.transitionStatus(caseData.id, newStatus, `Transitioned status to ${newStatus}`);
      setCaseData(updated);
    } catch (err: any) {
      alert(err.message || 'Could not transition status.');
    } finally {
      setTransitioning(false);
    }
  };

  const handleFileUpload = async (e: React.ChangeEvent<HTMLInputElement>) => {
    if (!e.target.files || e.target.files.length === 0 || !caseData) return;
    const file = e.target.files[0];
    setUploading(true);
    try {
      const newDoc = await miscService.uploadDocument(file, `Uploaded for Case ${caseData.caseNumber}`, caseData.id);
      setDocuments([...documents, newDoc]);
    } catch (err: any) {
      alert(err.message || 'Failed to upload document.');
    } finally {
      setUploading(false);
    }
  };

  if (loading) {
    return (
      <div className="py-24 flex flex-col items-center justify-center text-gray-400 gap-3">
        <Loader2 className="w-8 h-8 animate-spin text-blue-600" />
        <span className="text-sm font-medium text-[#123B63]">Retrieving case history and records from PostgreSQL...</span>
      </div>
    );
  }

  if (error || !caseData) {
    return (
      <div className="max-w-2xl mx-auto py-12 text-center bg-white p-8 rounded-3xl border border-red-100">
        <AlertCircle className="w-12 h-12 text-red-500 mx-auto mb-3" />
        <h2 className="text-lg font-bold text-gray-800">Case Record Not Found</h2>
        <p className="text-xs text-gray-500 mt-1">{error || 'Could not retrieve requested case details.'}</p>
        <button
          onClick={() => navigate('/citizen/cases')}
          className="mt-4 inline-flex items-center gap-1.5 bg-blue-600 text-white text-xs font-semibold px-4 py-2 rounded-xl"
        >
          <ArrowLeft className="w-3.5 h-3.5" /> Back to My Cases
        </button>
      </div>
    );
  }

  const stepsList = [
    { key: 'DRAFT', label: '1. Registered' },
    { key: 'DOCUMENTS_PENDING', label: '2. Documents' },
    { key: 'ACTION_TAKEN', label: '3. Notice / Action' },
    { key: 'UNDER_REVIEW', label: '4. Redressal Review' },
    { key: 'RESOLVED', label: '5. Resolved' }
  ];

  const currentStepIdx = stepsList.findIndex(s => s.key === caseData.status);

  return (
    <div className="max-w-6xl mx-auto space-y-6">
      {/* Back button */}
      <div>
        <button
          onClick={() => navigate('/citizen/cases')}
          className="inline-flex items-center gap-1.5 text-xs text-gray-500 hover:text-blue-600 font-semibold mb-2"
        >
          <ArrowLeft className="w-3.5 h-3.5" /> Back to My Cases
        </button>
      </div>

      {/* 1. Header Banner */}
      <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm flex flex-col md:flex-row md:items-center justify-between gap-4">
        <div>
          <div className="flex items-center gap-2 mb-1">
            <span className="text-sm font-extrabold text-[#2563EB] font-mono">{caseData.caseNumber}</span>
            <span className="text-gray-300">•</span>
            <span className="text-xs font-semibold text-gray-500">{caseData.categoryName || 'General Law'}</span>
          </div>
          <h1 className="text-xl sm:text-2xl font-black text-[#123B63]">{caseData.title}</h1>
        </div>

        <div className="flex items-center gap-3">
          <span className={`inline-flex items-center text-xs font-bold px-3 py-1.5 rounded-full ${
            caseData.status === 'RESOLVED' 
              ? 'bg-green-50 text-green-700 border border-green-200'
              : caseData.status === 'DOCUMENTS_PENDING'
              ? 'bg-amber-50 text-amber-700 border border-amber-200' 
              : 'bg-teal-50 text-teal-700 border border-teal-200'
          }`}>
            {caseData.status.replace('_', ' ')}
          </span>
        </div>
      </div>

      {/* 2. Visual Progression Stepper */}
      <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm overflow-x-auto">
        <div className="flex items-center min-w-[700px] justify-between px-2">
          {stepsList.map((step, idx) => {
            const isCompleted = currentStepIdx > idx || caseData.status === 'RESOLVED';
            const isCurrent = currentStepIdx === idx && caseData.status !== 'RESOLVED';
            return (
              <React.Fragment key={step.key}>
                <div className="flex flex-col items-center text-center space-y-1.5">
                  <div className={`w-8 h-8 rounded-full flex items-center justify-center text-xs font-bold transition-all ${
                    isCompleted 
                      ? 'bg-[#16A34A] text-white shadow-xs' 
                      : isCurrent 
                      ? 'bg-[#2563EB] text-white ring-4 ring-blue-100' 
                      : 'bg-gray-100 text-gray-400'
                  }`}>
                    {isCompleted ? <CheckCircle2 className="w-4 h-4" /> : idx + 1}
                  </div>
                  <span className={`text-[11px] font-bold ${
                    isCurrent ? 'text-[#2563EB]' : isCompleted ? 'text-[#123B63]' : 'text-gray-400'
                  }`}>
                    {step.label}
                  </span>
                </div>
                {idx < stepsList.length - 1 && (
                  <div className={`flex-1 h-0.5 mx-2 ${
                    isCompleted ? 'bg-[#16A34A]' : 'bg-gray-200'
                  }`}></div>
                )}
              </React.Fragment>
            );
          })}
        </div>
      </div>

      {/* 3. Navigation Tabs */}
      <div className="flex items-center gap-2 border-b border-gray-200">
        {(['Overview', 'Actions', 'Timeline', 'Documents'] as const).map((tab) => (
          <button
            key={tab}
            onClick={() => setActiveTab(tab)}
            className={`px-4 py-2 text-xs font-bold border-b-2 transition-colors ${
              activeTab === tab
                ? 'border-blue-600 text-blue-600'
                : 'border-transparent text-gray-500 hover:text-gray-800'
            }`}
          >
            {tab}
          </button>
        ))}
      </div>

      {/* 4. Tab Content */}
      {activeTab === 'Overview' && (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div className="md:col-span-2 bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
            <div>
              <h3 className="text-sm font-bold text-[#123B63]">Problem Summary</h3>
              <p className="text-xs text-gray-600 mt-1.5 leading-relaxed whitespace-pre-line">
                {caseData.problemDescription}
              </p>
            </div>

            {caseData.relatedLaws && (
              <div className="pt-3 border-t border-gray-100">
                <h4 className="text-xs font-bold text-gray-700">Applicable Statutory Law & Sections</h4>
                <p className="text-xs text-blue-700 mt-1 font-medium bg-blue-50/60 p-2.5 rounded-xl border border-blue-100">
                  {caseData.relatedLaws} {caseData.relatedProvisions ? `• ${caseData.relatedProvisions}` : ''}
                </p>
              </div>
            )}

            {caseData.authorityName && (
              <div className="pt-3 border-t border-gray-100 flex items-center justify-between">
                <div>
                  <h4 className="text-xs font-bold text-gray-700">Competent Authority / Redressal Forum</h4>
                  <p className="text-xs text-gray-600 mt-0.5">{caseData.authorityName}</p>
                </div>
                {caseData.serviceName && (
                  <span className="text-xs bg-gray-100 text-gray-700 px-3 py-1 rounded-xl font-medium">
                    {caseData.serviceName}
                  </span>
                )}
              </div>
            )}
          </div>

          {/* Quick Actions & Status Control */}
          <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
            <h3 className="text-sm font-bold text-[#123B63]">Update Case Stage</h3>
            <p className="text-xs text-gray-500">Advance your case status as you take action or receive responses.</p>

            <div className="space-y-2 pt-2">
              {caseData.status !== 'ACTION_TAKEN' && (
                <button
                  disabled={transitioning}
                  onClick={() => handleTransitionStatus('ACTION_TAKEN')}
                  className="w-full text-left p-3 rounded-xl border border-teal-200 bg-teal-50/50 hover:bg-teal-50 text-xs font-semibold text-teal-800 transition-colors"
                >
                  ✓ Notice Issued / Action Taken
                </button>
              )}
              {caseData.status !== 'UNDER_REVIEW' && (
                <button
                  disabled={transitioning}
                  onClick={() => handleTransitionStatus('UNDER_REVIEW')}
                  className="w-full text-left p-3 rounded-xl border border-purple-200 bg-purple-50/50 hover:bg-purple-50 text-xs font-semibold text-purple-800 transition-colors"
                >
                  ⏳ Submitted to Authority / Under Review
                </button>
              )}
              {caseData.status !== 'RESOLVED' && (
                <button
                  disabled={transitioning}
                  onClick={() => handleTransitionStatus('RESOLVED')}
                  className="w-full text-left p-3 rounded-xl border border-green-200 bg-green-50/50 hover:bg-green-50 text-xs font-semibold text-green-800 transition-colors"
                >
                  🎉 Case Successfully Resolved
                </button>
              )}
            </div>
          </div>
        </div>
      )}

      {activeTab === 'Actions' && (
        <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
          <div className="flex items-center justify-between">
            <div>
              <h3 className="text-base font-bold text-[#123B63]">Recommended Action Checklist</h3>
              <p className="text-xs text-gray-500 mt-0.5">Click each step to toggle completed status in PostgreSQL.</p>
            </div>
          </div>

          {!caseData.actions || caseData.actions.length === 0 ? (
            <div className="py-8 text-center text-gray-400 text-xs">
              No checklist items registered for this case.
            </div>
          ) : (
            <div className="space-y-2.5 pt-2">
              {caseData.actions.map((act) => {
                const isDone = act.status === 'COMPLETED';
                return (
                  <div
                    key={act.id}
                    onClick={() => handleToggleAction(act.id, act.status)}
                    className={`p-3.5 rounded-2xl border transition-all cursor-pointer flex items-center justify-between gap-3 ${
                      isDone 
                        ? 'bg-green-50/40 border-green-200 text-gray-500' 
                        : 'bg-white border-gray-200 hover:border-blue-300 text-gray-800'
                    }`}
                  >
                    <div className="flex items-center gap-3">
                      {isDone ? (
                        <CheckSquare className="w-5 h-5 text-green-600 flex-shrink-0" />
                      ) : (
                        <Square className="w-5 h-5 text-gray-400 flex-shrink-0" />
                      )}
                      <span className={`text-xs font-medium ${isDone ? 'line-through text-gray-400' : 'text-gray-800'}`}>
                        {act.actionStep}
                      </span>
                    </div>
                    <span className={`text-[10px] font-bold px-2 py-0.5 rounded-full ${
                      isDone ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-600'
                    }`}>
                      {act.status}
                    </span>
                  </div>
                );
              })}
            </div>
          )}
        </div>
      )}

      {activeTab === 'Timeline' && (
        <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
          <h3 className="text-base font-bold text-[#123B63]">Case Audit & History Log</h3>
          <p className="text-xs text-gray-500">Immutable chronological trail of status transitions stored in database.</p>

          {!caseData.history || caseData.history.length === 0 ? (
            <div className="py-8 text-center text-gray-400 text-xs">
              Case opened on {new Date(caseData.createdAt).toLocaleDateString()}. No subsequent transitions logged yet.
            </div>
          ) : (
            <div className="space-y-3 pt-2">
              {caseData.history.map((h) => (
                <div key={h.id} className="p-3.5 bg-gray-50 rounded-2xl border border-gray-100 flex items-start justify-between text-xs">
                  <div>
                    <div className="font-bold text-[#123B63] flex items-center gap-2">
                      <Clock className="w-3.5 h-3.5 text-blue-600" />
                      <span>{h.fromStatus ? `${h.fromStatus} → ${h.toStatus}` : `Status set to ${h.toStatus}`}</span>
                    </div>
                    <p className="text-gray-600 mt-1">{h.changeNote || 'Status updated.'}</p>
                  </div>
                  <span className="text-[10px] text-gray-400">{new Date(h.changedAt).toLocaleString()}</span>
                </div>
              ))}
            </div>
          )}
        </div>
      )}

      {activeTab === 'Documents' && (
        <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
          <div className="flex items-center justify-between">
            <div>
              <h3 className="text-base font-bold text-[#123B63]">Attached Evidence & Case Documents</h3>
              <p className="text-xs text-gray-500 mt-0.5">Upload contracts, salary slips, notices, or receipts.</p>
            </div>
            <label className="cursor-pointer inline-flex items-center gap-1.5 bg-blue-600 hover:bg-blue-700 text-white text-xs font-bold px-3.5 py-2 rounded-xl transition-colors shadow-xs">
              <Upload className="w-3.5 h-3.5" />
              <span>{uploading ? 'Uploading...' : 'Upload File'}</span>
              <input type="file" onChange={handleFileUpload} className="hidden" disabled={uploading} />
            </label>
          </div>

          {documents.length === 0 ? (
            <div className="py-10 text-center border-2 border-dashed border-gray-100 rounded-2xl p-6">
              <FileText className="w-8 h-8 text-gray-300 mx-auto mb-2" />
              <p className="text-xs text-gray-500">No documents uploaded for this case yet.</p>
            </div>
          ) : (
            <div className="space-y-2 pt-2">
              {documents.map((doc) => (
                <div key={doc.id} className="p-3 bg-gray-50 rounded-xl border border-gray-200 flex items-center justify-between text-xs">
                  <div className="flex items-center gap-2.5">
                    <FileText className="w-4 h-4 text-blue-600" />
                    <div>
                      <span className="font-bold text-gray-800">{doc.originalFileName || doc.fileName}</span>
                      <span className="text-[10px] text-gray-400 ml-2">({Math.round(doc.fileSizeBytes / 1024)} KB)</span>
                    </div>
                  </div>
                  <span className="text-[10px] text-gray-400">{new Date(doc.createdAt).toLocaleDateString()}</span>
                </div>
              ))}
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default CaseDetailPage;
