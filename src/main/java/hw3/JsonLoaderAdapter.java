package main.java.hw3;

import java.util.ArrayList;

public class JsonLoaderAdapter implements LoaderInterface{
    private JsonLoader jsonLoader;
    private int currIndex;
    private ArrayList<String> guitarDataList;

    public JsonLoaderAdapter(String filename, String directoryName) {
        this.jsonLoader = new JsonLoader(filename, directoryName);
        this.currIndex = 0;
        this.guitarDataList = this.jsonLoader.getGuitarDataList();
    }

    @Override
    public boolean hasNext() {
        return this.currIndex < this.guitarDataList.size();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            return null;
        };
        return this.guitarDataList.get(currIndex++);
    }
}
