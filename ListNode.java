/**
 * ListNode is a class to form the structure of each node in the linked list
 * representation of the graph. Each node has four fields, the row index, the 
 * column index, the value, which should be 1, and Next, which points to the 
 * next node if any.
 */

class ListNode {
    int row_p; 
    int col_p;
    ListNode Next;

    public ListNode(int row_p, int col_p) {
      this.row_p = row_p;
      this.col_p = col_p;       
    }

}
