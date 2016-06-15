CREATE TABLE Session (
    id integer AUTO_INCREMENT PRIMARY KEY,
    session_date date  
);

CREATE TABLE Running (
    id integer AUTO_INCREMENT PRIMARY KEY,
    distance integer, 
    duration integer NOT NULL,
    notes varchar(200),
    FOREIGN KEY (session_id) REFERENCES Session(id)
);

CREATE TABLE Cycling (
    id integer AUTO_INCREMENT PRIMARY KEY,
    distance integer, 
    duration integer NOT NULL,
    notes varchar(200),
    FOREIGN KEY (session_id) REFERENCES Session(id)
);
