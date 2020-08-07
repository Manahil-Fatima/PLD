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

import org.w3c.dom.Text;

import static com.example.pld.DatabaseContract.ADDCASE.TABLE_NAME;


public class addCase extends AppCompatActivity {

    //private TextInputLayout casetitle;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    EditText text_casetitle, text_casenumber, text_partyname,text_contactnumber,text_respondantname,text_partyadocate,text_advocatecntactnum,text_undersection;
    AutoCompleteTextView text_courtname;
    AutoCompleteTextView text_casetype;
    AutoCompleteTextView text_onbehalfof;
    String email ,adjourndate;
    Cursor cursor;
    //  String[] string_courtname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);
        dbHelper = new DatabaseHelper(this);
        setTitle("Adding Case Detail");

        Intent intent = getIntent();
        email= intent.getStringExtra("email");
        adjourndate= intent.getStringExtra("adjourndate");
      //  Toast.makeText(this, " " + email, Toast.LENGTH_SHORT).show();
        TextView adjourn=(TextView)findViewById(R.id.adate);
        adjourn.setText(adjourndate);


        text_courtname=findViewById(R.id.courtname);
        final String[] courtname=getResources().getStringArray(R.array.courtname);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,courtname);
       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        text_courtname.setAdapter(adapter);

        text_casetype=findViewById(R.id.casetype);
        final String[] casetype=getResources().getStringArray(R.array.casetype);
        final ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,casetype);
       // adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        text_casetype.setAdapter(adapter2);

        text_onbehalfof=findViewById(R.id.onbehalfof);
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


    }
   public void InsertData(View v) {
        db = dbHelper.getWritableDatabase();

        String casetile,courtname,casetype,casenumber,onbehalfof,partyname,respondantname,partyadvocate,undersection,contactnum,advocatecontactnum;

        text_casetitle=findViewById(R.id.casetitle);
     //   text_courtname=findViewById(R.id.courtname);
      //  text_casetype=findViewById(R.id.casetype);
        text_casenumber= findViewById(R.id.casenumber);
       // text_onbehalfof=findViewById(R.id.onbehalfof);
        text_partyname=findViewById(R.id.partyname);
        text_contactnumber=findViewById(R.id.contactnumber);
        text_respondantname=findViewById(R.id.respondantname);
        text_partyadocate=findViewById(R.id.advocatename);
        text_advocatecntactnum=findViewById(R.id.advocatecontact);
        text_undersection=findViewById(R.id.fieldsection);

       casetile = text_casetitle.getText().toString();
        courtname = text_courtname.getText().toString();
        casetype = text_casetype.getText().toString();
        casenumber = text_casenumber.getText().toString();
        onbehalfof = text_onbehalfof.getText().toString();
        partyname = text_partyname.getText().toString();
        contactnum=text_contactnumber.getText().toString();
       // int contactnum=Integer.parseInt(text_contactnumber.getText().toString());
        respondantname= text_respondantname.getText().toString();
        partyadvocate = text_partyadocate.getText().toString();
      //  int advocatecontactnum= Integer.parseInt(text_advocatecntactnum.getText().toString());
       advocatecontactnum=text_advocatecntactnum.getText().toString();
        undersection=text_undersection.getText().toString();

       if(casetile.length()>1 &&courtname.length()>1 && casetype.length()>1 && casenumber.length()>1
               && onbehalfof.length()>1&& partyname.length()>1&&  contactnum.length()>1&&  respondantname.length()>1
               && partyadvocate.length()>1&& advocatecontactnum.length()>1&&  undersection.length()>1) {

           String[] columns = {DatabaseContract.CASEHISTORY._ID, DatabaseContract.CASEHISTORY.Col_CaseNumber,
                   DatabaseContract.CASEHISTORY.COL_Previousdate,
                   DatabaseContract.CASEHISTORY.COL_Adjourndate, DatabaseContract.CASEHISTORY.COL_Step
           };

           // String sortOrder = DatabaseContract.ADDCASE._ID + " ASC";
           cursor = db.query(DatabaseContract.CASEHISTORY.TABLE_NAME, columns, DatabaseContract.CASEHISTORY.Col_CaseNumber + "=?", new String[]{casenumber}, null, null, null, null);
           Toast.makeText(this, "cursor value : " + cursor.getCount(), Toast.LENGTH_SHORT).show();
           if (cursor.getCount() > 0) {
               Toast.makeText(this, "CASENO Already Exist", Toast.LENGTH_SHORT).show();
               return;
           } else {
               ContentValues values = new ContentValues();
               values.put(DatabaseContract.ADDCASE.COL_EMAIL, email);
               values.put(DatabaseContract.ADDCASE.COL_CaseTitle, casetile);
               values.put(DatabaseContract.ADDCASE.Col_CourtName, courtname);
               values.put(DatabaseContract.ADDCASE.Col_CaseType, casetype);
               values.put(DatabaseContract.ADDCASE.Col_CaseNumber, casenumber);
               values.put(DatabaseContract.ADDCASE.Col_OnBehalfOf, onbehalfof);
               values.put(DatabaseContract.ADDCASE.Col_PartyName, partyname);
               values.put(DatabaseContract.ADDCASE.Col_ContactNumber, contactnum);
               values.put(DatabaseContract.ADDCASE.Col_RespondantName, respondantname);
               values.put(DatabaseContract.ADDCASE.Col_PartyAdvocateName, partyadvocate);
               values.put(DatabaseContract.ADDCASE.Col_AdvocateContactNumber, advocatecontactnum);
               values.put(DatabaseContract.ADDCASE.Col_FeldUnderSection, undersection);
               values.put(DatabaseContract.ADDCASE.Col_AdjournDate, adjourndate);
               long newRowId = db.insert(DatabaseContract.ADDCASE.TABLE_NAME, null, values);
               if (newRowId > 0) {
                   Toast.makeText(this, "New Record Inserted: " + newRowId, Toast.LENGTH_SHORT).show();
                   db.close();
                   addhistory();
                   Intent i = new Intent(this, Calendar.class);
                   startActivity(i);
                   finish();
               } else {
                   Toast.makeText(this, "ERROR IN DATABASE ", Toast.LENGTH_SHORT).show();
               }
           }
       }
       else
           {
               Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show();
           }

       //Next.putExtra("email",val2);
       //
    }
    public void addhistory()
    {
        String casenumber= text_casenumber.getText().toString();
        Toast.makeText(this, "add history", Toast.LENGTH_SHORT).show();
        db = dbHelper.getWritableDatabase();

        String[] columns={DatabaseContract.CASEHISTORY._ID,DatabaseContract.CASEHISTORY.Col_CaseNumber,
                DatabaseContract.CASEHISTORY.COL_Previousdate ,
                DatabaseContract.CASEHISTORY.COL_Adjourndate , DatabaseContract.CASEHISTORY.COL_Step
        };

        // String sortOrder = DatabaseContract.ADDCASE._ID + " ASC";
        cursor = db.query(DatabaseContract.CASEHISTORY.TABLE_NAME, columns,  DatabaseContract.CASEHISTORY.Col_CaseNumber + "=?" , new String[]{casenumber}, null, null, null, null);
        Toast.makeText(this, "cursor value : " + cursor.getCount(), Toast.LENGTH_SHORT).show();
        if(cursor.getCount()==0) {
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.CASEHISTORY.Col_CaseNumber,casenumber);
            values.put(DatabaseContract.CASEHISTORY.COL_Adjourndate,adjourndate );
            long newRowId = db.insert(DatabaseContract.CASEHISTORY.TABLE_NAME, null, values);
            Toast.makeText(this, "New Record Inserted: " + newRowId, Toast.LENGTH_SHORT).show();
            db.close();
            //Toast.makeText(this, "Empty database ", Toast.LENGTH_SHORT).show();


        }
        else
            db.close();

        // finish();

    }

}