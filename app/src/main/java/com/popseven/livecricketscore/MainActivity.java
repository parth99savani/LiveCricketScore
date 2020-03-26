package com.popseven.livecricketscore;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.ScreenSlidePagerAdapter;
import com.popseven.livecricketscore.Fragment.HighlightsFragment;
import com.popseven.livecricketscore.Fragment.HomeFragment;
import com.popseven.livecricketscore.Fragment.MatchFragment;
import com.popseven.livecricketscore.Fragment.MoreFragment;
import com.popseven.livecricketscore.Model.LiveMatches.Livematches;
import com.popseven.livecricketscore.Model.LiveMatches.Match;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BubbleNavigationLinearView bottomNavigationViewLinear;
    private Handler handler = new Handler();
    private int apiDelayed = 10000; //10 seconds
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        bottomNavigationViewLinear = findViewById(R.id.bottom_navigation_view_linear);

        ArrayList<Fragment> fragList = new ArrayList<>();
        fragList.add(new HomeFragment());
        fragList.add(new MatchFragment());
        fragList.add(new HighlightsFragment());
        fragList.add(new MoreFragment());
        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(fragList, getSupportFragmentManager());

        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                bottomNavigationViewLinear.setCurrentActiveItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        bottomNavigationViewLinear.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                viewPager.setCurrentItem(position, true);
            }
        });

        checkConnection();
    }

    @Override
    public void onResume() {
        super.onResume();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do your function;
                //progressBar.setVisibility(View.VISIBLE);
                checkConnection();
                handler.postDelayed(runnable, apiDelayed);
            }
        }, apiDelayed);
    }

    private void checkConnection() {
        new CheckNetworkConnection(this, new CheckNetworkConnection.OnConnectionCallback() {
            @Override
            public void onConnectionSuccess() {

            }

            @Override
            public void onConnectionFail(String msg) {
                Snackbar snackbar = Snackbar.make(MainActivity.this.findViewById(android.R.id.content), "No, Internet Connection!", Snackbar.LENGTH_SHORT);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(MainActivity.this.getResources().getColor(R.color.colorAccent));
                TextView textView = snackbarView.findViewById(R.id.snackbar_text);
                textView.setTextColor(MainActivity.this.getResources().getColor(R.color.colorPrimary));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                snackbar.show();
            }
        }).execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible
    }
}
