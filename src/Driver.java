package src;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Driver {
    
public static void main(String[] args) {

    // Variables and data structures
    ArrayList<String> validSeqs = new ArrayList<>();

    // (1.1) Create helpers, threads, etc. for reading input file
    BlockingQueue<ArrayList<String>> blockingQueue = new LinkedBlockingDeque<>(2); // Concurrent queue for threads, size 2 might be too small I was guessing
    DNAFileHelper fileHelper = new DNAFileHelper(blockingQueue); // This is a thread!

    // (1.2) Start and wait for the DNA file-reader thread
    fileHelper.start();
    try { fileHelper.join(); } catch (InterruptedException e) { e.printStackTrace(); }
    
    // (1.3) fileHelper thread should have given data to the blockingQueue
    try { validSeqs = blockingQueue.take(); } catch (InterruptedException e) { e.printStackTrace(); }

    // (2.1) Create either a DNA/Protein Helper based on which is needed
    System.out.println(validSeqs);

    }
}