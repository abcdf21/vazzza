-- �������� ���� ������
CREATE DATABASE OnlineStore;
USE OnlineStore;

CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL
);


CREATE TABLE Categories (
    CategoryID INT IDENTITY(1,1) PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Description TEXT
);

CREATE TABLE Brands (
    BrandID INT IDENTITY(1,1) PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Description TEXT
);

CREATE TABLE Products (
    ProductID INT IDENTITY(1,1) PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    CategoryID INT NOT NULL,
    BrandID INT NOT NULL,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID) ON DELETE CASCADE,
    FOREIGN KEY (BrandID) REFERENCES Brands(BrandID) ON DELETE CASCADE
);


CREATE TABLE Cart (
    CartID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT not null,
	ProductID INT not null,
	quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE,
	FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);

CREATE TABLE Orders (
    OrderID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT,
    Orderdate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Totalamount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);


CREATE TABLE Orderdetails (
    OrderdetailID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);


CREATE TABLE Reviews (
    ReviewID INT IDENTITY(1,1) PRIMARY KEY,
    ProductID INT,
    UserID INT,
    Rating INT CHECK (Rating >= 1 AND Rating <= 5),
	Comment TEXT,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

drop table Reviews
drop table Orderdetails
drop table Orders
drop table Cart
drop table Products
drop table Brands
drop table Categories
drop table Users

insert into Users(username, password) values
('ad2', 'ad2')

select * from Users

select * from Brands;
select * from Categories;
select * from Cart;
select * from Orders;
select * from Orderdetails;
select * from Products

INSERT INTO Brands (Name, Description) VALUES ('Samsung', '������� ������������� ������������� �����������, ��������� ������ �����������, ������������ � ������� ��������.');
INSERT INTO Brands (Name, Description) VALUES ('Apple', '������������ ��������, ��������� ������ ����������� � ������� ����������, ������� iPhone, iPad � MacBook.');
INSERT INTO Brands (Name, Description) VALUES ('Sony', '�������� ����������, ������������� �����������, ��� � ���������������� ��������, ��������� ������ ������������ � �������� ���������.');
INSERT INTO Brands (Name, Description) VALUES ('LG Electronics', '������������� ��������, ������������� ������� ����������� � ������� �������, ������� ������������ � ���������� ������.');
INSERT INTO Brands (Name, Description) VALUES ('Microsoft', '������������ ����������, ��������� ������ ������������ ����������, � ����� �������� ��������� Xbox.');

INSERT INTO Categories (Name, Description) VALUES 
('���������', '��������� �������� � ������������ ���������, ������������� ��������� � ������������.'),

('��������', '����������� ���������� ��� ������, ����� � �����������.'),

('����������', '���������� ��������� �������� � ����������, ������� OLED, QLED � 4K.'),

('������������', '�������, ��������, ��������� � ������ ������� ��� ��������������� �����.'),

('����� ����������', '������� ��� ������ ����, ������� ����� ����, ���������� � ������ ����������.');

-- ��������� �������� � ��������� "���������"
INSERT INTO Products (Name, Price, CategoryID, BrandID) 
VALUES 
('Samsung Galaxy S23', 799.99, (SELECT CategoryID FROM Categories WHERE Name = '��������'), (SELECT BrandID FROM Brands WHERE Name = 'Samsung')),
('iPhone 15 Pro', 1099.99, (SELECT CategoryID FROM Categories WHERE Name = '��������'), (SELECT BrandID FROM Brands WHERE Name = 'Apple'));

-- ��������� �������� � ��������� "��������"
INSERT INTO Products (Name, Price, CategoryID, BrandID) 
VALUES 
('MacBook Air M2', 1299.99, (SELECT CategoryID FROM Categories WHERE Name = '��������'), (SELECT BrandID FROM Brands WHERE Name = 'Apple')),
('LG Gram 17', 1599.99, (SELECT CategoryID FROM Categories WHERE Name = '��������'), (SELECT BrandID FROM Brands WHERE Name = 'LG Electronics'));

-- ��������� �������� � ��������� "����������"
INSERT INTO Products (Name, Price, CategoryID, BrandID) 
VALUES 
('Sony Bravia XR', 1899.99, (SELECT CategoryID FROM Categories WHERE Name = '����������'), (SELECT BrandID FROM Brands WHERE Name = 'Sony')),
('Samsung QLED Q80C', 1499.99, (SELECT CategoryID FROM Categories WHERE Name = '����������'), (SELECT BrandID FROM Brands WHERE Name = 'Samsung'));

-- ��������� �������� � ��������� "������������"
INSERT INTO Products (Name, Price, CategoryID, BrandID) 
VALUES 
('Sony WH-1000XM5', 399.99, (SELECT CategoryID FROM Categories WHERE Name = '������������'), (SELECT BrandID FROM Brands WHERE Name = 'Sony')),
('Apple AirPods Pro 2', 249.99, (SELECT CategoryID FROM Categories WHERE Name = '������������'), (SELECT BrandID FROM Brands WHERE Name = 'Apple'));

-- ��������� �������� � ��������� "����� ����������"
INSERT INTO Products (Name, Price, CategoryID, BrandID) 
VALUES 
('Microsoft Surface Duo', 1399.99, (SELECT CategoryID FROM Categories WHERE Name = '����� ����������'), (SELECT BrandID FROM Brands WHERE Name = 'Microsoft')),
('Samsung SmartThings Hub', 99.99, (SELECT CategoryID FROM Categories WHERE Name = '����� ����������'), (SELECT BrandID FROM Brands WHERE Name = 'Samsung'));

select * from Products

select * from Reviews

-- ��������� ������ ��� ������ � ProductID = 1
INSERT INTO Reviews (ProductID, UserID, Rating, Comment) 
VALUES 
(1, 1, 5, '������������ �������, ���� ����������!'),
(1, 2, 4, '����� �������, �� �������� ������� �����������.');

-- ��������� ������ ��� ������ � ProductID = 2
INSERT INTO Reviews (ProductID, UserID, Rating, Comment) 
VALUES 
(2, 2, 3, '������� ��������, ������ �������.'),
(2, 11, 4, '������� ������� �� ���� ������.');

-- ��������� ������ ��� ������ � ProductID = 3
INSERT INTO Reviews (ProductID, UserID, Rating, Comment) 
VALUES 
(3, 11, 5, '������ �����, ������� � �������!'),
(3, 2, 4, '���������� ������ � ����������������.');

-- ��������� ������ ��� ������ � ProductID = 4
INSERT INTO Reviews (ProductID, UserID, Rating, Comment) 
VALUES 
(4, 7, 2, '�������� ��������� ������ �������.'),
(4, 8, 3, '�������, �� ���� ��� ��� ����������.');

-- ��������� ������ ��� ������ � ProductID = 5
INSERT INTO Reviews (ProductID, UserID, Rating, Comment) 
VALUES 
(5, 9, 5, '���������� �����, � � ��������!'),
(5, 10, 5, '���������� ����, �������� �� ������.');
