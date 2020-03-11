package com.popseven.livecricketscore.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.LiveMatchAdapter;
import com.popseven.livecricketscore.MatchScoreActivity;
import com.popseven.livecricketscore.Model.LiveMatches.Livematches;
import com.popseven.livecricketscore.Model.LiveMatches.Match;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private TextView txtLiveMatch;
    private RecyclerView recyclerViewLiveMatches;
    private static final String URL_DATA = "http://mapps.cricbuzz.com/cbzios/match/livematches";
    private LiveMatchAdapter adapter;
    private List<Match> matchList;
    private Handler handler = new Handler();
    private int apiDelayed = 5000; //5 seconds
    private Runnable runnable;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefresh;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        txtLiveMatch = view.findViewById(R.id.txtLiveMatch);
        recyclerViewLiveMatches = view.findViewById(R.id.recyclerViewLiveMatches);
        progressBar = view.findViewById(R.id.progressBar);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);

        matchList = new ArrayList<>();

        adapter = new LiveMatchAdapter(getActivity(), matchList, new LiveMatchAdapter.LiveMatchAdapterListener() {
            @Override
            public void onLiveMatchSelected(String matchId) {

                presentActivity(getView(),matchId);

//                Intent intent = new Intent(getActivity(), MatchScoreActivity.class);
//                intent.putExtra("matchId", matchId);
//                startActivity(intent);
            }
        });

        recyclerViewLiveMatches.setHasFixedSize(true);

        recyclerViewLiveMatches.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerViewLiveMatches.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);

        loadMatch();

        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        return view;
    }

    public void presentActivity(View view, String matchId) {
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(getActivity(), view, "transition");
//        int revealX = (int) (view.getX() + view.getWidth() / 2);
//        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(getActivity(), MatchScoreActivity.class);
//        intent.putExtra(MatchScoreActivity.EXTRA_CIRCULAR_REVEAL_X, revealX);
//        intent.putExtra(MatchScoreActivity.EXTRA_CIRCULAR_REVEAL_Y, revealY);
        intent.putExtra("matchId", matchId);
        startActivity(intent);
//        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }

    @Override
    public void onResume() {
        super.onResume();

        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do your function;
                //progressBar.setVisibility(View.VISIBLE);
                loadMatch();
                handler.postDelayed(runnable, apiDelayed);
            }
        }, apiDelayed); // so basically after your getHeroes(), from next time it will be 5 sec repeated
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible
    }

    private void loadMatch() {

        swipeRefresh.setRefreshing(true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Livematches livematches = gson.fromJson(response, Livematches.class);

                matchList.clear();

                for (int i = 0; i < livematches.getMatches().size(); i++) {
                    final long TWELVE_HOURS = 1000 * 60 * 60 * 6;
                    long currentTime = System.currentTimeMillis();
                    long matchEndTime = Long.parseLong(livematches.getMatches().get(i).getHeader().getEndTime());
                    long matchStartTime = Long.parseLong(livematches.getMatches().get(i).getHeader().getStartTime());
                    long differ = currentTime - (matchEndTime * 1000);
                    long differ1 = (matchStartTime * 1000) - currentTime;

                    if (differ < TWELVE_HOURS && differ1 < TWELVE_HOURS) {
                        matchList.add(livematches.getMatches().get(i));
                    }
                }


                //matchList.addAll(livematches.getMatches());

                adapter.notifyDataSetChanged();

//                adapter = new LiveMatchAdapter(getActivity(), livematches.getMatches());
//                recyclerViewLiveMatches.setAdapter(adapter);

                progressBar.setVisibility(View.GONE);

//                final Handler handler = new Handler();
//                runnableCode = new Runnable() {
//                    @Override
//                    public void run() {
//
//                        adapter.notifyDataSetChanged();
//
//                        handler.postDelayed(runnableCode, 1000);
//                    }
//                };
//                handler.post(runnableCode);

                swipeRefresh.setRefreshing(false);

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

    @Override
    public void onRefresh() {
        loadMatch();
    }
}
