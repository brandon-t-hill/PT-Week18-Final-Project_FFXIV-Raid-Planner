create database if not exists FFXIVPlanner;
use FFXIVPlanner;
drop table if exists userjobgear;
drop table if exists userjobs;
drop table if exists gear;
drop table if exists jobs;
drop table if exists users;
create table users(
  user_pk int unsigned NOT NULL AUTO_INCREMENT,
  username VARCHAR(32) NOT NULL,
  charname VARCHAR(64) NOT NULL,
  PRIMARY KEY (user_pk)
);
create table jobs(
  job_pk CHAR(3) NOT NULL,
  name VARCHAR(32) NOT NULL,
  role enum(
    'TANK',
    'HEALER',
    'MELEE',
    'RANGED_PHYSICAL',
    'RANGED_MAGIC'
  ) NOT NULL,
  PRIMARY KEY (job_pk)
);
create table gear(
  gear_pk int unsigned NOT NULL AUTO_INCREMENT,
  tier varchar(16) NOT NULL,
  item_level INT UNSIGNED NOT NULL,
  PRIMARY KEY (gear_pk)
);
create table userjobs(
  userjobs_pk int unsigned NOT NULL AUTO_INCREMENT,
  user_pk int unsigned NOT NULL,
  job_pk INT unsigned NOT NULL,
  PRIMARY KEY (userjobs_pk),
  FOREIGN KEY (user_pk) REFERENCES users (user_pk),
  FOREIGN KEY (job_pk) REFERENCES jobs (job_pk)
);
create table userjobgear(
  jobgear_pk int unsigned NOT NULL AUTO_INCREMENT,
  userjobs_pk int unsigned NOT NULL,
  gear_pk int unsigned NOT NULL,
  slot enum(
    'WEAPON',
    'OFFHAND',
    'HEAD',
    'CHEST',
    'GLOVES',
    'PANTS',
    'BOOTS',
    'EARRING',
    'NECKLACE',
    'BRACELET',
    'RING1',
    'RING2'
  ) NOT NULL,
  PRIMARY KEY (jobgear_pk),
  FOREIGN KEY (userjobs_pk) REFERENCES userjobs (userjobs_pk),
  FOREIGN KEY (gear_pk) REFERENCES gear (gear_pk)
);