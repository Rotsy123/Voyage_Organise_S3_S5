create database voyage;
\c voyage;

create table activite(
    idactivite serial primary key,
    nom varchar(50)
);

create table categorielieu(
    idcategorie serial primary key,
    nom varchar(50)
);

create table bouquet (
    idbouquet serial primary key,
    nom varchar(50)
);

create table bouquetactivite(
    idbouquet int,
    idactivite int,
    duree double precision,
    FOREIGN KEY (idbouquet) REFERENCES bouquet (idbouquet),
    FOREIGN KEY (idactivite) REFERENCES activite (idactivite)
);

create table voyage(
    idvoyage serial primary key,
    idbouquet int,
    dureejours double precision,
    idcategorie int,
    prix double precision,
    FOREIGN KEY (idbouquet) REFERENCES bouquet (idbouquet),
    FOREIGN KEY (idcategorie) REFERENCES categorielieu (idcategorie)
);

insert into categorielieu (nom) values('National');
insert into categorielieu (nom) values('International');
insert into categorielieu (nom) values('Decouverte');

insert into voyage (idbouquet,dureejours,idcategorie,prix) values (1,10,2,1500000);
insert into voyage (idbouquet,dureejours,idcategorie,prix) values (2,,2,1500000);
insert into voyage (idbouquet,dureejours,idcategorie,prix) values (1,10,2,1500000);