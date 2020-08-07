package com.example.pld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class CaseHistory extends AppCompatActivity {

    Activity activity;
    ArrayList<History> casehistory=new ArrayList<History>();
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Cursor cursor;
    ListView lv;
    String casenumber;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_history);

        Intent i1 = getIntent();
        casenumber= i1.getStringExtra("casenumber");
        dbHelper = new DatabaseHelper(this);

        activity = this;
        String a="case history is empty";
        tv1=(TextView)findViewById(R.id.ch1);
        initArray();
        lv = (ListView) findViewById(R.id.chlist);

        int size=casehistory.size();
        if(casehistory.size()<=0)
        {
            //tv.setText(""+size);
            ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1, Collections.singletonList(a));
            lv.setAdapter(adapter);
        }

        tv1.setText("Case Number : "+casenumber);
        historycustomlist hcl = new  historycustomlist(activity, casehistory);
            lv.setAdapter(hcl);


    }
    public void initArray() {
        // final ArrayList<String> temp=new ArrayList<String>();
        db = dbHelper.getReadableDatabase();

        String[] columns={DatabaseContract.CASEHISTORY._ID,DatabaseContract.CASEHISTORY.Col_CaseNumber,
                DatabaseContract.CASEHISTORY.COL_Previousdate ,
                DatabaseContract.CASEHISTORY.COL_Adjourndate , DatabaseContract.CASEHISTORY.COL_Step
        };

        // String sortOrder = DatabaseContract.ADDCASE._ID + " ASC";
        cursor = db.query(DatabaseContract.CASEHISTORY.TABLE_NAME, columns,DatabaseContract.CASEHISTORY.Col_CaseNumber + "=?" , new String[]{casenumber},
                null, null, null, null);

        if(cursor.getCount()==0) {
            db.close();
            Toast.makeText(this, "Empty database ", Toast.LENGTH_SHORT).show();


        }
        else
        {
            while (cursor.moveToNext()) {

                casehistory.add(new History(cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4)));

            }
            db.close();

        }



    }
}