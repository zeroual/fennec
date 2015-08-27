DROP TABLE IF EXISTS POST;
DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS UserConnection;

CREATE TABLE POST (
  ID       BIGINT PRIMARY KEY AUTO_INCREMENT,
  POSTER_ID VARCHAR(50) NOT NULL,
  BODY  VARCHAR(50) NOT NULL,
  DATE  DATE
);
CREATE TABLE  MEMBER(
  ID varchar(255) PRIMARY KEY,
  DISPLAY_NAME varchar(255)
);

create table UserConnection (
  userId varchar(255) not null,
  providerId varchar(255) not null,
  providerUserId varchar(255),
  rank int not null,
  displayName varchar(255),
  profileUrl varchar(512),
  imageUrl varchar(512),
  accessToken varchar(1024) not null,
  secret varchar(255),
  refreshToken varchar(255),
  expireTime bigint,
  primary key (userId, providerId, providerUserId));
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);

