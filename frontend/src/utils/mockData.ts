// Mock Data for RightsFlow matching Indian Legal Framework and UI Prototype

export interface LegalRightItem {
  id: string;
  title: string;
  category: string;
  categoryColor: string;
  shortDescription: string;
  simpleExplanation: string;
  detailedExplanation: string;
  applicableLaw: string;
  section: string;
  officialSourceUrl: string;
  sourceAuthority: string;
  lastVerified: string;
  status: 'Published' | 'Under Review' | 'Draft' | 'Outdated';
  recommendedActions: string[];
  documentsNeeded: string[];
}

export const MOCK_RIGHTS: LegalRightItem[] = [
  {
    id: 'right-1',
    title: 'Right to Receive Salary',
    category: 'Employment & Labour',
    categoryColor: 'teal',
    shortDescription: 'Employees are entitled to receive salary for work performed as per applicable labour laws.',
    simpleExplanation: 'If you have worked, your employer must pay you the salary as per your employment agreement and labour laws without unauthorized deductions.',
    detailedExplanation: 'Under Section 3 and 15 of the Payment of Wages Act, 1936, and the Code on Wages, 2019, wages must be paid within 7 to 10 days of the wage period ending. Unpaid wages attract claims before the Labour Commissioner.',
    applicableLaw: 'Payment of Wages Act, 1936 & Code on Wages, 2019',
    section: 'Section 3 & Section 15 - Payment and deductions of wages',
    officialSourceUrl: 'https://labour.gov.in/payment-wages-act-1936',
    sourceAuthority: 'Ministry of Labour and Employment, Govt. of India',
    lastVerified: '12 Oct 2024',
    status: 'Published',
    recommendedActions: [
      'Collect salary slips and employment documents',
      'Send a formal written demand notice to your employer',
      'Approach the District Labour Commissioner if unpaid',
      'Track your complaint online or via conciliation'
    ],
    documentsNeeded: [
      'Employment agreement or Offer Letter',
      'Salary slips for last 3-6 months',
      'Bank statement showing missing credit',
      'Written communication/emails with employer',
      'Identity proof (Aadhaar/PAN)'
    ]
  },
  {
    id: 'right-2',
    title: 'Consumer Protection Against Defective Goods',
    category: 'Consumer Rights',
    categoryColor: 'orange',
    shortDescription: 'Consumers have the right to seek replacement, refund, or damages for defective products.',
    simpleExplanation: 'If you bought a product that is faulty or not as promised, the seller or manufacturer must repair it, replace it, or refund your money.',
    detailedExplanation: 'Under Section 2(9) and Section 35 of the Consumer Protection Act, 2019, consumers can file grievances with the National Consumer Helpline (NCH) or approach the District Consumer Disputes Redressal Commission.',
    applicableLaw: 'Consumer Protection Act, 2019',
    section: 'Section 2(9), 35 & 84 - Product liability and consumer rights',
    officialSourceUrl: 'https://consumerhelpline.gov.in',
    sourceAuthority: 'Department of Consumer Affairs, Govt. of India',
    lastVerified: '10 Oct 2024',
    status: 'Published',
    recommendedActions: [
      'Preserve tax invoice and warranty card',
      'File complaint on National Consumer Helpline (1915)',
      'Issue legal notice to manufacturer/seller',
      'File e-Daakhil case before District Commission'
    ],
    documentsNeeded: [
      'Tax Invoice / Purchase Receipt',
      'Warranty Card',
      'Photographs/Videos of the defect',
      'Correspondence with customer service'
    ]
  },
  {
    id: 'right-3',
    title: 'Right to Free Legal Aid',
    category: 'Legal Aid & Access to Justice',
    categoryColor: 'indigo',
    shortDescription: 'Eligible citizens are entitled to free legal counsel and court representation.',
    simpleExplanation: 'Women, children, SC/ST members, persons with disabilities, and low-income citizens can get a free lawyer appointed by the government.',
    detailedExplanation: 'Article 39A of the Constitution of India and Section 12 of the Legal Services Authorities Act, 1987 guarantee free legal assistance through NALSA, SLSA, and DLSA.',
    applicableLaw: 'Legal Services Authorities Act, 1987',
    section: 'Section 12 - Criteria for giving legal services',
    officialSourceUrl: 'https://nalsa.gov.in',
    sourceAuthority: 'National Legal Services Authority (NALSA)',
    lastVerified: '09 Oct 2024',
    status: 'Published',
    recommendedActions: [
      'Visit your District Legal Services Authority (DLSA)',
      'Submit Form-1 for legal aid application',
      'Meet the assigned panel lawyer for consultation'
    ],
    documentsNeeded: [
      'Income Certificate (if applicable)',
      'Identity Proof',
      'Documents related to dispute'
    ]
  },
  {
    id: 'right-4',
    title: 'Right to Security Deposit Refund (Tenancy)',
    category: 'Housing & Tenancy',
    categoryColor: 'cyan',
    shortDescription: 'Tenants are entitled to the full refund of security deposit upon vacating.',
    simpleExplanation: 'When you vacate a rental property, the landlord must return your security deposit within the agreed notice period after deducting only valid, agreed-upon repair costs.',
    detailedExplanation: 'Governed by State Rent Control Acts and the Model Tenancy Act, 2021. Landlords cannot arbitrarily withhold deposits without providing an itemized statement of damages.',
    applicableLaw: 'Model Tenancy Act, 2021 & State Tenancy Acts',
    section: 'Section 11 - Security deposit terms and refund',
    officialSourceUrl: 'https://mohua.gov.in/model-tenancy-act',
    sourceAuthority: 'Ministry of Housing and Urban Affairs',
    lastVerified: '08 Oct 2024',
    status: 'Under Review',
    recommendedActions: [
      'Conduct joint handover inspection with landlord',
      'Record walkthrough video showing property condition',
      'Send formal written demand for deposit refund',
      'Approach Rent Court / Rent Authority'
    ],
    documentsNeeded: [
      'Rental Agreement',
      'Rent Receipts / Bank Transfer Proofs',
      'Security Deposit Payment Proof',
      'Notice of Vacating Proof'
    ]
  },
  {
    id: 'right-5',
    title: 'Right to Digital Privacy & Data Protection',
    category: 'Cyber & Digital Rights',
    categoryColor: 'blue',
    shortDescription: 'Citizens have the right to withdraw consent and request erasure of personal data.',
    simpleExplanation: 'Companies cannot use your personal information without valid consent, and you can request them to delete or correct your personal information.',
    detailedExplanation: 'Under the Digital Personal Data Protection Act, 2023, data principals have rights of access, correction, erasure, and grievance redressal through the Data Protection Board of India.',
    applicableLaw: 'Digital Personal Data Protection Act, 2023',
    section: 'Section 11, 12 & 13 - Rights of Data Principal',
    officialSourceUrl: 'https://www.meity.gov.in/dpdp-act',
    sourceAuthority: 'Ministry of Electronics & Information Technology',
    lastVerified: '07 Oct 2024',
    status: 'Draft',
    recommendedActions: [
      'Send data erasure request to Data Protection Officer (DPO)',
      'File grievance if unresolved within 30 days',
      'Lodge complaint with Data Protection Board'
    ],
    documentsNeeded: [
      'Account details / Service ID',
      'Consent record / Privacy notice',
      'Communication logs with data fiduciary'
    ]
  }
];

export interface CaseItem {
  id: string;
  caseNumber: string;
  title: string;
  category: string;
  categoryColor: string;
  status: 'Created' | 'Information Reviewed' | 'Documents Pending' | 'Ready for Action' | 'Action Taken' | 'Submitted' | 'Under Review' | 'Resolved' | 'Closed';
  relatedRight: string;
  authority: string;
  service: string;
  createdOn: string;
  lastUpdated: string;
  timeline: { title: string; date: string; completed: boolean; current?: boolean }[];
  documents: { name: string; uploaded: boolean }[];
  actions: { title: string; status: 'Pending' | 'In Progress' | 'Completed' }[];
  notes: string[];
}

export const MOCK_CASES: CaseItem[] = [
  {
    id: 'case-1',
    caseNumber: '#RF1024',
    title: 'Employment Issue — Salary Delay',
    category: 'Employment & Labour',
    categoryColor: 'teal',
    status: 'Documents Pending',
    relatedRight: 'Right to Receive Salary',
    authority: 'Labour Department',
    service: 'File Labour Complaint',
    createdOn: '10 Oct 2024',
    lastUpdated: '12 Oct 2024',
    timeline: [
      { title: 'Problem Created', date: '10 Oct', completed: true },
      { title: 'Rights Identified', date: '11 Oct', completed: true },
      { title: 'Documents Pending', date: '12 Oct', completed: false, current: true },
      { title: 'Ready', date: '--', completed: false },
      { title: 'Submitted', date: '--', completed: false },
      { title: 'Under Review', date: '--', completed: false },
      { title: 'Resolved', date: '--', completed: false },
      { title: 'Closed', date: '--', completed: false },
    ],
    documents: [
      { name: 'Employment agreement', uploaded: true },
      { name: 'Salary slips (last 3 months)', uploaded: false },
      { name: 'Bank statement', uploaded: false },
      { name: 'Communication with employer', uploaded: false },
      { name: 'Identity proof', uploaded: true }
    ],
    actions: [
      { title: 'Collect salary slips and employment documents', status: 'In Progress' },
      { title: 'Send a written request to your employer', status: 'Pending' },
      { title: 'Approach the Labour Department', status: 'Pending' },
      { title: 'Track your complaint', status: 'Pending' }
    ],
    notes: [
      'Employer has not paid salary for August and September 2024.',
      'HR email received stating company cash flow delay.'
    ]
  },
  {
    id: 'case-2',
    caseNumber: '#RF1020',
    title: 'Consumer Complaint — Defective Product',
    category: 'Consumer Rights',
    categoryColor: 'orange',
    status: 'Under Review',
    relatedRight: 'Consumer Protection Against Defective Goods',
    authority: 'District Consumer Disputes Redressal Commission',
    service: 'National Consumer Helpline (NCH)',
    createdOn: '04 Oct 2024',
    lastUpdated: '09 Oct 2024',
    timeline: [
      { title: 'Problem Created', date: '04 Oct', completed: true },
      { title: 'Rights Identified', date: '05 Oct', completed: true },
      { title: 'Documents Verified', date: '06 Oct', completed: true },
      { title: 'Ready', date: '07 Oct', completed: true },
      { title: 'Submitted', date: '08 Oct', completed: true },
      { title: 'Under Review', date: '09 Oct', completed: false, current: true },
      { title: 'Resolved', date: '--', completed: false },
      { title: 'Closed', date: '--', completed: false },
    ],
    documents: [
      { name: 'Tax Invoice', uploaded: true },
      { name: 'Warranty Card', uploaded: true },
      { name: 'Defect photos', uploaded: true }
    ],
    actions: [
      { title: 'Grievance submitted on NCH (Ticket #82710)', status: 'Completed' },
      { title: 'Awaiting response from seller', status: 'In Progress' }
    ],
    notes: [
      'Refrigerator stopped cooling after 10 days of delivery. Seller refused return.'
    ]
  },
  {
    id: 'case-3',
    caseNumber: '#RF1018',
    title: 'Property Dispute — Security Deposit',
    category: 'Housing & Tenancy',
    categoryColor: 'cyan',
    status: 'Action Taken',
    relatedRight: 'Right to Security Deposit Refund (Tenancy)',
    authority: 'Rent Authority / Rent Court',
    service: 'Tenancy Dispute Resolution',
    createdOn: '28 Sep 2024',
    lastUpdated: '06 Oct 2024',
    timeline: [
      { title: 'Problem Created', date: '28 Sep', completed: true },
      { title: 'Rights Identified', date: '29 Sep', completed: true },
      { title: 'Documents Verified', date: '01 Oct', completed: true },
      { title: 'Action Taken', date: '06 Oct', completed: true, current: true },
      { title: 'Resolved', date: '--', completed: false },
      { title: 'Closed', date: '--', completed: false },
    ],
    documents: [
      { name: 'Rent Agreement', uploaded: true },
      { name: 'Bank Transfer Receipt', uploaded: true }
    ],
    actions: [
      { title: 'Legal notice sent to landlord', status: 'Completed' }
    ],
    notes: ['Landlord withheld Rs. 50,000 without valid justification.']
  },
  {
    id: 'case-4',
    caseNumber: '#RF1015',
    title: 'Education Fee Issue — Excess Charges',
    category: 'Education & Student Rights',
    categoryColor: 'purple',
    status: 'Resolved',
    relatedRight: 'Right to Fee Transparency in Private Institutions',
    authority: 'Fee Regulatory Committee',
    service: 'Student Grievance Portal',
    createdOn: '15 Sep 2024',
    lastUpdated: '02 Oct 2024',
    timeline: [
      { title: 'Problem Created', date: '15 Sep', completed: true },
      { title: 'Submitted', date: '20 Sep', completed: true },
      { title: 'Under Review', date: '25 Sep', completed: true },
      { title: 'Resolved', date: '02 Oct', completed: true, current: true },
      { title: 'Closed', date: '03 Oct', completed: true },
    ],
    documents: [
      { name: 'Fee Receipt', uploaded: true },
      { name: 'Prospectus', uploaded: true }
    ],
    actions: [
      { title: 'Refund processed by college', status: 'Completed' }
    ],
    notes: ['Excess capitation fee refunded by administration.']
  }
];

export const MOCK_LEGAL_UPDATES = [
  {
    id: 'upd-1',
    title: 'New Amendment in Consumer Protection Rules',
    category: 'Consumer Rights',
    categoryColor: 'orange',
    status: 'Published',
    lastUpdated: '12 Oct 2024',
    summary: 'Central Government updates guidelines regarding dark patterns and automatic subscription renewals on e-commerce platforms.',
    effectiveDate: '01 Nov 2024',
    source: 'Ministry of Consumer Affairs'
  },
  {
    id: 'upd-2',
    title: 'Updated Guidelines for Legal Aid Eligibility',
    category: 'Legal Aid',
    categoryColor: 'indigo',
    status: 'Published',
    lastUpdated: '10 Oct 2024',
    summary: 'NALSA increases annual income threshold for free legal counsel to expand coverage for middle-income citizens in metropolitan areas.',
    effectiveDate: '15 Oct 2024',
    source: 'National Legal Services Authority'
  },
  {
    id: 'upd-3',
    title: 'Labour Law Amendment on Timely Wage Settlements',
    category: 'Employment',
    categoryColor: 'teal',
    status: 'Under Review',
    lastUpdated: '08 Oct 2024',
    summary: 'Mandatory electronic wage transfer verification for contract and temporary workers across all registered establishments.',
    effectiveDate: 'Pending Verification',
    source: 'Ministry of Labour'
  },
  {
    id: 'upd-4',
    title: 'New Digital Privacy Guidelines for App Permissions',
    category: 'Cyber Rights',
    categoryColor: 'blue',
    status: 'Draft',
    lastUpdated: '07 Oct 2024',
    summary: 'Draft framework released specifying limits on mobile app access to microphone, camera, and gallery permissions.',
    effectiveDate: 'Draft Stage',
    source: 'Data Protection Board of India'
  }
];

export const MOCK_USERS = [
  {
    id: 'u-1',
    name: 'Priya Sharma',
    email: 'priya@example.com',
    role: 'CITIZEN',
    status: 'Active',
    joinedOn: '10 Oct 2024'
  },
  {
    id: 'u-2',
    name: 'Anil Kumar',
    email: 'anil@example.com',
    role: 'CONTENT_MANAGER',
    status: 'Active',
    joinedOn: '08 Oct 2024'
  },
  {
    id: 'u-3',
    name: 'Rekha Iyer',
    email: 'rekha@example.com',
    role: 'CITIZEN',
    status: 'Inactive',
    joinedOn: '05 Oct 2024'
  },
  {
    id: 'u-4',
    name: 'Vikram Singh',
    email: 'vikram@example.com',
    role: 'ADMINISTRATOR',
    status: 'Active',
    joinedOn: '01 Oct 2024'
  }
];
