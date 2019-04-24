INSERT INTO ProductCategory(Name, Description) VALUES
                ('convenience', 'frequent purchase, little effort, low customer involvement'),
                ('shopping', 'less frequent purchase, much effort (planning and comparison of brands on price, quality, style etc.)'),
                ('speciality', 'strong brand preference and loyalty, special purchase effort, little comparison of brands, low price sensitivity'),
                ('unsought', 'little product awareness and knowledge or little interest');

INSERT INTO Supplier(Name) VALUES('MWV Switzerland Ltd'),
                ('Bio-Rad ABD Serotec Inc.'),
                ('Wujiang Dongnan Garments Industry Co., Ltd.'),
                ('Americanfreight');

INSERT INTO Product(Name, Description, Price, Weight, Category, Supplier, ImageURL) VALUES
                ('furniture', 'curvilinear - bold, beautiful curves', 90.08, 50, 'shopping', 'MWV Switzerland Ltd', 'https://www.dictionary.com/e/wp-content/uploads/2017/08/curvilinear_2.jpg'),
                ('Rolex watch', 'crafted from the finest raw materials and assembled with scrupulous attention to detail', 8509.63, 0.11, 'speciality', 'Wujiang Dongnan Garments Industry Co., Ltd.', 'https://www.rolex.com/content/dam/rolex-58/navigation/pannel/mobile/navigation_sky-dweller_mobile_0001_270x380.jpg'),
                ('life insurance', 'the insurance company provides a lump-sum payment, known as a death benefit, to beneficiaries upon the insureds death', 814.56, 0, 'unsought', 'Bio-Rad ABD Serotec Inc.', 'http://www.globaldomainsnews.com/wp-content/uploads/2019/02/p_lifeinsurance-187048904.jpg'),
                ('laundry detergent', 'added for cleaning laundry', 9.92, 5, 'convenience', 'Americanfreight', 'https://images-na.ssl-images-amazon.com/images/I/81BiwXqtrQL._SY355_.jpg');

INSERT INTO Location(Name, AddressCountry, AddressCity, AddressCounty, AddressStreetAddress) VALUES
                ('Magedell', 'USA', 'Jamaica', 'New York', '1735 Pride Avenue'),
                ('Sunken Place', 'USA', 'Stockton', 'California', '3632 Maple Avenue'),
                ('Corhall', 'USA', 'Jonesboro', 'Arkansas', '623 Arlington Avenue'),
                ('Eastlyn', 'USA', 'Austin', 'Texas', '2148 Sundown Lane'),
                ('Mistden', 'USA', 'Cambridge', 'Massachusetts', '830 Hummingbird Way');

INSERT INTO Stock(Product, Location, Quantity) VALUES
                ('Rolex watch', 'Magedell', 100),
                ('furniture', 'Mistden', 50),
                ('laundry detergent', 'Corhall', 100),
                ('laundry detergent', 'Sunken Place', 200),
                ('life insurance', 'Eastlyn', 5);

INSERT INTO Customer(FirstName, LastName, Username, Password, EmailAddress) VALUES
                ('Evie', 'Kalfr', 'adantiou', 'password1', 'eimear@gmail.com'),
                ('Josephus', 'Marie', 'sardybda', 'password2', 'grdschl@yahoo.ca'),
                ('Christie', 'Torhild', 'procycen', 'password3', 'jlbaumga@hotmail.com'),
                ('Juliya', 'Larisa', 'minackso', 'password4', 'kosact@yahoo.com');

INSERT INTO Orders(ShippedFrom, Customer, CreatedAt, AddressCountry, AddressCity, AddressCounty, AddressStreet) VALUES
                ('Magedell', 'adantiou', '2018-08-19 12:17:55', 'USA', 'Pleasantville', 'Ohio', '4752 Irving Road'),
                ('Corhall', 'procycen', CURRENT_TIMESTAMP, 'USA', 'Albany', 'New York', '2371 Joes Road');

INSERT INTO OrderDetail VALUES
                (1, 'furniture', 2),
                (2, 'laundry detergent', 5);

INSERT INTO Revenue(Location, Date, Sum) VALUES
                ('Mistden', CURRENT_DATE, 6.7),
                ('Eastlyn', CURRENT_DATE, 8.7);
