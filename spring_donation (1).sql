-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 27, 2021 lúc 05:34 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `spring_donation`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donation`
--

CREATE TABLE `donation` (
  `id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `organization_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donation`
--

INSERT INTO `donation` (`id`, `code`, `created`, `description`, `end_date`, `money`, `name`, `organization_name`, `phone_number`, `start_date`, `status`) VALUES
(1, 'QG001', NULL, 'Alo alo', '02/12/2021', 570000, 'Giúp đỡ trẻ em nghèo', 'Hội từ thiện', '0123456789', '01/12/2021', 1),
(3, 'QG004', NULL, 'Alo alo', '02/12/2021', 100000, 'Giúp đỡ vùng cao', 'Hội từ thiện', '0123456789', '01/12/2021', 3),
(4, 'QG003', NULL, 'Alo alo', '02/12/2021', 100000, 'Giúp đỡ vùng lũ', 'Hội từ thiện', '0123456789', '01/12/2021', 3),
(19, 'CAO1', '2021-12-26', 'Hỗ trợ mùa lũ cho đồng bào miền trung', '2022-01-30', 0, 'Từ thiện lũ', 'Hội hỗ trợ mùa lũ', '0394072568', '2022-01-01', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'User'),
(2, 'Admin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `created` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `address`, `email`, `full_name`, `note`, `password`, `phone_number`, `status`, `user_name`, `role_id`, `created`) VALUES
(2, 'Khối phố 8 phường An Sơn thành phố Tam Kỳ', 'nguyencaonguyencmu@gmail.com', 'Nguyen Nguyen Cao', 'wellcome to my chanel', '66c6883a896b36c3734abccad05d84e1', '0394073759', 1, 'nguyennguyen', 1, '2021-12-27'),
(3, 'Khối phố 8 phường An Sơn thành phố Tam Kỳ', 'admin@gmail.com', 'Quản trị', NULL, '66c6883a896b36c3734abccad05d84e1', '0394073759', 1, 'nguyennguyen', 2, '2021-12-26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_donation`
--

CREATE TABLE `user_donation` (
  `id` int(11) NOT NULL,
  `created` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `donation_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user_donation`
--

INSERT INTO `user_donation` (`id`, `created`, `money`, `name`, `status`, `donation_id`, `user_id`, `text`) VALUES
(2, '2021-12-26', 120000, 'Nguyen Nguyen Cao', 1, 1, 2, 'ok'),
(3, '2021-12-27', 200000, 'Minh Nhã', 1, 1, NULL, '\r\n                  ok'),
(4, '2021-12-27', 50000, 'Minh Huy', 1, 1, NULL, '\r\n                         Cảm ơn                       '),
(5, '2021-12-27', 50000, 'Quang Huy', 0, 1, NULL, '\r\n                                                ok');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `donation`
--
ALTER TABLE `donation`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- Chỉ mục cho bảng `user_donation`
--
ALTER TABLE `user_donation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo6fstupjtrgax6hbbpvnge7w4` (`donation_id`),
  ADD KEY `FKaogyiuu1jj92bp58y9p9e7h5v` (`user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donation`
--
ALTER TABLE `donation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `user_donation`
--
ALTER TABLE `user_donation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
