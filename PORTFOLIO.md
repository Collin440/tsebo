# Portfolio: TSEBO

*A living document. Updated as the project progresses.*

**Author:** Fofo Collin Moleme ([@Collin440](https://github.com/Collin440))
**Status:** Pre-alpha, splash screen and contents page built, working toward the foundation phase
**Started:** October 2026
**Repo:** `https://github.com/Collin440/tsebo`

---

## 1. The project in one paragraph

TSEBO ("knowledge" in Setswana and Sesotho; tagline: *research. discover. engineer.*) is a mobile-first, anti-doomscroll app for African builders. It delivers a finite daily brief of research papers, tech news and build logs, with lenses that make each item useful to a student, an engineer or a researcher. Its success metric is "closed the app and built something", not time-on-app.

## 2. The problem

The problem is not a shortage of developer content. It is that content arrives through infinite feeds tuned for outrage and urgency, and that local (African) news, papers and events are scattered across many platforms and chat groups. Q&A sites cannot hold process knowledge, and general communities are noisy.

## 3. My goals

- Build a real, deployed product, not a tutorial clone
- Learn Kotlin Multiplatform and Compose Multiplatform properly
- Show end-to-end engineering with a single backend: Supabase with Row Level Security and Edge Functions
- Design for attention and accessibility, and treat both as engineering requirements
- Practise open-source collaboration (docs, ADRs, issues, reviews)
- Get serious early users and learn from their feedback

## 4. Skills this project demonstrates

| Area | What I'm using |
|---|---|
| Mobile / cross-platform | Kotlin Multiplatform, Compose Multiplatform (Android first) |
| Backend and data | Supabase: Postgres, Auth, Row Level Security, Realtime |
| Serverless | Supabase Edge Functions (Deno) with scheduled ingestion |
| AI | Source-first summarisation of papers and articles (not a chatbot) |
| Security | RLS on every table, service-role key kept out of the app |
| Accessibility and design | Dark design system, reduced motion, WCAG AA contrast |
| Engineering practice | Architecture decision records, documentation, code review, CI |

## 5. Architecture overview

*(Diagram to be added.)*

- **App:** shared Kotlin module (models, Ktor client, Supabase client, lens logic) with Compose UI on top
- **Backend:** Supabase for auth, data and realtime threads
- **Ingestion:** a scheduled Edge Function fetches RSS, arXiv and OpenAlex, upserts items, then a summarise function writes AI summaries
- **Admin:** a moderation queue and basic stats
- **Core tables:** profiles, hubs, items, summaries, threads, posts, reactions, events, flags, invites

Detailed decisions live in [`docs/decisions/`](docs/decisions).

## 6. Key decisions (ADR log)

| # | Decision | Why | Status |
|---|---|---|---|
| 001 | Kotlin Multiplatform, mobile first (Android, then iOS) | One codebase, Android users first, iOS after the beta | Accepted |
| 002 | Supabase as the only backend (no DynamoDB / AWS in v0.1) | Two databases means double the modelling and sync work | Accepted |
| 003 | Edge Functions + free cron instead of Lambda + EventBridge | One console, one bill (zero) | Accepted |
| 004 | Finite daily Brief instead of an infinite feed | The core differentiator and the anti-doomscroll thesis | Accepted |
| 005 | Role-aware lenses instead of separate feeds | One app, four audiences | Accepted |
| 006 | Events list + city filter; map deferred to v0.2 | Removes billing risk and about 2 weeks of work | Accepted |
| 007 | Web deferred; mobile first | Compose for web is still Beta and not needed for the beta | Accepted |
| 008 | Invite-only closed beta, recruited by role | Validate all personas, not just one | Accepted |
| 009 | Manual-style contents page as a finite, animated navigation screen | Fits the anti-doomscroll thesis: the page has a bottom, and live status counts replace page numbers | Accepted |

## 7. Milestones

- [x] Project created and running on the emulator
- [x] Animated terminal-style splash screen
- [x] Manual-style contents page with animated transitions, resume bookmark and a version easter egg
- [ ] Dark theme and design tokens
- [ ] Navigation, Supabase auth and onboarding
- [ ] Ingestion pipeline, hubs and the Brief
- [ ] Lenses, AI summaries, threads and reactions
- [ ] Events, admin moderation and command palette
- [ ] Closed beta (14-day Google Play test)

Estimated total: 16-18 weeks (a draft that I will revisit now that web is out of v0.1).

## 8. Progress log

| Date | Update |
|---|---|
| 2026-10-21 | Defined scope, wrote README, PORTFOLIO and CONTRIBUTING; chose the name TSEBO |
| 2026-10-21 | Installed Android Studio, created the Kotlin Multiplatform project (Android + Desktop), first sample app running on the emulator |
| 2026-10-22 | Fixed Android Studio JDK/SDK settings; added animated TSEBO splash screen |
| 2026-10-23 | Revised the plan (v2): finite daily brief, lenses, Supabase-only backend, mobile first; updated the docs |
| 2026-11-06 | Built the manual-style contents page (staggered reveal, dotted leaders, animated splash-to-contents transition), a "resume where you left off" bookmark and a long-press version easter egg |
| 2026-11-08 | Moved all colours into one dark theme (`Theme.kt`) shared by the splash and contents screens |

## 9. Screenshots and demo

![TSEBO splash screen](https://github.com/user-attachments/assets/f3421ca5-581f-40d7-9b2f-83514a5c2680)

## 10. Beta plan

Recruit by role, not by count:

| Group | Target |
|---|---|
| Students / juniors | 6-8 |
| Developers / engineers | 4-6 |
| Researchers | 3-4 |
| Professors | 1-2 |
| Event organiser | 1 |

Sources: university CS departments, local meetups (Johannesburg, Cape Town, Pretoria, Durban), bootcamps, Kotlin/Android communities and research groups. Feedback is recorded here weekly.

## 11. Results and metrics

*(Fill in: number of beta users, posts, events listed, feedback themes.)*

## 12. What I learned

*(Fill in as the project goes: what was hard, what I'd do differently.)*

## 13. What's next

Events map, images, DMs, public iOS release, light mode, personalised ranking, extra hubs (including Game Dev), and a web (Wasm) pilot once the mobile app is solid.
