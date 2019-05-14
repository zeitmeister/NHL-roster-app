package com.sporrong.recyclerviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    Team mTeam;
    String[] dataset = {"Hej", "kakan", "kuken"};

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TeamViewHolder(TextView v){
            super(v);
            textView = v.findViewById(R.id.playerTextView);
        }
    }

    public TeamAdapter(Team team, Context applicationContext) {
        mTeam = team;
    }

    @NonNull
    @Override
    public TeamAdapter.TeamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.player_text_view, viewGroup, false);
        TeamViewHolder vh = new TeamViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.TeamViewHolder teamViewHolder, int i) {
        System.out.println(mTeam.getPlayers().size());
        teamViewHolder.textView.setText(mTeam.getPlayers().get(i).getFullName()+ " is a "+ mTeam.getPlayers().get(i).getPosition());
    }


    @Override
    public int getItemCount() {
        return mTeam.getPlayers().size();
    }

}
