/*
 Navicat Premium Data Transfer

 Source Server         : xiEr
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : mysql

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 18/01/2022 23:55:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for weather
-- ----------------------------
DROP TABLE IF EXISTS `weather`;
CREATE TABLE `weather`  (
  `id` int NOT NULL,
  `fxDate` datetime NOT NULL,
  `tempMax` int NOT NULL,
  `tempMin` int NOT NULL,
  `textDay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`, `fxDate`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of weather
-- ----------------------------
INSERT INTO `weather` VALUES (101010100, '2022-01-18 00:00:00', 3, -7, '晴');
INSERT INTO `weather` VALUES (101010100, '2022-01-19 00:00:00', 3, -7, '晴');
INSERT INTO `weather` VALUES (101010100, '2022-01-20 00:00:00', -1, -8, '多云');
INSERT INTO `weather` VALUES (101020100, '2022-01-18 00:00:00', 11, 6, '晴');
INSERT INTO `weather` VALUES (101020100, '2022-01-19 00:00:00', 11, 6, '晴');
INSERT INTO `weather` VALUES (101020100, '2022-01-20 00:00:00', 14, 5, '多云');
INSERT INTO `weather` VALUES (101230101, '2022-01-18 00:00:00', 13, 11, '阴');
INSERT INTO `weather` VALUES (101230101, '2022-01-19 00:00:00', 13, 11, '阴');
INSERT INTO `weather` VALUES (101230101, '2022-01-20 00:00:00', 19, 10, '阴');

SET FOREIGN_KEY_CHECKS = 1;
