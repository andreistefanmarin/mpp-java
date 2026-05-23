package eu.ase.iosjon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class ProgMainIOJson {
    public static void buildAndWriteJSON(String fileName) throws JSONException, IOException {
        JSONObject dataset = new JSONObject();
        dataset.put("node-ipv4", "10.2.67.96");
        dataset.put("node-mac", "E4:F6:A8");
        dataset.put("OIDS", new JSONArray());

        JSONObject oid0 = new JSONObject();
        oid0.put("1.6.3.5.1", "4800");
        JSONObject oid1 = new JSONObject();
        oid1.put("1.6.3.5.2", "RHEL 6");

        dataset.append("OIDs", oid0);
        dataset.append("OIDs", oid1);

        System.out.println("Write: " + JSONObject.quote(dataset.toString()));

        FileWriter fw = new FileWriter(fileName);
        fw.write(dataset.toString());
        fw.close();
    }

    public static void readAndParseJSON(String fileName) throws IOException, JSONException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        reader.close();
        String myNodeJSON = stringBuilder.toString();
        JSONObject json = new JSONObject(myNodeJSON);

        System.out.println("Read node-ipv4 = " + json.get("node-ipv4"));
        JSONArray oidsArray = (JSONArray) json.get("OIDs");
        System.out.println("Read - oid[1] = " + oidsArray.get(1));
    }

    public static void main(String[] args) {
        try {
            buildAndWriteJSON("myNodeJSONObject.json");
            readAndParseJSON("myNodeJSONObject.json");
        } catch (JSONException io) {
            io.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
