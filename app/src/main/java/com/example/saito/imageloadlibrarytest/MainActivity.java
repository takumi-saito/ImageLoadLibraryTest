package com.example.saito.imageloadlibrarytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ListView glideListView;
    private ListView picassoListView;

    private static int LOOP_CNT = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://www.digital-dictionary.net/wildbird/listimg/wb_1_";

        // 測定開始
        long picassoStart = System.currentTimeMillis();

        picassoListView = (ListView) findViewById(R.id.picasso_listView);
        // Profile型のリストを作る
        List<ListViewModel> picassoListViewModels = new ArrayList<ListViewModel>();

        for (int i = 1; i < LOOP_CNT + 1; i++) {
            // サンプルデータを作る
            String cnt = String.format("%1$03d", i);
            String picUrl = url + cnt + ".jpg";
            Log.v("saito", "url:" + url);
            picassoListViewModels.add(new ListViewModel("picasso", i + "回目", picUrl, ListViewModel.LIBRARY_FLG.GLIDE));
        }

        // Adapterを宣言して、サンプルデータを入れる
        ListViewAdapter picassoAdapter = new ListViewAdapter(this, 0, picassoListViewModels);
        picassoListView.setAdapter(picassoAdapter);

        // 測定終了
        long picassoEnd = System.currentTimeMillis();
        Log.v(getClass().getName(), "picassoTime: " + (picassoEnd - picassoStart));
        TextView picassoTv = (TextView)findViewById(R.id.picasso_textView);
        picassoTv.setText("picassoTime: " + (picassoEnd - picassoStart));

        // 測定開始
        long glideStart = System.currentTimeMillis();
        glideListView = (ListView) findViewById(R.id.glide_listView);

        // Profile型のリストを作る
        List<ListViewModel> glideListViewModels = new ArrayList<ListViewModel>();

        for (int i = 1; i < LOOP_CNT + 1; i++) {
            // サンプルデータを作る
            String cnt = String.format("%1$03d", (LOOP_CNT + i));
            String picUrl = url + cnt + ".jpg";
            Log.v("saito", "url:" + url);
            glideListViewModels.add(new ListViewModel("glide", i + "回目", picUrl, ListViewModel.LIBRARY_FLG.GLIDE));
        }

        // Adapterを宣言して、サンプルデータを入れる
        ListViewAdapter glideAdapter = new ListViewAdapter(this, 0, glideListViewModels);

        // ListViewにAdapterをセットする
        glideListView.setAdapter(glideAdapter);
        // 測定終了
        long glideEnd = System.currentTimeMillis();
        Log.v(getClass().getName(), "glideTime: " + (glideEnd - glideStart));
        TextView glideTv = (TextView)findViewById(R.id.glide_textView);
        glideTv.setText("glideTime: " + (glideEnd - glideStart));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
