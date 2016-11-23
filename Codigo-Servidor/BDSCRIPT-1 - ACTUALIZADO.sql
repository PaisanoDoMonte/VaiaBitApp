SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `VaiaBitBD` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `VaiaBitBD` ;

-- -----------------------------------------------------
-- Table `VaiaBitBD`.`Fabricante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`Fabricante` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `VaiaBitBD`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`Producto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `FabricanteId` INT NULL,
  `Codigo` VARCHAR(45) NULL,
  `PartNumber` VARCHAR(45) NULL,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` TEXT NULL,
  `DescripcionCorta` VARCHAR(45) NULL,
  `Unidades` INT(11) NULL,
  `Imagen` VARCHAR(45) NULL,
  `Categoria` VARCHAR(45) NULL,
  `Novedad` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Productos_1_idx` (`FabricanteId` ASC),
  CONSTRAINT `fk_Productos_Fabricantes`
    FOREIGN KEY (`FabricanteId`)
    REFERENCES `VaiaBitBD`.`Fabricante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VaiaBitBD`.`ProductoPrecio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`ProductoPrecio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ProductoId` INT NOT NULL,
  `Descuento` DECIMAL(3,2) NULL,
  `FechaDescuentoInicio` DATETIME NULL,
  `FechaDescuentoFin` DATETIME NULL,
  `Precio` DECIMAL(10,2) NULL,
  `Iva` DECIMAL(4,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ProductoPrecio_Producto_idx` (`ProductoId` ASC),
  CONSTRAINT `fk_ProductoPrecio_Producto`
    FOREIGN KEY (`ProductoId`)
    REFERENCES `VaiaBitBD`.`Producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VaiaBitBD`.`Rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`Rol` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VaiaBitBD`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `RolId` INT NOT NULL,
  `Login` VARCHAR(45) NOT NULL,
  `PassWord` VARCHAR(45) NOT NULL,
  `Correo` VARCHAR(45) NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Apellidos` VARCHAR(45) NULL,
  `FechaNacimiento` DATE NULL,
  `Telefono1` VARCHAR(45) NULL,
  `Telefono2` VARCHAR(45) NULL,
  `FechaRegistro` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Correo_UNIQUE` (`Correo` ASC),
  INDEX `fk_Usuario_Rol1_idx` (`RolId` ASC),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC),
  CONSTRAINT `fk_Usuario_Rol1`
    FOREIGN KEY (`RolId`)
    REFERENCES `VaiaBitBD`.`Rol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VaiaBitBD`.`ProductoComentariosUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`ProductoComentariosUsuario` (
  `ProductoId` INT NOT NULL,
  `UsuarioId` INT NOT NULL,
  `Comentario` TEXT NULL,
  `Valoracion` INT NULL,
  `Fecha` DATETIME NULL,
  PRIMARY KEY (`ProductoId`, `UsuarioId`),
  INDEX `fk_ProductoComentariosUsuario_Producto_idx` (`ProductoId` ASC),
  INDEX `fk_ProductoComentariosUsuario_Usuario1_idx` (`UsuarioId` ASC),
  CONSTRAINT `fk_ProductoComentariosUsuario_Producto`
    FOREIGN KEY (`ProductoId`)
    REFERENCES `VaiaBitBD`.`Producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProductoComentariosUsuario_Usuario1`
    FOREIGN KEY (`UsuarioId`)
    REFERENCES `VaiaBitBD`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VaiaBitBD`.`ProductoReservaUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`ProductoReservaUsuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ProductoId` INT NOT NULL,
  `UsuarioId` INT NOT NULL,
  `Unidades` INT(11) NULL,
  `Fecha` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ProductoReservaUsuario_Producto_idx` (`ProductoId` ASC),
  INDEX `fk_ProductoReservaUsuario_Usuario_idx` (`UsuarioId` ASC),
  CONSTRAINT `fk_ProductoReservaUsuario_Producto`
    FOREIGN KEY (`ProductoId`)
    REFERENCES `VaiaBitBD`.`Producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProductoReservaUsuario_Usuario`
    FOREIGN KEY (`UsuarioId`)
    REFERENCES `VaiaBitBD`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VaiaBitBD`.`Funcionalidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`Funcionalidad` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VaiaBitBD`.`RolFuncionalidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`RolFuncionalidad` (
  `RolId` INT NOT NULL,
  `FuncionalidadId` INT NOT NULL,
  PRIMARY KEY (`RolId`, `FuncionalidadId`),
  INDEX `fk_Rol_has_Funcionalidad_Funcionalidad1_idx` (`FuncionalidadId` ASC),
  INDEX `fk_Rol_has_Funcionalidad_Rol1_idx` (`RolId` ASC),
  CONSTRAINT `fk_Rol_has_Funcionalidad_Rol1`
    FOREIGN KEY (`RolId`)
    REFERENCES `VaiaBitBD`.`Rol` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rol_has_Funcionalidad_Funcionalidad1`
    FOREIGN KEY (`FuncionalidadId`)
    REFERENCES `VaiaBitBD`.`Funcionalidad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VaiaBitBD`.`Domicilio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VaiaBitBD`.`Domicilio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `UsuarioId` INT NOT NULL UNIQUE,
  `Direccion1` VARCHAR(45) NULL,
  `Direccion2` VARCHAR(45) NULL,
  `Ciudad` VARCHAR(45) NULL,
  `Provincia` VARCHAR(45) NULL,
  `Pais` VARCHAR(45) NULL,
  `CodigoPostal` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Domicilio_Usuario1_idx` (`UsuarioId` ASC),
  CONSTRAINT `fk_Domicilio_Usuario1`
    FOREIGN KEY (`UsuarioId`)
    REFERENCES `VaiaBitBD`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
