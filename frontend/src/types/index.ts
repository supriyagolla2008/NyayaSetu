export type Role = 'CITIZEN' | 'CONTENT_MANAGER' | 'ADMINISTRATOR';

export interface User {
  id: string | number;
  name?: string;
  fullName: string;
  email: string;
  phone?: string;
  role: Role;
  roles?: string[];
  preferredLanguage?: string;
  stateLocation?: string;
  status?: string;
  createdAt?: string;
}

export interface LoginRequest {
  email: string;
  password: string;
  expectedRole?: string;
}

export interface RegisterRequest {
  fullName: string;
  email: string;
  password: string;
  phone?: string;
  preferredLanguage?: string;
  stateLocation?: string;
  legalInterests?: string[];
}

export interface AuthResponse {
  token: string;
  type: string;
  id: number;
  fullName: string;
  email: string;
  phone?: string;
  preferredLanguage?: string;
  stateLocation?: string;
  roles: string[];
}

export interface LegalCategory {
  id: number | string;
  name: string;
  slug: string;
  description: string;
  iconName?: string;
  colorTheme?: string;
  displayOrder?: number;
  rightsCount?: number;
}

export interface LawProvision {
  id: number | string;
  lawId?: number;
  lawTitle?: string;
  sectionNumber?: string;
  title: string;
  simpleSummary?: string;
  description?: string;
  fullText?: string;
  penaltyOrRemedy?: string;
}

export interface Law {
  id: number | string;
  title: string;
  shortCode?: string;
  year?: number;
  enactmentDate?: string;
  jurisdiction?: string;
  description?: string;
  officialLink?: string;
  sourceId?: number;
  sourceName?: string;
  provisions?: LawProvision[];
}

export interface FaqItem {
  id: number | string;
  question: string;
  answer: string;
  displayOrder?: number;
}

export interface LegalRightSummary {
  id: number | string;
  title: string;
  slug: string;
  categoryId: number | string;
  categoryName?: string;
  colorTheme?: string;
  shortDescription: string;
  simpleExplanation: string;
  applicableLaw?: string;
  relevantProvision?: string;
  relevantAuthority?: string;
  relevantService?: string;
  verificationStatus?: string;
  contentStatus?: string;
  viewCount?: number;
  lastVerified?: string;
}

export interface LegalRightDetail {
  id: number | string;
  categoryId: number;
  categoryName: string;
  categoryColor?: string;
  title: string;
  slug: string;
  shortDescription: string;
  simpleExplanation: string;
  detailedExplanation?: string;
  whoItAppliesTo?: string;
  whyItApplies?: string;
  realWorldExample?: string;
  recommendedActions?: string;
  requiredDocuments?: string;
  relevantAuthority?: string;
  relevantService?: string;
  procedureSummary?: string;
  officialSourceUrl?: string;
  sourceAuthority?: string;
  lastVerified?: string;
  effectiveFrom?: string;
  effectiveTo?: string;
  version?: number;
  verificationStatus: string;
  contentStatus: string;
  viewCount: number;
  laws?: Law[];
  provisions?: LawProvision[];
  faqs?: FaqItem[];
  bookmarked?: boolean;
}

// Aliases for compatibility
export type LegalRight = LegalRightDetail;
export type RightSummary = LegalRightSummary;

export type CaseStatus = 'DRAFT' | 'DOCUMENTS_PENDING' | 'ACTION_TAKEN' | 'UNDER_REVIEW' | 'RESOLVED' | 'CLOSED';

export interface CaseActionItem {
  id: number;
  actionStep: string;
  status: 'PENDING' | 'COMPLETED' | 'SKIPPED';
  completedAt?: string;
  notes?: string;
}

export interface CaseTimelineItem {
  id: number;
  fromStatus?: string;
  toStatus: string;
  changeNote?: string;
  changedAt: string;
  changedBy?: string;
}

export interface CaseItem {
  id: number;
  caseNumber: string;
  title: string;
  categoryId: number;
  categoryName?: string;
  rightId?: number;
  rightTitle?: string;
  problemDescription: string;
  status: CaseStatus;
  relatedLaws?: string;
  relatedProvisions?: string;
  authorityName?: string;
  serviceName?: string;
  createdAt: string;
  updatedAt: string;
  resolvedAt?: string;
  actions?: CaseActionItem[];
  history?: CaseTimelineItem[];
}

export type Case = CaseItem;

export interface CaseCreateRequest {
  title: string;
  categoryId?: number;
  rightId?: number;
  problemDescription: string;
  relatedLaws?: string;
  relatedProvisions?: string;
  authorityName?: string;
  serviceName?: string;
  initialActions?: string[];
  notes?: string;
}

export interface NotificationItem {
  id: number;
  title: string;
  message: string;
  notificationType: string;
  isRead: boolean;
  linkUrl?: string;
  createdAt: string;
}

export interface LegalUpdateItem {
  id: number;
  title: string;
  slug: string;
  summary: string;
  fullDetails?: string;
  affectedLawName?: string;
  categoryName?: string;
  sourceAuthority?: string;
  officialNotificationUrl?: string;
  effectiveDate: string;
  isImportantAlert?: boolean;
}

export interface AuthorityItem {
  id: number;
  name: string;
  jurisdictionLevel?: string;
  contactPhone?: string;
  contactEmail?: string;
  officialWebsite?: string;
  physicalAddress?: string;
  description?: string;
}

export interface LegalServiceItem {
  id: number;
  name: string;
  categoryName?: string;
  description: string;
  eligibilityCriteria?: string;
  onlinePortalUrl?: string;
  feeDetails?: string;
  processingTimeEstimate?: string;
  authorityName?: string;
}

export interface DocumentItem {
  id: number;
  fileName: string;
  originalFileName: string;
  contentType: string;
  fileSizeBytes: number;
  filePath: string;
  description?: string;
  caseId?: number;
  caseNumber?: string;
  createdAt: string;
}

export interface CitizenDashboardStats {
  activeCases: number;
  resolvedCases: number;
  pendingActions: number;
  pendingDocuments: number;
  savedRights: number;
  recentlyViewed?: LegalRightSummary[];
  recentCases?: CaseItem[];
  recentNotifications?: NotificationItem[];
  relevantUpdates?: LegalUpdateItem[];
}

export interface CMDashboardStats {
  totalRights: number;
  verifiedRights: number;
  pendingVerification: number;
  draftRights: number;
  outdatedRights: number;
  totalLegalUpdates: number;
  pendingReports: number;
  totalKnowledgeChunks: number;
  embeddedChunks: number;
  pendingEmbeddings: number;
  recentContent?: LegalRightSummary[];
  categoryDistribution?: Record<string, number>;
}

export interface AdminDashboardStats {
  totalUsers: number;
  activeUsers: number;
  inactiveUsers: number;
  totalCases: number;
  openCases: number;
  underReviewCases: number;
  resolvedCases: number;
  closedCases: number;
  totalSearches: number;
  aiQueriesCount: number;
  averageFeedbackRating: number;
  userGrowth?: Array<{ month: string; users: number }>;
  casesByStatus?: Array<{ status: string; count: number }>;
  popularCategories?: Array<{ category: string; count: number }>;
  aiUsageTrend?: Array<{ date: string; queries: number; tokenCount: number }>;
}

export interface AIAnalysisMatch {
  rightId: number;
  title: string;
  slug: string;
  category: string;
  relevanceScore: number;
  summary: string;
  keyProvision: string;
  authority: string;
  officialPortal: string;
}

export interface AIAnalysisResponse {
  query: string;
  detectedLanguage: string;
  identifiedCategory: string;
  summary: string;
  plainEnglishExplanation: string;
  recommendedSteps: string[];
  requiredDocuments: string[];
  authorityName: string;
  servicePortalUrl: string;
  relevantLaw: string;
  relevantSection: string;
  matchedRights: AIAnalysisMatch[];
  aiGenerated: boolean;
  modelUsed: string;
}

export interface GlobalSearchResponse {
  query: string;
  totalResults: number;
  rights: LegalRightSummary[];
  laws: Law[];
  services: LegalServiceItem[];
  updates: LegalUpdateItem[];
}

export interface AuditLogItem {
  id: number;
  userId?: number;
  userName?: string;
  userEmail?: string;
  action: string;
  entityType: string;
  entityId?: string;
  details?: string;
  ipAddress?: string;
  createdAt: string;
}

export interface PagedResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  page: number;
  size: number;
  number?: number;
}
