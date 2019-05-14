package com.sporrong.recyclerviewtest;

import java.util.ArrayList;
import java.util.List;

class Team {

    private String TeamName;
    private int Id;
    private List<Player> mPlayers = new ArrayList<>();


    String getTeamName() {
        return TeamName;
    }
    void setTeamName(String teamName) {
        TeamName = teamName;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<Player> getPlayers() {
        return mPlayers;
    }

    public void setPlayers(List<Player> mPlayers) {
        this.mPlayers = mPlayers;
    }

    public void addPlayer(Player player){
        mPlayers.add(player);
    }
}
