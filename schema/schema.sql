-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bepj
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bepj` ;

-- -----------------------------------------------------
-- Schema bepj
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bepj` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bepj` ;

-- -----------------------------------------------------
-- Table `bepj`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bepj`.`customer` ;

CREATE TABLE IF NOT EXISTS `bepj`.`customer` (
  `customerId` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `companyName` VARCHAR(255) NOT NULL COMMENT '',
  `street` VARCHAR(255) NULL COMMENT '',
  `houseNr` VARCHAR(100) NULL COMMENT '',
  `postCode` VARCHAR(20) NULL COMMENT '',
  `city` VARCHAR(255) NULL COMMENT '',
  `contactFirstName` VARCHAR(100) NULL COMMENT '',
  `contactLastName` VARCHAR(100) NULL COMMENT '',
  `contactEmail` VARCHAR(255) NULL COMMENT '',
  `contactGender` VARCHAR(1) NULL COMMENT '',
  `contactPhoneNr` VARCHAR(50) NULL COMMENT '',
  `insDt` DATETIME NOT NULL COMMENT '',
  `insUserId` BIGINT NOT NULL COMMENT '',
  `updDt` DATETIME NULL COMMENT '',
  `updUserId` BIGINT NULL COMMENT '',
  PRIMARY KEY (`customerId`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bepj`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bepj`.`employee` ;

CREATE TABLE IF NOT EXISTS `bepj`.`employee` (
  `employeeId` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(100) NOT NULL COMMENT '',
  `password` VARCHAR(100) NULL COMMENT '',
  `firstname` VARCHAR(100) NULL COMMENT '',
  `lastname` VARCHAR(100) NULL COMMENT '',
  `email` VARCHAR(255) NULL COMMENT '',
  `lastLoginDt` DATETIME NULL COMMENT '',
  PRIMARY KEY (`employeeId`)  COMMENT '')
ENGINE = InnoDB;

CREATE UNIQUE INDEX `username_UNIQUE` ON `bepj`.`employee` (`username` ASC)  COMMENT '';

CREATE UNIQUE INDEX `ixEmployee1` ON `bepj`.`employee` (`username` ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table `bepj`.`offer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bepj`.`offer` ;

CREATE TABLE IF NOT EXISTS `bepj`.`offer` (
  `offerId` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `createdDt` DATETIME NOT NULL COMMENT '',
  `completedDt` DATETIME NULL COMMENT '',
  `status` VARCHAR(10) NOT NULL COMMENT '',
  `customerId` BIGINT NOT NULL COMMENT '',
  `employeeId` BIGINT NOT NULL COMMENT '',
  `insDt` DATETIME NOT NULL COMMENT '',
  `insUserId` BIGINT NOT NULL COMMENT '',
  `updDt` DATETIME NULL COMMENT '',
  `updUserId` BIGINT NULL COMMENT '',
  PRIMARY KEY (`offerId`)  COMMENT '',
  CONSTRAINT `fk_Offer_Customer1`
    FOREIGN KEY (`customerId`)
    REFERENCES `bepj`.`customer` (`customerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Offer_Employee1`
    FOREIGN KEY (`employeeId`)
    REFERENCES `bepj`.`employee` (`employeeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Offer_Customer1_idx` ON `bepj`.`offer` (`customerId` ASC)  COMMENT '';

CREATE INDEX `fk_Offer_Employee1_idx` ON `bepj`.`offer` (`employeeId` ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table `bepj`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bepj`.`category` ;

CREATE TABLE IF NOT EXISTS `bepj`.`category` (
  `categoryId` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `sortNr` INT NOT NULL COMMENT '',
  PRIMARY KEY (`categoryId`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bepj`.`article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bepj`.`article` ;

CREATE TABLE IF NOT EXISTS `bepj`.`article` (
  `articleId` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `price` DECIMAL(10,2) NOT NULL COMMENT '',
  `description` TEXT NULL COMMENT '',
  `categoryId` BIGINT NOT NULL COMMENT '',
  `insDt` DATETIME NOT NULL COMMENT '',
  `insUserId` BIGINT NOT NULL COMMENT '',
  `updDt` DATETIME NULL COMMENT '',
  `updUserId` BIGINT NULL COMMENT '',
  PRIMARY KEY (`articleId`)  COMMENT '',
  CONSTRAINT `fk_Article_Category1`
    FOREIGN KEY (`categoryId`)
    REFERENCES `bepj`.`category` (`categoryId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Article_Category1_idx` ON `bepj`.`article` (`categoryId` ASC)  COMMENT '';


-- -----------------------------------------------------
-- Table `bepj`.`offerPosition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bepj`.`offerPosition` ;

CREATE TABLE IF NOT EXISTS `bepj`.`offerPosition` (
  `offerPositionId` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `offerId` BIGINT NOT NULL COMMENT '',
  `posNr` INT NOT NULL COMMENT '',
  `price` DECIMAL(10,2) NOT NULL COMMENT '',
  `amount` INT NOT NULL COMMENT '',
  `articleId` BIGINT NOT NULL COMMENT '',
  `insDt` DATETIME NOT NULL COMMENT '',
  `insUserId` BIGINT NOT NULL COMMENT '',
  `updDt` DATETIME NULL COMMENT '',
  `updUserId` BIGINT NULL COMMENT '',
  PRIMARY KEY (`offerPositionId`)  COMMENT '',
  CONSTRAINT `fk_OfferPosition_Offer`
    FOREIGN KEY (`offerId`)
    REFERENCES `bepj`.`offer` (`offerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OfferPosition_Article1`
    FOREIGN KEY (`articleId`)
    REFERENCES `bepj`.`article` (`articleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_OfferPosition_Offer_idx` ON `bepj`.`offerPosition` (`offerId` ASC)  COMMENT '';

CREATE UNIQUE INDEX `ixOfferPosition1` ON `bepj`.`offerPosition` (`offerId` ASC, `posNr` ASC)  COMMENT '';

CREATE INDEX `fk_OfferPosition_Article1_idx` ON `bepj`.`offerPosition` (`articleId` ASC)  COMMENT '';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
