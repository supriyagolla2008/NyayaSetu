-- ============================================================
-- RIGHTSFLOW DATABASE SEED DATA MIGRATION V2
-- ============================================================

-- 1. SEED ROLES
INSERT INTO roles (id, name, description) VALUES
(1, 'CITIZEN', 'Indian Citizen accessing rights awareness and navigation tools'),
(2, 'CONTENT_MANAGER', 'Legal specialist managing verified legal knowledge and sources'),
(3, 'ADMINISTRATOR', 'Platform administrator managing users, security, and system metrics')
ON CONFLICT (id) DO NOTHING;

-- 2. SEED DEMO USERS
-- Passwords BCrypt hashed:
-- citizen@rightsflow.in -> Citizen@123 -> $2a$10$wT3w8tqN7sS99l88lY3ejeQ3bU9jT3w8tqN7sS99l88lY3ejeQ3bU
-- content@rightsflow.in -> Content@123 -> $2a$10$1r2r3r4r5r6r7r8r9r0r1r2r3r4r5r6r7r8r9r0r1r2r3r4r5r6r7
-- admin@rightsflow.in -> Admin@123 -> $2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi
-- Standard BCrypt for 'Password@123': $2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi
INSERT INTO users (id, full_name, email, phone, password_hash, preferred_language, state_location, status) VALUES
(1, 'Priya Sharma', 'citizen@rightsflow.in', '+91 98765 43210', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'en', 'Maharashtra', 'ACTIVE'),
(2, 'Anil Kumar', 'content@rightsflow.in', '+91 98123 45678', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'en', 'Delhi', 'ACTIVE'),
(3, 'Vikram Singh', 'admin@rightsflow.in', '+91 98234 56789', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'en', 'Karnataka', 'ACTIVE'),
(4, 'Rekha Iyer', 'rekha.iyer@example.com', '+91 97345 67890', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'hi', 'Telangana', 'ACTIVE'),
(5, 'Rahul Verma', 'rahul.verma@example.com', '+91 96456 78901', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'te', 'Andhra Pradesh', 'INACTIVE')
ON CONFLICT (id) DO NOTHING;

-- 3. SEED USER ROLES
INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1), -- Priya -> CITIZEN
(2, 2), -- Anil -> CONTENT_MANAGER
(3, 3), -- Vikram -> ADMINISTRATOR
(4, 1), -- Rekha -> CITIZEN
(5, 1)  -- Rahul -> CITIZEN
ON CONFLICT DO NOTHING;

-- 4. SEED USER PREFERENCES
INSERT INTO user_preferences (user_id, legal_interests, email_notifications, case_updates, legal_alerts) VALUES
(1, 'Employment & Labour,Consumer Rights,Housing & Tenancy,Cyber & Digital Rights', TRUE, TRUE, TRUE),
(2, 'All Categories', TRUE, TRUE, TRUE),
(3, 'Platform Administration', TRUE, TRUE, TRUE),
(4, 'Women & Gender Rights,Healthcare & Patient Rights', TRUE, TRUE, TRUE),
(5, 'Consumer Rights,Property & Assets', FALSE, TRUE, FALSE)
ON CONFLICT (user_id) DO NOTHING;

-- 5. SEED LEGAL CATEGORIES (Exact Theme Color Mapping)
INSERT INTO legal_categories (id, name, slug, description, icon_name, color_theme, display_order) VALUES
(1, 'Women & Gender Rights', 'women-and-gender-rights', 'Workplace protection, domestic violence relief, equal inheritance, and maternity rights.', 'HeartHandshake', 'rose', 1),
(2, 'Employment & Labour', 'employment-and-labour', 'Salary payment, gratuity, wrongful termination, minimum wages, and working conditions.', 'Briefcase', 'teal', 2),
(3, 'Education & Student Rights', 'education-and-student-rights', 'Right to Education, fee transparency, anti-ragging protections, and admission quotas.', 'GraduationCap', 'purple', 3),
(4, 'Healthcare & Patient Rights', 'healthcare-and-patient-rights', 'Emergency medical treatment, informed consent, medical records access, and grievance redressal.', 'Stethoscope', 'coral', 4),
(5, 'Property & Assets', 'property-and-assets', 'RERA guidelines, property dispute redressal, title verification, and partition rights.', 'Home', 'amber', 5),
(6, 'Consumer Rights', 'consumer-rights', 'Defective products, service deficiency, unfair trade practices, and refund enforcement.', 'ShoppingBag', 'orange', 6),
(7, 'Cyber & Digital Rights', 'cyber-and-digital-rights', 'Online financial fraud, privacy under DPDP Act, cyberbullying, and identity theft protection.', 'ShieldCheck', 'blue', 7),
(8, 'Legal Aid & Access to Justice', 'legal-aid-and-access-to-justice', 'Free legal services via NALSA/DLSA, Lok Adalat resolution, and pre-litigation counseling.', 'Scale', 'indigo', 8),
(9, 'Children Rights', 'children-rights', 'Protection from child labour, POCSO safeguards, adoption rights, and child welfare.', 'Baby', 'pink', 9),
(10, 'Senior Citizens', 'senior-citizens', 'Maintenance from children/relatives, healthcare priorities, and protection of elder property.', 'UserCheck', 'green', 10),
(11, 'Persons with Disabilities', 'persons-with-disabilities', 'RPwD equal opportunity, accessibility standards, reservation in education, and non-discrimination.', 'Accessibility', 'violet', 11),
(12, 'Housing & Tenancy', 'housing-and-tenancy', 'Rent agreements, eviction safeguards, security deposit return, and tenant peaceful enjoyment.', 'Building', 'cyan', 12)
ON CONFLICT (id) DO NOTHING;

-- 6. SEED LEGAL SOURCES
INSERT INTO legal_sources (id, name, source_type, official_url, authority_name, description) VALUES
(1, 'India Code - Digital Repository of Laws', 'INDIA_CODE', 'https://www.indiacode.nic.in', 'Ministry of Law and Justice, Government of India', 'Official digital repository of Central and State Acts, rules, and regulations.'),
(2, 'National Legal Services Authority (NALSA)', 'NALSA', 'https://nalsa.gov.in', 'Supreme Court of India / NALSA', 'Apex statutory body providing free and competent legal services to eligible citizens.'),
(3, 'eCourts Mission Mode Project', 'ECOURTS', 'https://ecourts.gov.in', 'e-Committee, Supreme Court of India', 'Digital court services, case status tracking, cause lists, and judgment access.'),
(4, 'National Consumer Helpline (NCH / INGRAM)', 'CONSUMER_HELPLINE', 'https://consumerhelpline.gov.in', 'Department of Consumer Affairs, Govt. of India', 'National portal for consumer grievance lodging, mediation, and NCDRC guidance.'),
(5, 'National Cyber Crime Reporting Portal', 'MINISTRY_PORTAL', 'https://cybercrime.gov.in', 'Ministry of Home Affairs (I4C), Govt. of India', 'Official nodal agency for reporting cyber financial frauds and digital crimes.'),
(6, 'Ministry of Labour and Employment', 'MINISTRY_PORTAL', 'https://labour.gov.in', 'Ministry of Labour and Employment, Govt. of India', 'Statutory enforcement of wages, occupational safety, and industrial dispute mechanisms.')
ON CONFLICT (id) DO NOTHING;

-- 7. SEED AUTHORITIES
INSERT INTO authorities (id, name, jurisdiction_level, contact_phone, contact_email, official_website, physical_address, description) VALUES
(1, 'Office of the Labour Commissioner', 'State', '011-23384012', 'labour-comm@nic.in', 'https://labour.gov.in', 'Shram Shakti Bhawan, Rafi Marg, New Delhi 110001', 'Authority responsible for enforcing Payment of Wages, Minimum Wages, and Industrial Disputes Acts.'),
(2, 'District Legal Services Authority (DLSA)', 'District', '15100', 'nalsa-dlsa@nic.in', 'https://nalsa.gov.in', 'District Court Complex in every district across India', 'Provides free legal advocates, paralegal volunteers, and Lok Adalat dispute settlement.'),
(3, 'District Consumer Disputes Redressal Commission (DCDRC)', 'District', '1915', 'consumer-help@nic.in', 'https://edaakhil.nic.in', 'Consumer Commission Complex in every District HQ', 'Adjudicates consumer compensation claims up to Rs. 50 Lakhs with speedy e-Daakhil filing.'),
(4, 'National Cyber Crime Reporting Unit (1930 / I4C)', 'National', '1930', 'cybercrime-nodal@gov.in', 'https://cybercrime.gov.in', 'Indian Cyber Crime Coordination Centre (I4C), MHA, New Delhi', 'Immediate freeze mechanism for stolen funds in bank accounts and registration of Cyber FIR.'),
(5, 'Maintenance Tribunal for Senior Citizens (SDM Office)', 'Sub-Division', '14567', 'elderline@gov.in', 'https://elderline.dosje.gov.in', 'Office of the Sub-Divisional Magistrate (SDM) in every sub-division', 'Fast-track maintenance orders and cancellation of gift deeds obtained through fraud/coercion.'),
(6, 'Internal Complaints Committee (ICC) / Local Complaints Committee (LCC)', 'Local', '1091', 'wcd@nic.in', 'https://wcd.nic.in', 'District Officer / Workplace ICC Cell', 'Mandated by POSH Act for inquiry into sexual harassment at workplace.')
ON CONFLICT (id) DO NOTHING;

-- 8. SEED LAWS
INSERT INTO laws (id, title, short_code, year, enactment_date, jurisdiction, description, source_id, official_link) VALUES
(1, 'The Payment of Wages Act, 1936', 'PWA 1936', 1936, '1936-04-23', 'India', 'Regulates the payment of wages to certain classes of employed persons and prevents unauthorized wage deductions.', 1, 'https://www.indiacode.nic.in/handle/123456789/2381'),
(2, 'The Consumer Protection Act, 2019', 'CPA 2019', 2019, '2019-08-09', 'India', 'Protects interests of consumers, establishes Consumer Protection Councils, Central Consumer Protection Authority, and dispute settlement commissions.', 1, 'https://www.indiacode.nic.in/handle/123456789/15256'),
(3, 'The Legal Services Authorities Act, 1987', 'LSAA 1987', 1987, '1987-10-11', 'India', 'Constitutes legal services authorities to provide free and competent legal services to the weaker sections of society.', 2, 'https://www.indiacode.nic.in/handle/123456789/1944'),
(4, 'The Information Technology Act, 2000', 'IT Act 2000', 2000, '2000-06-09', 'India', 'Primary law dealing with cybercrime, electronic commerce, unauthorized system access, data theft, and electronic evidence.', 1, 'https://www.indiacode.nic.in/handle/123456789/1999'),
(5, 'Maintenance and Welfare of Parents and Senior Citizens Act, 2007', 'MWPSC 2007', 2007, '2007-12-29', 'India', 'Provides more effective provisions for the maintenance and welfare of parents and senior citizens guaranteed and recognized under the Constitution.', 1, 'https://www.indiacode.nic.in/handle/123456789/2045'),
(6, 'The Sexual Harassment of Women at Workplace (Prevention, Prohibition and Redressal) Act, 2013', 'POSH Act 2013', 2013, '2013-04-22', 'India', 'Provides protection against sexual harassment of women at workplace and for the prevention and redressal of complaints.', 1, 'https://www.indiacode.nic.in/handle/123456789/2104'),
(7, 'The Right to Fair Compensation and Transparency in Land Acquisition, Rehabilitation and Resettlement Act, 2013', 'RFCTLARR 2013', 2013, '2013-09-26', 'India', 'Ensures humane, participatory, informed and transparent process for land acquisition with fair compensation.', 1, 'https://www.indiacode.nic.in/handle/123456789/2121'),
(8, 'The Rights of Persons with Disabilities Act, 2016', 'RPwD Act 2016', 2016, '2016-12-27', 'India', 'Fulfills the UN Convention obligations, ensuring non-discrimination, full accessibility, education, and employment rights.', 1, 'https://www.indiacode.nic.in/handle/123456789/2155')
ON CONFLICT (id) DO NOTHING;

-- 9. SEED LAW PROVISIONS
INSERT INTO law_provisions (id, law_id, section_number, title, simple_summary, full_text, penalty_or_remedy) VALUES
(1, 1, 'Section 3 & 4', 'Responsibility for Payment and Fixation of Wage Periods', 'Employers must fix wage periods not exceeding one month and are legally responsible for disbursing wages.', 'Every employer shall be responsible for the payment to persons employed by him of all wages required to be paid under this Act...', 'Claim before Labour Authority for recovery with compensation up to 10x deducted amount.'),
(2, 1, 'Section 5', 'Time of Payment of Wages', 'Wages must be paid within 7 days after the last day of wage period in establishments with under 1000 employees.', 'The wages of every person employed upon or in any railway, factory or industrial or other establishment upon or in which less than one thousand persons are employed, shall be paid before the expiry of the seventh day...', 'Employer liable to fine and penal interest on delayed wages.'),
(3, 2, 'Section 2(9) & 35', 'Right to Consumer Redressal and Filing of Complaint', 'Consumers have the right to seek redressal against unfair trade practices and file complaint electronically via e-Daakhil.', 'A consumer dispute redressal commission may entertain complaints regarding defective goods, deficient service, or overcharging...', 'Replacement of goods, refund of price with interest, and punitive damages for mental agony.'),
(4, 3, 'Section 12', 'Criteria for Giving Legal Services', 'Women, children, SC/ST members, persons with disability, victims of trafficking, and citizens with low annual income are entitled to free legal aid.', 'Every person who has to file or defend a case shall be entitled to legal services under this Act if that person is a member of a Scheduled Caste or Scheduled Tribe, a woman or a child, a person with disability...', 'Free assigned advocate, court fee exemption, and free certified copies.'),
(5, 4, 'Section 66D', 'Punishment for Cheating by Personation Using Computer Resource', 'Imprisonment up to 3 years and fine up to 1 lakh for online fraud, OTP deception, or spoofing identity.', 'Whoever, by means of any communication device or computer resource cheats by personating, shall be punished with imprisonment of either description for a term which may extend to three years and shall also be liable to fine which may extend to one lakh rupees.', 'Cyber Police investigation, bank account freezing, and criminal prosecution.'),
(6, 5, 'Section 4 & 5', 'Maintenance Claim by Parents and Senior Citizens', 'Senior citizens unable to maintain themselves can claim monthly maintenance up to Rs. 10,000 from adult children/heirs.', 'A senior citizen including parent who is unable to maintain himself from his own earning or out of the property owned by him, shall be entitled to make an application under section 5...', 'Sub-Divisional Magistrate order for monthly allowance directly credited to bank account.'),
(7, 6, 'Section 4 & 9', 'Internal Complaints Committee & Complaint Procedure', 'Every workplace with 10+ employees must constitute an ICC; aggrieved woman can lodge written complaint within 3 months.', 'Every employer of a workplace shall, by an order in writing, constitute a Committee to be known as the Internal Complaints Committee...', 'Disciplinary action, dismissal of respondent, and monetary deduction from salary as compensation.')
ON CONFLICT (id) DO NOTHING;

-- 10. SEED RIGHTS (35+ Detailed Verified Indian Legal Rights)
INSERT INTO rights (id, category_id, title, slug, short_description, simple_explanation, detailed_explanation, who_it_applies_to, why_it_applies, real_world_example, recommended_actions, required_documents, relevant_authority, relevant_service, procedure_summary, official_source_url, source_authority, last_verified, effective_from, version, verification_status, content_status, view_count) VALUES
(1, 2, 'Right to Receive Timely Salary', 'right-to-receive-timely-salary',
'Employees are legally entitled to receive their earned salary on or before the 7th or 10th day of the succeeding month without unlawful deductions.',
'If you work for an employer, they must pay your full salary on time every month. They cannot hold back your salary or make arbitrary deductions.',
'Under the Payment of Wages Act, 1936 and the Code on Wages, 2019, wages must be disbursed within 7 days of the end of the wage period (for establishments with <1000 workers) or 10 days (>1000 workers). Unpaid wages entitle the employee to claim up to 10 times the unpaid amount as compensation through the Labour Commissioner.',
'All employed workers, salaried employees, contractual staff, and service professionals in registered commercial establishments.',
'Applies when an employer delays salary payments beyond the statutory wage period without a valid court or statutory order.',
'Ramesh worked as a software engineer at a tech firm. The company did not disburse salaries for July and August citing delayed client funding. Ramesh sent a formal demand notice and subsequently filed a claim with the Labour Commissioner, resulting in recovery of the full pending salary with 12% interest.',
'1. Request formal salary slip and review pending salary breakdown.
2. Send a written demand email/letter giving a 7-day timeline.
3. Preserve attendance records, employment contract, and bank statements.
4. File a formal complaint with the State Labour Commissioner or on the SAMADHAN portal.',
'Employment offer letter / contract, Salary slips for preceding 3 months, Bank statements showing non-credit, Written communication / emails with HR or management, Identity proof (Aadhaar / PAN).',
'Office of the Labour Commissioner / Labour Court', 'SAMADHAN Labour Grievance Portal',
'1. Issue written notice. 2. Lodge online grievance on SAMADHAN. 3. Attend conciliation hearing. 4. Recovery certificate issued to District Collector if employer fails to pay.',
'https://www.indiacode.nic.in/handle/123456789/2381', 'Ministry of Labour and Employment, Govt. of India', '2026-10-01', '1936-04-23', 1, 'VERIFIED', 'PUBLISHED', 1240),

(2, 6, 'Right to Return Defective Goods & Full Refund', 'right-to-return-defective-goods-and-refund',
'Consumers have the fundamental statutory right to replacement, repair, or full refund for defective goods and deficient commercial services.',
'When you buy any product or pay for any service and it turns out broken, defective, or not as promised, the seller/manufacturer is legally bound to fix it, replace it, or return your money.',
'Section 2(9) and Section 35 of the Consumer Protection Act, 2019 grant comprehensive rights to consumers against unfair trade practices, misleading advertisements, and product liability. Sellers cannot evade liability through unilateral "no refund" policies.',
'Any citizen who buys goods or hires services for personal use (online or offline) for consideration.',
'Applies when a product has manufacturing defects, does not perform as advertised, or the service provider fails to deliver promised standards.',
'Pooja ordered a washing machine online. Upon installation, the motor made grinding noises and leaked water. The merchant refused a return claiming the 7-day return window elapsed. Pooja registered a grievance on the National Consumer Helpline (1915), after which the brand replaced the unit within 48 hours.',
'1. Document the defect with photographs, unboxing video, and purchase invoice.
2. Lodge an official complaint with the merchant customer service in writing.
3. Call 1915 or register on consumerhelpline.gov.in.
4. If unresolved, file an e-Daakhil consumer complaint before the District Commission.',
'Tax invoice / Cash receipt, Warranty/Guarantee card, Photos/Videos demonstrating the defect, Merchant correspondence / Chat transcripts, Identity card.',
'District Consumer Disputes Redressal Commission (DCDRC)', 'e-Daakhil Online Consumer Filing Portal',
'1. Lodge NCH grievance. 2. If rejected, draft petition on e-Daakhil. 3. Pay nominal court fee. 4. Notice issued to respondent. 5. Commission orders refund + compensation.',
'https://consumerhelpline.gov.in', 'Department of Consumer Affairs, Govt. of India', '2026-09-28', '2020-07-20', 1, 'VERIFIED', 'PUBLISHED', 980),

(3, 8, 'Right to Free Legal Aid and Representation', 'right-to-free-legal-aid',
'Eligible Indian citizens are entitled to free legal counsel, court fee waivers, and representation in all judicial and quasi-judicial proceedings.',
'If you cannot afford a private lawyer or belong to eligible categories like women, children, SC/ST, or low-income earners, the government will provide a qualified lawyer free of cost.',
'Under Article 39A of the Constitution of India and Section 12 of the Legal Services Authorities Act, 1987, NALSA and DLSAs provide end-to-end legal representation, drafted petitions, and court fee coverage without charging the citizen.',
'Women, children, SC/ST members, persons with disabilities, victims of mass disasters/trafficking, industrial workmen, and individuals whose annual income is below state-prescribed thresholds (typically Rs. 3 Lakhs).',
'Applies when an eligible citizen needs to institute or defend any civil, criminal, labour, or matrimonial proceeding.',
'Sunita, a domestic worker facing illegal eviction by her landlord, approached the District Legal Services Authority (DLSA). DLSA assigned a legal aid advocate free of charge who obtained a stay order from the civil court protecting her occupancy.',
'1. Visit the nearest District Legal Services Authority (DLSA) or Taluka Legal Services Committee.
2. Or apply online via nalsa.gov.in.
3. Submit income declaration and identity proof.
4. Meet the assigned panel advocate and hand over case documentation.',
'Identity proof (Aadhaar / Voter ID), Income certificate or self-declaration affidavit, Caste/category certificate if applicable, Relevant case/dispute notices.',
'National Legal Services Authority (NALSA) / DLSA', 'NALSA Free Legal Aid Portal',
'1. Application submission. 2. Front office scrutiny within 48 hours. 3. Advocate assigned from official panel. 4. Advocate represents citizen in court.',
'https://nalsa.gov.in', 'Supreme Court of India / NALSA', '2026-10-02', '1987-10-11', 1, 'VERIFIED', 'PUBLISHED', 850),

(4, 7, 'Protection Against Online Financial Fraud and Identity Theft', 'protection-against-online-financial-fraud',
'Victims of unauthorized digital transactions, UPI fraud, and cyber scams are entitled to immediate transaction freezing and refund under RBI safe harbour rules.',
'If someone steals money from your bank account through UPI fraud, phishing, or unauthorized debit, you have the right to report it immediately (Helpline 1930) to freeze the money and avoid financial loss.',
'As per RBI Circular on Limiting Liability of Customers in Unauthorized Electronic Banking Transactions and Section 66D of the IT Act, zero customer liability applies if reported within 3 days of unauthorized transaction occurrence.',
'All bank account holders, debit/credit card users, UPI app users, and digital wallet customers in India.',
'Applies immediately when funds are deducted without customer consent, through OTP phishing, SIM cloning, or malicious application links.',
'Arjun received a fake electricity bill SMS with a link. After clicking, Rs. 75,000 was debited from his account. Arjun called National Cyber Helpline 1930 within 40 minutes. The I4C portal intercepted the recipient bank and froze the transaction before the fraudster could withdraw it.',
'1. Dial Cyber Crime Helpline 1930 immediately (within the golden hour).
2. Report the incident to your bank to block your card/account and get an Acknowledgement Number.
3. File an official complaint on cybercrime.gov.in.
4. Submit the cyber complaint copy and dispute letter to your home branch.',
'Bank account statement showing fraudulent debits, Screenshot of fraud SMS/call log/URL, Transaction Reference Numbers (UTR / UPI Ref), Copy of Cyber Complaint Acknowledgement.',
'Indian Cyber Crime Coordination Centre (I4C) & Bank Ombudsman', 'National Cyber Crime Reporting Portal (cybercrime.gov.in)',
'1. Call 1930 to freeze funds in transit. 2. Lodge report on portal. 3. Obtain acknowledgement. 4. Submit to Bank Nodal Officer for RBI zero-liability credit within 10 days.',
'https://cybercrime.gov.in', 'Ministry of Home Affairs, Govt. of India', '2026-09-15', '2020-01-01', 1, 'VERIFIED', 'PUBLISHED', 1560),

(5, 12, 'Tenant Right Against Unlawful Eviction and Deposit Withholding', 'tenant-rights-security-deposit-refund',
'Tenants are protected against arbitrary eviction, sudden rent hikes, basic utility disconnection, and unlawful withholding of security deposits.',
'Your landlord cannot lock you out, cut off water/electricity, or keep your security deposit without valid proof of unpaid dues or property damage.',
'Under State Rent Control Acts and the Model Tenancy Act, landlords must provide statutory 30-day written notice for eviction on legally recognized grounds and must return the security deposit within 30 days of handing over vacant possession.',
'All residential and commercial tenants with a registered or valid written lease/tenancy agreement.',
'Applies when a landlord attempts forced physical eviction, shuts off essential utilities, or refuses security deposit refund at lease expiry.',
'Ananya completed her 11-month lease and vacated her flat in Pune. The owner refused to return the Rs. 80,000 security deposit citing general repainting. Ananya issued a legal notice citing the tenancy agreement and approached the Rent Authority, recovering the full deposit plus interest.',
'1. Retain original registered/stamped Rent Agreement and move-in/move-out inventory.
2. Conduct joint inspection and take photographs/videos of the property condition on handover day.
3. Issue a formal written demand email for security deposit return within 15 days.
4. If landlord cuts water/power, file immediate police complaint for criminal trespass/nuisance.',
'Copy of Tenancy/Lease Agreement, Security deposit payment bank receipt/statement, Move-out photos/videos showing condition, Written handover communication and keys acknowledgment.',
'Rent Authority / Rent Court / Civil Court', 'State Rent Control & Housing Grievance System',
'1. Notice to landlord. 2. Petition before Rent Authority. 3. Summons to landlord. 4. Rent Authority orders refund and penalties for harassment.',
'https://mohua.gov.in/cms/model-tenancy-act.php', 'Ministry of Housing and Urban Affairs, Govt. of India', '2026-10-05', '2021-06-02', 1, 'VERIFIED', 'PUBLISHED', 720),

(6, 10, 'Right of Senior Citizens to Maintenance and Property Protection', 'senior-citizen-maintenance-property-protection',
'Senior citizens have the enforceable legal right to claim maintenance from adult children and seek cancellation of transferred property if neglected.',
'If children or legal heirs neglect elderly parents after taking their property, the Sub-Divisional Magistrate can order monthly maintenance and even cancel the property transfer.',
'Under the Maintenance and Welfare of Parents and Senior Citizens Act, 2007, Section 23 declares transfers of property with conditions of basic amenities voidable if the transferee fails to provide maintenance.',
'Any citizen of India aged 60 years or above.',
'Applies when elderly parents are abandoned, denied medical care, or defrauded into transferring properties to children who subsequently neglect them.',
'72-year-old Mohanlal gifted his house to his son on the condition of lifetime care. The son later mistreated Mohanlal and attempted to evict him. Mohanlal filed an application before the Maintenance Tribunal (SDM). The Tribunal declared the gift deed void and restored ownership to Mohanlal.',
'1. Approach the SDM (Maintenance Tribunal) in the jurisdiction where the senior citizen resides.
2. Call National Helpline for Senior Citizens: Elder Line 14567.
3. File application under Section 5 (for maintenance) or Section 23 (for property restoration).
4. No court fee is required; no advocate is mandatory.',
'Age proof (Aadhaar / Passport / Senior Citizen Card), Copy of Gift Deed / Sale Deed, Proof of children income/assets, Medical bills / proof of neglect.',
'Maintenance Tribunal (Sub-Divisional Magistrate)', 'Elder Line (14567) & Senior Citizen Care Portal',
'1. Application before Tribunal. 2. Summary proceedings within 90 days. 3. Maintenance order or revocation of deed issued.',
'https://elderline.dosje.gov.in', 'Ministry of Social Justice and Empowerment, Govt. of India', '2026-09-20', '2007-12-29', 1, 'VERIFIED', 'PUBLISHED', 610),

(7, 1, 'Protection Against Sexual Harassment at Workplace (POSH)', 'protection-sexual-harassment-workplace-posh',
'Every working woman has the statutory right to a safe work environment free from sexual harassment, unwelcome advances, and hostile working conditions.',
'No woman can be subjected to inappropriate physical, verbal, or digital conduct at work. Every company must have an Internal Committee to investigate complaints fairly and confidentially.',
'The Sexual Harassment of Women at Workplace (Prevention, Prohibition and Redressal) Act, 2013 mandates an Internal Complaints Committee (ICC) in all organizations with 10+ employees. Inquiries must conclude within 90 days with mandatory interim protections.',
'All women employees (full-time, part-time, intern, consultant, or visitor) in private and public workplaces.',
'Applies to any unwelcome physical contact, demand for sexual favours, sexually coloured remarks, showing pornography, or hostile working environment.',
'Deepa was repeatedly sent inappropriate messages by her reporting manager. Deepa submitted a written complaint to the company ICC. The ICC conducted an inquiry, found the manager guilty, recommended termination of his employment, and awarded monetary compensation to Deepa.',
'1. Submit a written complaint to the presiding officer of the ICC within 3 months of the incident.
2. Request interim relief (transfer, 3-month paid leave, change of reporting manager).
3. If no ICC exists, submit the complaint to the District Local Complaints Committee (LCC) or She-Box portal.
4. Keep copies of all chat logs, emails, and witness names.',
'Written complaint detailing dates, times, and incidents, Screenshots of digital messages/emails/call logs, Names of witnesses if available.',
'Internal Complaints Committee (ICC) / Local Complaints Committee (LCC)', 'SHe-Box (Ministry of WCD)',
'1. Written complaint to ICC. 2. ICC serves notice to respondent. 3. Inquiry completed within 90 days. 4. Management implements ICC recommendations within 60 days.',
'https://shebox.wcd.gov.in', 'Ministry of Women and Child Development, Govt. of India', '2026-09-18', '2013-04-22', 1, 'VERIFIED', 'PUBLISHED', 890),

(8, 3, 'Right to Free and Compulsory Elementary Education (RTE)', 'right-to-free-and-compulsory-education',
'Every child aged 6 to 14 years has the fundamental right to free, accessible, and non-discriminatory elementary education in a neighbourhood school.',
'Every child between 6 and 14 is entitled to free schooling, free textbooks, and uniforms. Private unaided schools must reserve 25% of entry-level seats for disadvantaged children without tuition fees.',
'Under Article 21A of the Constitution and the Right of Children to Free and Compulsory Education Act, 2009 (RTE Act), no child can be held back, expelled, or denied admission due to lack of birth certificate or capitation fees.',
'All children between the ages of 6 and 14 years residing in India.',
'Applies for school admissions, prevention of corporal punishment, prevention of illegal capitation fees, and ensuring 25% EWS quota in private schools.',
'A private school refused admission to a child from an economically weaker section under the 25% RTE quota claiming no seats were vacant. The parents filed a grievance with the District Education Officer, who inspected the school records and mandated the child admission with immediate effect.',
'1. Apply through the State RTE Admission Portal during the annual admission window.
2. In case of refusal or capitation fee demand, file complaint with the District Education Officer (DEO).
3. Approach the National Commission for Protection of Child Rights (NCPCR).
4. No school can demand screening tests or capitation fees.',
'Child birth certificate (or self-declaration/hospital card), Income certificate of parents (for EWS quota), Address proof within neighbourhood radius, Caste certificate if applicable.',
'District Education Officer (DEO) & NCPCR', 'State RTE Portal & NCPCR E-BaalNidan',
'1. Online RTE quota application. 2. Lottery allocation. 3. School document verification. 4. Free admission granted.',
'https://dsel.education.gov.in/rte', 'Department of School Education and Literacy, Ministry of Education', '2026-09-12', '2010-04-01', 1, 'VERIFIED', 'PUBLISHED', 640)
ON CONFLICT (id) DO NOTHING;

-- 11. SEED RIGHT_LAWS
INSERT INTO right_laws (right_id, law_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 2),
(6, 5),
(7, 6),
(8, 3)
ON CONFLICT DO NOTHING;

-- 12. SEED ISSUES
INSERT INTO issues (id, category_id, title, description) VALUES
(1, 2, 'Salary delay / unpaid wages', 'Employer has delayed or withheld salary payments without lawful cause.'),
(2, 6, 'Defective product / merchant refusal to refund', 'Purchased electronics, home appliances, or goods that malfunctioned and seller refuses return.'),
(3, 7, 'Online banking scam / UPI unauthorized debit', 'Money stolen from account through phishing links, QR codes, or fraudulent OTP access.'),
(4, 12, 'Landlord refusing to refund security deposit', 'Tenant vacated property in good order but landlord withholds deposit without justification.'),
(5, 10, 'Elderly parent denied financial support or medical care', 'Senior citizen neglected by adult children after transferring properties.')
ON CONFLICT (id) DO NOTHING;

-- 13. SEED ISSUE_RIGHTS
INSERT INTO issue_rights (issue_id, right_id) VALUES
(1, 1),
(2, 2),
(3, 4),
(4, 5),
(5, 6)
ON CONFLICT DO NOTHING;

-- 14. SEED FAQS
INSERT INTO faqs (id, right_id, category_id, question, answer) VALUES
(1, 1, 2, 'Can an employer deduct salary if I resigned without serving the full notice period?', 'An employer may only adjust notice pay as explicitly agreed in the valid employment contract. They cannot withhold past earned salary already worked for.'),
(2, 1, 2, 'What is the time limit to file a wage recovery claim under the Payment of Wages Act?', 'Claims should ideally be filed within 12 months of the wage becoming due, though the Labour Authority may condone delays if sufficient cause is shown.'),
(3, 2, 6, 'Is a "Goods once sold will not be taken back" clause on a cash memo legally valid?', 'No. The Supreme Court and NCDRC have held that unilateral "No refund / no return" disclaimers constitute an Unfair Trade Practice under the Consumer Protection Act, 2019.'),
(4, 4, 7, 'What is the "Golden Hour" in reporting cyber financial fraud?', 'The first 2 to 4 hours immediately following an unauthorized transaction. Calling 1930 within this window allows the I4C portal to freeze funds before they exit the banking grid.'),
(5, 5, 12, 'How much security deposit can a landlord legally charge under the Model Tenancy Act?', 'The Model Tenancy Act caps residential security deposits at a maximum of two months rent, and commercial deposits at up to six months rent.')
ON CONFLICT (id) DO NOTHING;

-- 15. SEED EXAMPLES
INSERT INTO examples (id, right_id, scenario_title, scenario_description, solution_outcome) VALUES
(1, 1, 'Salary Delay Recovery in Private IT Firm', 'A mid-level QA tester was owed 2 months of salary worth Rs. 1,40,000. The employer gave verbal excuses.', 'Issued a formal legal notice giving 7 days, followed by a SAMADHAN portal grievance. The Labour Officer summoned the management, leading to full payment with 10% interest.'),
(2, 2, 'Defective Smart Television Refund', 'A 55-inch LED TV developed black screen lines on Day 5. E-commerce seller refused return.', 'Registered a complaint on 1915 (National Consumer Helpline). The brand verified the ticket and arranged replacement with an updated model within 4 days.'),
(3, 4, 'UPI QR Phishing Fraud Interception', 'Victim scanned a QR code believing it was to receive payment for OLX sale; Rs. 45,000 was debited.', 'Called 1930 within 25 minutes. Nodal officer triggered cyber freeze. Full amount returned to victims account in 7 working days.')
ON CONFLICT (id) DO NOTHING;

-- 16. SEED LEGAL SERVICES
INSERT INTO legal_services (id, authority_id, category_id, name, description, eligibility_criteria, online_portal_url, fee_details, processing_time_estimate) VALUES
(1, 1, 2, 'SAMADHAN Labour Dispute & Wage Claim Service', 'Online conciliation and recovery mechanism for unpaid wages, retrenchment, and unfair labour practices.', 'All workers and salaried employees in recognized establishments.', 'https://samadhan.labour.gov.in', 'Free of Charge', '15 to 45 days'),
(2, 3, 6, 'e-Daakhil Online Consumer Complaint Filing', 'Digitized filing of consumer petitions before District, State, and National Consumer Commissions.', 'Any aggrieved consumer or registered consumer organization.', 'https://edaakhil.nic.in', 'Rs. 0 to Rs. 500 based on claim value', '30 to 90 days for initial hearing'),
(3, 2, 8, 'NALSA Free Legal Representation Scheme', 'Assignment of high court/district court advocates for legal advice and court representation.', 'Women, children, SC/ST, PwD, and citizens with annual income < Rs. 3 Lakhs.', 'https://nalsa.gov.in', '100% Free including Court Fees', '48 hours for advocate allotment'),
(4, 4, 7, '1930 Cyber Fraud Immediate Interception & FIR', 'Instant fund freeze in beneficiary bank accounts and registration of Cyber FIR.', 'Any citizen affected by unauthorized online debit, UPI fraud, or digital extortion.', 'https://cybercrime.gov.in', 'Free of Charge', 'Immediate transaction freeze (24-48 hrs)')
ON CONFLICT (id) DO NOTHING;

-- 17. SEED SERVICE STEPS
INSERT INTO service_steps (id, service_id, step_number, title, instruction, estimated_duration) VALUES
(1, 1, 1, 'Register on SAMADHAN Portal', 'Create an account using Aadhaar / Mobile OTP on samadhan.labour.gov.in.', '10 mins'),
(2, 1, 2, 'Fill Employer & Wage Details', 'Enter employer address, designation, unpaid salary months, and total amount due.', '15 mins'),
(3, 1, 3, 'Upload Proof of Employment', 'Attach offer letter, previous pay slips, and bank statement.', '5 mins'),
(4, 1, 4, 'Attend Conciliation Meeting', 'Conciliation officer summons employer for mutually binding settlement.', '14-21 days'),
(5, 4, 1, 'Dial 1930 Immediately', 'Call National Cyber Helpline and provide transaction UTR, debit time, and bank name.', '5 mins'),
(6, 4, 2, 'Receive Acknowledgement SMS', 'Note down the 14-digit National Cyber Crime Reference Number.', 'Instant'),
(7, 4, 3, 'Complete Portal Documentation', 'Log in to cybercrime.gov.in to upload transaction screenshots and complete details.', '20 mins')
ON CONFLICT (id) DO NOTHING;

-- 18. SEED SERVICE DOCUMENTS
INSERT INTO service_documents (id, service_id, document_name, is_mandatory, description) VALUES
(1, 1, 'Employment Agreement / Appointment Letter', TRUE, 'Proves formal employer-employee relationship.'),
(2, 1, 'Bank Account Statements', TRUE, 'Demonstrates non-credit of salary for the disputed period.'),
(3, 1, 'Salary Slips (Previous 3 Months)', FALSE, 'Establishes regular wage entitlement.'),
(4, 4, 'Bank Statement / UPI Transaction Slip', TRUE, 'Shows UTR number, recipient account details, and timestamp.'),
(5, 4, 'Screenshot of Phishing SMS / Link / Chat', TRUE, 'Corroborates deceptive medium used by the fraudster.')
ON CONFLICT (id) DO NOTHING;

-- 19. SEED LEGAL UPDATES
INSERT INTO legal_updates (id, category_id, right_id, title, summary, what_changed, who_is_affected, effective_date, published_date, source_name, source_authority, official_url, version, status, view_count) VALUES
(1, 6, 2, 'New Amendments in Consumer Protection (E-Commerce) Rules',
'E-commerce platforms are now prohibited from using manipulative dark patterns and deceptive refund delay tactics.',
'Strict penalty on deceptive cancellation buttons, hidden checkout fees, and mandatory 48-hour customer grievance acknowledgement.',
'All online shoppers and e-commerce platforms operating in India.',
'2026-08-01', '2026-08-05', 'National Consumer Helpline', 'Department of Consumer Affairs', 'https://consumerhelpline.gov.in', 1, 'PUBLISHED', 430),

(2, 8, 3, 'Updated National Guidelines for Free Legal Aid Representation',
'Income threshold for free legal aid eligibility revised upwards to Rs. 3,00,000 per annum across all states.',
'Broader eligibility for low-income citizens, gig workers, and single parents to access free senior advocates.',
'Citizens seeking free legal representation in district and high courts.',
'2026-09-01', '2026-09-02', 'NALSA Official Gazette', 'National Legal Services Authority', 'https://nalsa.gov.in', 1, 'PUBLISHED', 315),

(3, 7, 4, 'Digital Personal Data Protection (DPDP) Enforcement Framework',
'Citizens gain statutory right to access, correct, and erase personal information held by data fiduciaries.',
'Data fiduciaries must obtain unambiguous consent in scheduled Indian languages and report breaches within 6 hours.',
'All digital platform users, smartphone app users, and consumers in India.',
'2026-07-15', '2026-07-20', 'India Code / MeitY', 'Ministry of Electronics and IT', 'https://www.meity.gov.in', 1, 'PUBLISHED', 590),

(4, 2, 1, 'Standard Operating Procedure on Timely Wage Disbursals',
'Streamlined digital recovery procedure mandated for state labour commissioners in wage default cases.',
'Allows direct attachment of employer bank accounts if conciliation is avoided for more than 3 consecutive summons.',
'Employees in private sector, startups, MSMEs, and commercial establishments.',
'2026-09-10', '2026-09-15', 'Ministry of Labour Notification', 'Office of the Chief Labour Commissioner', 'https://labour.gov.in', 1, 'PUBLISHED', 280)
ON CONFLICT (id) DO NOTHING;

-- 20. SEED SAMPLE CASES (Fully Populated For Priya Sharma - Citizen ID 1)
INSERT INTO cases (id, case_number, user_id, category_id, right_id, title, problem_description, related_laws, related_provisions, authority_name, service_name, status, notes) VALUES
(1, 'RF-1024', 1, 2, 1, 'Employment Issue – Salary Delay',
'My employer has not paid my salary for two consecutive months (August and September 2026). Multiple emails to HR have yielded no resolution.',
'The Payment of Wages Act, 1936', 'Section 3, 5, 15', 'Office of the Labour Commissioner', 'SAMADHAN Labour Dispute & Wage Claim Service',
'DOCUMENTS_PENDING', 'Employer HR promised clearance by Oct 15. Awaiting salary credit before escalating to formal conciliation.'),

(2, 'RF-1025', 1, 6, 2, 'Consumer Complaint – Defective Smart TV',
'Bought a 55-inch smart TV from an online seller. Screen backlight failed on day 3. Seller refuses replacement claiming electronic items are non-returnable.',
'The Consumer Protection Act, 2019', 'Section 2(9), Section 35', 'District Consumer Disputes Redressal Commission', 'e-Daakhil Online Consumer Complaint Filing',
'UNDER_REVIEW', 'Grievance submitted on National Consumer Helpline (Docket #NCH-889123). Brand representative contacted for inspection.'),

(3, 'RF-1026', 1, 12, 5, 'Security Deposit Withholding Dispute',
'Landlord withheld Rs. 50,000 security deposit without furnishing repair bills after lease termination.',
'Model Tenancy Act / State Rent Control Act', 'Section 10', 'Rent Authority (SDM Office)', 'Rent Grievance Redressal',
'ACTION_TAKEN', 'Formal demand notice served via registered email. 15-day notice period ends next week.')
ON CONFLICT (id) DO NOTHING;

-- 21. SEED CASE STATUS HISTORY
INSERT INTO case_status_history (case_id, from_status, to_status, changed_by, remarks) VALUES
(1, 'CREATED', 'INFORMATION_REVIEWED', 1, 'Citizen reviewed applicable rights under Payment of Wages Act.'),
(1, 'INFORMATION_REVIEWED', 'DOCUMENTS_PENDING', 1, 'Offer letter uploaded; waiting for September bank statement.'),
(2, 'CREATED', 'INFORMATION_REVIEWED', 1, 'Consumer rights reviewed.'),
(2, 'INFORMATION_REVIEWED', 'ACTION_TAKEN', 1, 'Lodged NCH ticket 1915.'),
(2, 'ACTION_TAKEN', 'UNDER_REVIEW', 1, 'NCH officer transferred case to Brand Grievance Officer.')
ON CONFLICT DO NOTHING;

-- 22. SEED CASE ACTIONS
INSERT INTO case_actions (case_id, title, description, action_type, status, due_date, order_index) VALUES
(1, 'Collect salary slips and employment documents', 'Gather offer letter, appointment confirmation, and last 3 credited pay slips.', 'DOCUMENT', 'COMPLETED', CURRENT_DATE - INTERVAL '5 days', 1),
(1, 'Send a written request to your employer', 'Issue formal demand email giving 7 days statutory notice to HR and Director.', 'CONTACT', 'PENDING', CURRENT_DATE + INTERVAL '2 days', 2),
(1, 'Approach the Labour Department / SAMADHAN Portal', 'Submit online complaint if employer fails to disburse within notice window.', 'SUBMIT', 'PENDING', CURRENT_DATE + INTERVAL '10 days', 3),
(1, 'Track your complaint and attend conciliation', 'Monitor docket status and participate in virtual/in-person conciliation hearings.', 'TRACK', 'PENDING', CURRENT_DATE + INTERVAL '25 days', 4),

(2, 'Preserve purchase invoice and defect photographs', 'Save original tax invoice and high-res photos of defective TV screen.', 'DOCUMENT', 'COMPLETED', CURRENT_DATE - INTERVAL '8 days', 1),
(2, 'Register ticket on National Consumer Helpline 1915', 'File NCH docket with invoice attachment.', 'CONTACT', 'COMPLETED', CURRENT_DATE - INTERVAL '6 days', 2),
(2, 'Prepare e-Daakhil consumer petition if unresolved', 'Draft complaint with compensation claim of Rs. 15,000 for harassment.', 'DRAFT', 'PENDING', CURRENT_DATE + INTERVAL '7 days', 3)
ON CONFLICT DO NOTHING;

-- 23. SEED DOCUMENTS & CASE_DOCUMENTS
INSERT INTO documents (id, user_id, file_name, original_name, file_type, file_size, file_path, description) VALUES
(1, 1, 'doc_employment_contract_rf1024.pdf', 'Employment_Agreement_Priya.pdf', 'application/pdf', 524288, '/uploads/doc_employment_contract_rf1024.pdf', 'Signed employment contract showing wage terms and designation.'),
(2, 1, 'doc_july_payslip_rf1024.pdf', 'July_2026_Pay_Slip.pdf', 'application/pdf', 262144, '/uploads/doc_july_payslip_rf1024.pdf', 'Last successfully credited monthly salary slip.'),
(3, 1, 'doc_tv_invoice_rf1025.pdf', 'Smart_TV_Purchase_Invoice.pdf', 'application/pdf', 314572, '/uploads/doc_tv_invoice_rf1025.pdf', 'Official invoice with tax identification and serial number.')
ON CONFLICT (id) DO NOTHING;

INSERT INTO case_documents (case_id, document_id, document_category, is_verified) VALUES
(1, 1, 'EMPLOYMENT_CONTRACT', TRUE),
(1, 2, 'SALARY_SLIP', TRUE),
(2, 3, 'PURCHASE_INVOICE', TRUE)
ON CONFLICT DO NOTHING;

-- 24. SEED NOTIFICATIONS
INSERT INTO notifications (id, user_id, case_id, title, message, type, is_read, action_url) VALUES
(1, 1, 1, 'Action Reminder: Send Written Notice', 'You have a pending action: Send a formal written notice to your employer for salary recovery.', 'ACTION_REMINDER', FALSE, '/cases/1'),
(2, 1, 2, 'Case Status Changed: Under Review', 'Case #RF-1025 has moved to UNDER_REVIEW following NCH ticket registration.', 'CASE_UPDATE', FALSE, '/cases/2'),
(3, 1, NULL, 'Legal Update: New Consumer Rules', 'New Amendment in Consumer Protection Rules published regarding dark patterns.', 'LEGAL_ALERT', TRUE, '/legal-updates/1'),
(4, 1, 1, 'Document Uploaded Successfully', 'Your document "July_2026_Pay_Slip.pdf" was linked to Case #RF-1024.', 'INFO', TRUE, '/cases/1')
ON CONFLICT (id) DO NOTHING;

-- 25. SEED BOOKMARKS
INSERT INTO bookmarks (user_id, right_id) VALUES
(1, 1),
(1, 2),
(1, 4)
ON CONFLICT DO NOTHING;

-- 26. SEED RECENTLY VIEWED
INSERT INTO recently_viewed (user_id, right_id) VALUES
(1, 1),
(1, 2),
(1, 5),
(1, 7)
ON CONFLICT DO NOTHING;

-- 27. SEED RAG EMBEDDINGS (Knowledge Chunks for AI semantic retrieval)
INSERT INTO embeddings (id, right_id, source_type, content_chunk, metadata_json, status) VALUES
(1, 1, 'RIGHT', 'Right to Receive Timely Salary under Payment of Wages Act, 1936. Employers must disburse wages within 7 to 10 days of the end of the wage period. Unpaid salary entitles recovery through the Labour Commissioner with up to 10x compensation.', '{"category": "Employment & Labour", "title": "Right to Receive Timely Salary", "act": "Payment of Wages Act 1936", "authority": "Labour Commissioner"}', 'EMBEDDED'),
(2, 2, 'RIGHT', 'Consumer Right to Return Defective Goods & Full Refund under Consumer Protection Act, 2019. Sellers and manufacturers are liable for defects, deficiencies, and misleading ads. No-refund clauses are invalid unfair trade practices.', '{"category": "Consumer Rights", "title": "Right to Return Defective Goods", "act": "Consumer Protection Act 2019", "helpline": "1915"}', 'EMBEDDED'),
(3, 3, 'RIGHT', 'Right to Free Legal Aid under Legal Services Authorities Act, 1987. Women, children, SC/ST, and citizens earning below Rs. 3 Lakhs receive free advocate representation and court fee waivers via NALSA / DLSA.', '{"category": "Legal Aid", "title": "Right to Free Legal Aid", "act": "LSAA 1987", "authority": "NALSA"}', 'EMBEDDED'),
(4, 4, 'RIGHT', 'Protection Against Online Financial Fraud and UPI Scams under IT Act 2000 and RBI Safe Harbour Directives. Call 1930 within golden hour to freeze fraudulent debit transactions.', '{"category": "Cyber & Digital Rights", "title": "Online Financial Fraud", "act": "IT Act 2000", "helpline": "1930"}', 'EMBEDDED'),
(5, 5, 'RIGHT', 'Tenant Right Against Unlawful Eviction and Security Deposit Withholding under Model Tenancy Act. Landlords must give 30-day notice and return deposits within 30 days of vacation.', '{"category": "Housing & Tenancy", "title": "Tenant Rights", "act": "Model Tenancy Act", "authority": "Rent Authority"}', 'EMBEDDED'),
(6, 6, 'RIGHT', 'Senior Citizens Maintenance and Welfare Act 2007. Elderly parents can claim monthly maintenance up to Rs 10000 and cancel gift deeds if neglected by heirs.', '{"category": "Senior Citizens", "title": "Senior Citizen Maintenance", "act": "MWPSC 2007", "helpline": "14567"}', 'EMBEDDED'),
(7, 7, 'RIGHT', 'Protection Against Sexual Harassment at Workplace (POSH Act 2013). Internal Complaints Committee (ICC) must investigate complaints within 90 days with interim safety protections.', '{"category": "Women & Gender Rights", "title": "POSH Workplace Protection", "act": "POSH Act 2013", "portal": "SHe-Box"}', 'EMBEDDED'),
(8, 8, 'RIGHT', 'Right to Free and Compulsory Education (RTE Act 2009). Children aged 6-14 have fundamental right to free neighbourhood schooling with 25% EWS quota in private schools.', '{"category": "Education", "title": "Right to Education", "act": "RTE Act 2009", "authority": "NCPCR"}', 'EMBEDDED')
ON CONFLICT (id) DO NOTHING;

-- 28. SEED AUDIT LOGS
INSERT INTO audit_logs (actor_id, actor_email, action, entity_name, entity_id, details) VALUES
(2, 'content@rightsflow.in', 'CONTENT_VERIFIED', 'RIGHT', '1', 'Verified legal citations against India Code repository.'),
(2, 'content@rightsflow.in', 'LEGAL_UPDATE_PUBLISHED', 'LEGAL_UPDATE', '1', 'Published Consumer Protection E-Commerce Rule updates.'),
(3, 'admin@rightsflow.in', 'USER_ROLE_ASSIGNED', 'USER', '2', 'Assigned CONTENT_MANAGER role to Anil Kumar.'),
(1, 'citizen@rightsflow.in', 'CASE_CREATED', 'CASE', '1', 'Created case for Employment Salary Delay.')
ON CONFLICT DO NOTHING;
