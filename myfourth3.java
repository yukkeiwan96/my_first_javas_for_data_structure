import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

/* QuickInserB reads in a line of integers from a text file which is then split into an array
 * It uses the first element as pivot
 * It has a partition size of 100, which is then followed by insertion sort
 * quickinserb sorts the array iteratively until the partition size 100.
 * each partition of size 100 is then sorted by insertion sort
 */
public class QuickInserB { 
    public int partition(int arr[], int low, int high) {
        int pivot = arr[low];
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
   
    public void insertsort(int nums[], int first, int last){
        for (int x = first + 1; x < last; x++){
           int val = nums[x];
            int j = x-1;
            while (j >= 0 && val < nums[j]){
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = val;
        }
    }

    public void quickinserb(int arr[], int l, int h) { 
       if ((h-l)>100){ 
        int[] stack = new int[h-l+1];
        int top = -1;
        stack[++top] = l;
        stack[++top] = h;
        while (top >= 0){
            h = stack[top--];
            l = stack[top--];
            int p = partition(arr, l, h);
            if (p-1 > l){
                stack[++top] = l;
                stack[++top] = p-1;
            }
            if (p+1 < h){
                stack[++top] = p+1;
                stack[++top] = h;
            }
        }
          }else{
            insertsort(arr, l, h+1);
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
    QuickInserB qb= new QuickInserB();
    
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

        outputStream.write("Quick Sort: partition size of 100 followed by insertion sort. "+"\r\n");
        long startTimed = System.nanoTime();
        qb.quickinserb(nums, 0, le-1);
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
