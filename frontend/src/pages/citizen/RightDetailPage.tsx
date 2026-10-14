import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { 
  ArrowLeft, 
  Bookmark, 
  ShieldCheck, 
  ExternalLink, 
  CheckCircle, 
  FileText, 
  HelpCircle, 
  Scale, 
  Briefcase, 
  ChevronRight, 
  Loader2, 
  AlertCircle,
  Plus
} from 'lucide-react';
import rightsService from '../../services/rightsService';
import caseService from '../../services/caseService';
import { LegalRightDetail } from '../../types';

export const RightDetailPage: React.FC = () => {
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();
  const [right, setRight] = useState<LegalRightDetail | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [bookmarked, setBookmarked] = useState(false);
  const [creatingCase, setCreatingCase] = useState(false);

  useEffect(() => {
    const fetchRight = async () => {
      if (!id) return;
      setLoading(true);
      setError(null);
      try {
        const res = await rightsService.getRightBySlugOrId(id);
        setRight(res);
        setBookmarked(!!res.bookmarked);
      } catch (err: any) {
        setError(err.message || 'Failed to load right details from database.');
      } finally {
        setLoading(false);
      }
    };

    fetchRight();
  }, [id]);

  const handleToggleBookmark = async () => {
    if (!right) return;
    try {
      const isBookmarked = await rightsService.toggleBookmark(Number(right.id));
      setBookmarked(isBookmarked);
    } catch (e) {
      console.error('Failed to toggle bookmark:', e);
    }
  };

  const handleCreateCaseFromRight = async () => {
    if (!right) return;
    setCreatingCase(true);
    try {
      const newCase = await caseService.createCase({
        title: right.title,
        categoryId: Number(right.categoryId),
        rightId: Number(right.id),
        problemDescription: `Filing case regarding right: ${right.title}. Summary: ${right.shortDescription}`,
        relatedLaws: right.laws && right.laws.length > 0 ? right.laws[0].title : undefined,
        relatedProvisions: right.provisions && right.provisions.length > 0 ? right.provisions[0].sectionNumber : undefined,
        authorityName: right.relevantAuthority,
        serviceName: right.relevantService,
        initialActions: right.recommendedActions ? right.recommendedActions.split('\n').filter(Boolean) : undefined,
      });
      navigate(`/citizen/cases/${newCase.id}`);
    } catch (err: any) {
      alert(err.message || 'Could not create case.');
    } finally {
      setCreatingCase(false);
    }
  };

  if (loading) {
    return (
      <div className="py-24 flex flex-col items-center justify-center text-gray-400 gap-3">
        <Loader2 className="w-8 h-8 animate-spin text-blue-600" />
        <span className="text-sm font-medium text-[#123B63]">Loading legal citations and provisions from PostgreSQL...</span>
      </div>
    );
  }

  if (error || !right) {
    return (
      <div className="max-w-2xl mx-auto py-12 text-center bg-white p-8 rounded-3xl border border-red-100">
        <AlertCircle className="w-12 h-12 text-red-500 mx-auto mb-3" />
        <h2 className="text-lg font-bold text-gray-800">Legal Record Not Found</h2>
        <p className="text-xs text-gray-500 mt-1">{error || 'Could not retrieve requested legal right.'}</p>
        <button
          onClick={() => navigate('/citizen/rights')}
          className="mt-4 inline-flex items-center gap-1.5 bg-blue-600 text-white text-xs font-semibold px-4 py-2 rounded-xl"
        >
          <ArrowLeft className="w-3.5 h-3.5" /> Back to Rights Library
        </button>
      </div>
    );
  }

  return (
    <div className="max-w-5xl mx-auto space-y-6 pb-12">
      {/* Navigation Header */}
      <div className="flex items-center justify-between">
        <button
          onClick={() => navigate('/citizen/rights')}
          className="inline-flex items-center gap-1.5 text-xs text-gray-500 hover:text-blue-600 font-semibold"
        >
          <ArrowLeft className="w-3.5 h-3.5" /> Back to Rights Library
        </button>

        <div className="flex items-center gap-2">
          <button
            onClick={handleToggleBookmark}
            className={`flex items-center gap-1.5 px-3 py-1.5 rounded-xl text-xs font-semibold transition-colors border ${
              bookmarked
                ? 'bg-amber-50 text-amber-700 border-amber-200'
                : 'bg-white text-gray-600 border-gray-200 hover:bg-gray-50'
            }`}
          >
            <Bookmark className={`w-3.5 h-3.5 ${bookmarked ? 'fill-current' : ''}`} />
            <span>{bookmarked ? 'Saved to Bookmarks' : 'Save Right'}</span>
          </button>

          <button
            disabled={creatingCase}
            onClick={handleCreateCaseFromRight}
            className="flex items-center gap-1.5 bg-blue-600 hover:bg-blue-700 text-white text-xs font-bold px-4 py-1.5 rounded-xl shadow-xs transition-colors disabled:opacity-50"
          >
            <Plus className="w-3.5 h-3.5" />
            <span>{creatingCase ? 'Creating Case...' : 'Track as Case'}</span>
          </button>
        </div>
      </div>

      {/* Main Title Card */}
      <div className="bg-white rounded-3xl p-6 sm:p-8 border border-gray-100 shadow-sm space-y-4">
        <div className="flex flex-wrap items-center gap-2">
          <span className="text-xs font-bold text-teal-800 bg-teal-50 px-3 py-1 rounded-full">
            {right.categoryName}
          </span>
          <span className="text-xs font-bold text-green-700 bg-green-50 px-2.5 py-1 rounded-full flex items-center gap-1">
            <ShieldCheck className="w-3.5 h-3.5" /> Verified Legal Knowledge
          </span>
          {right.version && (
            <span className="text-xs text-gray-400 font-medium">Version {right.version}</span>
          )}
        </div>

        <h1 className="text-2xl sm:text-3xl font-black text-[#123B63]">{right.title}</h1>
        <p className="text-sm text-gray-600 leading-relaxed font-medium">{right.shortDescription}</p>
      </div>

      {/* Plain Language & Legal Basis Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        {/* Simple Explanation */}
        <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-2">
          <h2 className="text-sm font-bold text-[#123B63] flex items-center gap-2">
            <HelpCircle className="w-4 h-4 text-blue-600" /> In Plain English (Citizen Words)
          </h2>
          <p className="text-xs text-gray-600 leading-relaxed whitespace-pre-line pt-1">
            {right.simpleExplanation}
          </p>
        </div>

        {/* Detailed Statutory Basis */}
        <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-2">
          <h2 className="text-sm font-bold text-[#123B63] flex items-center gap-2">
            <Scale className="w-4 h-4 text-purple-600" /> Statutory Foundation & Precedents
          </h2>
          <p className="text-xs text-gray-600 leading-relaxed whitespace-pre-line pt-1">
            {right.detailedExplanation || right.whyItApplies}
          </p>
        </div>
      </div>

      {/* Real-World Practical Example */}
      {right.realWorldExample && (
        <div className="bg-gradient-to-r from-blue-50/60 to-indigo-50/60 rounded-3xl p-6 border border-blue-100 space-y-2">
          <h2 className="text-xs font-bold uppercase tracking-wider text-blue-900">
            Real-World Precedent / Practical Case Study
          </h2>
          <p className="text-xs text-gray-700 leading-relaxed italic">
            "{right.realWorldExample}"
          </p>
        </div>
      )}

      {/* Action Steps & Documents Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        {/* Action Steps */}
        <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-3">
          <h2 className="text-sm font-bold text-[#123B63] flex items-center gap-2">
            <CheckCircle className="w-4 h-4 text-teal-600" /> Step-by-Step Action Plan
          </h2>
          <div className="text-xs text-gray-600 leading-relaxed whitespace-pre-line pt-1 space-y-2">
            {right.recommendedActions ? (
              right.recommendedActions.split('\n').map((step, idx) => (
                <div key={idx} className="p-2.5 bg-gray-50 rounded-xl border border-gray-100">
                  {step}
                </div>
              ))
            ) : (
              <p>Follow standard grievance submission before the competent authority.</p>
            )}
          </div>
        </div>

        {/* Required Documents */}
        <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-3">
          <h2 className="text-sm font-bold text-[#123B63] flex items-center gap-2">
            <FileText className="w-4 h-4 text-amber-600" /> Document Checklist
          </h2>
          <div className="text-xs text-gray-600 leading-relaxed pt-1 space-y-2">
            {right.requiredDocuments ? (
              right.requiredDocuments.split(',').map((doc, idx) => (
                <div key={idx} className="flex items-center gap-2 p-2.5 bg-amber-50/40 rounded-xl border border-amber-100">
                  <CheckCircle className="w-3.5 h-3.5 text-amber-600 flex-shrink-0" />
                  <span className="font-medium text-gray-800">{doc.trim()}</span>
                </div>
              ))
            ) : (
              <p>Identity proof (Aadhaar/Voter ID) and transaction/communication receipts.</p>
            )}
          </div>
        </div>
      </div>

      {/* Competent Authority & Redressal Mechanism */}
      <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
        <h2 className="text-sm font-bold text-[#123B63]">Redressal Mechanism & Competent Authorities</h2>
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 pt-1">
          <div className="p-4 bg-gray-50 rounded-2xl border border-gray-200/80">
            <span className="text-[10px] font-bold uppercase tracking-wider text-gray-400">Authority</span>
            <p className="text-sm font-bold text-[#123B63] mt-0.5">{right.relevantAuthority || 'Judicial / Quasi-Judicial Tribunal'}</p>
          </div>
          <div className="p-4 bg-gray-50 rounded-2xl border border-gray-200/80">
            <span className="text-[10px] font-bold uppercase tracking-wider text-gray-400">Service / Redressal Portal</span>
            <p className="text-sm font-bold text-blue-700 mt-0.5">{right.relevantService || 'National Grievance Portal'}</p>
          </div>
        </div>

        {right.officialSourceUrl && (
          <div className="pt-2">
            <a
              href={right.officialSourceUrl}
              target="_blank"
              rel="noopener noreferrer"
              className="inline-flex items-center gap-1.5 text-xs text-blue-600 hover:underline font-bold"
            >
              <span>Verify at Official Source: {right.sourceAuthority || 'India Code / Ministry Portal'}</span>
              <ExternalLink className="w-3.5 h-3.5" />
            </a>
          </div>
        )}
      </div>

      {/* Frequently Asked Questions */}
      {right.faqs && right.faqs.length > 0 && (
        <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
          <h2 className="text-sm font-bold text-[#123B63]">Frequently Asked Citizen Questions</h2>
          <div className="space-y-3 pt-1">
            {right.faqs.map((faq) => (
              <div key={faq.id} className="p-4 bg-gray-50 rounded-2xl border border-gray-100 space-y-1">
                <h4 className="text-xs font-bold text-[#123B63]">Q: {faq.question}</h4>
                <p className="text-xs text-gray-600 leading-relaxed">A: {faq.answer}</p>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
};

export default RightDetailPage;
