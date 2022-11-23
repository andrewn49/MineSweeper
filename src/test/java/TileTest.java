import org.example.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    public void testConstructor(){
        Tile tile1 = new Tile(false);
        Assertions.assertFalse(tile1.getIsbomb());
        Tile tile2 = new Tile(true);
        Assertions.assertTrue(tile2.getIsbomb());
    }

    @Test
    public void testTilestates(){
        Tile tile3 = new Tile(false);
        Assertions.assertEquals(0, tile3.getTilestate(), "Tilestate is wrong: " + tile3.getTilestate());
        tile3.flagMe();
        Assertions.assertEquals(4, tile3.getTilestate(), "Tilestate is wrong: " + tile3.getTilestate());
        tile3.flagMe();
        tile3.revealMe();
        Assertions.assertEquals(1, tile3.getTilestate(), "Tilestate is wrong: " + tile3.getTilestate());
    }
}
