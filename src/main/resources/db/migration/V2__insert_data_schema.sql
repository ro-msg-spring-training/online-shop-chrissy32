INSERT INTO ProductCategory(name, description) VALUES
                ('convenience', 'frequent purchase, little effort, low customer involvement'),
                ('shopping', 'less frequent purchase, much effort (planning and comparison of brands on price, quality, style etc.'),
                ('speciality', 'strong brand preference and loyalty, special purchase effort, little comparison of brands, low price sensitivity'),
                ('unsought', 'little product awareness and knowledge or little interest');

INSERT INTO Supplier(name) VALUES('MWV Switzerland Ltd'),
                ('Bio-Rad ABD Serotec Inc.'),
                ('Wujiang Dongnan Garments Industry Co., Ltd.'),
                ('Americanfreight');

INSERT INTO Product(name, description, price, weight, category, supplier, imageURL) VALUES
                ('furniture', 'curvilinear - bold, beautiful curves', 90.08, 50, 2, 1, 'https://www.dictionary.com/e/wp-content/uploads/2017/08/curvilinear_2.jpg'),
                ('Rolex watch', 'crafted from the finest raw materials and assembled with scrupulous attention to detail', 8509.63, 0.11, 3, 3, 'https://www.rolex.com/content/dam/rolex-58/navigation/pannel/mobile/navigation_sky-dweller_mobile_0001_270x380.jpg'),
                ('life insurance', 'the insurance company provides a lump-sum payment, known as a death benefit, to beneficiaries upon the insureds death', 814.56, 0, 4, 2, 'http://www.globaldomainsnews.com/wp-content/uploads/2019/02/p_lifeinsurance-187048904.jpg'),
                ('laundry detergent', 'added for cleaning laundry', 9.92, 5, 1, 4, 'https://images-na.ssl-images-amazon.com/images/I/81BiwXqtrQL._SY355_.jpg');


INSERT INTO Address(addressCountry, addressCity, addressCounty, addressStreet) VALUES
                ('USA', 'Jamaica', 'New York', '1735 Pride Avenue'),
                ('USA', 'Stockton', 'California', '3632 Maple Avenue'),
                ('USA', 'Jonesboro', 'Arkansas', '623 Arlington Avenue'),
                ('USA', 'Austin', 'Texas', '2148 Sundown Lane'),
                ('USA', 'Cambridge', 'Massachusetts', '830 Hummingbird Way'),
                ('USA', 'Pleasantville', 'Ohio', '4752 Irving Road'),
                ('USA', 'Albany', 'New York', '2371 Joes Road');

INSERT INTO Location(name, address) VALUES
                ('Magedell', 1),
                ('Sunken Place', 2),
                ('Corhall', 3),
                ('Eastlyn', 4),
                ('Mistden', 5);

INSERT INTO Stock(product, location, quantity) VALUES
                (2, 1, 100),
                (1, 5, 50),
                (4, 3, 100),
                (4, 2, 200),
                (3, 4, 5);

INSERT INTO Customer(firstName, lastName, username, password, emailAddress) VALUES
                ('Evie', 'Kalfr', 'adantiou', '$2a$10$/8sJI32uGKmzcrz4NT9uH.XYtuNrr3bqAgnUDW9Bf8RIxBSS2ZRgC', 'eimear@gmail.com'),
                ('Josephus', 'Marie', 'sardybda', '$2a$10$Y2afFFH.jyCERISyTEjLz.0gDhNTQ62SZfYalQOUXnRfPbN7WedVG', 'grdschl@yahoo.ca'),
                ('Christie', 'Torhild', 'procycen', '$2a$10$J92r.2J1/BOVLI3vY3a6GeCVaLN9ytPeQld.P0IMMOPyuF7qv7u8K', 'jlbaumga@hotmail.com'),
                ('Juliya', 'Larisa', 'minackso', '$2a$10$jCZGy6Ks5N/RBWsGbvfFZuGh9cwX3ONVXQwz7YjT8rkQDGL..NtbK', 'kosact@yahoo.com');

INSERT INTO Orders(shippedFrom, customer, createdAt, address) VALUES
                (1, 1, '2018-08-19 12:17:55', 6),
                (3, 3, CURRENT_TIMESTAMP, 7);

INSERT INTO OrderDetail(orderID, product, quantity) VALUES
                (1, 1, 2),
                (2, 4, 5);

INSERT INTO Revenue(location, date, sum) VALUES
                (5, CURRENT_DATE, 6.7),
                (4, CURRENT_DATE, 8.7);
