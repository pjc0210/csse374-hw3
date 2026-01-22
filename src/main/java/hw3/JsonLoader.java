package main.java.hw3;

import jakarta.json.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonLoader {
    private final ArrayList<String> guitarDataList;

    public JsonLoader(String filename, String directoryName) {
        guitarDataList = new ArrayList<String>();
        loadJsonData(filename, directoryName);
    }

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

    public ArrayList<String> getGuitarDataList() {
        return this.guitarDataList;
    }
}
