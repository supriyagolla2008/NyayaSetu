import React from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  Scale, 
  Briefcase, 
  ShoppingBag, 
  Heart, 
  GraduationCap, 
  Activity, 
  Home, 
  ShieldCheck, 
  Grid,
  Bot,
  Compass,
  CheckCircle2,
  ArrowRight,
  FileText,
  Search,
  ExternalLink,
  BookOpen,
  Users,
  Shield,
  HelpCircle,
  Clock,
  Sparkles
} from 'lucide-react';

export const LandingPage: React.FC = () => {
  const navigate = useNavigate();

  const categories = [
    { name: 'Constitutional Rights', icon: Scale, color: 'text-indigo-600', bg: 'bg-indigo-50 border-indigo-200' },
    { name: 'Criminal Justice', icon: Shield, color: 'text-rose-600', bg: 'bg-rose-50 border-rose-200' },
    { name: 'Consumer Protection', icon: ShoppingBag, color: 'text-orange-600', bg: 'bg-orange-50 border-orange-200' },
    { name: 'Labour & Employment', icon: Briefcase, color: 'text-teal-600', bg: 'bg-teal-50 border-teal-200' },
    { name: 'Women & Gender Justice', icon: Heart, color: 'text-pink-600', bg: 'bg-pink-50 border-pink-200' },
    { name: 'Child Rights', icon: Users, color: 'text-emerald-600', bg: 'bg-emerald-50 border-emerald-200' },
    { name: 'Tenancy & Housing', icon: Home, color: 'text-amber-600', bg: 'bg-amber-50 border-amber-200' },
    { name: 'Digital & Cyber Rights', icon: ShieldCheck, color: 'text-blue-600', bg: 'bg-blue-50 border-blue-200' },
    { name: 'Healthcare & Medical', icon: Activity, color: 'text-red-600', bg: 'bg-red-50 border-red-200' },
    { name: 'Environment & Tribal', icon: Compass, color: 'text-green-600', bg: 'bg-green-50 border-green-200' },
    { name: 'Senior Citizens', icon: Clock, color: 'text-cyan-600', bg: 'bg-cyan-50 border-cyan-200' },
    { name: 'Persons with Disabilities', icon: Sparkles, color: 'text-violet-600', bg: 'bg-violet-50 border-violet-200' },
    { name: 'RTI & Governance', icon: FileText, color: 'text-blue-700', bg: 'bg-blue-50 border-blue-200' },
    { name: 'Family & Succession', icon: BookOpen, color: 'text-yellow-700', bg: 'bg-yellow-50 border-yellow-200' },
  ];

  return (
    <div className="min-h-screen bg-[#EFF8FF] text-[#172033] flex flex-col">
      {/* 1. Top Navigation Bar */}
      <nav className="bg-white border-b border-gray-100 shadow-sm sticky top-[33px] z-40">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between">
          <div className="flex items-center gap-2.5 cursor-pointer" onClick={() => navigate('/')}>
            <div className="w-10 h-10 rounded-xl bg-[#2563EB] flex items-center justify-center text-white shadow-md">
              <Scale className="w-6 h-6" />
            </div>
            <div>
              <span className="text-xl font-extrabold text-[#123B63] tracking-tight">NyayaSetu (न्यायसेतु)</span>
              <span className="block text-[10px] text-gray-500 font-semibold tracking-wider leading-none">KNOW YOUR RIGHTS. FIND YOUR WAY TO JUSTICE.</span>
            </div>
          </div>

          <div className="hidden md:flex items-center gap-8 text-sm font-medium text-gray-600">
            <a href="#home" className="text-[#2563EB] font-semibold">Home</a>
            <a href="#rights" onClick={(e) => { e.preventDefault(); navigate('/citizen/rights'); }} className="hover:text-[#123B63] transition-colors">Your Rights (70+)</a>
            <a href="#ai" onClick={(e) => { e.preventDefault(); navigate('/citizen/problem'); }} className="hover:text-[#123B63] transition-colors">AI Assistant</a>
            <a href="#updates" onClick={(e) => { e.preventDefault(); navigate('/citizen/legal-updates'); }} className="hover:text-[#123B63] transition-colors">Legal Updates</a>
            <a href="#how" onClick={(e) => { e.preventDefault(); navigate('/how-it-works'); }} className="hover:text-[#123B63] transition-colors">How It Works</a>
            <a href="#about" onClick={(e) => { e.preventDefault(); navigate('/about'); }} className="hover:text-[#123B63] transition-colors">About</a>
          </div>

          <div className="flex items-center gap-3">
            <button 
              onClick={() => navigate('/select-role')}
              className="bg-[#2563EB] hover:bg-blue-700 text-white px-5 py-2 rounded-xl text-sm font-semibold shadow-sm hover:shadow transition-all"
            >
              Get Started
            </button>
          </div>
        </div>
      </nav>

      {/* 2. Hero Section */}
      <section className="relative overflow-hidden pt-10 pb-16 px-4 sm:px-6 lg:px-8 max-w-7xl mx-auto w-full">
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-center">
          {/* Left Text Column */}
          <div className="lg:col-span-7 space-y-6">
            <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-blue-100/70 border border-blue-200 text-xs font-semibold text-[#123B63]">
              <ShieldCheck className="w-3.5 h-3.5 text-[#2563EB]" />
              Digital Legal Awareness & Citizen Service Platform • India
            </div>

            <h1 className="text-4xl sm:text-5xl lg:text-6xl font-extrabold text-[#123B63] leading-[1.15] tracking-tight">
              Know Your Rights.<br />
              <span className="text-[#2563EB]">Find Your Way to Justice.</span>
            </h1>

            <p className="text-lg text-[#64748B] max-w-xl leading-relaxed">
              Describe your everyday legal problem in ordinary language (English, हिन्दी, or తెలుగు). NyayaSetu maps your issue to verified Indian statutes, action checklists, and official grievance portals.
            </p>

            <div className="flex flex-wrap gap-4 pt-2">
              <button 
                onClick={() => navigate('/citizen/problem')}
                className="flex items-center gap-2 bg-[#2563EB] hover:bg-blue-700 text-white px-6 py-3.5 rounded-xl font-bold text-base shadow-md hover:shadow-lg transition-all"
              >
                <span>Describe Your Problem</span>
                <ArrowRight className="w-5 h-5" />
              </button>

              <button 
                onClick={() => navigate('/citizen/rights')}
                className="flex items-center gap-2 bg-white hover:bg-gray-50 text-[#123B63] border border-gray-200 px-6 py-3.5 rounded-xl font-semibold text-base shadow-sm transition-all"
              >
                <span>Browse 14 Categories</span>
              </button>
            </div>
          </div>

          {/* Right Column - Supreme Court Graphic Card */}
          <div className="lg:col-span-5 relative">
            <div className="bg-gradient-to-b from-sky-100/90 to-blue-50/50 rounded-3xl p-6 border border-blue-100 shadow-xl relative overflow-hidden text-center">
              <div className="relative mx-auto max-w-sm py-4">
                <div className="w-24 h-24 mx-auto rounded-full bg-white/90 shadow-md flex items-center justify-center border-4 border-amber-100 mb-4">
                  <Scale className="w-12 h-12 text-[#123B63]" />
                </div>
                <div className="bg-white/95 rounded-2xl p-5 shadow-lg border border-blue-100/80 backdrop-blur-sm">
                  <div className="flex items-center justify-center gap-2 mb-2">
                    <span className="w-3 h-2 bg-[#FF9933] rounded-xs"></span>
                    <span className="w-3 h-2 bg-white border border-gray-200 rounded-xs"></span>
                    <span className="w-3 h-2 bg-[#138808] rounded-xs"></span>
                    <span className="text-xs font-bold text-[#123B63] uppercase tracking-wider">Supreme Court of India • India Code</span>
                  </div>
                  <h3 className="font-bold text-[#123B63] text-base">Verified Legal Knowledge</h3>
                  <p className="text-xs text-gray-500 mt-1">Grounding guidance in 22+ primary Indian Acts, Supreme Court constitutional precedents, NALSA legal aid, and eCourts services.</p>
                  
                  <div className="mt-4 pt-3 border-t border-gray-100 flex items-center justify-around text-xs text-gray-600">
                    <span className="flex items-center gap-1 text-[#16A34A] font-semibold">
                      <CheckCircle2 className="w-3.5 h-3.5" /> 70 Verified Rights
                    </span>
                    <span className="text-gray-300">•</span>
                    <span>English / हिन्दी / తెలుగు</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* 3. Category Tiles Grid */}
        <div className="mt-14">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-xl font-bold text-[#123B63]">14 Statutory Legal Categories</h2>
            <button 
              onClick={() => navigate('/citizen/rights')}
              className="text-sm font-semibold text-[#2563EB] hover:underline flex items-center gap-1"
            >
              View All 70+ Rights <ArrowRight className="w-4 h-4" />
            </button>
          </div>
          <div className="grid grid-cols-2 sm:grid-cols-4 lg:grid-cols-7 gap-3">
            {categories.map((cat, idx) => {
              const IconComp = cat.icon;
              return (
                <div 
                  key={idx}
                  onClick={() => navigate('/citizen/rights')}
                  className={`bg-white hover:bg-gray-50 rounded-2xl p-3.5 border border-gray-100 shadow-sm hover:shadow transition-all cursor-pointer flex flex-col items-center text-center group`}
                >
                  <div className={`w-11 h-11 rounded-xl flex items-center justify-center mb-2.5 ${cat.bg} transition-transform group-hover:scale-105`}>
                    <IconComp className={`w-5 h-5 ${cat.color}`} />
                  </div>
                  <span className="text-xs font-semibold text-[#172033] line-clamp-2 leading-tight">
                    {cat.name}
                  </span>
                </div>
              );
            })}
          </div>
        </div>

        {/* 4. Stats Banner */}
        <div className="mt-10 bg-white rounded-2xl border border-gray-100 shadow-sm p-6 grid grid-cols-2 md:grid-cols-4 gap-6 text-center">
          <div className="space-y-1">
            <div className="text-2xl lg:text-3xl font-extrabold text-[#123B63]">70+</div>
            <div className="text-xs font-semibold text-gray-500">Statutory Rights Documented</div>
          </div>
          <div className="space-y-1 border-l border-gray-100">
            <div className="text-2xl lg:text-3xl font-extrabold text-[#16A34A]">14 Categories</div>
            <div className="text-xs font-semibold text-gray-500">Comprehensive Indian Law</div>
          </div>
          <div className="space-y-1 border-l border-gray-100">
            <div className="text-2xl lg:text-3xl font-extrabold text-[#2563EB]">Multilingual AI</div>
            <div className="text-xs font-semibold text-gray-500">English, Hindi & Telugu NLP</div>
          </div>
          <div className="space-y-1 border-l border-gray-100">
            <div className="text-2xl lg:text-3xl font-extrabold text-[#123B63]">Grievance Portals</div>
            <div className="text-xs font-semibold text-gray-500">eDaakhil, NALSA, CyberCrime</div>
          </div>
        </div>

        {/* 5. How It Works Section */}
        <div className="mt-16 text-center">
          <h2 className="text-2xl sm:text-3xl font-extrabold text-[#123B63]">How NyayaSetu Empowers You</h2>
          <p className="text-sm text-gray-500 mt-2 max-w-xl mx-auto">From understanding your legal situation to taking official action in four simple steps.</p>
          
          <div className="mt-8 grid grid-cols-1 md:grid-cols-4 gap-6 text-left">
            <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm">
              <div className="w-8 h-8 rounded-lg bg-blue-100 text-[#2563EB] font-bold flex items-center justify-center text-sm mb-3">1</div>
              <h3 className="font-bold text-[#123B63] text-sm">Describe Problem</h3>
              <p className="text-xs text-gray-500 mt-1">Speak or write your issue in plain language (English, Hindi, or Telugu) without legal jargon.</p>
            </div>
            <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm">
              <div className="w-8 h-8 rounded-lg bg-teal-100 text-teal-700 font-bold flex items-center justify-center text-sm mb-3">2</div>
              <h3 className="font-bold text-[#123B63] text-sm">Instant Statute Mapping</h3>
              <p className="text-xs text-gray-500 mt-1">Retrieves applicable Indian statutes, sections, and rights grounded in our verified database.</p>
            </div>
            <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm">
              <div className="w-8 h-8 rounded-lg bg-purple-100 text-purple-700 font-bold flex items-center justify-center text-sm mb-3">3</div>
              <h3 className="font-bold text-[#123B63] text-sm">Action Plan & Checklist</h3>
              <p className="text-xs text-gray-500 mt-1">Get an ordered next-step roadmap with required document checklists and official portal links.</p>
            </div>
            <div className="bg-white rounded-2xl p-5 border border-gray-100 shadow-sm">
              <div className="w-8 h-8 rounded-lg bg-amber-100 text-amber-700 font-bold flex items-center justify-center text-sm mb-3">4</div>
              <h3 className="font-bold text-[#123B63] text-sm">Track Your Case</h3>
              <p className="text-xs text-gray-500 mt-1">Save cases, manage action items, track progress, and store necessary documentation.</p>
            </div>
          </div>
        </div>

        {/* 6. Legal Disclaimer */}
        <div className="mt-14 bg-blue-50/70 border border-blue-200/60 rounded-2xl p-4 text-center max-w-4xl mx-auto">
          <p className="text-xs text-gray-600 leading-relaxed">
            <strong className="text-[#123B63]">Official Legal Disclaimer:</strong> NyayaSetu (न्यायसेतु) provides general legal awareness and public-service navigation. It does not provide formal legal advice, establish an advocate-client relationship, or substitute professional legal counsel.
          </p>
        </div>
      </section>

      {/* Footer */}
      <footer className="mt-auto bg-white border-t border-gray-200 py-8 px-4 text-center text-xs text-gray-500">
        <div className="max-w-7xl mx-auto flex flex-col sm:flex-row items-center justify-between gap-4">
          <div className="flex items-center gap-2">
            <Scale className="w-4 h-4 text-[#2563EB]" />
            <span className="font-bold text-[#123B63]">NyayaSetu (न्यायसेतु)</span>
            <span>• Know Your Rights. Find Your Way to Justice.</span>
          </div>
          <div>© 2024-2026 NyayaSetu. Grounded in Official Indian Legal Sources (India Code, NALSA, eCourts).</div>
        </div>
      </footer>
    </div>
  );
};

export default LandingPage;
