package com.example.win.omar;

/**
 * Created by WIN on 04/03/2015.
 */
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseWrapper extends SQLiteOpenHelper {




    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "gestion";



    public DataBaseWrapper(Context context, String name, CursorFactory factory,
                           int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {




            db.execSQL("CREATE TABLE IF NOT EXISTS `famille` (`nom` varchar(100)  NOT NULL," +

                    "   ID INTEGER PRIMARY KEY   AUTOINCREMENT ) ;");

            db.execSQL("CREATE TABLE IF NOT EXISTS `forniseur` (`idr` INTEGER PRIMARY KEY   AUTOINCREMENT," +
                    "`num` varchar(255)  NOT NULL ," +
                    " `nom` varchar(255)  NOT NULL, " +
                    "`adresse` varchar(255)  NOT NULL, " +
                    "`postal` varchar(255)  NOT NULL," +
                    " `Ville` varchar(255)  NOT NULL, " +
                    "`Contact` varchar(255)  NOT NULL, " +
                    "`Tel` integer ," +
                    "`mobile` integer," +
                    "`fax` integer," +
                    "`email` varchar(255)  NOT NULL , " +
                    "`mois` integer)");


            db.execSQL("CREATE TABLE IF NOT EXISTS `Article` (  `idr` INTEGER PRIMARY KEY   AUTOINCREMENT," +
                    "`ref`  varchar(255)  NOT NULL ," +
                    " `refc` varchar(255)  NOT NULL," +
                    " `des` varchar(255) NOT NULL," +
                    " `ID` INTEGER," +
                    " `TVA` varchar(255) NOT NULL," +
                    " `Prix` INTEGER," +
                    " `quantite` INTEGER," +
                    " FOREIGN KEY (`ID`) REFERENCES `famille` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION);");


            db.execSQL("CREATE TABLE IF NOT EXISTS `COM` ( `Id` INTEGER PRIMARY KEY   AUTOINCREMENT," +
                    " `date` date NOT NULL," +
                    " `qtes`  varchar(255) NOT NULL," +
                    " `qtecom`  varchar(255) NOT NULL," +
                    "`num`  varchar(255) NOT NULL, " +
                    " `id_article` varchar(255) NOT NULL," +
                    " `ref_article` varchar(255) NOT NULL," +
                    " `prix` varchar(255) NOT NULL," +
                    " `TVA` varchar(255) NOT NULL," +
                    " `reste` varchar(255) NOT NULL," +
                    " `prixttc` varchar(255) NOT NULL," +
                    " `qte0S` varchar(255) NOT NULL," +
                    " `nom` varchar(255) NOT NULL," +
                    " `idf` INTEGER," +
                    " `heure` varchar(255) NOT NULL," +
                    " FOREIGN KEY (`num`) REFERENCES `forniseur` (`idr`) ON DELETE NO ACTION ON UPDATE NO ACTION) ;");
        }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }






}
