// (troy) TODO: amek this into a runnable thready class, make a driver class

package src;

import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;

public class DNAFileHelper extends Thread implements Runnable {

	// Variables
	int choice; // which sequence detected by the program do you want to check, 0 for first, 1 for second, etc.
	String cTitle;
	String cSeq;
	String motif;
	ArrayList<ArrayList<String>> validSeqs;
	BlockingQueue<ArrayList<ArrayList<String>>> blockingQueue; // Java is stupid and doesnt have tuples, so instead using an arraylist of arraylists (innermost ArrayList will have [title, sequence])
	Scanner input;

	DNAFileHelper(BlockingQueue<ArrayList<ArrayList<String>>> bq, Scanner scnr) {
		this.blockingQueue = bq;
		this.input = scnr;
	}

	@Override
	public void run() {
		
		int loop = 0;
		//creates an array list for our titles and DNA sequences
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> DNA_array = new ArrayList<String>();
		//loop to see if the file exists
		while(loop == 0) {
			String fasta = "";
			//ask user for file to open
			System.out.print(">>> Type the input file: ");
			String fname1 = input.next();
			input.nextLine(); // clear the newline from the buffer
			//reads the file for the DNA sequence
			try {
				File iFile = new File(fname1);
				Scanner readF = new Scanner(iFile);
				//sets current sequence to blank and current title to null
				String cSeq = "";
				String cTitle = null;
				//reads from file
				while (readF.hasNextLine()) {
					//inputs text string into fasta
				    fasta = readF.nextLine();
				    //checks to see if it is a title string
				    if(fasta.startsWith(">")) {
				    	//if we reach the next title, save the current title and DNA sequence to the arrays
				    	if(cTitle != null) {
				    		title.add(cTitle);
				    		DNA_array.add(cSeq);
				    		//resets current DNA sequence to blank so we don't accidentally add the previous DNA sequence to the new sequence
				    		cSeq = "";
				    	}
				    	//set the title to be the current title
				    	cTitle = fasta;
				    } else if(!fasta.isEmpty()) {
				    	//if its not a title then we make the DNA sequence into one continuous upper case string
				    	cSeq += fasta.toUpperCase();
				    }
				}
				//outside of the while loop, we add the title and DNA sequence to the list
				if (cTitle != null) {
			    	title.add(cTitle);
		    		DNA_array.add(cSeq);
			    }
				//closes file
				readF.close();
				loop = 1;
			      
			} catch (FileNotFoundException e) {
				//error if file doesn't exist
				System.out.println("Wrong file, try again.\n(If needed add '.txt' to the end of the file you are looking for.)");
				System.out.println("(Example: file1.txt)");
			}
		}
		System.out.println("\n===================================\n");
		//calls on valid method to check and see if it is a DNA sequence or a protein Sequence or neither
		
		 // TROY: passes back the valid sequences
		validSeqs = new ArrayList<>(valid(title, DNA_array));
		// TROY: puts the valid sequences into the blocking queue
		try { blockingQueue.put(validSeqs); } catch (InterruptedException e) { e.printStackTrace(); }

		// TROY: deprecated, I'm moving this to a Helper class in the main
		/*
		System.out.println("Choose which sequence you to check the motif for:\n[I.E. type 0 for the first sequence, 1 for the second, etc.]");
		choice = input.nextInt();
		cTitle = title.get(choice);
		cSeq = DNA_array.get(choice);
		System.out.println("Type in the motif");
		motif = input.next();
		*/
	}

	private static ArrayList<ArrayList<String>> valid(ArrayList<String> title, ArrayList<String> DNA_array) {

		// TROY: my fake tuple to return the rest of the application
		ArrayList<ArrayList<String>> validTitleSeqs = new ArrayList<>();

		int display = 0;
		//goes through the list to check to see if protein or DNA or neither
		for(int i = 0; i < title.size(); i++) {
			//current title
			String cTitle = title.get(i);
			//current sequence
			String cSeq = DNA_array.get(i);
			//using regex to check and see if its a DNA
			boolean maybeDNA = cSeq.matches("[ACGT]+");
			//using regex to check and see if its a protein
			boolean maybeProtein = cSeq.matches("[ACDEFGHIKLMNPQRSTVWY]+");
			//prints out current title
			System.out.println("[" + display + "] " + cTitle);
			//says which of the following it is
			if(maybeDNA == true) {
				System.out.println("Letter sequence is DNA");
			} else if(maybeProtein ==  true) {
				System.out.println("Letter sequence is Protein");
			} else {
				System.out.println("Letter sequence is invalid");
			}
			//prints out the DNA sequence
			System.out.println(cSeq);
			System.out.println();
			display++;

			// TROY: adds the title and cSeq to the tuple arraylist
			ArrayList<String> pair = new ArrayList<String>();
			pair.add(cTitle);
			pair.add(cSeq);
			validTitleSeqs.add(pair);
		}
		return validTitleSeqs;
	}

	// Quick way to verify DNA/protein for internal stuff
	public static int isDNAOrProtien(String seq) {
		if (seq.isEmpty()) return -1; // Simpler than doing a try/catch
		else if (seq.matches("[ACGT]+")) return 0;
		else if (seq.matches("[ACDEFGHIKLMNPQRSTVWY]+")) return 1;
		else return -1;
	}

}
