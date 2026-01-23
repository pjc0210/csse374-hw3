package main.java.hw3;

import java.util.ArrayList;

/**
 * Adapter class that adapts JsonLoader to the LoaderInterface.
 * @author Pei-Jen Chen
 */
public class JsonLoaderAdapter implements LoaderInterface{
    private JsonLoader jsonLoader;
    private int currIndex;
    private ArrayList<String> guitarDataList;

    public JsonLoaderAdapter(String filename, String directoryName) {
        this.jsonLoader = new JsonLoader(filename, directoryName);
        this.currIndex = 0;
        this.guitarDataList = this.jsonLoader.getGuitarDataList();
    }

    /**
     * Check if there is a next guitar data entry.
     * @return true if there is a next entry, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return this.currIndex < this.guitarDataList.size();
    }

    /**
     * Get the next guitar data entry.
     * @return the next guitar data as a String.
     */
    @Override
    public String next() {
        if (!hasNext()) {
            return null;
        };
        return this.guitarDataList.get(currIndex++);
    }
}
