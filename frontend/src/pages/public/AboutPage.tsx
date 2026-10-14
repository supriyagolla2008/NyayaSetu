import React from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  Scale, 
  ArrowLeft, 
  ShieldCheck, 
  BookOpen, 
  Layers, 
  Database, 
  Cpu, 
  Globe2, 
  CheckCircle2, 
  Award,
  Users
} from 'lucide-react';

export const AboutPage: React.FC = () => {
  const navigate = useNavigate();

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
            <Award className="w-3.5 h-3.5 text-[#2563EB]" />
            About the NyayaSetu Project
          </div>
          <h1 className="text-3xl sm:text-4xl font-extrabold text-[#123B63] tracking-tight">
            Bridging Everyday Citizens with Indian Justice
          </h1>
          <p className="text-base text-gray-600 max-w-2xl mx-auto leading-relaxed">
            NyayaSetu (न्यायसेतु) is a comprehensive full-stack legal rights awareness, dispute guidance, and public service navigation platform designed specifically for Indian statutory law.
          </p>
        </div>

        {/* Mission Statement */}
        <div className="bg-white rounded-2xl border border-gray-200/80 p-8 shadow-sm space-y-4">
          <h2 className="text-xl font-bold text-[#123B63] flex items-center gap-2">
            <Scale className="w-5 h-5 text-[#2563EB]" />
            Our Core Mission
          </h2>
          <p className="text-sm text-gray-700 leading-relaxed">
            In India, millions of citizens encounter legal hurdles daily—ranging from wrongful workplace termination, unpaid wages, and tenant-landlord disputes to consumer fraud and domestic violence. However, complex legal terminology, lack of procedural awareness, and unfamiliarity with official grievance portals prevent individuals from exercising their legitimate statutory rights.
          </p>
          <p className="text-sm text-gray-700 leading-relaxed">
            <strong>NyayaSetu (The Bridge to Justice)</strong> democratizes legal access by translating natural-language problem descriptions into concrete, actionable legal pathways backed by primary Indian statutes, actionable checklists, and direct navigation to official government portals.
          </p>
        </div>

        {/* 14 Categories Overview */}
        <div className="bg-white rounded-2xl border border-gray-200/80 p-8 shadow-sm space-y-6">
          <div>
            <h2 className="text-xl font-bold text-[#123B63] flex items-center gap-2">
              <BookOpen className="w-5 h-5 text-[#2563EB]" />
              14 Statutory Legal Categories
            </h2>
            <p className="text-xs text-gray-500 mt-1">
              Every category includes at least 5 verified statutory rights (70 total rights) grounded in India Code and Supreme Court precedents:
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 text-xs">
            {[
              { title: '1. Constitutional & Fundamental Rights', desc: 'Articles 14, 19, 21, 22, 32 – Equality, Speech, Life & Liberty, Habeas Corpus.' },
              { title: '2. Criminal Justice & Police Procedures', desc: 'BNSS 2023 / CrPC, Zero FIR, Arrest guidelines (D.K. Basu), Free Legal Aid.' },
              { title: '3. Consumer Protection & Fair Trade', desc: 'CPA 2019, e-Daakhil filing, Unfair trade practices, E-commerce refunds.' },
              { title: '4. Labour & Employment Rights', desc: 'Payment of Wages Act, Industrial Disputes Act, POSH Act 2013, Gratuity.' },
              { title: '5. Women & Gender Justice', desc: 'Domestic Violence Act 2005, Maternity Benefit Act 1961, Section 498A BNS.' },
              { title: '6. Child Rights & Protection', desc: 'Right to Education (RTE) Act 2009, POCSO Act 2012, Child Labour Prohibition.' },
              { title: '7. Tenancy & Housing Rights', desc: 'Model Tenancy Act, RERA 2016 homebuyer protections, Security deposit refunds.' },
              { title: '8. Digital & Cyber Rights', desc: 'IT Act 2000, Digital Personal Data Protection Act 2023, CyberCrime portal 1930.' },
              { title: '9. Healthcare & Medical Rights', desc: 'Clinical Establishments Act, Emergency medical care (Parmanand Katara), Patient Charter.' },
              { title: '10. Environmental Protection & Tribal Rights', desc: 'National Green Tribunal Act, Forest Rights Act 2006, Clean environment under Art 21.' },
              { title: '11. Senior Citizens & Elder Care', desc: 'Maintenance and Welfare of Parents and Senior Citizens Act 2007, Old-age pensions.' },
              { title: '12. Persons with Disabilities (PwD) Rights', desc: 'RPwD Act 2016, 4% public employment reservation, Universal physical accessibility.' },
              { title: '13. Right to Information (RTI) & Governance', desc: 'RTI Act 2005 30-day disclosure mandates, Life & liberty 48-hr replies, First Appeals.' },
              { title: '14. Family & Succession Rights', desc: 'Hindu Succession Act 2005 (Coparcenary equality), Special Marriage Act, Custody.' }
            ].map((cat, idx) => (
              <div key={idx} className="p-3.5 bg-gray-50 rounded-xl border border-gray-100 space-y-1">
                <span className="font-bold text-[#123B63] block">{cat.title}</span>
                <span className="text-gray-600 block">{cat.desc}</span>
              </div>
            ))}
          </div>
        </div>

        {/* Technical Architecture */}
        <div className="bg-white rounded-2xl border border-gray-200/80 p-8 shadow-sm space-y-6">
          <h2 className="text-xl font-bold text-[#123B63] flex items-center gap-2">
            <Cpu className="w-5 h-5 text-[#2563EB]" />
            Full-Stack Technology Stack & Architecture
          </h2>

          <div className="grid grid-cols-1 sm:grid-cols-3 gap-6">
            <div className="p-4 bg-blue-50/60 rounded-xl border border-blue-100 space-y-2">
              <div className="flex items-center gap-2 font-bold text-sm text-[#123B63]">
                <Layers className="w-4 h-4 text-[#2563EB]" />
                React Frontend
              </div>
              <ul className="text-xs text-gray-600 space-y-1">
                <li>• React 18 & TypeScript</li>
                <li>• Vite fast build tooling</li>
                <li>• Tailwind CSS responsive design</li>
                <li>• Centralized Axios interceptors</li>
                <li>• Role-based route guards</li>
              </ul>
            </div>

            <div className="p-4 bg-teal-50/60 rounded-xl border border-teal-100 space-y-2">
              <div className="flex items-center gap-2 font-bold text-sm text-[#123B63]">
                <Cpu className="w-4 h-4 text-teal-600" />
                Spring Boot Backend
              </div>
              <ul className="text-xs text-gray-600 space-y-1">
                <li>• Java 21 LTS runtime</li>
                <li>• Spring Boot 3.3.4 & Spring Web</li>
                <li>• Spring Security 6 with JWT</li>
                <li>• Spring Data JPA & Hibernate</li>
                <li>• Multilingual NLP classification</li>
              </ul>
            </div>

            <div className="p-4 bg-purple-50/60 rounded-xl border border-purple-100 space-y-2">
              <div className="flex items-center gap-2 font-bold text-sm text-[#123B63]">
                <Database className="w-4 h-4 text-purple-600" />
                PostgreSQL Database
              </div>
              <ul className="text-xs text-gray-600 space-y-1">
                <li>• PostgreSQL 16 relational store</li>
                <li>• Flyway migrations (V1 through V7)</li>
                <li>• Normalized 3NF entity schema</li>
                <li>• Full-text search & GIN indexes</li>
                <li>• pgvector RAG readiness</li>
              </ul>
            </div>
          </div>
        </div>

        {/* Three Distinct RBAC Roles */}
        <div className="bg-white rounded-2xl border border-gray-200/80 p-8 shadow-sm space-y-4">
          <h2 className="text-xl font-bold text-[#123B63] flex items-center gap-2">
            <Users className="w-5 h-5 text-[#2563EB]" />
            Three Role-Based Workspaces
          </h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 text-xs">
            <div className="p-4 border border-blue-200 rounded-xl bg-blue-50/30">
              <h3 className="font-bold text-[#123B63] text-sm mb-1">Citizen</h3>
              <p className="text-gray-600">Access public rights library, search via natural language in 3 languages, submit problems for instant analysis, manage personal cases, track action checklists, and upload case documents.</p>
            </div>
            <div className="p-4 border border-teal-200 rounded-xl bg-teal-50/30">
              <h3 className="font-bold text-[#123B63] text-sm mb-1">Content Manager</h3>
              <p className="text-gray-600">Review statutory amendments, draft and verify legal rights, cite primary acts and official portals, maintain legal updates feed, and manage RAG vector search indexes.</p>
            </div>
            <div className="p-4 border border-purple-200 rounded-xl bg-purple-50/30">
              <h3 className="font-bold text-[#123B63] text-sm mb-1">Administrator</h3>
              <p className="text-gray-600">Oversee entire system health, view live user and case analytics, manage user accounts and role assignments, review audit logs, and configure system security settings.</p>
            </div>
          </div>
        </div>

        {/* College Project Disclaimer */}
        <div className="bg-amber-50/80 border border-amber-200 rounded-2xl p-6 text-center space-y-2">
          <h3 className="font-bold text-amber-900 text-sm">Academic Project Context & Legal Disclaimer</h3>
          <p className="text-xs text-amber-800 leading-relaxed max-w-3xl mx-auto">
            NyayaSetu is engineered as an advanced Java and DBMS capstone project. While legal information is diligently sourced and verified against India Code and Supreme Court precedents, this application serves exclusively as a digital educational and public-service navigation tool. It does not constitute formal legal counsel.
          </p>
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

export default AboutPage;
