package com.example.pld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.pld.DatabaseContract.Users.COL_EMAIL;
import static com.example.pld.DatabaseContract.Users.COL_Password;

import static com.example.pld.DatabaseContract.Users.TABLE_NAME;
public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText password;
   // Button login;
    Cursor c;
    String emailvalue ;

    TextView register;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    public static final String PREFS_NAME = "LoginPrefs";
    //SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean loggedin=isloggedIn();
        if(loggedin==true)
        {
            Intent intent = new Intent(this, Calendar.class);
            //    intent.putExtra("email", emvalue);
            startActivity(intent);

        }

        dbHelper = new DatabaseHelper(this);
       // sp=getSharedPreferences("login",MODE_PRIVATE);


       /* if(sp)
        {
            Intent intent=new Intent(getApplicationContext(),Calendar.class);
            intent.putExtra("email", emailvalue);
            startActivity(intent);
        }*/
     /*   email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.save);*/
        register = (TextView) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });

    }
    public void login(View v) {

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

         emailvalue = email.getText().toString();
        String passwordvalue = password.getText().toString();

        db = dbHelper.getReadableDatabase();
        if (emailvalue.length() > 1 && passwordvalue.length() > 1) {

            String[] columns = {DatabaseContract.Users._ID, DatabaseContract.Users.COL_FULLNAME,
                    COL_EMAIL, COL_Password};


            //String sortOrder = DatabaseContract.UserTasks._ID+ " ASC";
            c = db.query(DatabaseContract.Users.TABLE_NAME, columns, COL_EMAIL + "=?" + " AND " + COL_Password + "=?", new String[]{emailvalue, passwordvalue}
                    , null, null, null, null);
            if (c.getCount() > 0) {

                Toast.makeText(this, "LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                db.close();
                //make SharedPreferences object


                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("logged", emailvalue);
                editor.apply();
                //editor.commit();
                Intent intent=new Intent(getApplicationContext(),Calendar.class);
                intent.putExtra("email", emailvalue);
                startActivity(intent);

               // sp.edit().putBoolean("logged",true).apply();

            } else {
                Toast.makeText(MainActivity.this, "Invalid Email Or Password", Toast.LENGTH_SHORT).show();
            }
           // c.close();
            db.close();
        }
        else
        {
            Toast.makeText(this, "INVALID INPUTS", Toast.LENGTH_SHORT).show();
        }

    }
public boolean isloggedIn()
{

    /*
     * Check if we successfully logged in before.
     * If we did, redirect to home page
     */
    // Toast.makeText(MainActivity.this, "1. "+emvalue, Toast.LENGTH_SHORT).show();
    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    String ans=settings.getString("logged", null);
    Toast.makeText(MainActivity.this, "ans= "+ans, Toast.LENGTH_SHORT).show();
    if (settings.getString("logged", null)!=null) {
        return true;
    }
    return false;

}




}


