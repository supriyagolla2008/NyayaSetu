const fs = require('fs');
const path = require('path');

const projectDir = '/Users/gollasupriyashri/.gemini/antigravity/scratch/rightsflow/frontend';
if (!fs.existsSync(projectDir)) {
  fs.mkdirSync(projectDir, { recursive: true });
}
process.chdir(projectDir);

const dirs = [
    'src/components/common', 'src/components/layout', 'src/components/auth',
    'src/components/citizen', 'src/components/content-manager', 'src/components/admin',
    'src/pages/public', 'src/pages/citizen', 'src/pages/content-manager', 'src/pages/admin',
    'src/services', 'src/hooks', 'src/contexts', 'src/types', 'src/utils'
];
dirs.forEach(d => fs.mkdirSync(d, { recursive: true }));

const files = {
  'package.json': `{
  "name": "rightsflow-frontend",
  "private": true,
  "version": "0.0.0",
  "type": "module",
  "scripts": {
    "dev": "vite",
    "build": "tsc -b && vite build",
    "lint": "eslint .",
    "preview": "vite preview"
  },
  "dependencies": {
    "axios": "^1.7.7",
    "clsx": "^2.1.1",
    "date-fns": "^3.6.0",
    "lucide-react": "^0.453.0",
    "react": "^18.3.1",
    "react-dom": "^18.3.1",
    "react-hot-toast": "^2.4.1",
    "react-router-dom": "^6.27.0",
    "recharts": "^2.13.0",
    "tailwind-merge": "^2.5.4"
  },
  "devDependencies": {
    "@eslint/js": "^9.13.0",
    "@types/node": "^22.7.5",
    "@types/react": "^18.3.11",
    "@types/react-dom": "^18.3.1",
    "@vitejs/plugin-react": "^4.3.3",
    "autoprefixer": "^10.4.20",
    "eslint": "^9.13.0",
    "eslint-plugin-react-hooks": "^5.0.0",
    "eslint-plugin-react-refresh": "^0.4.13",
    "globals": "^15.11.0",
    "postcss": "^8.4.47",
    "tailwindcss": "^3.4.14",
    "typescript": "~5.6.2",
    "typescript-eslint": "^8.10.0",
    "vite": "^5.4.9"
  }
}`,
  'vite.config.ts': `import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
  server: {
    proxy: {
      '/api': 'http://localhost:8080'
    }
  }
});`,
  'tailwind.config.js': `/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        ice: {
          50: '#F0F9FF',
          100: '#EFF8FF',
          200: '#DCEFFF',
          300: '#BDDEFF'
        },
        navy: {
          DEFAULT: '#123B63',
          light: '#1e4d7a',
          dark: '#0d2a47'
        },
        primary: {
          blue: '#2563EB'
        }
      }
    },
  },
  plugins: [],
}`,
  'postcss.config.js': `export default {
  plugins: {
    tailwindcss: {},
    autoprefixer: {},
  },
}`,
  'tsconfig.json': `{
  "files": [],
  "references": [
    { "path": "./tsconfig.app.json" },
    { "path": "./tsconfig.node.json" }
  ]
}`,
  'tsconfig.app.json': `{
  "compilerOptions": {
    "target": "ES2020",
    "useDefineForClassFields": true,
    "lib": ["ES2020", "DOM", "DOM.Iterable"],
    "module": "ESNext",
    "skipLibCheck": true,
    "baseUrl": ".",
    "paths": {
      "@/*": ["src/*"]
    },
    "moduleResolution": "bundler",
    "allowImportingTsExtensions": true,
    "isolatedModules": true,
    "moduleDetection": "force",
    "noEmit": true,
    "jsx": "react-jsx",
    "strict": true,
    "noUnusedLocals": true,
    "noUnusedParameters": true,
    "noFallthroughCasesInSwitch": true
  },
  "include": ["src"]
}`,
  'tsconfig.node.json': `{
  "compilerOptions": {
    "target": "ES2022",
    "lib": ["ES2023"],
    "module": "ESNext",
    "skipLibCheck": true,
    "moduleResolution": "bundler",
    "allowImportingTsExtensions": true,
    "isolatedModules": true,
    "moduleDetection": "force",
    "noEmit": true,
    "strict": true,
    "noUnusedLocals": true,
    "noUnusedParameters": true,
    "noFallthroughCasesInSwitch": true
  },
  "include": ["vite.config.ts"]
}`,
  'index.html': `<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/svg+xml" href="/vite.svg" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>RightsFlow</title>
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.tsx"></script>
  </body>
</html>`,
  'Dockerfile': `FROM node:20-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]`
};

for (const [file, content] of Object.entries(files)) {
  fs.writeFileSync(file, content);
}

// Generate basic pages
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
    const content = \`import React from 'react';

export const \${name} = () => {
  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold text-navy">\${name}</h1>
      <p className="text-gray-600 mt-2">This page is automatically generated and ready for implementation.</p>
    </div>
  );
};

export default \${name};\n\`;
    fs.writeFileSync(\`src/pages/\${p}.tsx\`, content);
});

// Generate components
const layouts = ["Navbar", "Sidebar", "DashboardLayout", "Footer", "PublicLayout"];
layouts.forEach(l => {
    const content = \`import React from 'react';\nimport { Outlet } from 'react-router-dom';\n\nexport const \${l} = ({ children }: { children?: React.ReactNode }) => {\n  return (\n    <div className="\${l.toLowerCase()}-wrapper">\n      {children || <Outlet />}\n    </div>\n  );\n};\n\`;
    fs.writeFileSync(\`src/components/layout/\${l}.tsx\`, content);
});

const components = ["Button", "Card", "Badge", "Input", "Modal", "LoadingSkeleton", "StatCard"];
components.forEach(c => {
    const content = \`import React from 'react';\n\nexport const \${c} = ({ children, className = '' }: { children?: React.ReactNode, className?: string }) => {\n  return <div className={\`\${c.toLowerCase()} \${className}\`}>{children}</div>;\n};\n\`;
    fs.writeFileSync(\`src/components/common/\${c}.tsx\`, content);
});

console.log('Project setup complete.');
