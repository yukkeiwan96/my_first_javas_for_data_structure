import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

/**MergeSortLI is a linked-list implementation of merge sort.
 * Modified from https://www.geeksforgeeks.org/merge-sort/
 * It uses Nodes.class
 */
public class MergeSortLI {
 
    public Node head = null; 
    // node a,b; 
    static class Node{ 
        int data; 
        Node next; 
        public Node(int data){ 
            this.data = data; 
        } 
        public int getData() {
            return data;
        }
        public void setData(int data) {
            this.data = data;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
             this.next = next;
        } 
     }
 
 
public Node mergeSort(Node startNode){
    if(startNode==null || startNode.getNext()==null){
      return startNode;
     }
    Node middle = getMiddle(startNode);
    Node nextOfMiddle = middle.getNext();
    middle.setNext(null);
    Node left = mergeSort(startNode);
    Node right = mergeSort(nextOfMiddle);
    Node sortedList = mergeTwoListIterative(left, right);
    return sortedList;
    }
 

public Node mergeTwoListIterative(Node leftStart, Node rightStart) {
    Node merged=null;
    Node temp=null;
    Node lastAddedNode = null;
    while(leftStart!=null && rightStart!=null){
    if(leftStart.getData()>rightStart.getData()){
      temp = new Node(rightStart.getData());
      rightStart=rightStart.getNext();
    }else{
      temp = new Node(leftStart.getData());
      leftStart=leftStart.getNext();
    }
    if(merged==null){
      merged=temp;
    }else{
      lastAddedNode.setNext(temp);     
    }
    lastAddedNode=temp;
  }
   if(leftStart!=null){
    lastAddedNode.setNext(leftStart);
   }else{
    lastAddedNode.setNext(rightStart);
   }
   return merged;
 }
 
public Node getMiddle(Node startNode) {
   if(startNode==null){
    return startNode;
   }
   Node pointer1=startNode;
   Node pointer2=startNode;
   while(pointer2!=null && pointer2.getNext()!=null && pointer2.getNext().getNext()!=null){
    pointer1 = pointer1.getNext();
    pointer2 = pointer2.getNext().getNext();
   }
   return pointer1;
 }

public void push(int new_data){ 
        Node new_node = new Node(new_data); 
        new_node.next = head; 
        head = new_node; 
    } 

public void printList(Node headref, FileWriter outputStream) throws IOException{ 
        while (headref != null){ 
            outputStream.write(headref.data + " "); 
            headref = headref.next; 
        } 
    } 
      
    public static void main(String[] args) throws IOException{ 
  
        MergeSortLI ms = new MergeSortLI(); 
        Scanner sc;
        FileWriter outputStream = null;
        int n;
        // Check for two command lines arguments
        if (args.length != 2) {
            System.out.println("Usage:  java MergeSort [input file pathname]" +
                " [output file pathname]");
            System.exit(1);
         }
        try{
        sc = new Scanner(new BufferedReader(new FileReader(args[0])));
        outputStream = new FileWriter(args[1]);
        outputStream.write("#######OUTPUT FILE############"+"\r\n");
        if (!sc.hasNext()){
          System.out.println("empty input");
          System.exit(1);
         }
        String line= sc.nextLine();
        String[] ln= line.split("\\s+");
        int[] nums= new int[ln.length];
        int le= ln.length;
        for (int i = 0; i < ln.length; i++) {
         String ns = ln[i];
         ms.push(Integer.parseInt(ns));
        }
        outputStream.write("Linked-Implemented Merge Sort."+"\r\n");
        long startTimed = System.nanoTime();
        ms.head = ms.mergeSort(ms.head); 
        long endTimed   = System.nanoTime();
        long totalTimed = endTimed - startTimed;
        outputStream.write("sorted array"+"\r\n");
        ms.printList(ms.head, outputStream); 
        outputStream.write("total time used: ");
        outputStream.write(Long.toString(totalTimed)+" ns"+"\r\n");
        outputStream.write("\r\n");
    } finally{
           if (outputStream != null) outputStream.close();
  }
 }
}
