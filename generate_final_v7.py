import os
from cat_1_to_5 import rights_cat_1_to_5
from cat_6_to_10 import rights_cat_6_to_10
from cat_11_to_14 import rights_cat_11_to_14

def sq(text):
    if text is None:
        return "NULL"
    return "'" + str(text).replace("'", "''") + "'"

lines = []
lines.append("-- ============================================================")
lines.append("-- NYAYASETU (न्यायसेतु) DATABASE SEED MIGRATION V7")
lines.append("-- 14 Legal Categories, 70 Verified Rights, Statutory Laws, Provisions")
lines.append("-- Grounded in India Code, NALSA, eCourts, NCH, and Supreme Court Precedents")
lines.append("-- ============================================================\n")

# 1. Categories
lines.append("-- 1. ENSURE ALL 14 LEGAL CATEGORIES EXIST")
lines.append("INSERT INTO legal_categories (id, name, slug, description, icon_name, color_theme, display_order) VALUES")
cats = [
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
]
cat_values = []
for c in cats:
    cat_values.append(f"({c[0]}, {sq(c[1])}, {sq(c[2])}, {sq(c[3])}, {sq(c[4])}, {sq(c[5])}, {c[6]})")
lines.append(",\n".join(cat_values))
lines.append("""ON CONFLICT (id) DO UPDATE SET
  name = EXCLUDED.name,
  slug = EXCLUDED.slug,
  description = EXCLUDED.description,
  icon_name = EXCLUDED.icon_name,
  color_theme = EXCLUDED.color_theme,
  display_order = EXCLUDED.display_order;\n""")

# 2. Legal Sources
lines.append("-- 2. ENSURE OFFICIAL LEGAL SOURCES EXIST")
sources = [
    (1, 'India Code Legislative Portal', 'INDIA_CODE', 'https://www.indiacode.nic.in', 'Legislative Department, Ministry of Law and Justice', 'Official digital repository of all Central and State Acts.'),
    (2, 'National Legal Services Authority', 'NALSA', 'https://nalsa.gov.in', 'Supreme Court of India / NALSA', 'Apex statutory body for free legal aid and Lok Adalats.'),
    (3, 'eCourts Integrated Mission Mode Project', 'ECOURTS', 'https://ecourts.gov.in', 'e-Committee, Supreme Court of India', 'National portal for court case records, cause lists, and orders.'),
    (4, 'National Consumer Helpline (NCH)', 'CONSUMER_HELPLINE', 'https://consumerhelpline.gov.in', 'Department of Consumer Affairs, Govt. of India', 'Toll-free 1915 consumer grievance redressal portal.'),
    (5, 'National Cyber Crime Reporting Portal', 'MINISTRY_PORTAL', 'https://cybercrime.gov.in', 'Ministry of Home Affairs, Govt. of India', 'Citizen portal for reporting financial cyber fraud and digital crimes.'),
    (6, 'Ministry of Women and Child Development', 'MINISTRY_PORTAL', 'https://wcd.nic.in', 'Govt. of India', 'Official ministry portal for POSH, PWDVA, and child rights.'),
    (7, 'e-Daakhil Consumer Grievance System', 'CONSUMER_HELPLINE', 'https://edaakhil.nic.in', 'National Consumer Disputes Redressal Commission (NCDRC)', 'Online electronic filing portal for consumer commissions across India.'),
    (8, 'Right to Information Online Portal', 'MINISTRY_PORTAL', 'https://rtionline.gov.in', 'Department of Personnel and Training (DoPT), Govt. of India', 'Centralized portal for submitting RTI requests and first appeals.'),
    (9, 'CPGRAMS Centralized Public Grievance Redressal', 'MINISTRY_PORTAL', 'https://pgportal.gov.in', 'Department of Administrative Reforms & Public Grievances (DARPG)', 'Apex citizen grievance lodging and monitoring portal for Central and State ministries.'),
    (10, 'SAMADHAN Labour Dispute Portal', 'MINISTRY_PORTAL', 'https://samadhan.labour.gov.in', 'Ministry of Labour and Employment, Govt. of India', 'Online dispute resolution for industrial and commercial workers.'),
    (11, 'PENCiL Portal for Child Labour Elimination', 'MINISTRY_PORTAL', 'https://pencil.gov.in', 'Ministry of Labour & Employment, Govt. of India', 'Platform for effective enforcement for no child labour.'),
    (12, 'Elder Line Senior Citizen Welfare Portal', 'MINISTRY_PORTAL', 'https://elderline.dosje.gov.in', 'Ministry of Social Justice and Empowerment, Govt. of India', 'National helpline 14567 and welfare portal for senior citizens.')
]
lines.append("INSERT INTO legal_sources (id, name, source_type, official_url, authority_name, description) VALUES")
source_vals = []
for s in sources:
    source_vals.append(f"({s[0]}, {sq(s[1])}, {sq(s[2])}, {sq(s[3])}, {sq(s[4])}, {sq(s[5])})")
lines.append(",\n".join(source_vals))
lines.append("ON CONFLICT (id) DO NOTHING;\n")

# 3. Laws
lines.append("-- 3. ENSURE STATUTORY LAWS EXIST")
laws = [
    (1, 'The Payment of Wages Act, 1936', 'PWA 1936', 1936, '1936-04-23', 'India', 'Regulates the payment of wages to certain classes of employed persons.', 1, 'https://www.indiacode.nic.in/handle/123456789/2381'),
    (2, 'The Consumer Protection Act, 2019', 'CPA 2019', 2019, '2019-08-09', 'India', 'Protects interests of consumers against unfair trade practices and product defects.', 1, 'https://www.indiacode.nic.in/handle/123456789/15256'),
    (3, 'The Legal Services Authorities Act, 1987', 'LSAA 1987', 1987, '1987-10-11', 'India', 'Constitutes legal services authorities to provide free legal services.', 2, 'https://www.indiacode.nic.in/handle/123456789/1944'),
    (4, 'The Information Technology Act, 2000', 'IT Act 2000', 2000, '2000-06-09', 'India', 'Law dealing with cybercrime, electronic records, and digital signatures.', 1, 'https://www.indiacode.nic.in/handle/123456789/1999'),
    (5, 'Maintenance and Welfare of Parents and Senior Citizens Act, 2007', 'MWPSC 2007', 2007, '2007-12-29', 'India', 'Provisions for maintenance and welfare of parents and senior citizens.', 1, 'https://www.indiacode.nic.in/handle/123456789/2045'),
    (6, 'The Sexual Harassment of Women at Workplace Act, 2013', 'POSH Act 2013', 2013, '2013-04-22', 'India', 'Protection against sexual harassment of women at workplace.', 1, 'https://www.indiacode.nic.in/handle/123456789/2104'),
    (7, 'The Right to Fair Compensation and Transparency in Land Acquisition Act, 2013', 'RFCTLARR 2013', 2013, '2013-09-26', 'India', 'Ensures fair compensation and resettlement in land acquisition.', 1, 'https://www.indiacode.nic.in/handle/123456789/2121'),
    (8, 'The Rights of Persons with Disabilities Act, 2016', 'RPwD Act 2016', 2016, '2016-12-27', 'India', 'Ensures non-discrimination, full accessibility, education and employment for disabled.', 1, 'https://www.indiacode.nic.in/handle/123456789/2155'),
    (9, 'The Right to Information Act, 2005', 'RTI Act 2005', 2005, '2005-06-15', 'India', 'Enables citizens to secure information from public authorities.', 8, 'https://www.indiacode.nic.in/handle/123456789/2065'),
    (10, 'The Protection of Children from Sexual Offences Act, 2012', 'POCSO Act 2012', 2012, '2012-06-19', 'India', 'Special law protecting children from sexual assault and harassment.', 1, 'https://www.indiacode.nic.in/handle/123456789/2079'),
    (11, 'The Code of Criminal Procedure, 1973', 'CrPC 1973', 1974, '1974-01-25', 'India', 'Consolidates criminal procedure, police investigation powers, and bail.', 1, 'https://www.indiacode.nic.in/handle/123456789/16225'),
    (12, 'The Digital Personal Data Protection Act, 2023', 'DPDP Act 2023', 2023, '2023-08-11', 'India', 'Governs processing and protection of digital personal data.', 1, 'https://www.indiacode.nic.in/handle/123456789/19445'),
    (13, 'The Real Estate (Regulation and Development) Act, 2016', 'RERA 2016', 2016, '2016-03-25', 'India', 'Regulates real estate and protects rights of apartment buyers.', 1, 'https://www.indiacode.nic.in/handle/123456789/2158'),
    (14, 'The Protection of Women from Domestic Violence Act, 2005', 'PWDVA 2005', 2005, '2005-09-13', 'India', 'Protection against domestic violence and provides residence rights.', 1, 'https://www.indiacode.nic.in/handle/123456789/2021'),
    (15, 'The Maternity Benefit Act, 1961', 'MBA 1961', 1961, '1961-12-12', 'India', 'Provides 26 weeks paid maternity leave and prohibits pregnancy dismissal.', 1, 'https://www.indiacode.nic.in/handle/123456789/1681'),
    (16, 'The Payment of Gratuity Act, 1972', 'PGA 1972', 1972, '1972-08-21', 'India', 'Statutory gratuity scheme after 5 years continuous service.', 1, 'https://www.indiacode.nic.in/handle/123456789/1647'),
    (17, 'The Right of Children to Free and Compulsory Education Act, 2009', 'RTE Act 2009', 2009, '2009-08-27', 'India', 'Guarantees free education to children aged 6 to 14 years.', 1, 'https://www.indiacode.nic.in/handle/123456789/2088'),
    (18, 'The Clinical Establishments Act, 2010', 'CEA 2010', 2010, '2010-08-19', 'India', 'Regulates standards and mandates emergency treatment in hospitals.', 1, 'https://www.indiacode.nic.in/handle/123456789/2091'),
    (19, 'The Hindu Marriage Act, 1955', 'HMA 1955', 1955, '1955-05-18', 'India', 'Codifies marriage, mutual consent divorce, and restitution rights.', 1, 'https://www.indiacode.nic.in/handle/123456789/1572'),
    (20, 'The Juvenile Justice (Care and Protection of Children) Act, 2015', 'JJ Act 2015', 2015, '2015-12-31', 'India', 'Child protection, foster care, and CARA legal adoption.', 1, 'https://www.indiacode.nic.in/handle/123456789/2144'),
    (21, 'The Model Tenancy Act, 2021', 'MTA 2021', 2021, '2021-06-02', 'India', 'Balances tenancy agreements, security deposit refunds, and eviction.', 1, 'https://mohua.gov.in/cms/model-tenancy-act.php'),
    (22, 'The Child and Adolescent Labour (Prohibition and Regulation) Act, 1986', 'CALPRA 1986', 1986, '1986-12-23', 'India', 'Prohibits employment of children below 14 years.', 1, 'https://www.indiacode.nic.in/handle/123456789/1892')
]
lines.append("INSERT INTO laws (id, title, short_code, year, enactment_date, jurisdiction, description, source_id, official_link) VALUES")
law_vals = []
for l in laws:
    law_vals.append(f"({l[0]}, {sq(l[1])}, {sq(l[2])}, {l[3]}, {sq(l[4])}, {sq(l[5])}, {sq(l[6])}, {l[7]}, {sq(l[8])})")
lines.append(",\n".join(law_vals))
lines.append("ON CONFLICT (id) DO NOTHING;\n")

# 4. Law Provisions
lines.append("-- 4. ENSURE LAW PROVISIONS EXIST")
provisions = [
    (8, 9, 'Section 6 & 7', 'Request for Obtaining Information & Disposal of Request', 'Citizens can submit written or online RTI requests; Public Information Officer must provide information within 30 days or 48 hours for life and liberty.', 'A person, who desires to obtain any information under this Act, shall make a request in writing...', 'Rs. 250 per day up to Rs. 25,000 penalty on PIO for unjustified refusal or delay.'),
    (9, 10, 'Section 19 & 21', 'Reporting of Offences and Punishment for Failure to Report', 'Mandatory duty on any person having knowledge of child abuse to report immediately to Special Juvenile Police Unit or local police.', 'Notwithstanding anything contained in the Code of Criminal Procedure...', 'Imprisonment up to 6 months or fine for deliberate failure to report.'),
    (10, 11, 'Section 154 & 157', 'Information in Cognizable Cases & Procedure for Investigation', 'Police must register an FIR for cognizable offences immediately without jurisdictional excuses (Zero FIR doctrine).', 'Every information relating to the commission of a cognizable offence...', 'Disciplinary action and criminal prosecution under Section 166A IPC for refusing FIR.'),
    (11, 12, 'Section 12 & 13', 'Right to Grievance Redressal and Erasure of Personal Data', 'Data Principals have statutory rights to obtain summary of personal data, correct inaccuracies, and seek complete erasure.', 'A Data Principal shall have the right to obtain from the Data Fiduciary confirmation...', 'Data Protection Board inquiry and substantial civil penalties on non-compliant Data Fiduciaries.'),
    (12, 13, 'Section 18', 'Return of Amount and Compensation for Delay', 'Homebuyer is entitled to full refund with prescribed interest or monthly interest for delayed possession of flat/plot.', 'If the promoter fails to complete or is unable to give possession of an apartment...', 'Refund of entire principal amount with SBI Highest MCLR + 2% interest rate.'),
    (13, 14, 'Section 12 & 18', 'Application to Magistrate and Protection Orders', 'Aggrieved woman can file petition directly to Magistrate for urgent protection orders, residence rights, and interim monetary relief.', 'An aggrieved person or a Protection Officer on behalf of the aggrieved person may present an application...', 'Magistrate order restraining respondent from domestic violence or entering shared household.'),
    (14, 15, 'Section 5 & 12', 'Right to Payment of Maternity Benefit and Dismissal Protection', 'Women employees are entitled to 26 weeks paid maternity leave; employer cannot dismiss a woman during maternity absence.', 'Every woman shall be entitled to, and her employer shall be liable for, the payment of maternity benefit...', 'Imprisonment up to one year and mandatory payment of full wages.'),
    (15, 16, 'Section 4 & 7', 'Payment of Gratuity and Determination of Amount', 'Gratuity is payable on termination of employment after 5 years of continuous service calculated at 15 days wages per year.', 'Gratuity shall be payable to an employee on the termination of his employment...', 'Recovery certificate issued to District Collector with 10% compound interest.')
]
lines.append("INSERT INTO law_provisions (id, law_id, section_number, title, simple_summary, full_text, penalty_or_remedy) VALUES")
prov_vals = []
for p in provisions:
    prov_vals.append(f"({p[0]}, {p[1]}, {sq(p[2])}, {sq(p[3])}, {sq(p[4])}, {sq(p[5])}, {sq(p[6])})")
lines.append(",\n".join(prov_vals))
lines.append("ON CONFLICT (id) DO NOTHING;\n")

# 5. Insert All 70 Verified Rights
lines.append("-- 5. SEED ALL 70 VERIFIED LEGAL RIGHTS (5 PER CATEGORY FOR ALL 14 CATEGORIES)")
lines.append("""INSERT INTO rights (
    id, category_id, title, slug, short_description, simple_explanation, detailed_explanation,
    who_it_applies_to, why_it_applies, real_world_example, recommended_actions, required_documents,
    relevant_authority, relevant_service, procedure_summary, official_source_url, source_authority,
    last_verified, effective_from, version, verification_status, content_status, view_count
) VALUES""")

all_rights = rights_cat_1_to_5 + rights_cat_6_to_10 + rights_cat_11_to_14
right_vals = []
right_law_mappings = []

for r in all_rights:
    r_id = r[0]
    cat_id = r[1]
    title = r[2]
    slug = r[3]
    short_desc = r[4]
    simple_exp = r[5]
    detailed_exp = r[6]
    who_covered = r[7]
    why_matters = r[8]
    example = r[9]
    actions = r[10]
    docs = r[11]
    auth = r[12]
    service = r[13]
    proc = r[14]
    source_url = r[15]
    source_auth = r[16]
    law_id = r[17]
    
    val_str = f"({r_id}, {cat_id}, {sq(title)}, {sq(slug)}, {sq(short_desc)}, {sq(simple_exp)}, {sq(detailed_exp)}, {sq(who_covered)}, {sq(why_matters)}, {sq(example)}, {sq(actions)}, {sq(docs)}, {sq(auth)}, {sq(service)}, {sq(proc)}, {sq(source_url)}, {sq(source_auth)}, CURRENT_DATE, CURRENT_DATE, 1, 'VERIFIED', 'PUBLISHED', 0)"
    right_vals.append(val_str)
    
    if law_id:
        right_law_mappings.append((r_id, law_id))

lines.append(",\n".join(right_vals))
lines.append("""ON CONFLICT (id) DO UPDATE SET
  category_id = EXCLUDED.category_id,
  title = EXCLUDED.title,
  slug = EXCLUDED.slug,
  short_description = EXCLUDED.short_description,
  simple_explanation = EXCLUDED.simple_explanation,
  detailed_explanation = EXCLUDED.detailed_explanation,
  who_it_applies_to = EXCLUDED.who_it_applies_to,
  why_it_applies = EXCLUDED.why_it_applies,
  real_world_example = EXCLUDED.real_world_example,
  recommended_actions = EXCLUDED.recommended_actions,
  required_documents = EXCLUDED.required_documents,
  relevant_authority = EXCLUDED.relevant_authority,
  relevant_service = EXCLUDED.relevant_service,
  procedure_summary = EXCLUDED.procedure_summary,
  official_source_url = EXCLUDED.official_source_url,
  source_authority = EXCLUDED.source_authority,
  verification_status = EXCLUDED.verification_status,
  content_status = EXCLUDED.content_status;\n""")

# 6. Mappings to Laws
lines.append("-- 6. LINK RIGHTS TO STATUTORY LAWS IN RIGHT_LAWS")
lines.append("INSERT INTO right_laws (right_id, law_id) VALUES")
rl_vals = [f"({rl[0]}, {rl[1]})" for rl in right_law_mappings]
lines.append(",\n".join(rl_vals))
lines.append("ON CONFLICT (right_id, law_id) DO NOTHING;\n")

# 7. Additional Common FAQs
lines.append("-- 7. SEED PRACTICAL FAQS FOR NYAYASETU")
lines.append("INSERT INTO faqs (id, right_id, question, answer, display_order) VALUES")
faqs = [
    (101, 101, 'Can an ICC refuse to accept an anonymous POSH complaint?', 'While the Act contemplates written complaints from the aggrieved woman, guidelines clarify that if an anonymous complaint contains verifiable specific evidence, the employer can initiate an administrative fact-finding inquiry.', 1),
    (102, 102, 'Can an employer reduce my salary after returning from maternity leave?', 'No. Section 12 of the Maternity Benefit Act strictly prohibits any disadvantageous alteration in terms of service on account of maternity absence.', 1),
    (201, 201, 'Can my company hold back my salary because client payment was delayed?', 'No. Under the Payment of Wages Act, payment of wages is an unconditional statutory obligation of the employer, irrespective of their third-party client contracts.', 1),
    (501, 501, 'Can a builder force me to take possession if the Occupancy Certificate is not issued?', 'No. Offering possession without a valid Occupancy Certificate (OC) is illegal under RERA. You are entitled to interest on delay until valid OC is delivered.', 1),
    (701, 701, 'Can an e-commerce platform print "No return, no refund" on invoices?', 'No. Under the Consumer Protection (E-Commerce) Rules 2020, blanket no-refund clauses for defective or mismatched goods constitute unfair trade practices and are legally void.', 1),
    (801, 801, 'How fast must I call 1930 to stop an unauthorized bank transaction?', 'Within the first 1 to 2 hours (the "Golden Hour"). Immediate reporting allows the National Cyber Crime portal to place an instant freeze on the recipient bank account.', 1),
    (901, 901, 'What is the maximum time a government department has to reply to an RTI query?', '30 calendar days normally. However, if the information concerns the life or liberty of a person, it must be provided within 48 hours under Section 7(1).', 1),
    (1001, 1001, 'Do women have to show income proof to get free legal aid from DLSA?', 'No. Under Section 12(c) of the Legal Services Authorities Act 1987, all women and children are eligible for free legal aid regardless of their income level.', 1),
    (1101, 1101, 'Can a senior citizen file a maintenance case against a grandson?', 'Yes. If the senior citizen has no surviving children, or if grandchildren are major heirs in possession of or set to inherit the ancestral estate.', 1),
    (1401, 1401, 'Can a police station turn me away because the crime happened in another city?', 'No. The Supreme Court in Lalita Kumari held that police MUST register a Zero FIR immediately and subsequently transfer the investigation to the jurisdictional station.', 1)
]
faq_vals = [f"({f[0]}, {f[1]}, {sq(f[2])}, {sq(f[3])}, {f[4]})" for f in faqs]
lines.append(",\n".join(faq_vals))
lines.append("ON CONFLICT (id) DO NOTHING;\n")

# Write to target migration file
target_path = os.path.join("backend", "src", "main", "resources", "db", "migration", "V7__seed_full_14_categories_legal_data.sql")
with open(target_path, "w", encoding="utf-8") as f:
    f.write("\n".join(lines))

print(f"Successfully generated {target_path} with {len(all_rights)} rights and {len(right_law_mappings)} law links!")
