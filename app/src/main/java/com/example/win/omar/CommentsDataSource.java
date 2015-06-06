package com.example.win.omar;

/**
 * Created by WIN on 04/03/2015.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

    public class CommentsDataSource {
        private SQLiteDatabase database;
        private DataBaseWrapper dbHelper;
        private static final int DATABASE_VERSION = 1;



        private static final String DATABASE_NAME = "gestion";
        public CommentsDataSource(Context context) {
            dbHelper = new DataBaseWrapper(context,DATABASE_NAME, null,DATABASE_VERSION);
        }

        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
        }
        public void close() {
            dbHelper.close();
        }


        public void insertFamille(String nom) {
            ContentValues values = new ContentValues();
            values.put("nom", nom);
            database.insert("famille", null, values);
            database.close();
        }
        public String selection_Famille() {
            String[] nom1 = new String[]{"nom", "ID"};

            Cursor c = database.query("famille", nom1, null, null, null, null, null, null);
            String resultat = "";
            int nom = c.getColumnIndexOrThrow("nom");
            int id = c.getColumnIndexOrThrow("ID");


            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                resultat = resultat + c.getString(nom) + "/" + c.getString(id) + "//";

            }
            return resultat;
        }
        public String selection_F(int id1) {
            String[] nom1 = new String[]{"nom", "ID"};

            Cursor c = database.query("famille", nom1, "ID="+id1, null, null, null, null, null);
            String resultat = "";
            int nom = c.getColumnIndexOrThrow("nom");
            int id = c.getColumnIndexOrThrow("ID");


            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                resultat = resultat + c.getString(nom);

            }
            return resultat;
        }
        public String selection_A(int idr) {
            String[] nom1 = new String[]{"idr","ref", "refc", "des","ID","TVA","Prix","quantite"};

            Cursor c = database.query("Article", nom1, "idr="+idr, null, null, null, null, null);
            String resultat = "";
            int nom = c.getColumnIndexOrThrow("ID");



            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                resultat = resultat + c.getString(nom);

            }
            return resultat;
        }

        public void update_byID_fam(String nom,int idr){

            ContentValues values = new ContentValues();
            values.put("nom", nom);
            database.update("famille", values, "ID="+idr , null);
        }

        public void insert_forniseur(String num,String nom,String adresse,String postal,String Ville,String Contact,int Tel,int mobile,int fax,String email){

            ContentValues values = new ContentValues();

            values.put("num", num);
            values.put("nom", nom);
            values.put("adresse",adresse);
            values.put("postal",postal);
            values.put("Ville",Ville );
            values.put("Contact", Contact);
            values.put("Tel", Tel);
            values.put("mobile", mobile);
            values.put("fax", fax);
            values.put("email", email);


            database.insert("forniseur", null, values);
        }


        public String selection_Forniseur(){

            String[] nom= new String[]{"idr","num", "nom", "adresse","postal","Ville","Contact","Tel","mobile", "fax","email"};

            Cursor c = database.query("forniseur", nom, null, null, null, null, null, null);
            String resultat= "";
            int idr= c.getColumnIndexOrThrow("idr");
            int code= c.getColumnIndexOrThrow("num");
            int id= c.getColumnIndexOrThrow("nom");
            int marque= c.getColumnIndexOrThrow("adresse");
            int modele= c.getColumnIndexOrThrow("postal");
            int numero= c.getColumnIndexOrThrow("Ville");
            int annee= c.getColumnIndexOrThrow("Contact");
            int puissance= c.getColumnIndexOrThrow("Tel");
            int motorisation= c.getColumnIndexOrThrow("mobile");
            int km= c.getColumnIndexOrThrow("fax");
            int km1= c.getColumnIndexOrThrow("email");

            for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
                resultat=resultat+ c.getString(idr)+"/" + c.getString(code) +"/"+c.getString(id) +"/"+c.getString(marque) +"/"+c.getString(modele) +"/"+c.getString(numero) +"/"+c.getString(annee) +"/"+c.getString(puissance) +"/"+c.getString(motorisation)+"/"+c.getString(km)+"/"+c.getString(km1)+"//";
            }
            return resultat;
        }
        //-----------------------------------------------maison-----------------------
        public void insert_Article(String ref,String refc,String des,int ID,String TVA,int Prix,int quantite){

            ContentValues values = new ContentValues();

            values.put("ref", ref);
            values.put("refc", refc);
            values.put("des", des);
            values.put("ID", ID);
            values.put("TVA", TVA);
            values.put("Prix", Prix);
            values.put("quantite", quantite);

            database.insert("Article", null, values);
        }
        public String selection_Article(){

            String[] nom= new String[]{"idr","ref", "refc", "des","ID","TVA","Prix","quantite"};

            Cursor c = database.query("Article", nom, null, null, null, null, null, null);
            String resultat= "";
            int idr= c.getColumnIndexOrThrow("idr");
            int code= c.getColumnIndexOrThrow("ref");
            int id= c.getColumnIndexOrThrow("refc");
            int marque= c.getColumnIndexOrThrow("des");
            int modele= c.getColumnIndexOrThrow("ID");
            int annee= c.getColumnIndexOrThrow("TVA");
            int puissance= c.getColumnIndexOrThrow("Prix");
            int motorisation= c.getColumnIndexOrThrow("quantite");

            for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
                resultat=resultat+ c.getString(idr)+"/" + c.getString(code) +"/"+c.getString(id) +"/"+c.getString(marque) +"/"+c.getString(modele) +"/"+c.getString(annee) +"/"+c.getString(puissance) +"/"+c.getString(motorisation)+"//";
            }
            return resultat;
        }



        //-----------------------------------------------piece-----------------------
        public void update_byID_piece(int idr,String ref,String refc,String des,int id ,String TVA,int prix,int qte){

            ContentValues values = new ContentValues();
            values.put("ref", ref);
            values.put("refc", refc);
            values.put("des", des);
            values.put("ID", id);
            values.put("TVA",TVA);
            values.put("Prix", prix);
            values.put("quantite", qte);
            database.update("Article", values, "idr="+idr , null);
        }
        public void insert_Commande(String date,String qteS,String qtecom,String num,String id_article,String ref_article,String prix,String TVA,String reste,String prixttc,String qte0S,String nom,int id,String heure){

            ContentValues values = new ContentValues();

            values.put("date", date);
            values.put("qtes", qteS);
            values.put("qtecom", qtecom);
            values.put("num", num);
            values.put("id_article", id_article);
            values.put("ref_article", ref_article);
            values.put("prix", prix);
            values.put("TVA", TVA);
            values.put("reste", reste);
            values.put("prixttc", prixttc);
            values.put("qte0S", qte0S);
            values.put("nom", nom);
            values.put("idf", id);
            values.put("heure", heure);
            database.insert("COM", null, values);
        }
        public String selection_comm(){

            String[] nom1= new String[]{"Id","date","qtes","qtecom","num","id_article","ref_article","prix","TVA","reste","prixttc","qte0S","nom","idf","heure"};

            Cursor c = database.query("COM", nom1, null, null, null, null, null, null);
            String resultat= "";
            int code= c.getColumnIndexOrThrow("Id");
            int id= c.getColumnIndexOrThrow("date");
            int marque= c.getColumnIndexOrThrow("qtes");
            int modele= c.getColumnIndexOrThrow("qtecom");
            int annee= c.getColumnIndexOrThrow("num");
            int idar= c.getColumnIndexOrThrow("id_article");
            int puissance= c.getColumnIndexOrThrow("ref_article");
            int motorisation= c.getColumnIndexOrThrow("prix");
            int motorisation1= c.getColumnIndexOrThrow("TVA");
            int motorisation2= c.getColumnIndexOrThrow("reste");
            int motorisation3= c.getColumnIndexOrThrow("prixttc");
            int motorisation4= c.getColumnIndexOrThrow("qte0S");
            int motorisation5= c.getColumnIndexOrThrow("nom");
            int motorisation6= c.getColumnIndexOrThrow("idf");
            int motorisation7= c.getColumnIndexOrThrow("heure");

            for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
                resultat=resultat + c.getString(code) +"/"+c.getString(id) +"/"+c.getString(marque) +"/"+c.getString(modele) +"/"+c.getString(annee)+"/"+c.getString(idar) +"/"+c.getString(puissance)+"/"+c.getString(motorisation)+"/"+c.getString(motorisation1)+"/"+c.getString(motorisation2)+"/"+c.getString(motorisation3)+"/"+c.getString(motorisation4)+"/"+c.getString(motorisation5)+"/"+c.getString(motorisation6)+"/"+c.getString(motorisation7)+"//";
            }
            return resultat;
        }


        //-----------------------------------------------date-----------------------


        public String selection_valeurs_date_piece1(){
            String[] nom= new String[]{"identifiant","libelle"};
            Cursor c = database.query("pieces",nom, null, null, null, null,null);
            String resultat= "";
            int identifiant= c.getColumnIndexOrThrow("identifiant");
            int libelle= c.getColumnIndexOrThrow("libelle");
            for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
                resultat=resultat + c.getString(libelle) +"/";
                String[] nom1= new String[]{"date_proch_ent"};
                Cursor c1 = database.query("date_ent",nom1, "ref_pieces="+c.getString(identifiant), null, null, null,null);
                int date_proch_ent= c1.getColumnIndexOrThrow("date_proch_ent");
                for(c1.moveToFirst(); !c1.isAfterLast(); c1.moveToNext()){
                    resultat=resultat+c1.getString(date_proch_ent);
                }
                resultat=resultat+"\n";
            }
            Log.i("resultat", resultat);
            String str[]=resultat.split("\n");

            for(int i=0;i<str.length;i++){
                Log.i("1str[i]",str[i]);
            }

            //tri
            int longueur=str.length;
            boolean inversion;
            Date date11 = null,date22 = null;
            do
            {
                inversion=false;

                for(int i=0;i<longueur-1;i++)
                {
                    String date1,date2;
                    String str1[]=str[i].split("/");
                    date1=str1[1];//date1
                    String str11[]=str[i+1].split("/");
                    date2=str11[1];//date2
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        date11 = sdf.parse(date1);
                        date22 = sdf.parse(date2);
                        if (date11.compareTo(date22 ) == -1) {
                            // traitement du cas date1 < date2
                            String x=str[i];
                            str[i]=str[i+1];
                            str[i+1]=x;
                            inversion=true;
                        }
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
            while(inversion);

            resultat="";
            for(int i=0;i<str.length;i++){
                Log.i("str[i]",str[i]);
                resultat=resultat+str[i]+"//";
            }
            return resultat;
        }

    }


