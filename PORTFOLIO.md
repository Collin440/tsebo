# Portfolio: TSEBO

*A living document. Updated as the project progresses.*

**Author:** Fofo Collin Moleme ([@Collin440](https://github.com/Collin440))
**Status:** Pre-alpha, project created and running
**Started:** October 2026
**Repo:** `https://github.com/Collin440/tsebo`

---

## 1. The project in one paragraph

TSEBO ("knowledge" in Setswana and Sesotho; tagline: *research. discover. engineer.*) is a Kotlin Multiplatform community app for South African developers, engineers, data scientists, researchers and professors. It combines a feed of tech news and research papers (each with a discussion thread), a map of hackathons and tech events, and AI-generated plain-language summaries of papers and articles.

## 2. The problem

Developer and research communities are fragmented: news, papers, events and conversations live on different platforms, and local (South African) events are scattered across chat groups. Existing platforms are either Q&A sites, large general communities or chat-first tools with a lot of unrelated content.

## 3. My goals

- Build a real, deployed product, not a tutorial clone
- Learn Kotlin Multiplatform and Compose Multiplatform properly
- Show practical cloud skills using AWS
- Practise open-source collaboration (docs, issues, reviews)
- Get serious early users and learn from their feedback

## 4. Skills this project demonstrates

| Area | What I'm using |
|---|---|
| Mobile / cross-platform | Kotlin Multiplatform, Compose Multiplatform |
| Backend and data | Supabase (Postgres, auth, realtime) |
| Cloud | AWS Lambda, EventBridge, S3, CloudFront |
| DevOps | GitHub Actions, infrastructure as code |
| AI | Summarisation of papers and articles (not a chatbot) |
| Engineering practice | Architecture decision records, documentation, code review |

## 5. Architecture overview

*(Diagram to be added in week 1-2.)*

- **App:** shared Kotlin module for models and logic, Compose UI on top
- **Backend:** Supabase for auth, data and realtime threads
- **Ingestion:** scheduled AWS Lambda functions pull news and papers into the database
- **Media:** S3 + CloudFront
- **Admin:** a moderation queue and basic stats

Detailed decisions live in [`docs/decisions/`](docs/decisions/).

## 6. Key decisions (ADR log)

| # | Decision | Why | Status |
|---|---|---|---|
| 001 | Kotlin Multiplatform with Android first | Reach Android users first, add iOS later without a rewrite | Planned |
| 002 | Supabase for the core backend | Fast to build, already familiar from my React project | Planned |
| 003 | AWS for ingestion and media | Learn and show cloud skills with a small number of services | Planned |
| 004 | Invite-only closed beta | Quality of feedback over quantity of users | Planned |

## 7. Milestones

- [ ] **Weeks 1-3:** setup, auth, profiles, hubs, splash screen
- [ ] **Weeks 4-6:** feed ingestion and threads
- [ ] **Weeks 7-8:** events and map
- [ ] **Weeks 9-10:** AI summaries, admin screen, closed beta

## 8. Progress log

| Date | Update |
|---|---|
| 2026-10-21 | Defined scope, wrote README, PORTFOLIO and CONTRIBUTING; chose the name TSEBO |
| 2026-10-21 | Installed Android Studio, created the Kotlin Multiplatform project (Android + Desktop), first sample app running on the emulator |
| 2026-10-22 | Fixed Android Studio JDK/SDK settings; added animated TSEBO splash screen |

## 9. Screenshots and demo

*(Add screenshots, a short screen recording and a link to the Play Store test track here.)*

## 10. Results and metrics

*(Fill in: number of beta users, posts, events listed, feedback themes.)*

## 11. What I learned

*(Fill in as the project goes: what was hard, what I'd do differently.)*

## 12. What's next

Video support, university and research-centre pages, direct messaging, iOS release, more hubs (including Game Dev).
