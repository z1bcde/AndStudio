package com.take.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.content.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {
    static final int RESULT_SUBACTIVITY = 1000;
    public static final String EXTRA_MESSAGE
//            = "com.example.testactivitytrasdata.MESSAGE";
            = "YourPackageName.MESSAGE";
    public EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // 参照するリソースは上でリソースファイルに付けた名前と同じもの
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem1:
                Save();
                return true;

            case R.id.menuItem2:
                Open();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Open() {
        Toast.makeText(this, "開く", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, Open.class);
        startActivityForResult(i, RESULT_SUBACTIVITY);
    }

    public void Save() {

        Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK && requestCode == RESULT_SUBACTIVITY &&
                null != intent) {
            String res = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            edit = (EditText) findViewById(R.id.edit);
            File f;
            f = new File("/sdcard/homepage/" + res);
            String fname = f.getAbsolutePath();
            Toast.makeText(this, fname, Toast.LENGTH_SHORT).show();

            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String data;
                while ((data = br.readLine()) != null) {
                    edit.setText(data);
                }
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
