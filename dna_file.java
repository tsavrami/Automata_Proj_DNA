import java.io.*;
import java.util.*;

public class dna_file {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		int loop = 0;
		ArrayList<String> DNA_array = new ArrayList<String>();
		//loop to see if the file exists
		while(loop == 0) {
			String DNA = "";
			//ask user for file to open
			System.out.println("Type the input file: ");
			String fname1 = input.next();
			//reads the file for the DNA sequence
			try {
				File iFile = new File(fname1);
				Scanner readF = new Scanner(iFile);
				//count to display number of DNA sequences in file
				int count = 1;
				//reads from file
				while (readF.hasNextLine()) {
					//string goes into string DNA
				    DNA = readF.nextLine();
				    boolean result = DNA.matches("[ACGT]+");
				    //if string is valid then DNA string that can turn into mRNA, if not then it will let the user know its not valid
				    if (DNA.length() % 3 != 0 || !result) {
				        System.out.println("[" + count + "] "+ DNA + " Is not a valid DNA string that will turn into a mRNA.");
				    }else {
				    	System.out.println("[" + count + "] "+ DNA + " Is a valid DNA string that will turn into a mRNA.");
				    	//places the valid DNA strings into a array list
				    	DNA_array.add(DNA);
				    }
				    count++;
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
		System.out.println(DNA_array);

	}

}
