# TSEBO

> **research. discover. engineer.**
>
> A developer-first, anti-doomscroll mobile app for African builders: a finite daily brief of papers, news and build logs, with lenses that make each item useful to a student, an engineer or a researcher.

*Tsebo means "knowledge" in Setswana and Sesotho.*

![Status](https://img.shields.io/badge/status-pre--alpha-orange)
![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF)
![Platform](https://img.shields.io/badge/platform-Android%20first-3DDC84)
![License](https://img.shields.io/badge/license-TBD-lightgrey)

## Why this exists

The problem is not a shortage of developer content. It is that content arrives through infinite feeds tuned for outrage and urgency. TSEBO inverts that:

- **The feed has a bottom.** You get a finite daily brief, then the app tells you to go build something.
- **No streaks, no public leaderboards, no autoplay, no red badges.**
- **Success is "closed the app and built something"**, not time spent in the app.

## What makes it different

- **The Brief:** a curated daily set of 8-15 items across your hubs and lens. It ends with a deliberate "You're done. Go build something." screen.
- **Lenses:** one app for four audiences. A lens changes how each item is presented (see the table below).
- **Source-first AI summaries (not a chatbot):** always three parts (main claim, evidence offered, one limitation), labelled AI-generated, linked to the source and flaggable.
- **Build Log threads:** structured as Tried > Broke > Learned > Next time, creating a searchable archive of process knowledge.
- **Signal reactions instead of likes:** *Saved me time*, *Changed my mind*, *I'll try this*, *Needs evidence*.
- **Command palette:** jump to a hub, search, post or switch lens quickly.

## Hubs (fixed for v0.1)

| Hub | Covers |
|---|---|
| Mobile & Web | Kotlin, Compose, JS/TS, React, Flutter, frontend and backend web |
| Cloud & Infrastructure | AWS, Azure, GCP, DevOps, platform engineering, architecture |
| AI & ML | LLMs, ML engineering, applied research, data tooling |
| Africa Business Tech | Funding, startups, policy, hiring market, local industry news |

Papers and news flow into all four hubs. Items carry tags (`#kotlin`, `#llm`, `#series-a`, `#paper`) so cross-cutting content is filterable without extra hubs.

## Lenses

| Lens | Paper summary becomes | Thread template |
|---|---|---|
| Student | "What to learn here" | Build log + questions |
| Builder | "What to try this week" | Build log |
| Engineer | "What trade-off this makes" | Architecture decision: context, options, decision, consequences |
| Research | "Claim, method, evidence, limitation" | Critique / reading group |

Students also get an **Interview Lens** that frames a summary as "how would this surface in a system design or technical interview?" Roles (including professor) are **self-declared and clearly marked as unverified**.

## v0.1 scope (Android first)

- [x] Animated terminal-style splash screen
- [x] Manual-style contents page with animated transitions
- [ ] Dark theme and design tokens
- [ ] Email auth, role and interests onboarding
- [ ] Four hubs and the finite daily Brief
- [ ] Lenses and Interview Lens
- [ ] Item reading view, threads (Build Log and Research templates), signal reactions
- [ ] AI summaries with a "show me the evidence" jump
- [ ] Events list, city filter and organizer submission (admin-approved)
- [ ] Admin moderation and basic stats
- [ ] Command palette
- [ ] Invite-only closed beta on Google Play

**Out of v0.1:** web, events map, images and video, DMs, public iOS release, credential-gated sign-up, subscriptions, extra hubs, personalised ranking, light mode.

## Tech stack

| Layer | Choice |
|---|---|
| Shared logic | Kotlin Multiplatform (`shared/`): Ktor client, kotlinx.serialization, supabase-kt |
| Mobile UI | Compose Multiplatform. Android first; an iOS target is added after the beta |
| Backend | Supabase: Postgres, Auth, Row Level Security, Realtime |
| Serverless | Supabase Edge Functions (Deno) with a free cron trigger, for ingestion and AI summaries |
| AI summaries | Groq free tier (Llama/Gemma), with a low-cost fallback model. Async, capped per day |
| CI/CD | GitHub Actions: build and lint |
| Media | None in v0.1 (text-first) |

Web (Compose for Web / Wasm) is deliberately deferred: the project is mobile first. A desktop target is kept only for fast local testing.

## Design principles

- **Dark-first and terminal-inspired:** calm, dense but readable.
- **Readability:** about 68 characters per line, 1.6 line height, 15-16 sp minimum body text.
- **Accessibility is non-negotiable:** reduced-motion support, WCAG AA contrast, dynamic type, screen-reader labels.
- **No dark patterns:** no urgency timers, no "someone is typing", no streaks.

## Project structure

```
.
├── androidApp/        # Android app entry point
├── desktopApp/        # Desktop entry point (local testing only)
├── shared/            # Shared Kotlin: Compose UI, models, networking, lens logic
├── supabase/          # (planned) migrations + RLS policies, Edge Functions
│   ├── migrations/
│   └── functions/     # ingest, summarize, approve-event
├── docs/
│   └── decisions/     # Architecture decision records (ADRs)
├── README.md
├── PORTFOLIO.md
└── CONTRIBUTING.md
```

An `iosApp/` module is planned for after the beta.

## Getting started

### Prerequisites

- Latest stable **Android Studio** with the Kotlin Multiplatform plugin
- **JDK 21**
- A free **Supabase** project (needed once auth and data work begin)

### Run the Android app

```bash
git clone https://github.com/Collin440/tsebo.git
cd tsebo
```

1. Open the project in Android Studio and let Gradle sync.
2. Choose the `androidApp` run configuration and run it on an emulator or device.
3. When Supabase is wired in, add your project URL and **anon** key to `local.properties` (it is git-ignored).

> Never commit secrets. The Supabase **service-role** key must only ever live inside Edge Functions, never in the app.

## Roadmap (draft, 16-18 weeks)

| Phase | Focus |
|---|---|
| 1 | Foundation: theme, navigation, Supabase auth, onboarding |
| 2 | Content pipeline: ingestion functions, items, hubs, the Brief |
| 3 | Lenses, AI summaries, threads, reactions |
| 4 | Events, admin moderation, command palette |
| 5 | Invite-only closed beta (Google Play requires a 14-day closed test) |
| 6 | Iterate on feedback, polish, open up |

Progress is tracked in [PORTFOLIO.md](PORTFOLIO.md).

## Contributing

Contributions are welcome, especially from developers and researchers who will use this. Please read [CONTRIBUTING.md](CONTRIBUTING.md) first.

## License

MIT, to be added before the first public release.

## Author

Built by **Fofo Collin Moleme** ([@Collin440](https://github.com/Collin440)), South Africa.
