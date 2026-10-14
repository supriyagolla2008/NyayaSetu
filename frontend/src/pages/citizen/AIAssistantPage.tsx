import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { 
  Bot, 
  User, 
  ExternalLink, 
  Volume2, 
  Globe, 
  ArrowRight, 
  Send, 
  CheckCircle2, 
  FileText, 
  ShieldCheck,
  ChevronDown,
  Loader2,
  AlertCircle,
  Briefcase,
  Plus
} from 'lucide-react';
import aiService from '../../services/aiService';
import caseService from '../../services/caseService';
import { AIAnalysisResponse } from '../../types';

export const AIAssistantPage: React.FC = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const initialQuery = location.state?.query || 'My employer has not paid my salary for two months.';
  const [messages, setMessages] = useState<Array<{ sender: 'user' | 'ai'; text?: string; analysis?: AIAnalysisResponse }>>([]);
  const [followUp, setFollowUp] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [creatingCase, setCreatingCase] = useState(false);

  useEffect(() => {
    const runInitialAnalysis = async () => {
      setLoading(true);
      setError(null);
      try {
        const res = await aiService.analyzeProblem({
          problemDescription: initialQuery,
        });
        setMessages([
          { sender: 'user', text: initialQuery },
          { sender: 'ai', analysis: res }
        ]);
      } catch (err: any) {
        setError(err.message || 'Failed to analyze grievance with legal engine.');
      } finally {
        setLoading(false);
      }
    };

    runInitialAnalysis();
  }, [initialQuery]);

  const handleFollowUpSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!followUp.trim() || loading) return;

    const userQ = followUp.trim();
    setFollowUp('');
    setMessages(prev => [...prev, { sender: 'user', text: userQ }]);
    setLoading(true);

    try {
      const res = await aiService.chat({
        problemDescription: userQ,
      });
      setMessages(prev => [...prev, { sender: 'ai', analysis: res }]);
    } catch (err: any) {
      alert(err.message || 'AI assistant query failed.');
    } finally {
      setLoading(false);
    }
  };

  const handleCreateCaseFromAnalysis = async (analysis: AIAnalysisResponse) => {
    setCreatingCase(true);
    try {
      const primaryMatch = analysis.matchedRights && analysis.matchedRights.length > 0 ? analysis.matchedRights[0] : null;
      const newCase = await caseService.createCase({
        title: primaryMatch ? primaryMatch.title : `${analysis.identifiedCategory} Grievance`,
        categoryId: undefined,
        rightId: primaryMatch ? primaryMatch.rightId : undefined,
        problemDescription: `${analysis.query}\n\nAI Summary: ${analysis.summary}`,
        relatedLaws: analysis.relevantLaw,
        relatedProvisions: analysis.relevantSection,
        authorityName: analysis.authorityName,
        serviceName: primaryMatch?.officialPortal,
        initialActions: analysis.recommendedSteps,
      });
      navigate(`/citizen/cases/${newCase.id}`);
    } catch (err: any) {
      alert(err.message || 'Could not create case.');
    } finally {
      setCreatingCase(false);
    }
  };

  return (
    <div className="max-w-4xl mx-auto space-y-6 pb-12">
      {/* Messages Feed */}
      <div className="space-y-6">
        {messages.map((msg, idx) => {
          if (msg.sender === 'user') {
            return (
              <div key={idx} className="flex justify-end">
                <div className="bg-[#2563EB] text-white rounded-2xl rounded-tr-xs px-5 py-3 max-w-lg shadow-sm">
                  <p className="text-sm font-medium leading-relaxed">{msg.text}</p>
                </div>
              </div>
            );
          }

          const analysis = msg.analysis;
          if (!analysis) return null;

          return (
            <div key={idx} className="bg-white rounded-3xl border border-gray-100 shadow-sm p-6 sm:p-8 space-y-6">
              {/* Header */}
              <div className="flex items-center justify-between border-b border-gray-100 pb-4">
                <div className="flex items-center gap-3">
                  <div className="w-10 h-10 rounded-xl bg-blue-50 text-[#2563EB] flex items-center justify-center">
                    <Bot className="w-6 h-6" />
                  </div>
                  <div>
                    <h2 className="text-sm font-bold text-[#123B63]">
                      Identified Category: <span className="text-blue-600">{analysis.identifiedCategory}</span>
                    </h2>
                    <span className="text-[11px] text-[#16A34A] font-semibold flex items-center gap-1">
                      <CheckCircle2 className="w-3.5 h-3.5" /> Grounded in Verified Indian Statutory Law
                    </span>
                  </div>
                </div>

                <button
                  disabled={creatingCase}
                  onClick={() => handleCreateCaseFromAnalysis(analysis)}
                  className="flex items-center gap-1.5 bg-[#2563EB] hover:bg-blue-700 text-white text-xs font-bold px-3.5 py-2 rounded-xl shadow-xs transition-colors shrink-0 disabled:opacity-50"
                >
                  <Plus className="w-3.5 h-3.5" />
                  <span>{creatingCase ? 'Saving Case...' : 'Track as Case'}</span>
                </button>
              </div>

              {/* 1. Plain English Explanation */}
              <div className="bg-blue-50/60 rounded-2xl p-5 border border-blue-100/80 space-y-2">
                <div className="flex items-center gap-2 text-xs font-bold text-[#2563EB] uppercase tracking-wider">
                  <ShieldCheck className="w-4 h-4" />
                  <span>What the Law Says in Citizen Words</span>
                </div>
                <p className="text-sm text-[#123B63] font-medium leading-relaxed">
                  {analysis.plainEnglishExplanation || analysis.summary}
                </p>
              </div>

              {/* 2. Applicable Law & Section */}
              {(analysis.relevantLaw || analysis.relevantSection) && (
                <div className="space-y-1.5">
                  <h3 className="text-xs font-bold text-gray-500 uppercase tracking-wider">Applicable Statute & Sections</h3>
                  <div className="bg-teal-50/50 p-4 rounded-xl border border-teal-100 text-xs flex flex-col sm:flex-row sm:items-center justify-between gap-3">
                    <div>
                      <div className="font-bold text-[#123B63] text-sm">{analysis.relevantLaw}</div>
                      <div className="text-gray-600 mt-0.5">{analysis.relevantSection}</div>
                    </div>
                  </div>
                </div>
              )}

              {/* 3. Action Steps */}
              {analysis.recommendedSteps && analysis.recommendedSteps.length > 0 && (
                <div className="space-y-2">
                  <h3 className="text-xs font-bold text-gray-500 uppercase tracking-wider">What You Should Do Now (Action Steps)</h3>
                  <div className="grid grid-cols-1 sm:grid-cols-2 gap-2.5">
                    {analysis.recommendedSteps.map((step, sIdx) => (
                      <div key={sIdx} className="p-3 bg-gray-50 rounded-xl border border-gray-100 text-xs text-gray-700 flex items-start gap-2">
                        <span className="w-5 h-5 rounded-full bg-blue-100 text-blue-700 font-bold flex items-center justify-center text-[10px] shrink-0 mt-0.5">
                          {sIdx + 1}
                        </span>
                        <span>{step}</span>
                      </div>
                    ))}
                  </div>
                </div>
              )}

              {/* 4. Required Documents Checklist */}
              {analysis.requiredDocuments && analysis.requiredDocuments.length > 0 && (
                <div className="space-y-2">
                  <h3 className="text-xs font-bold text-gray-500 uppercase tracking-wider">Required Document Checklist</h3>
                  <div className="flex flex-wrap gap-2">
                    {analysis.requiredDocuments.map((doc, dIdx) => (
                      <span key={dIdx} className="inline-flex items-center gap-1.5 px-3 py-1.5 bg-amber-50 text-amber-900 border border-amber-200 rounded-xl text-xs font-medium">
                        <FileText className="w-3.5 h-3.5 text-amber-600" />
                        {doc}
                      </span>
                    ))}
                  </div>
                </div>
              )}

              {/* 5. Competent Authority & Official Redressal Portal */}
              <div className="bg-gray-50 p-4 rounded-2xl border border-gray-100 flex flex-col sm:flex-row sm:items-center justify-between gap-3 text-xs">
                <div>
                  <span className="font-bold text-gray-500 uppercase text-[10px]">Competent Authority</span>
                  <div className="font-bold text-[#123B63] text-sm mt-0.5">{analysis.authorityName || 'State / Central Competent Forum'}</div>
                </div>
                {analysis.servicePortalUrl && (
                  <a
                    href={analysis.servicePortalUrl}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="inline-flex items-center gap-1.5 bg-white border border-gray-200 px-3 py-2 rounded-xl text-xs font-semibold text-blue-600 hover:bg-gray-50"
                  >
                    <span>Official Portal</span>
                    <ExternalLink className="w-3.5 h-3.5" />
                  </a>
                )}
              </div>

              {/* 6. Matched Statutory Rights from Database */}
              {analysis.matchedRights && analysis.matchedRights.length > 0 && (
                <div className="pt-2 border-t border-gray-100">
                  <h3 className="text-xs font-bold text-gray-500 uppercase tracking-wider mb-2">
                    Directly Matched Database Rights
                  </h3>
                  <div className="space-y-2">
                    {analysis.matchedRights.map(mr => (
                      <div
                        key={mr.rightId}
                        onClick={() => navigate(`/citizen/rights/${mr.slug || mr.rightId}`)}
                        className="p-3 bg-white hover:bg-blue-50/30 rounded-xl border border-gray-200 hover:border-blue-300 transition-all cursor-pointer flex items-center justify-between text-xs"
                      >
                        <div>
                          <div className="font-bold text-[#123B63]">{mr.title}</div>
                          <div className="text-[11px] text-gray-500">{mr.summary}</div>
                        </div>
                        <span className="text-xs font-bold text-blue-600 shrink-0 flex items-center gap-1">
                          <span>View Right</span>
                          <ArrowRight className="w-3.5 h-3.5" />
                        </span>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          );
        })}

        {loading && (
          <div className="py-12 flex flex-col items-center justify-center text-gray-400 gap-2">
            <Loader2 className="w-7 h-7 animate-spin text-blue-600" />
            <span className="text-xs font-medium text-gray-600">NyayaSetu AI is analyzing statutes, penalties, and jurisdiction...</span>
          </div>
        )}
      </div>

      {/* Follow-up Question Input */}
      <div className="bg-white rounded-2xl border border-gray-200 p-2 shadow-sm flex items-center gap-2 sticky bottom-4">
        <form onSubmit={handleFollowUpSubmit} className="flex items-center gap-2 w-full">
          <input
            type="text"
            value={followUp}
            onChange={(e) => setFollowUp(e.target.value)}
            disabled={loading}
            placeholder="Ask a follow-up question (e.g. How do I send a legal notice? What is the limitation period?)..."
            className="flex-1 px-4 py-2 text-xs focus:outline-none text-gray-800"
          />
          <button
            type="submit"
            disabled={loading || !followUp.trim()}
            className="bg-[#2563EB] hover:bg-blue-700 text-white p-2.5 rounded-xl disabled:opacity-40 transition-colors shadow-xs"
          >
            <Send className="w-4 h-4" />
          </button>
        </form>
      </div>
    </div>
  );
};

export default AIAssistantPage;
