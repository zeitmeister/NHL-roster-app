package com.sporrong.recyclerviewtest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class League {
    private List<Team> teamList = new ArrayList<>();
    private List<String> teamNames = new ArrayList<>();

    List<Team> getTeamList() {
        Collections.sort(teamList, (o1, o2) -> o1.getTeamName().compareTo(o2.getTeamName()));
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }


    List<String> getTeamNames() {
        Collections.sort(teamNames);
        return teamNames;
    }

    void setTeamNames() {
        for(Team team : teamList){
            teamNames.add(team.getTeamName());
        }
    }

    void addTeam(Team team){
        teamList.add(team);
    }



}
