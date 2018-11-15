package com.example.cisco.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText fname,lname,score;
    DBHelper helper = new DBHelper(this);
    Cursor table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = helper.selectRecord();
        fname = findViewById(R.id.etFname);
        lname = findViewById(R.id.etLname);
        score = findViewById(R.id.etScore);
    }

    public void addRecord(View v){
        String f = fname.getText().toString();
        String l = lname.getText().toString();
        int s = Integer.parseInt(score.getText().toString());
        boolean inserted = helper.insert(f,l,s);
        if (inserted == true){
            Toast.makeText(this, "Record inserted", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, "Record not inserted", Toast.LENGTH_LONG).show();
    }

    public void first (View v){
        table.moveToFirst();
        data();
    }

    public void previous (View v){
        table.moveToPrevious();
        if (table.isBeforeFirst()){
            Toast.makeText(this, "Record is at first position", Toast.LENGTH_LONG).show();
            //table.moveToFirst();
        } else data();
    }

    public void next (View v){
        table.moveToNext();
        if (table.isAfterLast()) {
            Toast.makeText(this, "Record is at last position", Toast.LENGTH_LONG).show();
            //table.moveToLast();
        } else data();
    }
    public void last (View v){
        table.moveToLast();
        data();
    }

    public void data(){
        fname.setText(table.getString(1));
        lname.setText(table.getString(2));
        score.setText(table.getString(3));
    }
}
