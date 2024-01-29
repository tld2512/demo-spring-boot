SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE `users-authorities` RESTART IDENTITY;
TRUNCATE TABLE `product` RESTART IDENTITY;
TRUNCATE TABLE `category` RESTART IDENTITY;
TRUNCATE TABLE `authorities` RESTART IDENTITY;
TRUNCATE TABLE `users` RESTART IDENTITY;
SET REFERENTIAL_INTEGRITY TRUE;

-- Create users
-- Default password is 'ahihi@123'
INSERT INTO user VALUES(1, "pipilily@gmail.com", "$2a$12$yFLmzzgkGpg8vAAXPVMmOeWTQTf4UxoCz6SfkNadUzzfOb/wYZEYm", "pipilily")
INSERT INTO user VALUES(2, "admin@gmail.com", "$2a$12$yFLmzzgkGpg8vAAXPVMmOeWTQTf4UxoCz6SfkNadUzzfOb/wYZEYm", "admin")
INSERT INTO user VALUES(3, "user1@gmail.com", "$2a$12$yFLmzzgkGpg8vAAXPVMmOeWTQTf4UxoCz6SfkNadUzzfOb/wYZEYm", "user1")
INSERT INTO user VALUES(4, "user2@gmail.com", "$2a$12$yFLmzzgkGpg8vAAXPVMmOeWTQTf4UxoCz6SfkNadUzzfOb/wYZEYm", "user2")

-- Inserting data for table `authorities`
--
INSERT INTO authorities VALUES (1,'ADMIN');
INSERT INTO authorities VALUES (2,'USER');

-- Inserting data for table `users-authorities`
--
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (1,1);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (1,2);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (2,1);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (2,2);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (3,1);
INSERT INTO `users-authorities` (user_id, authority_id) VALUES (4,1);

-- Inserting data for table `category`
--
INSERT INTO category (id, name) VALUES (1,'Mobile');
INSERT INTO category (id, name) VALUES (2,'Computer');
INSERT INTO category (id, name) VALUES (3,'Book');

-- Inserting data for table `product`
--
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (1, "A hi hi", "A funny book",  120000, 3, 1);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (2, "iPhone 15", "A product from Apple",  22000000, 1, 3);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (3, "RTX 4080", "A graphic card",  38000000, 2, 4);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (4, "Spring in action", "A Spring book",  880000, 3, 1);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (5, "S24 Ultra", "A product from Samsung",  28000000, 1, 4);
INSERT INTO product (id, productName, description, price, category_id, user_id) VALUES (6, "i9 14900KF", "A CPU",  12000000, 2, 3);
