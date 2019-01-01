CREATE DATABASE vuekot_events;
\c vuekot_events

CREATE TABLE events (
  event_name varchar(255),
  event_type varchar(3),
  event_contact_email varchar(255)
);