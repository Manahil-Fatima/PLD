package com.example.pld;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import static com.example.pld.DatabaseContract.Users.TABLE_NAME;

public class Register extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    EditText repassword;
    TextView text_login;
//    Button signup;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DatabaseHelper(this);

      /*  username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.signup);*/

        text_login = (TextView) findViewById(R.id.text_login);
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void Register(View v) {

        username=(EditText)findViewById(R.id.username);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);

        String usernamevalue=username.getText().toString();
        String emailvalue=email.getText().toString();
        String passwordvalue=password.getText().toString();
        String repasswordvalue=repassword.getText().toString();
    //    Toast.makeText(this, "REGISTER", Toast.LENGTH_SHORT).show();
        if(usernamevalue.length()>1 && emailvalue.length()>1 && passwordvalue.length()>1 && repasswordvalue.length()>1)
        {
            //Toast.makeText(this, "IF LENGTH>1", Toast.LENGTH_SHORT).show();
          // db = dbHelper.getReadableDatabase();
            db = dbHelper.getWritableDatabase();
            String[] columns = {DatabaseContract.Users._ID, DatabaseContract.Users.COL_FULLNAME,
                    DatabaseContract.Users.COL_EMAIL, DatabaseContract.Users.COL_Password};
            c= db.query(DatabaseContract.Users.TABLE_NAME, columns, DatabaseContract.Users.COL_EMAIL + "=?" , new String[]{emailvalue}
                    , null, null, null, null);
         //   db.close();
          //  Toast.makeText(this, "COUNT>0", Toast.LENGTH_SHORT).show();
            if (c.getCount() > 0) {

                Toast.makeText(Register.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                return;
            }

            else {
                if (!(passwordvalue.equals(repasswordvalue))) {
                    Toast.makeText(Register.this, "PASSWORD DOES NOT MATCH", Toast.LENGTH_SHORT).show();
                    return;
                } else if (passwordvalue.equals(repasswordvalue)) {
                    db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(DatabaseContract.Users.COL_FULLNAME, usernamevalue);
                    values.put(DatabaseContract.Users.COL_EMAIL, emailvalue);
                    values.put(DatabaseContract.Users.COL_Password, passwordvalue);
                    long newRowId = db.insert(DatabaseContract.Users.TABLE_NAME, null, values);
                    if (newRowId > 0) {
                        Toast.makeText(this, "New Record Inserted: " + newRowId, Toast.LENGTH_SHORT).show();
                        Toast.makeText(Register.this, "SIGN UP SUCCESSFULL", Toast.LENGTH_SHORT).show();
                        db.close();
                        Intent Next = new Intent(this, MainActivity.class);
                        startActivity(Next);

                    }
                    else {
                        Toast.makeText(Register.this, "ERROR IN DB ", Toast.LENGTH_SHORT).show();
                    }



                }
            }
            db.close();

        }
        else
        {
            Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show();
        }



    }

}
