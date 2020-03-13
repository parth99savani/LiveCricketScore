package com.popseven.livecricketscore.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.asksira.loopingviewpager.LoopingViewPager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.HighlightAdapter;
import com.popseven.livecricketscore.Adapter.HighlightBannerAdapter;
import com.popseven.livecricketscore.HighlightPlayActivity;
import com.popseven.livecricketscore.Model.Highlights.Content;
import com.popseven.livecricketscore.Model.Highlights.Highlights;
import com.popseven.livecricketscore.R;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HighlightsFragment extends Fragment {

    private RecyclerView recyclerViewHighlights;
    private HighlightAdapter adapter, adapter2;
    private List<Content> highlightList;
    private List<Content> highlightIplList;
    private List<Content> highlightBannerList;
    private static final String URL_IPL = "http://api.platform.iplt20.com/content/ipl/VIDEO/en?page=0&pageSize=20&tagNames=indian-premier-league&tagNames=highlights&detail=DETAILED";
    private static final String URL_DATA = "https://api.platform.bcci.tv/content/bcci/video/EN/?tagNames=highlights&pageSize=20&page=0";
    private ProgressBar progressBar;
    private LoopingViewPager viewpager;
    private HighlightBannerAdapter highlightBannerAdapter;
    private RecyclerView recyclerViewHighlightsIpl;
    private PageIndicatorView indicator;
    private TextView txtInternational;
    private TextView txtIpl;

    public HighlightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_highlights, container, false);
        recyclerViewHighlights = view.findViewById(R.id.recyclerViewHighlights);
        recyclerViewHighlightsIpl = view.findViewById(R.id.recyclerViewHighlightsIpl);
        progressBar = view.findViewById(R.id.progressBar);
        viewpager = view.findViewById(R.id.viewpager);
        indicator = view.findViewById(R.id.indicator);
        txtInternational = view.findViewById(R.id.txtInternational);
        txtIpl = view.findViewById(R.id.txtIpl);

        progressBar.setVisibility(View.VISIBLE);
        txtInternational.setVisibility(View.GONE);
        txtIpl.setVisibility(View.GONE);

        highlightList = new ArrayList<>();
        highlightIplList = new ArrayList<>();
        highlightBannerList = new ArrayList<>();

        highlightBannerAdapter = new HighlightBannerAdapter(getActivity(), highlightBannerList, true, new HighlightBannerAdapter.HighlightBannerAdapterListener() {
            @Override
            public void onHighlightBannerSelected(String mediaId, String title) {
                Intent intent = new Intent(getActivity(), HighlightPlayActivity.class);
                intent.putExtra("videoId", mediaId);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        viewpager.setAdapter(highlightBannerAdapter);
        viewpager.setPageMargin(dpToPx(10));


        adapter = new HighlightAdapter(getActivity(), highlightList, new HighlightAdapter.HighlightAdapterListener() {
            @Override
            public void onHighlightSelected(String mediaId, String title) {
                Intent intent = new Intent(getActivity(), HighlightPlayActivity.class);
                intent.putExtra("videoId", mediaId);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

        recyclerViewHighlights.setHasFixedSize(true);
        recyclerViewHighlights.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHighlights.setAdapter(adapter);
        recyclerViewHighlights.setNestedScrollingEnabled(false);


        adapter2 = new HighlightAdapter(getActivity(), highlightIplList, new HighlightAdapter.HighlightAdapterListener() {
            @Override
            public void onHighlightSelected(String mediaId, String title) {
                Intent intent = new Intent(getActivity(), HighlightPlayActivity.class);
                intent.putExtra("videoId", mediaId);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });

        recyclerViewHighlightsIpl.setHasFixedSize(true);
        recyclerViewHighlightsIpl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHighlightsIpl.setAdapter(adapter2);
        recyclerViewHighlightsIpl.setNestedScrollingEnabled(false);

        loadData();
        loadDataIpl();

        indicator.setCount(viewpager.getIndicatorCount());
        viewpager.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
                indicator.setProgress(selectingPosition, progress);
            }

            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {
//                indicatorView.setSelection(newIndicatorPosition);
            }
        });


        return view;
    }

    private void loadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                highlightList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Highlights highlights = gson.fromJson(response, Highlights.class);

                highlightBannerList.add(highlights.getContent().get(0));
                highlightBannerList.add(highlights.getContent().get(1));

                highlightList.addAll(highlights.getContent());

                adapter.notifyDataSetChanged();
                txtInternational.setVisibility(View.VISIBLE);

                highlightBannerAdapter.notifyDataSetChanged();
                indicator.setCount(viewpager.getIndicatorCount());

                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void loadDataIpl() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_IPL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();
                highlightIplList.clear();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Highlights highlights = gson.fromJson(response, Highlights.class);

                highlightBannerList.add(highlights.getContent().get(2));
                highlightBannerList.add(highlights.getContent().get(3));

                highlightIplList.addAll(highlights.getContent());

                adapter2.notifyDataSetChanged();
                txtIpl.setVisibility(View.VISIBLE);

                highlightBannerAdapter.notifyDataSetChanged();
                indicator.setCount(viewpager.getIndicatorCount());

                progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
