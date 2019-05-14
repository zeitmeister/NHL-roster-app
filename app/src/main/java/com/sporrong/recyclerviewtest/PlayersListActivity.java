package com.sporrong.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlayersListActivity extends AppCompatActivity {
    Button teamButton;
    Team team = new Team();
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;




    void run(int id) throws IOException {
        String url = "https://statsapi.web.nhl.com/api/v1/teams/"+id+"/roster";
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
                    getPlayers(myResponse);
                } catch (JSONException e){
                    System.out.println(e);
                }
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);
        /*teamButton = findViewById(R.id.teamButton);*/
        String teamName = getIntent().getStringExtra("teamName");
        int teamId = getIntent().getIntExtra("teamId", 0);

        try {
            run(teamId);
        } catch (IOException e){
            System.out.println(e);
        }

        recyclerView = findViewById(R.id.player_recycler_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new TeamAdapter(team, getApplicationContext());
        recyclerView.setAdapter(mAdapter);

        /*teamButton.setText(teamName);
        teamButton.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), teamName + teamId, Toast.LENGTH_LONG).show();
        });*/
    }

    private void getPlayers(String response) throws JSONException {
        JSONObject rawObject = new JSONObject(response);
        JSONArray players = rawObject.getJSONArray("roster");

        for (int i = 0; i < players.length(); i++)
        {
            Player player = new Player();
            player.setFullName(players.getJSONObject(i).getJSONObject("person").getString("fullName"));
            player.setPosition(players.getJSONObject(i).getJSONObject("position").getString("name"));
            team.addPlayer(player);
        }

    }
}
