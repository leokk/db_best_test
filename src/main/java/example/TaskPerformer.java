package example;

import example.Utils.Constant;
import example.DB.H2DataBase;
import example.Utils.FileManager;
import example.algorithm.Deykstra;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskPerformer {
    private List<List<Integer>> pipeList;
    private List<List<Integer>> routeList;
    private List<String>results = new ArrayList<>();
    private String dbPath;
    private int[][] pipeMatrix;
    private int node;


    public TaskPerformer(String pipePath, String routePath, String dbPath) {
        this.pipeList = FileManager.readFile(pipePath);;
        this.routeList = FileManager.readFile(routePath);
        this.dbPath = dbPath;
        node = getMaxNode();
        this.pipeMatrix = new int[node][node];
        setMatrix();
    }

    private int getMaxNode(){
        int max = 0;
        for (List<Integer> integers : pipeList)
            for (int j = 0; j < 2; ++j) {
                if (integers.get(j) > max)
                    max = integers.get(j);
            }
        return max;
    }

//    make matrix values 0-1
    private void setMatrix(){
        Arrays.stream(pipeMatrix).forEach(a -> Arrays.fill(a, 0));
        //        set value to 1 if pipe exists
        for (List<Integer> pipes : pipeList){
            pipeMatrix[pipes.get(0) - 1][pipes.get(1) - 1] = pipes.get(2);
        }
    }

    public void generateFiles(){
        Deykstra deykstra = new Deykstra();
        results.add("ROUTE EXISTS;MIN LENGTH\n");

        for(List<Integer> arrays:routeList){
            int deykstraResult = deykstra.init(pipeMatrix,arrays.get(0) - 1,arrays.get(1) - 1, node );
            if(deykstraResult!=Integer.MAX_VALUE)
                results.add("TRUE;"+deykstraResult + "\n");
            else
                results.add("FALSE;\n");
        }
        FileManager.writeFile(results);

        //write sql
        H2DataBase dataBase = new H2DataBase(dbPath);
        try {
            dataBase.writeSql(pipeList, Constant.pipeListInsertQuery);
            dataBase.writeSql(routeList,Constant.routeListInsertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

}
