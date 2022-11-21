import org.example.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    public void testConstructor(){
        Tile tile = new Tile(false);
        Assertions.assertEquals(false, tile.getIsbomb());
    }
}
