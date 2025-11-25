/**
 * Simple class to represent a table of motifs
 */

package src.tables;
import java.util.ArrayList;

import src.motifs.*;

public class Table {

    // Data structure(s)
    ArrayList<Motif> motifTable;

    // No argument constructor
    public Table() {
        motifTable = new ArrayList<Motif>();
    }

    private void populateTable() {
        // To be overridden by child classes
    }

}
