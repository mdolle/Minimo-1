package edu.upc.dsa.models;
import java.util.*;

public class Game {

    private State state;
    private List<Team> teams = new ArrayList<>();

    public Game() {this.state = State.NO_INICIADO;}

    public State getState() {
        return this.state;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void create(int n, int p) {
        for (int i = 0; i < n; i++) {
            teams.add(new Team(p));
        }
        this.state = State.INICIADO_EN_PREPARACION;
    }
}
