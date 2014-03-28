CREATE TABLE IF NOT EXISTS priority (
    id INT(2),
    name CHAR(16),
    description CHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS rank (
    id INT(2),
    name CHAR(16),
    max_priority INT(2) REFERENCES priority (id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS staff (
    id INT,
    name CHAR(16),
    rank INT REFERENCES rank (id),
    PRIMARY KEY (id)
        
);

CREATE TABLE IF NOT EXISTS status (
    id INT(2),
    name CHAR(16),
    description CHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ticket (
    id INT AUTO_INCREMENT,
    submitter CHAR(16),
    message CHAR(255),
    date TIMESTAMP,
    priority INT DEFAULT 1,
    status INT DEFAULT 1 REFERENCES status (id),
    staff INT DEFAULT NULL  REFERENCES staff (id),
    world CHAR(32) NOT NULL,
    locx FLOAT NOT NULL,
    locy FLOAT NOT NULL,
    locz FLOAT NOT NULL,
    pitch FLOAT NOT NULL,
    yaw FLOAT NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS comment (
    ticket INT REFERENCES ticket (id),
    commenter CHAR(16),
    comment CHAR(255),
    date TIMESTAMP
);