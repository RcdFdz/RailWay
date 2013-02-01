package support;

/**
 * @version 1.0
 * @author Ricardo Fernandez Domenech
 */
public class InfoTrack {

    private int id;
    private int[] next;

    /**
     * 
     * @param fields
     */
    public InfoTrack(String[] fields) {
        id = Integer.parseInt(fields[0]);
        next = new int[Integer.parseInt(fields[1])];
        int i;
        for (i = 0; i < next.length; i++) {
            next[i] = Integer.parseInt(fields[2 + i]);
        }
    }

    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return
     */
    public int[] getNext() {
        return next;
    }
}
