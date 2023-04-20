package edu.upc.dsa.models;
import java.util.*;

public class Player {
    public static Map<String, Player> playersMap = new HashMap<>();
    private String id;
    private String name;
    private String surname;
    private int dsaCoins;
    private List<Product> inventory = new ArrayList<>();
    private int lifePoints;

    public Player(String nid, String nname, String nsurname) throws Exception {
        if (playersMap.containsKey(nid)) {
            throw new Exception();
        } else {
            this.id = nid;
            this.name = nname;
            this.surname = nsurname;
            this.dsaCoins = 25;
            this.lifePoints = 100;
            playersMap.put(this.id, this);
        }
    }

    public String getId() {
        return this.id;
    }

    public int getDsaCoins() {
        return this.dsaCoins;
    }

    public int getLifePoints() {
        return this.lifePoints;
    }

    public List<Product> getInventory() {
        return this.inventory;
    }

    public void setDsaCoins(int dsaCoins) {
        if (dsaCoins < 0) {this.dsaCoins = 0;}
        else {this.dsaCoins = dsaCoins;}
    }
}
