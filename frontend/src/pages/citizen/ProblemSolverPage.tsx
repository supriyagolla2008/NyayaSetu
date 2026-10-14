import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { 
  Keyboard, 
  Mic, 
  MicOff, 
  Sparkles, 
  ArrowRight, 
  Check, 
  Volume2, 
  Bot,
  Info
} from 'lucide-react';

export const ProblemSolverPage: React.FC = () => {
  const navigate = useNavigate();
  const [mode, setMode] = useState<'type' | 'voice'>('type');
  const [problemText, setProblemText] = useState('My employer has not paid my salary for two months.');
  const [isRecording, setIsRecording] = useState(false);
  const [recordingSeconds, setRecordingSeconds] = useState(0);

  const exampleProblems = [
    'My salary is not paid',
    'Landlord not returning deposit',
    'Defective product refund',
    'Student fee issue',
    'Property dispute',
    'Hospital overcharging'
  ];

  const handleVoiceToggle = () => {
    if (!isRecording) {
      setIsRecording(true);
      setRecordingSeconds(1);
    } else {
      setIsRecording(false);
      setProblemText('My employer has not paid my salary for two months despite completing full notice period.');
    }
  };

  const handleGetGuidance = () => {
    navigate('/citizen/ai-assistant', { state: { query: problemText } });
  };

  return (
    <div className="max-w-5xl mx-auto space-y-6">
      {/* 1. Progress Stepper Matching Screen 7 */}
      <div className="bg-white rounded-2xl p-4 sm:p-6 border border-gray-100 shadow-sm">
        <div className="flex items-center justify-between max-w-3xl mx-auto">
          {[
            { step: '1', title: 'Describe', active: true, completed: false },
            { step: '2', title: 'Understand', active: false, completed: false },
            { step: '3', title: 'Get Guidance', active: false, completed: false },
            { step: '4', title: 'Take Action', active: false, completed: false },
          ].map((s, idx) => (
            <React.Fragment key={idx}>
              <div className="flex items-center gap-2">
                <span className={`w-8 h-8 rounded-full text-xs font-bold flex items-center justify-center ${
                  s.active
                    ? 'bg-[#2563EB] text-white shadow-xs'
                    : 'bg-gray-100 text-gray-500'
                }`}>
                  {s.step}
                </span>
                <span className={`text-xs font-bold hidden sm:inline ${
                  s.active ? 'text-[#123B63]' : 'text-gray-400'
                }`}>
                  {s.title}
                </span>
              </div>
              {idx < 3 && <div className="flex-1 h-0.5 bg-gray-200 mx-2 sm:mx-4"></div>}
            </React.Fragment>
          ))}
        </div>
      </div>

      {/* 2. Main Problem Input Card Matching Screen 7 */}
      <div className="bg-white rounded-3xl p-6 sm:p-8 border border-gray-100 shadow-sm relative overflow-hidden">
        {/* Toggle Mode: Type / Voice */}
        <div className="flex items-center gap-2 mb-6">
          <button
            onClick={() => setMode('type')}
            className={`flex items-center gap-2 px-4 py-2 rounded-xl text-xs font-bold transition-all ${
              mode === 'type'
                ? 'bg-blue-50 text-[#2563EB] border border-blue-200'
                : 'bg-gray-50 text-gray-600 hover:bg-gray-100'
            }`}
          >
            <Keyboard className="w-4 h-4" />
            <span>Type</span>
          </button>

          <button
            onClick={() => setMode('voice')}
            className={`flex items-center gap-2 px-4 py-2 rounded-xl text-xs font-bold transition-all ${
              mode === 'voice'
                ? 'bg-blue-50 text-[#2563EB] border border-blue-200'
                : 'bg-gray-50 text-gray-600 hover:bg-gray-100'
            }`}
          >
            <Mic className="w-4 h-4" />
            <span>Voice</span>
          </button>
        </div>

        {/* Input Area with Avatar illustration on right */}
        <div className="grid grid-cols-1 md:grid-cols-12 gap-6 items-center">
          <div className="md:col-span-8 space-y-4">
            <div className="relative">
              <textarea
                rows={5}
                value={problemText}
                onChange={(e) => setProblemText(e.target.value)}
                placeholder="e.g. My landlord is not returning my security deposit..."
                className="w-full p-4 rounded-2xl border border-gray-200 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 bg-gray-50/40 text-gray-800 resize-none leading-relaxed"
              />

              {/* Voice recording trigger button inside textarea */}
              <button
                type="button"
                onClick={handleVoiceToggle}
                className={`absolute right-4 bottom-4 w-10 h-10 rounded-full flex items-center justify-center shadow-sm transition-all ${
                  isRecording 
                    ? 'bg-red-500 text-white animate-pulse' 
                    : 'bg-[#2563EB] text-white hover:bg-blue-700'
                }`}
                title="Speak your problem"
              >
                {isRecording ? <MicOff className="w-5 h-5" /> : <Mic className="w-5 h-5" />}
              </button>
            </div>

            {isRecording && (
              <div className="p-3 bg-red-50 border border-red-200 rounded-xl text-xs text-red-700 flex items-center gap-2">
                <span className="w-2.5 h-2.5 rounded-full bg-red-600 animate-ping"></span>
                <span>Listening to Indian English / Hindi / Telugu... Speak now. Click mic again to finish.</span>
              </div>
            )}
          </div>

          {/* Right Citizen illustration / assistant guide */}
          <div className="md:col-span-4 bg-gradient-to-tr from-blue-50 to-indigo-50/60 rounded-2xl p-6 border border-blue-100/70 text-center space-y-3">
            <div className="w-16 h-16 mx-auto rounded-full bg-white shadow-sm flex items-center justify-center text-[#2563EB]">
              <Bot className="w-8 h-8" />
            </div>
            <h3 className="font-bold text-[#123B63] text-sm">AI Legal Navigator</h3>
            <p className="text-xs text-gray-500 leading-relaxed">
              Our AI verifies Indian laws and maps your issue to exact sections and authorities.
            </p>
          </div>
        </div>

        {/* Example Problem Tags */}
        <div className="mt-6 pt-6 border-t border-gray-100">
          <div className="text-xs font-semibold text-gray-500 mb-3">Example problems:</div>
          <div className="flex flex-wrap gap-2">
            {exampleProblems.map((prob, i) => (
              <button
                key={i}
                type="button"
                onClick={() => setProblemText(prob)}
                className="text-xs font-medium text-gray-700 bg-gray-50 hover:bg-blue-50 hover:text-[#2563EB] border border-gray-200 rounded-lg px-3 py-1.5 transition-colors"
              >
                {prob}
              </button>
            ))}
          </div>
        </div>

        {/* Submit button */}
        <div className="mt-8 flex justify-end">
          <button
            onClick={handleGetGuidance}
            className="flex items-center gap-2 bg-[#2563EB] hover:bg-blue-700 text-white font-bold px-8 py-3.5 rounded-xl text-sm shadow-md transition-all"
          >
            <span>Get Guidance</span>
            <ArrowRight className="w-4 h-4" />
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProblemSolverPage;
