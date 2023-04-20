package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;


public class GameManagerImpl implements GameManager {
    private static GameManager instance;
    private Game game;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    private GameManagerImpl() {
        this.game = new Game();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    @Override
    public void createGame(int n, int p) throws Exception {
        if (n < 2) {logger.warn("number of teams too small");}
        else if (this.game.getState() == State.NO_INICIADO){
            this.game.create(n,p);
            this.game.setState(State.INICIADO_EN_PREPARACION);
            logger.info("game created");
        } else {
            logger.warn("game is already created");
            throw new Exception();
        }
    }

    @Override
    public void addUser(String nid, String nname, String nsurname) throws Exception {
        try {
            Player newcomer = new Player(nid, nname, nsurname);
            logger.info(nid + " added on the game");
        } catch (Exception e) {logger.warn("player already added");}
    }

    @Override
    public void addProduct(String nid, String ndescription, int nprice) throws Exception {
        try {
            Product newproduct = new Product(nid, ndescription, nprice);
            logger.info(nid + " added on the shop");
        } catch (Exception e) {logger.warn("product already added");}
    }

    @Override
    public void buyProduct(String productId, String playerId) throws Exception {
        if (Player.playersMap.containsKey(playerId) && Product.Shop.containsKey(productId)) {
            Player pl = Player.playersMap.get(playerId);
            Product pr = Product.Shop.get(productId);
            if (pl.getDsaCoins() > pr.getPrice()) {
                pl.getInventory().add(pr);
                int p = pl.getDsaCoins();
                pl.setDsaCoins(p - pr.getPrice());
                logger.info(productId + " buy by " + playerId);
            }
        } else {logger.warn("product or player not found");}
    }

    @Override
    public void init(String playerId) throws Exception {
        try {
            if (!Player.playersMap.containsKey(playerId)) {
                logger.warn("player not found");
            } else {
                List<Team> teams = this.game.getTeams();
                Player p = Player.playersMap.get(playerId);
                for (int i = 1; i < teams.size(); i++) {
                    if (teams.get(i - 1).getPlayers().size() == 0) {
                        teams.get(i - 1).addPlayer(p);
                    } else if (teams.get(i - 1).getPlayers().size() == teams.get(i).getPlayers().size()) {
                        teams.get(i - 1).addPlayer(p);
                    }
                }
            }
        } catch (Exception e) {logger.warn("teams are full");}
    }

    @Override
    public State consultState() {
        logger.info("State of the game is : " + this.game.getState());
        return this.game.getState();
    }

    @Override
    public void refresh(String playerId, int npoints) {
            if (!Player.playersMap.containsKey(playerId)){
                logger.warn("player not found");
            } else {
                Player p = Player.playersMap.get(playerId);
                int n = p.getDsaCoins();
                p.setDsaCoins(n-npoints);
                logger.info(playerId + " lost " + npoints + " points");
            }
    }

    @Override
    public Object consultLifePoints(String playerId) {
        if (!Player.playersMap.containsKey(playerId)){
            logger.warn("player not found");
            return null;
        } else {
            Player p = Player.playersMap.get(playerId);
            return p.getLifePoints();}
    }

    @Override
    public Object consulLifePoints(int j) {
        if (j > this.game.getTeams().size() - 1){
            logger.warn("team is not exist");
            return null;
        } else {
            Team t = this.game.getTeams().get(j);
            int sum = 0;
            for (int i = 0; i < t.getPlayers().size(); i++) {
                sum += t.getPlayers().get(i).getLifePoints();
            }
            logger.info("Points life of the " +j+ "th team is " +sum);
            return sum;
        }
    }

    @Override
    public void end() {
        this.game.setState(State.FINALISADO);
        logger.info("game is over");
    }
}