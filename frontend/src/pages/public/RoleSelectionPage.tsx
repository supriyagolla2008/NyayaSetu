import React from 'react';
import { useNavigate } from 'react-router-dom';
import { User, FileText, Shield, Check, ArrowLeft, Scale } from 'lucide-react';

export const RoleSelectionPage: React.FC = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen bg-[#EFF8FF] text-[#172033] flex flex-col justify-between p-4 sm:p-6 lg:p-8">
      {/* Header */}
      <div className="max-w-6xl mx-auto w-full flex items-center justify-between py-2">
        <button 
          onClick={() => navigate('/')}
          className="flex items-center gap-1.5 text-xs font-semibold text-gray-600 hover:text-[#123B63] bg-white px-3 py-1.5 rounded-lg border border-gray-200 shadow-xs transition-colors"
        >
          <ArrowLeft className="w-4 h-4" />
          Back to Home
        </button>

        <div className="flex items-center gap-2 cursor-pointer" onClick={() => navigate('/')}>
          <div className="w-8 h-8 rounded-lg bg-[#2563EB] flex items-center justify-center text-white">
            <Scale className="w-5 h-5" />
          </div>
          <span className="font-extrabold text-[#123B63] tracking-tight">NyayaSetu (न्यायसेतु)</span>
        </div>
      </div>

      {/* Main Section Matching Screen 2 */}
      <div className="max-w-5xl mx-auto w-full my-auto py-8">
        <div className="text-center space-y-2 mb-10">
          <h1 className="text-3xl sm:text-4xl font-extrabold text-[#123B63] tracking-tight">
            Welcome to NyayaSetu (न्यायसेतु)
          </h1>
          <p className="text-base text-[#64748B]">
            Know Your Rights. Find Your Way to Justice. Choose your workspace to continue.
          </p>
        </div>

        {/* 3 Role Cards Grid */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {/* CARD 1: CITIZEN */}
          <div className="bg-white rounded-2xl border border-gray-200/90 p-7 shadow-sm hover:shadow-md hover:border-blue-300 transition-all flex flex-col justify-between text-left">
            <div>
              <div className="w-14 h-14 rounded-2xl bg-blue-50 border border-blue-100 flex items-center justify-center mb-5 text-[#2563EB]">
                <User className="w-7 h-7" />
              </div>
              <h2 className="text-xl font-bold text-[#123B63]">Citizen</h2>
              <p className="text-xs text-gray-500 mt-2 leading-relaxed">
                Understand your rights, get guidance, and find your next step.
              </p>

              <div className="mt-6 space-y-3">
                {[
                  'Ask about a problem',
                  'Explore legal rights',
                  'Get AI guidance',
                  'Track cases'
                ].map((feat, i) => (
                  <div key={i} className="flex items-start gap-2.5 text-xs text-gray-700">
                    <span className="w-4 h-4 rounded-full bg-blue-100 text-[#2563EB] flex items-center justify-center shrink-0 mt-0.5">
                      <Check className="w-3 h-3 stroke-[3]" />
                    </span>
                    <span>{feat}</span>
                  </div>
                ))}
              </div>
            </div>

            <button
              onClick={() => navigate('/login/citizen')}
              className="mt-8 w-full bg-[#2563EB] hover:bg-blue-700 text-white font-semibold py-3 px-4 rounded-xl text-sm shadow-sm transition-colors text-center"
            >
              Continue as Citizen
            </button>
          </div>

          {/* CARD 2: CONTENT MANAGER */}
          <div className="bg-white rounded-2xl border border-gray-200/90 p-7 shadow-sm hover:shadow-md hover:border-teal-300 transition-all flex flex-col justify-between text-left">
            <div>
              <div className="w-14 h-14 rounded-2xl bg-teal-50 border border-teal-100 flex items-center justify-center mb-5 text-teal-600">
                <FileText className="w-7 h-7" />
              </div>
              <h2 className="text-xl font-bold text-[#123B63]">Content Manager</h2>
              <p className="text-xs text-gray-500 mt-2 leading-relaxed">
                Create, verify and manage trusted legal information.
              </p>

              <div className="mt-6 space-y-3">
                {[
                  'Manage rights and laws',
                  'Verify official sources',
                  'Manage legal updates',
                  'Manage RAG content'
                ].map((feat, i) => (
                  <div key={i} className="flex items-start gap-2.5 text-xs text-gray-700">
                    <span className="w-4 h-4 rounded-full bg-teal-100 text-teal-700 flex items-center justify-center shrink-0 mt-0.5">
                      <Check className="w-3 h-3 stroke-[3]" />
                    </span>
                    <span>{feat}</span>
                  </div>
                ))}
              </div>
            </div>

            <button
              onClick={() => navigate('/login/content-manager')}
              className="mt-8 w-full bg-[#0D9488] hover:bg-teal-700 text-white font-semibold py-3 px-4 rounded-xl text-sm shadow-sm transition-colors text-center"
            >
              Continue as Content Manager
            </button>
          </div>

          {/* CARD 3: ADMINISTRATOR */}
          <div className="bg-white rounded-2xl border border-gray-200/90 p-7 shadow-sm hover:shadow-md hover:border-purple-300 transition-all flex flex-col justify-between text-left">
            <div>
              <div className="w-14 h-14 rounded-2xl bg-purple-50 border border-purple-100 flex items-center justify-center mb-5 text-purple-600">
                <Shield className="w-7 h-7" />
              </div>
              <h2 className="text-xl font-bold text-[#123B63]">Administrator</h2>
              <p className="text-xs text-gray-500 mt-2 leading-relaxed">
                Manage users, security, analytics and platform operations.
              </p>

              <div className="mt-6 space-y-3">
                {[
                  'Manage users and roles',
                  'Monitor cases',
                  'View analytics',
                  'Manage system configuration'
                ].map((feat, i) => (
                  <div key={i} className="flex items-start gap-2.5 text-xs text-gray-700">
                    <span className="w-4 h-4 rounded-full bg-purple-100 text-purple-700 flex items-center justify-center shrink-0 mt-0.5">
                      <Check className="w-3 h-3 stroke-[3]" />
                    </span>
                    <span>{feat}</span>
                  </div>
                ))}
              </div>
            </div>

            <button
              onClick={() => navigate('/login/admin')}
              className="mt-8 w-full bg-[#7C3AED] hover:bg-purple-700 text-white font-semibold py-3 px-4 rounded-xl text-sm shadow-sm transition-colors text-center"
            >
              Continue as Administrator
            </button>
          </div>
        </div>
      </div>

      {/* Security Note */}
      <div className="text-center text-xs text-gray-500 py-2">
        Protected with Role-Based Access Control (RBAC) & Spring Security verification.
      </div>
    </div>
  );
};

export default RoleSelectionPage;
