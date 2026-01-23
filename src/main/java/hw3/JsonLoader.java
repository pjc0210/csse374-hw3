package main.java.hw3;

import jakarta.json.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JsonLoader class to load guitar data from a JSON file.
 * @author Pei-Jen Chen
 */
public class JsonLoader {
    private final ArrayList<String> guitarDataList;

    public JsonLoader(String filename, String directoryName) {
        guitarDataList = new ArrayList<String>();
        loadJsonData(filename, directoryName);
    }

    /**
     * Load guitar data from a JSON file.
     * @param filename
     * @param directoryName
     */
    private void loadJsonData(String filename, String directoryName) {
        String filePath = directoryName + filename;

        try (JsonReader reader = Json.createReader(new FileReader(filePath))) {
            JsonArray guitarsArray = reader.readObject().getJsonArray("inventory");

            for (JsonValue guitarValue : guitarsArray) {
                JsonObject guitarObject = guitarValue.asJsonObject();

                guitarDataList.add(guitarObject.getString("serialNumber"));
                guitarDataList.add(String.valueOf(guitarObject.getJsonNumber("price").doubleValue()));
                guitarDataList.add(guitarObject.getString("builder"));
                guitarDataList.add(guitarObject.getString("model"));
                guitarDataList.add(guitarObject.getString("guitarType"));
                guitarDataList.add(String.valueOf(guitarObject.getJsonNumber("numStrings").intValue()));
                guitarDataList.add(guitarObject.getString("backWood"));
                guitarDataList.add(guitarObject.getString("topWood"));
            }
        } catch (IOException e) {
            System.out.println(">>Error reading JSON file: " + e.getMessage());
        }
    }

    /**
     * Get the list of guitar data.
     * @return data list of guitars
     */
    public ArrayList<String> getGuitarDataList() {
        return this.guitarDataList;
    }
}
