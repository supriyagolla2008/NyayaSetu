const fs = require('fs');
const path = require('path');
const { execSync } = require('child_process');

const run = (cmd) => execSync(cmd, { stdio: 'inherit' });

console.log("Initializing Vite...");
run('npm create vite@latest . -- --template react-ts -y');
console.log("Installing dependencies...");
run('npm install react-router-dom axios lucide-react recharts react-hot-toast clsx date-fns tailwind-merge');
run('npm install -D tailwindcss postcss autoprefixer @types/node');
run('npx tailwindcss init -p');

const dirs = [
    'src/components/common', 'src/components/layout', 'src/components/auth',
    'src/components/citizen', 'src/components/content-manager', 'src/components/admin',
    'src/pages/public', 'src/pages/citizen', 'src/pages/content-manager', 'src/pages/admin',
    'src/services', 'src/hooks', 'src/contexts', 'src/types', 'src/utils'
];

dirs.forEach(d => fs.mkdirSync(d, { recursive: true }));

const pages = [
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
];

pages.forEach(p => {
    const name = path.basename(p);
    const content = `import React from 'react';\n\nexport const ${name} = () => {\n  return (\n    <div className="p-6">\n      <h1 className="text-2xl font-bold text-navy">${name}</h1>\n      <p className="text-gray-600 mt-2">This page is automatically generated and ready for implementation.</p>\n    </div>\n  );\n};\n\nexport default ${name};\n`;
    fs.writeFileSync(`src/pages/${p}.tsx`, content);
});

const layouts = ["Navbar", "Sidebar", "DashboardLayout", "Footer", "PublicLayout"];
layouts.forEach(l => {
    const content = `import React from 'react';\nimport { Outlet } from 'react-router-dom';\n\nexport const ${l} = ({ children }: { children?: React.ReactNode }) => {\n  return (\n    <div className="${l.toLowerCase()}-wrapper">\n      {children || <Outlet />}\n    </div>\n  );\n};\n`;
    fs.writeFileSync(`src/components/layout/${l}.tsx`, content);
});

const components = ["Button", "Card", "Badge", "Input", "Modal", "LoadingSkeleton", "StatCard"];
components.forEach(c => {
    const content = `import React from 'react';\n\nexport const ${c} = ({ children, className = '' }: { children?: React.ReactNode, className?: string }) => {\n  return <div className={\`${c.toLowerCase()} \${className}\`}>{children}</div>;\n};\n`;
    fs.writeFileSync(`src/components/common/${c}.tsx`, content);
});

// Setup tsconfig path aliases
const tsconfig = JSON.parse(fs.readFileSync('tsconfig.app.json', 'utf8'));
tsconfig.compilerOptions.baseUrl = ".";
tsconfig.compilerOptions.paths = { "@/*": ["src/*"] };
fs.writeFileSync('tsconfig.app.json', JSON.stringify(tsconfig, null, 2));

console.log("Scaffolding complete.");
