package ralkie.gcj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ralkie.gcj.Converter.*;

public class IO {

    public static final String DEFAULT_IN = "in";
    public static final String DEFAULT_OUT = "out";

    public static Dataset read(String filename) throws IOException {
        Dataset dataset = new Dataset();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null)
                dataset.add(line);
        }
        return dataset;
    }

    public static Dataset read() throws IOException {
        return read(DEFAULT_IN);
    }

    public static void write(String filename, List<String> results) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            int caseCnt = 1;
            for (String result : results) {
                writer.write(String.format("Case #%d: %s", caseCnt++, result));
            }
        }
    }

    public static void write(List<String> results) throws IOException {
        write(DEFAULT_OUT, results);
    }
}

class Dataset {
    public static final String DEFAULT_SEPARATOR = " ";
    List<List<String>> rows = new ArrayList<>();

    public int size() {
        return rows.size();
    }

    public void add(String s) {
        rows.add(Arrays.asList(s.split(DEFAULT_SEPARATOR)));
    }

    public void add(List<String> t) {
        rows.add(t);
    }

    public void addAll(List<List<String>> rows) {
        this.rows.addAll(rows);
    }

    public String get(int row, int col) {
        return rows.get(row).get(col);
    }

    public List<String> getRow(int row) {
        return rows.get(row);
    }

    public List<String> getRow(int row, int fromCol) {
        return getRow(row, fromCol, rows.get(row).size());
    }

    public List<String> getRow(int row, int fromCol, int toCol) {
        return rows.get(row).subList(fromCol, toCol);
    }

    public Dataset subDataset(int fromRow, int toRow) {
        Dataset sub = new Dataset();
        sub.addAll(rows.subList(fromRow, toRow));
        return sub;
    }
}

interface Caser {
    List<Dataset> getCases();
}

class FixedCaser implements Caser {
    private Dataset dataset;
    private int caseSize;

    public FixedCaser(Dataset dataset, int caseSize) {
        this.dataset = dataset;
        this.caseSize = caseSize;
    }

    public List<Dataset> getCases() {
        List<Dataset> cases = new ArrayList<>();
        int caseCnt = toI(dataset.get(0, 0));
        for (int i = 0; i < caseCnt; i++) {
            int fromRow = i * caseSize + 1;
            int toRow = fromRow + caseSize;
            cases.add(dataset.subDataset(fromRow, toRow));
        }
        return cases;
    }
}

class FloatingCaser implements Caser {
    private Dataset dataset;

    public FloatingCaser(Dataset dataset) {
        this.dataset = dataset;
    }

    @Override
    public List<Dataset> getCases() {
        List<Dataset> cases = new ArrayList<>();
        int i = 1;
        while (i < dataset.size()) {
            int caseSize = toI(dataset.get(i, 0));
            int fromRow = i;
            int toRow = fromRow + caseSize;
            cases.add(dataset.subDataset(fromRow, toRow));
        }
        return cases;
    }
}


class Converter {
    public static Integer toI(String s) {
        return Integer.valueOf(s);
    }

    public static Long toL(String s) {
        return Long.valueOf(s);
    }

    public static Double toD(String s) {
        return Double.valueOf(s);
    }

    public static List<Integer> toIL(String s) {
        return s.chars().map(c -> c - '0').boxed().collect(Collectors.toList());
    }

    public static List<Integer> toIL(List<String> list) {
        return list.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    public static List<Long> toLL(List<String> list) {
        return list.stream().map(Long::valueOf).collect(Collectors.toList());
    }

    public static List<Double> toDL(List<String> list) {
        return list.stream().map(Double::valueOf).collect(Collectors.toList());
    }
}