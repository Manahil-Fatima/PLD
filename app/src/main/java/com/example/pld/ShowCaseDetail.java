package com.example.pld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.pld.DatabaseContract.CASEHISTORY.COL_Adjourndate;
import static com.example.pld.DatabaseContract.CASEHISTORY.Col_CaseNumber;
import static com.example.pld.DatabaseContract.CASEHISTORY.TABLE_NAME;

public class ShowCaseDetail extends AppCompatActivity {

    String casetile,courtname,casetype,casenumber,onbehalfof,partyname, adjorrndate,respondantname,partyadvocate,undersection,contactnum,advocatecontactnum;
TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    String a,p,s;
    Cursor cursor;
    final int MY_REQUEST_CODE=42;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_case_detail);
        dbHelper = new DatabaseHelper(this);


      /*  if(cursor.getCount()==0) {
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.CASEHISTORY.COL_CaseNumber,casenumber);
            values.put(DatabaseContract.CASEHISTORY.COL_Adjourndate,adjorrndate );
            long newRowId = db.insert(DatabaseContract.CASEHISTORY.TABLE_NAME, null, values);
            Toast.makeText(this, "New Record Inserted: " + newRowId, Toast.LENGTH_SHORT).show();
            db.close();
            //Toast.makeText(this, "Empty database ", Toast.LENGTH_SHORT).show();


        }
        else
            db.close();
        Toast.makeText(this, "showcase detail: addhistory: ", Toast.LENGTH_SHORT).show();*/
        Intent i1 = getIntent();

       // casenumber= i1.getStringExtra("casenumber");
      //  addhistory();
     /*   t1 = (TextView) findViewById(R.id.ct);
        t2 = (TextView) findViewById(R.id.cn);
        t3 = (TextView)findViewById(R.id.ctype);
        t4 = (TextView)findViewById(R.id.cnum);
        t5 = (TextView)findViewById(R.id.obo);
        t6 = (TextView)findViewById(R.id.pn);
        t7 = (TextView)findViewById(R.id.contact);
        t8 = (TextView)findViewById(R.id.rn);
        t9 = (TextView)findViewById(R.id.advocatename);
        t10=(TextView)findViewById(R.id.acn);
        t11=(TextView)findViewById(R.id.fus);
        t12=(TextView)findViewById(R.id.ad);
        search();*/

        casetile= i1.getStringExtra("casetitle");
        courtname= i1.getStringExtra("courtname");
        casetype= i1.getStringExtra("casetype");
        casenumber= i1.getStringExtra("casenumber");
        onbehalfof= i1.getStringExtra("onbehalfof");
        partyname= i1.getStringExtra("partyname");
        contactnum= i1.getStringExtra("contact");
        respondantname= i1.getStringExtra("respondantname");
        partyadvocate= i1.getStringExtra("partyadvocatename");
        advocatecontactnum= i1.getStringExtra("advocatecontact");
        undersection= i1.getStringExtra("feldundersection");
     //   adjorrndate= i1.getStringExtra("adjurndate");

        t1 = (TextView) findViewById(R.id.ct);
        t2 = (TextView) findViewById(R.id.cn);
        t3 = (TextView)findViewById(R.id.ctype);
        t4 = (TextView)findViewById(R.id.cnum);
        t5 = (TextView)findViewById(R.id.obo);
        t6 = (TextView)findViewById(R.id.pn);
        t7 = (TextView)findViewById(R.id.contact);
        t8 = (TextView)findViewById(R.id.rn);
        t9 = (TextView)findViewById(R.id.advocatename);
        t10=(TextView)findViewById(R.id.acn);
        t11=(TextView)findViewById(R.id.fus);
        t12=(TextView)findViewById(R.id.ad);
        t13=(TextView)findViewById(R.id.pd);
        t14=(TextView)findViewById(R.id.step);

        find();
        t1.setText(casetile);
        t2.setText(courtname);
        t3.setText(casetype);
        t4.setText(casenumber);
        t5.setText(onbehalfof);
        t6.setText(partyname);
        t7.setText(contactnum);
        t8.setText(respondantname);
        t9.setText(partyadvocate);
        t10.setText( advocatecontactnum);
        t11.setText(undersection);
        adjorrndate = t12.getText().toString();
     //   t12.setText(adjorrndate);
      // addhistory();

    }
    public void find()
    {
        db = dbHelper.getReadableDatabase();

        String[] columns={DatabaseContract.CASEHISTORY._ID, Col_CaseNumber,
                DatabaseContract.CASEHISTORY.COL_Previousdate ,
                COL_Adjourndate , DatabaseContract.CASEHISTORY.COL_Step
        };

        // String sortOrder = DatabaseContract.ADDCASE._ID + " ASC";//COL_EMAIL + "=?" + " AND " + COL_Password + "=?", new String[]{emailvalue, passwordvalue}
        cursor = db.query(TABLE_NAME, columns,Col_CaseNumber + "=?", new String[]{casenumber},
                null, null, null, null);


        if(cursor.getCount()<=0)
        {
            Toast.makeText(this, "Empty database ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()) {

                a=cursor.getString(3);
                p=cursor.getString(2);
                s=cursor.getString(4);

            }
            t12.setText(a);
            t13.setText(p);
            t14.setText(s);

            db.close();

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK) {

            //t12.setText(data.getStringExtra("Adjourndate"));
            adjorrndate=data.getStringExtra("Adjourndate");
            find();
            updateadjourn();

        }
    }

    public void share(View view)
    {

        Intent intent = new Intent(getApplicationContext(),share.class);
        intent.putExtra("casetitle", casetile);
        intent.putExtra("courtname", courtname);
        intent.putExtra("casetype", casetype);
        intent.putExtra("casenumber", casenumber);
        intent.putExtra("onbehalfof",onbehalfof);
        intent.putExtra("partyname",partyname);
        intent.putExtra("contact", contactnum);
        intent.putExtra("respondantname", respondantname);
        intent.putExtra("partyadvocatename", partyadvocate);
        intent.putExtra("advocatecontact", advocatecontactnum);
        intent.putExtra("feldundersection", undersection);
        intent.putExtra("adjourndate", adjorrndate);
        intent.putExtra("casetile", casetile);
        intent.putExtra("advocatecontactnum", advocatecontactnum);
        intent.putExtra("partyadvocate", partyadvocate);
        intent.putExtra("undersection", undersection);
        startActivity(intent);
    }


public void updateadjourn()
{
    db = dbHelper.getWritableDatabase();


    ContentValues values = new ContentValues();


    values.put(DatabaseContract.ADDCASE.Col_AdjournDate,adjorrndate);
    String[] wherearg={casenumber};
    Integer count= db.update(DatabaseContract.ADDCASE.TABLE_NAME, values, DatabaseContract.ADDCASE.Col_CaseNumber + "=?",wherearg);
    Toast.makeText(getBaseContext(), "count = "+count, Toast.LENGTH_SHORT).show();
    if (count > 0) {
        Toast.makeText(this, count+"  Records updated: " , Toast.LENGTH_SHORT).show();

    }
    db.close();
}


   
    public void casehistory(View view)
    {

       Intent intent = new Intent(getApplicationContext(), CaseHistory.class);
       intent.putExtra("casenumber", casenumber);
        startActivity(intent);

    }
    public void step(View view)
    {
        Intent intent =new Intent();
        intent.setClassName("com.example.pld","com.example.pld.dialog");
        intent.putExtra("casenumber", casenumber);
        intent.putExtra("adjourndate",adjorrndate);
        startActivityForResult(intent,MY_REQUEST_CODE);
       /* Intent intent = new Intent(getApplicationContext(), dialog.class);
        intent.putExtra("casenumber", casenumber);
        intent.putExtra("adjourndate",adjorrndate);
        startActivity(intent);
*/
    }
    public void Edit(View view)
    {

        Intent intent = new Intent(getApplicationContext(), EditCaseDetail.class);
      //  intent.putExtra("casenumber", casenumber);
       intent.putExtra("casetitle", casetile);
        intent.putExtra("courtname", courtname);
        intent.putExtra("casetype", casetype);
        intent.putExtra("casenumber", casenumber);
        intent.putExtra("onbehalfof",onbehalfof);
        intent.putExtra("partyname",partyname);
        intent.putExtra("contact", contactnum);
        intent.putExtra("respondantname", respondantname);
        intent.putExtra("partyadvocatename", partyadvocate);
        intent.putExtra("advocatecontact", advocatecontactnum);
        intent.putExtra("feldundersection", undersection);
        startActivity(intent);

    }
    public void reminder(View view)
    { partyname=t6.getText().toString();
      contactnum=t7.getText().toString();

        Intent intent = new Intent(getApplicationContext(),Reminder.class);
    intent.putExtra("casenumber",casenumber);
    intent.putExtra("contact",contactnum);
    intent.putExtra("partyname",partyname);
    intent.putExtra("adjourndate",adjorrndate);
        startActivity(intent);
    }
}