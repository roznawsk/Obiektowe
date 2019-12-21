package World;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {

    private FileWriter file;
    private JSONArray list = new JSONArray();
    private int currentNum = 1;

    public JSONWriter(){
        try{
            file = new FileWriter("survivorsGenomes.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLn(String line) {
        list.add(line);
    }

    public void save(){
        try{
            file.write(list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
