import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class lab1 {
   public static void main(String[] args) throws IOException {
      FileReader inputStream = null;
      FileWriter outputStream = null;
      Stack elevator;
      Stack brokenelevator;
      Stack outside;
      String nameinout= null;
      Integer cannotgeton= 0;
      Integer whogoton= 0;
      Integer listsize = 0;
      lab1 lab1;
      lab1= new lab1();
     if (args.length != 2) {
       System.out.println("Usage: java lab1 [input]" + " [output]");
       System.exit(1);
     }
     try {
       Scanner sc = new Scanner(new BufferedReader(new FileReader(args[0])));
       outputStream = new FileWriter(args[1]);
       String line = sc.nextLine(); 
       line = sc.nextLine();
       outputStream.write("##elevator process##"+"\r\n");
       outputStream.write("####################"+"\r\n");
       elevator= new Stack(5);
       outside= new Stack(49);
       brokenelevator= new Stack(5);
       while (sc.hasNextLine()) {
        line= sc.nextLine();
        String[] parts=line.split("\t");
        nameinout= parts[0]+","+parts[1]+","+parts[2];
        if (elevator.isEmpty()){
          elevator.push(nameinout);
          whogoton+=1;
        }else{
          String prev= elevator.peek();
          String[] prevparts= prev.split(",");
          if (parts[1].equals(prevparts[1])){
           if(elevator.isFull() != true){
              elevator.push(nameinout);
              whogoton += 1;
             }else{
               cannotgeton += 1;
              }
          }else{
            String cur= "";
            String nextf;
            while (!elevator.isEmpty()){
              String outperson= elevator.pop();
              String[] outpparts=outperson.split(",");
              if (!outpparts[1].equals("5")){
              Integer next= Integer.parseInt(outpparts[1])+1;
              nextf= next.toString();
              }else{
              Integer next= Integer.parseInt(outpparts[1])-1;
              nextf= next.toString();
               }
              cur+= outpparts[0]+" ";
              if (!outpparts[2].equals(parts[1]) && parts[1].equals(nextf)){
                outside.push(outperson);
              }else if (!parts[1].equals(nextf) && !outpparts[2].equals(parts[1])){
                outside.push(outperson);
               }
            }
            outputStream.write(cur);
            outputStream.write("\r\n");
            while (!outside.isEmpty()){
              String waitp= outside.pop();
              elevator.push(waitp);}
            if(!elevator.isFull()){
                elevator.push(nameinout);
                whogoton += 1;
             }else{
               cannotgeton += 1;
               }
          } 
       }
      } 
     }finally {
       outputStream.write("##############################"+"\r\n");
       outputStream.write("number of people could not ride: " + cannotgeton.toString()+"\r\n");
            outputStream.write("total number of people rode: " + whogoton.toString()+"\r\n");
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
   
        }
     }
 }
