
import java.io.*;
import java.util.*;

public class dna_file {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int loop = 0;
		//creates an array list for our titles and DNA sequences
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> DNA_array = new ArrayList<String>();
		//loop to see if the file exists
		while(loop == 0) {
			String fasta = "";
			//ask user for file to open
			System.out.println("Type the input file: ");
			String fname1 = input.next();
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
		System.out.println("===================================");
		//calls on valid method to check and see if it is a DNA sequence or a protein Sequence or neither
		valid(title, DNA_array);

	}
	public static void valid(ArrayList<String> title, ArrayList<String> DNA_array) {
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
			System.out.println(cTitle);
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
		}
	}

}
