package edu.rit.croatia.swen383.g1.dm.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class which has the methods that LogCSV class and FoodCSV class share
 */
public abstract class CSVManager {

    // reading data from a CSV file
    public List<String[]> loadCSV(String fileName) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                records.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    // writes data to a CSV file
    public void saveCSV(String fileName, List<String> lines) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T findItemByName(String name, ArrayList<T> itemList, String typeToFind) {
        for (T item : itemList) {
            if (typeToFind.equals("e") && item instanceof Exercise && ((Exercise)item).getName().equals(name)) {
                return item;
            }
            if (typeToFind.equals("f") && item instanceof Food && ((Food)item).getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public abstract void loadEntries(String fileName);

    public abstract void saveEntries(String fileName);

    public abstract boolean isValidDataStructure(String[] parts);

}
