package com.example.pld;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class customlistview extends BaseAdapter {
    private Activity context;
    ArrayList<CaseDetail> casedetail;


    public customlistview(Activity context, ArrayList casedetail) {
        // super(context, R.layout.row_item, countries);
        this.context = context;
        this.casedetail=casedetail;
    }

    public static class ViewHolder
    {
        TextView t1;
        TextView t2;
        TextView t3;
        TextView t4;
        TextView t5;
        TextView t6;
        TextView t7;
        TextView t8;
        TextView t9;
     //   TextView t10;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;

        LayoutInflater inflater = context.getLayoutInflater();

        ViewHolder vh;
        if(convertView==null) {
            vh=new ViewHolder();
            row = inflater.inflate(R.layout.activity_listview_layout, null, true);

            vh.t1 = (TextView) row.findViewById(R.id.ct);
            vh.t2 = (TextView) row.findViewById(R.id.cn);
            vh.t3 = (TextView) row.findViewById(R.id.ctype);
            vh.t4 = (TextView) row.findViewById(R.id.cnum);
            vh.t5 = (TextView) row.findViewById(R.id.obo);
            vh.t6 = (TextView) row.findViewById(R.id.pn);
            vh.t7 = (TextView) row.findViewById(R.id.contact);
            vh.t8 = (TextView) row.findViewById(R.id.rn);
            vh.t9 = (TextView) row.findViewById(R.id.advocatename);
        //    vh.t10=(TextView) row.findViewById(R.id.ad);


            // store the holder with the view.
            row.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.t1.setText(casedetail.get(position).getcasetitle());
        vh.t2.setText(""+casedetail.get(position).getcourtname());
        vh.t3.setText(""+casedetail.get(position).getcasetype());
        vh.t4.setText(""+casedetail.get(position).getcasenumber());
        vh.t5.setText(""+casedetail.get(position).getonbehalfof());
        vh.t6.setText(""+casedetail.get(position).getpartyname());
        vh.t7.setText(""+casedetail.get(position).getcontactnumber());
        vh.t8.setText(""+casedetail.get(position).getrespondantname());
        vh.t9.setText(""+casedetail.get(position).getadversepartyadvocatename());
      //  vh.t10.setText(""+casedetail.get(position).getadjourndate());


        return  row;
    }


    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {

        if(casedetail.size()<=0)
            return 1;
        return casedetail.size();
    }
}