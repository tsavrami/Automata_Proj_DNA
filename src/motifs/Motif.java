package src.motifs;

public class Motif {

    // Variables
    String motif; // The self-named motif, both the name and the sequence
    String result; // The associated amino acid or protein of the motif

    // No argument constructor
    public Motif() {
        String motif = "";
        String result = "";
    }

    // Parameterized constructor
    public Motif(String motif, String result) {
        this.motif = motif;
        this.result = result;
    }

    // Getters and Setters
    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

}
