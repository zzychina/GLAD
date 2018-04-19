import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.regex.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;

import javax.script.Compilable;

public class Main {


    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\s13653\\Documents\\book.csv");
        //FileReader fReader = new FileReader(file);
        String[] s = null;
        CSVReader reader = new CSVReaderBuilder(new java.io.FileReader(file)).withCSVParser(new CSVParserBuilder()
                        .withSeparator('\t').build()).build();
        List<String[]> list = reader.readAll();
        System.out.println(list.size());
        boolean isSentence = false;
        for(int i= 0; i < list.size(); i++){
            s = list.get(i);
            int z = i + 1;
            //check point 1: language, Demestic form, Etymon, type of borrowing and Pos Should not be empty
            if(!s[0].trim().matches("Engish|Japanese|Polish") || null == s[0] || s[0].equals("")){
                System.out.println("line " + z +" is not right. The Language should be Polish but it is " + s[0]);
                isSentence = true;}

            if(null == s[1] || s[1].equals("")){
                System.out.println("line " + z +" is not right. The Domestic form should not be empty but it is " + s[1]);
                isSentence = true;}

            if(null == s[3] || s[3].equals("")){
                System.out.println("line " + z +" is not right. The Etymon should not be empty but it is " + s[3]);
                isSentence = true;}

            if(!s[5].trim().matches("unadapted|adapted|unadapted borrowing|adapted borrowing|semantic loan|loan translation|hybrid|pseudo-Anglicism|phono-semantic matching") || null == s[5] || s[5].equals(""))
            { System.out.println("line " + z +" is not right. The type of borrowing should be in unadapted borrowing|adapted borrowing|semantic loan|loan translation|hybrid|pseudo-Anglicism|phono-semantic matching but it is " + s[5]);
                isSentence = true;}

            if(!s[7].trim().matches("noun|verb|adjective|adverb|interjection|other") || null == s[7] || s[7].equals(""))
            {System.out.println("line " + z +" is not right. The POS should be in noun|verb|adjective|adverb|interjection|other but it is " + s[7]);
                isSentence = true;}

            //check point 2: Phrasemic type, gender and frequence should match the following values
            if(!s[9].trim().matches("idiom|simile|discourse marker|commonplace|proverb|slgan") && null != s[9] && !s[9].equals(""))
            {System.out.println("line " + z +" is not right. The Phrasemic type should be in idiom|simile|discourse marker|commonplace|proverb|slgan but it is " + s[9]);
               isSentence = true;}

            if(!s[10].trim().matches("m|f|n|c|m/f") && null != s[10] && !s[10].equals(""))
            {System.out.println("line " + z +" is not right. The gender type should be in m|f|n|c|m/f but it is " + s[9]);
                isSentence = true;}

            if(!s[13].trim().matches("\\*|\\*\\*|\\*\\*\\*") && null != s[13] && !s[13].equals(""))
            {System.out.println("line " + z +" is not right. The frequence type should be in *|**|*** but it is  " + s[13]);
                isSentence = true;}

            //check point 3: Domestic form variants and Compounds and derivations should be separated by comma
            Pattern pattern = Pattern.compile("[\\^%&'.()!@;=?$\\x22]+");
            if(pattern.matcher(s[2].trim()).find() && null != s[2] && !s[2].equals(""))
            {System.out.println("line " + z +" is not right. The domestic form variants should be separated by comma but it is  " + s[2]);
                isSentence = true;}

            if(pattern.matcher(s[12].trim()).find() && null != s[12] && !s[12].equals(""))
            {System.out.println("line " + z +" is not right. The domestic form variants should be separated by comma but it is  " + s[12]);
                isSentence = true;}


            for(int j = 0; j < list.get(i).length; j++){
                if(isSentence)
                    System.out.print(s[j] + "\t");
            }
            if(isSentence) {
                isSentence = false;
                System.out.println();
                System.out.println();
            }
        }

    }
}
