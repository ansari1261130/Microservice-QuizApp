CREATE TABLE questions
(
    id BIGINT auto_increment
        primary key,
    category       VARCHAR(255) not null,
    difficulty_level VARCHAR(255) not null,
    question VARCHAR(255) not null,
    option1        VARCHAR(255) not null,
    option2        VARCHAR(255) not null,
    option3        VARCHAR(255) not null,
    option4        VARCHAR(255) not null,
    answer   VARCHAR(255) not null
);