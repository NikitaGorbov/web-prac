DROP DATABASE IF EXISTS webprac;

CREATE DATABASE webprac;

\connect webprac

CREATE TABLE Company (
   comp_id SERIAL PRIMARY KEY,
   comp_name CHARACTER VARYING(32) NOT NULL,
   description text,
   location CHARACTER VARYING(32)
   );

CREATE TABLE Position (
    pos_id SERIAL PRIMARY KEY,
    position_name CHARACTER VARYING(32) NOT NULL
    );

CREATE TABLE Vacancy (
   vac_id SERIAL PRIMARY KEY,
   pos_id INTEGER REFERENCES Position ON DELETE RESTRICT,
   comp_id INTEGER REFERENCES Company ON DELETE CASCADE,
   requirements text
   );

CREATE TABLE Education (
    ed_id SERIAL PRIMARY KEY,
    name CHARACTER VARYING(32)
    );

CREATE TABLE Applicant(
    appl_id SERIAL PRIMARY KEY,
    ed_id INTEGER REFERENCES Education ON DELETE RESTRICT,
    appl_name CHARACTER VARYING(32) NOT NULL,
    appl_status INTEGER,     --- TODO: create table Status
    appl_address CHARACTER VARYING(32)
);

CREATE TABLE CV (
    cv_id SERIAL PRIMARY KEY,
    appl_id INTEGER REFERENCES Applicant ON DELETE CASCADE,
    work_exp INTEGER,
    objective INTEGER REFERENCES Position (pos_id) ON DELETE RESTRICT
);

CREATE TABLE Previous_job_record (
    previous_job_record_id SERIAL PRIMARY KEY,
    appl_id INTEGER REFERENCES Applicant ON DELETE CASCADE,
    comp_id INTEGER REFERENCES Company ON DELETE RESTRICT,
    pos_id INTEGER REFERENCES Position ON DELETE RESTRICT,
    duration INTEGER
);