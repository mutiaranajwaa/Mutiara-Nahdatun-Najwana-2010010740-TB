-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.33 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_penyewaan_pc
DROP DATABASE IF EXISTS `db_penyewaan_pc`;
CREATE DATABASE IF NOT EXISTS `db_penyewaan_pc` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db_penyewaan_pc`;

-- Dumping structure for table db_penyewaan_pc.tb_pc
DROP TABLE IF EXISTS `tb_pc`;
CREATE TABLE IF NOT EXISTS `tb_pc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_pc` varchar(50) DEFAULT '0',
  `spesifikasi` varchar(50) DEFAULT '0',
  `harga_sewa` varchar(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table db_penyewaan_pc.tb_pelanggan
DROP TABLE IF EXISTS `tb_pelanggan`;
CREATE TABLE IF NOT EXISTS `tb_pelanggan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `no_telp` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table db_penyewaan_pc.tb_transaksi
DROP TABLE IF EXISTS `tb_transaksi`;
CREATE TABLE IF NOT EXISTS `tb_transaksi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_pc` varchar(50) DEFAULT NULL,
  `nama_pelanggan` varchar(50) DEFAULT NULL,
  `tgl_sewa` varchar(50) DEFAULT NULL,
  `total_harga` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
