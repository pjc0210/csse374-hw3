package main.java.hw3;

/**
 * Adapter class that adapts JsonSaver to the SaverInterface.
 */
public class JsonSaverAdapter implements SaverInterface {

    private JsonSaver jsonSaver;
    private String[] currentGuitarFields;
    private int currentFieldIndex;
    private static final int FIELDS_PER_GUITAR = 8;

    public JsonSaverAdapter(String dataFileName, String directoryName) {
        this.jsonSaver = new JsonSaver(dataFileName, directoryName);
        this.currentGuitarFields = new String[FIELDS_PER_GUITAR];
        this.currentFieldIndex = 0;
    }

    @Override
    public boolean writeNext(String data) {
        currentGuitarFields[currentFieldIndex] = data;
        currentFieldIndex++;

        if (currentFieldIndex >= FIELDS_PER_GUITAR) {
            jsonSaver.addGuitar(
                    currentGuitarFields[0],  // serialNumber
                    currentGuitarFields[1],  // price
                    currentGuitarFields[2],  // builder
                    currentGuitarFields[3],  // model
                    currentGuitarFields[4],  // guitarType
                    currentGuitarFields[5],  // numStrings
                    currentGuitarFields[6],  // backWood
                    currentGuitarFields[7]   // topWood
            );
            currentFieldIndex = 0;
        }
        return true;
    }

    @Override
    public boolean closeSaver() {
        return jsonSaver.writeToFile();
    }
}