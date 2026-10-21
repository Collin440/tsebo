# TSEBO

> **research. discover. engineer.**
>
> A developer-first, research-friendly community app for South African builders, engineers, data scientists, researchers and professors. News, papers and events in one place, with a discussion thread on every item.

*Tsebo means "knowledge" in Setswana and Sesotho.*

![Status](https://img.shields.io/badge/status-pre--alpha-orange)
![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF)
![License](https://img.shields.io/badge/license-TBD-lightgrey)

## Why this exists

Developer and research communities are scattered. News lives in one place, papers in another, events in WhatsApp groups, and discussions on yet another platform. TSEBO brings the useful parts together, with a South African focus:

- **Hubs** for focused topics (Kotlin/Mobile, Web, AI & ML, Data Science & Stats; more later)
- **A smart feed** of new tech news and research papers, each with its own discussion thread
- **An events map** for hackathons, conferences and meetups, where organizers can submit their own events
- **AI summaries** (not a chatbot) that give a plain-language take on a paper or article: the main claim, the evidence and a limitation

## Who it's for

Students, junior and employed developers, engineers, architects, data scientists, researchers (MSc, PhD and beyond) and professors. Roles are **self-declared on the profile**; there is no credential validation in v0.1.

## Features (v0.1 scope)

- [ ] Sign-up, profiles and roles (student, developer, researcher, professor)
- [ ] Four starter hubs
- [ ] Aggregated feed of news and papers, with a thread per item
- [ ] Events list and map, with organizer submission and admin approval
- [ ] AI summaries of papers and articles
- [ ] Admin screen: moderation queue and basic stats
- [ ] Animated splash screen
- [ ] Invite-only closed beta on Google Play

**Deferred:** video, university pages, direct messaging, credential gating, subscriptions, extra hubs (e.g. Game Dev).

## Tech stack

| Layer | Choice |
|---|---|
| App | Kotlin Multiplatform + Compose Multiplatform (Android and Desktop now, iOS later) |
| Auth, database, realtime | Supabase (via `supabase-kt`) |
| Media | AWS S3 + CloudFront |
| Ingestion (news, papers) | AWS Lambda + EventBridge (scheduled) |
| Infrastructure as code | Terraform or AWS CDK (decision pending, see `docs/decisions/`) |
| CI/CD | GitHub Actions |

The backend may later gain a Ktor service. See `docs/decisions/` for the reasoning behind each choice.

## Project structure (planned)

```
.
├── androidApp/        # Android app entry point
├── desktopApp/        # Desktop app entry point (fast local testing)
├── shared/            # Shared Kotlin: Compose UI, models, data layer, business logic
├── infra/             # AWS infrastructure as code
├── docs/
│   └── decisions/     # Architecture decision records (ADRs)
├── README.md
├── PORTFOLIO.md
└── CONTRIBUTING.md
```

## Getting started

### Prerequisites

- Latest stable **Android Studio** with the Kotlin Multiplatform plugin
- **JDK 17** or newer
- A Supabase project (free tier is enough)
- An AWS account (optional until the ingestion and media work begins)

### Run the Android app

```bash
git clone https://github.com/Collin440/<repo-name>.git
cd <repo-name>
```

1. Copy `local.properties.example` to `local.properties` and add your Supabase URL and anon key.
2. Open the project in Android Studio and let Gradle sync.
3. Choose the `androidApp` run configuration and run it on an emulator or device (or the `desktopApp` configuration to run it as a desktop window).

> Never commit secrets. `local.properties` is git-ignored.

## Roadmap (about 10 weeks)

| Weeks | Focus |
|---|---|
| 1-3 | Project setup, auth, profiles, hubs, splash screen |
| 4-6 | Feed ingestion (AWS Lambda), threads |
| 7-8 | Events and map |
| 9-10 | AI summaries, admin screen, closed beta |

Progress is tracked in [PORTFOLIO.md](PORTFOLIO.md).

## Contributing

Contributions are welcome, especially from developers and researchers who will use this. Please read [CONTRIBUTING.md](CONTRIBUTING.md) first.

## License

To be decided before the first public release (MIT or Apache 2.0 are the leading options).

## Author

Built by **Fofo Collin Moleme** ([@Collin440](https://github.com/Collin440)), South Africa.
