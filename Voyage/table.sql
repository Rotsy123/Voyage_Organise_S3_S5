-- /////////////////////////////////////////////////////////////////////////////////////////
-- TABLES
create table activite(
    idactivite serial primary key,
    nom varchar(50),
    prix double precision
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



create table EntreeStock(
    idEntreeStock serial primary key,
    nb INT,
    idactivite INT,
    DateEntreeStock DATE,
    prix double precision,
    FOREIGN KEY (idactivite) REFERENCES activite (idactivite)
);


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
    dtembauche date,
    FOREIGN KEY (idCategorie) REFERENCES CategorieMpiasa(id)
);

create table FabricationVoyage(
    idVoyage int,
    idMpiasa int,
    Horaire double precision,
    FOREIGN KEY (idVoyage) REFERENCES Voyage(idvoyage),
    FOREIGN KEY (idMpiasa) REFERENCES Mpiasa(id)
);

create table client(
    id serial primary key,
    nom varchar(100),
    dtn DATE,
    Genre varchar(2)
);

create table vente(
    id serial primary key,
    idvoyage int,
    nbpdtlafo int,
    idclient int,
    dateachat date,
    FOREIGN KEY (idclient) REFERENCES client(id),
    FOREIGN KEY (idVoyage) REFERENCES Voyage(idvoyage)
);
-- /////////////////////////////////////////////////////////////////////////////////////////
-- VIEW
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


create or replace view v_prixVoyage as
(
    select voyage.idvoyage,sum(activite.prix*bouquetactivite.nbactivite) from voyage join bouquetactivite on bouquetactivite.idbouquet=voyage.idbouquet join activite on bouquetactivite.idactivite=activite.idactivite group by voyage.idvoyage
);

create or replace view v_vraiPrixVoyage as
select voyage.idvoyage,voyage.idbouquet,voyage.dureejours,voyage.idcategorie,v_prixVoyage.sum from voyage join v_prixVoyage on voyage.idvoyage=v_prixVoyage.idvoyage;

-- maka activite anaty voyage
create view v_voyagebouquetact as
select idvoyage,voyage.idbouquet,dureejours,idcategorie,prix,idactivite,nbactivite from voyage join bouquetactivite on voyage.idbouquet=bouquetactivite.idbouquet;

create or replace view v_prixactivitestock as
select sum(entreestock.prix),  from entreestock

create or replace view v_resteenstock as
select sum(EntreeStock.nb)-COALESCE ((SELECT SUM(nb) FROM SortieStock WHERE idactivite=EntreeStock.idactivite),0) as stock_actuel,
EntreeStock.idactivite from EntreeStock 
group by EntreeStock.idactivite;


create or replace view prixactiviteparentree as
select voyage.idvoyage,  sum (bouquetactivite.nbactivite*entreestock.prix) from voyage 
join bouquetactivite on voyage.idbouquet = bouquetactivite.idbouquet 
join entreestock on entreestock.idactivite= bouquetactivite.idactivite group by voyage.idvoyage;

create or replace view KaramaMpiasa as 
select sum(mpiasa.salairehoraire*horaire*voyage.taille), fabricationvoyage.idvoyage from
mpiasa 
join fabricationvoyage on fabricationvoyage.idmpiasa = mpiasa.id
join voyage on voyage.idvoyage = fabricationvoyage.idvoyage
group by fabricationvoyage.idvoyage;


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
JOIN
    grade g ON EXTRACT(YEAR FROM AGE(current_date, m.dtembauche)) >= g.ancienneteMin
           AND EXTRACT(YEAR FROM AGE(current_date, m.dtembauche)) < g.ancienneteMax;


create or replace view v_statistiquevente as
SELECT 
    v.idvoyage,
    SUM(CASE WHEN c.genre = '0' THEN v.nbpdtlafo ELSE 0 END) AS nb_produits_masculin,
    SUM(CASE WHEN c.genre = '1' THEN v.nbpdtlafo ELSE 0 END) AS nb_produits_feminin,
COUNT(DISTINCT CASE WHEN c.genre = '0' THEN c.id END) as genre_masculin,
    COUNT(DISTINCT CASE WHEN c.genre = '1' THEN c.id END) as genre_feminin
FROM 
    vente v
JOIN 
    client c ON v.idclient = c.id
GROUP BY 
    v.idvoyage;


create or replace view v_statistique as
SELECT 
    SUM(CASE WHEN c.genre = '0' THEN v.nbpdtlafo ELSE 0 END) AS nb_produits_masculin,
    SUM(CASE WHEN c.genre = '1' THEN v.nbpdtlafo ELSE 0 END) AS nb_produits_feminin,
     COUNT(DISTINCT CASE WHEN c.genre = '0' THEN c.id END) as genre_masculin,
    COUNT(DISTINCT CASE WHEN c.genre = '1' THEN c.id END) as genre_feminin
FROM 
    vente v
JOIN 
    client c ON v.idclient = c.id;



create or replace view v_statistiquevente as
SELECT 
    vo.idvoyage,
    COALESCE (SUM(CASE WHEN c.genre = '0' THEN v.nbpdtlafo ELSE 0 END),0) AS nb_produits_masculin,
    COALESCE (SUM(CASE WHEN c.genre = '1' THEN v.nbpdtlafo ELSE 0 END),0) AS nb_produits_feminin,
    COALESCE (COUNT(DISTINCT CASE WHEN c.genre = '0' THEN c.id END), 0)as genre_masculin,
    COALESCE (COUNT(DISTINCT CASE WHEN c.genre = '1' THEN c.id END),0) as genre_feminin
FROM 
    voyage vo
LEFT JOIN vente v ON vo.idvoyage = v.idVoyage
LEFT JOIN 
    client c ON v.idclient = c.id
    GROUP BY vo.idvoyage;

    
create or replace view v_restefabrique as
select sum(fabrication.nb)-COALESCE ((SELECT SUM(nbpdtlafo) FROM vente WHERE idvoyage=fabrication.idvoyage),0) as stock_actuel,
fabrication.idvoyage from fabrication 
group by fabrication.idvoyage;