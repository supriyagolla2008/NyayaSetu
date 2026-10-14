import os

def sq(text):
    if text is None:
        return "NULL"
    return "'" + str(text).replace("'", "''") + "'"

categories = [
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

# Write all categories and full rights logic to generate the complete migration
with open("create_v7_runner.py", "w") as f:
    f.write("# Generator script\n")
print("Initialized runner script")
