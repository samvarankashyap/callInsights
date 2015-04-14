package com.samvarankashyap.callinsightsapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class AndroidSQLiteTutorialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_sqlite_tutorial);
        DbManager db = new DbManager(this);
        Log.d("Insert: ", "Inserting ..");
        db.addCallRecord(new CallRecord(2,"sa","s","dw","s","a"));


        List<CallRecord> crs = db.getAllCallRecords();

        for (CallRecord cr : crs) {
            String log = "Id: "+cr.getId()+" ,caller_id: " + cr.getCallerId() + " ,call_time: " + cr.getCallTime();
            // Writing Contacts to log
            Log.d("logName: ", log);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_android_sqlite_tutorial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
