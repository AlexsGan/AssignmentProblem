/*
_________________________________________________________________________________________________________________________
_________________________________________________________________________________________________________________________||
“Assignment Problem” Program
Alex Gan
4/01/2018
Java, Version: Neon.3 Release (4.6.3)
_________________________________________________________________________________________________________________________
_________________________________________________________________________________________________________________________||
Problem Definition:
			Suppose you are the owner of a manufacturing company and have a staff of seven people. There are seven job 
		tasks that each person can do but each person gets paid at a different rate for each task. Your job is to 
		distribute the jobs to minimize the cost. Write a recursive solution to the problem. The input will be 7 rows by 
		8 columns. 
Input:	
			In the assignmentInput.txt file, enter a name followed by seven costs in the first line (with space between 
		each value/name entered), then repeat 6 times in the next 6 lines. 
Output:
			The program outputs the total minimum cost and the specific job that each worker gets.
Process:
			Use a recursion method to find all the possible job configurations to find the minimum total cost and its 
		specific job configuration (the 1st found).
_________________________________________________________________________________________________________________________
_________________________________________________________________________________________________________________________||
List of Identifiers:

===Global:===
	min (type int)	- the minimum cost after distributed each jobs
	SIZE (type int) - a constant, indicate the size of employees and jobs
	
	cost[][] (type int)		- stores the cost of all job costs
	jobAvb[] (type boolean)	- tells whether a job is already taken
	r[] (type String)		- stores the names of each employee according to their jobs (index of array + 1)
	f[] (type String)		- the version of r[] that gives the minimum cost
	name[] (type String)	- stores the names of each employee 
_________________________________________________________________________________________________________________________
_________________________________________________________________________________________________________________________||
*/

package assignmentProblem;

import java.io.*;			//allow access to the coding within the io library 
import java.util.Arrays;	//import util.Arrays

public class AssignmentProblem {

	final static int SIZE = 7;
	static int min = Integer.MAX_VALUE;
	static int cost[][] = new int[SIZE][SIZE];
	static boolean jobAvb[] = new boolean[SIZE];
	static String r[] = new String[SIZE];
	static String f[] = new String[SIZE];
	static String name[] = new String[SIZE];

	/**main method
	 * This procedural method which is called automatically calls 4 methods:
	 *  1.intro  2.input 3.calculate 4.output
	 * 
	 * @param args
	 * @throws IOException
	 * 
	 * @return void
	 */
	public static void main(String[] args) throws IOException {
		intro();			//call intro method
		input();			//call input method
		calculate(0, 0);	//call calculate method
		output();			//call output method
	}// end main method
	
	/**intro method
	 * This procedural method describes the problem that the program will solve, and
	 * also tells the user how they can input from a file
	 * 
	 * @return void
	 */
	public static void intro(){
		System.out.println("			Assignment Problem Program\n				by Alex Gan\n\n"
				+ "	You are the owner of a manufacturing company and have a staff of 7 people.\n"
				+ "There are 7 job tasks that each person can do but each person gets paid at a different rate for each task.\n"
				+ "This program uses recursion to distribute the jobs (1 job per person) to minimize the cost.\n"
				+ "The input will be 7 rows by 8 columns (a name followed by 7 costs) from the assignmentInput.txt file.\n");
	}//end intro method

	/**input method
	 * this procedural method reads the employee names and their respective
	 * job costs from the text file and copy them to the program's arrays
	 * 
	 * @throws IOException
	 * 
	 * @return void
	 */
	public static void input() throws IOException {
		System.out.println("\n		    Job Cost ($)\n");
		
		FileReader fr = new FileReader("assignment1/assignmentInput.txt");//declare & instantiate FileReader
		BufferedReader br = new BufferedReader(fr);					//declare & instantiate BufferedReader

		String line = null;
		int numWord = 0, index = 0;

		while ((line = br.readLine()) != null && index < SIZE) {
			String[] temp = line.split(" ");
			if (SIZE/(index+1)==2)
				System.out.print("   Employee:  ");
			else
				System.out.print("	      ");
			for (byte i = 0; i < temp.length; i++) {
				if (i == 0)
					name[index] = temp[i];//set the first term in the line to name[index]
				else {
					cost[index][numWord] = Integer.parseInt(temp[i]);//set the cost to cost[][] array
					numWord++;//increases numWord by 1
				}
				System.out.print(temp[i] + "  ");//prints out a name or a job cost
			}//end for loop
			System.out.println("\n");
			numWord = 0;
			index++;//index increments by 1
		} // end while loop
		
		Arrays.fill(jobAvb, true);
		br.close();
	}// end outputFile method

	/**calculate method
	 * this procedural method uses recursion and backtracking to find the job distribution along
	 * with the minimum cost which came from this particular distribution
	 * 
	 * @param person - tells which person is currently doing the job <type integer> 
	 * @param sum - adds up the costs of each job in each different combination
	 * 
	 * @return void
	 */
	public static void calculate(int person, int sum) {
		if (person == SIZE && sum < min) {
			min = sum;
			for (byte i = 0; i < SIZE; i++) 
				f[i] = r[i];//set all values of f[] equal to r[]
		}//end if statement
		for (byte i = 0; i < SIZE; i++) {			
			if (jobAvb[i] == true) {//if the job is available
				jobAvb[i] = false;	//set jobAvb to false
				r[i] = name[person];//stores the name of the person into r[i] with i+1 being its job number
				calculate(person + 1, sum + cost[person][i]);//call calculate method (recursion)
				jobAvb[i] = true;//set jobAvb back to true
			}//end if statement for if job is available
		} // end for loop

	}// end calculate method

	/**output method
	 * this procedural method prints out the minimum cost and the most 
	 * ideal job distribution
	 * 
	 * @return void
	 */
	public static void output() {
		System.out.println("minimum cost: " + min+"\n");
		System.out.println("The jobs should be distributed as:\n");
		for (byte i = 0; i < SIZE; i++) {
			System.out.println(" "+f[i] + " should take job " + (i + 1));//outputs who should take which job
		}
	}// end output method

}//end AssignmentProblem class
