package com.sporrong.recyclerviewtest;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private League league = new League();


    private String url = "https://statsapi.web.nhl.com/api/v1/teams";

    void run() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();

                try {
                    getTeams(myResponse);
                } catch (JSONException e){
                    System.out.println(e);
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            run();
        } catch (IOException e){
            e.printStackTrace();
        }
        recyclerView = findViewById(R.id.team_recycler_list);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(league, getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }

    private void getTeams(String response) throws JSONException {
        JSONObject rawObject = new JSONObject(response);
        JSONArray teams = rawObject.getJSONArray("teams");

        for (int i = 0; i < teams.length(); i++)
        {
            Team team = new Team();
            team.setTeamName(teams.getJSONObject(i).getString("name"));
            team.setId(teams.getJSONObject(i).getInt("id"));
            league.addTeam(team);
        }
        league.setTeamNames();
    }
}
