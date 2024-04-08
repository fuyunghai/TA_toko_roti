-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 14, 2024 at 10:36 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jual`
--

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` varchar(400) NOT NULL,
  `nama_karyawan` varchar(800) NOT NULL,
  `no_telpon` varchar(200) NOT NULL,
  `alamat` varchar(2000) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama_karyawan`, `no_telpon`, `alamat`, `password`) VALUES
('KT0001', 'ren', '081314997271', 'jalan braga, cimahi, bandung', 'd41suk1'),
('KT0002', 'yuto n', '089677812236', 'jalan pramuka, mampang, depok', 'rotibakar5000'),
('KT0003', 'm kota', '081314896870', 'jalan setu, jonggol, bogor', 'mamang kota'),
('KT0006', 'leviana', '081314997175', 'cibubur, jakarta timur', 'abcd12');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id_menu` varchar(44) NOT NULL,
  `nama_menu` varchar(800) NOT NULL,
  `kategori` varchar(400) NOT NULL,
  `harga_jual` int(11) NOT NULL,
  `stok_menu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id_menu`, `nama_menu`, `kategori`, `harga_jual`, `stok_menu`) VALUES
('MN0001', 'roti kepang keju', 'Bakery', 18000, 11),
('MN0002', 'roti keju bun', 'Bakery', 12500, 10),
('MN0003', 'roti coklat bun', 'Bakery', 12500, 10),
('MN0004', 'cupcake', 'Cakes', 8000, 8),
('MN0005', 'pizza keju cheddar original', 'Bakery', 12500, 7),
('MN0006', 'roti pisang keju', 'Bakery', 12500, 9),
('MN0007', 'coklat sendal', 'Cookies', 50000, 10),
('MN0008', 'kastengel', 'Cookies', 50000, 3),
('MN0009', 'roti tawar', 'Bakery', 12000, 15);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` varchar(400) NOT NULL,
  `nama_pelanggan` varchar(800) NOT NULL,
  `no_telpon` varchar(200) NOT NULL,
  `alamat` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_telpon`, `alamat`) VALUES
('P0001', 'kaito', '081766329091', 'jalan letda natsir, cikeas, bogor'),
('P0002', 'mama hapis', '089233457780', 'jalan cicadas, gunung putri, bogor'),
('P0003', 'mama beri', '089233417172', 'jalan nilem, margaasih, bandung'),
('P0004', 'aki shige', '089577812345', 'jalan mampang prapatan, tegal parang, jakarta selatan'),
('P0005', 'momo', '089987653451', 'kampung sirih, kuningan'),
('P0006', 'nanda', '082122334571', 'bojong gede, bogor');

-- --------------------------------------------------------

--
-- Table structure for table `trans`
--

CREATE TABLE `trans` (
  `id_transaksi` varchar(400) NOT NULL,
  `tanggal` date NOT NULL,
  `id_karyawan` varchar(400) NOT NULL,
  `nama_karyawan` varchar(2000) NOT NULL,
  `id_pelanggan` varchar(400) NOT NULL,
  `nama_pelanggan` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trans`
--

INSERT INTO `trans` (`id_transaksi`, `tanggal`, `id_karyawan`, `nama_karyawan`, `id_pelanggan`, `nama_pelanggan`) VALUES
('TRN0001', '2023-10-15', 'KT0002', 'yuto', 'P0002', 'mama hapis'),
('TRN0002', '2023-10-15', 'KT0001', 'ren', 'P0001', 'kaito'),
('TRN0003', '2023-10-15', 'KT0001', 'ren', 'P0003', 'mama beri'),
('TRN0004', '2023-11-06', 'KT0002', 'yuto n', 'P0005', 'momo'),
('TRN0005', '2023-11-07', 'KT0002', 'yuto n', 'P0003', 'mama beri'),
('TRN0006', '2023-12-02', 'KT0005', 'levi', 'P0003', 'mama beri'),
('TRN0007', '2023-12-02', 'KT0005', 'levi', 'P0003', 'mama beri'),
('TRN0008', '2023-12-02', 'KT0001', 'ren', 'P0001', 'kaito'),
('TRN0009', '2023-12-02', 'KT0005', 'levi', 'P0003', 'mama beri'),
('TRN0010', '2023-12-02', 'KT0003', 'm kota', 'P0001', 'kaito'),
('TRN0011', '2023-12-02', 'KT0003', 'm kota', 'P0002', 'mama hapis'),
('TRN0012', '2023-12-02', 'KT0002', 'yuto n', 'P0004', 'aki shige'),
('TRN0013', '2023-12-02', 'KT0003', 'm kota', 'P0002', 'mama hapis'),
('TRN0014', '2023-12-02', 'KT0002', 'yuto n', 'P0004', 'aki shige'),
('TRN0015', '2023-12-02', 'KT0003', 'm kota', 'P0003', 'mama beri'),
('TRN0016', '2023-12-02', 'KT0003', 'm kota', 'P0005', 'momo'),
('TRN0017', '2023-12-02', 'KT0003', 'm kota', 'P0003', 'mama beri'),
('TRN0018', '2023-12-02', 'KT0002', 'yuto n', 'P0004', 'aki shige'),
('TRN0019', '2023-12-02', 'KT0003', 'm kota', 'P0004', 'aki shige'),
('TRN0020', '2023-12-02', 'KT0003', 'm kota', 'P0002', 'mama hapis'),
('TRN0021', '2023-12-02', 'KT0002', 'yuto n', 'P0003', 'mama beri'),
('TRN0022', '2023-12-02', 'KT0002', 'yuto n', 'P0004', 'aki shige'),
('TRN0023', '2023-12-02', 'KT0002', 'yuto n', 'P0001', 'kaito'),
('TRN0024', '2024-01-14', 'KT0006', 'leviana', 'P0006', 'nanda');

-- --------------------------------------------------------

--
-- Table structure for table `trans_rinci`
--

CREATE TABLE `trans_rinci` (
  `id_transaksi` varchar(400) NOT NULL,
  `id_menu` varchar(400) NOT NULL,
  `nama_menu` varchar(2000) NOT NULL,
  `harga_jual` int(100) NOT NULL,
  `jumlah_menu` int(100) NOT NULL,
  `subtotal` int(100) NOT NULL,
  `total_bayar` int(100) NOT NULL,
  `tunai` int(100) NOT NULL,
  `kembalian` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trans_rinci`
--

INSERT INTO `trans_rinci` (`id_transaksi`, `id_menu`, `nama_menu`, `harga_jual`, `jumlah_menu`, `subtotal`, `total_bayar`, `tunai`, `kembalian`) VALUES
('TRN0001', 'MN0003', 'roti coklat bun', 12500, 2, 25000, 50000, 50000, 0),
('TRN0001', 'MN0006', 'roti pisang keju', 12500, 2, 25000, 50000, 50000, 0),
('TRN0002', 'MN0005', 'pizza keju cheddar original', 12500, 3, 37500, 74000, 100000, 26000),
('TRN0002', 'MN0004', 'cupcake', 8000, 3, 24000, 74000, 100000, 26000),
('TRN0002', 'MN0002', 'roti keju bun', 12500, 1, 12500, 74000, 100000, 26000),
('TRN0003', 'MN0007', 'coklat sendal', 50000, 1, 50000, 75000, 80000, 5000),
('TRN0003', 'MN0003', 'roti coklat bun', 12500, 2, 25000, 75000, 80000, 5000),
('TRN0004', 'MN0003', 'roti coklat bun', 12500, 2, 25000, 83000, 100000, 17000),
('TRN0004', 'MN0008', 'kastengel', 50000, 1, 50000, 83000, 100000, 17000),
('TRN0004', 'MN0004', 'cupcake', 8000, 1, 8000, 83000, 100000, 17000),
('TRN0005', 'MN0005', 'pizza keju cheddar original', 12500, 2, 25000, 25000, 50000, 25000),
('TRN0006', 'MN0003', 'roti coklat bun', 12500, 2, 25000, 25000, 50000, 25000),
('TRN0007', 'MN0007', 'coklat sendal', 50000, 1, 50000, 50000, 50000, 0),
('TRN0008', 'MN0007', 'coklat sendal', 50000, 2, 100000, 100000, 100000, 0),
('TRN0009', 'MN0006', 'roti pisang keju', 12500, 1, 12500, 12500, 15000, 2500),
('TRN0010', 'MN0002', 'roti keju bun', 12500, 2, 25000, 25000, 50000, 25000),
('TRN0011', 'MN0005', 'pizza keju cheddar original', 12500, 1, 12500, 12500, 12500, 0),
('TRN0012', 'MN0004', 'cupcake', 8000, 1, 8000, 8000, 10000, 2000),
('TRN0013', 'MN0006', 'roti pisang keju', 12500, 1, 12500, 12500, 12500, 0),
('TRN0014', 'MN0005', 'pizza keju cheddar original', 12500, 1, 12500, 12500, 15000, 2500),
('TRN0015', 'MN0006', 'roti pisang keju', 12500, 1, 12500, 12500, 12500, 0),
('TRN0016', 'MN0002', 'roti keju bun', 12500, 2, 25000, 25000, 25000, 0),
('TRN0017', 'MN0005', 'pizza keju cheddar original', 12500, 2, 25000, 25000, 50000, 25000),
('TRN0018', 'MN0004', 'cupcake', 8000, 1, 8000, 8000, 8000, 0),
('TRN0019', 'MN0003', 'roti coklat bun', 12500, 1, 12500, 12500, 12500, 0),
('TRN0020', 'MN0005', 'pizza keju cheddar original', 12500, 1, 12500, 12500, 19000, 6500),
('TRN0021', 'MN0009', 'roti tawar', 12000, 1, 12000, 12000, 12000, 0),
('TRN0022', 'MN0007', 'coklat sendal', 50000, 3, 150000, 150000, 200000, 50000),
('TRN0023', 'MN0009', 'roti tawar', 12000, 1, 12000, 12000, 15000, 3000),
('TRN0024', 'MN0002', 'roti keju bun', 12500, 2, 25000, 25000, 50000, 25000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_menu`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`),
  ADD KEY `id_pelanggan` (`id_pelanggan`);

--
-- Indexes for table `trans`
--
ALTER TABLE `trans`
  ADD PRIMARY KEY (`id_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
