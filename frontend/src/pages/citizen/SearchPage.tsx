import React, { useState, useEffect } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { Search, BookOpen, FileText, Briefcase, Sparkles, ArrowRight, Loader2, AlertCircle } from 'lucide-react';
import aiService from '../../services/aiService';
import { GlobalSearchResponse } from '../../types';

export const SearchPage: React.FC = () => {
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();
  const initialQuery = searchParams.get('q') || 'salary';

  const [query, setQuery] = useState(initialQuery);
  const [searchType, setSearchType] = useState('ALL');
  const [results, setResults] = useState<GlobalSearchResponse | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const performSearch = async (term: string, type: string) => {
    if (!term.trim()) return;
    setLoading(true);
    setError(null);
    try {
      const res = await aiService.search(term.trim(), undefined, type);
      setResults(res);
      setSearchParams({ q: term.trim() });
    } catch (err: any) {
      setError(err.message || 'Search execution failed.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (initialQuery) {
      performSearch(initialQuery, searchType);
    }
  }, [searchType]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    performSearch(query, searchType);
  };

  const sampleKeywords = [
    'Salary Delay',
    'Tenant Security Deposit',
    'Consumer Refund TV',
    'POSH Workplace',
    'UPI Fraud 1930',
    'Zero FIR Lalita Kumari',
    'Free Legal Aid DLSA',
    'Senior Citizen Maintenance'
  ];

  return (
    <div className="max-w-5xl mx-auto space-y-6 pb-12">
      <div>
        <h1 className="text-2xl font-black text-[#123B63]">Unified Legal Knowledge Search</h1>
        <p className="text-xs text-gray-500 mt-1">
          Search across 70 verified rights, statutory laws, government dispute portals, and recent amendments.
        </p>
      </div>

      {/* Search Input Box */}
      <div className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm space-y-4">
        <form onSubmit={handleSubmit} className="relative">
          <Search className="w-5 h-5 text-gray-400 absolute left-4 top-1/2 -translate-y-1/2" />
          <input
            type="text"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            placeholder="Describe your legal question or search terms..."
            className="w-full pl-12 pr-28 py-3 rounded-2xl border border-gray-200 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50/50"
          />
          <button
            type="submit"
            disabled={loading}
            className="absolute right-2.5 top-1/2 -translate-y-1/2 bg-[#2563EB] hover:bg-blue-700 text-white text-xs font-bold px-4 py-2 rounded-xl shadow-xs transition-colors disabled:opacity-50"
          >
            {loading ? 'Searching...' : 'Search'}
          </button>
        </form>

        {/* Popular chips */}
        <div className="flex items-center gap-1.5 flex-wrap text-xs text-gray-500">
          <span className="font-semibold text-gray-400 mr-1">Suggestions:</span>
          {sampleKeywords.map(chip => (
            <button
              key={chip}
              onClick={() => {
                setQuery(chip);
                performSearch(chip, searchType);
              }}
              className="px-2.5 py-1 rounded-lg bg-gray-50 hover:bg-blue-50 hover:text-blue-600 border border-gray-200/60 text-[11px] transition-colors"
            >
              {chip}
            </button>
          ))}
        </div>

        {/* Type Tabs */}
        <div className="flex items-center gap-2 pt-2 border-t border-gray-100 overflow-x-auto">
          {[
            { key: 'ALL', label: 'All Results' },
            { key: 'RIGHT', label: 'Legal Rights' },
            { key: 'LAW', label: 'Statutory Acts' },
            { key: 'SERVICE', label: 'Dispute Services' },
            { key: 'UPDATE', label: 'Legal Updates' },
          ].map(t => (
            <button
              key={t.key}
              onClick={() => setSearchType(t.key)}
              className={`px-3 py-1.5 rounded-xl text-xs font-bold transition-colors ${
                searchType === t.key
                  ? 'bg-blue-600 text-white shadow-xs'
                  : 'bg-gray-50 text-gray-600 hover:bg-gray-100'
              }`}
            >
              {t.label}
            </button>
          ))}
        </div>
      </div>

      {error && (
        <div className="p-4 bg-red-50 border border-red-200 rounded-2xl flex items-center gap-2 text-xs text-red-700">
          <AlertCircle className="w-4 h-4" />
          <span>{error}</span>
        </div>
      )}

      {loading ? (
        <div className="py-20 flex flex-col items-center justify-center text-gray-400 gap-2">
          <Loader2 className="w-7 h-7 animate-spin text-blue-600" />
          <span className="text-xs">Searching verified database records...</span>
        </div>
      ) : results && (
        <div className="space-y-6">
          <div className="text-xs font-semibold text-gray-500">
            Found <strong className="text-[#123B63]">{results.totalResults}</strong> verified records matching "{results.query}".
          </div>

          {/* Rights Section */}
          {results.rights && results.rights.length > 0 && (
            <div className="space-y-3">
              <h3 className="text-xs font-bold text-gray-500 uppercase tracking-wider flex items-center gap-1.5">
                <BookOpen className="w-4 h-4 text-blue-600" /> Matched Legal Rights ({results.rights.length})
              </h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {results.rights.map(r => (
                  <div
                    key={r.id}
                    onClick={() => navigate(`/citizen/rights/${r.slug || r.id}`)}
                    className="p-5 bg-white rounded-2xl border border-gray-100 shadow-sm hover:border-blue-200 hover:shadow-md transition-all cursor-pointer flex flex-col justify-between"
                  >
                    <div>
                      <span className="text-[10px] font-bold text-teal-800 bg-teal-50 px-2.5 py-0.5 rounded-full">
                        {r.categoryName || 'Legal Right'}
                      </span>
                      <h4 className="text-sm font-bold text-[#123B63] mt-2">{r.title}</h4>
                      <p className="text-xs text-gray-500 mt-1 line-clamp-2">{r.shortDescription || r.simpleExplanation}</p>
                    </div>
                    <div className="mt-4 pt-3 border-t border-gray-100 flex justify-end">
                      <span className="text-xs font-bold text-blue-600 flex items-center gap-1">
                        <span>Read Guidance</span>
                        <ArrowRight className="w-3.5 h-3.5" />
                      </span>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* Laws Section */}
          {results.laws && results.laws.length > 0 && (
            <div className="space-y-3 pt-4 border-t border-gray-100">
              <h3 className="text-xs font-bold text-gray-500 uppercase tracking-wider flex items-center gap-1.5">
                <FileText className="w-4 h-4 text-purple-600" /> Statutory Acts & Laws ({results.laws.length})
              </h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {results.laws.map(l => (
                  <div key={l.id} className="p-4 bg-white rounded-2xl border border-gray-100 shadow-sm space-y-1">
                    <span className="text-[10px] font-mono font-bold text-purple-700 bg-purple-50 px-2 py-0.5 rounded-full">
                      {l.shortCode || `Act of ${l.year}`}
                    </span>
                    <h4 className="text-sm font-bold text-[#123B63] mt-1">{l.title}</h4>
                    <p className="text-xs text-gray-500">{l.description}</p>
                    {l.officialLink && (
                      <a
                        href={l.officialLink}
                        target="_blank"
                        rel="noreferrer"
                        className="inline-block text-xs text-blue-600 hover:underline font-semibold pt-1"
                      >
                        View Official Legislation Link →
                      </a>
                    )}
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* Services Section */}
          {results.services && results.services.length > 0 && (
            <div className="space-y-3 pt-4 border-t border-gray-100">
              <h3 className="text-xs font-bold text-gray-500 uppercase tracking-wider flex items-center gap-1.5">
                <Briefcase className="w-4 h-4 text-teal-600" /> Redressal Services & Portals ({results.services.length})
              </h3>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {results.services.map(s => (
                  <div key={s.id} className="p-4 bg-white rounded-2xl border border-gray-100 shadow-sm space-y-1">
                    <h4 className="text-sm font-bold text-[#123B63]">{s.name}</h4>
                    <p className="text-xs text-gray-500">{s.description}</p>
                    {s.onlinePortalUrl && (
                      <a
                        href={s.onlinePortalUrl}
                        target="_blank"
                        rel="noreferrer"
                        className="inline-block text-xs text-teal-700 hover:underline font-semibold pt-1"
                      >
                        Visit Service Portal ({s.onlinePortalUrl}) →
                      </a>
                    )}
                  </div>
                ))}
              </div>
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default SearchPage;
