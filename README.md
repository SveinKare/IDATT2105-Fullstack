## How to run
The backend requires a database with a user and history table. I has a user called "calculator_backend" as well, but root user would work fine. The following SQL code can be used to initialize these in MySQL:
``` 
CREATE TABLE user(
  username varchar(50) PRIMARY KEY,
  password varchar(255) NOT NULL);
```

```
CREATE TABLE history(
  username varchar(50),
  expression varchar(100),
  PRIMARY KEY(expression, username),
  FOREIGN KEY (username) REFERENCES user(username));
```

The DBMS used does shouldn't matter, but it needs to be set up in the application.properties file. I used a MySQL Docker container with port 3306 exposed, which is not included here. 
