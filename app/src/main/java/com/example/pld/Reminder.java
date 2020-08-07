package com.example.pld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import java.net.URI;

import android.view.View;



import android.view.View;
import android.widget.Toast;

public class Reminder extends AppCompatActivity {

    String casenumber,adjourndate,contact,partyname;
    String contactnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        setTitle("Reminder");

        Intent i1 = getIntent();
        casenumber= i1.getStringExtra("casenumber");
        partyname=i1.getStringExtra("partyname");
        contact=i1.getStringExtra("contact");
        adjourndate = i1.getStringExtra("adjourndate");

        contactnum="tel:"+contact;

        Toast.makeText(this, " " + contact, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, " " + partyname, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, " " +  adjourndate, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, " " + contactnum, Toast.LENGTH_SHORT).show();

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.6),(int)(height*.4));


    }
    public void call(View view)
    {

        String url= contactnum;
        Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse(url));
        startActivity(i);
        finish();

    }
    public void sms(View view)
    {
        String url= "smsto:"+contact;
        Uri sms_uri = Uri.parse(url);
        Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
        String sms_body="Reminder:\nMr/Mrs "+partyname+" YOUR CASE ADJOURN DATE IS SCHEDULED ON "+adjourndate+" PLEASE MAKE SURE TO ATTEND AT COURT. INFORM TO ME YOUR AVAILABILITY STATUS SOON.\nKindly Regards,";
        sms_intent.putExtra("sms_body", sms_body);
        startActivity(sms_intent);
        finish();
    }
}