package src;

import java.util.ArrayList;
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
        String motif = "";
        String sequence = "";

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
        choice = scnr.nextInt();
        scnr.nextLine(); // newline buffer clear blah 

        // (2.2) Get the stuff from the validTitleSeqs (index 0 is title, 1 is seq)
        title = validTitleSeqs.get(choice).get(0);
        sequence = validTitleSeqs.get(choice).get(1);

        }
    }
}