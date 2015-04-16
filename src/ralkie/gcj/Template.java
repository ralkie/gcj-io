package ralkie.gcj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ralkie.gcj.Converter.toIL;

public class Template {
    public static void main(String[] args) throws IOException {
        Dataset data = IO.read();
        IO.write(solve(data));
    }

    private static List solve(Dataset ds) {
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



    // UTILS

    //comparators
    public static final Comparator<Integer> ASC_I = new Comparator<Integer>(){
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };
    public static final Comparator<Integer> DESC_I = new Comparator<Integer>(){
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    };
    public static final Comparator<Double> ASC_D = new Comparator<Double>(){
        @Override
        public int compare(Double o1, Double o2) {
            return o1.compareTo(o2);
        }
    };
    public static final Comparator<Double> DESC_D = new Comparator<Double>(){
        @Override
        public int compare(Double o1, Double o2) {
            return o2.compareTo(o1);
        }
    };
}
