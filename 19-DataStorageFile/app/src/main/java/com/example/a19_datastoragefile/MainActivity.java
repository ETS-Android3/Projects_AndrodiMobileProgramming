package com.example.a19_datastoragefile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    private EditText editFileName, editFile;
    private TextView path;
    private FileInputStream fis;
    private FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFileName = findViewById(R.id.edit_file_name);
        editFile = findViewById(R.id.edit_file);
        path = findViewById(R.id.tv_path);

        path.setText("Path: " + getFilesDir().toString());
    }

    public void onClickSave(View v) {
        String file_name = editFileName.getText().toString();
        try  {
            fos = openFileOutput(file_name, MODE_PRIVATE);
            fos.write(editFile.getText().toString().getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Il file " + file_name + " non esiste",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickLoad(View v) {
        String file_name = editFileName.getText().toString();
        try  {
            fis = openFileInput(file_name);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            String data = "";
            while ((line = br.readLine()) != null) {
                data += line + "\n";
            }
            br.close();
            fis.close();
            editFile.setText(data);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Il file " + file_name + " non esiste",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}