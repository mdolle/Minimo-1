package edu.upc.dsa;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.State;

import java.util.List;

public interface GameManager {


    public void createGame(int n, int p);
    public void addUser(String nid, String nname, String nsurname) throws Exception;
    public void addProduct(String nid, String ndescription, int nprice) throws Exception;
    public void buyProduct(String productId, String playerId);
    public void init(String playerId) throws Exception;
    public State consultState();
    public void refresh(String playerId, int npoints);
    public Object consultLifePoints(String playerId);
    public Object consulLifePoints(int j);
    public void end();



    public Game getGame();
}
