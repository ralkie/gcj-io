package ralkie.gcj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ralkie.gcj.Converters.*;

public class Template {
    public static void main(String[] args) throws IOException {
        Dataset data = IO.read();
        IO.write(solve(data));
    }

    private static List<String> solve(Dataset ds) {
        List<String> result = new ArrayList<>();
        Caser caser = new FixedCaser(ds, 1);
        int i = 1;
        for (Dataset c : caser.getCases()) {
            System.out.println("Case " + (i++));
            result.add(solveCase(c));
        }
        return result;
    }

    private static String solveCase(Dataset c) {
        return null;//todo
    }


    // -=UTILS=-

    // filters
    private static Integer max(List<Integer> list) {
        return list.stream().max(Integer::compare).get();
    }

    private static Integer min(List<Integer> list) {
        return list.stream().min(Integer::compare).get();
    }

    // comparators
    public static final Comparator<Integer> ASC_I = Integer::compareTo;
    public static final Comparator<Integer> DESC_I = (o1, o2) -> o2.compareTo(o1);
    public static final Comparator<Double> ASC_D = Double::compareTo;
    public static final Comparator<Double> DESC_D = (o1, o2) -> o2.compareTo(o1);
}
