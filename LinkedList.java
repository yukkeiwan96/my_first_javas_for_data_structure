import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * LinkedList is a class that stores the ListNodes to a linked list, where the head
 * pointer is pointing to the first element and the tail pointer is pointing to the
 * last element. If the LinkedList is empty, the first ListNode will be added, and 
 * both the head and tail pointers will be updated. Otherwise, if the LinkedList is 
 * not empty, then new ListNode will be added with only the tail pointer updated.
 */

class LinkedList {
    private ListNode head = null;  
    private ListNode tail = null;  

    public void AddNode(int row_p, int col_p) {
      ListNode ln = new ListNode(row_p, col_p);
      if(this.head == null) {
        this.head = ln;
        this.tail = ln;
      } else {
        ListNode tail = this.tail;
        tail.Next = ln;
        this.tail = ln;
      }
    }


  /**
   * Search for all the paths from the linked list iteratively
   */

    public void search(FileWriter output, Integer nodes, LinkedList edges, ArrayList<Integer> pathlist)throws IOException{
      ListNode item = this.head;
      ListNode sub = null;
      boolean[] visited= new boolean[nodes];
      output.write("All paths:");
      output.write("\r\n");
      while(item != null) {
        pathlist.clear();
	int nextnode = item.row_p;
	pathlist.add(nextnode);
        visited[nextnode]= true;
	nextnode = item.col_p;
        pathlist.add(nextnode); 
        sub= this.head;
        boolean found= false;
        while(sub != null){
          if(sub.row_p == nextnode && !visited[nextnode]){
            pathlist.add(sub.col_p); 
            found = true;
            nextnode = sub.col_p;
           }sub = sub.Next;
         }
        for (int z=0; z<pathlist.size(); z++){
          Integer element = pathlist.get(z);
          if ((z+1) < pathlist.size()){
            output.write(element+"->");
            }else{
             output.write(element+"\r\n");
            }  
        }
        item = item.Next;
      }
    }

}
