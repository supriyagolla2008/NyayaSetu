import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Search, BookOpen, ArrowRight, Bookmark, ShieldCheck, Loader2, ExternalLink, HelpCircle } from 'lucide-react';
import rightsService from '../../services/rightsService';
import { LegalCategory, LegalRightSummary } from '../../types';

export const RightsLibraryPage: React.FC = () => {
  const navigate = useNavigate();
  const [categories, setCategories] = useState<LegalCategory[]>([]);
  const [rights, setRights] = useState<LegalRightSummary[]>([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCatId, setSelectedCatId] = useState<number | 'ALL'>('ALL');
  const [loading, setLoading] = useState(true);
  const [bookmarkedIds, setBookmarkedIds] = useState<Set<number>>(new Set());

  useEffect(() => {
    const loadInitialData = async () => {
      try {
        const [catsRes, bookmarksRes] = await Promise.allSettled([
          rightsService.getCategories(),
          rightsService.getBookmarks(),
        ]);

        if (catsRes.status === 'fulfilled') {
          setCategories(catsRes.value || []);
        }
        if (bookmarksRes.status === 'fulfilled') {
          const ids = new Set((bookmarksRes.value || []).map(b => Number(b.id)));
          setBookmarkedIds(ids);
        }
      } catch (e) {
        console.error('Failed to load categories/bookmarks:', e);
      }
    };

    loadInitialData();
  }, []);

  const loadRights = async () => {
    setLoading(true);
    try {
      const res = await rightsService.getRights({
        categoryId: selectedCatId === 'ALL' ? undefined : selectedCatId,
        query: searchTerm.trim() || undefined,
        size: 100,
      });
      setRights(res.content || []);
    } catch (e) {
      console.error('Failed to fetch rights:', e);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadRights();
  }, [selectedCatId]);

  const handleSearchSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    loadRights();
  };

  const handleToggleBookmark = async (e: React.MouseEvent, rightId: number) => {
    e.stopPropagation();
    try {
      const isBookmarked = await rightsService.toggleBookmark(rightId);
      setBookmarkedIds(prev => {
        const next = new Set(prev);
        if (isBookmarked) {
          next.add(rightId);
        } else {
          next.delete(rightId);
        }
        return next;
      });
    } catch (e) {
      console.error('Bookmark toggle failed:', e);
    }
  };

  return (
    <div className="max-w-7xl mx-auto space-y-6">
      <div>
        <h1 className="text-2xl font-black text-[#123B63]">Verified Indian Legal Rights Library</h1>
        <p className="text-xs text-gray-500 mt-1">
          70 statutory citizen rights across 14 categories. Grounded in India Code, Supreme Court precedents, and official portals.
        </p>
      </div>

      {/* Search & Category Filter */}
      <div className="bg-white rounded-2xl p-4 border border-gray-100 shadow-sm space-y-3">
        <form onSubmit={handleSearchSubmit} className="relative">
          <Search className="w-4 h-4 text-gray-400 absolute left-3.5 top-1/2 -translate-y-1/2" />
          <input
            type="text"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            placeholder="Search rights across laws (e.g. salary, rent deposit, posH, consumer refund, zero fir, rti)..."
            className="w-full pl-9 pr-24 py-2.5 rounded-xl border border-gray-200 text-xs focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50/50"
          />
          <button
            type="submit"
            className="absolute right-2 top-1/2 -translate-y-1/2 bg-blue-600 hover:bg-blue-700 text-white text-xs font-semibold px-3 py-1.5 rounded-lg shadow-xs"
          >
            Search
          </button>
        </form>

        {/* 14 Categories Pills */}
        <div className="flex flex-wrap gap-1.5 pt-1 max-h-28 overflow-y-auto">
          <button
            onClick={() => setSelectedCatId('ALL')}
            className={`px-3 py-1.5 rounded-xl text-xs font-semibold transition-all ${
              selectedCatId === 'ALL'
                ? 'bg-[#2563EB] text-white shadow-xs'
                : 'bg-gray-50 text-gray-600 hover:bg-gray-100 border border-gray-200/60'
            }`}
          >
            All 14 Categories
          </button>
          {categories.map((cat) => (
            <button
              key={cat.id}
              onClick={() => setSelectedCatId(Number(cat.id))}
              className={`px-3 py-1.5 rounded-xl text-xs font-semibold transition-all ${
                selectedCatId === Number(cat.id)
                  ? 'bg-[#2563EB] text-white shadow-xs'
                  : 'bg-gray-50 text-gray-600 hover:bg-gray-100 border border-gray-200/60'
              }`}
            >
              {cat.name}
            </button>
          ))}
        </div>
      </div>

      {/* Rights Grid */}
      {loading ? (
        <div className="py-20 flex flex-col items-center justify-center text-gray-400 gap-2">
          <Loader2 className="w-7 h-7 animate-spin text-blue-600" />
          <span className="text-xs">Querying verified legal database...</span>
        </div>
      ) : rights.length === 0 ? (
        <div className="py-16 text-center bg-white border border-dashed border-gray-200 rounded-3xl p-8">
          <BookOpen className="w-10 h-10 text-gray-300 mx-auto mb-2" />
          <h3 className="text-base font-bold text-gray-700">No Legal Rights Found</h3>
          <p className="text-xs text-gray-500 mt-1 max-w-sm mx-auto">
            Try adjusting your search query or choose "All 14 Categories" to explore available rights.
          </p>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {rights.map((right) => {
            const isBookmarked = bookmarkedIds.has(Number(right.id));
            return (
              <div
                key={right.id}
                onClick={() => navigate(`/citizen/rights/${right.slug || right.id}`)}
                className="bg-white rounded-3xl p-6 border border-gray-100 shadow-sm hover:shadow-md hover:border-blue-200 transition-all flex flex-col justify-between group cursor-pointer"
              >
                <div>
                  <div className="flex items-center justify-between mb-3">
                    <span className="text-[10px] font-bold text-teal-800 bg-teal-50 px-2.5 py-1 rounded-full truncate max-w-[180px]">
                      {right.categoryName || 'Legal Right'}
                    </span>
                    <div className="flex items-center gap-1.5">
                      <span className="text-[10px] font-bold text-green-700 bg-green-50 px-2 py-0.5 rounded-full flex items-center gap-1">
                        <ShieldCheck className="w-3 h-3" /> Verified
                      </span>
                      <button
                        onClick={(e) => handleToggleBookmark(e, Number(right.id))}
                        className={`p-1.5 rounded-lg transition-colors ${
                          isBookmarked 
                            ? 'text-amber-500 bg-amber-50' 
                            : 'text-gray-300 hover:text-gray-600 hover:bg-gray-50'
                        }`}
                        title={isBookmarked ? 'Bookmarked' : 'Save Right'}
                      >
                        <Bookmark className="w-3.5 h-3.5 fill-current" />
                      </button>
                    </div>
                  </div>

                  <h2 className="text-base font-bold text-[#123B63] group-hover:text-[#2563EB] transition-colors">
                    {right.title}
                  </h2>
                  <p className="text-xs text-gray-500 mt-2 line-clamp-3 leading-relaxed">
                    {right.simpleExplanation || right.shortDescription}
                  </p>

                  {right.applicableLaw && (
                    <div className="mt-4 pt-3 border-t border-gray-100 text-[11px] text-gray-500">
                      <strong className="text-gray-700">Governing Law:</strong> {right.applicableLaw}
                    </div>
                  )}

                  {right.relevantAuthority && (
                    <div className="text-[11px] text-gray-500 mt-1">
                      <strong className="text-gray-700">Forum:</strong> {right.relevantAuthority}
                    </div>
                  )}
                </div>

                <div className="mt-6 pt-4 border-t border-gray-100 flex items-center justify-between">
                  <span className="text-xs font-bold text-[#2563EB] flex items-center gap-1 group-hover:underline">
                    <span>Full Legal Basis & Steps</span>
                    <ArrowRight className="w-3.5 h-3.5" />
                  </span>

                  <button
                    onClick={(e) => {
                      e.stopPropagation();
                      navigate('/citizen/problem', { state: { rightHint: right.title, categoryHint: right.categoryName } });
                    }}
                    className="p-1.5 rounded-lg text-gray-400 hover:text-blue-600 hover:bg-blue-50"
                    title="Ask AI regarding this right"
                  >
                    <HelpCircle className="w-4 h-4" />
                  </button>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};

export default RightsLibraryPage;
