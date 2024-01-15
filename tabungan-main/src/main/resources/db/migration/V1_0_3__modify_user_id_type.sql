alter table transactions drop constraint transactions_user_id_fkey;
alter table categories drop constraint categories_user_id_fkey;

ALTER TABLE users ADD COLUMN user_id uuid;
alter table users drop constraint users_pkey;
alter table users drop column id;
ALTER TABLE users add primary key (user_id);

alter table categories drop user_id;
alter table categories add user_id uuid references users(user_id);
update categories set user_id='35d3d618-b0f7-4353-be2a-b60e7727ef5d';

alter table transactions drop user_id;
alter table transactions add user_id uuid references users(user_id);
update transactions set user_id='35d3d618-b0f7-4353-be2a-b60e7727ef5d';
