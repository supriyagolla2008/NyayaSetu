import React, { useEffect } from 'react';
import { BrowserRouter, Routes, Route, Navigate, useLocation } from 'react-router-dom';
import { AuthProvider, useAuth, Role } from './contexts/AuthContext';
import { ScreenShowcaseBar } from './components/common/ScreenShowcaseBar';

// Layouts
import { PublicLayout } from './components/layout/PublicLayout';
import { DashboardLayout } from './components/layout/DashboardLayout';

// Public Pages
import LandingPage from './pages/public/LandingPage';
import AboutPage from './pages/public/AboutPage';
import HowItWorksPage from './pages/public/HowItWorksPage';
import RoleSelectionPage from './pages/public/RoleSelectionPage';
import CitizenLoginPage from './pages/public/CitizenLoginPage';
import ContentManagerLoginPage from './pages/public/ContentManagerLoginPage';
import AdminLoginPage from './pages/public/AdminLoginPage';
import RegisterPage from './pages/public/RegisterPage';

// Citizen Pages
import CitizenDashboard from './pages/citizen/CitizenDashboard';
import ProblemSolverPage from './pages/citizen/ProblemSolverPage';
import AIAssistantPage from './pages/citizen/AIAssistantPage';
import RightsLibraryPage from './pages/citizen/RightsLibraryPage';
import RightDetailPage from './pages/citizen/RightDetailPage';
import SearchPage from './pages/citizen/SearchPage';
import CasesPage from './pages/citizen/CasesPage';
import CaseDetailPage from './pages/citizen/CaseDetailPage';
import DocumentsPage from './pages/citizen/DocumentsPage';
import ActionPlanPage from './pages/citizen/ActionPlanPage';
import ServicesPage from './pages/citizen/ServicesPage';
import AuthoritiesPage from './pages/citizen/AuthoritiesPage';
import NotificationsPage from './pages/citizen/NotificationsPage';
import LegalUpdatesPage from './pages/citizen/LegalUpdatesPage';
import ProfilePage from './pages/citizen/ProfilePage';
import SettingsPage from './pages/citizen/SettingsPage';
import FeedbackPage from './pages/citizen/FeedbackPage';

// Content Manager Pages
import CMDashboard from './pages/content-manager/CMDashboard';
import ManageRightsPage from './pages/content-manager/ManageRightsPage';
import AddRightPage from './pages/content-manager/AddRightPage';
import EditRightPage from './pages/content-manager/EditRightPage';
import ManageLawsPage from './pages/content-manager/ManageLawsPage';
import ManageProvisionsPage from './pages/content-manager/ManageProvisionsPage';
import ManageServicesPage from './pages/content-manager/ManageServicesPage';
import ManageAuthoritiesPage from './pages/content-manager/ManageAuthoritiesPage';
import ManageLegalUpdatesPage from './pages/content-manager/ManageLegalUpdatesPage';
import ManageSourcesPage from './pages/content-manager/ManageSourcesPage';
import VerificationPage from './pages/content-manager/VerificationPage';
import ContentReportsPage from './pages/content-manager/ContentReportsPage';
import RAGManagementPage from './pages/content-manager/RAGManagementPage';
import CMAnalyticsPage from './pages/content-manager/CMAnalyticsPage';

// Admin Pages
import AdminDashboard from './pages/admin/AdminDashboard';
import UserManagementPage from './pages/admin/UserManagementPage';
import RoleManagementPage from './pages/admin/RoleManagementPage';
import CaseMonitoringPage from './pages/admin/CaseMonitoringPage';
import AdminNotificationsPage from './pages/admin/AdminNotificationsPage';
import FeedbackManagementPage from './pages/admin/FeedbackManagementPage';
import AnalyticsPage from './pages/admin/AnalyticsPage';
import AuditLogsPage from './pages/admin/AuditLogsPage';
import SystemSettingsPage from './pages/admin/SystemSettingsPage';
import AIMonitoringPage from './pages/admin/AIMonitoringPage';

// Secure Role-Based Protected Route
const ProtectedRoute = ({ children, allowedRole }: { children: React.ReactNode, allowedRole: Role }) => {
  const { user, isAuthenticated, loading, hasRole } = useAuth();
  const location = useLocation();

  if (loading) {
    return (
      <div className="min-h-screen bg-[#EFF8FF] flex items-center justify-center">
        <div className="flex flex-col items-center gap-3">
          <div className="w-10 h-10 border-4 border-blue-600 border-t-transparent rounded-full animate-spin"></div>
          <p className="text-sm font-medium text-[#123B63]">Loading NyayaSetu Session...</p>
        </div>
      </div>
    );
  }

  if (!isAuthenticated || !user) {
    const loginPath = allowedRole === 'ADMINISTRATOR' 
      ? '/login/admin' 
      : allowedRole === 'CONTENT_MANAGER' 
      ? '/login/content-manager' 
      : '/login/citizen';
    return <Navigate to={loginPath} state={{ from: location }} replace />;
  }

  if (!hasRole(allowedRole)) {
    // User is authenticated but doesn't have the required role; route to their authorized area
    if (hasRole('ADMINISTRATOR')) {
      return <Navigate to="/admin/dashboard" replace />;
    } else if (hasRole('CONTENT_MANAGER')) {
      return <Navigate to="/content-manager/dashboard" replace />;
    } else {
      return <Navigate to="/citizen/dashboard" replace />;
    }
  }

  return <>{children}</>;
};

const AppRoutes = () => {
  return (
    <>
      <ScreenShowcaseBar />
      <Routes>
        <Route element={<PublicLayout />}>
          <Route path="/" element={<LandingPage />} />
          <Route path="/about" element={<AboutPage />} />
          <Route path="/how-it-works" element={<HowItWorksPage />} />
          <Route path="/select-role" element={<RoleSelectionPage />} />
          <Route path="/login/citizen" element={<CitizenLoginPage />} />
          <Route path="/login/content-manager" element={<ContentManagerLoginPage />} />
          <Route path="/login/admin" element={<AdminLoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
        </Route>

        {/* Citizen Routes */}
        <Route path="/citizen" element={<ProtectedRoute allowedRole="CITIZEN"><DashboardLayout /></ProtectedRoute>}>
          <Route path="dashboard" element={<CitizenDashboard />} />
          <Route path="problem" element={<ProblemSolverPage />} />
          <Route path="ai-assistant" element={<AIAssistantPage />} />
          <Route path="rights" element={<RightsLibraryPage />} />
          <Route path="rights/:id" element={<RightDetailPage />} />
          <Route path="search" element={<SearchPage />} />
          <Route path="cases" element={<CasesPage />} />
          <Route path="cases/:id" element={<CaseDetailPage />} />
          <Route path="documents" element={<DocumentsPage />} />
          <Route path="action-plan/:caseId" element={<ActionPlanPage />} />
          <Route path="services" element={<ServicesPage />} />
          <Route path="authorities" element={<AuthoritiesPage />} />
          <Route path="notifications" element={<NotificationsPage />} />
          <Route path="legal-updates" element={<LegalUpdatesPage />} />
          <Route path="profile" element={<ProfilePage />} />
          <Route path="settings" element={<SettingsPage />} />
          <Route path="feedback" element={<FeedbackPage />} />
        </Route>

        {/* Content Manager Routes */}
        <Route path="/content-manager" element={<ProtectedRoute allowedRole="CONTENT_MANAGER"><DashboardLayout /></ProtectedRoute>}>
          <Route path="dashboard" element={<CMDashboard />} />
          <Route path="rights" element={<ManageRightsPage />} />
          <Route path="rights/new" element={<AddRightPage />} />
          <Route path="rights/:id/edit" element={<EditRightPage />} />
          <Route path="laws" element={<ManageLawsPage />} />
          <Route path="provisions" element={<ManageProvisionsPage />} />
          <Route path="services" element={<ManageServicesPage />} />
          <Route path="authorities" element={<ManageAuthoritiesPage />} />
          <Route path="legal-updates" element={<ManageLegalUpdatesPage />} />
          <Route path="sources" element={<ManageSourcesPage />} />
          <Route path="verification" element={<VerificationPage />} />
          <Route path="reports" element={<ContentReportsPage />} />
          <Route path="rag" element={<RAGManagementPage />} />
          <Route path="analytics" element={<CMAnalyticsPage />} />
        </Route>

        {/* Admin Routes */}
        <Route path="/admin" element={<ProtectedRoute allowedRole="ADMINISTRATOR"><DashboardLayout /></ProtectedRoute>}>
          <Route path="dashboard" element={<AdminDashboard />} />
          <Route path="users" element={<UserManagementPage />} />
          <Route path="roles" element={<RoleManagementPage />} />
          <Route path="cases" element={<CaseMonitoringPage />} />
          <Route path="notifications" element={<AdminNotificationsPage />} />
          <Route path="feedback" element={<FeedbackManagementPage />} />
          <Route path="analytics" element={<AnalyticsPage />} />
          <Route path="audit-logs" element={<AuditLogsPage />} />
          <Route path="settings" element={<SystemSettingsPage />} />
          <Route path="ai-monitoring" element={<AIMonitoringPage />} />
        </Route>

        {/* Fallback to landing */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </>
  );
};

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <AppRoutes />
      </BrowserRouter>
    </AuthProvider>
  );
}
