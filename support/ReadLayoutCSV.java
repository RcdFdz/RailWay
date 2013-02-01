package railway.support;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import support.InfoTrack;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class ReadLayoutCSV {

    private static String split = ";";

    /**
     * 
     * @param fileName
     * @return
     */
    public static List<InfoTrack> readLayoutCSV(String fileName) {
        List<InfoTrack> tracks = new LinkedList<InfoTrack>();
        File file = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            file = new File(fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            //File Read
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] fields = linea.split(split);
                if (fields.length >= 3) {
                    tracks.add(new InfoTrack(fields));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            tracks = null;
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return tracks;
    }
}