package top.mineor.linkedlist;


/**
 * Created by mineor on 2016/12/19.
 */
public class LinkedList {

    public static void main(String[] args) {
        ListNode head = null;
        for(int i = 0; i < 5; i++)
            head = addListNode(head,i);
        //head = removeListNode(head,4);
        //System.out.println(getListLength(null));
        //head = reverseLinkedList2(head);
        head = insertListNodeInIndex(head,new ListNode(6),1);
        printList(head);
    }

    /**
     * 打印函数
     * @param head
     * @return
     */
    public static void printList(ListNode head){
        if(head != null){
            System.out.print(head.val);
            head = head.next;
        }
        while(head != null){
            System.out.print("->" + head.val);
            head = head.next;
        }
    }

    /**
     * 新增结点
     */
    public static ListNode addListNode(ListNode head,int val){
        if(head == null){
            head = new ListNode(val);
            return head;
        }
        ListNode p = head;
        while(p.next != null)
            p = p.next;
        p.next = new ListNode(val);
        return head;
    }

    /**
     * 删除结点
     */
    public static ListNode removeListNode(ListNode head,int val){
        if(head == null)
            return null;
        if(head.val == val)
            return head.next;
        ListNode p = head;
        while(p.next != null && p.next.val != val){
            p = p.next;
        }
        if(p.next == null)
            return head;
        else{
            p.next = p.next.next;
            return head;
        }
    }

    /**
     * 将新结点插入到链表指定位置
     * @param head
     * @param node
     * @param index
     * @return
     */
    public static ListNode insertListNodeInIndex(ListNode head,ListNode node,int index){
        int i = 1;
        ListNode  p = head;
        if(index <= 0){
            node.next = head;
            return node;
        }
        while(p.next != null && index > i){
            p = p.next;
            i++;
        }
        if(p.next == null)
            p.next = node;
        else{
            node.next = p.next;
            p.next = node;
        }
        return head;
    }

    /**
     * 获取链表长度
     * @return
     */
    public static int getListLength(ListNode head){
        int length = 0;
        while (head != null){
            length++;
            head = head.next;
        }
        return length;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public static ListNode reverseLinkedList1(ListNode head){
        if(head == null)
            return null;
        ListNode p = head;//p,q为交换指向的两个结点
        ListNode q = head.next;
        p.next = null; //反转后第一个结点next为null
        ListNode r;//用来记录q的下一个结点，即未操作过的结点
        while(q != null){
            r = q.next; //将下一个结点赋给r，接下来要q.next会丢失，所以需要先保存下
            q.next = p; //改变q的指向，这样p->q就反转成了q->p
            p = q; //将p向前移动
            q = r; //将q向前移动
        }
        return p; //退出循环后q已经为null，p要比q晚一步，所以会是新链表的头结点
    }

    /**
     * 递归反转链表
     * 递归反转A->B->C->D,只需要将A加到D->C->B的最后即可，
     * 想要D->C->B，只需要将B加入D->C的最后即可，想要D->C
     * 只需要将D加在C最后即可，至此，递归结束.
     * @param head
     * @return
     */
    public static ListNode reverseLinkedList2(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode result = reverseLinkedList2(head.next);
        head.next = null; //解除head之前的next关系,保证链表不会原地画圈
        ListNode p = result;
        while(p.next != null)
            p = p.next;
        p.next = head;
        return result;
    }

    /**
     * 通过插入节点来反转链表
     * @param head
     * @return
     */
    public static ListNode reverseLinkedList3(ListNode head){
        //待补充
        return null;
    }

}
