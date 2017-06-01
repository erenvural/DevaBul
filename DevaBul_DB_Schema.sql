-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 29, 2017 at 11:04 PM
-- Server version: 10.1.20-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id1529553_a7751875_test`
--
CREATE DATABASE IF NOT EXISTS `id1529553_a7751875_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id1529553_a7751875_test`;

-- --------------------------------------------------------

--
-- Table structure for table `comment_connections`
--

CREATE TABLE `comment_connections` (
  `commentId` int(11) NOT NULL,
  `commentVoter` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment_connections`
--

INSERT INTO `comment_connections` (`commentId`, `commentVoter`) VALUES
(1, 'CozumArayanKisi');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `commentSubject` varchar(80) NOT NULL,
  `commentOwner` varchar(24) NOT NULL,
  `commentContent` varchar(500) NOT NULL,
  `commentTotalLikes` int(11) NOT NULL DEFAULT '0',
  `commentDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `commentSubject`, `commentOwner`, `commentContent`, `commentTotalLikes`, `commentDate`) VALUES
(1, 'AIDS kapmamak icin ne yapilmali?', 'Hiv.Bilgilendirici', 'Tanimadiginiz insanlarla cinsel iliskiye girmemek en buyuk tedbirdir.', 0, '2017-05-21 14:40:03'),
(2, 'Migren tedavisinde antidepresan yontemi', 'MigrenTedavicisi', 'Lustral adli ilaci onerebilirim.', 0, '2017-05-24 05:12:26'),
(3, 'Migren tedavisinde antidepresan yontemi', 'LosemiSavascisi', 'Kardesim Prozac kullanmisti, genel olarak memnun kaldigini soyleyebilirim.', 0, '2017-05-25 11:28:27'),
(4, 'Migren tedavisinde antidepresan yontemi', 'ErenVural', 'Alveresi onerebilirim', 0, '2017-05-25 16:12:12');

-- --------------------------------------------------------

--
-- Table structure for table `disease_suggestions`
--

CREATE TABLE `disease_suggestions` (
  `id` int(11) NOT NULL,
  `suggestName` varchar(70) NOT NULL,
  `suggestType` varchar(45) NOT NULL,
  `suggestDescription` text NOT NULL,
  `suggestOwner` varchar(11) NOT NULL,
  `suggestDate` datetime NOT NULL,
  `suggestConfirm` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `disease_suggestions`
--

INSERT INTO `disease_suggestions` (`id`, `suggestName`, `suggestType`, `suggestDescription`, `suggestOwner`, `suggestDate`, `suggestConfirm`) VALUES
(1, 'Sizofreni', 'Ruh Sagligı ve Hastaliklari', 'Sizofreni kisinin neyin gercek neyin hayali oldugunu anlayamadigi bir zihinsel hastalık, bir psikozdur.', 'ErenVural', '2017-05-17 03:09:08', 0);

-- --------------------------------------------------------

--
-- Table structure for table `diseases`
--

CREATE TABLE `diseases` (
  `id` int(11) NOT NULL,
  `diseaseName` varchar(70) NOT NULL,
  `diseaseType` varchar(45) NOT NULL,
  `diseaseDescription` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diseases`
--

INSERT INTO `diseases` (`id`, `diseaseName`, `diseaseType`, `diseaseDescription`) VALUES
(1, 'Losemi', 'Kanser', 'Losemi kan ile alakali bir kanserdir.'),
(2, 'AIDS', 'Cinsel Hastalik', 'AIDS, HIV etkeni nedeniyle insanlarda bagisiklik sisteminin cokmesine neden olan bulasici bir hastalik.'),
(3, 'Migren', 'Norolojik ', 'Migren cogunlukla otonom sinir sisteminde görülen birkac belirtiyle baglantili olan tekrarlayici orta siddette ve siddetli bas agrisi ile karakterize kronik bir rahatsizliktir.'),
(4, 'Reflu', 'Gastroenteroloji', 'Reflu, halk arasinda mide reflüsü olarak bilinen gastro ozofageal reflü hastalıgı, mide iceriginin yemek borusuna geri kacmasidir'),
(5, 'Astim', 'Gogus Hastaliklari', 'Solunum yollarında gerceklesen iltihaplanmalar ve daralmalar nedeniyle ortaya cikan hastaliga astim adı verilir'),
(6, 'Akciger Kanseri', 'Kanser', 'Sigara ve alkol yuzunden tüketimden kaynaklanabilen bir kanser turudur.'),
(7, 'Alkolizm', 'Genel', 'Surekli olarak alkollu icki icenlerde gorulen bedensel ve ruhsal bozuklukların genel adidir.'),
(8, 'Bel Soguklugu', 'Cinsel Hastalik', 'Cinsel birlesme yoluyla bulasan bir bakteriyel enfeksiyondur. Erkeklerde cogunlukla idrar yolunda enfeksiyona neden olur. Kadinlarda idrar yolu, rahim boynu veya her ikisinin de enfekte olmasina neden olabilir.'),
(9, 'Meme Kanseri', 'Kanser', 'Memenin sut bezlerinde ve uretilen sutu meme basına tasıyan kanallari doseyen hucreler arasinda, cesitli etkenler sonucu kontrolsuz sekilde cogalan ve baska organlara yayilma potansiyeli tasiyan hucrelerden meydana gelen tumoral olusumdur.');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `username` varchar(25) COLLATE latin1_general_ci NOT NULL,
  `image_url` varchar(400) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `username`, `image_url`) VALUES
(3, 'user1', 'ftp://a7751875:e1r2e3n4@31.170.160.95/uploads/user10.jpg'),
(2, 'Eren06', 'ftp://a7751875:e1r2e3n4@31.170.160.95/public_html/uploads/Eren06.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `sender` varchar(25) NOT NULL,
  `receiver` varchar(25) NOT NULL,
  `messageContent` varchar(400) NOT NULL,
  `messageDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `sender`, `receiver`, `messageContent`, `messageDate`) VALUES
(1, 'CozumArayanKisi', 'Hiv.Bilgilendirici', 'Merhaba AIDSli tanidigin var mi?', '2017-05-22 10:04:20');

-- --------------------------------------------------------

--
-- Table structure for table `subject_connections`
--

CREATE TABLE `subject_connections` (
  `subjectTitle` varchar(80) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject_connections`
--

INSERT INTO `subject_connections` (`subjectTitle`, `username`) VALUES
('AIDS kapmamak icin ne yapilmali?', 'CozumArayanKisi'),
('Astim ve sigara', 'ErenVural');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `subjectDisease` varchar(400) CHARACTER SET utf8 NOT NULL,
  `subjectTitle` varchar(80) CHARACTER SET utf8 NOT NULL,
  `subjectContent` varchar(500) CHARACTER SET utf8 NOT NULL,
  `subjectOwner` varchar(25) CHARACTER SET utf8 NOT NULL,
  `subjectDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `subjectDisease`, `subjectTitle`, `subjectContent`, `subjectOwner`, `subjectDate`) VALUES
(1, 'AIDS', 'AIDS kapmamak icin ne yapilmali?', 'AIDS bulasmamasi icin gerekli tedbirleri aciklayabilir misiniz?', 'CozumArayanKisi', '2017-05-21 14:12:00'),
(2, 'Migren', 'Migrenin kesin bir tedavisi var midir?', 'Migrenin kesin bir tedavisini ariyorum, bilgi verebilir misiniz?', 'Ankarali.Sifaci', '2017-05-21 07:26:30'),
(3, 'Migren', 'Migren tedavisinde antidepresan yontemi', 'Migren tedavisinde antidepresan kullanıp fayda gören bir tanidigi olan bilgi verebilir mi?', 'CozumArayanKisi', '2017-05-22 09:23:23'),
(4, 'Migren', 'Migren agrisini kesebilcek en guclu agri kesici nedir?', 'Migren sanciniz tuttugunda ilk aldiginiz hap hangisidir?', 'Lokman.Hekim.01', '2017-05-22 04:13:12'),
(5, 'Astim, Gogus Hastaliklari', 'Astim ve sigara', 'Astimi olan biri sigara icerse ne gibi sonuclar ortaya cikar?', 'ErenVural', '2017-05-25 14:37:34');

-- --------------------------------------------------------

--
-- Table structure for table `user_connections`
--

CREATE TABLE `user_connections` (
  `followingUserName` varchar(25) NOT NULL,
  `followedUserName` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_connections`
--

INSERT INTO `user_connections` (`followingUserName`, `followedUserName`) VALUES
('CozumArayanKisi', 'Hiv.Bilgilendirici'),
('ErenVural', 'LosemiSavascisi'),
('ErenVural', 'MigrenTedavicisi');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `profile_status` varchar(11) COLLATE latin1_general_ci NOT NULL DEFAULT 'Normal',
  `username` varchar(25) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 NOT NULL,
  `birthday` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `relatedDisease` varchar(70) COLLATE latin1_general_ci NOT NULL DEFAULT 'Hepsi'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `profile_status`, `username`, `email`, `password`, `birthday`, `gender`, `relatedDisease`) VALUES
(1, 'Normal', 'LosemiSavascisi', 'losemi.savascisi@gmail.com', 'e1r2e3n4', '12.04.1985', 'Erkek', 'Hepsi'),
(2, 'Normal', 'Hiv.Bilgilendirici', 'hiv.bilgilendirici@gmail.com', 'e1r2e3n4', '12.05.1982', 'Kadin', 'Hepsi'),
(3, 'Normal', 'AlkolBagimlisi07', 'alkolbagimlisi@gmail.com', 'e1r2e3n4', '13.10.1990', 'Erkek', 'Hepsi'),
(4, 'Normal', 'Kansiz.Cocuk', 'kansiz.cocuk@gmail.com', 'e1r2e3n4', '12.08.1992', 'Erkek', 'Hepsi'),
(5, 'Normal', 'Alerjik.Kadin', 'alerjikkadin@gmail.com', 'e1r2e3n4', '05.04.1989', 'Kadin', 'Hepsi'),
(6, 'Normal', 'Ankarali.Sifaci', 'ankaralisifaci@gmail.com', 'e1r2e3n4', '04.01.1979', 'Erkek', 'Hepsi'),
(7, 'Normal', 'Lokman.Hekim.01', 'lokmanhekim01@gmail.com', 'e1r2e3n4', '12.03.1981', 'Kadin', 'Hepsi'),
(8, 'Normal', 'MigrenTedavicisi', 'migrentedavicisi@gmail.com', 'e1r2e3n4', '12.08.1990', 'Erkek', 'Hepsi'),
(9, 'Normal', 'ErenVural', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi'),
(10, 'Normal', 'CozumArayanKisi', 'cozumarayan@gmail.com', 'e1r2e3n4', '12.10.1990', 'Erkek', 'Hepsi'),
(11, 'Normal', 'VuralEren', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment_connections`
--
ALTER TABLE `comment_connections`
  ADD PRIMARY KEY (`commentId`,`commentVoter`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diseases`
--
ALTER TABLE `diseases`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `diseaseName` (`diseaseName`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject_connections`
--
ALTER TABLE `subject_connections`
  ADD PRIMARY KEY (`subjectTitle`,`username`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `subjectTitle` (`subjectTitle`);

--
-- Indexes for table `user_connections`
--
ALTER TABLE `user_connections`
  ADD PRIMARY KEY (`followingUserName`,`followedUserName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `diseases`
--
ALTER TABLE `diseases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;--
-- Database: `id1529553_a7751875_test`
--
CREATE DATABASE IF NOT EXISTS `id1529553_a7751875_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id1529553_a7751875_test`;

-- --------------------------------------------------------

--
-- Table structure for table `comment_connections`
--

CREATE TABLE `comment_connections` (
  `commentId` int(11) NOT NULL,
  `commentVoter` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment_connections`
--

INSERT INTO `comment_connections` (`commentId`, `commentVoter`) VALUES
(1, 'CozumArayanKisi');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `commentSubject` varchar(80) NOT NULL,
  `commentOwner` varchar(24) NOT NULL,
  `commentContent` varchar(500) NOT NULL,
  `commentTotalLikes` int(11) NOT NULL DEFAULT '0',
  `commentDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `commentSubject`, `commentOwner`, `commentContent`, `commentTotalLikes`, `commentDate`) VALUES
(1, 'AIDS kapmamak icin ne yapilmali?', 'Hiv.Bilgilendirici', 'Tanimadiginiz insanlarla cinsel iliskiye girmemek en buyuk tedbirdir.', 0, '2017-05-21 14:40:03'),
(2, 'Migren tedavisinde antidepresan yontemi', 'MigrenTedavicisi', 'Lustral adli ilaci onerebilirim.', 0, '2017-05-24 05:12:26'),
(3, 'Migren tedavisinde antidepresan yontemi', 'LosemiSavascisi', 'Kardesim Prozac kullanmisti, genel olarak memnun kaldigini soyleyebilirim.', 0, '2017-05-25 11:28:27'),
(4, 'Migren tedavisinde antidepresan yontemi', 'ErenVural', 'Alveresi onerebilirim', 0, '2017-05-25 16:12:12');

-- --------------------------------------------------------

--
-- Table structure for table `disease_suggestions`
--

CREATE TABLE `disease_suggestions` (
  `id` int(11) NOT NULL,
  `suggestName` varchar(70) NOT NULL,
  `suggestType` varchar(45) NOT NULL,
  `suggestDescription` text NOT NULL,
  `suggestOwner` varchar(11) NOT NULL,
  `suggestDate` datetime NOT NULL,
  `suggestConfirm` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `disease_suggestions`
--

INSERT INTO `disease_suggestions` (`id`, `suggestName`, `suggestType`, `suggestDescription`, `suggestOwner`, `suggestDate`, `suggestConfirm`) VALUES
(1, 'Sizofreni', 'Ruh Sagligı ve Hastaliklari', 'Sizofreni kisinin neyin gercek neyin hayali oldugunu anlayamadigi bir zihinsel hastalık, bir psikozdur.', 'ErenVural', '2017-05-17 03:09:08', 0);

-- --------------------------------------------------------

--
-- Table structure for table `diseases`
--

CREATE TABLE `diseases` (
  `id` int(11) NOT NULL,
  `diseaseName` varchar(70) NOT NULL,
  `diseaseType` varchar(45) NOT NULL,
  `diseaseDescription` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diseases`
--

INSERT INTO `diseases` (`id`, `diseaseName`, `diseaseType`, `diseaseDescription`) VALUES
(1, 'Losemi', 'Kanser', 'Losemi kan ile alakali bir kanserdir.'),
(2, 'AIDS', 'Cinsel Hastalik', 'AIDS, HIV etkeni nedeniyle insanlarda bagisiklik sisteminin cokmesine neden olan bulasici bir hastalik.'),
(3, 'Migren', 'Norolojik ', 'Migren cogunlukla otonom sinir sisteminde görülen birkac belirtiyle baglantili olan tekrarlayici orta siddette ve siddetli bas agrisi ile karakterize kronik bir rahatsizliktir.'),
(4, 'Reflu', 'Gastroenteroloji', 'Reflu, halk arasinda mide reflüsü olarak bilinen gastro ozofageal reflü hastalıgı, mide iceriginin yemek borusuna geri kacmasidir'),
(5, 'Astim', 'Gogus Hastaliklari', 'Solunum yollarında gerceklesen iltihaplanmalar ve daralmalar nedeniyle ortaya cikan hastaliga astim adı verilir'),
(6, 'Akciger Kanseri', 'Kanser', 'Sigara ve alkol yuzunden tüketimden kaynaklanabilen bir kanser turudur.'),
(7, 'Alkolizm', 'Genel', 'Surekli olarak alkollu icki icenlerde gorulen bedensel ve ruhsal bozuklukların genel adidir.'),
(8, 'Bel Soguklugu', 'Cinsel Hastalik', 'Cinsel birlesme yoluyla bulasan bir bakteriyel enfeksiyondur. Erkeklerde cogunlukla idrar yolunda enfeksiyona neden olur. Kadinlarda idrar yolu, rahim boynu veya her ikisinin de enfekte olmasina neden olabilir.'),
(9, 'Meme Kanseri', 'Kanser', 'Memenin sut bezlerinde ve uretilen sutu meme basına tasıyan kanallari doseyen hucreler arasinda, cesitli etkenler sonucu kontrolsuz sekilde cogalan ve baska organlara yayilma potansiyeli tasiyan hucrelerden meydana gelen tumoral olusumdur.');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `username` varchar(25) COLLATE latin1_general_ci NOT NULL,
  `image_url` varchar(400) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `username`, `image_url`) VALUES
(3, 'user1', 'ftp://a7751875:e1r2e3n4@31.170.160.95/uploads/user10.jpg'),
(2, 'Eren06', 'ftp://a7751875:e1r2e3n4@31.170.160.95/public_html/uploads/Eren06.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `sender` varchar(25) NOT NULL,
  `receiver` varchar(25) NOT NULL,
  `messageContent` varchar(400) NOT NULL,
  `messageDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `sender`, `receiver`, `messageContent`, `messageDate`) VALUES
(1, 'CozumArayanKisi', 'Hiv.Bilgilendirici', 'Merhaba AIDSli tanidigin var mi?', '2017-05-22 10:04:20');

-- --------------------------------------------------------

--
-- Table structure for table `subject_connections`
--

CREATE TABLE `subject_connections` (
  `subjectTitle` varchar(80) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject_connections`
--

INSERT INTO `subject_connections` (`subjectTitle`, `username`) VALUES
('AIDS kapmamak icin ne yapilmali?', 'CozumArayanKisi'),
('Astim ve sigara', 'ErenVural');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `subjectDisease` varchar(400) CHARACTER SET utf8 NOT NULL,
  `subjectTitle` varchar(80) CHARACTER SET utf8 NOT NULL,
  `subjectContent` varchar(500) CHARACTER SET utf8 NOT NULL,
  `subjectOwner` varchar(25) CHARACTER SET utf8 NOT NULL,
  `subjectDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `subjectDisease`, `subjectTitle`, `subjectContent`, `subjectOwner`, `subjectDate`) VALUES
(1, 'AIDS', 'AIDS kapmamak icin ne yapilmali?', 'AIDS bulasmamasi icin gerekli tedbirleri aciklayabilir misiniz?', 'CozumArayanKisi', '2017-05-21 14:12:00'),
(2, 'Migren', 'Migrenin kesin bir tedavisi var midir?', 'Migrenin kesin bir tedavisini ariyorum, bilgi verebilir misiniz?', 'Ankarali.Sifaci', '2017-05-21 07:26:30'),
(3, 'Migren', 'Migren tedavisinde antidepresan yontemi', 'Migren tedavisinde antidepresan kullanıp fayda gören bir tanidigi olan bilgi verebilir mi?', 'CozumArayanKisi', '2017-05-22 09:23:23'),
(4, 'Migren', 'Migren agrisini kesebilcek en guclu agri kesici nedir?', 'Migren sanciniz tuttugunda ilk aldiginiz hap hangisidir?', 'Lokman.Hekim.01', '2017-05-22 04:13:12'),
(5, 'Astim, Gogus Hastaliklari', 'Astim ve sigara', 'Astimi olan biri sigara icerse ne gibi sonuclar ortaya cikar?', 'ErenVural', '2017-05-25 14:37:34');

-- --------------------------------------------------------

--
-- Table structure for table `user_connections`
--

CREATE TABLE `user_connections` (
  `followingUserName` varchar(25) NOT NULL,
  `followedUserName` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_connections`
--

INSERT INTO `user_connections` (`followingUserName`, `followedUserName`) VALUES
('CozumArayanKisi', 'Hiv.Bilgilendirici'),
('ErenVural', 'LosemiSavascisi'),
('ErenVural', 'MigrenTedavicisi');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `profile_status` varchar(11) COLLATE latin1_general_ci NOT NULL DEFAULT 'Normal',
  `username` varchar(25) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 NOT NULL,
  `birthday` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `relatedDisease` varchar(70) COLLATE latin1_general_ci NOT NULL DEFAULT 'Hepsi'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `profile_status`, `username`, `email`, `password`, `birthday`, `gender`, `relatedDisease`) VALUES
(1, 'Normal', 'LosemiSavascisi', 'losemi.savascisi@gmail.com', 'e1r2e3n4', '12.04.1985', 'Erkek', 'Hepsi'),
(2, 'Normal', 'Hiv.Bilgilendirici', 'hiv.bilgilendirici@gmail.com', 'e1r2e3n4', '12.05.1982', 'Kadin', 'Hepsi'),
(3, 'Normal', 'AlkolBagimlisi07', 'alkolbagimlisi@gmail.com', 'e1r2e3n4', '13.10.1990', 'Erkek', 'Hepsi'),
(4, 'Normal', 'Kansiz.Cocuk', 'kansiz.cocuk@gmail.com', 'e1r2e3n4', '12.08.1992', 'Erkek', 'Hepsi'),
(5, 'Normal', 'Alerjik.Kadin', 'alerjikkadin@gmail.com', 'e1r2e3n4', '05.04.1989', 'Kadin', 'Hepsi'),
(6, 'Normal', 'Ankarali.Sifaci', 'ankaralisifaci@gmail.com', 'e1r2e3n4', '04.01.1979', 'Erkek', 'Hepsi'),
(7, 'Normal', 'Lokman.Hekim.01', 'lokmanhekim01@gmail.com', 'e1r2e3n4', '12.03.1981', 'Kadin', 'Hepsi'),
(8, 'Normal', 'MigrenTedavicisi', 'migrentedavicisi@gmail.com', 'e1r2e3n4', '12.08.1990', 'Erkek', 'Hepsi'),
(9, 'Normal', 'ErenVural', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi'),
(10, 'Normal', 'CozumArayanKisi', 'cozumarayan@gmail.com', 'e1r2e3n4', '12.10.1990', 'Erkek', 'Hepsi'),
(11, 'Normal', 'VuralEren', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment_connections`
--
ALTER TABLE `comment_connections`
  ADD PRIMARY KEY (`commentId`,`commentVoter`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diseases`
--
ALTER TABLE `diseases`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `diseaseName` (`diseaseName`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject_connections`
--
ALTER TABLE `subject_connections`
  ADD PRIMARY KEY (`subjectTitle`,`username`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `subjectTitle` (`subjectTitle`);

--
-- Indexes for table `user_connections`
--
ALTER TABLE `user_connections`
  ADD PRIMARY KEY (`followingUserName`,`followedUserName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `diseases`
--
ALTER TABLE `diseases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;--
-- Database: `id1529553_a7751875_test`
--
CREATE DATABASE IF NOT EXISTS `id1529553_a7751875_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id1529553_a7751875_test`;

-- --------------------------------------------------------

--
-- Table structure for table `comment_connections`
--

CREATE TABLE `comment_connections` (
  `commentId` int(11) NOT NULL,
  `commentVoter` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment_connections`
--

INSERT INTO `comment_connections` (`commentId`, `commentVoter`) VALUES
(1, 'CozumArayanKisi');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `commentSubject` varchar(80) NOT NULL,
  `commentOwner` varchar(24) NOT NULL,
  `commentContent` varchar(500) NOT NULL,
  `commentTotalLikes` int(11) NOT NULL DEFAULT '0',
  `commentDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `commentSubject`, `commentOwner`, `commentContent`, `commentTotalLikes`, `commentDate`) VALUES
(1, 'AIDS kapmamak icin ne yapilmali?', 'Hiv.Bilgilendirici', 'Tanimadiginiz insanlarla cinsel iliskiye girmemek en buyuk tedbirdir.', 0, '2017-05-21 14:40:03'),
(2, 'Migren tedavisinde antidepresan yontemi', 'MigrenTedavicisi', 'Lustral adli ilaci onerebilirim.', 0, '2017-05-24 05:12:26'),
(3, 'Migren tedavisinde antidepresan yontemi', 'LosemiSavascisi', 'Kardesim Prozac kullanmisti, genel olarak memnun kaldigini soyleyebilirim.', 0, '2017-05-25 11:28:27'),
(4, 'Migren tedavisinde antidepresan yontemi', 'ErenVural', 'Alveresi onerebilirim', 0, '2017-05-25 16:12:12');

-- --------------------------------------------------------

--
-- Table structure for table `disease_suggestions`
--

CREATE TABLE `disease_suggestions` (
  `id` int(11) NOT NULL,
  `suggestName` varchar(70) NOT NULL,
  `suggestType` varchar(45) NOT NULL,
  `suggestDescription` text NOT NULL,
  `suggestOwner` varchar(11) NOT NULL,
  `suggestDate` datetime NOT NULL,
  `suggestConfirm` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `disease_suggestions`
--

INSERT INTO `disease_suggestions` (`id`, `suggestName`, `suggestType`, `suggestDescription`, `suggestOwner`, `suggestDate`, `suggestConfirm`) VALUES
(1, 'Sizofreni', 'Ruh Sagligı ve Hastaliklari', 'Sizofreni kisinin neyin gercek neyin hayali oldugunu anlayamadigi bir zihinsel hastalık, bir psikozdur.', 'ErenVural', '2017-05-17 03:09:08', 0);

-- --------------------------------------------------------

--
-- Table structure for table `diseases`
--

CREATE TABLE `diseases` (
  `id` int(11) NOT NULL,
  `diseaseName` varchar(70) NOT NULL,
  `diseaseType` varchar(45) NOT NULL,
  `diseaseDescription` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diseases`
--

INSERT INTO `diseases` (`id`, `diseaseName`, `diseaseType`, `diseaseDescription`) VALUES
(1, 'Losemi', 'Kanser', 'Losemi kan ile alakali bir kanserdir.'),
(2, 'AIDS', 'Cinsel Hastalik', 'AIDS, HIV etkeni nedeniyle insanlarda bagisiklik sisteminin cokmesine neden olan bulasici bir hastalik.'),
(3, 'Migren', 'Norolojik ', 'Migren cogunlukla otonom sinir sisteminde görülen birkac belirtiyle baglantili olan tekrarlayici orta siddette ve siddetli bas agrisi ile karakterize kronik bir rahatsizliktir.'),
(4, 'Reflu', 'Gastroenteroloji', 'Reflu, halk arasinda mide reflüsü olarak bilinen gastro ozofageal reflü hastalıgı, mide iceriginin yemek borusuna geri kacmasidir'),
(5, 'Astim', 'Gogus Hastaliklari', 'Solunum yollarında gerceklesen iltihaplanmalar ve daralmalar nedeniyle ortaya cikan hastaliga astim adı verilir'),
(6, 'Akciger Kanseri', 'Kanser', 'Sigara ve alkol yuzunden tüketimden kaynaklanabilen bir kanser turudur.'),
(7, 'Alkolizm', 'Genel', 'Surekli olarak alkollu icki icenlerde gorulen bedensel ve ruhsal bozuklukların genel adidir.'),
(8, 'Bel Soguklugu', 'Cinsel Hastalik', 'Cinsel birlesme yoluyla bulasan bir bakteriyel enfeksiyondur. Erkeklerde cogunlukla idrar yolunda enfeksiyona neden olur. Kadinlarda idrar yolu, rahim boynu veya her ikisinin de enfekte olmasina neden olabilir.'),
(9, 'Meme Kanseri', 'Kanser', 'Memenin sut bezlerinde ve uretilen sutu meme basına tasıyan kanallari doseyen hucreler arasinda, cesitli etkenler sonucu kontrolsuz sekilde cogalan ve baska organlara yayilma potansiyeli tasiyan hucrelerden meydana gelen tumoral olusumdur.');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `username` varchar(25) COLLATE latin1_general_ci NOT NULL,
  `image_url` varchar(400) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `username`, `image_url`) VALUES
(3, 'user1', 'ftp://a7751875:e1r2e3n4@31.170.160.95/uploads/user10.jpg'),
(2, 'Eren06', 'ftp://a7751875:e1r2e3n4@31.170.160.95/public_html/uploads/Eren06.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `sender` varchar(25) NOT NULL,
  `receiver` varchar(25) NOT NULL,
  `messageContent` varchar(400) NOT NULL,
  `messageDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `sender`, `receiver`, `messageContent`, `messageDate`) VALUES
(1, 'CozumArayanKisi', 'Hiv.Bilgilendirici', 'Merhaba AIDSli tanidigin var mi?', '2017-05-22 10:04:20');

-- --------------------------------------------------------

--
-- Table structure for table `subject_connections`
--

CREATE TABLE `subject_connections` (
  `subjectTitle` varchar(80) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject_connections`
--

INSERT INTO `subject_connections` (`subjectTitle`, `username`) VALUES
('AIDS kapmamak icin ne yapilmali?', 'CozumArayanKisi'),
('Astim ve sigara', 'ErenVural');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `subjectDisease` varchar(400) CHARACTER SET utf8 NOT NULL,
  `subjectTitle` varchar(80) CHARACTER SET utf8 NOT NULL,
  `subjectContent` varchar(500) CHARACTER SET utf8 NOT NULL,
  `subjectOwner` varchar(25) CHARACTER SET utf8 NOT NULL,
  `subjectDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `subjectDisease`, `subjectTitle`, `subjectContent`, `subjectOwner`, `subjectDate`) VALUES
(1, 'AIDS', 'AIDS kapmamak icin ne yapilmali?', 'AIDS bulasmamasi icin gerekli tedbirleri aciklayabilir misiniz?', 'CozumArayanKisi', '2017-05-21 14:12:00'),
(2, 'Migren', 'Migrenin kesin bir tedavisi var midir?', 'Migrenin kesin bir tedavisini ariyorum, bilgi verebilir misiniz?', 'Ankarali.Sifaci', '2017-05-21 07:26:30'),
(3, 'Migren', 'Migren tedavisinde antidepresan yontemi', 'Migren tedavisinde antidepresan kullanıp fayda gören bir tanidigi olan bilgi verebilir mi?', 'CozumArayanKisi', '2017-05-22 09:23:23'),
(4, 'Migren', 'Migren agrisini kesebilcek en guclu agri kesici nedir?', 'Migren sanciniz tuttugunda ilk aldiginiz hap hangisidir?', 'Lokman.Hekim.01', '2017-05-22 04:13:12'),
(5, 'Astim, Gogus Hastaliklari', 'Astim ve sigara', 'Astimi olan biri sigara icerse ne gibi sonuclar ortaya cikar?', 'ErenVural', '2017-05-25 14:37:34');

-- --------------------------------------------------------

--
-- Table structure for table `user_connections`
--

CREATE TABLE `user_connections` (
  `followingUserName` varchar(25) NOT NULL,
  `followedUserName` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_connections`
--

INSERT INTO `user_connections` (`followingUserName`, `followedUserName`) VALUES
('CozumArayanKisi', 'Hiv.Bilgilendirici'),
('ErenVural', 'LosemiSavascisi'),
('ErenVural', 'MigrenTedavicisi');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `profile_status` varchar(11) COLLATE latin1_general_ci NOT NULL DEFAULT 'Normal',
  `username` varchar(25) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 NOT NULL,
  `birthday` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `relatedDisease` varchar(70) COLLATE latin1_general_ci NOT NULL DEFAULT 'Hepsi'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `profile_status`, `username`, `email`, `password`, `birthday`, `gender`, `relatedDisease`) VALUES
(1, 'Normal', 'LosemiSavascisi', 'losemi.savascisi@gmail.com', 'e1r2e3n4', '12.04.1985', 'Erkek', 'Hepsi'),
(2, 'Normal', 'Hiv.Bilgilendirici', 'hiv.bilgilendirici@gmail.com', 'e1r2e3n4', '12.05.1982', 'Kadin', 'Hepsi'),
(3, 'Normal', 'AlkolBagimlisi07', 'alkolbagimlisi@gmail.com', 'e1r2e3n4', '13.10.1990', 'Erkek', 'Hepsi'),
(4, 'Normal', 'Kansiz.Cocuk', 'kansiz.cocuk@gmail.com', 'e1r2e3n4', '12.08.1992', 'Erkek', 'Hepsi'),
(5, 'Normal', 'Alerjik.Kadin', 'alerjikkadin@gmail.com', 'e1r2e3n4', '05.04.1989', 'Kadin', 'Hepsi'),
(6, 'Normal', 'Ankarali.Sifaci', 'ankaralisifaci@gmail.com', 'e1r2e3n4', '04.01.1979', 'Erkek', 'Hepsi'),
(7, 'Normal', 'Lokman.Hekim.01', 'lokmanhekim01@gmail.com', 'e1r2e3n4', '12.03.1981', 'Kadin', 'Hepsi'),
(8, 'Normal', 'MigrenTedavicisi', 'migrentedavicisi@gmail.com', 'e1r2e3n4', '12.08.1990', 'Erkek', 'Hepsi'),
(9, 'Normal', 'ErenVural', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi'),
(10, 'Normal', 'CozumArayanKisi', 'cozumarayan@gmail.com', 'e1r2e3n4', '12.10.1990', 'Erkek', 'Hepsi'),
(11, 'Normal', 'VuralEren', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment_connections`
--
ALTER TABLE `comment_connections`
  ADD PRIMARY KEY (`commentId`,`commentVoter`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diseases`
--
ALTER TABLE `diseases`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `diseaseName` (`diseaseName`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject_connections`
--
ALTER TABLE `subject_connections`
  ADD PRIMARY KEY (`subjectTitle`,`username`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `subjectTitle` (`subjectTitle`);

--
-- Indexes for table `user_connections`
--
ALTER TABLE `user_connections`
  ADD PRIMARY KEY (`followingUserName`,`followedUserName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `diseases`
--
ALTER TABLE `diseases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;--
-- Database: `id1529553_a7751875_test`
--
CREATE DATABASE IF NOT EXISTS `id1529553_a7751875_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id1529553_a7751875_test`;

-- --------------------------------------------------------

--
-- Table structure for table `comment_connections`
--

CREATE TABLE `comment_connections` (
  `commentId` int(11) NOT NULL,
  `commentVoter` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment_connections`
--

INSERT INTO `comment_connections` (`commentId`, `commentVoter`) VALUES
(1, 'CozumArayanKisi');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `commentSubject` varchar(80) NOT NULL,
  `commentOwner` varchar(24) NOT NULL,
  `commentContent` varchar(500) NOT NULL,
  `commentTotalLikes` int(11) NOT NULL DEFAULT '0',
  `commentDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `commentSubject`, `commentOwner`, `commentContent`, `commentTotalLikes`, `commentDate`) VALUES
(1, 'AIDS kapmamak icin ne yapilmali?', 'Hiv.Bilgilendirici', 'Tanimadiginiz insanlarla cinsel iliskiye girmemek en buyuk tedbirdir.', 0, '2017-05-21 14:40:03'),
(2, 'Migren tedavisinde antidepresan yontemi', 'MigrenTedavicisi', 'Lustral adli ilaci onerebilirim.', 0, '2017-05-24 05:12:26'),
(3, 'Migren tedavisinde antidepresan yontemi', 'LosemiSavascisi', 'Kardesim Prozac kullanmisti, genel olarak memnun kaldigini soyleyebilirim.', 0, '2017-05-25 11:28:27'),
(4, 'Migren tedavisinde antidepresan yontemi', 'ErenVural', 'Alveresi onerebilirim', 0, '2017-05-25 16:12:12');

-- --------------------------------------------------------

--
-- Table structure for table `disease_suggestions`
--

CREATE TABLE `disease_suggestions` (
  `id` int(11) NOT NULL,
  `suggestName` varchar(70) NOT NULL,
  `suggestType` varchar(45) NOT NULL,
  `suggestDescription` text NOT NULL,
  `suggestOwner` varchar(11) NOT NULL,
  `suggestDate` datetime NOT NULL,
  `suggestConfirm` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `disease_suggestions`
--

INSERT INTO `disease_suggestions` (`id`, `suggestName`, `suggestType`, `suggestDescription`, `suggestOwner`, `suggestDate`, `suggestConfirm`) VALUES
(1, 'Sizofreni', 'Ruh Sagligı ve Hastaliklari', 'Sizofreni kisinin neyin gercek neyin hayali oldugunu anlayamadigi bir zihinsel hastalık, bir psikozdur.', 'ErenVural', '2017-05-17 03:09:08', 0);

-- --------------------------------------------------------

--
-- Table structure for table `diseases`
--

CREATE TABLE `diseases` (
  `id` int(11) NOT NULL,
  `diseaseName` varchar(70) NOT NULL,
  `diseaseType` varchar(45) NOT NULL,
  `diseaseDescription` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diseases`
--

INSERT INTO `diseases` (`id`, `diseaseName`, `diseaseType`, `diseaseDescription`) VALUES
(1, 'Losemi', 'Kanser', 'Losemi kan ile alakali bir kanserdir.'),
(2, 'AIDS', 'Cinsel Hastalik', 'AIDS, HIV etkeni nedeniyle insanlarda bagisiklik sisteminin cokmesine neden olan bulasici bir hastalik.'),
(3, 'Migren', 'Norolojik ', 'Migren cogunlukla otonom sinir sisteminde görülen birkac belirtiyle baglantili olan tekrarlayici orta siddette ve siddetli bas agrisi ile karakterize kronik bir rahatsizliktir.'),
(4, 'Reflu', 'Gastroenteroloji', 'Reflu, halk arasinda mide reflüsü olarak bilinen gastro ozofageal reflü hastalıgı, mide iceriginin yemek borusuna geri kacmasidir'),
(5, 'Astim', 'Gogus Hastaliklari', 'Solunum yollarında gerceklesen iltihaplanmalar ve daralmalar nedeniyle ortaya cikan hastaliga astim adı verilir'),
(6, 'Akciger Kanseri', 'Kanser', 'Sigara ve alkol yuzunden tüketimden kaynaklanabilen bir kanser turudur.'),
(7, 'Alkolizm', 'Genel', 'Surekli olarak alkollu icki icenlerde gorulen bedensel ve ruhsal bozuklukların genel adidir.'),
(8, 'Bel Soguklugu', 'Cinsel Hastalik', 'Cinsel birlesme yoluyla bulasan bir bakteriyel enfeksiyondur. Erkeklerde cogunlukla idrar yolunda enfeksiyona neden olur. Kadinlarda idrar yolu, rahim boynu veya her ikisinin de enfekte olmasina neden olabilir.'),
(9, 'Meme Kanseri', 'Kanser', 'Memenin sut bezlerinde ve uretilen sutu meme basına tasıyan kanallari doseyen hucreler arasinda, cesitli etkenler sonucu kontrolsuz sekilde cogalan ve baska organlara yayilma potansiyeli tasiyan hucrelerden meydana gelen tumoral olusumdur.');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `username` varchar(25) COLLATE latin1_general_ci NOT NULL,
  `image_url` varchar(400) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `username`, `image_url`) VALUES
(3, 'user1', 'ftp://a7751875:e1r2e3n4@31.170.160.95/uploads/user10.jpg'),
(2, 'Eren06', 'ftp://a7751875:e1r2e3n4@31.170.160.95/public_html/uploads/Eren06.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `sender` varchar(25) NOT NULL,
  `receiver` varchar(25) NOT NULL,
  `messageContent` varchar(400) NOT NULL,
  `messageDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `sender`, `receiver`, `messageContent`, `messageDate`) VALUES
(1, 'CozumArayanKisi', 'Hiv.Bilgilendirici', 'Merhaba AIDSli tanidigin var mi?', '2017-05-22 10:04:20');

-- --------------------------------------------------------

--
-- Table structure for table `subject_connections`
--

CREATE TABLE `subject_connections` (
  `subjectTitle` varchar(80) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject_connections`
--

INSERT INTO `subject_connections` (`subjectTitle`, `username`) VALUES
('AIDS kapmamak icin ne yapilmali?', 'CozumArayanKisi'),
('Astim ve sigara', 'ErenVural');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `subjectDisease` varchar(400) CHARACTER SET utf8 NOT NULL,
  `subjectTitle` varchar(80) CHARACTER SET utf8 NOT NULL,
  `subjectContent` varchar(500) CHARACTER SET utf8 NOT NULL,
  `subjectOwner` varchar(25) CHARACTER SET utf8 NOT NULL,
  `subjectDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `subjectDisease`, `subjectTitle`, `subjectContent`, `subjectOwner`, `subjectDate`) VALUES
(1, 'AIDS', 'AIDS kapmamak icin ne yapilmali?', 'AIDS bulasmamasi icin gerekli tedbirleri aciklayabilir misiniz?', 'CozumArayanKisi', '2017-05-21 14:12:00'),
(2, 'Migren', 'Migrenin kesin bir tedavisi var midir?', 'Migrenin kesin bir tedavisini ariyorum, bilgi verebilir misiniz?', 'Ankarali.Sifaci', '2017-05-21 07:26:30'),
(3, 'Migren', 'Migren tedavisinde antidepresan yontemi', 'Migren tedavisinde antidepresan kullanıp fayda gören bir tanidigi olan bilgi verebilir mi?', 'CozumArayanKisi', '2017-05-22 09:23:23'),
(4, 'Migren', 'Migren agrisini kesebilcek en guclu agri kesici nedir?', 'Migren sanciniz tuttugunda ilk aldiginiz hap hangisidir?', 'Lokman.Hekim.01', '2017-05-22 04:13:12'),
(5, 'Astim, Gogus Hastaliklari', 'Astim ve sigara', 'Astimi olan biri sigara icerse ne gibi sonuclar ortaya cikar?', 'ErenVural', '2017-05-25 14:37:34');

-- --------------------------------------------------------

--
-- Table structure for table `user_connections`
--

CREATE TABLE `user_connections` (
  `followingUserName` varchar(25) NOT NULL,
  `followedUserName` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_connections`
--

INSERT INTO `user_connections` (`followingUserName`, `followedUserName`) VALUES
('CozumArayanKisi', 'Hiv.Bilgilendirici'),
('ErenVural', 'LosemiSavascisi'),
('ErenVural', 'MigrenTedavicisi');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `profile_status` varchar(11) COLLATE latin1_general_ci NOT NULL DEFAULT 'Normal',
  `username` varchar(25) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 NOT NULL,
  `birthday` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `relatedDisease` varchar(70) COLLATE latin1_general_ci NOT NULL DEFAULT 'Hepsi'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `profile_status`, `username`, `email`, `password`, `birthday`, `gender`, `relatedDisease`) VALUES
(1, 'Normal', 'LosemiSavascisi', 'losemi.savascisi@gmail.com', 'e1r2e3n4', '12.04.1985', 'Erkek', 'Hepsi'),
(2, 'Normal', 'Hiv.Bilgilendirici', 'hiv.bilgilendirici@gmail.com', 'e1r2e3n4', '12.05.1982', 'Kadin', 'Hepsi'),
(3, 'Normal', 'AlkolBagimlisi07', 'alkolbagimlisi@gmail.com', 'e1r2e3n4', '13.10.1990', 'Erkek', 'Hepsi'),
(4, 'Normal', 'Kansiz.Cocuk', 'kansiz.cocuk@gmail.com', 'e1r2e3n4', '12.08.1992', 'Erkek', 'Hepsi'),
(5, 'Normal', 'Alerjik.Kadin', 'alerjikkadin@gmail.com', 'e1r2e3n4', '05.04.1989', 'Kadin', 'Hepsi'),
(6, 'Normal', 'Ankarali.Sifaci', 'ankaralisifaci@gmail.com', 'e1r2e3n4', '04.01.1979', 'Erkek', 'Hepsi'),
(7, 'Normal', 'Lokman.Hekim.01', 'lokmanhekim01@gmail.com', 'e1r2e3n4', '12.03.1981', 'Kadin', 'Hepsi'),
(8, 'Normal', 'MigrenTedavicisi', 'migrentedavicisi@gmail.com', 'e1r2e3n4', '12.08.1990', 'Erkek', 'Hepsi'),
(9, 'Normal', 'ErenVural', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi'),
(10, 'Normal', 'CozumArayanKisi', 'cozumarayan@gmail.com', 'e1r2e3n4', '12.10.1990', 'Erkek', 'Hepsi'),
(11, 'Normal', 'VuralEren', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment_connections`
--
ALTER TABLE `comment_connections`
  ADD PRIMARY KEY (`commentId`,`commentVoter`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diseases`
--
ALTER TABLE `diseases`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `diseaseName` (`diseaseName`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject_connections`
--
ALTER TABLE `subject_connections`
  ADD PRIMARY KEY (`subjectTitle`,`username`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `subjectTitle` (`subjectTitle`);

--
-- Indexes for table `user_connections`
--
ALTER TABLE `user_connections`
  ADD PRIMARY KEY (`followingUserName`,`followedUserName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `diseases`
--
ALTER TABLE `diseases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;--
-- Database: `id1529553_a7751875_test`
--
CREATE DATABASE IF NOT EXISTS `id1529553_a7751875_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id1529553_a7751875_test`;

-- --------------------------------------------------------

--
-- Table structure for table `comment_connections`
--

CREATE TABLE `comment_connections` (
  `commentId` int(11) NOT NULL,
  `commentVoter` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment_connections`
--

INSERT INTO `comment_connections` (`commentId`, `commentVoter`) VALUES
(1, 'CozumArayanKisi');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `commentSubject` varchar(80) NOT NULL,
  `commentOwner` varchar(24) NOT NULL,
  `commentContent` varchar(500) NOT NULL,
  `commentTotalLikes` int(11) NOT NULL DEFAULT '0',
  `commentDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `commentSubject`, `commentOwner`, `commentContent`, `commentTotalLikes`, `commentDate`) VALUES
(1, 'AIDS kapmamak icin ne yapilmali?', 'Hiv.Bilgilendirici', 'Tanimadiginiz insanlarla cinsel iliskiye girmemek en buyuk tedbirdir.', 0, '2017-05-21 14:40:03'),
(2, 'Migren tedavisinde antidepresan yontemi', 'MigrenTedavicisi', 'Lustral adli ilaci onerebilirim.', 0, '2017-05-24 05:12:26'),
(3, 'Migren tedavisinde antidepresan yontemi', 'LosemiSavascisi', 'Kardesim Prozac kullanmisti, genel olarak memnun kaldigini soyleyebilirim.', 0, '2017-05-25 11:28:27'),
(4, 'Migren tedavisinde antidepresan yontemi', 'ErenVural', 'Alveresi onerebilirim', 0, '2017-05-25 16:12:12');

-- --------------------------------------------------------

--
-- Table structure for table `disease_suggestions`
--

CREATE TABLE `disease_suggestions` (
  `id` int(11) NOT NULL,
  `suggestName` varchar(70) NOT NULL,
  `suggestType` varchar(45) NOT NULL,
  `suggestDescription` text NOT NULL,
  `suggestOwner` varchar(11) NOT NULL,
  `suggestDate` datetime NOT NULL,
  `suggestConfirm` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `disease_suggestions`
--

INSERT INTO `disease_suggestions` (`id`, `suggestName`, `suggestType`, `suggestDescription`, `suggestOwner`, `suggestDate`, `suggestConfirm`) VALUES
(1, 'Sizofreni', 'Ruh Sagligı ve Hastaliklari', 'Sizofreni kisinin neyin gercek neyin hayali oldugunu anlayamadigi bir zihinsel hastalık, bir psikozdur.', 'ErenVural', '2017-05-17 03:09:08', 0);

-- --------------------------------------------------------

--
-- Table structure for table `diseases`
--

CREATE TABLE `diseases` (
  `id` int(11) NOT NULL,
  `diseaseName` varchar(70) NOT NULL,
  `diseaseType` varchar(45) NOT NULL,
  `diseaseDescription` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diseases`
--

INSERT INTO `diseases` (`id`, `diseaseName`, `diseaseType`, `diseaseDescription`) VALUES
(1, 'Losemi', 'Kanser', 'Losemi kan ile alakali bir kanserdir.'),
(2, 'AIDS', 'Cinsel Hastalik', 'AIDS, HIV etkeni nedeniyle insanlarda bagisiklik sisteminin cokmesine neden olan bulasici bir hastalik.'),
(3, 'Migren', 'Norolojik ', 'Migren cogunlukla otonom sinir sisteminde görülen birkac belirtiyle baglantili olan tekrarlayici orta siddette ve siddetli bas agrisi ile karakterize kronik bir rahatsizliktir.'),
(4, 'Reflu', 'Gastroenteroloji', 'Reflu, halk arasinda mide reflüsü olarak bilinen gastro ozofageal reflü hastalıgı, mide iceriginin yemek borusuna geri kacmasidir'),
(5, 'Astim', 'Gogus Hastaliklari', 'Solunum yollarında gerceklesen iltihaplanmalar ve daralmalar nedeniyle ortaya cikan hastaliga astim adı verilir'),
(6, 'Akciger Kanseri', 'Kanser', 'Sigara ve alkol yuzunden tüketimden kaynaklanabilen bir kanser turudur.'),
(7, 'Alkolizm', 'Genel', 'Surekli olarak alkollu icki icenlerde gorulen bedensel ve ruhsal bozuklukların genel adidir.'),
(8, 'Bel Soguklugu', 'Cinsel Hastalik', 'Cinsel birlesme yoluyla bulasan bir bakteriyel enfeksiyondur. Erkeklerde cogunlukla idrar yolunda enfeksiyona neden olur. Kadinlarda idrar yolu, rahim boynu veya her ikisinin de enfekte olmasina neden olabilir.'),
(9, 'Meme Kanseri', 'Kanser', 'Memenin sut bezlerinde ve uretilen sutu meme basına tasıyan kanallari doseyen hucreler arasinda, cesitli etkenler sonucu kontrolsuz sekilde cogalan ve baska organlara yayilma potansiyeli tasiyan hucrelerden meydana gelen tumoral olusumdur.');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `username` varchar(25) COLLATE latin1_general_ci NOT NULL,
  `image_url` varchar(400) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `username`, `image_url`) VALUES
(3, 'user1', 'ftp://a7751875:e1r2e3n4@31.170.160.95/uploads/user10.jpg'),
(2, 'Eren06', 'ftp://a7751875:e1r2e3n4@31.170.160.95/public_html/uploads/Eren06.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `sender` varchar(25) NOT NULL,
  `receiver` varchar(25) NOT NULL,
  `messageContent` varchar(400) NOT NULL,
  `messageDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `sender`, `receiver`, `messageContent`, `messageDate`) VALUES
(1, 'CozumArayanKisi', 'Hiv.Bilgilendirici', 'Merhaba AIDSli tanidigin var mi?', '2017-05-22 10:04:20');

-- --------------------------------------------------------

--
-- Table structure for table `subject_connections`
--

CREATE TABLE `subject_connections` (
  `subjectTitle` varchar(80) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject_connections`
--

INSERT INTO `subject_connections` (`subjectTitle`, `username`) VALUES
('AIDS kapmamak icin ne yapilmali?', 'CozumArayanKisi'),
('Astim ve sigara', 'ErenVural');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `subjectDisease` varchar(400) CHARACTER SET utf8 NOT NULL,
  `subjectTitle` varchar(80) CHARACTER SET utf8 NOT NULL,
  `subjectContent` varchar(500) CHARACTER SET utf8 NOT NULL,
  `subjectOwner` varchar(25) CHARACTER SET utf8 NOT NULL,
  `subjectDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `subjectDisease`, `subjectTitle`, `subjectContent`, `subjectOwner`, `subjectDate`) VALUES
(1, 'AIDS', 'AIDS kapmamak icin ne yapilmali?', 'AIDS bulasmamasi icin gerekli tedbirleri aciklayabilir misiniz?', 'CozumArayanKisi', '2017-05-21 14:12:00'),
(2, 'Migren', 'Migrenin kesin bir tedavisi var midir?', 'Migrenin kesin bir tedavisini ariyorum, bilgi verebilir misiniz?', 'Ankarali.Sifaci', '2017-05-21 07:26:30'),
(3, 'Migren', 'Migren tedavisinde antidepresan yontemi', 'Migren tedavisinde antidepresan kullanıp fayda gören bir tanidigi olan bilgi verebilir mi?', 'CozumArayanKisi', '2017-05-22 09:23:23'),
(4, 'Migren', 'Migren agrisini kesebilcek en guclu agri kesici nedir?', 'Migren sanciniz tuttugunda ilk aldiginiz hap hangisidir?', 'Lokman.Hekim.01', '2017-05-22 04:13:12'),
(5, 'Astim, Gogus Hastaliklari', 'Astim ve sigara', 'Astimi olan biri sigara icerse ne gibi sonuclar ortaya cikar?', 'ErenVural', '2017-05-25 14:37:34');

-- --------------------------------------------------------

--
-- Table structure for table `user_connections`
--

CREATE TABLE `user_connections` (
  `followingUserName` varchar(25) NOT NULL,
  `followedUserName` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_connections`
--

INSERT INTO `user_connections` (`followingUserName`, `followedUserName`) VALUES
('CozumArayanKisi', 'Hiv.Bilgilendirici'),
('ErenVural', 'LosemiSavascisi'),
('ErenVural', 'MigrenTedavicisi');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `profile_status` varchar(11) COLLATE latin1_general_ci NOT NULL DEFAULT 'Normal',
  `username` varchar(25) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 NOT NULL,
  `birthday` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `relatedDisease` varchar(70) COLLATE latin1_general_ci NOT NULL DEFAULT 'Hepsi'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `profile_status`, `username`, `email`, `password`, `birthday`, `gender`, `relatedDisease`) VALUES
(1, 'Normal', 'LosemiSavascisi', 'losemi.savascisi@gmail.com', 'e1r2e3n4', '12.04.1985', 'Erkek', 'Hepsi'),
(2, 'Normal', 'Hiv.Bilgilendirici', 'hiv.bilgilendirici@gmail.com', 'e1r2e3n4', '12.05.1982', 'Kadin', 'Hepsi'),
(3, 'Normal', 'AlkolBagimlisi07', 'alkolbagimlisi@gmail.com', 'e1r2e3n4', '13.10.1990', 'Erkek', 'Hepsi'),
(4, 'Normal', 'Kansiz.Cocuk', 'kansiz.cocuk@gmail.com', 'e1r2e3n4', '12.08.1992', 'Erkek', 'Hepsi'),
(5, 'Normal', 'Alerjik.Kadin', 'alerjikkadin@gmail.com', 'e1r2e3n4', '05.04.1989', 'Kadin', 'Hepsi'),
(6, 'Normal', 'Ankarali.Sifaci', 'ankaralisifaci@gmail.com', 'e1r2e3n4', '04.01.1979', 'Erkek', 'Hepsi'),
(7, 'Normal', 'Lokman.Hekim.01', 'lokmanhekim01@gmail.com', 'e1r2e3n4', '12.03.1981', 'Kadin', 'Hepsi'),
(8, 'Normal', 'MigrenTedavicisi', 'migrentedavicisi@gmail.com', 'e1r2e3n4', '12.08.1990', 'Erkek', 'Hepsi'),
(9, 'Normal', 'ErenVural', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi'),
(10, 'Normal', 'CozumArayanKisi', 'cozumarayan@gmail.com', 'e1r2e3n4', '12.10.1990', 'Erkek', 'Hepsi'),
(11, 'Normal', 'VuralEren', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment_connections`
--
ALTER TABLE `comment_connections`
  ADD PRIMARY KEY (`commentId`,`commentVoter`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diseases`
--
ALTER TABLE `diseases`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `diseaseName` (`diseaseName`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject_connections`
--
ALTER TABLE `subject_connections`
  ADD PRIMARY KEY (`subjectTitle`,`username`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `subjectTitle` (`subjectTitle`);

--
-- Indexes for table `user_connections`
--
ALTER TABLE `user_connections`
  ADD PRIMARY KEY (`followingUserName`,`followedUserName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `diseases`
--
ALTER TABLE `diseases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;--
-- Database: `id1529553_a7751875_test`
--
CREATE DATABASE IF NOT EXISTS `id1529553_a7751875_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `id1529553_a7751875_test`;

-- --------------------------------------------------------

--
-- Table structure for table `comment_connections`
--

CREATE TABLE `comment_connections` (
  `commentId` int(11) NOT NULL,
  `commentVoter` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment_connections`
--

INSERT INTO `comment_connections` (`commentId`, `commentVoter`) VALUES
(1, 'CozumArayanKisi');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `commentSubject` varchar(80) NOT NULL,
  `commentOwner` varchar(24) NOT NULL,
  `commentContent` varchar(500) NOT NULL,
  `commentTotalLikes` int(11) NOT NULL DEFAULT '0',
  `commentDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `commentSubject`, `commentOwner`, `commentContent`, `commentTotalLikes`, `commentDate`) VALUES
(1, 'AIDS kapmamak icin ne yapilmali?', 'Hiv.Bilgilendirici', 'Tanimadiginiz insanlarla cinsel iliskiye girmemek en buyuk tedbirdir.', 0, '2017-05-21 14:40:03'),
(2, 'Migren tedavisinde antidepresan yontemi', 'MigrenTedavicisi', 'Lustral adli ilaci onerebilirim.', 0, '2017-05-24 05:12:26'),
(3, 'Migren tedavisinde antidepresan yontemi', 'LosemiSavascisi', 'Kardesim Prozac kullanmisti, genel olarak memnun kaldigini soyleyebilirim.', 0, '2017-05-25 11:28:27'),
(4, 'Migren tedavisinde antidepresan yontemi', 'ErenVural', 'Alveresi onerebilirim', 0, '2017-05-25 16:12:12');

-- --------------------------------------------------------

--
-- Table structure for table `disease_suggestions`
--

CREATE TABLE `disease_suggestions` (
  `id` int(11) NOT NULL,
  `suggestName` varchar(70) NOT NULL,
  `suggestType` varchar(45) NOT NULL,
  `suggestDescription` text NOT NULL,
  `suggestOwner` varchar(11) NOT NULL,
  `suggestDate` datetime NOT NULL,
  `suggestConfirm` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `disease_suggestions`
--

INSERT INTO `disease_suggestions` (`id`, `suggestName`, `suggestType`, `suggestDescription`, `suggestOwner`, `suggestDate`, `suggestConfirm`) VALUES
(1, 'Sizofreni', 'Ruh Sagligı ve Hastaliklari', 'Sizofreni kisinin neyin gercek neyin hayali oldugunu anlayamadigi bir zihinsel hastalık, bir psikozdur.', 'ErenVural', '2017-05-17 03:09:08', 0);

-- --------------------------------------------------------

--
-- Table structure for table `diseases`
--

CREATE TABLE `diseases` (
  `id` int(11) NOT NULL,
  `diseaseName` varchar(70) NOT NULL,
  `diseaseType` varchar(45) NOT NULL,
  `diseaseDescription` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `diseases`
--

INSERT INTO `diseases` (`id`, `diseaseName`, `diseaseType`, `diseaseDescription`) VALUES
(1, 'Losemi', 'Kanser', 'Losemi kan ile alakali bir kanserdir.'),
(2, 'AIDS', 'Cinsel Hastalik', 'AIDS, HIV etkeni nedeniyle insanlarda bagisiklik sisteminin cokmesine neden olan bulasici bir hastalik.'),
(3, 'Migren', 'Norolojik ', 'Migren cogunlukla otonom sinir sisteminde görülen birkac belirtiyle baglantili olan tekrarlayici orta siddette ve siddetli bas agrisi ile karakterize kronik bir rahatsizliktir.'),
(4, 'Reflu', 'Gastroenteroloji', 'Reflu, halk arasinda mide reflüsü olarak bilinen gastro ozofageal reflü hastalıgı, mide iceriginin yemek borusuna geri kacmasidir'),
(5, 'Astim', 'Gogus Hastaliklari', 'Solunum yollarında gerceklesen iltihaplanmalar ve daralmalar nedeniyle ortaya cikan hastaliga astim adı verilir'),
(6, 'Akciger Kanseri', 'Kanser', 'Sigara ve alkol yuzunden tüketimden kaynaklanabilen bir kanser turudur.'),
(7, 'Alkolizm', 'Genel', 'Surekli olarak alkollu icki icenlerde gorulen bedensel ve ruhsal bozuklukların genel adidir.'),
(8, 'Bel Soguklugu', 'Cinsel Hastalik', 'Cinsel birlesme yoluyla bulasan bir bakteriyel enfeksiyondur. Erkeklerde cogunlukla idrar yolunda enfeksiyona neden olur. Kadinlarda idrar yolu, rahim boynu veya her ikisinin de enfekte olmasina neden olabilir.'),
(9, 'Meme Kanseri', 'Kanser', 'Memenin sut bezlerinde ve uretilen sutu meme basına tasıyan kanallari doseyen hucreler arasinda, cesitli etkenler sonucu kontrolsuz sekilde cogalan ve baska organlara yayilma potansiyeli tasiyan hucrelerden meydana gelen tumoral olusumdur.');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `username` varchar(25) COLLATE latin1_general_ci NOT NULL,
  `image_url` varchar(400) COLLATE latin1_general_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`id`, `username`, `image_url`) VALUES
(3, 'user1', 'ftp://a7751875:e1r2e3n4@31.170.160.95/uploads/user10.jpg'),
(2, 'Eren06', 'ftp://a7751875:e1r2e3n4@31.170.160.95/public_html/uploads/Eren06.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `sender` varchar(25) NOT NULL,
  `receiver` varchar(25) NOT NULL,
  `messageContent` varchar(400) NOT NULL,
  `messageDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `sender`, `receiver`, `messageContent`, `messageDate`) VALUES
(1, 'CozumArayanKisi', 'Hiv.Bilgilendirici', 'Merhaba AIDSli tanidigin var mi?', '2017-05-22 10:04:20');

-- --------------------------------------------------------

--
-- Table structure for table `subject_connections`
--

CREATE TABLE `subject_connections` (
  `subjectTitle` varchar(80) NOT NULL,
  `username` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject_connections`
--

INSERT INTO `subject_connections` (`subjectTitle`, `username`) VALUES
('AIDS kapmamak icin ne yapilmali?', 'CozumArayanKisi'),
('Astim ve sigara', 'ErenVural');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `id` int(11) NOT NULL,
  `subjectDisease` varchar(400) CHARACTER SET utf8 NOT NULL,
  `subjectTitle` varchar(80) CHARACTER SET utf8 NOT NULL,
  `subjectContent` varchar(500) CHARACTER SET utf8 NOT NULL,
  `subjectOwner` varchar(25) CHARACTER SET utf8 NOT NULL,
  `subjectDate` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`id`, `subjectDisease`, `subjectTitle`, `subjectContent`, `subjectOwner`, `subjectDate`) VALUES
(1, 'AIDS', 'AIDS kapmamak icin ne yapilmali?', 'AIDS bulasmamasi icin gerekli tedbirleri aciklayabilir misiniz?', 'CozumArayanKisi', '2017-05-21 14:12:00'),
(2, 'Migren', 'Migrenin kesin bir tedavisi var midir?', 'Migrenin kesin bir tedavisini ariyorum, bilgi verebilir misiniz?', 'Ankarali.Sifaci', '2017-05-21 07:26:30'),
(3, 'Migren', 'Migren tedavisinde antidepresan yontemi', 'Migren tedavisinde antidepresan kullanıp fayda gören bir tanidigi olan bilgi verebilir mi?', 'CozumArayanKisi', '2017-05-22 09:23:23'),
(4, 'Migren', 'Migren agrisini kesebilcek en guclu agri kesici nedir?', 'Migren sanciniz tuttugunda ilk aldiginiz hap hangisidir?', 'Lokman.Hekim.01', '2017-05-22 04:13:12'),
(5, 'Astim, Gogus Hastaliklari', 'Astim ve sigara', 'Astimi olan biri sigara icerse ne gibi sonuclar ortaya cikar?', 'ErenVural', '2017-05-25 14:37:34');

-- --------------------------------------------------------

--
-- Table structure for table `user_connections`
--

CREATE TABLE `user_connections` (
  `followingUserName` varchar(25) NOT NULL,
  `followedUserName` varchar(25) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_connections`
--

INSERT INTO `user_connections` (`followingUserName`, `followedUserName`) VALUES
('CozumArayanKisi', 'Hiv.Bilgilendirici'),
('ErenVural', 'LosemiSavascisi'),
('ErenVural', 'MigrenTedavicisi');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `profile_status` varchar(11) COLLATE latin1_general_ci NOT NULL DEFAULT 'Normal',
  `username` varchar(25) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(45) CHARACTER SET utf8 NOT NULL,
  `birthday` varchar(45) COLLATE latin1_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `relatedDisease` varchar(70) COLLATE latin1_general_ci NOT NULL DEFAULT 'Hepsi'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `profile_status`, `username`, `email`, `password`, `birthday`, `gender`, `relatedDisease`) VALUES
(1, 'Normal', 'LosemiSavascisi', 'losemi.savascisi@gmail.com', 'e1r2e3n4', '12.04.1985', 'Erkek', 'Hepsi'),
(2, 'Normal', 'Hiv.Bilgilendirici', 'hiv.bilgilendirici@gmail.com', 'e1r2e3n4', '12.05.1982', 'Kadin', 'Hepsi'),
(3, 'Normal', 'AlkolBagimlisi07', 'alkolbagimlisi@gmail.com', 'e1r2e3n4', '13.10.1990', 'Erkek', 'Hepsi'),
(4, 'Normal', 'Kansiz.Cocuk', 'kansiz.cocuk@gmail.com', 'e1r2e3n4', '12.08.1992', 'Erkek', 'Hepsi'),
(5, 'Normal', 'Alerjik.Kadin', 'alerjikkadin@gmail.com', 'e1r2e3n4', '05.04.1989', 'Kadin', 'Hepsi'),
(6, 'Normal', 'Ankarali.Sifaci', 'ankaralisifaci@gmail.com', 'e1r2e3n4', '04.01.1979', 'Erkek', 'Hepsi'),
(7, 'Normal', 'Lokman.Hekim.01', 'lokmanhekim01@gmail.com', 'e1r2e3n4', '12.03.1981', 'Kadin', 'Hepsi'),
(8, 'Normal', 'MigrenTedavicisi', 'migrentedavicisi@gmail.com', 'e1r2e3n4', '12.08.1990', 'Erkek', 'Hepsi'),
(9, 'Normal', 'ErenVural', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi'),
(10, 'Normal', 'CozumArayanKisi', 'cozumarayan@gmail.com', 'e1r2e3n4', '12.10.1990', 'Erkek', 'Hepsi'),
(11, 'Normal', 'VuralEren', 'evural06@gmail.com', 'e1r2e3n4', '16.05.1993', 'Erkek', 'Hepsi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment_connections`
--
ALTER TABLE `comment_connections`
  ADD PRIMARY KEY (`commentId`,`commentVoter`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `diseases`
--
ALTER TABLE `diseases`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `diseaseName` (`diseaseName`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subject_connections`
--
ALTER TABLE `subject_connections`
  ADD PRIMARY KEY (`subjectTitle`,`username`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `subjectTitle` (`subjectTitle`);

--
-- Indexes for table `user_connections`
--
ALTER TABLE `user_connections`
  ADD PRIMARY KEY (`followingUserName`,`followedUserName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `disease_suggestions`
--
ALTER TABLE `disease_suggestions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `diseases`
--
ALTER TABLE `diseases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
