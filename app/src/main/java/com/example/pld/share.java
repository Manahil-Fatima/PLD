package com.example.pld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class share extends AppCompatActivity {
    String casenumber,adjourndate,contact,partyname;
    String contactnum, casetile, courtname, casetype, onbehalfof, respondantname, partyadvocate,advocatecontactnum,undersection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Intent i1 = getIntent();
        casenumber= i1.getStringExtra("casenumber");
        partyname=i1.getStringExtra("partyname");
        contact=i1.getStringExtra("contact");
        adjourndate = i1.getStringExtra("adjourndate");
        casetile = i1.getStringExtra("casetile");
        courtname = i1.getStringExtra("courtname");
        casetype = i1.getStringExtra("casetype");
        onbehalfof = i1.getStringExtra("onbehalfof");
        contactnum = i1.getStringExtra("contactnum");
        respondantname = i1.getStringExtra("respondantname");
        partyadvocate = i1.getStringExtra("partyadvocate");
        advocatecontactnum = i1.getStringExtra("advocatecontactnum");
        undersection = i1.getStringExtra("undersection");


        contactnum="tel:"+contact;
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.6),(int)(height*.4));
        wapp();
    }
    public void wapp( )
    {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        String sms_body="CaseNo: "+casenumber+"\nPartyName:"+partyname+
                "\nContact Number: "+contact+
                "\nAdjourn Date: "+
                adjourndate+
                "\ncaseTitle: "+casetile+
                "\nCourt Name: "+courtname+
                "\nCase Type: "+casetype+
                "\nOn Behalf Of: "+onbehalfof+
                "\nRespondent Nmae: "+respondantname+
                "\nAdverse Party Advocate Name: "+partyadvocate+
                "\nAdverse Party Advocate Number: "+advocatecontactnum+
                "\nField Under Section: "+undersection;
        share.putExtra(Intent.EXTRA_SUBJECT, "CASE");
        share.putExtra(Intent.EXTRA_TEXT,sms_body);
        // share.putExtra("sms_body", sms_body);
        startActivity(Intent.createChooser(share, "Share link!"));
        finish();
    }
}
