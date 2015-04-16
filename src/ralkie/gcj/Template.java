package ralkie.gcj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ralkie.gcj.Converter.toI;
import static ralkie.gcj.Converter.toIL;

public class Template {
    public static void main(String[] args) throws IOException {
        Dataset data = IO.read();
        IO.write(process(data));
    }

    private static List process(Dataset ds) {
        List<String> result = new ArrayList<>();
        Caser caser = new FixedCaser(ds, 1);
        for (Dataset c : caser.getCases()) {
            result.add(solveCase(c));
        }
        return result;
    }

    private static String solveCase(Dataset c) {
        System.out.println(c.getRow(0));
        return null;//todo
    }
}
