package railway.support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class ReadCSV {

    private static String split = ";";

    /**
     * 
     * @param fileName
     * @return
     */
    public static Map<String, List<Integer>> readCSV(String fileName) {
        Map<String, List<Integer>> disp = new HashMap<String, List<Integer>>();
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            file = new File(fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            // File Read
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] fields = linea.split(split);
                List<Integer> identifiers = new ArrayList<Integer>();
                for (int i = 1; i < fields.length; i++) {
                    identifiers.add(Integer.parseInt(fields[i]));
                }
                disp.put(fields[0], identifiers);
            }
        } catch (Exception e) {
            e.printStackTrace();
            disp = null;
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return disp;
    }
}