package Easy;

public class Remove_Linked_List_Elements {

    public static ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return head;
        while(head != null && head.val == val)
            head = head.next;
        ListNode p = head;

        while( p !=null && p.next != null)
        {
            while(p.next != null && p.next.val == val)
            {
                p.next = p.next.next;
            }
            p = p.next;
        }
        return head;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}



 class ListNode {
    int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }


