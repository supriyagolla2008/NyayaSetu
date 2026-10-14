# Python script to generate comprehensive V7__seed_full_14_categories_legal_data.sql
import os

sql_file = "backend/src/main/resources/db/migration/V7__seed_full_14_categories_legal_data.sql"

migration_sql = """-- ============================================================
-- NYAYASETU (न्यायसेतु) DATABASE SEED MIGRATION V7
-- Comprehensive 14 Indian Legal Rights Categories & Verified Knowledge
-- Grounded in India Code, NALSA, eCourts, NCH, and Supreme Court Precedents
-- ============================================================

-- 1. ENSURE ALL 14 LEGAL CATEGORIES EXIST
INSERT INTO legal_categories (id, name, slug, description, icon_name, color_theme, display_order) VALUES
(1, 'Women & Gender Rights', 'women-and-gender-rights', 'Workplace protection, domestic violence relief, equal inheritance, and maternity rights.', 'HeartHandshake', 'rose', 1),
(2, 'Employment & Labour', 'employment-and-labour', 'Salary payment, gratuity, wrongful termination, minimum wages, and working conditions.', 'Briefcase', 'teal', 2),
(3, 'Education & Student Rights', 'education-and-student-rights', 'Right to Education, fee transparency, anti-ragging protections, and admission quotas.', 'GraduationCap', 'purple', 3),
(4, 'Healthcare & Patient Rights', 'healthcare-and-patient-rights', 'Emergency medical treatment, informed consent, medical records access, and grievance redressal.', 'Stethoscope', 'coral', 4),
(5, 'Property, Tenancy & Assets', 'property-tenancy-and-assets', 'RERA guidelines, tenancy security deposits, title verification, and partition rights.', 'Home', 'amber', 5),
(6, 'Family & Personal Matters', 'family-and-personal-matters', 'Maintenance, child custody, mutual divorce, domestic protection, and succession.', 'Users', 'indigo', 6),
(7, 'Consumer Rights', 'consumer-rights', 'Defective products, service deficiency, unfair trade practices, and refund enforcement.', 'ShoppingBag', 'orange', 7),
(8, 'Cybercrime & Digital Rights', 'cybercrime-and-digital-rights', 'Online financial fraud, privacy under DPDP Act, cyberbullying, and identity theft protection.', 'ShieldCheck', 'blue', 8),
(9, 'Government Services & Citizen Rights', 'government-services-and-citizen-rights', 'Right to Information, citizen charters, Aadhaar corrections, and grievance portals.', 'Landmark', 'emerald', 9),
(10, 'Legal Aid & Access to Justice', 'legal-aid-and-access-to-justice', 'Free legal services via NALSA/DLSA, Lok Adalat resolution, and pre-litigation counseling.', 'Scale', 'indigo', 10),
(11, 'Senior Citizens', 'senior-citizens', 'Maintenance from children/relatives, healthcare priorities, and protection of elder property.', 'UserCheck', 'green', 11),
(12, 'Children & Child Protection', 'children-and-child-protection', 'Protection from child labour, POCSO safeguards, adoption rights, and child welfare.', 'Baby', 'pink', 12),
(13, 'Persons with Disabilities', 'persons-with-disabilities', 'RPwD equal opportunity, accessibility standards, reservation in education, and non-discrimination.', 'Accessibility', 'violet', 13),
(14, 'Crime, Personal Safety & Victim Support', 'crime-personal-safety-and-victim-support', 'Zero FIR registration, arrest safeguards, bail rights, and victim compensation.', 'ShieldAlert', 'red', 14)
ON CONFLICT (id) DO UPDATE SET
  name = EXCLUDED.name,
  slug = EXCLUDED.slug,
  description = EXCLUDED.description,
  icon_name = EXCLUDED.icon_name,
  color_theme = EXCLUDED.color_theme,
  display_order = EXCLUDED.display_order;

-- 2. SEED EXPANDED OFFICIAL LEGAL SOURCES
INSERT INTO legal_sources (id, name, source_type, official_url, authority_name, description) VALUES
(7, 'e-Daakhil Consumer Grievance System', 'CONSUMER_HELPLINE', 'https://edaakhil.nic.in', 'National Consumer Disputes Redressal Commission (NCDRC)', 'Online electronic filing portal for consumer commissions across India.'),
(8, 'Right to Information Online Portal', 'MINISTRY_PORTAL', 'https://rtionline.gov.in', 'Department of Personnel and Training (DoPT), Govt. of India', 'Centralized portal for submitting RTI requests and first appeals.'),
(9, 'CPGRAMS Centralized Public Grievance Redressal', 'MINISTRY_PORTAL', 'https://pgportal.gov.in', 'Department of Administrative Reforms & Public Grievances (DARPG)', 'Apex citizen grievance lodging and monitoring portal for Central and State ministries.'),
(10, 'SAMADHAN Labour Dispute Portal', 'MINISTRY_PORTAL', 'https://samadhan.labour.gov.in', 'Ministry of Labour and Employment, Govt. of India', 'Online dispute resolution for industrial and commercial workers.'),
(11, 'PENCiL Portal for Child Labour Elimination', 'MINISTRY_PORTAL', 'https://pencil.gov.in', 'Ministry of Labour & Employment, Govt. of India', 'Platform for effective enforcement for no child labour.'),
(12, 'National Legal Services Authority Legal Aid Portal', 'NALSA', 'https://nalsa.gov.in', 'Supreme Court of India / NALSA', 'Online application portal for free legal counsel and Lok Adalat matters.')
ON CONFLICT (id) DO NOTHING;

-- 3. SEED ADDITIONAL STATUTORY LAWS
INSERT INTO laws (id, title, short_code, year, enactment_date, jurisdiction, description, source_id, official_link) VALUES
(9, 'The Right to Information Act, 2005', 'RTI Act 2005', 2005, '2005-06-15', 'India', 'Provides for setting out the practical regime of right to information for citizens to secure access to information under public authorities.', 8, 'https://www.indiacode.nic.in/handle/123456789/2065'),
(10, 'The Protection of Children from Sexual Offences Act, 2012', 'POCSO Act 2012', 2012, '2012-06-19', 'India', 'Special law to protect children from offences of sexual assault, sexual harassment and pornography.', 1, 'https://www.indiacode.nic.in/handle/123456789/2079'),
(11, 'The Code of Criminal Procedure, 1973', 'CrPC 1973', 1974, '1974-01-25', 'India', 'Consolidates the law relating to criminal procedure, powers of police, arrest procedures, bail, and victim compensation.', 1, 'https://www.indiacode.nic.in/handle/123456789/16225'),
(12, 'The Digital Personal Data Protection Act, 2023', 'DPDP Act 2023', 2023, '2023-08-11', 'India', 'Regulates processing of digital personal data respecting right of individuals to protect data and lawful purposes.', 1, 'https://www.indiacode.nic.in/handle/123456789/19445'),
(13, 'The Real Estate (Regulation and Development) Act, 2016', 'RERA 2016', 2016, '2016-03-25', 'India', 'Regulates and promotes real estate sector, ensuring sale of plots/apartments in transparent manner and protecting buyers.', 1, 'https://www.indiacode.nic.in/handle/123456789/2158'),
(14, 'The Protection of Women from Domestic Violence Act, 2005', 'PWDVA 2005', 2005, '2005-09-13', 'India', 'Provides more effective protection of the rights of women guaranteed under the Constitution who are victims of violence within domestic relationship.', 1, 'https://www.indiacode.nic.in/handle/123456789/2021'),
(15, 'The Maternity Benefit Act, 1961', 'MBA 1961', 1961, '1961-12-12', 'India', 'Regulates employment of women in certain establishments for certain periods before and after child-birth and provides maternity benefit.', 1, 'https://www.indiacode.nic.in/handle/123456789/1681'),
(16, 'The Payment of Gratuity Act, 1972', 'PGA 1972', 1972, '1972-08-21', 'India', 'Provides for a scheme for payment of gratuity to employees engaged in factories, mines, oilfields, shops or other establishments.', 1, 'https://www.indiacode.nic.in/handle/123456789/1647')
ON CONFLICT (id) DO NOTHING;

-- 4. SEED LAW PROVISIONS
INSERT INTO law_provisions (id, law_id, section_number, title, simple_summary, full_text, penalty_or_remedy) VALUES
(8, 9, 'Section 6 & 7', 'Request for Obtaining Information & Disposal of Request', 'Citizens can submit written or online RTI requests; Public Information Officer must provide information within 30 days or 48 hours for life and liberty.', 'A person, who desires to obtain any information under this Act, shall make a request in writing or through electronic means...', 'Rs. 250 per day up to Rs. 25,000 penalty on PIO for unjustified refusal or delay.'),
(9, 10, 'Section 19 & 21', 'Reporting of Offences and Punishment for Failure to Report', 'Mandatory duty on any person having knowledge of child abuse to report immediately to Special Juvenile Police Unit or local police.', 'Notwithstanding anything contained in the Code of Criminal Procedure, any person who has apprehension that an offence under this Act has been committed shall provide such information...', 'Imprisonment up to 6 months or fine for deliberate failure to report.'),
(10, 11, 'Section 154 & 157', 'Information in Cognizable Cases & Procedure for Investigation', 'Police must register an FIR for cognizable offences immediately without jurisdictional excuses (Zero FIR doctrine).', 'Every information relating to the commission of a cognizable offence, if given orally to an officer in charge of a police station, shall be reduced to writing...', 'Disciplinary action and criminal prosecution under Section 166A IPC for refusing FIR.'),
(11, 12, 'Section 12 & 13', 'Right to Grievance Redressal and Erasure of Personal Data', 'Data Principals have statutory rights to obtain summary of personal data, correct inaccuracies, and seek complete erasure.', 'A Data Principal shall have the right to obtain from the Data Fiduciary confirmation whether personal data is being processed...', 'Data Protection Board inquiry and substantial civil penalties on non-compliant Data Fiduciaries.'),
(12, 13, 'Section 18', 'Return of Amount and Compensation for Delay', 'Homebuyer is entitled to full refund with prescribed interest or monthly interest for delayed possession of flat/plot.', 'If the promoter fails to complete or is unable to give possession of an apartment, plot or building in accordance with the terms of the agreement for sale...', 'Refund of entire principal amount with SBI Highest MCLR + 2% interest rate.'),
(13, 14, 'Section 12 & 18', 'Application to Magistrate and Protection Orders', 'Aggrieved woman can file petition directly to Magistrate for urgent protection orders, residence rights, and interim monetary relief.', 'An aggrieved person or a Protection Officer on behalf of the aggrieved person may present an application to the Magistrate...', 'Magistrate order restraining respondent from domestic violence, entering shared household, or communicating.'),
(14, 15, 'Section 5 & 12', 'Right to Payment of Maternity Benefit and Dismissal Protection', 'Women employees are entitled to 26 weeks paid maternity leave; employer cannot dismiss a woman during maternity absence.', 'Every woman shall be entitled to, and her employer shall be liable for, the payment of maternity benefit at the rate of the average daily wage...', 'Imprisonment up to one year and mandatory payment of full wages.'),
(15, 16, 'Section 4 & 7', 'Payment of Gratuity and Determination of Amount', 'Gratuity is payable on termination of employment after 5 years of continuous service calculated at 15 days wages per year.', 'Gratuity shall be payable to an employee on the termination of his employment after he has rendered continuous service for not less than five years...', 'Recovery certificate issued to District Collector with 10% compound interest.')
ON CONFLICT (id) DO NOTHING;
"""

with open(sql_file, "w") as f:
    f.write(migration_sql)
print(f"Generated base migration in {sql_file}")
