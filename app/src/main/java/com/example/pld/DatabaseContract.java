package com.example.pld;

import android.provider.BaseColumns;

public class DatabaseContract {
    public DatabaseContract() {}

    /* Inner class that defines the table contents */
   public static abstract class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COL_FULLNAME = "fullname";
        public static final String COL_EMAIL = "email";
        public static final String COL_Password= "password";
    }
    public static abstract class ADDCASE implements BaseColumns {
        public static final String TABLE_NAME = "addcase";
        public static final String COL_EMAIL = "email";
        public static final String COL_CaseTitle = "CaseTitle";
        public static final String Col_CourtName="CaseName";
        public static final String Col_CaseType = "CaseType";
        public static final String Col_CaseNumber = "CaseNumber";
        public static final String Col_OnBehalfOf = "OnBehalfOf";
        public static final String Col_PartyName = "PartyName";
        public static final String Col_ContactNumber = "ContactNumber";
        public static final String Col_RespondantName= "RespondantName";
        public static final String Col_PartyAdvocateName = "PartyAdvocateName";
        public static final String Col_AdvocateContactNumber = "AdvocateContactNumber";
        public static final String Col_FeldUnderSection="FeldUnderSection";
        public static final String Col_AdjournDate="AdjournDate";

    }
    public static abstract class CASEHISTORY implements BaseColumns {
        public static final String TABLE_NAME = "casehistoy";
        public static final String Col_CaseNumber = "CaseNumber";
        public static final String COL_Previousdate = "PreviousDate";
        public static final String COL_Adjourndate = "AdjournDate";
        public static final String COL_Step= "Step";
    }
}