package main.java.hw3;

/**
 * Interface for saving guitar inventory data.
 * @author Jack Frampton
 */
public interface SaverInterface {
    /**
     * Write the next piece of data.
     * @param data the data value to write
     * @return true if write was successful, false otherwise
     */
    boolean writeNext(String data);

    /**
     * Close the saver.
     * @return true if close was successful, false otherwise
     */
    boolean closeSaver();
}