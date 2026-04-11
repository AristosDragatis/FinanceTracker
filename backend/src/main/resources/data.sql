-- delete old data to avoid duplicates
TRUNCATE TABLE category CASCADE;

-- Incomes
INSERT INTO category (name, type) VALUES ('Salary', 'INCOME');
INSERT INTO category (name, type) VALUES ('Gift', 'INCOME');
INSERT INTO category (name, type) VALUES ('Investment', 'INCOME');

-- Expenses
INSERT INTO category (name, type) VALUES ('Supermarket', 'EXPENSE');
INSERT INTO category (name, type) VALUES ('Rent', 'EXPENSE');
INSERT INTO category (name, type) VALUES ('Utilities', 'EXPENSE');
INSERT INTO category (name, type) VALUES ('Entertainment', 'EXPENSE');
INSERT INTO category (name, type) VALUES ('Transport/Gas', 'EXPENSE');
INSERT INTO category (name, type) VALUES ('Healthcare', 'EXPENSE');