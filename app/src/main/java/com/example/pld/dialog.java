package com.example.pld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.app.PendingIntent.getActivity;

public class dialog extends AppCompatActivity  {
    TextView adjourn;
    EditText step;
    String adjournvalue;
    String stepvalue;
  private  String casenumber, adjourndate;
    String stp,ajd;
    private Calendar calendar;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Cursor cursor;
    private int year , month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        dbHelper = new DatabaseHelper(this);



        Intent i1 = getIntent();
        casenumber= i1.getStringExtra("casenumber");
       adjourndate = i1.getStringExtra("adjourndate");

        step=(EditText)findViewById(R.id.step);
        adjourn=(TextView) findViewById(R.id.adjourn);
        adjournvalue=adjourn.getText().toString();
        stepvalue=step.getText().toString();
        Toast.makeText(this, "adjournvalue " +adjournvalue, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, " stepvalue "+ stepvalue, Toast.LENGTH_SHORT).show();

        calendar= Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        //   Intent i=new Intent(getApplicationContext(),showDialog.class);
        //  i.putExtra("adjourn",adjournvalue);
        //  i.putExtra("step",stepvalue);
        //   startActivity(i);


        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));


    }
    public void addhistory()
    {
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
        Toast.makeText(this, "showcase detail: addhistory: ", Toast.LENGTH_SHORT).show();
        // finish();

    }

    public void date(View view)
    {
        adjourn=(TextView) view;
        showDialog(999);
        //  Toast.makeText(getApplicationContext(),"calendar",Toast.LENGTH_SHORT).show();
    }
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    adjourn.setText(new StringBuilder().append(arg3).append("/").append(arg2+1).append("/").append(arg1));
                    // showDate(arg1, arg2+1, arg3);
                }
            };

    public void savedata(View view)
    {
        ajd=adjourn.getText().toString();
        stp=step.getText().toString();
        db = dbHelper.getWritableDatabase();

        String[] columns={DatabaseContract.CASEHISTORY._ID,DatabaseContract.CASEHISTORY.Col_CaseNumber,
                DatabaseContract.CASEHISTORY.COL_Previousdate , DatabaseContract.CASEHISTORY.COL_Adjourndate ,
                DatabaseContract.CASEHISTORY.COL_Step
        };

        // String sortOrder = DatabaseContract.ADDCASE._ID + " ASC";
        cursor = db.query(DatabaseContract.CASEHISTORY.TABLE_NAME, columns,  DatabaseContract.CASEHISTORY.Col_CaseNumber + "=?" , new String[]{casenumber},
                null, null, null, null);

        if(cursor.getCount()<=0) {

            Toast.makeText(getApplicationContext(), "empty " , Toast.LENGTH_SHORT).show();

        }
        else
        {  Toast.makeText(getApplicationContext(), "else " , Toast.LENGTH_SHORT).show();
           ContentValues values = new ContentValues();
            values.put(DatabaseContract.CASEHISTORY.Col_CaseNumber,casenumber);
             values.put(DatabaseContract.CASEHISTORY.COL_Previousdate,adjourndate);
             values.put(DatabaseContract.CASEHISTORY.COL_Adjourndate, ajd);
              values.put(DatabaseContract.CASEHISTORY.COL_Step,stp);
          long newRowId = db.insert(DatabaseContract.CASEHISTORY.TABLE_NAME, null, values);
            Toast.makeText(this, "New Record Inserted: " + newRowId, Toast.LENGTH_SHORT).show();
            db.close();
            Intent intent =new Intent();
            intent.putExtra("Adjourndate",ajd);
            setResult(RESULT_OK,intent);
            finish();
            //Intent intent = new Intent(getApplicationContext(),ShowCaseDetail.class);
           //  startActivity(intent);
            //finish();
        }
    }


}
