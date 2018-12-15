import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.*;

/**
 * Lab is a class that reads in a txt.file containing one or more adjacency 
 * matrix/matrices and the number of node each matrix has. The matrix is then
 * stored in a 2D array. The program uses a recursive function to find all paths
 * without a repeating node.
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
   * Loops through the dimension of the matrix and add ListNodes of two connecting
   * nodes to the LinkedList. 
   */
   private LinkedList matrixToLinkedList (int[][] matrix) {
     LinkedList edges = new LinkedList();
     for (int i=0; i< matrix.length; i++){
       for (int j=0; j< matrix[i].length; j++){
         if (matrix[i][j] != 0){
           edges.AddNode(i, j);
         }
       }
     }
     return edges;
   }


  /**
   * Main entry of the program.
   * Holds two commands: the input filename and output filename.
   * It reads in and pass the txt file to be stored as a 2D array
   * The output echoes the matrix and all the searched paths
   */ 
  public static void main (String args[]) throws IOException {
    long startTime = System.nanoTime();
    Scanner sc;
    FileWriter outputStream = null;
    int nodes;
    int[][] values;
    Lab lab;
    lab = new Lab();
    LinkedList list;
    ArrayList<Integer> pathlist = new ArrayList<>(); 

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
                  }outputStream.write("\r\n");
               }
             LinkedList edges = lab.matrixToLinkedList(values);
             edges.search(outputStream, nodes, edges, pathlist);
             
         }
         } 
        }catch (Exception ioe) {
          System.err.println(ioe.toString());
          return;
         }finally{
           if (outputStream != null) outputStream.close();
long endTime   = System.nanoTime();
long totalTime = endTime - startTime;
System.out.println(totalTime);
            }
  }
}
