INSERT INTO product (id, code, name, price_eur, price_usd, description)
VALUES
 ('1', '123456789012345', 'Samsung S10', '500', '547.98', 'description'),
 ('2', '123456789012346', 'Samsung A10', '250', '284.95', 'description'),
 ('3', '123456789012347', 'Iphone 10', '1000', '1095.95', 'description'),
 ('4', '123456789012348', 'Xiaomi 13', '950', '1041.15', 'description'),
 ('5', '123456789012349', 'Nokia', '1500', '1643.93', 'description');

INSERT INTO review (id, rating, reviewer, text, product_id)
VALUES
 ('1', '2', 'User', 'tekst', '1'),
 ('2', '3', 'User', 'tekst', '1'),
 ('3', '4', 'User', 'tekst', '1'),
 ('4', '5', 'User', 'tekst', '2'),
 ('5', '2', 'User', 'tekst', '2'),
 ('6', '2', 'User', 'tekst', '2'),
 ('7', '3', 'User', 'tekst', '2'),
 ('8', '3', 'User', 'tekst', '3'),
 ('9', '3', 'User', 'tekst', '3'),
 ('10', '5', 'User', 'tekst', '3'),
 ('11', '5', 'User', 'tekst', '3'),
 ('12', '5', 'User', 'tekst', '4'),
 ('13', '1', 'User', 'tekst', '5'),
 ('14', '2', 'User', 'tekst', '5');
