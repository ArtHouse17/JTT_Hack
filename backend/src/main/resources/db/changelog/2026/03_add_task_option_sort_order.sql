-- liquibase formatted sql

-- changeset codex:3-add-task-option-sort-order
alter table task_options add column sort_order int not null default 0;

with ordered as (
    select ctid, row_number() over (partition by task_id order by ctid) as rn
    from task_options
)
update task_options task_option
set sort_order = ordered.rn
from ordered
where task_option.ctid = ordered.ctid;
