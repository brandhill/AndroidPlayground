package com.mitsw.androidplayground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mitsw.androidplayground.animation.AnimationActivity;
import com.mitsw.androidplayground.memoryleak.MemoryLeakActivity;
import com.mitsw.androidplayground.rx.RxJavaDemoActivity;
import com.mitsw.androidplayground.services.MitswService;
import com.mitsw.androidplayground.thread.ThreadDemoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private SimpleAdapter simpleAdapter;

    private String[] textArray = {
            "Thread Pool", "Memory Leak Detection", "Animation", "Rx"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ParseAnalytics.trackAppOpenedInBackground(getIntent());

        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0, length = textArray.length; i < length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            //item.put("image", image[i]);
            item.put("text", textArray[i]);
            items.add(item);
        }

        simpleAdapter = new SimpleAdapter(this, items, R.layout.simple_adapter_item, new String[]{ "text"}, new int[]{ R.id.text});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;

                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, ThreadDemoActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(MainActivity.this, MemoryLeakActivity.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent = new Intent(MainActivity.this, AnimationActivity.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(MainActivity.this, RxJavaDemoActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MitswService.class);
                startService(intent);
            }
        },1000);

    }
}
