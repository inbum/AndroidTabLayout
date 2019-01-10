package com.inbum.tablayoutexample;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SampleFragmentPagerAdapter sampleFragmentPagerAdapter;
    private ArrayList<TabModel> tabModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        this.tabModels = new ArrayList<TabModel>();
        sampleFragmentPagerAdapter = new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this, tabModels);
        viewPager.setAdapter(sampleFragmentPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        SampleAsyncTask task = new SampleAsyncTask(new AsyncResponse<List<TabModel>>() {
            @Override
            public void onResponse(List<TabModel> response) {
                tabModels.clear();
                tabModels.addAll(response);
                sampleFragmentPagerAdapter.notifyDataSetChanged();
            }
        });

        task.execute("null string");
    }

    public class SampleAsyncTask extends AsyncTask<String, Void, List<TabModel>> {

        private AsyncResponse<List<TabModel>> callback;

        public SampleAsyncTask(AsyncResponse<List<TabModel>> callback) {
            this.callback = callback;
        }

        @Override
        protected List<TabModel> doInBackground(String... strings) {
            String url = strings[0];
            final List<TabModel> tabModels = new ArrayList<TabModel>();
            tabModels.add(new TabModel("title1"));
            tabModels.add(new TabModel("title2"));
            tabModels.add(new TabModel("title3"));
            tabModels.add(new TabModel("title4"));
            return tabModels;
        }

        @Override
        protected void onPostExecute(List<TabModel> tabModels) {
            if( this.callback != null ){
                this.callback.onResponse(tabModels);
            } else {
                Log.w("ConnectionAsyncTask", "Ignoring result");
            }
        }

    }
}
