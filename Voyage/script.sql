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
    nbactivite double precision,
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
    bouquetactivite.nbactivite, -- Replace with actual column names
    bouquet.nom, -- Replace with actual column names
    bouquet.idbouquet AS bouquet_idbouquet,
    activite.idactivite AS activite_idactivite,
    activite.nom AS activite_nom, -- Replace with actual column names
    activite.prix AS activite_prix
FROM
    bouquetactivite
JOIN
    bouquet ON bouquetactivite.idbouquet = bouquet.idbouquet
JOIN
    activite ON activite.idactivite = bouquetactivite.idactivite);
select * from v_bouquetactivite where bouquet_idbouquet=2;

alter table activite add column prix double precision;

update activite set prix=10000 where idactivite=1;
update activite set prix=12000 where idactivite=2;
update activite set prix=14000 where idactivite=3;
update activite set prix=22000 where idactivite=4;
update activite set prix=2000 where idactivite=5;
update activite set prix=42000 where idactivite=6;

create or replace view v_prixVoyage as
(
    select voyage.idvoyage,sum(activite.prix*bouquetactivite.nbactivite) from voyage join bouquetactivite on bouquetactivite.idbouquet=voyage.idbouquet join activite on bouquetactivite.idactivite=activite.idactivite group by voyage.idvoyage
);

create or replace view v_vraiPrixVoyage as
select voyage.idvoyage,voyage.idbouquet,voyage.dureejours,voyage.idcategorie,v_prixVoyage.sum from voyage join v_prixVoyage on voyage.idvoyage=v_prixVoyage.idvoyage;

select * from voyage join bouquetactivite on bouquetactivite.idbouquet=voyage.idbouquet join activite on bouquetactivite.idactivite=activite.idactivite;



