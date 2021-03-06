CREATE TABLE studentAsisgnment (ID INTEGER AUTO_INCREMENT NOT NULL, ASSIGNEDON DATETIME, DESCRIPTION VARCHAR(255), DUEON DATETIME, TITLE VARCHAR(255), COURSE_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE ACHIEVEMENT (ID INTEGER AUTO_INCREMENT NOT NULL, NAME VARCHAR(255), POINTS INTEGER, RESULTTITLE_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE ACHIEVEMENTRECORD (ID INTEGER AUTO_INCREMENT NOT NULL, COMPLETIONTIME DATETIME, ACHIEVEMENT_ID INTEGER, USER_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE CAUSUALRELATION (ID INTEGER AUTO_INCREMENT NOT NULL, CARDINALITY INTEGER, ACHIEVEMENTCAUSE_ID INTEGER, ACHIEVEMENTRESULT_ID INTEGER, TASKCAUSE_ID INTEGER, TASKRESULT_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE COURSE (ID INTEGER AUTO_INCREMENT NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE QUEST (ID INTEGER AUTO_INCREMENT NOT NULL, DESCRIPTION VARCHAR(255), EXPERIENCEPOINTS INTEGER, NAME VARCHAR(255), ASSIGNMENT_ID INTEGER, PARENT_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE LEVEL (ID INTEGER AUTO_INCREMENT NOT NULL, LEVEL INTEGER, XPMAX INTEGER, XPREQUIRED INTEGER, PRIMARY KEY (ID))
CREATE TABLE TITLE (ID INTEGER AUTO_INCREMENT NOT NULL, POSITION VARCHAR(255), TITLE VARCHAR(255), COMESFROM_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE LOGACTION (ID INTEGER AUTO_INCREMENT NOT NULL, log_key VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE LOGENTRY (ID INTEGER AUTO_INCREMENT NOT NULL, CREATEDAT DATETIME, IP BIGINT, PARAMS VARCHAR(255), ACTION_ID INTEGER, CREATEDBY_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE QUESTPROGRESS (ID INTEGER AUTO_INCREMENT NOT NULL, COMPLETED TINYINT(1) default 0, UPDATED DATETIME, USER_ID INTEGER, QUEST_ID INTEGER, TASK_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE TASK (ID INTEGER AUTO_INCREMENT NOT NULL, DESCRIPTION VARCHAR(255), NAME VARCHAR(255), TYPE VARCHAR(255), PARENT_ID INTEGER, QUEST_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE h_user (ID INTEGER AUTO_INCREMENT NOT NULL, ACHIEVEMENTPOINTS INTEGER, EMAIL VARCHAR(255), FACEBOOKSESSIONKEY VARCHAR(255), FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), PASSWORD VARCHAR(255), STATUS VARCHAR(255), THUMBNAIL LONGBLOB, XP INTEGER, ACTIVETITLE_ID INTEGER, LEVEL_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE ENROLLMENT (ID INTEGER AUTO_INCREMENT NOT NULL, TYPE VARCHAR(255), COURSE_ID INTEGER, USER_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE h_user_TITLE (User_ID INTEGER NOT NULL, titles_ID INTEGER NOT NULL, PRIMARY KEY (User_ID, titles_ID))
ALTER TABLE studentAsisgnment ADD CONSTRAINT FK_studentAsisgnment_COURSE_ID FOREIGN KEY (COURSE_ID) REFERENCES COURSE (ID)
ALTER TABLE ACHIEVEMENT ADD CONSTRAINT FK_ACHIEVEMENT_RESULTTITLE_ID FOREIGN KEY (RESULTTITLE_ID) REFERENCES TITLE (ID)
ALTER TABLE ACHIEVEMENTRECORD ADD CONSTRAINT FK_ACHIEVEMENTRECORD_USER_ID FOREIGN KEY (USER_ID) REFERENCES h_user (ID)
ALTER TABLE ACHIEVEMENTRECORD ADD CONSTRAINT FK_ACHIEVEMENTRECORD_ACHIEVEMENT_ID FOREIGN KEY (ACHIEVEMENT_ID) REFERENCES ACHIEVEMENT (ID)
ALTER TABLE CAUSUALRELATION ADD CONSTRAINT FK_CAUSUALRELATION_ACHIEVEMENTCAUSE_ID FOREIGN KEY (ACHIEVEMENTCAUSE_ID) REFERENCES ACHIEVEMENT (ID)
ALTER TABLE CAUSUALRELATION ADD CONSTRAINT FK_CAUSUALRELATION_TASKRESULT_ID FOREIGN KEY (TASKRESULT_ID) REFERENCES TASK (ID)
ALTER TABLE CAUSUALRELATION ADD CONSTRAINT FK_CAUSUALRELATION_TASKCAUSE_ID FOREIGN KEY (TASKCAUSE_ID) REFERENCES TASK (ID)
ALTER TABLE CAUSUALRELATION ADD CONSTRAINT FK_CAUSUALRELATION_ACHIEVEMENTRESULT_ID FOREIGN KEY (ACHIEVEMENTRESULT_ID) REFERENCES ACHIEVEMENT (ID)
ALTER TABLE QUEST ADD CONSTRAINT FK_QUEST_ASSIGNMENT_ID FOREIGN KEY (ASSIGNMENT_ID) REFERENCES studentAsisgnment (ID)
ALTER TABLE QUEST ADD CONSTRAINT FK_QUEST_PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES QUEST (ID)
ALTER TABLE TITLE ADD CONSTRAINT FK_TITLE_COMESFROM_ID FOREIGN KEY (COMESFROM_ID) REFERENCES ACHIEVEMENT (ID)
ALTER TABLE LOGENTRY ADD CONSTRAINT FK_LOGENTRY_CREATEDBY_ID FOREIGN KEY (CREATEDBY_ID) REFERENCES h_user (ID)
ALTER TABLE LOGENTRY ADD CONSTRAINT FK_LOGENTRY_ACTION_ID FOREIGN KEY (ACTION_ID) REFERENCES LOGACTION (ID)
ALTER TABLE QUESTPROGRESS ADD CONSTRAINT FK_QUESTPROGRESS_TASK_ID FOREIGN KEY (TASK_ID) REFERENCES TASK (ID)
ALTER TABLE QUESTPROGRESS ADD CONSTRAINT FK_QUESTPROGRESS_QUEST_ID FOREIGN KEY (QUEST_ID) REFERENCES QUEST (ID)
ALTER TABLE QUESTPROGRESS ADD CONSTRAINT FK_QUESTPROGRESS_USER_ID FOREIGN KEY (USER_ID) REFERENCES h_user (ID)
ALTER TABLE TASK ADD CONSTRAINT FK_TASK_QUEST_ID FOREIGN KEY (QUEST_ID) REFERENCES QUEST (ID)
ALTER TABLE TASK ADD CONSTRAINT FK_TASK_PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES TASK (ID)
ALTER TABLE h_user ADD CONSTRAINT FK_h_user_LEVEL_ID FOREIGN KEY (LEVEL_ID) REFERENCES LEVEL (ID)
ALTER TABLE h_user ADD CONSTRAINT FK_h_user_ACTIVETITLE_ID FOREIGN KEY (ACTIVETITLE_ID) REFERENCES TITLE (ID)
ALTER TABLE ENROLLMENT ADD CONSTRAINT FK_ENROLLMENT_USER_ID FOREIGN KEY (USER_ID) REFERENCES h_user (ID)
ALTER TABLE ENROLLMENT ADD CONSTRAINT FK_ENROLLMENT_COURSE_ID FOREIGN KEY (COURSE_ID) REFERENCES COURSE (ID)
ALTER TABLE h_user_TITLE ADD CONSTRAINT FK_h_user_TITLE_User_ID FOREIGN KEY (User_ID) REFERENCES h_user (ID)
ALTER TABLE h_user_TITLE ADD CONSTRAINT FK_h_user_TITLE_titles_ID FOREIGN KEY (titles_ID) REFERENCES TITLE (ID)
