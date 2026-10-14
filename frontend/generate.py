import os

project_dir = "/Users/gollasupriyashri/.gemini/antigravity/scratch/rightsflow/frontend"
os.chdir(project_dir)

pages = [
  "public/LandingPage", "public/AboutPage", "public/HowItWorksPage", "public/RoleSelectionPage", "public/RegisterPage",
  "public/CitizenLoginPage", "public/ContentManagerLoginPage", "public/AdminLoginPage",
  "citizen/CitizenDashboard", "citizen/ProblemSolverPage", "citizen/AIAssistantPage", "citizen/RightsLibraryPage",
  "citizen/RightDetailPage", "citizen/SearchPage", "citizen/CasesPage", "citizen/CaseDetailPage", "citizen/DocumentsPage",
  "citizen/ActionPlanPage", "citizen/ServicesPage", "citizen/AuthoritiesPage", "citizen/NotificationsPage",
  "citizen/LegalUpdatesPage", "citizen/ProfilePage", "citizen/SettingsPage", "citizen/FeedbackPage",
  "content-manager/CMDashboard", "content-manager/ManageRightsPage", "content-manager/AddRightPage",
  "content-manager/EditRightPage", "content-manager/ManageLawsPage", "content-manager/ManageProvisionsPage",
  "content-manager/ManageServicesPage", "content-manager/ManageAuthoritiesPage", "content-manager/ManageLegalUpdatesPage",
  "content-manager/ManageSourcesPage", "content-manager/VerificationPage", "content-manager/ContentReportsPage",
  "content-manager/RAGManagementPage", "content-manager/CMAnalyticsPage",
  "admin/AdminDashboard", "admin/UserManagementPage", "admin/RoleManagementPage", "admin/CaseMonitoringPage",
  "admin/AdminNotificationsPage", "admin/FeedbackManagementPage", "admin/AnalyticsPage", "admin/AuditLogsPage",
  "admin/SystemSettingsPage", "admin/AIMonitoringPage"
]

for p in pages:
    name = os.path.basename(p)
    content = f"""import React from 'react';

export const {name} = () => {{
  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold text-navy">{name}</h1>
      <p className="text-gray-600 mt-2">This page is automatically generated and ready for implementation.</p>
    </div>
  );
}};

export default {name};
"""
    with open(f"src/pages/{p}.tsx", "w") as f:
        f.write(content)

layouts = ["Navbar", "Sidebar", "DashboardLayout", "Footer", "PublicLayout"]
for l in layouts:
    content = f"""import React from 'react';
import {{ Outlet }} from 'react-router-dom';

export const {l} = ({{ children }}: {{ children?: React.ReactNode }}) => {{
  return (
    <div className="{l.lower()}-wrapper">
      {{children || <Outlet />}}
    </div>
  );
}};
"""
    with open(f"src/components/layout/{l}.tsx", "w") as f:
        f.write(content)

components = ["Button", "Card", "Badge", "Input", "Modal", "LoadingSkeleton", "StatCard"]
for c in components:
    content = f"""import React from 'react';

export const {c} = ({{ children, className = '' }}: {{ children?: React.ReactNode, className?: string }}) => {{
  return <div className={{`{c.lower()} ${{className}}`}}>{{children}}</div>;
}};
"""
    with open(f"src/components/common/{c}.tsx", "w") as f:
        f.write(content)

print("Scaffolding complete.")
