package ralkie.gcj;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IO {

    public static final String DEFAULT_IN = "in";
    public static final String DEFAULT_OUT = "out";

    public static Data read(String filename) throws IOException {
        Data data = new Data();
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename))){
            String line;
            while ((line = reader.readLine()) != null)
                data.add(line);
        }
        return data;
    }

    public static Data read() throws IOException {
        return read(DEFAULT_IN);
    }

    public static void write(String filename, List<String> results){

    }

    public static void write(List<String> results){
        write(DEFAULT_OUT, results);
    }
}

class Data {
    public static final String DEFAULT_SEPARATOR = " ";
    List<List<String>> data = new ArrayList<>();

    public int size(){
        return data.size();
    }

    public void add(String s){
        data.add(Arrays.asList(s.split(DEFAULT_SEPARATOR)));
    }

    public void add(List<String> t){
        data.add(t);
    }

    public String get(int row, int col){
        return data.get(row).get(col);
    }

    public List<String> getAll(int row){
        return data.get(row);
    }

    public List<String> getAll(int row, int fromCol) {
        return getAll(row, fromCol, data.get(row).size());
    }

    public List<String> getAll(int row, int fromCol, int toCol){
        return data.get(row).subList(fromCol, toCol);
    }

}

interface Caser{
    List<Data> getCases();
}

class FixedCaser implements Caser{
    private Data all;
    private int caseSize;
    public FixedCaser(Data all, int caseSize) {
        this.all = all;
        this.caseSize = caseSize;
    }

    public List<Data> getCases(){
        return null; //todo
    }
}

class FloatingCaser implements Caser{
    private Data all;

    public FloatingCaser(Data all) {
        this.all = all;
    }

    @Override
    public List<Data> getCases() {
        return null; //todo
    }
}


class Converter {
    public static Integer toI(String s){
        return Integer.valueOf(s);
    }

    public static Double toD(String s){
        return Double.valueOf(s);
    }

    public static List<Integer> toIL(String s) {
        return s.chars().map(c -> c - '0').boxed().collect(Collectors.toList());
    }

    public static List<Integer> toIL(List<String> list) {
        return list.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    public static List<Double> toDL(List<String> list) {
        return list.stream().map(Double::valueOf).collect(Collectors.toList());
    }
}