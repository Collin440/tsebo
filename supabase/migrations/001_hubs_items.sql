-- Hubs: the four fixed topic areas.
create table public.hubs (
  id          text primary key,
  position    smallint not null unique,
  title       text not null,
  description text not null default ''
);

-- Items: papers and news that flow into hubs.
create table public.items (
  id           uuid primary key default gen_random_uuid(),
  hub_id       text not null references public.hubs (id),
  kind         text not null check (kind in ('paper', 'news')),
  title        text not null,
  source_name  text not null,
  source_url   text not null unique,
  published_at timestamptz,
  tags         text[] not null default '{}',
  created_at   timestamptz not null default now()
);

create index items_hub_published_idx on public.items (hub_id, published_at desc);

-- Row Level Security: content is public to read, and the app can never write it.
alter table public.hubs enable row level security;
alter table public.items enable row level security;

create policy "hubs are readable by everyone"
  on public.hubs for select using (true);

create policy "items are readable by everyone"
  on public.items for select using (true);

-- Expose read access to the Data API explicitly (least privilege).
grant usage on schema public to anon, authenticated;
grant select on public.hubs, public.items to anon, authenticated;

-- There are no insert, update or delete policies on purpose.
-- Only the service role (inside Edge Functions) can write content.

insert into public.hubs (id, position, title, description) values
  ('mobile-web', 1, 'Mobile & Web', 'Kotlin, Compose, JS/TS, React, Flutter, frontend and backend web'),
  ('cloud-infra', 2, 'Cloud & Infrastructure', 'AWS, Azure, GCP, DevOps, platform engineering, architecture'),
  ('ai-ml', 3, 'AI & ML', 'LLMs, ML engineering, applied research, data tooling'),
  ('africa-biz', 4, 'Africa Business Tech', 'Funding, startups, policy, hiring market, local industry news');