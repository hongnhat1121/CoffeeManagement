Skip to content
Search or jump to…
Pull requests
Issues
Marketplace
Explore
 
@hongnhat1121 
hongnhat1121
/
KTPM-CoffeeManagementApp
Private
Code
Issues
Pull requests
Actions
Projects
Security
Insights
Settings
KTPM-CoffeeManagementApp/Other/UMLDiagrams.sql
@hongnhat1121
hongnhat1121 upload pojo
Latest commit 92c119d yesterday
 History
 1 contributor
272 lines (232 sloc)  10.2 KB
   
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema coffeemanagementdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema coffeemanagementdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `coffeemanagementdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `coffeemanagementdb` ;

-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`actives`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`actives` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`actives` (
  `ActiveID` INT NOT NULL AUTO_INCREMENT,
  `ActiveName` VARCHAR(15) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  PRIMARY KEY (`ActiveID`),
  UNIQUE INDEX `ActiveID_UNIQUE` (`ActiveID` ASC) VISIBLE,
  UNIQUE INDEX `ActiveName_UNIQUE` (`ActiveName` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`roles` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`roles` (
  `RoleID` INT NOT NULL AUTO_INCREMENT,
  `RoleName` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`RoleID`),
  UNIQUE INDEX `UserroleID_UNIQUE` (`RoleID` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`accounts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`accounts` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`accounts` (
  `AccountID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `Password` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `ActiveID` INT NOT NULL,
  `RoleID` INT NOT NULL,
  PRIMARY KEY (`AccountID`),
  UNIQUE INDEX `AccountID_UNIQUE` (`AccountID` ASC) VISIBLE,
  INDEX `fk_accounts_roles_idx` (`RoleID` ASC) VISIBLE,
  INDEX `fk_accounts_actives_idx` (`ActiveID` ASC) VISIBLE,
  CONSTRAINT `fk_accounts_actives`
    FOREIGN KEY (`ActiveID`)
    REFERENCES `coffeemanagementdb`.`actives` (`ActiveID`),
  CONSTRAINT `fk_accounts_roles`
    FOREIGN KEY (`RoleID`)
    REFERENCES `coffeemanagementdb`.`roles` (`RoleID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`categories` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`categories` (
  `CategoryID` INT NOT NULL AUTO_INCREMENT,
  `CategoryName` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`CategoryID`),
  UNIQUE INDEX `CategoryID_UNIQUE` (`CategoryID` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`genders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`genders` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`genders` (
  `GenderID` INT NOT NULL AUTO_INCREMENT,
  `GenderName` VARCHAR(10) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`GenderID`),
  UNIQUE INDEX `GenderID_UNIQUE` (`GenderID` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`states`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`states` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`states` (
  `StateID` INT NOT NULL AUTO_INCREMENT,
  `StateName` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`StateID`),
  UNIQUE INDEX `StateID_UNIQUE` (`StateID` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`employees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`employees` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`employees` (
  `EmployeeID` INT NOT NULL AUTO_INCREMENT,
  `LastName` VARCHAR(45) NULL DEFAULT NULL,
  `FirstName` VARCHAR(45) NULL DEFAULT NULL,
  `GenderID` INT NOT NULL,
  `HireDate` DATE NOT NULL,
  `Address` VARCHAR(45) NULL DEFAULT NULL,
  `Phone` VARCHAR(45) NULL DEFAULT NULL,
  `StateID` INT NOT NULL,
  `AccountID` INT NOT NULL DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  UNIQUE INDEX `EmployeeID_UNIQUE` (`EmployeeID` ASC) VISIBLE,
  INDEX `fk_employees_states_idx` (`StateID` ASC) VISIBLE,
  INDEX `fk_employees_genders_idx` (`GenderID` ASC) VISIBLE,
  INDEX `fk_employees_accounts_idx` (`AccountID` ASC) VISIBLE,
  CONSTRAINT `fk_employees_accounts`
    FOREIGN KEY (`AccountID`)
    REFERENCES `coffeemanagementdb`.`accounts` (`AccountID`),
  CONSTRAINT `fk_employees_genders`
    FOREIGN KEY (`GenderID`)
    REFERENCES `coffeemanagementdb`.`genders` (`GenderID`),
  CONSTRAINT `fk_employees_states`
    FOREIGN KEY (`StateID`)
    REFERENCES `coffeemanagementdb`.`states` (`StateID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`status` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`status` (
  `StatusID` INT NOT NULL AUTO_INCREMENT,
  `StatusName` VARCHAR(25) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  PRIMARY KEY (`StatusID`),
  UNIQUE INDEX `StatusID_UNIQUE` (`StatusID` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`tables`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`tables` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`tables` (
  `TableID` INT NOT NULL AUTO_INCREMENT,
  `TableName` VARCHAR(20) CHARACTER SET 'utf8' NOT NULL,
  `Capacity` INT NULL DEFAULT NULL,
  `StatusID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`TableID`),
  UNIQUE INDEX `TableID_UNIQUE` (`TableID` ASC) VISIBLE,
  INDEX `fk_tables_status_idx` (`StatusID` ASC) VISIBLE,
  CONSTRAINT `fk_tables_status`
    FOREIGN KEY (`StatusID`)
    REFERENCES `coffeemanagementdb`.`status` (`StatusID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`orders` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`orders` (
  `OrderID` INT NOT NULL AUTO_INCREMENT,
  `OrderDate` DATE NOT NULL,
  `Total` DECIMAL(10,0) NOT NULL,
  `EmployeeID` INT NOT NULL,
  `TableID` INT NOT NULL,
  `Payment` INT NULL DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  UNIQUE INDEX `OrderID_UNIQUE` (`OrderID` ASC) VISIBLE,
  INDEX `fk_orders_employees_idx` (`EmployeeID` ASC) VISIBLE,
  INDEX `fk_orders_tables_idx` (`TableID` ASC) VISIBLE,
  CONSTRAINT `fk_orders_employees`
    FOREIGN KEY (`EmployeeID`)
    REFERENCES `coffeemanagementdb`.`employees` (`EmployeeID`),
  CONSTRAINT `fk_orders_tables`
    FOREIGN KEY (`TableID`)
    REFERENCES `coffeemanagementdb`.`tables` (`TableID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`products` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`products` (
  `ProductID` INT NOT NULL AUTO_INCREMENT,
  `ProductName` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `UnitPrice` DECIMAL(10,0) NULL DEFAULT NULL,
  `CategoryID` INT NOT NULL,
  PRIMARY KEY (`ProductID`),
  UNIQUE INDEX `ProductID_UNIQUE` (`ProductID` ASC) VISIBLE,
  INDEX `fk_products_catagories_idx` (`CategoryID` ASC) VISIBLE,
  CONSTRAINT `fk_products_catagories`
    FOREIGN KEY (`CategoryID`)
    REFERENCES `coffeemanagementdb`.`categories` (`CategoryID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `coffeemanagementdb`.`orderdetails`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `coffeemanagementdb`.`orderdetails` ;

CREATE TABLE IF NOT EXISTS `coffeemanagementdb`.`orderdetails` (
  `OrderID` INT NOT NULL,
  `ProductID` INT NOT NULL,
  `Quantity` INT NULL DEFAULT '1',
  `UnitPrice` DECIMAL(10,0) NOT NULL,
  `Note` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`OrderID`, `ProductID`),
  INDEX `fk_orderdetails_products_idx` (`ProductID` ASC) VISIBLE,
  CONSTRAINT `fk_orderdetails_orders`
    FOREIGN KEY (`OrderID`)
    REFERENCES `coffeemanagementdb`.`orders` (`OrderID`),
  CONSTRAINT `fk_orderdetails_products`
    FOREIGN KEY (`ProductID`)
    REFERENCES `coffeemanagementdb`.`products` (`ProductID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
© 2021 GitHub, Inc.
Terms
Privacy
Security
Status
Docs
Contact GitHub
Pricing
API
Training
Blog
About
Loading complete