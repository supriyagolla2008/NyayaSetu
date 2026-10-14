# 🏛️ NyayaSetu (न्यायसेतु) — Digital Legal Rights & Citizen Service Navigation Platform

<div align="center">

![NyayaSetu](https://img.shields.io/badge/NyayaSetu-न्यायसेतु-2563EB?style=for-the-badge&logo=scale&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-18-61DAFB?style=flat-square&logo=react&logoColor=black)
![TypeScript](https://img.shields.io/badge/TypeScript-5.4-3178C6?style=flat-square&logo=typescript&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![Vite](https://img.shields.io/badge/Vite-5.4-646CFF?style=flat-square&logo=vite&logoColor=white)
![Tailwind](https://img.shields.io/badge/Tailwind-3.4-06B6D4?style=flat-square&logo=tailwindcss&logoColor=white)

**Know Your Rights. Find Your Way to Justice.**

*A full-stack Indian legal awareness, dispute guidance, and public service navigation platform connecting ordinary citizens to statutory rights, actionable roadmaps, and official government portals.*

</div>

---

> ⚖️ **Official Legal Disclaimer:** NyayaSetu (न्यायसेतु) provides general legal information and service-navigation assistance. It does not provide formal legal advice, establish an advocate-client relationship, or replace professional legal counsel.

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [14 Statutory Legal Categories & 70 Verified Rights](#-14-statutory-legal-categories--70-verified-rights)
- [Three Role-Based Workspaces](#-three-role-based-workspaces)
- [System Architecture](#-system-architecture)
- [Tech Stack](#-tech-stack)
- [Database Schema & Flyway Migrations](#-database-schema--flyway-migrations)
- [Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Quick Start with Docker Compose](#quick-start-with-docker-compose)
  - [Manual Local Setup](#manual-local-setup)
- [Demo Credentials](#-demo-credentials)
- [API Endpoints](#-api-endpoints)
- [Automated Testing](#-automated-testing)
- [Project Structure](#-project-structure)

---

## 🌟 Overview

NyayaSetu (The Bridge to Justice) resolves a critical barrier in India's legal ecosystem: while citizens possess extensive rights under statutory law, complex terminology and administrative opacity prevent them from taking effective action.

### The Request Flow
```
React UI (TypeScript + Tailwind)
       │
       ▼ (Axios Client with JWT Bearer Interceptors)
Spring Boot REST API (Java 21 + Spring Security 6)
       │
       ▼ (Service Business Logic & Multilingual NLP Classifier)
Spring Data JPA / Parameterized Hibernate Queries
       │
       ▼ (ACID Transactions & Constraints)
PostgreSQL 16 (Flyway Migrations V1–V7)
```

---

## ✨ Key Features

1. **Multilingual Problem Classification** — Plain-language problem inputs in English, Hindi (हिन्दी), and Telugu (తెలుగు) mapped to statutory domains.
2. **70 Codified Statutory Rights** — Exactly 5 comprehensive rights per category covering legal basis, procedural action steps, document checklists, and authorities.
3. **Smart Checklist Engine** — Interactive action and document checklists persisted directly to PostgreSQL.
4. **Lifecycle Case Tracking** — Track cases through formal statuses: `CREATED` → `IN_PROGRESS` → `RESOLVED` / `CLOSED`.
5. **Government Portal Routing** — Direct integration pathways to e-Daakhil, NALSA, CyberCrime (1930 Helpline), eCourts, and SAMADHAN.
6. **Robust Non-AI Fallback** — Operates fully offline without external AI keys via high-precision statutory keyword rules.
7. **Three Role Workspaces** — Tailored dashboards and capabilities for Citizens, Content Managers, and Administrators.

---

## 📚 14 Statutory Legal Categories & 70 Verified Rights

| # | Category | Core Statutory Acts Grounded | Verified Rights |
|---|---|---|---|
| 1 | **Women & Gender Rights** | POSH Act 2013, PWDVA 2005, Maternity Benefit 1961, BNS Sec 85/86 | 5 Rights |
| 2 | **Employment & Labour** | Payment of Wages 1936, Industrial Disputes 1947, Payment of Gratuity 1972 | 5 Rights |
| 3 | **Education & Student Rights** | RTE Act 2009, UGC Anti-Ragging Regulations, Fee Transparency | 5 Rights |
| 4 | **Healthcare & Patient Rights** | Clinical Establishments Act 2010, Patient Rights Charter (NHRC/MoHFW) | 5 Rights |
| 5 | **Property, Tenancy & Assets** | Model Tenancy Act 2021, Transfer of Property Act 1882, RERA 2016 | 5 Rights |
| 6 | **Family & Personal Matters** | Hindu Marriage Act 1955, Special Marriage Act 1954, Guardians & Wards | 5 Rights |
| 7 | **Consumer Rights** | Consumer Protection Act 2019 (CPA 2019), E-Commerce Rules 2020 | 5 Rights |
| 8 | **Cybercrime & Digital Rights** | Information Technology Act 2000, DPDP Act 2023, CyberCrime 1930 | 5 Rights |
| 9 | **Government Services & Citizen Rights** | Right to Information (RTI) Act 2005, Public Records Act 1993 | 5 Rights |
| 10 | **Legal Aid & Access to Justice** | Legal Services Authorities Act 1987, Constitution Art 39A, Lok Adalats | 5 Rights |
| 11 | **Senior Citizens** | Maintenance and Welfare of Parents and Senior Citizens Act 2007 | 5 Rights |
| 12 | **Children & Child Protection** | POCSO Act 2012, Child Labour (Prohibition & Regulation) Act 1986 | 5 Rights |
| 13 | **Persons with Disabilities** | Rights of Persons with Disabilities (RPwD) Act 2016 (4% Quota, Accessibility) | 5 Rights |
| 14 | **Crime, Personal Safety & Victim Support** | Bharatiya Nagarik Suraksha Sanhita 2023 (BNSS), CrPC, D.K. Basu Guidelines | 5 Rights |

---

## 👥 Three Role-Based Workspaces

### 1. Citizen (`ROLE_CITIZEN`)
- Natural-language dispute submission and instant statute mapping.
- Browse 70+ verified rights with complete citations.
- Create cases, toggle interactive action checklists, and upload case documents.
- Bookmark key legal rights and view status update notifications.

### 2. Content Manager (`ROLE_CONTENT_MANAGER`)
- Author and verify legal rights, laws, and statutory provisions.
- Ground entries in verified legal sources (India Code, Supreme Court, Ministries).
- Publish legal updates and manage RAG vector search embeddings.

### 3. Administrator (`ROLE_ADMINISTRATOR`)
- Real-time platform metrics: total users, active cases, resolution rates.
- Manage citizen accounts, toggle active/inactive statuses, and assign roles.
- Monitor system audit logs and configure platform security settings.

---

## 🛠️ Tech Stack

### Frontend
- **React 18** with **TypeScript 5.4**
- **Vite 5.4** build system
- **Tailwind CSS 3.4** responsive design system
- **React Router 6** declarative routing with RBAC guards
- **Axios** with centralized interceptors for JWT Bearer headers
- **Lucide React** iconography

### Backend
- **Java 21 LTS**
- **Spring Boot 3.3.4**
- **Spring Security 6** with stateless JWT authentication
- **Spring Data JPA & Hibernate**
- **Maven 3.9** build tool
- **Spring AI** (optional LLM integration with deterministic fallback)

### Database
- **PostgreSQL 16**
- **Flyway Migrations** (V1 through V7)
- Normalized relational schema (3NF) with foreign key constraints & indexes

---

## 🚀 Getting Started

### Prerequisites
- Java 21 (JDK)
- Node.js 18+ & npm
- Docker & Docker Compose (or local PostgreSQL 16)
- Maven 3.9+

### Quick Start with Docker Compose
```bash
# Clone and navigate to workspace
cd rightsflow

# Start PostgreSQL database, Spring Boot backend, and React frontend
docker compose up -d

# Verify containers are running
docker compose ps
```
- Frontend UI: `http://localhost:5173`
- Backend API: `http://localhost:8080`
- API Documentation: `http://localhost:8080/swagger-ui.html`

### Manual Local Setup

#### 1. Database Setup
Ensure PostgreSQL is running locally on port 5432 with database `rightsflow_db`:
```bash
createdb rightsflow_db
```

#### 2. Backend Setup
```bash
cd backend
mvn clean compile
mvn spring-boot:run
```
Flyway automatically runs migrations `V1` through `V7`, seeding all 14 categories and 70 verified rights.

#### 3. Frontend Setup
```bash
cd frontend
npm install
npm run build
npm run dev
```

---

## 🔑 Demo Credentials

All seed accounts are initialized with password: `Password@123`

| Role | Email | Password | Primary Workspace |
|---|---|---|---|
| **Citizen** | `citizen@rightsflow.in` | `Password@123` | `/citizen/dashboard` |
| **Content Manager** | `content@rightsflow.in` | `Password@123` | `/content-manager/dashboard` |
| **Administrator** | `admin@rightsflow.in` | `Password@123` | `/admin/dashboard` |

---

## 📡 API Endpoints

### Authentication (`/api/auth/**`)
- `POST /api/auth/register` — Citizen registration
- `POST /api/auth/login` — JWT login with role validation
- `GET /api/user/profile` — Current user profile and roles

### Public Legal Knowledge (`/api/**`)
- `GET /api/categories` — List all 14 statutory categories
- `GET /api/rights` — Filter rights by category, query, or slug
- `GET /api/rights/{idOrSlug}` — Complete right details with citations
- `POST /api/problems/analyze` — Natural language problem analysis
- `GET /api/legal-updates` — Real-time legal amendments feed

### Citizen Case Management (`/api/cases/**`)
- `GET /api/cases` — Citizen cases with pagination and status filters
- `POST /api/cases` — Create a case from a problem or right
- `GET /api/cases/{id}` — Case detail with timeline and actions
- `PUT /api/cases/{id}/status` — Status transition (`CREATED` → `IN_PROGRESS` → `RESOLVED`)
- `PATCH /api/cases/actions/{actionId}` — Toggle checklist item status (`COMPLETED` / `PENDING`)

### Content Manager (`/api/content/**`)
- `GET /api/content/rights` — Content rights management table
- `POST /api/content/rights` — Create new verified right
- `PUT /api/content/rights/{id}` — Update statutory right
- `DELETE /api/content/rights/{id}` — Archive right
- `GET /api/content/embeddings/status` — Vector embeddings status

### Administrator (`/api/admin/**`)
- `GET /api/admin/users` — User list with role filtering
- `PATCH /api/admin/users/{id}/status` — Toggle user active/suspended status
- `GET /api/admin/cases` — Platform-wide case monitoring
- `GET /api/admin/audit-logs` — Security and access audit logs
- `GET /api/analytics/admin` — Live platform metrics

---

## 🧪 Automated Testing

### Backend Automated Test Suite (27/27 Passing)
Execute all tests using Maven:
```bash
cd backend
mvn test
```
**Test Coverage Includes:**
- `RightsFlowApplicationTests`: Spring Boot context boot.
- `AuthServiceTest`: User registration, BCrypt password hashing, JWT creation.
- `SecurityRbacTest`: MockMvc verification of RBAC rules across public, citizen, and admin endpoints.
- `CaseServiceTest`: Case creation, status transition timeline, checklist toggling, cross-user isolation.
- `ProblemClassificationTest`: Natural-language classification across all 14 categories in English, Hindi, and Telugu.

### Frontend Production Build (0 Errors)
```bash
cd frontend
npm run build
```
Type checks and bundles the entire React 18 application with 0 TypeScript errors.

---

## 📁 Project Structure

```
rightsflow/
├── backend/
│   ├── src/main/java/com/rightsflow/
│   │   ├── ai/                # Classification & NLP service
│   │   ├── config/            # SecurityConfig, CorsConfig, SwaggerConfig
│   │   ├── controller/        # REST Controllers (Auth, Cases, Rights, Admin, CM)
│   │   ├── dto/               # Typed Request/Response DTOs
│   │   ├── entity/            # JPA Entities (3NF normalized)
│   │   ├── repository/        # Spring Data JPA Repositories
│   │   ├── security/          # JwtUtil, JwtAuthenticationFilter, SecurityUser
│   │   └── service/           # Business logic & transaction orchestration
│   ├── src/main/resources/
│   │   ├── db/migration/      # Flyway Migrations V1 through V7
│   │   └── application.yml    # App configuration & datasource
│   └── src/test/java/         # Automated unit & integration test suite
├── frontend/
│   ├── src/
│   │   ├── components/        # Reusable UI components & layouts
│   │   ├── contexts/          # AuthContext with live JWT session
│   │   ├── pages/
│   │   │   ├── public/        # LandingPage, AboutPage, HowItWorksPage, Login
│   │   │   ├── citizen/       # CitizenDashboard, CasesPage, ProblemSolverPage
│   │   │   ├── content-manager/# CMDashboard, ManageRightsPage, RAGManagement
│   │   │   └── admin/         # AdminDashboard, UserManagement, AuditLogs
│   │   ├── services/          # Centralized Axios client & typed API services
│   │   └── types/             # TypeScript interfaces matching backend DTOs
│   └── vite.config.ts         # Vite configuration with /api proxy
├── docker-compose.yml         # Container orchestration
├── IMPLEMENTATION_STATUS.md   # Detailed implementation checklist & matrix
└── README.md                  # Project documentation
```
