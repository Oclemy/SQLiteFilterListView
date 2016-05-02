package com.tutorials.hp.sqlitefilterlistview;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.tutorials.hp.sqlitefilterlistview.mDataBase.DBAdapter;
import com.tutorials.hp.sqlitefilterlistview.mDataObject.Planet;
import com.tutorials.hp.sqlitefilterlistview.mListView.CustomAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    SearchView sv;
    EditText nameEditText;
    Button saveBtn,retrieveBtn;
    CustomAdapter adapter;
    ArrayList<Planet> planets=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        lv= (ListView) findViewById(R.id.lv);
        sv= (SearchView) findViewById(R.id.sv);

        adapter=new CustomAdapter(this,planets);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   displayDialog();
            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getPlanets(newText);
                return false;
            }
        });



    }

    private void displayDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("SQLite Database");
        d.setContentView(R.layout.dialog_layout);

        nameEditText= (EditText) d.findViewById(R.id.nameEditTxt);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);
        retrieveBtn= (Button) d.findViewById(R.id.retrieveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(nameEditText.getText().toString());
            }
        });
        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 getPlanets(null);
            }
        });

        d.show();
    }

    private void save(String name)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        if(db.add(name))
        {
            nameEditText.setText("");
        }else {
            Toast.makeText(this,"Unable To Save",Toast.LENGTH_SHORT).show();
        }

        db.closeDB();

        this.getPlanets(null);
    }

    private void getPlanets(String searchTerm)
    {
        planets.clear();

        DBAdapter db=new DBAdapter(this);
        db.openDB();
        Planet p=null;
        Cursor c=db.retrieve(searchTerm);
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);

            p=new Planet();
            p.setId(id);
            p.setName(name);

            planets.add(p);
        }

        db.closeDB();

        lv.setAdapter(adapter);

    }



}










