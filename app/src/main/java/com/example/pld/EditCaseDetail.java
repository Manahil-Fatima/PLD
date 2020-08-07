package com.example.pld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;



public class EditCaseDetail extends AppCompatActivity {
    String casetile,cname,catype,casenumber,obof,partyname,respondantname,partyadvocate,undersection,contactnum,advocatecontactnum;
    TextView t4;
    EditText t1,t6,t7,t8,t9,t10,t11;
    AutoCompleteTextView t2,t3,t5;
    AutoCompleteTextView text_courtname;
    AutoCompleteTextView text_casetype;
    AutoCompleteTextView text_onbehalfof;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Cursor cursor;
   // String casenum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_case_detail);
        setTitle("Edit Case Detail");
        dbHelper = new DatabaseHelper(this);



        Intent i1 = getIntent();

        //casenumber= i1.getStringExtra("casenumber");
        casetile= i1.getStringExtra("casetitle");
        cname= i1.getStringExtra("courtname");
        catype= i1.getStringExtra("casetype");
        casenumber= i1.getStringExtra("casenumber");
        obof= i1.getStringExtra("onbehalfof");
        partyname= i1.getStringExtra("partyname");
        contactnum= i1.getStringExtra("contact");
        respondantname= i1.getStringExtra("respondantname");
        partyadvocate= i1.getStringExtra("partyadvocatename");
        advocatecontactnum= i1.getStringExtra("advocatecontact");
        undersection= i1.getStringExtra("feldundersection");

        text_courtname=findViewById(R.id.cn);
        final String[] courtname=getResources().getStringArray(R.array.courtname);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,courtname);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        text_courtname.setAdapter(adapter);

        text_casetype=findViewById(R.id.ctype);
        final String[] casetype=getResources().getStringArray(R.array.casetype);
        final ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,casetype);
        // adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        text_casetype.setAdapter(adapter2);

        text_onbehalfof=findViewById(R.id.obo);
        final String[] onbehalfof=getResources().getStringArray(R.array.onbehalfof);
        final ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,onbehalfof);
        //adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        text_onbehalfof.setAdapter(adapter3);
        text_courtname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text_courtname.showDropDown();

            }
        });

        text_casetype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text_casetype.showDropDown();

            }
        });

        text_onbehalfof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text_onbehalfof.showDropDown();

            }
        });


       // casenum=i1.getStringExtra("casenumber");
        t1 = (EditText) findViewById(R.id.ct);
        t2 = (AutoCompleteTextView) findViewById(R.id.cn);
        t3 = (AutoCompleteTextView) findViewById(R.id.ctype);
        t4 = (TextView)findViewById(R.id.cnum);
        t5 = (AutoCompleteTextView) findViewById(R.id.obo);
        t6 = (EditText)findViewById(R.id.pn);
        t7 = (EditText)findViewById(R.id.contact);
        t8 = (EditText)findViewById(R.id.rn);
        t9 = (EditText)findViewById(R.id.advocatename);
        t10=(EditText)findViewById(R.id.acn);
        t11=(EditText)findViewById(R.id.fus);
       // search();

       t1.setText(casetile);
        t2.setText(cname);
        t3.setText(catype);
        t4.setText("  "+casenumber);
        t5.setText(obof);
        t6.setText(partyname);
        t7.setText(contactnum);
        t8.setText(respondantname);
        t9.setText(partyadvocate);
        t10.setText( advocatecontactnum);
        t11.setText(undersection);

    }

    public void savecase(View view)
    {

        Toast.makeText(getBaseContext(), "Update clicked", Toast.LENGTH_SHORT).show();
        db = dbHelper.getWritableDatabase();

        String ct,cn,ctype,cnum,obo,pn,contact,rn,advocatename,acn,fus;

        ct=t1.getText().toString();
       cn=t2.getText().toString();
       ctype=t3.getText().toString();
       cnum=t4.getText().toString();
       obo=t5.getText().toString();
       pn=t6.getText().toString();
       contact=t7.getText().toString();
       rn=t8.getText().toString();
       advocatename=t9.getText().toString();
       acn=t10.getText().toString();
       fus=t11.getText().toString();



        ContentValues values = new ContentValues();

        values.put(DatabaseContract.ADDCASE.COL_CaseTitle,ct);
        values.put(DatabaseContract.ADDCASE.Col_CourtName, cn);
        values.put(DatabaseContract.ADDCASE.Col_CaseType,ctype);
        //values.put(DatabaseContract.ADDCASE.Col_CaseNumber,cnum);
        values.put(DatabaseContract.ADDCASE.Col_OnBehalfOf,obo);
        values.put(DatabaseContract.ADDCASE.Col_PartyName,pn);
        values.put(DatabaseContract.ADDCASE.Col_ContactNumber,contact);
        values.put(DatabaseContract.ADDCASE.Col_RespondantName,rn);
        values.put(DatabaseContract.ADDCASE.Col_PartyAdvocateName, advocatename);
        values.put(DatabaseContract.ADDCASE.Col_AdvocateContactNumber,acn);
        values.put(DatabaseContract.ADDCASE.Col_FeldUnderSection,fus);
        //  Toast.makeText(getBaseContext(), "aa = "+aa, Toast.LENGTH_SHORT).show();
        //  Toast.makeText(getBaseContext(), "pp = "+pp, Toast.LENGTH_SHORT).show();
        // Toast.makeText(getBaseContext(), "n1 = "+n1, Toast.LENGTH_SHORT).show();

        String[] wherearg={casenumber};
        Integer count= db.update(DatabaseContract.ADDCASE.TABLE_NAME, values, DatabaseContract.ADDCASE.Col_CaseNumber + "=?",wherearg);
        Toast.makeText(getBaseContext(), "count = "+count, Toast.LENGTH_SHORT).show();
        if (count > 0) {
            Toast.makeText(this, count+"  Records updated: " , Toast.LENGTH_SHORT).show();

        }
        db.close();
        Intent intent=new Intent(getApplicationContext(),Calendar.class);
        startActivity(intent);

        finish();


        /*else
        {
            Toast.makeText(this, count+"  Records not updated: " , Toast.LENGTH_SHORT).show();
            finish();
        }*/


    }
}