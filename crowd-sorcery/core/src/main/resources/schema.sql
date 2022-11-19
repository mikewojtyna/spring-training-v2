CREATE TABLE investors
(
    id         varchar NOT NULL,
    name       varchar NOT NULL,
    is_default BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE investor_profiles
(
    id          varchar NOT NULL,
    isVip       BOOLEAN,
    score       INT,
    ref_link    varchar,
    investor_id varchar NOT NULL,
    is_default  BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (investor_id) REFERENCES investors (id)
);

CREATE TABLE borrowers
(
    id         varchar NOT NULL,
    name       varchar NOT NULL,
    is_default BOOLEAN,
    PRIMARY KEY (id)
);