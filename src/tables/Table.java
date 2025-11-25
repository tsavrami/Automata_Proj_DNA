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

    /*
    Amino acid abbreviations:
     */
    private void populateNucleotideTable() {
        motifTable.add(new NucleotideMotif("UUU", "F"));
        motifTable.add(new NucleotideMotif("UUC", "F"));
        motifTable.add(new NucleotideMotif("UUA", "L"));
        motifTable.add(new NucleotideMotif("UUG", "L"));
        motifTable.add(new NucleotideMotif("UCU", "S"));
        motifTable.add(new NucleotideMotif("UCC", "S"));
        motifTable.add(new NucleotideMotif("UCA", "S"));
        motifTable.add(new NucleotideMotif("UCG", "S"));
        motifTable.add(new NucleotideMotif("UAU", "Y"));
        motifTable.add(new NucleotideMotif("UAC", "Y"));
        motifTable.add(new NucleotideMotif("UAA", "N/A"));
        motifTable.add(new NucleotideMotif("UAG", "N/A"));
        motifTable.add(new NucleotideMotif("UGU", "C"));
        motifTable.add(new NucleotideMotif("UGC", "C"));
        motifTable.add(new NucleotideMotif("UGA", "N/A"));
        motifTable.add(new NucleotideMotif("UGG", "W"));
        motifTable.add(new NucleotideMotif("CUU", "L"));
        motifTable.add(new NucleotideMotif("CUC", "L"));
        motifTable.add(new NucleotideMotif("CUA", "L"));
        motifTable.add(new NucleotideMotif("CUG", "L"));
        motifTable.add(new NucleotideMotif("CCU", "P"));
        motifTable.add(new NucleotideMotif("CCC", "P"));
        motifTable.add(new NucleotideMotif("CCA", "P"));
        motifTable.add(new NucleotideMotif("CCG", "P"));
        motifTable.add(new NucleotideMotif("CAU", "H"));
        motifTable.add(new NucleotideMotif("CAC", "H"));
        motifTable.add(new NucleotideMotif("CAA", "Q"));
        motifTable.add(new NucleotideMotif("CAG", "Q"));
        motifTable.add(new NucleotideMotif("CGU", "R"));
        motifTable.add(new NucleotideMotif("CGC", "R"));
        motifTable.add(new NucleotideMotif("CGA", "R"));
        motifTable.add(new NucleotideMotif("CGG", "R"));
        motifTable.add(new NucleotideMotif("AUU", "I"));
        motifTable.add(new NucleotideMotif("AUC", "I"));
        motifTable.add(new NucleotideMotif("AUA", "I"));
        motifTable.add(new NucleotideMotif("AUG", "M"));
        motifTable.add(new NucleotideMotif("ACU", "T"));
        motifTable.add(new NucleotideMotif("ACC", "T"));
        motifTable.add(new NucleotideMotif("ACA", "T"));
        motifTable.add(new NucleotideMotif("ACG", "T"));
        motifTable.add(new NucleotideMotif("AAU", "N"));
        motifTable.add(new NucleotideMotif("AAC", "N"));
        motifTable.add(new NucleotideMotif("AAA", "K"));
        motifTable.add(new NucleotideMotif("AAG", "K"));
        motifTable.add(new NucleotideMotif("AGU", "S"));
        motifTable.add(new NucleotideMotif("AGC", "S"));
        motifTable.add(new NucleotideMotif("AGA", "R"));
        motifTable.add(new NucleotideMotif("AGG", "R"));
        motifTable.add(new NucleotideMotif("GUU", "V"));
        motifTable.add(new NucleotideMotif("GUC", "V"));
        motifTable.add(new NucleotideMotif("GUA", "V"));
        motifTable.add(new NucleotideMotif("GUG", "V"));
        motifTable.add(new NucleotideMotif("GCU", "A"));
        motifTable.add(new NucleotideMotif("GCC", "A"));
        motifTable.add(new NucleotideMotif("GCA", "A"));
        motifTable.add(new NucleotideMotif("GCG", "A"));
        motifTable.add(new NucleotideMotif("GAU", "D"));
        motifTable.add(new NucleotideMotif("GAC", "D"));
        motifTable.add(new NucleotideMotif("GAA", "E"));
        motifTable.add(new NucleotideMotif("GAG", "E"));
        motifTable.add(new NucleotideMotif("GGU", "G"));
        motifTable.add(new NucleotideMotif("GGC", "G"));
        motifTable.add(new NucleotideMotif("GGA", "G"));
        motifTable.add(new NucleotideMotif("GGG", "G"));
    }

}
