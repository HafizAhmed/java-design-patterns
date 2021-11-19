USE UserSession;

CREATE TABLE userData
(
    userID   INT NOT NULL AUTO_INCREMENT,
    sessionID VARCHAR(128),
    preferenceID INT,
    userName VARCHAR(255),
    password VARCHAR(40),
    PRIMARY KEY (UserID),
    FOREIGN KEY (sessionID)
         REFERENCES Session(sessionID),
    FOREIGN KEY (preferenceID)
         REFERENCES Preferences(preferenceID)
);



CREATE TABLE Session
(
    sessionID VARCHAR(128) NOT NULL,
    creationTime TIMESTAMP,
    lastAccessTime TIMESTAMP,
    PRIMARY KEY (sessionID)
);

CREATE TABLE Preferences
(
    preferenceID INT NOT NULL AUTO_INCREMENT,
    fontSize INT,
    fontColor VARCHAR(28),
    PRIMARY KEY (preferenceID)
);

