CREATE TABLE investors
(
    id   varchar NOT NULL,
    name varchar NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE investor_profiles
(
    id          varchar NOT NULL,
    isVip       BOOLEAN,
    score       INT,
    ref_link    varchar,
    investor_id varchar NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (investor_id) REFERENCES investors (id)
);