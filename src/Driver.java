package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Driver {
    public static void main(String[] args) {

        try(Scanner scnr = new Scanner(System.in)) {

            // Variables and data structures
            ArrayList<ArrayList<String>> validTitleSeqs = new ArrayList<>();
            int choice = -1;
            String title = ""; // These title, motif, sequence are to be chosen by the user
            ArrayList<String> motifArrayList = new ArrayList<>();
            String motif = "";
            String sequence = "";
            boolean flag = true;

            // (1.1) Create helpers, threads, etc. for reading input file
            BlockingQueue<ArrayList<ArrayList<String>>> blockingQueue = new LinkedBlockingDeque<>(2); // Concurrent queue for threads, size 2 might be too small I was guessing
            DNAFileHelper fileHelper = new DNAFileHelper(blockingQueue, scnr); // This is a thread!

            // (1.2) Start and wait for the DNA file-reader thread
            fileHelper.start();
            try { fileHelper.join(); } catch (InterruptedException e) { e.printStackTrace(); }

            // (1.3) fileHelper thread should have given data to the blockingQueue
            try { validTitleSeqs = blockingQueue.take(); } catch (InterruptedException e) { e.printStackTrace(); }

            // (2.1) Ask the user to choose which sequence to analyze
            System.out.print(">>> Enter the [#] number of your selected sequence to analyze: ");
            while(flag){
                choice = scnr.nextInt();
                scnr.nextLine(); // newline buffer clear blah 
                if(choice < 0 || choice >= validTitleSeqs.size())
                    System.out.print(">>> Invalid choice. Enter a valid sequence number: ");
                else flag = false;
            }
            flag = true;

            // (2.2) Get the stuff from the validTitleSeqs (index 0 is title, 1 is seq)
            title = validTitleSeqs.get(choice).get(0);
            sequence = validTitleSeqs.get(choice).get(1);

            // (2.3) Echo the user's choice
            System.out.println("\nSelected sequence: #" + choice);
            System.out.println("Title: " + title);
            System.out.println("Sequence: " + sequence);

            // (3.1) Ask the user for a motif(s) to search for
            int sequenceTypeCode = DNAFileHelper.isDNAOrProtien(sequence);
            System.out.print("\n>>> Enter the motif to search for: ");

            //ANDREW: added exit condition for this while loop
            boolean whileCondition = true;
            while(whileCondition){ // outer loop for multiple motifs
                while(flag){ // inner loop for data validation
                    motif = scnr.nextLine();
                    int motifTypeCode = DNAFileHelper.isDNAOrProtien(motif); // -1 is invalid, 0 is Dna, 1 is Protein
                    if (motifTypeCode == -1)
                        System.out.print(">>> Invalid motif. Enter a valid motif: ");
                    else if (motifTypeCode == 1 && motifTypeCode != sequenceTypeCode) // user put in a protein motif but the sequence is dna
                        System.out.print(">>> Selected sequence is of a protein, enter a protien motif: "); 
                    else if (motifTypeCode == 0 && motifTypeCode != sequenceTypeCode) // user but in a dna motif but the sequence is protein
                        System.out.print(">>> Selected sequence is DNA, enter a DNA motif: ");
                    else flag = false; // valid input, exit the loop
                }
                flag = true; // remember to reset it for later
                motifArrayList.add(motif); // Add motif to list (for multi-motif searches)

                // (3.2) Echo all the enetered motifs
                System.out.println("Entered motifs so far: " + motifArrayList);

                // (3.3) Ask user if they want to add another motif
                System.out.print("\n\n>>> Do you want to add another motif? (y/n): ");
                while(flag){
                    String yn = scnr.nextLine();
                    if(yn.equalsIgnoreCase("y")){
                        flag = false; // exit inner loop to add another motif
                        System.out.print("\n>>> Enter the motif to search for: ");
                    }
                    else if(yn.equalsIgnoreCase("n")){
                        flag = false; // exit inner loop
                        whileCondition = false;
                        break; // !!! This breaks the outer while(true) loop
                    }
                    else System.out.print("\n>>> Invalid input. Do you want to add another motif? (y/n): ");
                }

                flag = true;
                
            }
            System.out.println("\n\n===========================================================================================");
            
            System.out.println("Report: ");
            for(int i = 0; i< motifArrayList.size(); i++){
                List<Integer> positions = new ArrayList<>();
                int pos = sequence.indexOf(motifArrayList.get(i));
                
                //find all instances of the motif in the sequence
                while (pos != -1) { //when there are no more instances of the motif, .indexOf() will return -1
                    positions.add(pos);
                    pos = sequence.indexOf(motifArrayList.get(i), pos + 1);
                }
                System.out.println("\n\nMotif #" + (i + 1) + ": " + motifArrayList.get(i));
                System.out.println("Number of Appearances: " + positions.size());
                System.out.println("Positions: ");
                System.out.println(positions + "\n");

                int prevPos = 0;
                //print out the sequence with each instance of the current motif highlighted
                for( int u = 0; u < positions.size(); u++){
                    String sub = sequence.substring(prevPos, positions.get(u));
                    System.out.print(sub + "\033[47m" + motifArrayList.get(i) + "\033[0m"); //the funky looking expressions are to signal start highlighting and stop highlighting
                    if(u < positions.size()-1)
                        if(positions.get(u + 1) - positions.get(u) >= motifArrayList.get(i).length() ){
                            prevPos = positions.get(u) + motifArrayList.get(i).length();
                        }
                }
            }
        }
    } 
}