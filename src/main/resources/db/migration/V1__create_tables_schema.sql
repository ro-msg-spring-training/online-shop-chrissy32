CREATE TABLE ProductCategory(
        ID INT auto_increment PRIMARY KEY,
        name VARCHAR(100),
        description VARCHAR(1000));

CREATE UNIQUE INDEX UI_PC ON ProductCategory(name);

CREATE TABLE Supplier(
        ID INT auto_increment PRIMARY KEY,
        name VARCHAR(100));

CREATE UNIQUE INDEX UI_S ON Supplier(Name);

CREATE TABLE Product(
        ID INT auto_increment PRIMARY KEY,
        name VARCHAR(100),
        description VARCHAR(1000),
        price DECIMAL,
        weight DOUBLE,
        category INT,
        supplier INT,
        imageURL VARCHAR(200),
        FOREIGN KEY(category) REFERENCES ProductCategory(ID),
        FOREIGN KEY(supplier) REFERENCES Supplier(ID));

CREATE UNIQUE INDEX UI_P ON Product(name);

CREATE TABLE Address(
        ID INT auto_increment PRIMARY KEY,
        addressCountry VARCHAR(100),
        addressCity VARCHAR(100),
        addressCounty VARCHAR(100),
        addressStreet VARCHAR(200));

CREATE TABLE Location(
        ID INT auto_increment PRIMARY KEY,
        name VARCHAR(100),
        address INT,
        FOREIGN KEY(address) REFERENCES Address(ID));

CREATE UNIQUE INDEX UI_L ON Location(name);

CREATE TABLE Stock(
        ID INT auto_increment PRIMARY KEY,
        product INT,
        location INT,
        quantity INT,
        FOREIGN KEY(product) REFERENCES Product(ID),
        FOREIGN KEY(location) REFERENCES Location(ID));

CREATE TABLE Customer(
        ID INT auto_increment PRIMARY KEY,
        firstName VARCHAR(100),
        lastName VARCHAR(100),
        username VARCHAR(100),
        password VARCHAR(100),
        emailAddress VARCHAR(200) UNIQUE);

CREATE UNIQUE INDEX UI_C ON Customer(username);

CREATE TABLE Orders(
        ID INT auto_increment PRIMARY KEY,
        shippedFrom INT,
        customer INT,
        createdAt TIMESTAMP,
        address INT,
        FOREIGN KEY(address) REFERENCES Address(ID),
        FOREIGN KEY(shippedFrom) REFERENCES Location(ID),
        FOREIGN KEY(customer) REFERENCES Customer(ID));

CREATE TABLE OrderDetail(
        ID INT auto_increment PRIMARY KEY,
        orderID INT,
        product INT,
        quantity INT,
        FOREIGN KEY(orderID) REFERENCES Orders(ID),
        FOREIGN KEY(product) REFERENCES Product(ID));

CREATE TABLE Revenue(
        ID INT auto_increment PRIMARY KEY,
        location INT,
        Date DATE,
        Sum DECIMAL,
        FOREIGN KEY(location) REFERENCES Location(ID));