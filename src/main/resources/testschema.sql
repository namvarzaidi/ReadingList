DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
	`id` BIGINT AUTO_INCREMENT,
	`book_title` VARCHAR(255) NOT NULL,
	`author_name` VARCHAR(255) NOT NULL,
	`date_started` VARCHAR(255) NOT NULL,
	`book_rating` INT NOT NULL,
	PRIMARY KEY(`id`)
);