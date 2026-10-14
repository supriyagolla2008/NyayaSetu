import React from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  Scale, 
  ArrowLeft, 
  MessageSquare, 
  Search, 
  CheckSquare, 
  FolderPlus, 
  ShieldCheck, 
  ExternalLink,
  ArrowRight,
  FileText,
  Clock,
  Sparkles
} from 'lucide-react';

export const HowItWorksPage: React.FC = () => {
  const navigate = useNavigate();

  const steps = [
    {
      step: '01',
      title: 'Describe Your Problem in Plain Words',
      badge: 'Multilingual Natural Language Input',
      icon: MessageSquare,
      color: 'bg-blue-50 text-[#2563EB] border-blue-200',
      description: 'You do not need to know legal terms or act names. Simply describe what happened in everyday English, Hindi, or Telugu—e.g. "My landlord kept my deposit", "Employer did not pay 3 months salary", or "Someone stole money through online OTP scam".',
      details: [
        'Supports informal vocabulary and transliteration',
        'Optional prompt templates for common disputes',
        'Complete confidentiality of citizen submissions'
      ]
    },
    {
      step: '02',
      title: 'Intelligent Problem Classification & Grounding',
      badge: 'AI & Deterministic Engine',
      icon: Sparkles,
      color: 'bg-teal-50 text-teal-600 border-teal-200',
      description: 'NyayaSetu classifies your dispute across 14 statutory categories using high-precision keyword patterns and AI semantic search. It matches your case to exact sections of Indian Acts.',
      details: [
        'Works seamlessly with or without external AI keys via deterministic fallback',
        'Grounded in 22 primary Indian Acts and 70+ codified statutory rights',
        'Cites landmark Supreme Court and High Court precedents'
      ]
    },
    {
      step: '03',
      title: 'Structured Action Plan & Document Checklist',
      badge: 'Practical Legal Guidance',
      icon: CheckSquare,
      color: 'bg-purple-50 text-purple-600 border-purple-200',
      description: 'You receive an ordered, procedural roadmap detailing exactly what to do first, which notices to serve, and what evidentiary documents you must gather before approaching an authority.',
      details: [
        'Chronological action items (e.g., Step 1: Issue formal notice, Step 2: Lodge complaint)',
        'Mandatory vs optional document checklist (contracts, receipts, chat logs)',
        'Statutory limitation periods and expected resolution timelines'
      ]
    },
    {
      step: '04',
      title: 'Official Government Portal Navigation',
      badge: 'Public Service Routing',
      icon: ExternalLink,
      color: 'bg-amber-50 text-amber-600 border-amber-200',
      description: 'Avoid middlemen and touts. NyayaSetu provides direct links, helplines, and procedural instructions for official Government of India dispute resolution portals.',
      details: [
        'e-Daakhil for Consumer Commission filings',
        'National Cyber Crime Reporting Portal (1930 Helpline)',
        'National Legal Services Authority (NALSA) for free legal aid',
        'Samadhan portal for labour conciliation and eCourts services'
      ]
    },
    {
      step: '05',
      title: 'Track Cases & Manage Documentation',
      badge: 'Citizen Dispute Dashboard',
      icon: FolderPlus,
      color: 'bg-rose-50 text-rose-600 border-rose-200',
      description: 'Save your problem analysis directly as an active case. Track your checklist completion, log case milestones from Draft to Resolved, and manage reference documents.',
      details: [
        'Real-time status transitions: Draft → Pending → In Progress → Resolved',
        'Interactive checklist checkboxes saved directly to PostgreSQL',
        'Secure document storage and personal bookmarks'
      ]
    }
  ];

  return (
    <div className="min-h-screen bg-[#EFF8FF] text-[#172033] flex flex-col">
      {/* Top Navigation */}
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

          <div className="flex items-center gap-4">
            <button 
              onClick={() => navigate('/')}
              className="flex items-center gap-1.5 text-xs font-semibold text-gray-600 hover:text-[#123B63] bg-white px-3 py-1.5 rounded-lg border border-gray-200 shadow-xs transition-colors"
            >
              <ArrowLeft className="w-4 h-4" />
              Home
            </button>
            <button 
              onClick={() => navigate('/select-role')}
              className="bg-[#2563EB] hover:bg-blue-700 text-white px-4 py-1.5 rounded-lg text-xs font-semibold shadow-xs"
            >
              Get Started
            </button>
          </div>
        </div>
      </nav>

      {/* Main Content */}
      <main className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 py-10 space-y-12">
        {/* Header */}
        <div className="text-center space-y-3">
          <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-blue-100 text-[#123B63] text-xs font-semibold">
            <ShieldCheck className="w-3.5 h-3.5 text-[#2563EB]" />
            Citizen Process Walkthrough
          </div>
          <h1 className="text-3xl sm:text-4xl font-extrabold text-[#123B63] tracking-tight">
            How NyayaSetu Works
          </h1>
          <p className="text-base text-gray-600 max-w-2xl mx-auto leading-relaxed">
            From plain-language problem submission to statutory resolution and government portal filing—here is how the platform guides you step by step.
          </p>
        </div>

        {/* Steps Grid */}
        <div className="space-y-6">
          {steps.map((st, idx) => {
            const Icon = st.icon;
            return (
              <div 
                key={idx}
                className="bg-white rounded-2xl border border-gray-200/80 p-6 sm:p-8 shadow-sm flex flex-col md:flex-row gap-6 items-start"
              >
                <div className="flex items-center md:flex-col gap-4 shrink-0">
                  <div className={`w-14 h-14 rounded-2xl border flex items-center justify-center font-extrabold text-xl ${st.color}`}>
                    {st.step}
                  </div>
                  <div className="w-10 h-10 rounded-xl bg-gray-50 flex items-center justify-center text-gray-600">
                    <Icon className="w-5 h-5" />
                  </div>
                </div>

                <div className="space-y-3 flex-1">
                  <div className="flex flex-wrap items-center gap-3">
                    <h2 className="text-lg sm:text-xl font-bold text-[#123B63]">{st.title}</h2>
                    <span className="text-[11px] font-semibold px-2.5 py-0.5 rounded-full bg-blue-50 text-[#2563EB] border border-blue-100">
                      {st.badge}
                    </span>
                  </div>
                  <p className="text-sm text-gray-700 leading-relaxed">
                    {st.description}
                  </p>

                  <div className="pt-2">
                    <ul className="grid grid-cols-1 sm:grid-cols-2 gap-2 text-xs text-gray-600">
                      {st.details.map((item, i) => (
                        <li key={i} className="flex items-center gap-2">
                          <span className="w-1.5 h-1.5 rounded-full bg-[#2563EB] shrink-0" />
                          <span>{item}</span>
                        </li>
                      ))}
                    </ul>
                  </div>
                </div>
              </div>
            );
          })}
        </div>

        {/* Call to action */}
        <div className="bg-[#123B63] text-white rounded-2xl p-8 text-center space-y-4 shadow-md">
          <h2 className="text-2xl font-bold">Ready to Resolve Your Legal Dispute?</h2>
          <p className="text-sm text-blue-100 max-w-xl mx-auto">
            Experience NyayaSetu right now. Start by describing your problem or browsing the 14 verified legal categories.
          </p>
          <div className="flex flex-wrap justify-center gap-4 pt-2">
            <button
              onClick={() => navigate('/citizen/problem')}
              className="bg-[#2563EB] hover:bg-blue-600 text-white font-bold px-6 py-3 rounded-xl text-sm shadow transition-all flex items-center gap-2"
            >
              <span>Solve a Legal Problem</span>
              <ArrowRight className="w-4 h-4" />
            </button>
            <button
              onClick={() => navigate('/citizen/rights')}
              className="bg-white/10 hover:bg-white/20 text-white font-semibold px-6 py-3 rounded-xl text-sm border border-white/20 transition-all"
            >
              Browse 70+ Rights
            </button>
          </div>
        </div>
      </main>

      {/* Footer */}
      <footer className="mt-auto bg-white border-t border-gray-200 py-6 px-4 text-center text-xs text-gray-500">
        <div className="max-w-7xl mx-auto flex flex-col sm:flex-row items-center justify-between gap-4">
          <div className="flex items-center gap-2">
            <Scale className="w-4 h-4 text-[#2563EB]" />
            <span className="font-bold text-[#123B63]">NyayaSetu (न्यायसेतु)</span>
            <span>• Know Your Rights. Find Your Way to Justice.</span>
          </div>
          <div>© 2024-2026 NyayaSetu. Grounded in Official Indian Legal Sources.</div>
        </div>
      </footer>
    </div>
  );
};

export default HowItWorksPage;
