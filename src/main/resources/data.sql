DROP TABLE IF EXISTS currency;
DROP TABLE IF EXISTS transaction;

CREATE TABLE currency (
  id integer PRIMARY KEY AUTO_INCREMENT ,
  name varchar(45) NOT NULL,
  exchange_rate decimal(20,6) NOT NULL);

INSERT INTO currency VALUES (1,'SGD', 1.0000000000),(2,'USD', 0.7421640000),(3,'INR', 54.4100000000),(4,'RMB', 3.0500000000);


-- CREATE TABLE exchange_currency (
--   id integer PRIMARY KEY AUTO_INCREMENT ,
--   currency_id int(11) NOT NULL,
--   exchange_rate decimal(20,6) NOT NULL,
--   date datetime NOT NULL);
-- INSERT INTO exchange_currency VALUES (1,1,1.0000000000,'2020-11-02 00:00:00'),(2,2,0.7421640000,'2020-11-02 00:00:00'),(3,3,54.4100000000,'2020-11-02 00:00:00'),(4,4,3.0500000000,'2020-11-02 00:00:00');


CREATE TABLE transaction (
  id integer PRIMARY KEY AUTO_INCREMENT,
  transaction_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  input_quantity DECIMAL(30,6) NOT NULL,
  input_currency VARCHAR(45) NOT NULL,
  output_quantity DECIMAL(30,6) NOT NULL,
  output_currency VARCHAR(45) NOT NULL,
  exchange_rate DECIMAL(20,6) NOT NULL);