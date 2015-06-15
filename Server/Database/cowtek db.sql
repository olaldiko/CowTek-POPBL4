-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 15, 2015 at 04:11 AM
-- Server version: 5.6.19-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `COWTEK`
--
CREATE DATABASE IF NOT EXISTS `COWTEK` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `COWTEK`;

-- --------------------------------------------------------

--
-- Table structure for table `comedero`
--

DROP TABLE IF EXISTS `comedero`;
CREATE TABLE IF NOT EXISTS `comedero` (
  `ComederoID` int(11) NOT NULL,
  `Descripcion` varchar(70) DEFAULT NULL,
  `ModeloID` int(11) NOT NULL,
  `EstacionID` int(11) NOT NULL,
  PRIMARY KEY (`ComederoID`),
  KEY `EstacionID_FK_idx` (`EstacionID`),
  KEY `ModeloID_FK_idx` (`ModeloID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `consumoVaca`
--

DROP TABLE IF EXISTS `consumoVaca`;
CREATE TABLE IF NOT EXISTS `consumoVaca` (
  `VacaID` int(11) NOT NULL,
  `ComederoID` int(11) NOT NULL,
  `FechaHora` datetime NOT NULL,
  `Cantidad` float NOT NULL,
  PRIMARY KEY (`VacaID`,`FechaHora`,`ComederoID`),
  KEY `ComederoID_FK_idx` (`ComederoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `datosEstacion`
--

DROP TABLE IF EXISTS `datosEstacion`;
CREATE TABLE IF NOT EXISTS `datosEstacion` (
  `SensorID` int(11) NOT NULL,
  `EstacionID` int(11) NOT NULL,
  `FechaHora` datetime NOT NULL,
  `Valor` float NOT NULL,
  `UnidadID` int(11) NOT NULL,
  PRIMARY KEY (`SensorID`,`EstacionID`,`FechaHora`,`UnidadID`),
  KEY `SensorRecolectaEstacion_EstacionID_FK_idx` (`EstacionID`),
  KEY `DatosEstacion_UnidadID_FK` (`UnidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `datosVaca`
--

DROP TABLE IF EXISTS `datosVaca`;
CREATE TABLE IF NOT EXISTS `datosVaca` (
  `SensorID` int(11) NOT NULL,
  `VacaID` int(11) NOT NULL,
  `FechaHora` datetime NOT NULL,
  `Valor` float NOT NULL,
  `UnidadID` int(11) NOT NULL,
  PRIMARY KEY (`SensorID`,`VacaID`,`FechaHora`,`UnidadID`),
  KEY `SensorRecolectaVaca_VacaID_FK_idx` (`VacaID`),
  KEY `DatosVaca_UnidadID_FK` (`UnidadID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `estacion`
--

DROP TABLE IF EXISTS `estacion`;
CREATE TABLE IF NOT EXISTS `estacion` (
  `EstacionID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) DEFAULT NULL,
  `Descripcion` varchar(70) DEFAULT NULL,
  `Latitud` double NOT NULL DEFAULT '0',
  `Longitud` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`EstacionID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `fabricante`
--

DROP TABLE IF EXISTS `fabricante`;
CREATE TABLE IF NOT EXISTS `fabricante` (
  `FabricanteID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `FechaCreacion` date DEFAULT NULL,
  PRIMARY KEY (`FabricanteID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `humAmb_v`
--
DROP VIEW IF EXISTS `humAmb_v`;
CREATE TABLE IF NOT EXISTS `humAmb_v` (
`EstacionID` int(11)
,`fechahora` datetime
,`valor` float
,`nombre` varchar(25)
);
-- --------------------------------------------------------

--
-- Table structure for table `modelo`
--

DROP TABLE IF EXISTS `modelo`;
CREATE TABLE IF NOT EXISTS `modelo` (
  `ModeloID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) DEFAULT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  `Precio` float DEFAULT NULL,
  `FechaCreacion` date DEFAULT NULL,
  `FabricanteID` int(11) NOT NULL,
  PRIMARY KEY (`ModeloID`),
  KEY `FabricanteID_FK_idx` (`FabricanteID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=26 ;

-- --------------------------------------------------------

--
-- Table structure for table `ordenadero`
--

DROP TABLE IF EXISTS `ordenadero`;
CREATE TABLE IF NOT EXISTS `ordenadero` (
  `OrdenaderoID` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(75) DEFAULT NULL,
  `ModeloID` int(11) NOT NULL,
  `EstacionID` int(11) NOT NULL,
  PRIMARY KEY (`OrdenaderoID`),
  KEY `EstacionID_FK_idx` (`EstacionID`),
  KEY `ModeloID_FK_idx` (`ModeloID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `placa`
--

DROP TABLE IF EXISTS `placa`;
CREATE TABLE IF NOT EXISTS `placa` (
  `PlacaID` int(11) NOT NULL AUTO_INCREMENT,
  `FechaCompra` date NOT NULL,
  `TipoPlacaID` int(11) NOT NULL,
  PRIMARY KEY (`PlacaID`),
  KEY `TipoPlaca_FK_idx` (`TipoPlacaID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=133 ;

-- --------------------------------------------------------

--
-- Table structure for table `placaEstacion`
--

DROP TABLE IF EXISTS `placaEstacion`;
CREATE TABLE IF NOT EXISTS `placaEstacion` (
  `EstacionID` int(11) NOT NULL,
  `PlacaID` int(11) NOT NULL,
  `FechaInicio` date NOT NULL,
  `FechaFinal` date DEFAULT NULL,
  PRIMARY KEY (`EstacionID`,`PlacaID`,`FechaInicio`),
  KEY `PlacaID_FK_idx` (`PlacaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `placaVaca`
--

DROP TABLE IF EXISTS `placaVaca`;
CREATE TABLE IF NOT EXISTS `placaVaca` (
  `VacaID` int(11) NOT NULL,
  `PlacaID` int(11) NOT NULL,
  `FechaInicio` date NOT NULL,
  `FechaFInal` date DEFAULT NULL,
  PRIMARY KEY (`PlacaID`,`FechaInicio`,`VacaID`),
  KEY `VacaID_FK_idx` (`VacaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `produccionVaca`
--

DROP TABLE IF EXISTS `produccionVaca`;
CREATE TABLE IF NOT EXISTS `produccionVaca` (
  `VacaID` int(11) NOT NULL,
  `OrdenaderoID` int(11) NOT NULL,
  `FechaHora` datetime NOT NULL,
  `Cantidad` float NOT NULL,
  PRIMARY KEY (`VacaID`,`OrdenaderoID`,`FechaHora`),
  KEY `OrdenaderoID_FK_idx` (`OrdenaderoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `sensor`
--

DROP TABLE IF EXISTS `sensor`;
CREATE TABLE IF NOT EXISTS `sensor` (
  `SensorID` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(70) DEFAULT NULL,
  `PlacaID` int(11) NOT NULL,
  `TipoSensorID` int(11) NOT NULL,
  PRIMARY KEY (`SensorID`),
  KEY `PlacaID_FK_idx` (`PlacaID`),
  KEY `TipoSensorID_FK_idx` (`TipoSensorID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1003 ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `tempAmb_v`
--
DROP VIEW IF EXISTS `tempAmb_v`;
CREATE TABLE IF NOT EXISTS `tempAmb_v` (
`EstacionID` int(11)
,`fechahora` datetime
,`valor` float
,`nombre` varchar(25)
);
-- --------------------------------------------------------

--
-- Stand-in structure for view `tempCorpVaca_v`
--
DROP VIEW IF EXISTS `tempCorpVaca_v`;
CREATE TABLE IF NOT EXISTS `tempCorpVaca_v` (
`VacaID` int(11)
,`fechahora` datetime
,`valor` float
,`nombre` varchar(25)
);
-- --------------------------------------------------------

--
-- Table structure for table `tipoPlaca`
--

DROP TABLE IF EXISTS `tipoPlaca`;
CREATE TABLE IF NOT EXISTS `tipoPlaca` (
  `TipoPlacaID` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(45) DEFAULT NULL,
  `ModeloID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TipoPlacaID`),
  KEY `ModeloID_FK_idx` (`ModeloID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `tipoSensor`
--

DROP TABLE IF EXISTS `tipoSensor`;
CREATE TABLE IF NOT EXISTS `tipoSensor` (
  `TipoSensorID` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(70) DEFAULT NULL,
  `ModeloID` int(11) NOT NULL,
  PRIMARY KEY (`TipoSensorID`),
  KEY `ModeloID_FK_idx` (`ModeloID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `ubicacionVaca_v`
--
DROP VIEW IF EXISTS `ubicacionVaca_v`;
CREATE TABLE IF NOT EXISTS `ubicacionVaca_v` (
`VacaID` int(11)
,`FechaHora` datetime
,`Valor` float
,`Unidad` varchar(25)
);
-- --------------------------------------------------------

--
-- Table structure for table `unidad`
--

DROP TABLE IF EXISTS `unidad`;
CREATE TABLE IF NOT EXISTS `unidad` (
  `UnidadID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`UnidadID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=57 ;

-- --------------------------------------------------------

--
-- Table structure for table `vaca`
--

DROP TABLE IF EXISTS `vaca`;
CREATE TABLE IF NOT EXISTS `vaca` (
  `VacaID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) DEFAULT NULL,
  `Raza` varchar(25) DEFAULT NULL,
  `FechaNacimiento` date DEFAULT NULL,
  PRIMARY KEY (`VacaID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=167 ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `vistaVaca_v`
--
DROP VIEW IF EXISTS `vistaVaca_v`;
CREATE TABLE IF NOT EXISTS `vistaVaca_v` (
`VacaID` int(11)
,`NombreVaca` varchar(25)
,`FechaHora` datetime
,`Valor` float
,`NombreUnidad` varchar(25)
);
-- --------------------------------------------------------

--
-- Structure for view `humAmb_v`
--
DROP TABLE IF EXISTS `humAmb_v`;

CREATE ALGORITHM=UNDEFINED DEFINER=`aitorbarreiro`@`%` SQL SECURITY DEFINER VIEW `humAmb_v` AS select `d`.`EstacionID` AS `EstacionID`,`d`.`FechaHora` AS `fechahora`,`d`.`Valor` AS `valor`,`u`.`Nombre` AS `nombre` from (`datosEstacion` `d` join `unidad` `u` on((`d`.`UnidadID` = `u`.`UnidadID`))) where (`u`.`UnidadID` = 7) order by `d`.`FechaHora`,`u`.`Nombre`;

-- --------------------------------------------------------

--
-- Structure for view `tempAmb_v`
--
DROP TABLE IF EXISTS `tempAmb_v`;

CREATE ALGORITHM=UNDEFINED DEFINER=`aitorbarreiro`@`%` SQL SECURITY DEFINER VIEW `tempAmb_v` AS select `d`.`EstacionID` AS `EstacionID`,`d`.`FechaHora` AS `fechahora`,`d`.`Valor` AS `valor`,`u`.`Nombre` AS `nombre` from (`datosEstacion` `d` join `unidad` `u` on((`d`.`UnidadID` = `u`.`UnidadID`))) where (`u`.`UnidadID` = 6) order by `d`.`FechaHora`,`u`.`Nombre`;

-- --------------------------------------------------------

--
-- Structure for view `tempCorpVaca_v`
--
DROP TABLE IF EXISTS `tempCorpVaca_v`;

CREATE ALGORITHM=UNDEFINED DEFINER=`aitorbarreiro`@`%` SQL SECURITY DEFINER VIEW `tempCorpVaca_v` AS select `d`.`VacaID` AS `VacaID`,`d`.`FechaHora` AS `fechahora`,`d`.`Valor` AS `valor`,`u`.`Nombre` AS `nombre` from (`datosVaca` `d` join `unidad` `u` on((`d`.`UnidadID` = `u`.`UnidadID`))) where (`u`.`UnidadID` = 1) order by `d`.`FechaHora`,`u`.`Nombre`;

-- --------------------------------------------------------

--
-- Structure for view `ubicacionVaca_v`
--
DROP TABLE IF EXISTS `ubicacionVaca_v`;

CREATE ALGORITHM=UNDEFINED DEFINER=`aitorbarreiro`@`%` SQL SECURITY DEFINER VIEW `ubicacionVaca_v` AS select `d`.`VacaID` AS `VacaID`,`d`.`FechaHora` AS `FechaHora`,`d`.`Valor` AS `Valor`,`u`.`Nombre` AS `Unidad` from (`datosVaca` `d` join `unidad` `u` on((`d`.`UnidadID` = `u`.`UnidadID`))) where (`u`.`UnidadID` in (3,4)) order by `d`.`FechaHora`,`u`.`Nombre`;

-- --------------------------------------------------------

--
-- Structure for view `vistaVaca_v`
--
DROP TABLE IF EXISTS `vistaVaca_v`;

CREATE ALGORITHM=UNDEFINED DEFINER=`aitorbarreiro`@`%` SQL SECURITY DEFINER VIEW `vistaVaca_v` AS select `d`.`VacaID` AS `VacaID`,`v`.`Nombre` AS `NombreVaca`,`d`.`FechaHora` AS `FechaHora`,`d`.`Valor` AS `Valor`,`u`.`Nombre` AS `NombreUnidad` from ((`datosVaca` `d` join `vaca` `v` on((`d`.`VacaID` = `v`.`VacaID`))) join `unidad` `u` on((`d`.`UnidadID` = `u`.`UnidadID`))) order by `d`.`FechaHora`,`u`.`Nombre`;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comedero`
--
ALTER TABLE `comedero`
  ADD CONSTRAINT `Comedero_EstacionID_FK` FOREIGN KEY (`EstacionID`) REFERENCES `estacion` (`EstacionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Comedero_ModeloID_FK` FOREIGN KEY (`ModeloID`) REFERENCES `modelo` (`ModeloID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `consumoVaca`
--
ALTER TABLE `consumoVaca`
  ADD CONSTRAINT `VacaComeComedero_ComederoID_FK` FOREIGN KEY (`ComederoID`) REFERENCES `comedero` (`ComederoID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `VacaComeComedero_VacaID_FK` FOREIGN KEY (`VacaID`) REFERENCES `vaca` (`VacaID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `datosEstacion`
--
ALTER TABLE `datosEstacion`
  ADD CONSTRAINT `DatosEstacion_UnidadID_FK` FOREIGN KEY (`UnidadID`) REFERENCES `unidad` (`UnidadID`),
  ADD CONSTRAINT `SensorRecolectaEstacion_EstacionID_FK` FOREIGN KEY (`EstacionID`) REFERENCES `estacion` (`EstacionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `SensorRecolectaEstacion_SensorID_FK` FOREIGN KEY (`SensorID`) REFERENCES `sensor` (`SensorID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `datosVaca`
--
ALTER TABLE `datosVaca`
  ADD CONSTRAINT `DatosVaca_UnidadID_FK` FOREIGN KEY (`UnidadID`) REFERENCES `unidad` (`UnidadID`),
  ADD CONSTRAINT `SensorRecolectaVaca_SensorID_FK` FOREIGN KEY (`SensorID`) REFERENCES `sensor` (`SensorID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `SensorRecolectaVaca_VacaID_FK` FOREIGN KEY (`VacaID`) REFERENCES `vaca` (`VacaID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `modelo`
--
ALTER TABLE `modelo`
  ADD CONSTRAINT `Modelo_FabricanteID_FK` FOREIGN KEY (`FabricanteID`) REFERENCES `fabricante` (`FabricanteID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ordenadero`
--
ALTER TABLE `ordenadero`
  ADD CONSTRAINT `Ordenadero_EstacionID_FK` FOREIGN KEY (`EstacionID`) REFERENCES `estacion` (`EstacionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Ordenadero_ModeloID_FK` FOREIGN KEY (`ModeloID`) REFERENCES `modelo` (`ModeloID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `placa`
--
ALTER TABLE `placa`
  ADD CONSTRAINT `Placa_TipoPlaca_FK` FOREIGN KEY (`TipoPlacaID`) REFERENCES `tipoPlaca` (`TipoPlacaID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `placaEstacion`
--
ALTER TABLE `placaEstacion`
  ADD CONSTRAINT `EstacionTienePlaca_EstacionID_FK` FOREIGN KEY (`EstacionID`) REFERENCES `estacion` (`EstacionID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `EstacionTienePlaca_PlacaID_FK` FOREIGN KEY (`PlacaID`) REFERENCES `placa` (`PlacaID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `placaVaca`
--
ALTER TABLE `placaVaca`
  ADD CONSTRAINT `VacaTienePlacaPlacaID_FK` FOREIGN KEY (`PlacaID`) REFERENCES `placa` (`PlacaID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `VacaTienePlacaVacaID_FK` FOREIGN KEY (`VacaID`) REFERENCES `vaca` (`VacaID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `produccionVaca`
--
ALTER TABLE `produccionVaca`
  ADD CONSTRAINT `VacaProduceOrdenaderoOrdenaderoID_FK` FOREIGN KEY (`OrdenaderoID`) REFERENCES `ordenadero` (`OrdenaderoID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `VacaProduceOrdenaderoVacaID_FK` FOREIGN KEY (`VacaID`) REFERENCES `vaca` (`VacaID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `sensor`
--
ALTER TABLE `sensor`
  ADD CONSTRAINT `Sensor_PlacaID_FK` FOREIGN KEY (`PlacaID`) REFERENCES `placa` (`PlacaID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Sensor_TipoSensorID_FK` FOREIGN KEY (`TipoSensorID`) REFERENCES `tipoSensor` (`TipoSensorID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tipoPlaca`
--
ALTER TABLE `tipoPlaca`
  ADD CONSTRAINT `TipoPlaca_ModeloID_FK` FOREIGN KEY (`ModeloID`) REFERENCES `modelo` (`ModeloID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tipoSensor`
--
ALTER TABLE `tipoSensor`
  ADD CONSTRAINT `TipoSensor_ModeloID_FK` FOREIGN KEY (`ModeloID`) REFERENCES `modelo` (`ModeloID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
