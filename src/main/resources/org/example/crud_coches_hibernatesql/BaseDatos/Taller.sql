DROP DATABASE IF EXISTS Taller;
CREATE DATABASE Taller;
Use Taller;


CREATE TABLE Coches(
Id int unsigned auto_increment primary key,
Matricula varchar(10),
Marca varchar(20),
Modelo varchar(20),
Tipo enum('Familiar','Monovolumen','Deportivo','SUV')

 );
 INSERT INTO Coches  VALUES
 (1,"1122BBC","Renault","Clio","Familiar"),
 (2,"2233DDB","Seat","Ibiza","Familiar"),
 (3,"3344FFC","Seat","Leon","Deportivo"),
 (4,"4455BBZ","Ford","S-Max","Monovolumen"),
 (5,"5566CCR","Ford","Kuga","SUV"),
 (6,"6677FFD","Nisan","Micra","Familiar"),
 (7,"7788JJZ","Volkswagen","Pasat","Familiar"),
 (8,"8899BBV","Volkswagen","T-ROC","SUV"),
 (9,"9911FFG","Volkswagen","Touran","Monovolumen"),
 (10,"8855GFR","Renault","Scenic","Monovolumen");