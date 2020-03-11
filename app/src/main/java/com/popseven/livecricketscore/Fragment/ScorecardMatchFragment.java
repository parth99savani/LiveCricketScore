package com.popseven.livecricketscore.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.popseven.livecricketscore.Adapter.ScreenSlidePagerAdapter;
import com.popseven.livecricketscore.Model.Series.Match;
import com.popseven.livecricketscore.Model.Series.Series;
import com.popseven.livecricketscore.R;

import java.util.ArrayList;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScorecardMatchFragment extends Fragment {

    private static String URL_DATA;
    private RelativeLayout rlScorecard;
    private ToggleSwitch toggleScoreCard;
    private LinearLayout ll1;
    private ViewPager viewPager;
    private String matchId,seriesId;
    private String teamId1, teamId2;
    private LinearLayout llMatchNotStart;

    public ScorecardMatchFragment(String matchId,String seriesId) {
        // Required empty public constructor
        this.matchId = matchId;
        this.seriesId = seriesId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scorecard_match, container, false);

        rlScorecard = view.findViewById(R.id.rlScorecard);
        toggleScoreCard = view.findViewById(R.id.toggleScoreCard);
        ll1 = view.findViewById(R.id.ll1);
        viewPager = view.findViewById(R.id.viewPagerSC);
        llMatchNotStart = view.findViewById(R.id.llMatchNotStart);

        loadData(matchId,seriesId);

        return view;
    }

    private void loadData(final String matchId, final String seriesId) {

        URL_DATA="http://mapps.cricbuzz.com/cbzios/series/" + seriesId + "/matches";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                Series series = gson.fromJson(response, Series.class);

                Match match = null;

                for (int i = 0; i < series.getMatches().size(); i++) {
                    if (series.getMatches().get(i).getMatchId().equals(matchId)) {
                        match = series.getMatches().get(i);
                    }
                }

                if(match==null){

                }else {
                    if (match.getBatTeam() == null && match.getBowTeam() == null) {
                        rlScorecard.setVisibility(View.GONE);
                        llMatchNotStart.setVisibility(View.VISIBLE);
                    } else {
                        llMatchNotStart.setVisibility(View.GONE);

                        ArrayList<String> labels = new ArrayList<>();
                        labels.add(match.getTeam1().getSName());
                        teamId1 = match.getTeam1().getId();
                        labels.add(match.getTeam2().getSName());
                        teamId2 = match.getTeam2().getId();
                        toggleScoreCard.setLabels(labels);

                        ArrayList<Fragment> fragList = new ArrayList<>();
                        fragList.add(new TeamSCFragment(matchId, teamId1));
                        fragList.add(new TeamSCFragment(matchId, teamId2));
                        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(fragList, getFragmentManager());

                        viewPager.setAdapter(pagerAdapter);
                        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int i, float v, int i1) {
                            }

                            @Override
                            public void onPageSelected(int i) {
                                toggleScoreCard.setCheckedTogglePosition(i);
                            }

                            @Override
                            public void onPageScrollStateChanged(int i) {

                            }
                        });

                        toggleScoreCard.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener() {
                            @Override
                            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                                viewPager.setCurrentItem(position, true);
                            }
                        });

                        rlScorecard.setVisibility(View.VISIBLE);
                    }
                }



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
