package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int positionCountry;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvDeaths,tvTodayCases,tvTodayDeaths,tvAffectedCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        positionCountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+Affected_Countries.countryModelList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCountry=findViewById(R.id.tvCountry);
        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActive);
        tvDeaths=findViewById(R.id.tvDeaths);
        tvTodayCases=findViewById(R.id.tvTodayCases);
        tvTodayDeaths=findViewById(R.id.tvTodayDeaths);

        tvCountry.setText(Affected_Countries.countryModelList.get(positionCountry).getCountry());
        tvCases.setText(Affected_Countries.countryModelList.get(positionCountry).getCases());
        tvRecovered.setText(Affected_Countries.countryModelList.get(positionCountry).getRecovered());
        tvCritical.setText(Affected_Countries.countryModelList.get(positionCountry).getCritical());
        tvActive.setText(Affected_Countries.countryModelList.get(positionCountry).getActive());
        tvDeaths.setText(Affected_Countries.countryModelList.get(positionCountry).getDeaths());
        tvTodayCases.setText(Affected_Countries.countryModelList.get(positionCountry).getTodayCases());
        tvTodayDeaths.setText(Affected_Countries.countryModelList.get(positionCountry).getTodayDeaths());
//        tvCountry.setText(Affected_Countries.countryModelList.get(positionCountry).getCountry());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}