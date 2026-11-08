# Contributing to TSEBO

Thanks for your interest! TSEBO is built for developers and researchers, so contributions from people who would actually use it are very welcome. Beginners are welcome too.

## What we are building (and what we are not)

TSEBO is an **anti-doomscroll** app. Please keep these principles in mind, because contributions that break them will not be merged:

- The feed has a bottom. No infinite scroll or "load more" loops.
- No streaks, public leaderboards, autoplay, red notification badges, urgency timers or "someone is typing" indicators.
- AI summaries are always labelled as AI-generated, linked to the source and flaggable.
- Accessibility is required, not optional (see the checklist below).

## Ways to contribute

- **Code:** features, bug fixes, tests, refactors
- **Docs:** README improvements, architecture notes, ADRs
- **Design:** UI polish, accessibility improvements
- **Feedback:** bug reports and feature ideas (open an issue)
- **Content ideas:** good sources of papers, news and events in Africa

Look for issues labelled `good first issue` or `help wanted`.

## Getting set up

Follow **Getting started** in the [README](README.md): Android Studio, JDK 21 and (once auth work begins) a free Supabase project. If something does not work, open an issue. That is a useful contribution too.

## Workflow

1. **Open or comment on an issue** before starting larger work.
2. **Fork** the repo and create a branch from `main`: `feat/short-description`, `fix/short-description` or `docs/short-description`.
3. Make small, focused commits.
4. **Pull before you start** (`git pull`) so your branch is up to date.
5. **Open a pull request** that explains what changed and why.
6. Respond to review feedback. Reviews are about the code, never the person.

## Commit messages

Use [Conventional Commits](https://www.conventionalcommits.org/):

```
feat: add Brief screen
fix: handle empty ingestion response
docs: explain Supabase setup
chore: update dependencies
```

## Pull request checklist

- [ ] The Android app builds and runs
- [ ] New logic has tests where reasonable
- [ ] No secrets, keys or personal data committed
- [ ] Any new database table ships with **Row Level Security** policies in a migration
- [ ] Accessibility checked: contrast (WCAG AA), screen-reader labels, dynamic type, reduced motion
- [ ] Docs updated if behaviour changed
- [ ] Larger design choices are recorded in `docs/decisions/` as a short ADR

## Security rules

- The Supabase **anon** key is public by design, so **RLS must be enabled on every table**.
- The **service-role** key must only ever be used inside Edge Functions and must never be committed or bundled into the app.
- Please **don't** open a public issue for security problems. Contact the maintainer directly through the email on the GitHub profile.

## Code style

- Follow standard [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Keep shared logic in `shared/`; keep platform-specific code to the minimum
- Use the design tokens (dark theme, 8 pt grid, 12 pt cards, 8 pt inputs) instead of hard-coded colours
- Prefer small, readable functions and meaningful names
- Linting will be added to CI; run it before opening a PR once available

## Architecture decisions

When you make a significant technical choice, add a short note in `docs/decisions/` with:

- **Context:** what problem you were solving
- **Decision:** what you chose
- **Consequences:** trade-offs and what it affects

## Reporting bugs

Open an issue and include what you expected, what happened, steps to reproduce, device, OS version and app version, plus screenshots or logs if helpful.

## Community standards

Be respectful, patient and constructive. Harassment, discrimination and personal attacks are not tolerated. The goal is a welcoming space for students, professionals, researchers and professors alike. A full Code of Conduct will be added before the first public release.

## Questions?

Open a GitHub Discussion or an issue. Thanks for helping build this.
