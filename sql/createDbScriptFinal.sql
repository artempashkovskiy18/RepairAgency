-- MySQL Script generated by MySQL Workbench
-- Fri Jan 27 19:01:21 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema repair_agency
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `repair_agency` ;

-- -----------------------------------------------------
-- Schema repair_agency
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `repair_agency` DEFAULT CHARACTER SET utf8 ;
USE `repair_agency` ;

-- -----------------------------------------------------
-- Table `repair_agency`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency`.`roles` (
  `id_role` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_role`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair_agency`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency`.`users` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `id_role` INT NOT NULL,
  `phone` VARCHAR(255) NULL,
  PRIMARY KEY (`id_user`),
  INDEX `id_role_idx` (`id_role` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  CONSTRAINT `id_role`
    FOREIGN KEY (`id_role`)
    REFERENCES `repair_agency`.`roles` (`id_role`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair_agency`.`statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency`.`statuses` (
  `id_status` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_status`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `repair_agency`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `repair_agency`.`orders` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NOT NULL,
  `id_user` INT NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `id_status` INT NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  PRIMARY KEY (`id_order`),
  INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
  INDEX `id_status_idx` (`id_status` ASC) VISIBLE,
  CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `repair_agency`.`users` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_status`
    FOREIGN KEY (`id_status`)
    REFERENCES `repair_agency`.`statuses` (`id_status`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


insert into roles(name) values("user");
insert into roles(name) values("manager");
insert into roles(name) values("craftsman");

insert into statuses(name) values("pending_payment");
insert into statuses(name) values("paid");
insert into statuses(name) values("in_progress");
insert into statuses(name) values("complete");
insert into statuses(name) values("canceled");
insert into statuses(name) values("waiting");

insert into users(name, email, password, id_role, phone) values ("Artem", "artempashkovskiy18@gmail.com", "123", 2, "+380964568447")