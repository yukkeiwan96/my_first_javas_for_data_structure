import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

/* QuickSortD reads in a line of integers from a text file which is then split into an array
 * mediantres finds the median of three as the pivot
 * It has a partition size of 1 and 2
 * quicksortd sorts the array iteratively.
 */
public class QuickSortD{ 
         public static void swap(int[] arr,int a,int b) {
	    int temp=arr[a];
	    arr[a]=arr[b];
	    arr[b]=temp;    
	    }

         public static int mediantres(int[] arr,int left,int right){
	  int mid=(left+right)/2;
	  if(arr[mid]<arr[left])
	   swap(arr,mid,left);
	  if(arr[right]<arr[left])
	  swap(arr,right,left);
	  if(arr[mid]>arr[right])
	  swap(arr,mid,right);
	  swap(arr,mid,right-1);
	  return arr[right-1];
	 }

        public int partition(int arr[], int low, int high, int pivot) {
        int i = low+1; // index of smaller element
        for (int j=low+1; j<=high; j++){
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot){
                if(i != j) {
                    // swap arr[i] and arr[j]
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                i++;
            }
        }

        arr[low] = arr[i-1];
        arr[i-1] = pivot;

        return i-1;
    }
        

	 public void quicksortd(int[] arr,int l,int h){
             int[] stack = new int[h-l+1];
             int top = -1;
             stack[++top] = l;
             stack[++top] = h;
             while (top >= 0){
               h = stack[top--];
               l = stack[top--];
               int pivot=mediantres(arr,l,h);
	       int p=partition(arr, l, h, pivot);
	       if (p-1 > l){
                stack[++top] = l;
                stack[++top] = p-1;
               }
               if (p+1 < h){
                stack[++top] = p+1;
                stack[++top] = h;
               }
            } 
	 
	}

   public static void printArray(int nums[], FileWriter outputStream) throws IOException{ 
        int n = nums.length; 
        for (int i=0; i<n; ++i) 
            outputStream.write(Integer.toString(nums[i])+" "); 
        outputStream.write("\r\n");
    }
  /**
   * Main entry of the program.
   * Holds two commands: the input filename and output filename.
   */ 
  public static void main (String args[]) throws IOException {
    Scanner sc;
    FileWriter outputStream = null;
    int n;
    QuickSortD qd= new QuickSortD();
    
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
        String line= sc.nextLine();
        String[] ln= line.split("\\s+");
        int[] nums= new int[ln.length];
        int le= ln.length;
        for (int i = 0; i < ln.length; i++) {
         String ns = ln[i];
         nums[i] = Integer.parseInt(ns);
        }
        outputStream.write("Quick Sort: partition size 1 and 2. Median of three as pivot."+"\r\n");
        long startTimed = System.nanoTime();
        qd.quicksortd(nums, 0, le-1);
        long endTimed   = System.nanoTime();
        long totalTimed = endTimed - startTimed;
        outputStream.write("sorted array"+"\r\n");
        printArray(nums, outputStream); 
        outputStream.write("total time used: ");
        outputStream.write(Long.toString(totalTimed)+" ns"+"\r\n");
        outputStream.write("\r\n");
        }catch (Exception ioe) {
          System.err.println(ioe.toString());
          return;
         }finally{
           if (outputStream != null) outputStream.close();
            }
  }
       
}
  
