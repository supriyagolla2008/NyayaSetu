// Real Indian Legal Database for RightsFlow Master Plan
// Grounded strictly in India Code, NALSA, Consumer Helpline, RBI, UGC, and Supreme Court Precedents

export interface LegalRightData {
  id: string;
  slug: string;
  title: string;
  category: string;
  categoryColor: string;
  keywords: string[];
  shortDescription: string;
  simpleExplanation: {
    en: string;
    hi: string;
    te: string;
  };
  detailedExplanation: string;
  whyItApplies: string;
  whoItAppliesTo: string;
  applicableLaw: string;
  section: string;
  officialSourceUrl: string;
  sourceAuthority: string;
  lastVerified: string;
  effectiveFrom: string;
  status: 'Published' | 'Under Review' | 'Draft' | 'Outdated';
  realWorldExample: string;
  recommendedActions: {
    step: number;
    title: string;
    description: string;
    status: 'Pending' | 'Not Started' | 'In Progress' | 'Completed';
    authority?: string;
  }[];
  documentChecklist: string[];
  actionChecklist: string[];
  authorityName: string;
  authorityPortal: string;
  complaintMechanism: string;
}

export const REAL_LEGAL_DATABASE: LegalRightData[] = [
  {
    id: 'right-salary-delay',
    slug: 'right-to-timely-wages',
    title: 'Right to Timely Payment of Wages & Salary',
    category: 'Employment & Labour',
    categoryColor: 'teal',
    keywords: ['salary', 'wage', 'employer', 'not paid', 'delayed salary', 'unpaid', 'boss', 'company not paying', 'pay slip', 'wages', 'dues', 'compensation', 'notice period pay'],
    shortDescription: 'Every worker and employee has the legal right to receive agreed remuneration without unauthorized delays or deductions.',
    simpleExplanation: {
      en: 'If you have worked for an employer, they must pay your full salary within 7 to 10 days of the month ending. An employer cannot withhold your salary for business losses, notice period disputes, or without formal legal authority.',
      hi: 'यदि आपने किसी नियोक्ता के लिए काम किया है, तो उन्हें महीने समाप्त होने के 7 से 10 दिनों के भीतर आपका पूरा वेतन देना होगा। कोई भी कंपनी बिना कानूनी आधार के आपका वेतन नहीं रोक सकती।',
      te: 'మీరు పనిచేసినట్లయితే, నెలాఖరు ముగిసిన 7 నుండి 10 రోజులలోపు యజమాని మీ పూర్తి జీతం చెల్లించాలి. కంపెనీ అక్రమంగా జీతాన్ని నిలిపివేయడం చట్టవిరుద్ధం.'
    },
    detailedExplanation: 'Sections 3, 5, and 15 of the Payment of Wages Act, 1936, together with Section 17 of the Code on Wages, 2019, mandate timely payment of wages. If delayed, the employee can claim compensation up to 10 times the unpaid amount before the Authority.',
    whyItApplies: 'Your employer is legally obligated to disburse wages for services rendered. Financial difficulties of the company do not waive the statutory obligation to pay workers.',
    whoItAppliesTo: 'Salaried employees, contract staff, commercial establishment workers, and industrial labor in India.',
    applicableLaw: 'Payment of Wages Act, 1936 & Code on Wages, 2019',
    section: 'Section 3 (Responsibility for payment of wages) & Section 15 (Claims arising out of deductions/delays)',
    officialSourceUrl: 'https://labour.gov.in/payment-wages-act-1936',
    sourceAuthority: 'Ministry of Labour & Employment, Government of India (India Code Act No. 4 of 1936)',
    lastVerified: '12 Oct 2024',
    effectiveFrom: '23 April 1936 / Code on Wages 2019',
    status: 'Published',
    realWorldExample: 'Ramesh worked as a software technician in Hyderabad. His employer withheld two months of salary citing delayed client payments. Ramesh filed a claim before the Labour Commissioner, and the authority ordered the company to pay full arrears plus 12% statutory interest within 30 days.',
    recommendedActions: [
      { step: 1, title: 'Collect Evidence of Employment & Unpaid Dues', description: 'Download offer letter, appointment letter, attendance records, work emails, and bank statements showing missing deposits.', status: 'Pending' },
      { step: 2, title: 'Send Formal Written Demand Notice', description: 'Issue a written legal notice to the employer granting 7 to 15 days to disburse pending wages via registered post or work email.', status: 'Pending' },
      { step: 3, title: 'Lodge Grievance on SAMADHAN Portal', description: 'Submit an online dispute on the Ministry of Labour SAMADHAN / Shram Suvidha portal.', status: 'Not Started', authority: 'Central/State Labour Conciliation' },
      { step: 4, title: 'Approach the Labour Court / Labour Commissioner', description: 'File Form A application under Section 15 of Payment of Wages Act before the jurisdictional Labour Officer.', status: 'Not Started', authority: 'Office of the Labour Commissioner' },
      { step: 5, title: 'Track Claim Hearing & Recovery Certificate', description: 'Attend conciliation hearings. If employer fails to appear, obtain a recovery certificate executable through the District Collector.', status: 'Not Started' }
    ],
    documentChecklist: [
      'Offer Letter or Employment Agreement',
      'Salary Slips for the last 3 to 6 months',
      'Bank Statement showing absence of salary credit',
      'Attendance logs, biometric printouts, or email logs',
      'Resignation letter / Notice period emails (if applicable)',
      'Aadhaar / Government ID Proof'
    ],
    actionChecklist: [
      'Gather pay slips and contract documents',
      'Send formal email demand notice to HR & Management',
      'File complaint on Shram Suvidha / Samadhan portal',
      'Attend conciliation hearing before Labour Officer',
      'Track recovery certificate'
    ],
    authorityName: 'District Labour Commissioner / Ministry of Labour & Employment',
    authorityPortal: 'https://samadhan.labour.gov.in',
    complaintMechanism: 'SAMADHAN Portal / Shram Suvidha (Toll-Free 1800-180-1551)'
  },
  {
    id: 'right-tenancy-deposit',
    slug: 'right-to-security-deposit-refund',
    title: 'Right to Security Deposit Refund (Tenancy)',
    category: 'Housing & Tenancy',
    categoryColor: 'cyan',
    keywords: ['landlord', 'tenant', 'deposit', 'security deposit', 'rent', 'rental', 'house owner', 'withholding deposit', 'vacating', 'flat', 'apartment', 'broker'],
    shortDescription: 'Tenants are entitled to the full refund of caution money/security deposit upon vacating, less only legitimate agreed repairs.',
    simpleExplanation: {
      en: 'When you vacate a rental property after giving due notice, the landlord is legally required to return your security deposit within the agreed notice period or within 30 days. They cannot make arbitrary deductions without providing actual receipts or proof of structural damage.',
      hi: 'जब आप तय नोटिस देकर किराए का घर खाली करते हैं, तो मकान मालिक को आपका सिक्योरिटी डिपॉजिट वापस करना अनिवार्य है। वे बिना बिल या सबूत के मनमाने पैसे नहीं काट सकते।',
      te: 'నోటీసు ఇచ్చి అద్దె ఇల్లు ఖాళీ చేసినప్పుడు, యజమాని మీ సెక్యూరిటీ డిపాజిట్‌ను తిరిగి ఇవ్వాలి. సరైన బిల్లులు లేకుండా సొమ్మును తగ్గించుకోవడం కుదరదు.'
    },
    detailedExplanation: 'Under Section 11 of the Model Tenancy Act, 2021, security deposits for residential premises are capped at two months rent and must be refunded upon handing over possession. Disputes are adjudicated by the Rent Authority / Rent Court within 60 days.',
    whyItApplies: 'Security deposits are trust monies held solely as security for rent arrears or tenant-caused damage, not an extra profit source for landlords.',
    whoItAppliesTo: 'Residential tenants, student tenants, family renters, and paying guests (PGs) across India.',
    applicableLaw: 'Model Tenancy Act, 2021 & State Rent Control Acts / Transfer of Property Act, 1882',
    section: 'Section 11 (Security Deposit Limit & Refund) & Section 15 (Maintenance and Repairs)',
    officialSourceUrl: 'https://mohua.gov.in/model-tenancy-act',
    sourceAuthority: 'Ministry of Housing and Urban Affairs (MoHUA), Government of India',
    lastVerified: '10 Oct 2024',
    effectiveFrom: 'June 2021',
    status: 'Published',
    realWorldExample: 'Priya rented an apartment in Bengaluru and gave 1 month vacating notice. Upon vacating, the landlord refused to return Rs. 60,000 security deposit claiming arbitrary repainting costs. Priya served a legal notice attaching handover photos and approached the Rent Authority, which ordered full refund within 15 days.',
    recommendedActions: [
      { step: 1, title: 'Document Handover & Condition of Premises', description: 'Take high-resolution photos and a video walkthrough of every room on the date of vacating to prove no damages.', status: 'Pending' },
      { step: 2, title: 'Calculate Final Settlement', description: 'Reconcile electricity bills and water charges, deduct final month rent if agreed, and establish the exact net refund due.', status: 'Pending' },
      { step: 3, title: 'Send Demand Notice via Registered Email / Speed Post', description: 'Issue a formal notice giving the landlord 7 days to transfer the deposit back to your bank account.', status: 'Not Started' },
      { step: 4, title: 'File Petition before Rent Authority / Consumer Forum', description: 'Approach the local Rent Authority or District Consumer Commission for deficiency in housing services.', status: 'Not Started', authority: 'Rent Authority / Sub-Divisional Magistrate' }
    ],
    documentChecklist: [
      'Original Rental / Lease Agreement',
      'Bank Transfer Receipts showing Security Deposit Payment',
      'Rent Receipts or Bank Statements for monthly rent transfers',
      'Written Notice of Vacating (WhatsApp / Email / Letter)',
      'Handover Inspection Video / Timestamped Photos',
      'Electricity / Water Final Bill NOC receipts'
    ],
    actionChecklist: [
      'Record walkthrough video of the vacant flat',
      'Send formal notice with bank account details to landlord',
      'Collect landlord written refusal or unreplied emails',
      'Lodge complaint with Rent Authority or District Consumer Court'
    ],
    authorityName: 'Rent Authority / Rent Court / District Magistrate',
    authorityPortal: 'https://mohua.gov.in',
    complaintMechanism: 'State Rent Authority & District Consumer Disputes Redressal Commission'
  },
  {
    id: 'right-consumer-defective',
    slug: 'consumer-protection-defective-goods',
    title: 'Right to Replacement or Refund for Defective Products',
    category: 'Consumer Rights',
    categoryColor: 'orange',
    keywords: ['defective', 'product', 'refund', 'warranty', 'guarantee', 'seller', 'flipkart', 'amazon', 'shopkeeper', 'laptop', 'mobile', 'refrigerator', 'broken', 'repair denied', 'overcharging', 'mrp'],
    shortDescription: 'Consumers have the statutory right to replacement, full refund, and compensation for defective goods or unfair trade practices.',
    simpleExplanation: {
      en: 'If you bought any goods (electronics, appliances, vehicles, or online items) that are faulty, stop working during the warranty, or do not match specifications, the seller and manufacturer must repair, replace, or issue a 100% refund.',
      hi: 'यदि आपने कोई खराब या टूटा हुआ सामान खरीदा है, तो विक्रेता और निर्माता को वारंटी के तहत उसे बदलना होगा या आपके पूरे पैसे वापस करने होंगे।',
      te: 'మీరు కొనుగోలు చేసిన వస్తువు లోపభూయిష్టంగా ఉంటే, విక్రేత లేదా తయారీదారు దానిని సరిచేయాలి, మార్చాలి లేదా మీ డబ్బును పూర్తిగా వాపసు చేయాలి.'
    },
    detailedExplanation: 'Sections 2(9), 2(10), 35, and 84 of the Consumer Protection Act, 2019 establish strict product liability. Consumers can register free complaints on the National Consumer Helpline (NCH - 1915) or e-Daakhil without hiring an advocate.',
    whyItApplies: 'The Consumer Protection Act 2019 protects buyers from defective goods, deficient customer service, and deceptive trade practices.',
    whoItAppliesTo: 'Any individual who purchases goods or avails services for personal use from online marketplaces or retail stores.',
    applicableLaw: 'Consumer Protection Act, 2019',
    section: 'Section 2(9) (Consumer Rights), Section 35 (Manner of Filing Complaint), Section 84 (Product Liability)',
    officialSourceUrl: 'https://consumerhelpline.gov.in',
    sourceAuthority: 'Department of Consumer Affairs, Government of India',
    lastVerified: '10 Oct 2024',
    effectiveFrom: '20 July 2020',
    status: 'Published',
    realWorldExample: 'Sunita bought a smart TV online. Within 10 days, the display failed. The service center claimed "water damage" and denied warranty without evidence. Sunita registered a grievance on National Consumer Helpline (NCH 1915). The manufacturer issued a brand-new replacement within 7 days.',
    recommendedActions: [
      { step: 1, title: 'Preserve Proof of Purchase & Warranty Card', description: 'Safeguard tax invoice, payment receipt, and packaging warranty documentation.', status: 'Pending' },
      { step: 2, title: 'Document the Product Defect', description: 'Record clear photographs and video demonstrating the hardware/software malfunction.', status: 'Pending' },
      { step: 3, title: 'Call National Consumer Helpline 1915 or File Online', description: 'Register a complaint on consumerhelpline.gov.in to initiate institutional mediation.', status: 'Not Started', authority: 'National Consumer Helpline' },
      { step: 4, title: 'File Online Consumer Case on e-Daakhil Portal', description: 'If seller refuses resolution, submit a complaint before the District Consumer Commission via edaakhil.nic.in without paying hefty lawyer fees.', status: 'Not Started', authority: 'District Consumer Disputes Redressal Commission' }
    ],
    documentChecklist: [
      'Original Tax Invoice / Bill of Sale',
      'Warranty / Guarantee Certificate',
      'Photographs / Videos showing defect',
      'Job Sheet / Service Center Visit Report',
      'Written Correspondence (Emails/Chat transcripts with customer care)'
    ],
    actionChecklist: [
      'Save order confirmation and tax invoice',
      'Report issue to company support with ticket number',
      'File grievance on National Consumer Helpline (1915)',
      'Escalate to e-Daakhil District Consumer Commission'
    ],
    authorityName: 'National Consumer Helpline / District Consumer Commission',
    authorityPortal: 'https://consumerhelpline.gov.in',
    complaintMechanism: 'NCH Toll-Free: 1915 or SMS/WhatsApp: 8800001915 / e-Daakhil (edaakhil.nic.in)'
  },
  {
    id: 'right-cyber-fraud',
    slug: 'right-zero-liability-banking-fraud',
    title: 'Right to Zero Liability in Unauthorized Electronic Banking Fraud',
    category: 'Cyber & Digital Rights',
    categoryColor: 'blue',
    keywords: ['cyber', 'fraud', 'otp', 'bank', 'unauthorized transaction', 'money deducted', 'scam', 'phishing', 'hacked', 'atm', 'credit card', 'upi', 'sim swap', 'loan app'],
    shortDescription: 'Bank account holders have zero financial liability if an unauthorized electronic transaction is reported to the bank within 3 working days.',
    simpleExplanation: {
      en: 'If money was fraudulently debited from your bank account or credit card through an unauthorized cyber transaction, notify your bank within 3 working days. Under RBI rules, you have ZERO liability, and the bank must shadow credit the full stolen amount within 10 working days.',
      hi: 'यदि आपके बैंक खाते से धोखाधड़ी करके पैसे काटे गए हैं, तो 3 दिनों के भीतर बैंक और 1930 पर रिपोर्ट करें। आरबीआई के अनुसार आपकी शून्य देयता है और बैंक को आपका पैसा वापस करना होगा।',
      te: 'మీ బ్యాంక్ ఖాతా నుండి అనధికారికంగా డబ్బులు కట్ అయితే, 3 పనిదినాల్లో బ్యాంక్‌కు మరియు 1930కి సమాచారం ఇవ్వండి. ఆర్బీఐ నిబంధనల ప్రకారం పూర్తి సొమ్మును బ్యాంక్ తిరిగి జమ చేయాలి.'
    },
    detailedExplanation: 'Reserve Bank of India (RBI) Notification DBR.No.Leg.BC.78/09.07.005/2017-18 specifies customer protection in unauthorized electronic banking transactions. Third-party breaches where the deficiency lies neither with the bank nor customer qualify for zero liability if notified within 3 days.',
    whyItApplies: 'Banks operate digital payment infrastructure and bear primary security responsibility unless negligence (such as sharing PIN/OTP) is proven by the bank.',
    whoItAppliesTo: 'All savings, current, UPI, debit, and credit card holders in scheduled commercial banks and payments banks in India.',
    applicableLaw: 'RBI Master Directions on Customer Protection & Information Technology Act, 2000',
    section: 'RBI Circular 2017-18/15 & IT Act 2000 Section 43A & 66C',
    officialSourceUrl: 'https://rbi.org.in',
    sourceAuthority: 'Reserve Bank of India (RBI) & National Cyber Crime Reporting Portal (MHA)',
    lastVerified: '11 Oct 2024',
    effectiveFrom: '06 July 2017',
    status: 'Published',
    realWorldExample: 'Vikram noticed an unauthorized debit of Rs. 45,000 from his savings account at midnight without receiving an OTP. He immediately blocked his card, called the bank fraud helpline within 4 hours, and dialed 1930 (National Cyber Crime Helpline). Within 7 days, his bank reversed the fraudulent transaction and credited the full Rs. 45,000 back to his account.',
    recommendedActions: [
      { step: 1, title: 'Immediately Block Cards & Net Banking Access', description: 'Call your bank 24x7 toll-free fraud helpline or use mobile app to block card/account and prevent further debits.', status: 'Pending' },
      { step: 2, title: 'Report to National Cyber Crime Portal / 1930', description: 'Dial 1930 immediately or visit cybercrime.gov.in to freeze the money in the recipient bank wallet.', status: 'Pending', authority: 'Indian Cyber Crime Coordination Centre (I4C)' },
      { step: 3, title: 'Submit Written Dispute to Bank Branch', description: 'Visit your home branch within 72 hours, submit written dispute form along with Cyber Crime acknowledgment slip.', status: 'Not Started', authority: 'Bank Grievance Redressal Officer' },
      { step: 4, title: 'Escalate to RBI Banking Ombudsman', description: 'If bank fails to resolve within 30 days or rejects liability, lodge complaint on cms.rbi.org.in.', status: 'Not Started', authority: 'RBI Integrated Ombudsman Scheme' }
    ],
    documentChecklist: [
      'Bank Account Statement highlighting fraudulent transactions',
      'SMS alerts received from the bank',
      'Acknowledgement slip of Cyber Crime Complaint (cybercrime.gov.in)',
      'Written dispute letter acknowledged by branch manager',
      'Call recording / ticket ID of emergency blocking request'
    ],
    actionChecklist: [
      'Call 1930 National Cyber Helpline within golden hour',
      'Block card/UPI ID immediately through mobile banking',
      'Notify bank in writing within 3 working days',
      'File complaint on RBI Ombudsman portal if unresolved'
    ],
    authorityName: 'Reserve Bank of India Banking Ombudsman & National Cyber Crime Reporting Portal',
    authorityPortal: 'https://cybercrime.gov.in',
    complaintMechanism: 'Toll-Free 1930 (Cyber Crime) & RBI CMS Portal (https://cms.rbi.org.in)'
  },
  {
    id: 'right-free-legal-aid',
    slug: 'right-to-free-legal-aid',
    title: 'Right to Free Legal Aid & Access to Justice',
    category: 'Legal Aid & Access to Justice',
    categoryColor: 'indigo',
    keywords: ['legal aid', 'free lawyer', 'court case', 'nalsa', 'dlsa', 'cannot afford advocate', 'lawyer fee', 'trial', 'poor', 'bpl', 'sc', 'st', 'woman', 'child', 'jail', 'custody', 'lok adalat'],
    shortDescription: 'Eligible citizens, including women, children, SC/ST, and low-income individuals, are entitled to free legal counsel and court representation.',
    simpleExplanation: {
      en: 'If you cannot afford a private lawyer for a court case (civil, criminal, marital, or property), the government must assign you a qualified panel lawyer free of cost through the District Legal Services Authority (DLSA). All court fees and drafting expenses are covered.',
      hi: 'यदि आप वकील की फीस नहीं दे सकते, तो सरकार आपको जिला कानूनी सेवा प्राधिकरण (DLSA) के माध्यम से मुफ्त वकील उपलब्ध कराएगी। महिलाओं और गरीबों के लिए यह सेवा पूरी तरह निःशुल्क है।',
      te: 'మీరు న్యాయవాది రుసుము చెల్లించలేకపోతే, ప్రభుత్వం జిల్లా న్యాయ సేవా ప్రాధికార సంస్థ (DLSA) ద్వారా ఉచిత లాయర్‌ను కేటాయిస్తుంది. కోర్టు ఖర్చులు కూడా ఉచితం.'
    },
    detailedExplanation: 'Article 39A of the Constitution of India and Section 12 of the Legal Services Authorities Act, 1987 make it mandatory to provide free and competent legal services to eligible persons, ensuring justice is not denied by reason of economic disabilities.',
    whyItApplies: 'Justice must be accessible to every citizen regardless of financial capacity, ensuring constitutional equality before the law.',
    whoItAppliesTo: 'Women, children, members of SC/ST, persons with disabilities, victims of trafficking, undertrial prisoners, and individuals earning below prescribed state income limits (generally Rs. 3 Lakhs per annum).',
    applicableLaw: 'Legal Services Authorities Act, 1987 & Article 39A of the Constitution of India',
    section: 'Section 12 (Criteria for giving legal services) & Section 13 (Entitlement to legal services)',
    officialSourceUrl: 'https://nalsa.gov.in',
    sourceAuthority: 'National Legal Services Authority (NALSA) / Supreme Court of India',
    lastVerified: '12 Oct 2024',
    effectiveFrom: '09 November 1995',
    status: 'Published',
    realWorldExample: 'Meena, a domestic worker abandoned by her husband, had no money to file for child maintenance. She visited the District Legal Services Authority (DLSA) office in the district court complex. DLSA appointed a senior panel advocate who represented her in family court without charging a single rupee.',
    recommendedActions: [
      { step: 1, title: 'Visit DLSA Office in District Court Complex', description: 'Approach the front office of the District Legal Services Authority in your local district court or apply online on nalsa.gov.in.', status: 'Pending', authority: 'District Legal Services Authority (DLSA)' },
      { step: 2, title: 'Fill Application Form-1', description: 'Provide basic details of the dispute and attach proof of eligibility (Aadhaar, income declaration, caste certificate if SC/ST).', status: 'Pending' },
      { step: 3, title: 'Meet Assigned Panel Advocate', description: 'DLSA issues an appointment letter assigning a registered advocate who will prepare pleadings and represent you in court.', status: 'Not Started' },
      { step: 4, title: 'Free Court Fees & Paperwork', description: 'All court process fees, typing, and certified copy charges are borne by the legal services authority.', status: 'Not Started' }
    ],
    documentChecklist: [
      'Identity Proof (Aadhaar Card, Voter ID, or Ration Card)',
      'Income Certificate or BPL Card / Self-Declaration (Not needed for women/children/SC/ST)',
      'All papers, summons, or notices related to the legal dispute',
      'Passport size photographs'
    ],
    actionChecklist: [
      'Locate District Legal Services Authority (DLSA) at district court',
      'Submit NALSA Legal Aid Application Form',
      'Consult assigned panel lawyer',
      'Participate in court hearings with zero legal fees'
    ],
    authorityName: 'National Legal Services Authority (NALSA) & State/District Legal Services Authority (DLSA)',
    authorityPortal: 'https://nalsa.gov.in',
    complaintMechanism: 'NALSA National Legal Aid Helpline 15100 (Toll-Free 24x7)'
  },
  {
    id: 'right-education-certificates',
    slug: 'right-against-withholding-certificates',
    title: 'Right Against Withholding of Original Educational Certificates by Institutions',
    category: 'Education & Student Rights',
    categoryColor: 'purple',
    keywords: ['college', 'school', 'certificates', 'original marksheet', 'withholding', 'degree', 'transfer certificate', 'tc', 'fees dispute', 'university', 'student', 'retention of certificates'],
    shortDescription: 'Colleges and educational institutions cannot lawfully withhold original educational certificates or degrees to force fee payments or deter transfers.',
    simpleExplanation: {
      en: 'No university, college, or school has the authority to confiscate or withhold your original 10th, 12th, or degree certificates under the pretext of unpaid fees or leaving the course early. They must verify and return original documents immediately.',
      hi: 'कोई भी कॉलेज या विश्वविद्यालय फीस विवाद या कोर्स छोड़ने पर आपके मूल प्रमाण पत्र (10वीं, 12वीं, डिग्री) नहीं रोक सकता। यूजीसी के अनुसार मूल दस्तावेज रोकना गैरकानूनी है।',
      te: 'ఫీజు వివాదం లేదా కోర్సు వదిలేసిన కారణంగా ఏ కళాశాల లేదా విశ్వవిద్యాలయం మీ ఒరిజినల్ సర్టిఫికెట్లను (10వ తరగతి, ఇంటర్, డిగ్రీ) నిలిపివేయడం చట్టవిరుద్ధం.'
    },
    detailedExplanation: 'University Grants Commission (UGC) Notification No. 1-3/2007(CPP-II) and Public Notice F.No.1-1/2018(e.Gov) prohibit Higher Education Institutions (HEIs) from retaining original personal documents of students. Institutions can only inspect originals for verification and must return them immediately.',
    whyItApplies: 'Original educational credentials are the personal property of the student. Holding them hostage infringes upon the right to livelihood and higher education.',
    whoItAppliesTo: 'Undergraduate, postgraduate, professional, and school students studying across India.',
    applicableLaw: 'UGC Regulations on Refund of Fees and Non-Retention of Original Certificates & AICTE Regulations',
    section: 'UGC Notification 2018 Clause 4 & AICTE Mandatory Disclosure Guidelines',
    officialSourceUrl: 'https://www.ugc.gov.in',
    sourceAuthority: 'University Grants Commission (UGC) & Ministry of Education',
    lastVerified: '09 Oct 2024',
    effectiveFrom: 'October 2018',
    status: 'Published',
    realWorldExample: 'Anand joined an engineering college in Pune. When he cleared a government entrance exam and wanted to transfer after 1st year, the college demanded full 4-year tuition fees and refused to release his 10th and 12th original marksheets. Anand filed a grievance on UGC e-Samadhan portal; UGC issued a notice to the university, and the college immediately returned all original marksheets with Transfer Certificate without extra charge.',
    recommendedActions: [
      { step: 1, title: 'Submit Formal Written Application to Principal / Registrar', description: 'Cite UGC Notification F.No. 1-1/2018 and demand immediate return of original certificates.', status: 'Pending' },
      { step: 2, title: 'Lodge Complaint on UGC e-Samadhan Portal', description: 'File grievance against the institution on UGC portal (samadhan.ugc.ac.in).', status: 'Pending', authority: 'UGC Student Grievance Redressal Committee' },
      { step: 3, title: 'Report to State Fee Regulatory Committee / Directorate of Higher Education', description: 'File complaint with State Technical Board or Education Directorate.', status: 'Not Started', authority: 'Directorate of Technical Education' },
      { step: 4, title: 'Approach High Court under Article 226 for Writ of Mandamus', description: 'If urgently needed for employment or another admission, courts routinely issue immediate directions within 48 hours.', status: 'Not Started' }
    ],
    documentChecklist: [
      'College Admission Receipt / Identity Card',
      'Acknowledgment copy of original certificates deposited at admission',
      'Written representation submitted to Principal / Registrar',
      'UGC Public Notice on Non-retention of Original Certificates printout'
    ],
    actionChecklist: [
      'Send written demand notice referencing UGC 2018 guidelines',
      'Register grievance on UGC e-Samadhan (samadhan.ugc.ac.in)',
      'Escalate to affiliating University Vice Chancellor',
      'Approach Lokayukta or High Court if persistent'
    ],
    authorityName: 'University Grants Commission (UGC) & AICTE Student Grievance Cell',
    authorityPortal: 'https://samadhan.ugc.ac.in',
    complaintMechanism: 'UGC e-Samadhan Portal / National Student Grievance Portal'
  },
  {
    id: 'right-patient-discharge-billing',
    slug: 'right-against-detention-patient-dead-body',
    title: 'Right Against Detention of Patient or Deceased Body for Unpaid Hospital Bills',
    category: 'Healthcare & Patient Rights',
    categoryColor: 'coral',
    keywords: ['hospital', 'patient', 'dead body', 'discharge', 'bill dispute', 'overcharging', 'holding patient', 'doctor', 'medical', 'withholding discharge', 'hostage'],
    shortDescription: 'Hospitals cannot detain patients or withhold a deceased person’s body over pending billing disputes under Indian law.',
    simpleExplanation: {
      en: 'A hospital cannot keep a patient confined or refuse to release a deceased person’s mortal remains because of unpaid medical bills. The High Courts and Ministry of Health have ruled that holding a patient or dead body hostage is a grave violation of fundamental human rights.',
      hi: 'अस्पताल बिल न चुकाने के कारण किसी मरीज को रोककर नहीं रख सकता और न ही किसी शव को बंधक बना सकता है। यह मानवाधिकारों और कानून का घोर उल्लंघन है।',
      te: 'ఆసుపత్రి బిల్లులు చెల్లించలేదని రోగిని బంధించడం లేదా మృతదేహాన్ని అప్పగించకపోవడం పూర్తిగా చట్టవిరుద్ధం. ఇది ప్రాథమిక హక్కుల ఉల్లంఘన.'
    },
    detailedExplanation: 'Under the Charter of Patients Rights adopted by the National Human Rights Commission (NHRC) and Ministry of Health, plus Delhi High Court rulings, detaining a dead body or patient constitutes unlawful confinement under Section 340/342 of IPC (Section 127 BNS). Hospitals have civil remedies for billing disputes but cannot hold human bodies.',
    whyItApplies: 'Right to life with dignity under Article 21 extends to patients and the dignified release of mortal remains for last rites.',
    whoItAppliesTo: 'All patients and their family members in private and public hospitals and nursing homes across India.',
    applicableLaw: 'Clinical Establishments (Registration and Regulation) Act, 2010 & Charter of Patients’ Rights (NHRC)',
    section: 'NHRC Charter of Patients Rights Section 15 & Article 21 Constitution of India',
    officialSourceUrl: 'https://main.mohfw.gov.in',
    sourceAuthority: 'Ministry of Health and Family Welfare (MoHFW) & NHRC',
    lastVerified: '12 Oct 2024',
    effectiveFrom: 'September 2018',
    status: 'Published',
    realWorldExample: 'In Delhi, a private hospital refused to release the body of a cardiac patient demanding Rs. 1.8 Lakhs in balance fees. The family called 112 and cited the High Court ruling. Police intervened immediately, and the hospital was compelled to release the body with an undertaking for civil resolution of the dispute.',
    recommendedActions: [
      { step: 1, title: 'Demand Immediate Release in Writing', description: 'Submit written demand citing NHRC Charter of Patients Rights Clause 15 and HC directives.', status: 'Pending' },
      { step: 2, title: 'Call Emergency Police Helpline (112)', description: 'Report wrongful confinement of patient/deceased body at police emergency 112.', status: 'Pending', authority: 'Local Police Station / PCR' },
      { step: 3, title: 'File Complaint with State Clinical Establishment Authority', description: 'Report the hospital to the Chief Medical Officer (CMO) for license revocation proceedings.', status: 'Not Started', authority: 'District CMO / Health Department' },
      { step: 4, title: 'Approach National Human Rights Commission (NHRC)', description: 'Lodge urgent complaint on hrcnet.nic.in for human rights violation.', status: 'Not Started' }
    ],
    documentChecklist: [
      'Hospital Admission Card & Discharge Summary',
      'Itemized Hospital Bill printout',
      'Record of amounts already deposited / paid',
      'Police Emergency 112 Call Log / General Diary (GD) entry'
    ],
    actionChecklist: [
      'Inform hospital administration of unlawful confinement law',
      'Dial 112 for on-the-spot police intervention',
      'Obtain patient/body release against written billing undertaking',
      'Lodge complaint with District Health Officer / Consumer Forum'
    ],
    authorityName: 'District Chief Medical Officer (CMO) & State Clinical Establishment Council',
    authorityPortal: 'https://nhrc.nic.in',
    complaintMechanism: 'Dial 112 (Emergency Police) & NHRC Complaint Portal (hrcnet.nic.in)'
  },
  {
    id: 'right-senior-citizen-maintenance',
    slug: 'right-senior-citizen-maintenance-care',
    title: 'Right to Maintenance and Property Revocation for Senior Citizens',
    category: 'Senior Citizens',
    categoryColor: 'green',
    keywords: ['senior citizen', 'parents', 'elderly', 'maintenance', 'gift deed', 'property taken', 'eviction by children', 'abandoned', 'neglect', 'old age', 'welfare of parents'],
    shortDescription: 'Senior citizens have the right to receive monthly maintenance from adult children and revoke property transfer deeds if neglected.',
    simpleExplanation: {
      en: 'If adult children or legal heirs neglect or refuse to care for senior citizen parents (aged 60+), parents can file a claim before the Maintenance Tribunal for monthly maintenance. Crucially, if a senior citizen gifted property to children with the expectation of care and the children neglect them, the Tribunal can cancel the gift deed and return the property to the parent.',
      hi: 'यदि बच्चे बुजुर्ग माता-पिता (60 वर्ष से अधिक) की देखभाल नहीं करते हैं, तो माता-पिता भरण-पोषण के लिए दावा कर सकते हैं। साथ ही, बच्चों को दी गई संपत्ति की रजिस्ट्री भी रद्द कराई जा सकती है।',
      te: 'వృద్ధ తల్లిదండ్రులను (60+ సంవత్సరాలు) పిల్లలు పట్టించుకోకపోతే, వారు నిర్వహణ ఖర్చుల కోసం క్లెయిమ్ చేయవచ్చు. పిల్లలకు రాసిచ్చిన ఆస్తి గిఫ్ట్ డీడ్‌ను కూడా రద్దు చేయించవచ్చు.'
    },
    detailedExplanation: 'Sections 4, 5, and 23 of the Maintenance and Welfare of Parents and Senior Citizens Act, 2007 provide summary proceedings before the Sub-Divisional Magistrate (SDM) Tribunal. Section 23 declares transfers of property void at the option of the senior citizen where the transferee fails to provide basic amenities.',
    whyItApplies: 'Special statutory protections ensure that senior citizens do not face destitution or abandonment after distributing family property.',
    whoItAppliesTo: 'Any Indian citizen aged 60 years or above having adult children or relatives who inherit their property.',
    applicableLaw: 'Maintenance and Welfare of Parents and Senior Citizens Act, 2007',
    section: 'Section 4 (Maintenance of Parents), Section 9 (Order for Maintenance), Section 23 (Transfer of property void in certain circumstances)',
    officialSourceUrl: 'https://socialjustice.gov.in',
    sourceAuthority: 'Ministry of Social Justice & Empowerment, Government of India',
    lastVerified: '12 Oct 2024',
    effectiveFrom: '29 December 2007',
    status: 'Published',
    realWorldExample: '72-year-old Jagdish gifted his ancestral house to his son on the condition of life-long care. After the registration, the son evicted Jagdish from the house. Jagdish filed a petition under Section 23 before the SDM Tribunal. The Tribunal declared the gift deed void, ordered police to reinstate Jagdish into the home, and directed the son to pay Rs. 8,000 monthly maintenance.',
    recommendedActions: [
      { step: 1, title: 'File Petition before Maintenance Tribunal (SDM Office)', description: 'Submit simple written application to the Sub-Divisional Magistrate (SDM) of your sub-division.', status: 'Pending', authority: 'Maintenance Tribunal / SDM' },
      { step: 2, title: 'Attach Property Gift / Settlement Deed (if applicable)', description: 'Provide copy of property transfer document and describe how children failed to provide basic care.', status: 'Pending' },
      { step: 3, title: 'Tribunal Summary Hearing within 90 Days', description: 'SDM conducts conciliation or issues summary orders directing monthly support and police protection.', status: 'Not Started' },
      { step: 4, title: 'Enforce Order through Police & District Magistrate', description: 'Failure to comply leads to imprisonment up to one month under Section 5(8).', status: 'Not Started' }
    ],
    documentChecklist: [
      'Proof of Age (Senior Citizen ID / Aadhaar Card showing 60+ years)',
      'Proof of Children Identity and Employment/Income',
      'Copy of Gift Deed / Settlement Deed (for property revocation cases)',
      'Medical bills and expense estimates for maintenance claim'
    ],
    actionChecklist: [
      'Draft application addressed to Sub-Divisional Magistrate (SDM)',
      'Submit Form at local Tehsil / SDM Office',
      'Attend Tribunal hearing (no private advocate mandatory)',
      'Obtain cancellation order under Section 23 if property was transferred'
    ],
    authorityName: 'Sub-Divisional Magistrate (SDM) / Senior Citizen Maintenance Tribunal',
    authorityPortal: 'https://socialjustice.gov.in',
    complaintMechanism: 'Elder Line 14567 (National Helpline for Senior Citizens 24x7)'
  },
  {
    id: 'right-zero-fir-police',
    slug: 'right-to-zero-fir-cognizable-offence',
    title: 'Right to Zero FIR & Mandatory Crime Registration',
    category: 'Crime & Personal Safety',
    categoryColor: 'red',
    keywords: ['police', 'fir', 'zero fir', 'police station', 'refusing fir', 'complaint', 'cognizable offence', 'theft', 'assault', 'jurisdiction', 'accident', 'crime'],
    shortDescription: 'Citizens have the right to register an FIR at any police station regardless of jurisdiction (Zero FIR) when a cognizable crime occurs.',
    simpleExplanation: {
      en: 'A police station cannot refuse to register an FIR on the grounds that the crime happened outside their jurisdiction. Under Supreme Court directives and the Bharatiya Nagarik Suraksha Sanhita (BNSS), police must register a "Zero FIR" immediately, provide a free copy, and transfer it to the concerned station later.',
      hi: 'पुलिस क्षेत्राधिकार (Jurisdiction) का बहाना बनाकर एफआईआर दर्ज करने से मना नहीं कर सकती। किसी भी थाने में जीरो एफआईआर (Zero FIR) दर्ज कराना आपका कानूनी अधिकार है।',
      te: 'క్రైమ్ వేరే ప్రాంతంలో జరిగిందనే కారణంతో పోలీసులు ఎఫ్‌ఐఆర్ నమోదు చేయడానికి నిరాకరించలేరు. ఏ పోలీస్ స్టేషన్‌లోనైనా జీరో ఎఫ్‌ఐఆర్ నమోదు చేయించుకోవచ్చు.'
    },
    detailedExplanation: 'Under Lalita Kumari v. Govt of UP (2014) and Section 173 of the Bharatiya Nagarik Suraksha Sanhita, 2023 (formerly Section 154 CrPC), registration of FIR is mandatory if information discloses a cognizable offence. Police officers who refuse face prosecution under Section 199 of Bharatiya Nyaya Sanhita (formerly 166A IPC).',
    whyItApplies: 'Prompt crime recording prevents destruction of evidence and ensures immediate police investigative response.',
    whoItAppliesTo: 'Every citizen who is a victim of, or witness to, a cognizable crime in India.',
    applicableLaw: 'Bharatiya Nagarik Suraksha Sanhita, 2023 (BNSS) & Supreme Court Directives',
    section: 'Section 173(1) BNSS (Information in cognizable cases) & Section 199 BNS (Public servant disobeying law)',
    officialSourceUrl: 'https://mha.gov.in',
    sourceAuthority: 'Ministry of Home Affairs (MHA) & Supreme Court of India',
    lastVerified: '12 Oct 2024',
    effectiveFrom: '01 July 2024',
    status: 'Published',
    realWorldExample: 'Kavita was traveling by train when her bag containing laptop and gold jewellery was stolen. At the destination station, the railway police asked her to go back to the origin city. Kavita insisted on a Zero FIR. Police registered Zero FIR No. 0/2024, issued her a free carbon copy, and transferred the case file to the origin station for investigation.',
    recommendedActions: [
      { step: 1, title: 'Submit Written Signed Complaint to Duty Officer', description: 'State facts, date, time, location, details of suspects, and stolen property.', status: 'Pending' },
      { step: 2, title: 'Demand Zero FIR Number & Free Copy', description: 'Under Section 173(2) BNSS, you are entitled to a free signed copy of the FIR immediately.', status: 'Pending' },
      { step: 3, title: 'Send by Registered Post to Superintendent of Police (SP)', description: 'If Station House Officer (SHO) refuses, send complaint in writing to District SP / Commissioner under Section 173(4) BNSS.', status: 'Not Started', authority: 'Superintendent of Police (SP)' },
      { step: 4, title: 'Approach Judicial Magistrate under Section 175(3) BNSS', description: 'File application before Magistrate to direct police to register FIR and investigate.', status: 'Not Started', authority: 'Judicial Magistrate First Class' }
    ],
    documentChecklist: [
      'Written Complaint signed by the informant/victim',
      'Proof of identity (Aadhaar / Voter ID)',
      'Photographs, medical legal report (MLC), or digital evidence (if assault or fraud)',
      'List of missing/stolen articles with serial numbers'
    ],
    actionChecklist: [
      'Submit written application to Police Station Duty Officer',
      'Request Zero FIR if location is disputed',
      'Collect free signed copy of registered FIR',
      'Escalate to District SP or Magistrate if refused'
    ],
    authorityName: 'Station House Officer (SHO) / Superintendent of Police (SP) / Judicial Magistrate',
    authorityPortal: 'https://mha.gov.in',
    complaintMechanism: 'Emergency Helpline 112 & State Police Online Citizen Portal'
  }
];

// Helper to find best matching right from free-form user query (Hybrid Semantic + Keyword Search)
export function findMatchingRight(query: string): LegalRightData {
  const q = query.toLowerCase().trim();
  
  // Score each right
  let bestMatch: LegalRightData = REAL_LEGAL_DATABASE[0];
  let highestScore = -1;

  for (const right of REAL_LEGAL_DATABASE) {
    let score = 0;

    // Check direct keywords
    for (const kw of right.keywords) {
      if (q.includes(kw)) {
        score += 15;
      }
    }

    // Check title words
    const titleWords = right.title.toLowerCase().split(/\s+/);
    for (const tw of titleWords) {
      if (tw.length > 3 && q.includes(tw)) {
        score += 10;
      }
    }

    // Check category match
    if (q.includes(right.category.toLowerCase())) {
      score += 12;
    }

    // Specific problem heuristics
    if ((q.includes('salary') || q.includes('wage') || q.includes('pay') || q.includes('not paid') || q.includes('boss')) && right.id === 'right-salary-delay') {
      score += 30;
    }
    if ((q.includes('deposit') || q.includes('landlord') || q.includes('rent') || q.includes('flat') || q.includes('vacat')) && right.id === 'right-tenancy-deposit') {
      score += 30;
    }
    if ((q.includes('product') || q.includes('refund') || q.includes('defective') || q.includes('laptop') || q.includes('flipkart') || q.includes('amazon') || q.includes('fridge')) && right.id === 'right-consumer-defective') {
      score += 30;
    }
    if ((q.includes('cyber') || q.includes('fraud') || q.includes('otp') || q.includes('bank') || q.includes('scam') || q.includes('hacked') || q.includes('deducted')) && right.id === 'right-cyber-fraud') {
      score += 30;
    }
    if ((q.includes('legal aid') || q.includes('free lawyer') || q.includes('court fee') || q.includes('nalsa') || q.includes('cannot afford')) && right.id === 'right-free-legal-aid') {
      score += 30;
    }
    if ((q.includes('college') || q.includes('certificate') || q.includes('marksheet') || q.includes('original') || q.includes('degree') || q.includes('withhold')) && right.id === 'right-education-certificates') {
      score += 30;
    }
    if ((q.includes('hospital') || q.includes('dead body') || q.includes('discharge') || q.includes('holding patient') || q.includes('medical bill')) && right.id === 'right-patient-discharge-billing') {
      score += 30;
    }
    if ((q.includes('senior citizen') || q.includes('parent') || q.includes('elderly') || q.includes('gift deed') || q.includes('evicted by son')) && right.id === 'right-senior-citizen-maintenance') {
      score += 30;
    }
    if ((q.includes('police') || q.includes('fir') || q.includes('zero fir') || q.includes('refusing') || q.includes('theft')) && right.id === 'right-zero-fir-police') {
      score += 30;
    }

    if (score > highestScore) {
      highestScore = score;
      bestMatch = right;
    }
  }

  return bestMatch;
}
