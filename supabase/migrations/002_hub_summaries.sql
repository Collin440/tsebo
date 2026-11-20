-- One row per hub with its item count, so the app needs a single request.
create view public.hub_summaries
with (security_invoker = true) as
select
  h.id,
  h.position,
  h.title,
  count(i.id)::int as item_count
from public.hubs h
left join public.items i on i.hub_id = h.id
group by h.id, h.position, h.title;

grant select on public.hub_summaries to anon, authenticated;