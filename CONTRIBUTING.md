# Contributing to TSEBO

Thanks for your interest! This project is built for developers and researchers, so contributions from people who would actually use it are very welcome. Beginners are welcome too.

## Ways to contribute

- **Code:** features, bug fixes, tests, refactors
- **Docs:** README improvements, architecture notes, guides
- **Design:** UI ideas, accessibility, the splash screen
- **Feedback:** bug reports and feature ideas (open an issue)
- **Content ideas:** good sources of papers, news and events for South Africa

Look for issues labelled `good first issue` or `help wanted`.

## Getting set up

Follow the **Getting started** section in the [README](README.md). If something doesn't work, open an issue. That's a useful contribution too.

## Workflow

1. **Open or comment on an issue** before starting larger work, so we don't duplicate effort.
2. **Fork** the repo and create a branch from `main`:
   - `feat/short-description`
   - `fix/short-description`
   - `docs/short-description`
3. Make your change in small, focused commits.
4. **Open a pull request** with a clear description of what and why.
5. Respond to review feedback. Reviews are about the code, never the person.

## Commit messages

Use [Conventional Commits](https://www.conventionalcommits.org/):

```
feat: add hub list screen
fix: handle empty feed response
docs: explain Supabase setup
chore: update dependencies
```

## Pull request checklist

- [ ] The app builds and runs
- [ ] New logic has tests where reasonable
- [ ] No secrets, keys or personal data committed
- [ ] Docs updated if behaviour changed
- [ ] Larger design choices are recorded in `docs/decisions/` as a short ADR

## Code style

- Follow standard [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Keep shared logic in `shared/`; keep platform-specific code to the minimum
- Prefer small, readable functions and meaningful names
- Linting (ktlint/detekt) will be added; run it before opening a PR once available

## Architecture decisions

When you make a significant technical choice, add a short note in `docs/decisions/` with:

- **Context:** what problem you were solving
- **Decision:** what you chose
- **Consequences:** trade-offs and what it affects

## Reporting bugs

Open an issue and include:

- What you expected and what happened
- Steps to reproduce
- Device, OS version and app version
- Screenshots or logs if helpful

## Security

Please **don't** open a public issue for security problems. Contact the maintainer directly through the email on the GitHub profile.

## Community standards

Be respectful, patient and constructive. Harassment, discrimination and personal attacks are not tolerated. The goal is a welcoming space for students, professionals, researchers and professors alike. A full Code of Conduct will be added before the first public release.

## Questions?

Open a GitHub Discussion or an issue. Thanks for helping build this.
