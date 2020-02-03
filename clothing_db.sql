/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.62 : Database - clothing_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`clothing_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `clothing_db`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `price` double(10,2) NOT NULL COMMENT '商品价格',
  `imge` varchar(255) DEFAULT NULL COMMENT '商品图片地址',
  `good_date` datetime DEFAULT NULL COMMENT '商品上架日期',
  `desc` varchar(100) DEFAULT NULL COMMENT '商品描述',
  `stock` int(5) DEFAULT NULL COMMENT '商品库存',
  `goods_type_id` int(11) DEFAULT NULL COMMENT '外键-商品分类',
  PRIMARY KEY (`goods_id`),
  KEY `goods_type_id` (`goods_type_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`goods_type_id`) REFERENCES `goods_type` (`goods_type_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`goods_id`,`name`,`price`,`imge`,`good_date`,`desc`,`stock`,`goods_type_id`) values (1,'圆领针织衫',249.00,'static/img/1577265469.jpg',NULL,'采用优质羊毛面料制成，质地上乘！商务休闲两相宜。',95,1),(2,'瓜皮帽',199.00,'static/img/320777.jpg',NULL,'图文不符，请勿在意',0,3),(11,'易打理精纺针织衬衫(条纹)(长袖)',199.00,'static/img/1577341639.jpg','2019-12-26 17:20:24','具有柔软、舒适的穿着感受。洗后较不易起皱是其魅力所在。',80,1),(19,'卫衣',149.00,'/static/2.jpg','2019-12-27 12:02:36','加厚',35,1),(20,'EZY紧身彩色长裤',149.00,'/static/1.jpg','2019-12-30 00:05:39','采用具有伸展性、便于活动的弹力面料制成的新潮彩色裤子。',60,2);

/*Table structure for table `goods_size` */

DROP TABLE IF EXISTS `goods_size`;

CREATE TABLE `goods_size` (
  `goods_size_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_size` varchar(10) DEFAULT NULL COMMENT '商品的尺码',
  `goods_size_stock` int(4) DEFAULT NULL COMMENT '商品该尺码的库存',
  `goods_id` int(11) DEFAULT NULL COMMENT '外键：商品id',
  PRIMARY KEY (`goods_size_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `goods_size_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `goods_size` */

insert  into `goods_size`(`goods_size_id`,`goods_size`,`goods_size_stock`,`goods_id`) values (24,'s',8,19),(25,'m',6,19),(26,'l',0,19),(27,'xl',7,19),(28,'xxl',5,19),(29,'xxxl',9,19),(30,'average',NULL,19),(31,'s',10,20),(32,'m',10,20),(33,'l',10,20),(34,'xl',10,20),(35,'xxl',10,20),(36,'xxxl',10,20),(37,'average',0,20);

/*Table structure for table `goods_type` */

DROP TABLE IF EXISTS `goods_type`;

CREATE TABLE `goods_type` (
  `goods_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '商品类别名称',
  PRIMARY KEY (`goods_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `goods_type` */

insert  into `goods_type`(`goods_type_id`,`name`) values (1,'上装'),(2,'裤装'),(3,'配件'),(4,'鞋靴');

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `manager_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(30) DEFAULT NULL COMMENT '管理员账号',
  `password` varchar(30) DEFAULT NULL COMMENT '管理员密码',
  `name` varchar(10) DEFAULT NULL COMMENT '管理员姓名',
  `sex` varchar(5) DEFAULT NULL COMMENT '管理员性别',
  `telephone` varchar(20) DEFAULT NULL COMMENT '管理员手机号码',
  `work_time` datetime DEFAULT NULL COMMENT '管理员入职时间',
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

insert  into `manager`(`manager_id`,`username`,`password`,`name`,`sex`,`telephone`,`work_time`) values (1,'admin','admin','铭','男','111','2019-12-28 12:27:30'),(3,'123456','123456','满','男','09876543210','2019-12-31 16:01:35');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `total` double(10,2) DEFAULT NULL COMMENT '订单总价',
  `state` int(2) DEFAULT NULL COMMENT '订单状态 0：待付款 1：待发货 2：待收货 3：待评价',
  `name` varchar(20) DEFAULT NULL COMMENT '下单人姓名',
  `address` varchar(50) DEFAULT NULL COMMENT '下单人收货地址',
  `telephone` varchar(20) DEFAULT NULL COMMENT '下单人手机号码',
  `user_id` int(11) DEFAULT NULL COMMENT '外键：下单人userId',
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`order_id`,`order_time`,`total`,`state`,`name`,`address`,`telephone`,`user_id`) values (10,'2019-12-27 14:48:04',745.00,1,'王铭满','广东技术师范大学天河学院','15102064905',1),(11,'2019-12-27 18:25:07',348.00,1,'王铭满','广东技术师范大学天河学院','15102064905',1),(12,'2019-12-27 21:01:16',697.00,1,'王铭满','广东技术师范大学天河学院','15102064905',1),(13,'2019-12-27 21:14:29',498.00,1,'王铭满','广东技术师范大学天河学院','15102064905',1),(14,'2019-12-27 21:25:24',298.00,0,'王铭满','广东技术师范大学天河学院','15102064905',1),(15,'2019-12-27 21:27:25',298.00,0,'王铭满','广东技术师范大学天河学院','15102064905',1),(16,'2019-12-27 21:32:41',2.00,2,'王铭满','广东技术师范大学天河学院','15102064905',1),(17,'2019-12-27 21:34:05',149.00,2,'王铭满','广东技术师范大学天河学院','15102064905',1),(18,'2019-12-27 21:37:16',149.00,0,'王铭满','广东技术师范大学天河学院','15102064905',1),(19,'2020-01-06 15:24:06',1243.00,0,'小满','铜锣湾','15102064905',1),(20,'2020-01-06 16:28:26',199.00,0,'小满','铜锣湾','15102064905',1),(21,'2020-01-06 16:36:02',298.00,0,'小满','铜锣湾','15102064905',1),(22,'2020-01-06 16:42:00',298.00,0,'小满','铜锣湾','15102064905',1),(23,'2020-01-06 16:48:23',447.00,1,'小满','铜锣湾','15102064905',1),(24,'2020-01-06 18:00:14',149.00,1,'小满','铜锣湾','15102064905',1);

/*Table structure for table `orders_list` */

DROP TABLE IF EXISTS `orders_list`;

CREATE TABLE `orders_list` (
  `orders_list_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `size` varchar(20) DEFAULT NULL COMMENT '订单该商品尺寸',
  `quantities` int(4) DEFAULT NULL COMMENT '订单该商品数量',
  `total` double(10,2) DEFAULT NULL COMMENT '订单该商品合计价格',
  `goods_id` int(11) DEFAULT NULL COMMENT '外键：商品id',
  `order_id` int(11) DEFAULT NULL COMMENT '外键：订单id',
  `appraise` varchar(100) DEFAULT NULL COMMENT '用户对该订单列表中该商品的评价',
  `user_id` int(11) DEFAULT NULL COMMENT '外键：用户id',
  `price` double(10,2) DEFAULT NULL COMMENT '订单该商品单价',
  PRIMARY KEY (`orders_list_id`),
  KEY `goods_id` (`goods_id`),
  KEY `order_id` (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_list_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `orders_list_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `orders_list_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `orders_list` */

insert  into `orders_list`(`orders_list_id`,`size`,`quantities`,`total`,`goods_id`,`order_id`,`appraise`,`user_id`,`price`) values (10,'l',5,745.00,19,10,'五星好评！',1,149.00),(11,'average',1,199.00,2,11,NULL,1,199.00),(12,'l',1,149.00,19,11,NULL,1,149.00),(13,'l',2,498.00,1,12,NULL,1,249.00),(14,'average',1,199.00,2,12,NULL,1,199.00),(15,'s',1,249.00,1,13,NULL,1,249.00),(16,'l',1,249.00,1,13,NULL,1,249.00),(17,'xl',1,149.00,19,14,NULL,1,149.00),(18,'xxl',1,149.00,19,14,NULL,1,149.00),(19,'s',1,149.00,19,15,NULL,1,149.00),(20,'m',1,149.00,19,15,NULL,1,149.00),(21,'xxxl',1,1.00,19,16,NULL,1,149.00),(22,'xxl',1,1.00,19,16,NULL,1,149.00),(23,'s',1,149.00,1,17,NULL,1,149.00),(24,'s',1,149.00,19,18,NULL,1,149.00),(25,'l',5,745.00,19,19,NULL,1,149.00),(26,'l',2,498.00,1,19,NULL,1,249.00),(27,'average',1,199.00,2,20,NULL,1,199.00),(28,'m',2,298.00,19,21,NULL,1,149.00),(29,'xl',2,298.00,19,22,NULL,1,149.00),(30,'xxl',3,447.00,19,23,NULL,1,149.00),(31,'m',1,149.00,19,24,NULL,1,149.00);

/*Table structure for table `orders_log` */

DROP TABLE IF EXISTS `orders_log`;

CREATE TABLE `orders_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_time` datetime DEFAULT NULL COMMENT '“待付款”状态：下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '“待发货”状态：付款时间',
  `out_time` datetime DEFAULT NULL COMMENT '”待收货“状态：发货时间',
  `get_time` datetime DEFAULT NULL COMMENT '”待评价“状态：收货时间',
  `order_id` int(11) DEFAULT NULL COMMENT '外键：订单id',
  `manager_id` int(11) DEFAULT NULL COMMENT '外键：管理员id',
  PRIMARY KEY (`log_id`),
  KEY `order_id` (`order_id`),
  KEY `manager_id` (`manager_id`),
  CONSTRAINT `orders_log_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `orders_log_ibfk_2` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`manager_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `orders_log` */

insert  into `orders_log`(`log_id`,`order_time`,`pay_time`,`out_time`,`get_time`,`order_id`,`manager_id`) values (1,'2019-12-27 21:01:16',NULL,NULL,NULL,NULL,NULL),(2,'2019-12-27 21:14:29',NULL,NULL,NULL,NULL,NULL),(3,'2019-12-27 21:25:24',NULL,NULL,NULL,NULL,NULL),(4,'2019-12-27 21:27:25',NULL,NULL,NULL,NULL,NULL),(5,'2019-12-27 21:32:41',NULL,'2019-12-28 21:35:24',NULL,16,1),(6,'2019-12-27 21:34:05',NULL,'2019-12-28 21:36:25',NULL,17,1),(7,'2019-12-27 21:37:16',NULL,NULL,NULL,18,NULL),(8,'2020-01-06 15:24:06',NULL,NULL,NULL,19,NULL),(9,'2020-01-06 16:28:26',NULL,NULL,NULL,20,NULL),(10,'2020-01-06 16:36:02',NULL,NULL,NULL,21,NULL),(11,'2020-01-06 16:42:00',NULL,NULL,NULL,22,NULL),(12,'2020-01-06 16:48:23','2020-01-06 17:33:09',NULL,NULL,23,NULL),(13,'2020-01-06 18:00:14','2020-01-06 18:00:18',NULL,NULL,24,NULL);

/*Table structure for table `rotation` */

DROP TABLE IF EXISTS `rotation`;

CREATE TABLE `rotation` (
  `rotation_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `imge` varchar(255) DEFAULT NULL COMMENT '轮播图片地址',
  `goods_id` int(11) DEFAULT NULL COMMENT '外键1：商品id',
  `manager_id` int(11) DEFAULT NULL COMMENT '外键2：上传管理员的id',
  PRIMARY KEY (`rotation_id`),
  KEY `goods_id` (`goods_id`),
  KEY `manager_id` (`manager_id`),
  CONSTRAINT `rotation_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `rotation_ibfk_2` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`manager_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `rotation` */

insert  into `rotation`(`rotation_id`,`imge`,`goods_id`,`manager_id`) values (13,'D:\\work\\workspace\\clothing_back_end\\src\\main\\resources\\static\\imgc1c61822-48b1-4891-aa34-75b50e04e55d.jpg',20,1),(14,'D:\\work\\workspace\\clothing_back_end\\src\\main\\resources\\static\\img\\b3f00a43-c68b-482e-b4c1-0dae5a278338.jpg',20,1);

/*Table structure for table `shopping_cart` */

DROP TABLE IF EXISTS `shopping_cart`;

CREATE TABLE `shopping_cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `size` varchar(20) DEFAULT NULL COMMENT '购物车中该商品尺寸',
  `price` double(10,2) DEFAULT NULL COMMENT '购物车中该商品单价',
  `quantities` int(4) DEFAULT NULL COMMENT '购物车中该商品数量',
  `total` double(10,2) DEFAULT NULL COMMENT '购物车中该商品合计价格',
  `goods_id` int(11) DEFAULT NULL COMMENT '外键-商品id',
  `user_id` int(11) DEFAULT NULL COMMENT '外键-用户id',
  PRIMARY KEY (`cart_id`),
  KEY `goods_id` (`goods_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `shopping_cart_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `shopping_cart` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(30) DEFAULT NULL COMMENT '密码',
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `sex` varchar(5) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `area` varchar(30) DEFAULT NULL COMMENT '地区',
  `wechat` varchar(30) DEFAULT NULL COMMENT '微信号',
  `address` varchar(50) DEFAULT NULL COMMENT '收货地址',
  `balance` double(10,2) DEFAULT NULL COMMENT '用户余额',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`username`,`password`,`name`,`telephone`,`sex`,`birthday`,`area`,`wechat`,`address`,`balance`) values (1,'966966','966966','小满','15102064905','男','2019-12-28 20:34:59','香港','15102064905','铜锣湾',6366.00),(4,'joewong','joewong','乔','12345678901','男','2019-12-31 15:23:17','广州-番禺','12345678901','南浦',NULL),(5,'123456','123456','123','123456','女','2000-04-06 00:00:00','广州','123456','番禺',NULL),(6,'666','666','小六','13100000000','男','2003-01-07 00:00:00','成都','13100000000','成都',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
