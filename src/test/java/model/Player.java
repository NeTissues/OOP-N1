package model;

import java.util.ArrayList;
import java.util.Objects;

public class Player {

    private String name;
    private ArrayList<Monster> team;

    public Player(String name, ArrayList<Monster> team) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Monster> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Monster> team) {
        this.team = team;
    }

    public void addToTeam(ArrayList<Monster> team, Monster monsterToBeAdded){
        team.add(monsterToBeAdded);
    }

    public void removeFromTeam(ArrayList<Monster> team, Monster monsterToBeDeleted){
        for (int i = 0; i < team.size(); i++){
            if (team.get(i).equals(monsterToBeDeleted))
                team.remove(monsterToBeDeleted);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName()) && Objects.equals(getTeam(), player.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTeam());
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", team=" + team +
                '}';
    }
}
