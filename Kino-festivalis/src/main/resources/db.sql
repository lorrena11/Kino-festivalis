drop table if exists review;
drop table if exists movie;

create table movie (
	id bigserial primary key not null,
	name varchar(100) not null,
	description text,
	duration varchar(10),
	premiere_date date not null,
	average_score numeric(3,1) not null
);

create table review (
	id bigserial not null primary key,
	movie_id bigserial not null references movie(id),
	score numeric(3,1) not null,
	comment text,
	created_at timestamp without time zone default current_timestamp
)