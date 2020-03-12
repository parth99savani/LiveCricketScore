package com.popseven.livecricketscore.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.HighlightAdapter;
import com.popseven.livecricketscore.HighlightPlayActivity;
import com.popseven.livecricketscore.Model.Highlights.Content;
import com.popseven.livecricketscore.Model.Highlights.Highlights;
import com.popseven.livecricketscore.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HighlightsFragment extends Fragment {

    private RecyclerView recyclerViewHighlights;
    private HighlightAdapter adapter;
    private List<Content> highlightList;
    private static final String URL_DATA = "https://api.platform.bcci.tv/content/bcci/video/EN/?tagNames=highlights&pageSize=20&page=0";

    public HighlightsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_highlights, container, false);
        recyclerViewHighlights = view.findViewById(R.id.recyclerViewHighlights);

        highlightList = new ArrayList<>();

        adapter = new HighlightAdapter(getActivity(), highlightList, new HighlightAdapter.HighlightAdapterListener() {
            @Override
            public void onHighlightSelected(String mediaId) {
                Intent intent = new Intent(getActivity(), HighlightPlayActivity.class);
                intent.putExtra("videoId",mediaId);
                startActivity(intent);
            }
        });

        recyclerViewHighlights.setHasFixedSize(true);

        recyclerViewHighlights.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerViewHighlights.setAdapter(adapter);

        loadData();

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

                highlightList.addAll(highlights.getContent());

                adapter.notifyDataSetChanged();


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

}
