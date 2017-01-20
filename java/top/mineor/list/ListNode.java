package top.mineor.list;

/**
 * Created by mineor on 2016/12/19.
 */
public class ListNode {
    int val;
    ListNode next;
    public ListNode(int val){
        this(val,null);
    }
    public ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }
}
