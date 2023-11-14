-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Kmarket
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Kmarket
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Kmarket` DEFAULT CHARACTER SET utf8 ;
USE `Kmarket` ;

-- -----------------------------------------------------
-- Table `Kmarket`.`km_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_member` (
  `uid` VARCHAR(20) NOT NULL,
  `level` TINYINT NOT NULL DEFAULT 1,
  `pass` VARCHAR(255) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `gender` TINYINT NOT NULL,
  `hp` CHAR(13) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `type` TINYINT NOT NULL,
  `point` INT NOT NULL DEFAULT 0,
  `zip` VARCHAR(10) NULL,
  `addr1` VARCHAR(255) NULL,
  `addr2` VARCHAR(255) NULL,
  `company` VARCHAR(20) NULL,
  `ceo` VARCHAR(20) NULL,
  `bizRegNum` CHAR(12) NULL,
  `comRegNum` VARCHAR(100) NULL,
  `tel` VARCHAR(20) NULL,
  `manager` VARCHAR(20) NULL,
  `managerHp` CHAR(13) NULL,
  `fax` VARCHAR(20) NULL,
  `regip` VARCHAR(100) NOT NULL,
  `wdate` DATETIME NULL,
  `rdate` DATETIME NOT NULL,
  `etc1` INT NULL,
  `etc2` INT NULL,
  `etc3` VARCHAR(10) NULL,
  `etc4` VARCHAR(20) NULL,
  `etc5` VARCHAR(30) NULL,
  PRIMARY KEY (`uid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_member_terms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_member_terms` (
  `terms` TEXT NOT NULL,
  `privacy` TEXT NOT NULL,
  `location` TEXT NOT NULL,
  `finance` TEXT NOT NULL,
  `tax` TEXT NOT NULL)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_member_point`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_member_point` (
  `pointNo` INT NOT NULL,
  `uid` VARCHAR(20) NOT NULL,
  `ordNo` INT NOT NULL,
  `point` INT NOT NULL,
  `pointDate` DATETIME NOT NULL,
  PRIMARY KEY (`pointNo`),
  INDEX `fk_km_member_point_km_member1_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_km_member_point_km_member1`
    FOREIGN KEY (`uid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_cate1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_cate1` (
  `cate1` TINYINT(40) NOT NULL,
  `c1Name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cate1`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product` (
  `prodNo` INT(10) NOT NULL AUTO_INCREMENT,
  `seller` VARCHAR(20) NOT NULL,
  `prodCate1` TINYINT(2) NOT NULL,
  `prodCate2` TINYINT NOT NULL,
  `prodName` VARCHAR(100) NOT NULL,
  `descript` VARCHAR(100) NOT NULL,
  `company` VARCHAR(100) NOT NULL,
  `price` INT NOT NULL,
  `discount` TINYINT NOT NULL DEFAULT 0,
  `point` INT NOT NULL DEFAULT 0,
  `stock` INT NOT NULL DEFAULT 0,
  `sold` INT NOT NULL DEFAULT 0,
  `delivery` INT NOT NULL DEFAULT 0,
  `hit` INT NOT NULL DEFAULT 0,
  `score` TINYINT NOT NULL DEFAULT 0,
  `review` INT NOT NULL DEFAULT 0,
  `thumb1` VARCHAR(255) NOT NULL,
  `newThumb1` VARCHAR(255) NULL,
  `thumb2` VARCHAR(255) NOT NULL,
  `newThumb2` VARCHAR(255) NULL,
  `thumb3` VARCHAR(255) NOT NULL,
  `newThumb3` VARCHAR(255) NULL,
  `detail` VARCHAR(255) NOT NULL,
  `newDetail` VARCHAR(255) NULL,
  `status` VARCHAR(20) NOT NULL DEFAULT '새상품',
  `duty` VARCHAR(20) NOT NULL DEFAULT '과세상품',
  `receipt` VARCHAR(100) NOT NULL DEFAULT '발행가능 - 신용카드 전표, 온라인 현금영수증',
  `bizType` VARCHAR(20) NOT NULL DEFAULT '사업자 판매자',
  `origin` VARCHAR(20) NOT NULL DEFAULT '상세설명참고',
  `ip` VARCHAR(20) NOT NULL,
  `rdate` DATETIME NOT NULL,
  `etc1` INT NULL,
  `etc2` INT NULL,
  `etc3` VARCHAR(10) NULL,
  `etc4` VARCHAR(20) NULL,
  `etc5` VARCHAR(30) NULL,
  PRIMARY KEY (`prodNo`),
  INDEX `fk_km_product_km_member_idx` (`seller` ASC) VISIBLE,
  INDEX `fk_km_product_km_product_cate11_idx` (`prodCate1` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_km_member`
    FOREIGN KEY (`seller`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_product_km_product_cate11`
    FOREIGN KEY (`prodCate1`)
    REFERENCES `Kmarket`.`km_product_cate1` (`cate1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_cate2`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_cate2` (
  `cate2` TINYINT(40) NOT NULL,
  `c2Name` VARCHAR(20) NOT NULL,
  `cate3` TINYINT(40) NOT NULL,
  INDEX `fk_km_product_cate2_km_product_cate11_idx` (`cate3` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_cate2_km_product_cate11`
    FOREIGN KEY (`cate3`)
    REFERENCES `Kmarket`.`km_product_cate1` (`cate1`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_cart` (
  `cartNo` INT NOT NULL AUTO_INCREMENT,
  `uid` VARCHAR(20) NOT NULL,
  `prodNo` INT(10) NOT NULL,
  `count` INT NOT NULL,
  `price` INT NOT NULL,
  `discount` INT NOT NULL,
  `point` INT NOT NULL,
  `delivery` INT NOT NULL,
  `total` INT NOT NULL,
  `rdate` DATETIME NOT NULL,
  PRIMARY KEY (`cartNo`),
  INDEX `fk_km_product_cart_km_product1_idx` (`prodNo` ASC) VISIBLE,
  INDEX `fk_km_product_cart_km_member1_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_cart_km_product1`
    FOREIGN KEY (`prodNo`)
    REFERENCES `Kmarket`.`km_product` (`prodNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_product_cart_km_member1`
    FOREIGN KEY (`uid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_order` (
  `ordNo` INT NOT NULL AUTO_INCREMENT,
  `ordUid` VARCHAR(20) NOT NULL,
  `ordCount` INT NOT NULL DEFAULT 0,
  `ordPrice` INT NOT NULL DEFAULT 0,
  `ordDiscount` INT NOT NULL DEFAULT 0,
  `ordDelivery` INT NOT NULL DEFAULT 0,
  `savePoint` INT NOT NULL DEFAULT 0,
  `usedPoint` INT NOT NULL DEFAULT 0,
  `ordTotPrice` INT NOT NULL DEFAULT 0,
  `recipName` VARCHAR(20) NOT NULL,
  `recipHp` CHAR(13) NOT NULL,
  `recipZip` CHAR(5) NOT NULL,
  `recipAddr1` VARCHAR(255) NOT NULL,
  `recipAddr2` VARCHAR(255) NOT NULL,
  `ordStatus` VARCHAR(45) NOT NULL,
  `ordPayment` TINYINT NOT NULL,
  `ordComplete` TINYINT NOT NULL,
  `deliveryStatus` VARCHAR(45) NOT NULL,
  `ordDate` DATETIME NOT NULL,
  PRIMARY KEY (`ordNo`),
  INDEX `fk_km_product_order_km_member1_idx` (`ordUid` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_order_km_member1`
    FOREIGN KEY (`ordUid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_review` (
  `revNo` INT NOT NULL AUTO_INCREMENT,
  `ordNo` INT NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  `uid` VARCHAR(20) NOT NULL,
  `rating` TINYINT NOT NULL,
  `regip` VARCHAR(100) NOT NULL,
  `rdate` DATETIME NOT NULL,
  PRIMARY KEY (`revNo`),
  INDEX `fk_km_product_review_km_member1_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_review_km_member1`
    FOREIGN KEY (`uid`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_product_order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_product_order_item` (
  `ordNo` INT NOT NULL,
  `prodNo` INT(10) NOT NULL,
  `count` INT NOT NULL,
  `price` INT NOT NULL,
  `discount` TINYINT NOT NULL,
  `point` INT NOT NULL,
  `delivery` INT NOT NULL,
  `total` INT NOT NULL,
  INDEX `fk_km_product_order_item_km_product1_idx` (`prodNo` ASC) VISIBLE,
  PRIMARY KEY (`ordNo`),
  INDEX `fk_km_product_order_item_km_product_order1_idx` (`ordNo` ASC) VISIBLE,
  CONSTRAINT `fk_km_product_order_item_km_product1`
    FOREIGN KEY (`prodNo`)
    REFERENCES `Kmarket`.`km_product` (`prodNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_product_order_item_km_product_order1`
    FOREIGN KEY (`ordNo`)
    REFERENCES `Kmarket`.`km_product_order` (`ordNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_cs_aside`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_cs_aside` (
  `aeName` VARCHAR(50) NOT NULL,
  `akName` VARCHAR(50) NULL,
  `aside_Num` INT NULL,
  PRIMARY KEY (`aeName`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_cs_article`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_cs_article` (
  `aNo` INT NOT NULL AUTO_INCREMENT,
  `group` VARCHAR(20) NOT NULL,
  `cateDetail` VARCHAR(50) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `file` INT NOT NULL DEFAULT 0,
  `writer` VARCHAR(20) NOT NULL,
  `regip` VARCHAR(100) NULL,
  `rdate` DATETIME NULL,
  `type` INT NULL,
  `uLevel` INT NULL,
  `aStatus` INT NULL,
  PRIMARY KEY (`aNo`),
  INDEX `fk_km_cs_article_km_member1_idx` (`writer` ASC) VISIBLE,
  INDEX `fk_km_cs_article_km_cs_aside1_idx` (`cateDetail` ASC) VISIBLE,
  CONSTRAINT `fk_km_cs_article_km_member1`
    FOREIGN KEY (`writer`)
    REFERENCES `Kmarket`.`km_member` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_km_cs_article_km_cs_aside1`
    FOREIGN KEY (`cateDetail`)
    REFERENCES `Kmarket`.`km_cs_aside` (`aeName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_file`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_file` (
  `fNo` INT NOT NULL AUTO_INCREMENT,
  `aNo` INT NOT NULL,
  `oriName` VARCHAR(255) NULL,
  `newName` VARCHAR(255) NULL,
  `rdate` DATETIME NULL,
  INDEX `fk_km_cs_file_km_cs_article1_idx` (`aNo` ASC) VISIBLE,
  PRIMARY KEY (`fNo`),
  CONSTRAINT `fk_km_cs_file_km_cs_article1`
    FOREIGN KEY (`aNo`)
    REFERENCES `Kmarket`.`km_cs_article` (`aNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`banner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`banner` (
  `bNo` INT NOT NULL AUTO_INCREMENT,
  `bName` VARCHAR(45) NOT NULL,
  `bFile` VARCHAR(255) NOT NULL,
  `bSize` VARCHAR(45) NOT NULL,
  `bPath` VARCHAR(255) NOT NULL,
  `bLocation` VARCHAR(255) NOT NULL,
  `bDate` DATETIME NOT NULL,
  PRIMARY KEY (`bNo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_admin_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_admin_comment` (
  `rNo` INT NOT NULL AUTO_INCREMENT,
  `aNo` INT NOT NULL,
  `writer` VARCHAR(50) NULL,
  `rdate` DATETIME NULL,
  `content` TEXT NULL,
  PRIMARY KEY (`rNo`),
  INDEX `fk_km_admin_comment_km_cs_article1_idx` (`aNo` ASC) VISIBLE,
  CONSTRAINT `fk_km_admin_comment_km_cs_article1`
    FOREIGN KEY (`aNo`)
    REFERENCES `Kmarket`.`km_cs_article` (`aNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Kmarket`.`km_cs_cate_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Kmarket`.`km_cs_cate_detail` (
  `type` INT NOT NULL,
  `dName` VARCHAR(50) NULL,
  `aeName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`type`),
  INDEX `fk_km_cs_cate_detail_km_cs_aside1_idx` (`aeName` ASC) VISIBLE,
  CONSTRAINT `fk_km_cs_cate_detail_km_cs_aside1`
    FOREIGN KEY (`aeName`)
    REFERENCES `Kmarket`.`km_cs_aside` (`aeName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
