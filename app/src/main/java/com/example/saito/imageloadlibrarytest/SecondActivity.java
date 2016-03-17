package com.example.saito.imageloadlibrarytest;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();
    private ListView glideListView;
    private ListView picassoListView;
    private ListView frescoListView;

    private int LOOP_CNT = 30;
    private static String url = "http://www.digital-dictionary.net/wildbird/listimg/wb_1_";
    private static String url2 = "http://www.digital-dictionary.net/wildbird/listimg/wb_2_";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        LOOP_CNT = intent.getIntExtra(Constants.CREATE_COUNT, 0);
        if (intent.getBooleanExtra(Constants.PICASSO, false)) {
            /** picasso **/
            picassoListCreate();
        }
        if (intent.getBooleanExtra(Constants.GLIDE, false)) {
            /** glide **/
            glideListCreate();
        }
        if (intent.getBooleanExtra(Constants.FRESCO, false)) {
            /** fresco **/
            frescoListCreate();
        }

        Button backButton = (Button) findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void picassoListCreate() {
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
            picassoListViewModels.add(new ListViewModel("picasso", i + "回目", picUrl, ListViewModel.LIBRARY_FLG.PICASSO));
        }

        // Adapterを宣言して、サンプルデータを入れる
        ListViewAdapter picassoAdapter = new ListViewAdapter(this, 0, picassoListViewModels);
        picassoListView.setAdapter(picassoAdapter);

        // 測定終了
        long picassoEnd = System.currentTimeMillis();
        Log.v(getClass().getName(), "picassoTime: " + (picassoEnd - picassoStart));
        TextView picassoTv = (TextView)findViewById(R.id.picasso_textView);
        picassoTv.setText("picassoTime: " + (picassoEnd - picassoStart));
    }
    private void glideListCreate() {
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
    private void frescoListCreate() {
        // 測定開始
        long frescoStart = System.currentTimeMillis();

        frescoListView = (ListView) findViewById(R.id.fresco_listView);
        // Profile型のリストを作る

        List<ListViewModel> frescoListViewModels = new ArrayList<ListViewModel>();
        for (int i = 1; i < LOOP_CNT + 1; i++) {
            // サンプルデータを作る
            String cnt = String.format("%1$03d", i);
            String picUrl = url + cnt + ".jpg";
            Log.v("saito", "url:" + url);
            frescoListViewModels.add(new ListViewModel("fresco", i + "回目", picUrl, ListViewModel.LIBRARY_FLG.FRESCO));
        }

        // Adapterを宣言して、サンプルデータを入れる
        ListViewAdapter frescoAdapter = new ListViewAdapter(this, 0, frescoListViewModels, true);
        frescoListView.setAdapter(frescoAdapter);

        // 測定終了
        long frescoEnd = System.currentTimeMillis();
        Log.v(getClass().getName(), "frescoTime: " + (frescoEnd - frescoStart));
        TextView frescoTv = (TextView)findViewById(R.id.fresco_textView);
        frescoTv.setText("frescoTime: " + (frescoEnd - frescoStart));
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
