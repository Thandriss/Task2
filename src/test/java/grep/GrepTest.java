package grep;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class GrepTest {
    Grep a = new Grep();

    @Test
    void ignoreCase() throws IOException {
        a = new Grep("ручка", new File("456456456toFind.txt"));
        ArrayList<String> b = new ArrayList<String>();
        b.add("ручка книга пенал кружка");
        b.add("телефон растение слон корова ручка");
        assertArrayEquals(b, a.ignoreCase());
    }

    private boolean assertArrayEquals(ArrayList<String> b, List<String> ignoreCase) {
        return b.equals(ignoreCase);
    }
}