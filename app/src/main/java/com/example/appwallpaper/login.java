package com.example.appwallpaper;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class login extends AppCompatActivity {
    private Button log;
    private Button reg;
    private EditText adminname;
    private EditText password;
    DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Intent i=getIntent();
        adminname = findViewById(R.id.aname);
        password = findViewById(R.id.pwd);
        log = findViewById(R.id.log);
        reg= findViewById(R.id.reg);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = password.getText().toString().trim();
                String adm = adminname.getText().toString().trim();
                if(pwd.isEmpty()||adm.isEmpty()){

                    TextView ad = (TextView) findViewById(R.id.tex);
                    ad.setText("Invalid Username  Pasword");
                }
                else {
                    boolean id = dbHelper.getUser(adm, pwd);
                    while (id) {
                        Intent intent = new Intent(login.this, displayfrag.class);
                        startActivity(intent);

                    }
                    TextView ad = (TextView) findViewById(R.id.tex);
                    ad.setText("Invalid Username  Pasword");

                }
            }   });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(login.this,RegisterActivity.class);
                    startActivity(intent);
            }   });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.optionmenu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}



