-- Users
INSERT INTO users(username, charname)
VALUES('Beef', 'Beef Skillet');
INSERT INTO users(username, charname)
VALUES('Chrono', 'Chrono Elric');
INSERT INTO users(username, charname)
VALUES('Xabre', 'Xabre');
INSERT INTO users(username, charname)
VALUES('Erem', 'Eremita Celesturia');
INSERT INTO users(username, charname)
VALUES('Makoto', 'Makoto');
INSERT INTO users(username, charname)
VALUES('Teo', 'Teo Kroll');
INSERT INTO users(username, charname)
VALUES('Skylar', 'Skylar');
INSERT INTO users(username, charname)
VALUES('Seamus', 'Seamus');
-- Jobs Lookup
-- Tanks
INSERT INTO jobs
VALUES('PLD', 'PALADIN', 'TANK');
INSERT INTO jobs
VALUES('WAR', 'WARRIOR', 'TANK');
INSERT INTO jobs
VALUES('DRK', 'DARK KNIGHT', 'TANK');
INSERT INTO jobs
VALUES('GNB', 'GUNBREAKER', 'TANK');
-- Healers
INSERT INTO jobs
VALUES('WHM', 'WHITE MAGE', 'HEALER');
INSERT INTO jobs
VALUES('SCH', 'SCHOLAR', 'HEALER');
INSERT INTO jobs
VALUES('AST', 'ASTROLOGIAN', 'HEALER');
INSERT INTO jobs
VALUES('SGE', 'SAGE', 'HEALER');
-- Melee
INSERT INTO jobs
VALUES('DRG', 'DRAGOON', 'MELEE');
INSERT INTO jobs
VALUES('RPR', 'REAPER', 'MELEE');
INSERT INTO jobs
VALUES('MNK', 'MONK', 'MELEE');
INSERT INTO jobs
VALUES('SAM', 'SAMURAI', 'MELEE');
INSERT INTO jobs
VALUES('NIN', 'NINJA', 'MELEE');
-- Ranged Physical
INSERT INTO jobs
VALUES('BRD', 'BARD', 'RANGED_PHYSICAL');
INSERT INTO jobs
VALUES('MCH', 'MACHINIST', 'RANGED_PHYSICAL');
INSERT INTO jobs
VALUES('DNC', 'DANCER', 'RANGED_PHYSICAL');
-- Ranged Magical
INSERT INTO jobs
VALUES('BLM', 'BLACK MAGE', 'RANGED_MAGIC');
INSERT INTO jobs
VALUES('SMN', 'SUMMONER', 'RANGED_MAGIC');
INSERT INTO jobs
VALUES('RDM', 'RED MAGE', 'RANGED_MAGIC');
-- Gear Lookup
INSERT INTO gear(tier, item_level)
VALUES('SAVAGE', 600);
INSERT INTO gear(tier, item_level)
VALUES('TOME UP', 600);
INSERT INTO gear(tier, item_level)
VALUES('CATCHUP', 595);
INSERT INTO gear(tier, item_level)
VALUES('TOME', 590);
INSERT INTO gear(tier, item_level)
VALUES('EW RELIC', 580);
INSERT INTO gear(tier, item_level)
VALUES('CRAFTED', 580);
INSERT INTO gear(tier, item_level)
VALUES('PREP', 580);
-- User Jobs
INSERT INTO userjobs(user_pk, job_pk)
VALUES(
    (
      SELECT user_pk
      FROM users
      WHERE username = 'Beef'
    ),
    (
      SELECT job_pk
      FROM jobs
      WHERE name = 'PALADIN'
    )
  );
INSERT INTO userjobs(user_pk, job_pk)
VALUES(
    (
      SELECT user_pk
      FROM users
      WHERE username = 'Chrono'
    ),
    (
      SELECT job_pk
      FROM jobs
      WHERE name = 'WARRIOR'
    )
  );
INSERT INTO userjobs(user_pk, job_pk)
VALUES(
    (
      SELECT user_pk
      FROM users
      WHERE username = 'Xabre'
    ),
    (
      SELECT job_pk
      FROM jobs
      WHERE name = 'SAGE'
    )
  );
INSERT INTO userjobs(user_pk, job_pk)
VALUES(
    (
      SELECT user_pk
      FROM users
      WHERE username = 'Erem'
    ),
    (
      SELECT job_pk
      FROM jobs
      WHERE name = 'ASTROLOGIAN'
    )
  );
-- Job Gear
INSERT INTO userjobgear(userjobs_pk, gear_pk, slot)
VALUES(
    (
      SELECT userjobs_pk
      FROM userjobs
      WHERE user_pk = (
          SELECT user_pk
          FROM users
          WHERE username = 'Beef'
        )
    ),
    (
      SELECT gear_pk
      FROM gear
      WHERE tier = 'TOME'
    ),
    'WEAPON'
  );