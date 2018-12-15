import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Lab is a class that reads in a txt.file containing one or more adjacency 
 * matrix/matrices and the number of node each matrix has. The matrix is then
 * stored in a 2D array. The program uses a recursive function to find all paths
 * without a repeating node.
 * @version 1.0 2018-10-23
 * @author Y Wan
 */
public class Lab {
   
  /** 
   *  Read the txt file and store the adjacency matrix in a 2D array 
   */
  private int[][] readMatrix(Scanner sc, Integer nodes, FileWriter output) throws IOException{
      String errorMessage= "Error in the input!";
      int rows = nodes;
      int columns = nodes;
      int [][] arr = new int[rows][columns];
      for (int i=0; i<rows; i++) {
        for (int j = 0; j < columns; ++j){
          arr[i][j] = sc.nextInt();
          if (arr[i][j] > 1){
            System.out.println(errorMessage);
            System.exit(1);
          }
        }
      } 
      return arr;
   }

  /** 
   * A recursive function that searches for all paths without a repeating node.
   * It prints out the path out put in the format of e.g. 0->1->2 
   */    
  private void search(FileWriter output, int i, int j, int[][] values, boolean[] visited, List<Integer> pathlist) throws IOException {
    if(!visited[i]){
      visited[i] = true;
      if ((i == j) && (pathlist.size()>1)){
           for (int z=0; z< pathlist.size(); z++){
              Integer element = pathlist.get(z);
            if ((z+1) < pathlist.size()){
               output.write(element+"->");
            }else{
               output.write(element+"\r\n");
             }  
           }
       }
      for(int k=0; k <values[i].length; k++ ){
        if(values[i][k] == 1 && !visited[k]){
          pathlist.add(k);
          search(output, k,j, values, visited,pathlist);
        }
     } 	
   }
 }

  /** 
   * It stores the variables:
   *   1. visited: the boolean array to keep track of whether a node is visited or not.
   *   2. pathlist: the arraylist that stores the path from one node to another.
   * It calls the recursive function search.
   */
  public void printpaths(FileWriter output, int s, int d, int nodes, int[][] values) throws IOException { 
        boolean[] visited = new boolean[nodes]; 
        ArrayList<Integer> pathlist = new ArrayList<>(); 
        pathlist.add(s); 
        search(output, s, d,  values, visited,pathlist);
    } 

  /**
   * Main entry of the program.
   * Holds two commands: the input filename and output filename.
   * It reads in and pass the txt file to be stored as a 2D array
   * The output echoes the matrix and all the searched paths
   */ 
  public static void main (String args[]) throws IOException {

    Scanner sc;
    FileWriter outputStream = null;
    int nodes;
    int[][] values;
    Lab lab;
    lab = new Lab();

    // Check for two command lines arguments
    if (args.length != 2) {
            System.out.println("Usage:  java Project0 [input file pathname]" +
                " [output file pathname]");
            System.exit(1);
    }

    //  Open the files that will be used for input and output, and writes the output file.
     try{
        sc = new Scanner(new BufferedReader(new FileReader(args[0])));
        outputStream = new FileWriter(args[1]);
        outputStream.write("#######OUTPUT FILE############"+"\r\n");
        if (!sc.hasNext()){
          System.out.println("empty input");
          System.exit(1);
         }
        while (sc.hasNext()){
         nodes= sc.nextInt();
         if (nodes == 1){
          outputStream.write("##############################"+"\r\n");
          outputStream.write("matrix of "+nodes+"X"+nodes+"\r\n");
          outputStream.write("Nodes= "+nodes+"\r\n"+"Only one node. No paths.");
          return;
         }else{
         values=lab.readMatrix(sc, nodes, outputStream);
         outputStream.write("##############################"+"\r\n");
         outputStream.write("matrix of "+nodes+"X"+nodes+"\r\n");
         for (int i=0; i< nodes; i++){
          for (int j=0; j< nodes; j++){
            outputStream.write(values[i][j] + " ");
          }
           outputStream.write("\r\n");
         }outputStream.write("\r\n");
          outputStream.write("All paths without revisiting a node"+"\r\n");
          for (int i=0; i< nodes; i++){
           for (int j=0; j< nodes; j++){
            lab.printpaths(outputStream,i,j,nodes,values);
            }
           }
          }
         } 
        }catch (Exception ioe) {
          System.err.println(ioe.toString());
          return;
         }finally{
           if (outputStream != null) outputStream.close();
            }
  }
}
