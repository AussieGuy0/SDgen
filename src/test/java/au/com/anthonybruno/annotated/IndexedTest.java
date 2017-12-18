package au.com.anthonybruno.annotated;

import au.com.anthonybruno.Gen;
import au.com.anthonybruno.annotation.Index;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class IndexedTest {

    @Test
    public void generateCsv() {
        String generated = Gen.start()
                .use(Pojo.class)
                .generate(5)
                .asCsv()
                .toStringForm();

        String[] firstRow = generated.split("\n")[0].split(",");
        assertArrayEquals(new String[]{"c", "b", "d", "a"}, firstRow);
    }


    private static class Pojo {

        @Index(3)
        private final String a;

        @Index(1)
        private final int b;

        @Index(0)
        private final int c;

        @Index(2)
        private final String d;

        private Pojo(String a, int b, int c, String d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }
}
