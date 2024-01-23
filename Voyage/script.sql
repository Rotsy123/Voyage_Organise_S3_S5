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

create view v_voyagebouquetact as
select idvoyage,voyage.idbouquet,dureejours,idcategorie,prix,idactivite,nbactivite from voyage join bouquetactivite on voyage.idbouquet=bouquetactivite.idbouquet;


create table EntreeStock(
    idEntreeStock serial primary key,
    nb INT,
    idactivite INT,
    DateEntreeStock DATE,
    FOREIGN KEY (idactivite) REFERENCES activite (idactivite)
);

ALTER table EntreeStock add column prix double precision;

create table SortieStock(
    idSortieStock serial primary key,
    nb INT,
    idactivite INT,
    DateSortieStock DATE,
    FOREIGN KEY (idactivite) REFERENCES activite (idactivite)
);

create table Fabrication(
    idFabrication serial primary key,
    idvoyage INT,
    DateFabrication DATE,
    nb INT,
    FOREIGN KEY (idvoyage) REFERENCES voyage(idvoyage)
);

create table CategorieMpiasa(
    id serial primary key,
    Categorie VARCHAR(20)
);

create table Mpiasa(
    id serial primary key,
    Nom VARCHAR(50),
    Dtn DATE,
    idCategorie int,
    SalaireHoraire double precision,
    DtEmbauche DATE,
    FOREIGN KEY (idCategorie) REFERENCES CategorieMpiasa(id)
);

create table FabricationVoyage(
    idVoyage int,
    idMpiasa int,
    Horaire double precision,
    FOREIGN KEY (idVoyage) REFERENCES Voyage(idvoyage),
    FOREIGN KEY (idMpiasa) REFERENCES Mpiasa(id)
);

create or replace view v_prixactivitestock as
select sum(entreestock.prix),  from entreestock



create or replace view v_resteenstock as
select sum(EntreeStock.nb)-COALESCE ((SELECT SUM(nb) FROM SortieStock WHERE idactivite=EntreeStock.idactivite),0) as stock_actuel,
EntreeStock.idactivite from EntreeStock 
group by EntreeStock.idactivite;


-- Données de test pour la table EntreeStock
INSERT INTO EntreeStock (nb, idactivite, DateEntreeStock) VALUES
    (100, 10, '2024-01-11'),
    (50, 11, '2024-01-12'),
    (75, 12, '2024-01-13');

-- Données de test pour la table SortieStock
INSERT INTO SortieStock (nb, idactivite, DateSortieStock) VALUES
    (30, 10, '2024-01-14'),
    (20, 11, '2024-01-15'),
    (10, 12, '2024-01-16');


create or replace view prixactiviteparentree as
select voyage.idvoyage,  sum (bouquetactivite.nbactivite*entreestock.prix) from voyage join bouquetactivite on voyage.idbouquet = bouquetactivite.idbouquet join entreestock on entreestock.idactivite= bouquetactivite.idactivite group by voyage.idvoyage;

create or replace view KaramaMpiasa as 
select sum(mpiasa.salairehoraire*horaire*voyage.taille), fabricationvoyage.idvoyage from
mpiasa 
join fabricationvoyage on fabricationvoyage.idmpiasa = mpiasa.id
join voyage on voyage.idvoyage = fabricationvoyage.idvoyage
group by fabricationvoyage.idvoyage;

create table Grade(
    idGrade serial primary key,
    Nom VARCHAR(50),
    Grade INT,
    ancienneteMin int,
    ancienneteMax int
);

insert into Grade (Nom,Grade,ancienneteMin,ancienneteMax) values ('Ouvrier',1,0,2);
insert into Grade (Nom,Grade,ancienneteMin,ancienneteMax) values ('Senior',2,2,3);
insert into Grade (Nom,Grade,ancienneteMin,ancienneteMax) values ('Expert',3,3,10000);

insert into categoriempiasa (categorie) values('Cuisinier');
insert into categoriempiasa (categorie) values('Gérant d hotel');


create or replace view v_checkgrade as
SELECT
    m.id,
    m.Nom,
    m.Dtn,
    m.salairehoraire*g.grade as salairehoraire,
    m.dtembauche,
    EXTRACT(YEAR FROM AGE(current_date, m.dtembauche)) as anciennete,
    g.nom AS Grade
FROM
    Mpiasa m
JOIN categoriempiasa on categoriempiasa.id= m.idcategorie
JOIN
    grade g ON EXTRACT(YEAR FROM AGE(current_date, m.dtembauche)) >= g.ancienneteMin
           AND EXTRACT(YEAR FROM AGE(current_date, m.dtembauche)) < g.ancienneteMax;



