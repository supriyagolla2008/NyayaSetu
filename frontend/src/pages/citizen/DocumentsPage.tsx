import React, { useState } from 'react';
import { Upload, FileText, Trash2, CheckCircle2, Clock, Eye, Download } from 'lucide-react';

export const DocumentsPage: React.FC = () => {
  const [docs, setDocs] = useState([
    { id: 1, name: 'Employment_Agreement_2024.pdf', size: '2.4 MB', case: '#RF1024', uploadedAt: '11 Oct 2024', status: 'Verified' },
    { id: 2, name: 'Identity_Proof_Aadhaar.pdf', size: '1.1 MB', case: '#RF1024', uploadedAt: '10 Oct 2024', status: 'Verified' },
    { id: 3, name: 'Fridge_Invoice_Receipt.pdf', size: '850 KB', case: '#RF1020', uploadedAt: '05 Oct 2024', status: 'Verified' },
    { id: 4, name: 'Rent_Agreement_Signed.pdf', size: '3.6 MB', case: '#RF1018', uploadedAt: '29 Sep 2024', status: 'Verified' },
  ]);

  const handleUpload = () => {
    alert('Document upload simulation: File uploaded securely and encrypted for case #RF1024.');
  };

  return (
    <div className="max-w-6xl mx-auto space-y-6">
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-black text-[#123B63]">Case Documents</h1>
          <p className="text-xs text-gray-500 mt-1">Upload and securely associate legal proofs, receipts, notices and agreements with your cases.</p>
        </div>
        <button
          onClick={handleUpload}
          className="flex items-center gap-2 bg-[#2563EB] hover:bg-blue-700 text-white font-bold px-4 py-2.5 rounded-xl text-xs shadow-xs"
        >
          <Upload className="w-4 h-4" />
          <span>Upload Document</span>
        </button>
      </div>

      {/* Drag & Drop Upload Mockup */}
      <div 
        onClick={handleUpload}
        className="bg-white rounded-3xl p-8 border-2 border-dashed border-blue-200 hover:border-blue-400 cursor-pointer text-center space-y-2 transition-colors"
      >
        <div className="w-12 h-12 mx-auto rounded-2xl bg-blue-50 text-[#2563EB] flex items-center justify-center">
          <Upload className="w-6 h-6" />
        </div>
        <h3 className="text-sm font-bold text-[#123B63]">Click or drag files here to upload</h3>
        <p className="text-xs text-gray-400">Supported: PDF, JPG, PNG (Max 10MB). Encrypted with Spring Security.</p>
      </div>

      {/* Documents Table */}
      <div className="bg-white rounded-3xl border border-gray-100 shadow-sm overflow-hidden">
        <div className="p-4 border-b border-gray-100 text-xs font-bold text-[#123B63]">
          Uploaded Files
        </div>
        <div className="divide-y divide-gray-100">
          {docs.map((doc) => (
            <div key={doc.id} className="p-4 flex items-center justify-between hover:bg-gray-50/50 text-xs">
              <div className="flex items-center gap-3">
                <div className="w-9 h-9 rounded-xl bg-red-50 text-red-600 flex items-center justify-center font-bold text-[10px]">
                  PDF
                </div>
                <div>
                  <span className="font-bold text-[#123B63] block">{doc.name}</span>
                  <span className="text-[11px] text-gray-400">{doc.size} • Linked to {doc.case} • Uploaded {doc.uploadedAt}</span>
                </div>
              </div>

              <div className="flex items-center gap-2">
                <span className="text-[10px] font-bold text-green-700 bg-green-50 px-2 py-0.5 rounded-full flex items-center gap-1">
                  <CheckCircle2 className="w-3 h-3" /> {doc.status}
                </span>
                <button className="p-1.5 rounded-lg border border-gray-200 hover:bg-gray-100 text-gray-600">
                  <Download className="w-3.5 h-3.5" />
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default DocumentsPage;
