package com.example.pld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class showCase extends AppCompatActivity {

    Activity activity;
    ArrayList<CaseDetail> casedetail=new ArrayList<CaseDetail>();
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Cursor cursor;
    TextView tv;
    ListView lv;
    String emailvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_case);

        Intent i1 = getIntent();
        emailvalue= i1.getStringExtra("email");

        dbHelper = new DatabaseHelper(this);
        activity = this;
        String a="NO Adjourn Case Data .";
        initArray();

        tv=(TextView)findViewById(R.id.totalcases);
        lv = (ListView) findViewById(R.id.list);
        int size=casedetail.size();

        if(casedetail.size()<=0)
        {
            tv.setText(""+size);
            ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1, Collections.singletonList(a));
            lv.setAdapter(adapter);
        }
        else {
            tv.setText(""+size);
            customlistview clv = new customlistview(activity, casedetail);
            lv.setAdapter(clv);
        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ShowCaseDetail.class);
              //  intent.putExtra("casenumber", casedetail.get(position).getcasenumber());
               intent.putExtra("casetitle", casedetail.get(position).getcasetitle());
                intent.putExtra("courtname", casedetail.get(position).getcourtname());
                intent.putExtra("casetype", casedetail.get(position).getcasetype());
                intent.putExtra("casenumber", casedetail.get(position).getcasenumber());
                intent.putExtra("onbehalfof", casedetail.get(position).getonbehalfof());
                intent.putExtra("partyname", casedetail.get(position).getpartyname());
                intent.putExtra("contact", casedetail.get(position).getcontactnumber());
                intent.putExtra("respondantname", casedetail.get(position).getrespondantname());
                intent.putExtra("partyadvocatename", casedetail.get(position).getadversepartyadvocatename());
                intent.putExtra("advocatecontact", casedetail.get(position).getadvocatecontactnumber());
                intent.putExtra("feldundersection", casedetail.get(position).getfeldundersection());
              //  intent.putExtra("adjurndate", casedetail.get(position).getadjourndate());
                startActivity(intent);

            }
        });


    }

    public void initArray() {

        db = dbHelper.getReadableDatabase();

        String[] columns={DatabaseContract.ADDCASE._ID,DatabaseContract.ADDCASE.COL_EMAIL, DatabaseContract.ADDCASE.COL_CaseTitle,
                DatabaseContract.ADDCASE.Col_CourtName, DatabaseContract.ADDCASE.Col_CaseType,DatabaseContract.ADDCASE.Col_CaseNumber,
                DatabaseContract.ADDCASE.Col_OnBehalfOf, DatabaseContract.ADDCASE.Col_PartyName,DatabaseContract.ADDCASE.Col_ContactNumber,
                DatabaseContract.ADDCASE.Col_RespondantName, DatabaseContract.ADDCASE.Col_PartyAdvocateName,DatabaseContract.ADDCASE.Col_AdvocateContactNumber,
                DatabaseContract.ADDCASE.Col_FeldUnderSection,DatabaseContract.ADDCASE.Col_AdjournDate
        };

        // String sortOrder = DatabaseContract.ADDCASE._ID + " ASC";
        cursor = db.query(DatabaseContract.ADDCASE.TABLE_NAME, columns,  DatabaseContract.ADDCASE.COL_EMAIL + "=?" , new String[]{emailvalue}, null, null, null, null);

        if(cursor.getCount()==0) {
            db.close();
            Toast.makeText(this, "Empty database ", Toast.LENGTH_SHORT).show();


        }
        else
        {
            while (cursor.moveToNext()) {

                casedetail.add(new CaseDetail(cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7), cursor.getString(8),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11),
                        cursor.getString(12), cursor.getString(13)));

            }
            db.close();

        }



    }
}