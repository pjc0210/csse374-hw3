package main.java.hw3;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonWriter;
import jakarta.json.JsonWriterFactory;
import jakarta.json.stream.JsonGenerator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Adapter class that adapts JsonSaver to the SaverInterface.
 * @author Jack Frampton
 */

public class JsonSaver {
    private String filePath;
    private JsonArrayBuilder inventoryArrayBuilder;

    public JsonSaver(String dataFileName, String directoryName) {
        this.filePath = directoryName + dataFileName;
        this.inventoryArrayBuilder = Json.createArrayBuilder();
    }

    /**
     * Add a guitar to the JSON inventory.
     */
    public void addGuitar(String serialNumber, String price, String builder,
                          String model, String guitarType, String numStrings,
                          String backWood, String topWood) {
        JsonObjectBuilder guitarBuilder = Json.createObjectBuilder()
                .add("serialNumber", serialNumber)
                .add("price", Double.parseDouble(price))
                .add("builder", builder)
                .add("model", model)
                .add("guitarType", guitarType)
                .add("numStrings", Integer.parseInt(numStrings))
                .add("backWood", backWood)
                .add("topWood", topWood);

        inventoryArrayBuilder.add(guitarBuilder);
    }

    /**
     * Write all collected guitar data to the JSON file.
     */
    public boolean writeToFile() {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            Map<String, Object> config = new HashMap<>();
            config.put(JsonGenerator.PRETTY_PRINTING, true);
            JsonWriterFactory writerFactory = Json.createWriterFactory(config);

            JsonObjectBuilder rootBuilder = Json.createObjectBuilder()
                    .add("inventory", inventoryArrayBuilder);

            try (JsonWriter jsonWriter = writerFactory.createWriter(outputStream)) {
                jsonWriter.writeObject(rootBuilder.build());
            }
            return true;
        } catch (IOException e) {
            System.out.println(">>Error writing JSON file: " + e.getMessage());
            return false;
        }
    }
}