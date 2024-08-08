package com.example.gestionimmobilier.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BD_SQLiteOpenHelper extends SQLiteOpenHelper{

//------------------------------------------------------------
// Table: TypeVilla
//------------------------------------------------------------

    private String createTableTypeVilla = " create table typeVilla ( "
            + " id int, "
            + " nbChambresPossible int, "
            + " nom text, "
            + " primary key (id) "
            +  " ); ";

//------------------------------------------------------------
// Table: Locataires
//------------------------------------------------------------

    private String createTableLocataires = " create table locataires ( "
            + " id integer primary key AUTOINCREMENT, "
            + " nom text, "
            + " prenom text, "
            + " adresse text, "
            + " numeroTelephone text, "
            + " email text, "
            + " commentaire text "
            +  " ); ";

//------------------------------------------------------------
// Table: villa
//------------------------------------------------------------

    private String createTableVilla = " create table villa ( "
            + " id integer primary key AUTOINCREMENT, "
            + " nom text , "
            + " adresse text, "
            + " descriptionV text, "
            + " descriptionP text, "
            + " superficie text, "
            + " anneesConstru int, "
            + " caution float, "
            + " idTypeVilla int, "
            + " foreign key (idTypeVilla) references typeVilla (id) "
            +  " ); ";

//------------------------------------------------------------
// Table: equipements
//------------------------------------------------------------

    private String createTableEquipements = " create table equipements ( "
            + " id int, "
            + " nom text , "
            + " description text , "
            + " caution float, "
            + " primary key (id) "
            +  " ); ";

//------------------------------------------------------------
// Table: reservations
//------------------------------------------------------------

    private String createTableReservations = " create table reservations ( "
            + " id int, "
            + " dateArrivee text , "
            + " dateDepart text, "
            + " nbAdultes int, "
            + " nbEnfants int, "
            + " dateReservation text, "
            + " optionMenage boolean, "
            + " montantR float, "
            + " idVilla int, "
            + " idLocataires int, "
            + " primary key (id), "
            + " foreign key (idVilla) references villa (id), "
            + " foreign key (idLocataires) references locataires (id) "
            +  " ); ";

//------------------------------------------------------------
// Table: roles
//------------------------------------------------------------

    private String createTableRoles = " create table roles ( "
            + " id int, "
            + " type text , "
            + " primary key (id) "
            +  " ); ";

//------------------------------------------------------------
// Table: utilisateur
//------------------------------------------------------------

    private String createTableUtilisateur = " create table utilisateur ( "
            + " id text, "
            + " login text , "
            + " motDePasse text, "
            + " idRoles int, "
            + " primary key (id), "
            + " foreign key (idRoles) references roles (id) "
            +  " ); ";

//------------------------------------------------------------
// Table: equiper
//------------------------------------------------------------

    private String createTableEquiper = " create table equiper ( "
            + " idVilla int, "
            + " idEquipements int, "
            + " etat text, "
            + " primary key (idVilla, idEquipements), "
            + " foreign key (idVilla) references villa (id), "
            + " foreign key (idEquipements) references equipements (id) "
            +  " ); ";

    public BD_SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(createTableVilla);
        db.execSQL(createTableTypeVilla);
        db.execSQL(createTableRoles);
        db.execSQL(createTableEquipements);
        db.execSQL(createTableReservations);
        db.execSQL(createTableLocataires);
        db.execSQL(createTableUtilisateur);
        db.execSQL(createTableEquiper);

        db.execSQL("INSERT INTO `typeVilla` (`id`, `nbChambresPossible`, `nom`) VALUES (1, 1, 'T1') ");
        db.execSQL("INSERT INTO `typeVilla` (`id`, `nbChambresPossible`, `nom`) VALUES (2, 2, 'T2') ");
        db.execSQL("INSERT INTO `typeVilla` (`id`, `nbChambresPossible`, `nom`) VALUES (3, 3, 'T3') ");
        db.execSQL("INSERT INTO `typeVilla` (`id`, `nbChambresPossible`, `nom`) VALUES (4, 1, 'STUDIO') ");

        db.execSQL("INSERT INTO `locataires` (`id`, `nom`, `prenom`, `adresse`, `numeroTelephone`, `email`, `commentaire`) " +
                "VALUES (3, 'Ruisseau', 'Pierre', '85 rue de Geneve AMIENS 80080', '06 96 09 17 71'," +
                " 'pierre.ruisseau@chevalier.com', 'Bon accueil') ");
        db.execSQL("INSERT INTO `locataires` (`id`, `nom`, `prenom`, `adresse`, `numeroTelephone`, `email`, `commentaire`) " +
                "VALUES (30, 'Monnier', 'Margaud', '2 Place du Jeu de Paume  VILLEJUIF 94800', '07 40 58 53 14'," +
                " 'margaud.monnier@bertin.com', 'Deçu  des chambre') ");
        db.execSQL("INSERT INTO `locataires` (`id`, `nom`, `prenom`, `adresse`, `numeroTelephone`, `email`, `commentaire`)" +
                " VALUES (58, 'Lefevre', 'Sylvie', '96 boulevard Albin Durand CERGY 95800', '06 33 22 59 95'," +
                " 'sylvie.lefevre@risoto.com', 'Satisfait') ");
        db.execSQL("INSERT INTO `locataires` (`id`, `nom`, `prenom`, `adresse`, `numeroTelephone`, `email`, `commentaire`)" +
                " VALUES (90, 'Carpentier', 'Thierry', '8 Place Napoléon LAON 02000', '07 60 09 28 90'," +
                " 'thierry.carpentier@dosso.com', 'Très bon accueil') ");
        db.execSQL("INSERT INTO `locataires` (`id`, `nom`, `prenom`, `adresse`, `numeroTelephone`, `email`, `commentaire`)" +
                " VALUES (115, 'Goncalves', 'Brigitte', '78 Place de la Madeleine PARIS 75011', '06 28 77 90 15'," +
                " 'brigitte.goncalves@ribeiro.com', 'Bien') ");

        db.execSQL("INSERT INTO `equipements` (`id`, `nom`, `description`, `caution`) VALUES " +
                "(1, 'réfrigérateur', 'Americain', 100.50) ");
        db.execSQL("INSERT INTO `equipements` (`id`, `nom`, `description`, `caution`) VALUES " +
                "(2, 'cafetière', 'Kaffait', 25.99) ");
        db.execSQL("INSERT INTO `equipements` (`id`, `nom`, `description`, `caution`) VALUES " +
                "(3, 'lave-linge', 'Avec Hublot', 150.99) ");
        db.execSQL("INSERT INTO `equipements` (`id`, `nom`, `description`, `caution`) VALUES " +
                "(4, 'table', 'Ikea', 74.99) ");
        db.execSQL("INSERT INTO `equipements` (`id`, `nom`, `description`, `caution`) VALUES " +
                "(5, 'sèche-linge', 'Miele', 125.99) ");

        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa ALIZE', '77, Rue Joseph Heuzé'," +
                " 'Villa de petite taille, parfaite pour une petite famille en vacances.', 'Il y a 1 chambres spacieuses.'," +
                " '100', '2003', '1000', 1) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa des BTS SIO', '77, Rue John-Perse '," +
                " 'Villa de grande taille, parfaite pour une classe de BTS SIO en vacances.', 'Il y a 4 chambres spacieuses.'," +
                " '100', '2003', '1000', 4) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa Thibaut', '77, Rue Thibaut'," +
                " 'Villa de petite taille, parfaite pour un couple en vacances.', 'Il y a 1 chambres spacieuses.'," +
                " '100', '2003', '1000', 1) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa du Crous', '51, Route du Crous'," +
                " 'Villa de taille normale, parfaite pour un solitaire en vacances.', 'Il y a 1 chambres spacieuses.'," +
                " '150', '2005', '3000', 1) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa MALMAISON', '8, Rue des Champs'," +
                " 'Villa de taille moyenne, parfaite pour une famille normale  en vacances.', 'Il y a 2 chambres spacieuses.'," +
                " '125', '1999', '2000', 2) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa du Tarbes', '51, Route tarbes'," +
                " 'Villa de taille normale, parfaite pour un couple en vacances.', 'Il y a 2 chambres spacieuses.'," +
                " '150', '2005', '3000', 2) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa du COTEAU', '51, Route de la Mer'," +
                " 'Villa de taille normale, parfaite pour une famille normale en vacances.', 'Il y a 3 chambres spacieuses.'," +
                " '150', '2005', '3000', 3) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa de Pau', '51, Route de Pau'," +
                " 'Villa de taille normale, parfaite pour une famille normale en vacances.', 'Il y a 3 chambres spacieuses.'," +
                " '150', '2005', '3000', 3) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa des Marseillais', '51, Route de une émission nul'," +
                " 'Villa de taille normale, parfaite pour une série nul en vacances.', 'Il y a 3 chambres spacieuses.'," +
                " '150', '2005', '3000', 3) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa CAUCHOISE', '9, Chemin de Bellevue'," +
                " 'Villa de grande taille, parfaite pour une grande famille ou entre amis en vacances.'," +
                " 'Il y a 1 chambres spacieuses.', '175', '2017', '4000', 4) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa GENEVIEVE', '11, Sente des Douaniers'," +
                " 'Villa immense, parfaite pour une grande famille ou entre amis.', 'Il y a 2 chambres spacieuses.'," +
                " '200', '2000', '5000', 2) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa de Bordes', '5 , Chemin Hilari'," +
                " 'Villa de taille normale, parfaite pour un couple en vacances.', 'Il y a 2 chambres spacieuses.'," +
                " '150', '2005', '3000', 2) ");
        db.execSQL("INSERT INTO `villa` (`nom`, `adresse`, `descriptionV`, `descriptionP`, `superficie`," +
                " `anneesConstru`, `caution`, `idTypeVilla` ) VALUES ('Villa de Bayonne', '51, Route de Bayonne'," +
                " 'Villa de taille normale, parfaite pour un couple en vacances.', 'Il y a 2 chambres spacieuses.'," +
                " '150', '2005', '3000', 2) ");

        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (1, '18-07-2022', '30-07-2022', 3, 0, '10-02-2022'," +
                " 'oui', '300', 1 , 1) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (2, '15-06-2023', '25-07-2023', 1, 3, '05-01-2023'," +
                " 'non', '400', 2 , 2) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (3, '23-07-2020', '10-08-2020', 2, 2, '20-03-2020'," +
                " 'oui', '1500', 3 , 3) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (4, '02-08-2021', '16-08-2021', 5, 0, '18-01-2021'," +
                " 'non', '3000', 4 , 4) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (5, '01-08-2022', '31-08-2022', 2, 4, '02-02-2022'," +
                " 'oui', '6000', 5 , 5) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (6, '15-06-2023', '25-07-2023', 1, 3, '05-01-2023'," +
                " 'non', '400', 1 , 2) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (7, '23-07-2020', '10-08-2020', 2, 2, '20-03-2020'," +
                " 'oui', '1500', 3 , 3) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (8, '02-08-2021', '16-08-2021', 5, 0, '18-01-2021'," +
                " 'non', '3000', 1 , 4) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (9, '01-08-2022', '31-08-2022', 2, 4, '02-02-2022'," +
                " 'oui', '6000', 5 , 5) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (10, '15-06-2023', '25-07-2023', 1, 3, '05-01-2023'," +
                " 'non', '400', 2 , 2) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (11, '23-07-2020', '10-08-2020', 2, 2, '20-03-2020'," +
                " 'oui', '1500', 3 , 3) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (12, '02-08-2021', '16-08-2021', 5, 0, '18-01-2021'," +
                " 'non', '3000', 4 , 4) ");
        db.execSQL("INSERT INTO `reservations` (`id`, `dateArrivee`, `dateDepart`, `nbAdultes`, `nbEnfants`, `dateReservation`," +
                " `optionMenage`, `montantR`, `idVilla`, `idLocataires` ) VALUES (13, '01-08-2022', '31-08-2022', 2, 4, '02-02-2022'," +
                " 'oui', '6000', 5 , 5) ");

        db.execSQL("INSERT INTO `roles` (`id`, `type`) VALUES (1, 'admin') ");
        db.execSQL("INSERT INTO `roles` (`id`, `type`) VALUES (2, 'utilisateur authentifié') ");

        db.execSQL("INSERT INTO `utilisateur` (`id`, `login`, `motDePasse`, `idRoles`) VALUES " +
                "('a10', 'admin', 'toto', 1) ");
        db.execSQL("INSERT INTO `utilisateur` (`id`, `login`, `motDePasse`, `idRoles`) VALUES " +
                "('u10', 'pruisseau', 'i62V', 2) ");
        db.execSQL("INSERT INTO `utilisateur` (`id`, `login`, `motDePasse`, `idRoles`) VALUES " +
                "('u15', 'mmonier', 'qi7G', 2) ");
        db.execSQL("INSERT INTO `utilisateur` (`id`, `login`, `motDePasse`, `idRoles`) VALUES " +
                "('u20', 'slefevre', 's5P5', 2) ");
        db.execSQL("INSERT INTO `utilisateur` (`id`, `login`, `motDePasse`, `idRoles`) VALUES " +
                "('u50', 'tcarpentier', '9v6E', 2) ");
        db.execSQL("INSERT INTO `utilisateur` (`id`, `login`, `motDePasse`, `idRoles`) VALUES " +
                "('u60', 'bgoncalves', '5ta5', 2) ");

        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (1, 1, 'Bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (2, 2, 'Tres bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (3, 3, 'Bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (4, 4, 'Tres bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (5, 5, 'Bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (6, 1, 'Tres bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (7, 2, 'Bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (8, 3, 'Tres bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (9, 4, 'Bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (10, 5, 'Tres bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (11, 1, 'Tres bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (12, 2, 'Bon état') ");
        db.execSQL("INSERT INTO `equiper` (`idVilla`, `idEquipements`, `etat`) VALUES (13, 3, 'Tres bon état') ");

        Log.d("log", "base de test cree");
    }

}

