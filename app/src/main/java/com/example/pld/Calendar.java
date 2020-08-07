package com.example.pld;


import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity {
    String email;
    DatePicker picker;
    DatabaseHelper dbHelper;
   SQLiteDatabase db;
   Cursor cursor;
  //  SharedPreferences sp;

  TextView total,tomorrow,today;
    public static final String PREFS_NAME = "LoginPrefs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        dbHelper = new DatabaseHelper(this);
        picker= (DatePicker)findViewById(R.id.datepicker);

        // Intent intent = getIntent();
     //   email= intent.getStringExtra("email");
      SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        email=settings.getString("logged",null).toString();
      //  Toast.makeText(this, "1. "+email, Toast.LENGTH_SHORT).show();


        total=(TextView)findViewById(R.id.total);
        tomorrow=(TextView)findViewById(R.id.tommorrow) ;
        today=(TextView)findViewById(R.id.today);
        total.setText("Total : "+findtotal());
       today.setText("Today : "+findtoday());
       tomorrow.setText("Tomorrow : "+findtomorrow());


    }
    public int findtomorrow()
    {

        int count=0;
        db = dbHelper.getReadableDatabase();

        String[] columns={DatabaseContract.ADDCASE._ID,DatabaseContract.ADDCASE.COL_EMAIL, DatabaseContract.ADDCASE.COL_CaseTitle,
                DatabaseContract.ADDCASE.Col_CourtName, DatabaseContract.ADDCASE.Col_CaseType,DatabaseContract.ADDCASE.Col_CaseNumber,
                DatabaseContract.ADDCASE.Col_OnBehalfOf, DatabaseContract.ADDCASE.Col_PartyName,DatabaseContract.ADDCASE.Col_ContactNumber,
                DatabaseContract.ADDCASE.Col_RespondantName, DatabaseContract.ADDCASE.Col_PartyAdvocateName,DatabaseContract.ADDCASE.Col_AdvocateContactNumber,
                DatabaseContract.ADDCASE.Col_FeldUnderSection,DatabaseContract.ADDCASE.Col_AdjournDate
        };

        // String sortOrder = DatabaseContract.ADDCASE._ID + " ASC";
        cursor = db.query(DatabaseContract.ADDCASE.TABLE_NAME, columns,  DatabaseContract.ADDCASE.COL_EMAIL + "=?" , new String[]{email}, null, null, null, null);

        if(cursor.getCount()==0) {
            db.close();
            Toast.makeText(this, "Empty database ", Toast.LENGTH_SHORT).show();


        }

        else
        {
            while (cursor.moveToNext()) {
              //  Toast.makeText(this, " "+gettomorrowDate(), Toast.LENGTH_SHORT).show();

                if(gettomorrowDate().equals(cursor.getString(13))) {
                   // Toast.makeText(this, " "+gettomorrowDate(), Toast.LENGTH_SHORT).show();

                    count++;
                }
                //   Toast.makeText(this, " "+cursor.getString(13), Toast.LENGTH_SHORT).show();

            }
            db.close();

        }
        return count;

    }
    public int findtoday()
    {
        String adate= getCurrentDate();
        int count=0;
        db = dbHelper.getReadableDatabase();

        String[] columns={DatabaseContract.ADDCASE._ID,DatabaseContract.ADDCASE.COL_EMAIL, DatabaseContract.ADDCASE.COL_CaseTitle,
                DatabaseContract.ADDCASE.Col_CourtName, DatabaseContract.ADDCASE.Col_CaseType,DatabaseContract.ADDCASE.Col_CaseNumber,
                DatabaseContract.ADDCASE.Col_OnBehalfOf, DatabaseContract.ADDCASE.Col_PartyName,DatabaseContract.ADDCASE.Col_ContactNumber,
                DatabaseContract.ADDCASE.Col_RespondantName, DatabaseContract.ADDCASE.Col_PartyAdvocateName,DatabaseContract.ADDCASE.Col_AdvocateContactNumber,
                DatabaseContract.ADDCASE.Col_FeldUnderSection,DatabaseContract.ADDCASE.Col_AdjournDate
        };

        // String sortOrder = DatabaseContract.ADDCASE._ID + " ASC";
        cursor = db.query(DatabaseContract.ADDCASE.TABLE_NAME, columns,  DatabaseContract.ADDCASE.COL_EMAIL + "=?" , new String[]{email}, null, null, null, null);

        if(cursor.getCount()==0) {
            db.close();
            Toast.makeText(this, "Empty database ", Toast.LENGTH_SHORT).show();


        }

        else
        {
            while (cursor.moveToNext()) {
               // Toast.makeText(this, " "+getCurrentDate(), Toast.LENGTH_SHORT).show();

                if(getCurrentDate().equals(cursor.getString(13))) {
                   // Toast.makeText(this, " "+getCurrentDate(), Toast.LENGTH_SHORT).show();

                    count++;
                }
                 //   Toast.makeText(this, " "+cursor.getString(13), Toast.LENGTH_SHORT).show();

            }
            db.close();

        }
        return count;

    }

    public int findtotal()
    {
        int count=0;
        db = dbHelper.getReadableDatabase();

        String[] columns={DatabaseContract.ADDCASE._ID,DatabaseContract.ADDCASE.COL_EMAIL, DatabaseContract.ADDCASE.COL_CaseTitle,
                DatabaseContract.ADDCASE.Col_CourtName, DatabaseContract.ADDCASE.Col_CaseType,DatabaseContract.ADDCASE.Col_CaseNumber,
                DatabaseContract.ADDCASE.Col_OnBehalfOf, DatabaseContract.ADDCASE.Col_PartyName,DatabaseContract.ADDCASE.Col_ContactNumber,
                DatabaseContract.ADDCASE.Col_RespondantName, DatabaseContract.ADDCASE.Col_PartyAdvocateName,DatabaseContract.ADDCASE.Col_AdvocateContactNumber,
                DatabaseContract.ADDCASE.Col_FeldUnderSection,DatabaseContract.ADDCASE.Col_AdjournDate
        };

        // String sortOrder = DatabaseContract.ADDCASE._ID + " ASC";
        cursor = db.query(DatabaseContract.ADDCASE.TABLE_NAME, columns,  DatabaseContract.ADDCASE.COL_EMAIL + "=?" , new String[]{email}, null, null, null, null);

        if(cursor.getCount()==0) {
            db.close();
            Toast.makeText(this, "Empty database ", Toast.LENGTH_SHORT).show();


        }
        else
        {
            while (cursor.moveToNext()) {
                count++;
            }
            db.close();

        }
        return count;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK)
            Toast.makeText(getApplicationContext(), "Back Press", Toast.LENGTH_SHORT).show();
        return false;
    }

    public String gettomorrowDate() {
        StringBuilder builder = new StringBuilder();
        builder.append((picker.getMonth() + 1) + "/");//month is 0 based
        builder.append(picker.getDayOfMonth()+1 + "/");
        builder.append(picker.getYear());
        return builder.toString();
    }
    public String getCurrentDate() {
        StringBuilder builder = new StringBuilder();
        builder.append((picker.getMonth() + 1) + "/");//month is 0 based
        builder.append(picker.getDayOfMonth() + "/");
        builder.append(picker.getYear());
        return builder.toString();
    }

    public void add(View v)
    {
        String adate= getCurrentDate();

      // String adate="25/07/20";
        Intent addcase=new Intent(getApplicationContext(),addCase.class);
        addcase.putExtra("email", email);
        addcase.putExtra("adjourndate",  adate);
        startActivity(addcase);
    }
    public void show(View v)
    {
        Intent showcase=new Intent(getApplicationContext(),showCase.class);
        showcase.putExtra("email", email);
        startActivity(showcase);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
            {
                boolean log= logout();
                if(log==true)
                {
                    finish();
                }

            }
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean logout()
    {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
       // editor.remove("logged");
        editor.clear();
        editor.apply();
        return true;
       // editor.commit();


    }
}