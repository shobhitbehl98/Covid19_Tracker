package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.loaderspack.loaders.PulseLoader;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tvCases,tvRecovered,tvCritical,tvActive,tvDeaths,tvTodayCases,tvTodayDeaths,tvAffectedCountries;
//    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActive);
        tvDeaths=findViewById(R.id.tvDeaths);
        tvTodayCases=findViewById(R.id.tvTodayCases);
        tvTodayDeaths=findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries=findViewById(R.id.tvAffectedCountries);

//        simpleArcLoader=findViewById(R.id.loader);
        scrollView=findViewById(R.id.scrollstats);
        pieChart=findViewById(R.id.pieChart);

        fetchData();

    }

    private void fetchData() {
        String url="https://corona.lmao.ninja/v2/all/";
//        simpleArcLoader.start();
        StringRequest request=new StringRequest(Request.Method.GET, url,
     new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    tvCases.setText(jsonObject.getString("cases"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvDeaths.setText(jsonObject.getString("deaths"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvAffectedCountries.setText((jsonObject.getString("affectedCountries")));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()),Color.parseColor("#FF0000")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()),Color.parseColor("#07FF12")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvDeaths.getText().toString()),Color.parseColor("#3756F6")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()),Color.parseColor("#29B6F6")));

                    pieChart.startAnimation();
//                    simpleArcLoader.stop();
//                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();
//                    simpleArcLoader.stop();
//                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
//                simpleArcLoader.stop();
//                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void goTrackCountries(View view) {
        startActivity(new Intent(getApplicationContext(),Affected_Countries.class));
    }
}