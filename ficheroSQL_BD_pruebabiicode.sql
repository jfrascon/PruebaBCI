
drop table Carreteras;
drop table Ciudades;

create table Ciudades (
	nombreCiudad VARCHAR(50) not null, 
	coordX FLOAT not null,
	coordY FLOAT not null, 
	PRIMARY KEY (nombreCiudad)
);

create table Carreteras (
	nombreCiudadA VARCHAR(50) not null, 
	nombreCiudadB VARCHAR(50) not null, 
	PRIMARY KEY (nombreCiudadA, nombreCiudadB), 
	FOREIGN KEY (nombreCiudadA) REFERENCES Ciudades (nombreCiudad) ON DELETE CASCADE ON UPDATE CASCADE,  
	FOREIGN KEY (nombreCiudadB) REFERENCES Ciudades (nombreCiudad) ON DELETE CASCADE ON UPDATE CASCADE  
);


insert into Ciudades (nombreCiudad, coordX, coordY) values ("Vigo", -427.565, 190.870);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Oviedo", -185.972, 314.726);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Bilbao", 65.115, 305.826);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Zaragoza", 238.036, 125.383);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Barcelona", 498.305, 94.228);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Valencia", 278.942, -118.063);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Albacete", 156.221, -169.231);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Murcia", 215.841, -282.180);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Almeria", 102.298, -410.407);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Granada", 10.846, -373.286);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Malaga", -65.343, -426.079);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Cordoba", -91.211, -297.611);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Sevilla", -195.320, -351.877);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Huelva", -277.136, -361.604);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Madrid", 0.000, 0.000);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Badajoz", -278.995, -184.443);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Salamanca", -167.378, 48.225);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Valladolid", -85.679, 132.823);
insert into Ciudades (nombreCiudad, coordX, coordY) values ("Burgos", 0, 209.177);
insert into Carreteras (nombreCiudadA, nombreCiudadB) values ("Vigo", "Oviedo");
