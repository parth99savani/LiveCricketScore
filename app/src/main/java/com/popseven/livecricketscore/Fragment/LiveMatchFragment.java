package com.popseven.livecricketscore.Fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.popseven.livecricketscore.Adapter.CurrentOverAdapter;
import com.popseven.livecricketscore.Common.TeamColorList;
import com.popseven.livecricketscore.Model.MiniCommentary.MiniCommentary;
import com.popseven.livecricketscore.Model.TeamColor;
import com.popseven.livecricketscore.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveMatchFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private TextView textviewBatsman;
    private TextView textviewRun;
    private TextView textviewBowl;
    private TextView textviewFour;
    private TextView textviewSix;
    private TextView textviewStrikeRate;
    private LinearLayout relative;
    private TextView textviewBatsmanp1;
    private TextView textviewRunp1;
    private TextView textviewBowlp1;
    private TextView textviewFourp1;
    private TextView textviewSixp1;
    private TextView textviewStrikeRatep1;
    private LinearLayout relative1;
    private TextView textviewBatsmanp2;
    private TextView textviewRunp2;
    private TextView textviewBowlp2;
    private TextView textviewFourp2;
    private TextView textviewSixp2;
    private TextView textviewStrikeRatep2;
    private LinearLayout relative2;
    private TextView textviewBowler;
    private TextView textviewOver;
    private TextView textviewMedan;
    private TextView textviewRunn;
    private TextView textviewWicket;
    private TextView textviewEconomicrate;
    private LinearLayout relative3;
    private TextView textviewBowler1;
    private TextView textviewOver1;
    private TextView textviewMedan1;
    private TextView textviewRun1;
    private TextView textviewWicket1;
    private TextView textviewEconomicrate1;
    private LinearLayout relative4;
    private TextView textviewCurrentOver;
    private TextView textviewCurrentover1;
    private RecyclerView recyclerViewBalls;
    private LinearLayout linearLayoutSB;
    private String matchId;
    private RelativeLayout rlBatman1;
    private RelativeLayout rlBatman2;
    private Handler handler = new Handler();
    private int apiDelayed = 5000; //5 seconds
    private Runnable runnable;
    private CurrentOverAdapter adapter;
    private List<String> ballList;
    private TextView textviewCRR1;
    private TextView textviewRRR1;
    private TextView textviewCRR;
    private TextView textviewRRR;
    private TextView txtTimer;
    private LinearLayout llTimer;
    private SwipeRefreshLayout swipeRefresh;
    private TextView txtMOM;
    private LinearLayout llMOM;
    private TeamColorList teamColorList = new TeamColorList();
    private List<TeamColor> colorList = new ArrayList<>();
    private Button btnTeamWinner;
    private static String URL_OVER;
    private static String URL_DATA;

    public LiveMatchFragment(String matchId) {
        // Required empty public constructor
        this.matchId = matchId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_match, container, false);

        textviewBatsman = view.findViewById(R.id.textviewBatsman);
        textviewRun = view.findViewById(R.id.textviewRun);
        textviewBowl = view.findViewById(R.id.textviewBowl);
        textviewFour = view.findViewById(R.id.textviewFour);
        textviewSix = view.findViewById(R.id.textviewSix);
        textviewStrikeRate = view.findViewById(R.id.textviewStrikeRate);
        relative = view.findViewById(R.id.relative);
        textviewBatsmanp1 = view.findViewById(R.id.textviewBatsmanp1);
        textviewRunp1 = view.findViewById(R.id.textviewRunp1);
        textviewBowlp1 = view.findViewById(R.id.textviewBowlp1);
        textviewFourp1 = view.findViewById(R.id.textviewFourp1);
        textviewSixp1 = view.findViewById(R.id.textviewSixp1);
        textviewStrikeRatep1 = view.findViewById(R.id.textviewStrikeRatep1);
        relative1 = view.findViewById(R.id.relative1);
        textviewBatsmanp2 = view.findViewById(R.id.textviewBatsmanp2);
        textviewRunp2 = view.findViewById(R.id.textviewRunp2);
        textviewBowlp2 = view.findViewById(R.id.textviewBowlp2);
        textviewFourp2 = view.findViewById(R.id.textviewFourp2);
        textviewSixp2 = view.findViewById(R.id.textviewSixp2);
        textviewStrikeRatep2 = view.findViewById(R.id.textviewStrikeRatep2);
        relative2 = view.findViewById(R.id.relative2);
        textviewBowler = view.findViewById(R.id.textviewBowler);
        textviewOver = view.findViewById(R.id.textviewOver);
        textviewMedan = view.findViewById(R.id.textviewMedan);
        textviewRunn = view.findViewById(R.id.textviewRunn);
        textviewWicket = view.findViewById(R.id.textviewWicket);
        textviewEconomicrate = view.findViewById(R.id.textviewEconomicrate);
        relative3 = view.findViewById(R.id.relative3);
        textviewBowler1 = view.findViewById(R.id.textviewBowler1);
        textviewOver1 = view.findViewById(R.id.textviewOver1);
        textviewMedan1 = view.findViewById(R.id.textviewMedan1);
        textviewRun1 = view.findViewById(R.id.textviewRun1);
        textviewWicket1 = view.findViewById(R.id.textviewWicket1);
        textviewEconomicrate1 = view.findViewById(R.id.textviewEconomicrate1);
        relative4 = view.findViewById(R.id.relative4);
        textviewCurrentOver = view.findViewById(R.id.textviewCurrentOver);
        textviewCurrentover1 = view.findViewById(R.id.textviewCurrentover1);
        recyclerViewBalls = view.findViewById(R.id.recyclerViewBalls);
        linearLayoutSB = view.findViewById(R.id.linearLayoutSB);
        rlBatman1 = view.findViewById(R.id.rlBatman1);
        rlBatman2 = view.findViewById(R.id.rlBatman2);
        textviewCRR1 = view.findViewById(R.id.textviewCRR1);
        textviewRRR1 = view.findViewById(R.id.textviewRRR1);
        textviewCRR = view.findViewById(R.id.textviewCRR);
        textviewRRR = view.findViewById(R.id.textviewRRR);
        txtTimer = view.findViewById(R.id.txtTimer);
        llTimer = view.findViewById(R.id.llTimer);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        txtMOM = view.findViewById(R.id.txtMOM);
        llMOM = view.findViewById(R.id.llMOM);
        btnTeamWinner = view.findViewById(R.id.btnTeamWinner);

        colorList = teamColorList.getTeamColorList();

        recyclerViewBalls.setHasFixedSize(true);
        recyclerViewBalls.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ballList = new ArrayList<>();

        adapter = new CurrentOverAdapter(getActivity(), ballList);
        recyclerViewBalls.setAdapter(adapter);

        loadData();
        //loadOver();

        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);

        return view;
    }

    private void loadData() {

        URL_DATA="http://mapps.cricbuzz.com/cbzios/match/" + matchId + "/mini-commentary";

        swipeRefresh.setRefreshing(true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //progressDialog.dismiss();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.serializeNulls().create();
                // pass response
                MiniCommentary match = gson.fromJson(response, MiniCommentary.class);

                //Match match = null;

//                for (int i = 0; i < series.getMatches().size(); i++) {
//                    if (series.getMatches().get(i).getMatchId().equals(matchId)) {
//                        match = series.getMatches().get(i);
//                    }
//                }

                if(match.getHeader()==null){

                }else {

                    if (match.getHeader().getMomNames() == null) {

                        llMOM.setVisibility(View.GONE);

                        if (match.getBatsman() == null && match.getBowler() == null) {
                            linearLayoutSB.setVisibility(View.GONE);
                            long currentTime = System.currentTimeMillis();
                            final Calendar cal = Calendar.getInstance();
                            cal.setTimeInMillis(currentTime);
                            Date date = cal.getTime();
                            int hour = date.getHours();
                            int min = date.getMinutes();

                            long matchStartTime = Long.parseLong(match.getHeader().getStartTime());
                            final Calendar cal1 = Calendar.getInstance();
                            cal1.setTimeInMillis(matchStartTime * 1000);
                            Date date1 = cal1.getTime();
                            int hour1 = date1.getHours();
                            int min1 = date1.getMinutes();

//                        if ((min1 - min) >= 0) {
//                            txtTimer.setText(String.valueOf(hour1 - hour) + ":" + String.valueOf(min1 - min));
//                        } else if((hour1-hour)>0){
//                            txtTimer.setText(String.valueOf(hour1 - hour - 1) + ":" + String.valueOf(60 + min1 - min));
//                            //txtTimer.setText("0:" + String.valueOf(60 - min1 - min));
//                        } else {
//                            txtTimer.setText(String.valueOf(hour1 - hour) + ":" + String.valueOf(60 + min1 - min));
//                        }

                            if ((hour1 - hour) > 0) {
                                if ((min1 - min) >= 0) {
                                    txtTimer.setText(String.valueOf(hour1 - hour) + ":" + String.valueOf(min1 - min));
                                } else {
                                    txtTimer.setText(String.valueOf(hour1 - hour - 1) + ":" + String.valueOf(60 + min1 - min));
                                }
                            } else if((hour1 - hour)==0){
                                if ((min1 - min) >= 0) {
                                    txtTimer.setText(String.valueOf(hour1 - hour) + ":" + String.valueOf(min1 - min));
                                } else {
                                    txtTimer.setVisibility(View.GONE);
                                }
                            } else {
                                txtTimer.setVisibility(View.GONE);
                            }

                            llTimer.setVisibility(View.VISIBLE);

                        } else {
                            if (match.getBatTeam().getInnings().size() == 1) {
                                textviewCurrentover1.setText("(" + match.getBatTeam().getInnings().get(0).getOvers() + ")");
                            } else if (match.getBatTeam().getInnings().size() == 2) {
                                textviewCurrentover1.setText("(" + match.getBatTeam().getInnings().get(1).getOvers() + ")");
                            } else if (match.getBatTeam().getInnings().size() == 0) {
                                textviewCurrentover1.setText("0");
                            }

                            llTimer.setVisibility(View.GONE);
                            linearLayoutSB.setVisibility(View.VISIBLE);
                            if (match.getBatsman().size() == 1) {
                                textviewBatsmanp1.setText(match.getBatsman().get(0).getName());
                                textviewRunp1.setText(match.getBatsman().get(0).getR());
                                textviewBowlp1.setText(match.getBatsman().get(0).getB());
                                textviewFourp1.setText(match.getBatsman().get(0).get4s());
                                textviewSixp1.setText(match.getBatsman().get(0).get6s());
                                if (Integer.parseInt(match.getBatsman().get(0).getB()) > 0) {
                                    float r = Float.parseFloat(match.getBatsman().get(0).getR());
                                    float b = Float.parseFloat(match.getBatsman().get(0).getB());
                                    float strikeRateP1 = (r / b) * 100;
                                    textviewStrikeRatep1.setText(new DecimalFormat("###.##").format(strikeRateP1));
                                } else {
                                    textviewStrikeRatep1.setText("0");
                                }
                                if (match.getBatsman().get(0).getStrike().equals("1")) {
                                    textviewBatsmanp1.setTextColor(getActivity().getResources().getColor(R.color.colorGreen));
                                } else if (match.getBatsman().get(0).getStrike().equals("0")) {
                                    textviewBatsmanp1.setTextColor(getActivity().getResources().getColor(android.R.color.black));
                                }
                                rlBatman2.setVisibility(View.GONE);
                            } else if (match.getBatsman().size() == 2) {
                                textviewBatsmanp1.setText(match.getBatsman().get(0).getName());
                                textviewRunp1.setText(match.getBatsman().get(0).getR());
                                textviewBowlp1.setText(match.getBatsman().get(0).getB());
                                textviewFourp1.setText(match.getBatsman().get(0).get4s());
                                textviewSixp1.setText(match.getBatsman().get(0).get6s());
                                if (Integer.parseInt(match.getBatsman().get(0).getB()) > 0) {
                                    float r = Float.parseFloat(match.getBatsman().get(0).getR());
                                    float b = Float.parseFloat(match.getBatsman().get(0).getB());
                                    float strikeRateP1 = (r / b) * 100;
                                    textviewStrikeRatep1.setText(new DecimalFormat("###.##").format(strikeRateP1));
                                } else {
                                    textviewStrikeRatep1.setText("0");
                                }

                                if (match.getBatsman().get(0).getStrike().equals("1")) {
                                    textviewBatsmanp1.setTextColor(getActivity().getResources().getColor(R.color.colorGreen));
                                } else if (match.getBatsman().get(0).getStrike().equals("0")) {
                                    textviewBatsmanp1.setTextColor(getActivity().getResources().getColor(android.R.color.black));
                                }
                                textviewBatsmanp2.setText(match.getBatsman().get(1).getName());
                                textviewRunp2.setText(match.getBatsman().get(1).getR());
                                textviewBowlp2.setText(match.getBatsman().get(1).getB());
                                textviewFourp2.setText(match.getBatsman().get(1).get4s());
                                textviewSixp2.setText(match.getBatsman().get(1).get6s());
                                if (Integer.parseInt(match.getBatsman().get(1).getB()) > 0) {
                                    float r = Float.parseFloat(match.getBatsman().get(1).getR());
                                    float b = Float.parseFloat(match.getBatsman().get(1).getB());
                                    float strikeRateP2 = (r / b) * 100;
                                    textviewStrikeRatep2.setText(new DecimalFormat("###.##").format(strikeRateP2));
                                } else {
                                    textviewStrikeRatep2.setText("0");
                                }
                                if (match.getBatsman().get(1).getStrike().equals("1")) {
                                    textviewBatsmanp2.setTextColor(getActivity().getResources().getColor(R.color.colorGreen));
                                } else if (match.getBatsman().get(1).getStrike().equals("0")) {
                                    textviewBatsmanp2.setTextColor(getActivity().getResources().getColor(android.R.color.black));
                                }
                                rlBatman2.setVisibility(View.VISIBLE);
                            }

                            if (match.getBowler().size() == 1) {
                                textviewBowler1.setText(match.getBowler().get(0).getName());
                                textviewOver1.setText(match.getBowler().get(0).getO());
                                textviewMedan1.setText(match.getBowler().get(0).getM());
                                textviewRun1.setText(match.getBowler().get(0).getR());
                                textviewWicket1.setText(match.getBowler().get(0).getW());
                                if (Float.parseFloat(match.getBowler().get(0).getO()) > 0) {
                                    float r = Float.parseFloat(match.getBowler().get(0).getR());
                                    int o = (int) (Float.parseFloat(match.getBowler().get(0).getO()));
                                    float b = (Float.parseFloat(match.getBowler().get(0).getO()) - o) * 10;
                                    b = b + (o * 6);
                                    float economicRateP1 = (r / b) * 6;
                                    textviewEconomicrate1.setText(new DecimalFormat("###.##").format(economicRateP1));
                                } else {
                                    textviewEconomicrate1.setText("0");
                                }
                            }

                        }
                    } else {

                        if (match.getHeader().getMomNames().size() > 0) {
                            txtMOM.setText(match.getHeader().getMomNames().get(0));

                            if (String.valueOf(match.getHeader().getWinningTeamId()).equals(match.getBatTeam().getId())) {
                                btnTeamWinner.setText(match.getBatTeam().getName());
                                llMOM.setVisibility(View.VISIBLE);
                            }else if(String.valueOf(match.getHeader().getWinningTeamId()).equals(match.getBowTeam().getId())) {
                                btnTeamWinner.setText(match.getBowTeam().getName());
                                llMOM.setVisibility(View.VISIBLE);
                            }else {

                            }

                            for (int j = 0; j < colorList.size(); j++) {
                                if (String.valueOf(match.getHeader().getWinningTeamId()).equals(colorList.get(j).getId())) {
                                    txtMOM.setTextColor(Color.parseColor(colorList.get(j).getColorCode()));
                                    Drawable buttonDrawable = btnTeamWinner.getBackground();
                                    buttonDrawable = DrawableCompat.wrap(buttonDrawable);
                                    DrawableCompat.setTint(buttonDrawable, Color.parseColor(colorList.get(j).getColorCode()));
                                    btnTeamWinner.setBackground(buttonDrawable);
                                }
                            }

                            //llMOM.setVisibility(View.VISIBLE);
                        }
                    }

                }

                ballList.clear();

                if(match.getCommLines()==null){

                }else {
                    if (match.getCommLines().size() > 0) {
                        ballList.addAll(match.getCommLines().get(0).getOSummary());
                    }
                }

                if (match.getCrr() == null) {
                    textviewCRR.setVisibility(View.GONE);
                    textviewCRR1.setVisibility(View.GONE);
                } else {
                    textviewCRR1.setText(match.getCrr());
                    textviewCRR.setVisibility(View.VISIBLE);
                    textviewCRR1.setVisibility(View.VISIBLE);
                }

                if (match.getRrr() == null) {
                    textviewRRR.setVisibility(View.GONE);
                    textviewRRR1.setVisibility(View.GONE);
                } else {
                    textviewRRR1.setText(match.getRrr());
                    textviewRRR.setVisibility(View.VISIBLE);
                    textviewRRR1.setVisibility(View.VISIBLE);
                }


                adapter.notifyDataSetChanged();


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

    }
}
