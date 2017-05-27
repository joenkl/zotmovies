DROP DATABASE IF EXISTS moviedb;
CREATE DATABASE moviedb;
USE moviedb;

CREATE TABLE IF NOT EXISTS user_stopword(
	value varchar(30)
    )engine = innodb;
    
SET GLOBAL innodb_ft_server_stopword_table="moviedb/user_stopword";
DROP TABLE IF EXISTS `sales`;
DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `creditcards`;
DROP TABLE IF EXISTS `genres_in_movies`;
DROP TABLE IF EXISTS `genres`;
DROP TABLE IF EXISTS `stars_in_movies`;
DROP TABLE IF EXISTS `stars`;
DROP TABLE IF EXISTS `movies`;

SET @@SESSION.innodb_ft_enable_stopword = 'ON';
CREATE TABLE movies (
    id INTEGER NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    year INTEGER NOT NULL,
    director VARCHAR(100) NOT NULL,
    banner_url VARCHAR(200),
    trailer_url VARCHAR(200),
    PRIMARY KEY (id),
    FULLTEXT(title)
) ENGINE=InnoDB;


CREATE TABLE stars (
    id INTEGER NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    dob DATE,
    photo_url VARCHAR(200),
    PRIMARY KEY (id)
) ENGINE=InnoDB;


CREATE TABLE stars_in_movies (
    star_id INTEGER NOT NULL,
    movie_id INTEGER NOT NULL,
    PRIMARY KEY (star_id , movie_id),
    FOREIGN KEY (star_id)
        REFERENCES stars (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (movie_id)
        REFERENCES movies (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE genres (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE genres_in_movies (
    genre_id INTEGER NOT NULL,
    movie_id INTEGER NOT NULL,
    PRIMARY KEY (genre_id , movie_id),
    FOREIGN KEY (genre_id)
        REFERENCES genres (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (movie_id)
        REFERENCES movies (id)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE creditcards (
    id VARCHAR(20) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    expiration DATE NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE customers (
    id INTEGER NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    cc_id VARCHAR(20) NOT NULL,
    address VARCHAR(200) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cc_id)
        REFERENCES creditcards (id)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;



CREATE TABLE sales (
    id INTEGER NOT NULL AUTO_INCREMENT,
    customer_id INTEGER NOT NULL,
    movie_id INTEGER NOT NULL,
    sale_date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id)
        REFERENCES customers (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (movie_id)
        REFERENCES movies (id)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;