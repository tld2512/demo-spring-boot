DROP SCHEMA IF EXISTS `demo`;

CREATE SCHEMA `demo`;
use demo;

DROP TABLE IF EXISTS `users-authorities`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

-- Create table user
CREATE TABLE `user` (
  `id` int NOT NULL,
  `email` varchar(255) DEFAULT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Create users with default password is 'ahihi@123'
INSERT INTO user VALUES(1, "pipilily@gmail.com", "$2a$12$yFLmzzgkGpg8vAAXPVMmOeWTQTf4UxoCz6SfkNadUzzfOb/wYZEYm", "pipilily");
INSERT INTO user VALUES(2, "admin@gmail.com", "$2a$12$yFLmzzgkGpg8vAAXPVMmOeWTQTf4UxoCz6SfkNadUzzfOb/wYZEYm", "admin");
INSERT INTO user VALUES(3, "user1@gmail.com", "$2a$12$yFLmzzgkGpg8vAAXPVMmOeWTQTf4UxoCz6SfkNadUzzfOb/wYZEYm", "user1");
INSERT INTO user VALUES(4, "user2@gmail.com", "$2a$12$yFLmzzgkGpg8vAAXPVMmOeWTQTf4UxoCz6SfkNadUzzfOb/wYZEYm", "user2");
--

-- Create table authorities
CREATE TABLE `authorities` (
  `id` int NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Inserting data for table `authorities`
INSERT INTO authorities VALUES (1,'ADMIN');
INSERT INTO authorities VALUES (2,'USER');
--

--
CREATE TABLE `users-authorities` (
  `user_id` int NOT NULL,
  `authority_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`authority_id`),
  CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Inserting data for table `users-authorities`
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (1,1);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (1,2);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (2,1);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (2,2);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (3,1);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (4,1);
--

--Create table category
CREATE TABLE `category` (
  `id` int NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`category_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Inserting data for table `category`
INSERT INTO category (id, category_name) VALUES (1,'Mobile');
INSERT INTO category (id, category_name) VALUES (2,'Computer');
INSERT INTO category (id, category_name) VALUES (3,'Book');
--

--Create table product
CREATE TABLE `product` (
  `id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY (`category_id`),
  KEY (`user_id`),
  CONSTRAINT FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Inserting data for table `product`
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (1, "A hi hi", "A funny book",  120000, 3, 1);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (2, "iPhone 15", "A product from Apple",  22000000, 1, 3);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (3, "RTX 4080", "A graphic card",  38000000, 2, 4);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (4, "Spring in action", "A Spring book",  880000, 3, 1);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (5, "S24 Ultra", "A product from Samsung",  28000000, 1, 4);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (6, "i9 14900KF", "A CPU",  12000000, 2, 3);
