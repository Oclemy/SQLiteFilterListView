package com.tutorials.hp.sqlitefilterlistview.mListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorials.hp.sqlitefilterlistview.R;
import com.tutorials.hp.sqlitefilterlistview.mDataObject.Planet;

import java.util.ArrayList;

/**
 * Created by Oclemmy on 5/2/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Planet> planets;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Planet> planets) {
        this.c = c;
        this.planets = planets;
    }

    @Override
    public int getCount() {
        return planets.size();
    }

    @Override
    public Object getItem(int position) {
        return planets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);
        nameTxt.setText(planets.get(position).getName());

        final int pos=position;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,planets.get(pos).getName(),Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
