package edu.upc.dsa;
import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.State;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class GameManagerTest {

    GameManager gm;
    @Before
    public void setUp() throws Exception{
        this.gm = GameManagerImpl.getInstance();
        gm.createGame(2, 2);
        gm.addUser("og9", "olivier", "Giroud");
        gm.addUser("kb9", "Karim", "Benzema");
        gm.addUser("km7", "Kylian", "Mbappé");
        gm.addUser("ag7", "Antoine", "Griezmann");
        gm.init("og9");
        gm.init("kb9");
        gm.init("km7");
        gm.init("ag7");
    }

    @After
    public void tearDown() {
        this.gm =null;
    }

    @Test
    public void testConsultState() throws Exception {
        State s = gm.consultState();
        Assert.assertEquals(s, State.INICIADO_EN_FUNCIONAMIENTO);
    }
}
