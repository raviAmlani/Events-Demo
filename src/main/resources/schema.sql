
CREATE TABLE IF NOT EXISTS Events (
 id VARCHAR(45) NOT NULL,
 duration INT,
 type VARCHAR(45) DEFAULT NULL,
 host VARCHAR(45) DEFAULT NULL,
 alert BOOLEAN,
 PRIMARY KEY (id)
);