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

create or replace view v_bouquetactivite as
(SELECT
    bouquetactivite.idbouquet AS bouquetactivite_idbouquet,
    bouquetactivite.idactivite AS bouquetactivite_idactivite,
    bouquetactivite.duree, -- Replace with actual column names
    bouquet.nom, -- Replace with actual column names
    bouquet.idbouquet AS bouquet_idbouquet,
    activite.idactivite AS activite_idactivite,
    activite.nom AS activite_nom -- Replace with actual column names
FROM
    bouquetactivite
JOIN
    bouquet ON bouquetactivite.idbouquet = bouquet.idbouquet
JOIN
    activite ON activite.idactivite = bouquetactivite.idactivite);
select * from v_bouquetactivite where bouquet_idbouquet=2;


