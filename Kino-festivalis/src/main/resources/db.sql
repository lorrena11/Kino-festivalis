drop table if exists review;
drop table if exists movie;

create table movie
(
    id            bigserial primary key not null,
    name          varchar(100)          not null,
    description   text,
    duration      varchar(10),
    premiere_date date                  not null,
    initial_score numeric(3, 1)         not null,
    average_score numeric (3,1)
);

insert into movie (name, description, duration, premiere_date, initial_score)
values ('Spring Day', 'A short description of Spring Day', '1h 40m', '2021-04-29', 7),
       ('Parasite', 'A short description of Parasite', '2h 03m', '2021-04-30', 8.9),
       ('Green Book', 'A short description of Green Book', '1h 56m', '2021-04-30', 8),
       ('Unreleased', 'A short description of Unreleased', '1h 30m', '2021-05-30', 9.2),
       ('Tom & Jerry', 'A short description of Tom & Jerry', '1h 35m', '2021-04-01', 3.7),
       ('Raya and the last dragon', 'A short description of Raya and the last dragon', '1h 45m', '2021-04-02', 8),
       ('Godzilla vs Kong', 'A short description of Godzilla vs Kong', '2h 03m', '2021-04-03', 5),
       ('Minari', 'A short description of Minari', '2h 00m', '2021-04-07', 9.5),
       ('Soul', 'A short description of Soul', '1h 40m', '2021-03-25', 9),
       ('Harry Potter', 'A short description of Harry Potter', '1h 35m', '2021-04-01', 5.9),
       ('Casablanca', 'A short description of Casablanca', '2h 35m', '2021-04-29', 8.3),
       ('The Godfather', 'A short description of The Godfather', '2h 15m', '2021-04-20', 9),
       ('Spotlight', 'A short description of Spotlight', '2h 07m', '2021-04-07', 8),
       ('Frozen', 'A short description of Frozen', '2h 00m', '2021-06-07', 5.5);


create table review
(
    id         bigserial     not null primary key,
    movie_id   bigserial     not null references movie (id),
    score      numeric(3, 1) not null,
    comment    text,
    created_at timestamp without time zone default current_timestamp
)

insert into review (movie_id, score, comment)
values (1, 9, 'very good'),
       (1, 8, 'good'),
       (2, 4.5, 'overrated'),
       (2, 7, 'not great'),
       (3, 7.5, 'i liked it'),
       (3, 5, 'fell asleep'),
       (3, 10, 'just happy to be here tbh'),
       (3, 8.5, 'very good movie'),
       (6, 8, 'was ok I guess'),
       (6, 6, 'dragon looks weird'),
       (7, 3, 'unrealistic'),
       (8, 6, 'gave me an existential crisis'),
       (8, 9.2, 'it was nice'),
       (10, 10, 'i liked the guy without nose'),
       (10, 7, 'too long'),
       (10, 8.5, 'very nice film');













