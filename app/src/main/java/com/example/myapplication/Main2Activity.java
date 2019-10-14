package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;




import java.io.OutputStreamWriter;


public class Main2Activity extends AppCompatActivity {
    EditText textTitle;
    EditText editNote1;
    int num = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textTitle = (EditText) findViewById(R.id.edit_text);
        editNote1 = findViewById(R.id.edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final int mode = getIntent().getIntExtra("mode", 0);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save("Note" + num + ".txt", "Note");
                Save("Title" + num + ".txt", "Title");
                num++;
                submitText(mode);
            }
        });
        String tempHolder = getIntent().getStringExtra("Listviewclickvalue");
        textTitle.setText(tempHolder);
        textTitle.setText(Open("Title" + num + ".txt"));
        editNote1.setText(Open("Note" + num + ".txt"));


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



    public String Open(String fileName) {
        String content = "";
        if (FileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if ( in != null) {
                    InputStreamReader tmp = new InputStreamReader( in );
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    } in .close();
                    content = buf.toString();
                }
            } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }

    public boolean FileExists(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }


    private void submitText(final int mode) {
        if (mode == 2) {
            EditText editText = findViewById(R.id.edit_text);
            EditText editNote = findViewById(R.id.edit_note);
            // Prepare data intent
            Intent data = new Intent();
            // Pass relevant data back as a result
            data.putExtra("text", editText.getText().toString());
            data.putExtra("notes", editNote.getText().toString());
            // Activity finished ok, return the data
            setResult(RESULT_OK, data); // set result code and bundle data for response

            finish(); // closes the activity, pass data to parent
        }
    }

    public void Save(String fileName, String type) {
        try{
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(fileName, 0));
            if(type.equals("Title")) {
                out.write(textTitle.getText().toString());
            }
            else if(type.equals("Note")) {
                out.write(editNote1.getText().toString());
            }
            out.close();
            Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }




}

