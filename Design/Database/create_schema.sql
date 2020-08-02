-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sharing_file_system
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sharing_file_system` ;

-- -----------------------------------------------------
-- Schema sharing_file_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sharing_file_system` DEFAULT CHARACTER SET utf8 ;
USE `sharing_file_system` ;

-- -----------------------------------------------------
-- Table `sharing_file_system`.`action`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sharing_file_system`.`action` ;

CREATE TABLE IF NOT EXISTS `sharing_file_system`.`action` (
  `ID` INT(4) NOT NULL AUTO_INCREMENT,
  `Code` CHAR(6) NOT NULL,
  `Name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sharing_file_system`.`document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sharing_file_system`.`document` ;

CREATE TABLE IF NOT EXISTS `sharing_file_system`.`document` (
  `ID` INT(4) NOT NULL AUTO_INCREMENT,
  `FileName` VARCHAR(255) NOT NULL,
  `Title` VARCHAR(255) NOT NULL,
  `Extension` CHAR(10) NOT NULL,
  `Size` INT(4) NULL DEFAULT NULL,
  `Description` VARCHAR(255) NULL DEFAULT NULL,
  `Is_Active` CHAR(1) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sharing_file_system`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sharing_file_system`.`user` ;

CREATE TABLE IF NOT EXISTS `sharing_file_system`.`user` (
  `UserName` VARCHAR(100) NOT NULL,
  `Password` CHAR(255) NOT NULL,
  `Is_Active` CHAR(1) NOT NULL,
  PRIMARY KEY (`UserName`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sharing_file_system`.`action_document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sharing_file_system`.`action_document` ;

CREATE TABLE IF NOT EXISTS `sharing_file_system`.`action_document` (
  `ID` INT(4) NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(100) NOT NULL,
  `DocID` INT(4) NOT NULL,
  `ActionID` INT(4) NOT NULL,
  `Note` VARCHAR(255) NOT NULL,
  `ModifyOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sharing_file_system`.`group_department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sharing_file_system`.`group_department` ;

CREATE TABLE IF NOT EXISTS `sharing_file_system`.`group_department` (
  `ID` INT(4) NOT NULL AUTO_INCREMENT,
  `Code` CHAR(20) NOT NULL,
  `Name` VARCHAR(255) NOT NULL,
  `Description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sharing_file_system`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sharing_file_system`.`employee` ;

CREATE TABLE IF NOT EXISTS `sharing_file_system`.`employee` (
  `ID` INT(4) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(100) NOT NULL,
  `Email` VARCHAR(255) NOT NULL,
  `Phone` CHAR(10) NOT NULL,
  `Is_Working` CHAR(1) NOT NULL,
  `GroupID` INT(4) NOT NULL,
  `UserName` VARCHAR(100) NOT NULL,
  `Manager` INT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sharing_file_system`.`hibernate_sequence`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sharing_file_system`.`hibernate_sequence` ;

CREATE TABLE IF NOT EXISTS `sharing_file_system`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sharing_file_system`.`sharing_document_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sharing_file_system`.`sharing_document_group` ;

CREATE TABLE IF NOT EXISTS `sharing_file_system`.`sharing_document_group` (
  `ID` INT(4) NOT NULL AUTO_INCREMENT,
  `GroupID` INT(4) NOT NULL,
  `DocID` INT(4) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
