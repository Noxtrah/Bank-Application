create database bankdb;
create user "bank"@"localhost" identified by "securepassword"; -- creates a user
use bankdb; -- selects which database will be used
grant all on bankdb.* to "bank"@"localhost"; -- user allowed to access to everything on this table
create table Users(ID INT primary key auto_increment, FirstName VARCHAR(50), LastName VARCHAR(50), SSN VARCHAR(9)) Engine = InnoDB;
create table Accounts(ID INT primary key auto_increment, Type VARCHAR(50), Balance DOUBLE) Engine = InnoDB;
create table Mappings(ID INT primary key auto_increment, UserID INT, AccountID INT, foreign key (UserID) references Users(ID) on delete cascade,
foreign key (AccountID) references Accounts(ID) on delete cascade) Engine = InnoDb;