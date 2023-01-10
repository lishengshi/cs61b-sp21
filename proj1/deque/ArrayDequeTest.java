package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addRemoveTest(){

        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addFirst("i");
        ad.addLast("am");
        assertEquals("2",ad.size());
    }


}
