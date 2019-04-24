CREATE TABLE ProductCategory(
        ID INT auto_increment PRIMARY KEY,
        Name VARCHAR(100),
        Description VARCHAR(1000));

CREATE UNIQUE INDEX UI_PC ON ProductCategory(Name);

CREATE TABLE Supplier(
        ID INT auto_increment PRIMARY KEY,
        Name VARCHAR(100));

CREATE UNIQUE INDEX UI_S ON Supplier(Name);

CREATE TABLE Product(
        ID INT auto_increment PRIMARY KEY,
        Name VARCHAR(100),
        Description VARCHAR(1000),
        Price DECIMAL,
        Weight DOUBLE,
        Category VARCHAR(100),
        Supplier VARCHAR(100),
        ImageURL VARCHAR(200),
        FOREIGN KEY(Category) REFERENCES ProductCategory(Name),
        FOREIGN KEY(Supplier) REFERENCES Supplier(Name));

CREATE UNIQUE INDEX UI_P ON Product(Name);

CREATE TABLE Location(
        ID INT auto_increment PRIMARY KEY,
        Name VARCHAR(100),
        AddressCountry VARCHAR(100),
        AddressCity VARCHAR(100),
        AddressCounty VARCHAR(100),
        AddressStreetAddress VARCHAR(200));

CREATE UNIQUE INDEX UI_L ON Location(Name);

CREATE TABLE Stock(
        Product VARCHAR(100),
        Location VARCHAR(100),
        Quantity INT,
        FOREIGN KEY(Product) REFERENCES Product(Name),
        FOREIGN KEY(Location) REFERENCES Location(Name),
        PRIMARY KEY(Product, Location));

CREATE TABLE Customer(
        ID INT auto_increment PRIMARY KEY,
        FirstName VARCHAR(100),
        LastName VARCHAR(100),
        Username VARCHAR(100),
        Password VARCHAR(100),
        EmailAddress VARCHAR(200) UNIQUE);

CREATE UNIQUE INDEX UI_C ON Customer(username);

CREATE TABLE Orders(
        ID INT auto_increment PRIMARY KEY,
        ShippedFrom VARCHAR(100),
        Customer VARCHAR(100),
        CreatedAt TIMESTAMP,
        AddressCountry VARCHAR(100),
        AddressCity VARCHAR(100),
        AddressCounty VARCHAR(100),
        AddressStreet VARCHAR(200),
        FOREIGN KEY(ShippedFrom) REFERENCES Location(Name),
        FOREIGN KEY(Customer) REFERENCES Customer(Username));

CREATE TABLE OrderDetail(
        orderID INT,
        Product VARCHAR(100),
        Quantity INT,
        FOREIGN KEY(orderID) REFERENCES Orders(ID),
        FOREIGN KEY(Product) REFERENCES Product(Name),
        PRIMARY KEY(orderID, Product));

CREATE TABLE Revenue(
        ID INT auto_increment PRIMARY KEY,
        Location VARCHAR(100),
        Date DATE,
        Sum DECIMAL,
        FOREIGN KEY(Location) REFERENCES Location(Name));