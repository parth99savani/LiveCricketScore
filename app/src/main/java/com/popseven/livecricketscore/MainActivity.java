package com.popseven.livecricketscore;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
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

    private RecyclerView recycler;
    private static final String URL_DATA = "http://mapps.cricbuzz.com/cbzios/match/livematches";
    //private RecyclerView.Adapter adapter;
    private List<Match> itemList;
    private ViewPager viewPager;
    private BubbleNavigationLinearView bottomNavigationViewLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //recycler = findViewById(R.id.recycler);

//        recycler.setHasFixedSize(true);
//        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        itemList = new ArrayList<>();

        //loadData();

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
    }

    private void loadData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                //JSONObject jsonObject = new JSONObject(response);

                //JSONArray array = jsonObject.getJSONArray("items");

                //JSONArray array = new JSONArray(response);

                    /*for (int i = 0; i < array.length(); i++){

                        JSONObject jo = array.getJSONObject(i);

                        Match item = new Match(jo.getString("booking_name"), jo.getString("from_date"),
                                jo.getString("to_date"),jo.getString("event_name"));
                        itemList.add(item);

                    }*/

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                // pass response
                Livematches users = gson.fromJson(response, Livematches.class);

//                adapter = new ItemAdapter(users.getMatches(), MainActivity.this);
//                recycler.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
