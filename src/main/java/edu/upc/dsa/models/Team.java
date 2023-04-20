package edu.upc.dsa.models;
import java.util.*;
public class Team {
    private int nplayers;
    private List<Player> players = new ArrayList<>();
    //private int lifePoints = 0;

    public Team(int n) {
        this.nplayers = n;
    }

    //public int getLifePoints() {
        //return this.lifePoints;
    //}

    public int getNplayers() {
        return this.nplayers;
    }

    public List<Player> getPlayers() {
        return this.players;
    }
    public void addPlayer(Player p) throws Exception{
        if (this.players.size() >= this.nplayers) {
            throw new Exception();
        } else {
            this.players.add(p);
        }
    }
    public int getLifePoints() {
        int lifePoints = 0;
       for (int i = 0; i<this.players.size(); i++) {
           lifePoints += this.players.get(i).getLifePoints();
       }
       return lifePoints;
    }
}
