package com.sporrong.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static java.security.AccessController.getContext;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private League mLeague;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder(TextView v){
            super(v);
            textView = v.findViewById(R.id.teamTextView);
        }
    }

    public MyAdapter(League league, Context c){
        mLeague = league;
        context = c;
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_text_view, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder viewHolder, int i) {
        viewHolder.textView.setText(mLeague.getTeamNames().toArray(new String[mLeague.getTeamList().size()])[i]);

        viewHolder.
        textView.setOnClickListener(v -> {
            Toast.makeText(context, viewHolder.textView.getText(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(v.getContext(), PlayersListActivity.class);
            intent.putExtra("teamName", mLeague.getTeamList().get(i).getTeamName());
            intent.putExtra("teamId", mLeague.getTeamList().get(i).getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mLeague.getTeamList().size();
    }
}
