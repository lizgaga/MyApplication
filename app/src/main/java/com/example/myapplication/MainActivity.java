package com.example.myapplication;

import android.os.Bundle;
import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.io.OutputStreamWriter;
import android.widget.EditText;
import java.util.ArrayList;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int REQUEST_CODE = 20;
    ListView simpleList;
    EditText EditText1;

    ArrayList<String> notes =  new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> titleAdapter;
    ArrayAdapter<String> noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        simpleList = (ListView) findViewById(R.id.list_view);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.content_main, R.id.textView, notes);
        simpleList.setAdapter(arrayAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text1 = simpleList.getItemAtPosition(i).toString().trim();
                Intent n = new Intent(getApplicationContext(), Main2Activity.class);
                n.putExtra("position", i);
                n.putExtra("textTitle", text1);
                startActivity(n);
            }
        });

//        next_note = (ArrayAdapter)findViewById(R.id.textView);

/*            @Override
            public void onClick(View view) {
                Intent intz = new Intent(MainActivity.this, Main2Activity.class);
                intz.putExtra("mode", 2); // pass arbitrary data to launched activity
                startActivityForResult(intz, REQUEST_CODE);

            }
                                        });*/


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
     /*   fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("mode", 2); // pass arbitrary data to launched activity
                startActivityForResult(i, REQUEST_CODE);*/

           // }
      //  });

//        arrayAdapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                startActivity(intent);
//            }
//        });


/*        next_note.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });*/

    }


    public void onClick(View view) {
        Intent intz = new Intent(MainActivity.this, Main2Activity.class);
        intz.putExtra("mode", 2); // pass arbitrary data to launched activity
        startActivityForResult(intz, REQUEST_CODE);

    }

    // ActivityOne.java, time to handle the result of the sub-activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            // String name = data.getExtras().getString("Saved");
            //     String name = "Saved.";
            //  String name = data.getExtras().getString("text");
            // Toast the name to display temporarily on screen
            //     Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

            String message = data.getExtras().getString("text");
            String message2 = data.getExtras().getString("notes");
            arrayAdapter.addAll(message);
            File directory;
            directory = getFilesDir();
            File[] files = directory.listFiles();
            ArrayList<String> titleList =  new ArrayList<>();
            String notesMessage, titleMessage;
            Log.d("System.out", "I am here");
            for (int f = 0; f < files.length; f++) {
                titleMessage = "Title" + "f" + ".txt";
                notesMessage = "Note" + f + ".txt";
            //    titleList.add(titleMessage);
            //    Log.d("System.out", titleList.get(f));
            //    titleAdapter.add(titleMessage);
             //   noteAdapter.add(Open(notesMessage));
           //     System.out.println("This is what message we:" + files[f]);
            }
          //  titleAdapter.add("hi");
           // int index;
           // for (index = 0; index < titleList.size(); index++);
           //     titleAdapter.add(titleList.get(index));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


