import java.io.File;
import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

public class Main {

    public static void main(String[] args) throws Exception {
        File file = new File("e:\\read.csv");
        FileReader fReader = new FileReader(file);
        CSVReader csvReader = new CSVReader(fReader, ',');
        String[] strs = csvReader.readNext();
        if(strs != null && strs.length > 0){
            for(String str : strs)
                if(null != str && !str.equals(""))
                    System.out.print(str + " , ");
            System.out.println("\n---------------");
        }
        List<String[]> list = csvReader.readAll();
        for(String[] ss : list){
            for(String s : ss)
                if(null != s && !s.equals(""))
                    System.out.print(s + " , ");
            System.out.println();
        }
        csvReader.close();
    }
}
