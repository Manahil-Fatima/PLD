package com.example.pld;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pakistan_Lawyer_Diary.db";

    private static final String CREATE_TBL_USERS = "CREATE TABLE "
            + DatabaseContract.Users.TABLE_NAME + " ("
            + DatabaseContract.Users._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.Users.COL_FULLNAME + " TEXT NOT NULL, "
            + DatabaseContract.Users.COL_EMAIL + " TEXT NOT NULL,"
            + DatabaseContract.Users.COL_Password+ " TEXT  NOT NULL)";



    private static final String CREATE_TBL_ADDCASE = "CREATE TABLE "
            + DatabaseContract.ADDCASE.TABLE_NAME + " ("
            + DatabaseContract.ADDCASE._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.ADDCASE.COL_EMAIL + " TEXT NOT NULL,"
            + DatabaseContract.ADDCASE.COL_CaseTitle + " TEXT NOT NULL, "
            + DatabaseContract.ADDCASE.Col_CourtName + " TEXT NOT NULL,"
          + DatabaseContract.ADDCASE.Col_CaseType + " TEXT NOT NULL,"
          + DatabaseContract.ADDCASE.Col_CaseNumber + " TEXT NOT NULL,"
          + DatabaseContract.ADDCASE.Col_OnBehalfOf + " TEXT NOT NULL,"
          + DatabaseContract.ADDCASE.Col_PartyName+ " TEXT NOT NULL,"
          + DatabaseContract.ADDCASE.Col_ContactNumber + " TEXT NOT NULL,"
          + DatabaseContract.ADDCASE.Col_RespondantName + " TEXT NOT NULL,"
            + DatabaseContract.ADDCASE.Col_PartyAdvocateName+ " TEXT NOT NULL,"
          + DatabaseContract.ADDCASE.Col_AdvocateContactNumber+ " TEXT NOT NULL ,"
            + DatabaseContract.ADDCASE.Col_FeldUnderSection+ " TEXT NOT NULL ,"
          + DatabaseContract.ADDCASE.Col_AdjournDate+ " TEXT NOT NULL )";

    private static final String CREATE_TBL_HISTORY = "CREATE TABLE "
            + DatabaseContract.CASEHISTORY.TABLE_NAME + " ("
            + DatabaseContract.CASEHISTORY._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DatabaseContract.CASEHISTORY.Col_CaseNumber+ " TEXT NOT NULL, "
            + DatabaseContract.CASEHISTORY.COL_Previousdate + " TEXT , "
            + DatabaseContract.CASEHISTORY.COL_Adjourndate + " TEXT NOT NULL,"
            + DatabaseContract.CASEHISTORY.COL_Step+ " TEXT )";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TBL_USERS);
       db.execSQL(CREATE_TBL_ADDCASE);
        db.execSQL(CREATE_TBL_HISTORY);

        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
      /*  db.delete(DatabaseContract.Users.TABLE_NAME, null, null);
        db.delete(DatabaseContract.ADDCASE.TABLE_NAME, null, null);
        db.delete(DatabaseContract.CASEHISTORY.TABLE_NAME, null, null);
        onCreate(db);*/
      //  db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Users.TABLE_NAME );
       // db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.ADDCASE.TABLE_NAME );
        //db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.CASEHISTORY.TABLE_NAME );

        // Create tables again
      //  onCreate(db);
    }



}
