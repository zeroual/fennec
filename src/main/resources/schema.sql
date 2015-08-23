DROP TABLE IF EXISTS POSTS;
DROP TABLE IF EXISTS UserConnection;
DROP TABLE IF EXISTS UserProfile;

CREATE TABLE POSTS (
  ID       BIGINT PRIMARY KEY AUTO_INCREMENT,
  USERNAME VARCHAR(50) NOT NULL,
  BODY  VARCHAR(50) NOT NULL,
  DATE  DATE
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

create table UserProfile (
  userId varchar(255) not null,
  email varchar(255),
  firstName varchar(255),
  lastName varchar(255),
  name  varchar(255),
  username varchar(255),
  primary key (userId));
create unique index UserProfilePK on UserProfile(userId);