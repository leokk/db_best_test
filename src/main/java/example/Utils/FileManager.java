package example.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public static List<List<Integer>> readFile(String filePath){
//        "src/sample/pipe.csv"
        List<List<Integer>>pipeList = new ArrayList<>();
        try {
            File pipeFile = new File(filePath);
            Scanner myReader = new Scanner(pipeFile);

//          ignore first string
            if(myReader.hasNextLine())
                myReader.nextLine();

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                List<Integer> temp = new ArrayList<>();
                for (String retval : line.split(";")){
                    temp.add(Integer.parseInt(retval));
                }
                pipeList.add(temp);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return pipeList;
    }

    public static void writeFile(List<String> result){
        try {
            FileWriter myWriter = new FileWriter("result.csv");
            for(String str:result)
                myWriter.write(str);

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
