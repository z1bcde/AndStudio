package com.take.myapplication;

import android.content.Intent;
import android.os.*;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Open extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ファイルを開く");
        setContentView(R.layout.open);
        listView = (ListView)findViewById(R.id.lview);
        File f = new File("/storage/emulated/0/homepage");
        if(!f.exists())
        {
            f.mkdir();
        }
        String data[] = f.list();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectItem(i);
            }
        });
    }
    public void selectItem(int position)
    {
        String selectitem = (String) listView.getItemAtPosition(position);
        TextView pathView = (TextView)findViewById(R.id.pathView);
        pathView.append(selectitem);
        Toast.makeText(this, selectitem, Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        intent.putExtra(MainActivity.EXTRA_MESSAGE, selectitem);
        setResult(RESULT_OK, intent);
        finish();
    }
}
