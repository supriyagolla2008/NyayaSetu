# Complete SQL generator for NyayaSetu Indian Legal Knowledge Base
import sys

def sq(text):
    if text is None:
        return "NULL"
    return "'" + str(text).replace("'", "''") + "'"

lines = []
lines.append("-- ============================================================")
lines.append("-- NYAYASETU (न्यायसेतु) DATABASE SEED MIGRATION V7")
lines.append("-- 14 Legal Categories, 70 Verified Rights, Statutory Laws, Provisions")
lines.append("-- ============================================================\n")

# 1. Categories
lines.append("-- 1. ENSURE 14 LEGAL CATEGORIES EXIST")
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

# 2. Laws
lines.append("-- 2. ENSURE STATUTORY LAWS EXIST")
laws = [
    (1, 'The Payment of Wages Act, 1936', 'PWA 1936', 1936, '1936-04-23', 'India', 'Regulates the payment of wages to certain classes of employed persons.', 1, 'https://www.indiacode.nic.in/handle/123456789/2381'),
    (2, 'The Consumer Protection Act, 2019', 'CPA 2019', 2019, '2019-08-09', 'India', 'Protects interests of consumers against unfair trade practices and product defects.', 1, 'https://www.indiacode.nic.in/handle/123456789/15256'),
    (3, 'The Legal Services Authorities Act, 1987', 'LSAA 1987', 1987, '1987-10-11', 'India', 'Constitutes legal services authorities to provide free legal services.', 2, 'https://www.indiacode.nic.in/handle/123456789/1944'),
    (4, 'The Information Technology Act, 2000', 'IT Act 2000', 2000, '2000-06-09', 'India', 'Law dealing with cybercrime, electronic records, and digital signatures.', 1, 'https://www.indiacode.nic.in/handle/123456789/1999'),
    (5, 'Maintenance and Welfare of Parents and Senior Citizens Act, 2007', 'MWPSC 2007', 2007, '2007-12-29', 'India', 'Provisions for maintenance and welfare of parents and senior citizens.', 1, 'https://www.indiacode.nic.in/handle/123456789/2045'),
    (6, 'The Sexual Harassment of Women at Workplace Act, 2013', 'POSH Act 2013', 2013, '2013-04-22', 'India', 'Protection against sexual harassment of women at workplace.', 1, 'https://www.indiacode.nic.in/handle/123456789/2104'),
    (7, 'The Right to Fair Compensation and Transparency in Land Acquisition Act, 2013', 'RFCTLARR 2013', 2013, '2013-09-26', 'India', 'Ensures fair compensation and resettlement in land acquisition.', 1, 'https://www.indiacode.nic.in/handle/123456789/2121'),
    (8, 'The Rights of Persons with Disabilities Act, 2016', 'RPwD Act 2016', 2016, '2016-12-27', 'India', 'Ensures non-discrimination, full accessibility, education and employment for disabled.', 1, 'https://www.indiacode.nic.in/handle/123456789/2155'),
    (9, 'The Right to Information Act, 2005', 'RTI Act 2005', 2005, '2005-06-15', 'India', 'Enables citizens to secure information from public authorities.', 1, 'https://www.indiacode.nic.in/handle/123456789/2065'),
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

print(f"Generated base laws and categories ({len(laws)} laws, {len(cats)} categories)")
