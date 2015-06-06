package com.example.win.omar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends Activity {


    EditText login;
    EditText password;
    ImageView btn ;
    String login_str="", password_str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.login= (EditText)this.findViewById(R.id.editText2);
        this.password= (EditText)this.findViewById(R.id.editText3);


        this.login.setText("");
        this.password.setText("");


        btn = (ImageView)this.findViewById(R.id.connexionBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login_str = login.getText().toString();
                password_str = password.getText().toString();
                boolean valid= false;


                    if (login_str.equals("gestion") && password_str.equals("gestion"))
                    {


                        Toast.makeText(MainActivity.this    ,"Bienvenue " , Toast.LENGTH_LONG).show();
                        Intent i =new Intent(MainActivity.this,entree.class);
                        startActivity(i);
                        valid=true;


                    }

                if(!valid){
                    Toast.makeText(MainActivity.this, "votre authentification est échoué!, ressayer une autre fois", Toast.LENGTH_LONG).show();
                }

            }




        });
    }
}

