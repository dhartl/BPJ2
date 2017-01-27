-- -----------------------------------------------------
-- Table customer
-- -----------------------------------------------------
DROP TABLE IF EXISTS customer ;

CREATE TABLE IF NOT EXISTS customer (
  customerId BIGINT NOT NULL AUTO_INCREMENT ,
  companyName VARCHAR(255) NOT NULL ,
  street VARCHAR(255) NULL ,
  houseNr VARCHAR(100) NULL ,
  postCode VARCHAR(20) NULL ,
  city VARCHAR(255) NULL ,
  contactFirstName VARCHAR(100) NULL ,
  contactLastName VARCHAR(100) NULL ,
  contactEmail VARCHAR(255) NULL ,
  contactGender VARCHAR(1) NULL ,
  contactPhoneNr VARCHAR(50) NULL ,
  insDt DATETIME NOT NULL ,
  insUserId BIGINT NOT NULL ,
  updDt DATETIME NULL ,
  updUserId BIGINT NULL ,
  PRIMARY KEY (customerId)  )
;


-- -----------------------------------------------------
-- Table employee
-- -----------------------------------------------------
DROP TABLE IF EXISTS employee ;

CREATE TABLE IF NOT EXISTS employee (
  employeeId BIGINT NOT NULL AUTO_INCREMENT ,
  username VARCHAR(100) NOT NULL ,
  password VARCHAR(100) NULL ,
  firstname VARCHAR(100) NULL ,
  lastname VARCHAR(100) NULL ,
  email VARCHAR(255) NULL ,
  lastLoginDt DATETIME NULL ,
  PRIMARY KEY (employeeId)  )
;

CREATE UNIQUE INDEX username_UNIQUE ON employee (username ASC)  ;

CREATE UNIQUE INDEX ixEmployee1 ON employee (username ASC)  ;


-- -----------------------------------------------------
-- Table offer
-- -----------------------------------------------------
DROP TABLE IF EXISTS offer ;

CREATE TABLE IF NOT EXISTS offer (
  offerId BIGINT NOT NULL AUTO_INCREMENT ,
  createdDt DATETIME NOT NULL ,
  completedDt DATETIME NULL ,
  status VARCHAR(10) NOT NULL ,
  customerId BIGINT NOT NULL ,
  employeeId BIGINT NOT NULL ,
  insDt DATETIME NOT NULL ,
  insUserId BIGINT NOT NULL ,
  updDt DATETIME NULL ,
  updUserId BIGINT NULL ,
  PRIMARY KEY (offerId)  ,
  CONSTRAINT fk_Offer_Customer1
    FOREIGN KEY (customerId)
    REFERENCES customer (customerId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Offer_Employee1
    FOREIGN KEY (employeeId)
    REFERENCES employee (employeeId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Offer_Customer1_idx ON offer (customerId ASC)  ;

CREATE INDEX fk_Offer_Employee1_idx ON offer (employeeId ASC)  ;


-- -----------------------------------------------------
-- Table category
-- -----------------------------------------------------
DROP TABLE IF EXISTS category ;

CREATE TABLE IF NOT EXISTS category (
  categoryId BIGINT NOT NULL AUTO_INCREMENT ,
  name VARCHAR(100) NOT NULL ,
  sortNr INT NOT NULL ,
  PRIMARY KEY (categoryId)  )
;


-- -----------------------------------------------------
-- Table article
-- -----------------------------------------------------
DROP TABLE IF EXISTS article ;

CREATE TABLE IF NOT EXISTS article (
  articleId BIGINT NOT NULL AUTO_INCREMENT ,
  name VARCHAR(100) NOT NULL ,
  price DECIMAL(10,2) NOT NULL ,
  description VARCHAR(100) NULL ,
  categoryId BIGINT NOT NULL ,
  insDt DATETIME NOT NULL ,
  insUserId BIGINT NOT NULL ,
  updDt DATETIME NULL ,
  updUserId BIGINT NULL ,
  PRIMARY KEY (articleId)  ,
  CONSTRAINT fk_Article_Category1
    FOREIGN KEY (categoryId)
    REFERENCES category (categoryId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_Article_Category1_idx ON article (categoryId ASC)  ;


-- -----------------------------------------------------
-- Table offerPosition
-- -----------------------------------------------------
DROP TABLE IF EXISTS offerPosition ;

CREATE TABLE IF NOT EXISTS offerPosition (
  offerPositionId BIGINT NOT NULL AUTO_INCREMENT ,
  offerId BIGINT NOT NULL ,
  posNr INT NOT NULL ,
  price DECIMAL(10,2) NOT NULL ,
  amount INT NOT NULL ,
  articleId BIGINT NOT NULL ,
  insDt DATETIME NOT NULL ,
  insUserId BIGINT NOT NULL ,
  updDt DATETIME NULL ,
  updUserId BIGINT NULL ,
  PRIMARY KEY (offerPositionId)  ,
  CONSTRAINT fk_OfferPosition_Offer
    FOREIGN KEY (offerId)
    REFERENCES offer (offerId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_OfferPosition_Article1
    FOREIGN KEY (articleId)
    REFERENCES article (articleId)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

CREATE INDEX fk_OfferPosition_Offer_idx ON offerPosition (offerId ASC)  ;

CREATE UNIQUE INDEX ixOfferPosition1 ON offerPosition (offerId ASC, posNr ASC)  ;

CREATE INDEX fk_OfferPosition_Article1_idx ON offerPosition (articleId ASC)  ;