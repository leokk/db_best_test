package example.Utils;

public class Constant {
    public final static StringBuilder pipeListInsertQuery =  new StringBuilder("INSERT INTO \"PUBLIC\".\"Pipe\" " +
            "(\"idX\", \"idY\",\"LENGTH\") VALUES(");
    public final static StringBuilder routeListInsertQuery =  new StringBuilder("INSERT INTO \"PUBLIC\".\"Route\"" +
            "(\"idA\", \"idB\") VALUES(");
}
