package example.algorithm;

public class Deykstra {
    private int[] pos = new int[7];
    private boolean[] node = new boolean[7];
    private int indexMin = 0;



    public int init(int[][] matrix, int start, int finish, int nodes) {

        int bigNum = Integer.MAX_VALUE;
        for (int i = 0; i < nodes; ++i) {
            pos[i] = bigNum;       // fill the path to node with big int
            node[i] = false;       // all nodes are unseen
        }
        pos[start] = 0;

        for (int i = 0; i < nodes - 1; ++i) {    // main loop
            int min = bigNum;
            for (int j = 0; j < 7; ++j) {     // find the node with minimal distance 
                if (!node[j] && pos[j] < min) {
                    min = pos[j];
                    indexMin = j;
                }
            }
            node[indexMin] = true;   // mark selected node as passed
            for (int j = 0; j < nodes; ++j) {   // add weight
                if (!node[j] && matrix[indexMin][j] > 0 && pos[indexMin] != bigNum && pos[indexMin] + matrix[indexMin][j] < pos[j]) {
                    pos[j] = pos[indexMin] + matrix[indexMin][j];
                }
                // if the node is not passed and it is adjacent to the selected one and if the sum of the weight
                // of the selected vertex and the edge to the current one will be less than
            } // weight of the current one, then - change the value of the weight of the current vertex.
        }
        for(int i=0; i<nodes; ++i)
            System.out.print(pos[i]+"\t");
        System.out.println();
        return pos[finish];
    }
}