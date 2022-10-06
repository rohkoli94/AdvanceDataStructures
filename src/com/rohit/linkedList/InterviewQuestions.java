package com.rohit.linkedList;


public class InterviewQuestions {

    // https://leetcode.com/problems/linked-list-cycle/
    // Q.3 cycle detection
    // Amazon , Microsoft

    public boolean hasCycle(ListNode head) {
        /* fast and slow pointer method
            fast 2x speed, slow 1x speed

            if(fast == slow) cycle present
            check untill fast!= null and fast.next != null,
            if condition breaks then there is no cycle, as it has reached and crossed last node's
         */

        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    // Q. length of cycle
    public int lengthOfCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){ // cycle found
                /*
                now keep fast pointer there itself and move slow pointer
                until it reached fast again and increment length
                 */
                ListNode temp  = slow;
                int length = 0;
                do{
                    temp = temp.next;
                    length++;
                }while(slow != temp);
                return length;
            }
        }
        return 0; // no cycle found , hence 0
    }

    // Q.4 find the node of cycle
    public ListNode detectCycle(ListNode head){
        /*
         (1) use fast and slow method
         (2) calculate length and break the loop
         (3) take 2 pointers f and s
         (4) move s pointer ahead as per length
         (5) move f and s pointers ahead until they meet, thats the starting node of cycle
         */
        ListNode fast = head;
        ListNode slow = head;

        int length = 0;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){  //cycle found
                // calculate length
                ListNode temp = slow;
                do{
                    temp = temp.next;
                    length++;
                }while(temp != slow);
                break; //v.v.important orelse will go infinite
            }
        }
        if(length == 0){ // no cycle present
            return null;
        }

        // take 2 pointers
        ListNode f = head;
        ListNode s = head;

        //and move s ahead length time
        while(length > 0){
            s = s.next;
            length--;
        }

        // now move f and s ahead, whenever they meet thats starting node
        while(f != s){
            f= f.next;
            s= s.next;
        }
        return f; // or s;
    }

    // https://leetcode.com/problems/happy-number
    // Q.5 happy number
    // Google

    public boolean isHappy(int n){
        int fast = n;
        int slow = n;

        while(fast != 1 && slow != 1){
            slow = sumOfSqOfDigits(slow);
            fast = sumOfSqOfDigits(sumOfSqOfDigits(fast));
            if(fast != 1 && fast == slow){
                return false; // cyclic and 1 is not found orelse it would have been caught in while loop
            }
        };
        return true; // happy number
    }

    private int sumOfSqOfDigits(int n){
        int ans = 0;
        while(n > 0){
            int rem = n%10;
            ans += rem*rem;
            n = n/10;
        }
        return ans;
    }


    // https://leetcode.com/problems/middle-of-the-linked-list
    // Q.6 find middle of linked list
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            slow = slow.next; // 1x speed
            fast = fast.next.next; //2x speed
        }
        return slow;
    }

   // https://leetcode.com/problems/reverse-linked-list
    // reverse a linked list
    // google, microsoft, apple, amazon

    public ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode present = head;
        ListNode next = null;
        if(present != null){
            next = present.next;
        }

        while(present != null){
            present.next = prev;
            //move pointers ahead
            prev = present;
            present = next;
            if(next != null){
                next = next.next;
            }
        }
        // when loop terminates then prev will be at last position, so assign head
        head = prev;
        return head;
    }

// https://leetcode.com/problems/reverse-linked-list-ii
    // reverse subpart using left and right given
   //    Google, Microsoft, Facebook

    public ListNode reverseBetween(ListNode head, int left, int right) {
        //if left and right are same, then nothing to reverse
        if(left == right){
            return head;
        };

        // (1)store beforeReversed (connect to start of reversal) node and end Of Rev node (for connecting purpose)

        //before reverse can be found using left -1
        ListNode prev = null;
        ListNode current = head;
        for (int i = 0; current != null && i < left - 1; i++) {
            prev = current;
            current = current.next; // handled end case in for loop
        }
        ListNode beforeReversedNode = prev;
        ListNode EndOfReverseNode = current; //(as this value will go at last when reversed)

        // (2)now reverse
        ListNode next = current.next;
        for (int i = 0; current != null && i < right - left + 1 ; i++) {
            // reverse logic
            current.next = prev; // handles in for loop
            // move pointers
            prev = current;
            current =  next;
            if(next != null){
                next = next.next;
            }
        }
        /*
        345
        output
        543(prev is at 3) , current is one step ahead of prev
         */
        //e.g 345 ::rev:: 5(prev)43  i.e  beforeReversedNode -> 5(prev)
        // here prev is first element of reverse data, current is one ahead

        //(3) connect beforeReversedNode and EndOfReverseNode to its nodes
        if(beforeReversedNode == null){
            head = prev;
        }else{
            beforeReversedNode.next = prev;
        }
        EndOfReverseNode.next = current;
        return head;
    }

    // https://leetcode.com/problems/palindrome-linked-list/
    //check if palindrome of or not
   // linkedin, google, facebook, microsoft, amazon, apple
    public boolean isPalindrome(ListNode head) {
        ListNode mid = middleNode(head); // find mid and reverse that data
        ListNode head2 = reverse(mid);  // reverse that data

        ListNode reverseHead = head2;  // used for again reversing back to orignal form

        /* here e.g :odd: (1->2->3(mid)->2->1->null) or :even: (1->2->3->3(mid)->2->1->null),
        so reverse is :odd: (1->2->3->null)  or :even: (1->2->3->null),
         so original head :odd:(1->2->3->null) and head2 (1->2->3->null) or :even:(1->2->3->3->null) and head2 (1->2->3->null)
         we check until head or head2 , anyone becomes null, if its becomes null then its palindrome
         */

        //compare head and head2
        while(head != null && head2 != null){
            if(head.val != head2.val){
                break; // we can also return from here (i.e it is not palindrome) if you dont want to reverse the list back to normal form
            }
            head = head.next;
            head2 = head2.next;
        }
        // reverse the list if needed or else you can return true from here (i.e it is palindrome)
        reverse(reverseHead);

        if(head == null || head2 == null){
            return true; // everthing is proper
        }
        return false;
    }

   // https://leetcode.com/problems/reorder-list
    // reorder list (first-last-second-secondlast-third-thirdlast and so on)
    // Google, Facebook
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }

        // find mid and reverse
        ListNode mid = middleNode(head);
        ListNode head2 = reverse(mid);
        ListNode head1 = head;
        // here head2's (6->5->4->null) , hence head1's (1->2->3->4->null)

        // compare
        while(head1 != null && head2 != null){
            ListNode temp = head1.next;
            head1.next = head2;
            head1 = temp;


            temp = head2.next;
            head2.next = head1;
            head2 = temp;
        }
        /* head1 contains all the data of nodes ,
        if head2 has finished earlier work is done
        just check if head is null - then its ok
        but head1 is not null means it has data(due to the reverse approach)
        so just make that next to null
*/
        if(head1 != null){ // means head2 has finished earlier
            head1.next =  null; // so point head1's next to null (orelse leetoce will get cyclic error as there is no null)
        }
    }

   // https://leetcode.com/problems/reverse-nodes-in-k-group
    //reverse k group nodes
    // google, amazon, facebook, microsoft
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k <= 1 || head == null || head.next == null){
            return head;
        };

        int length = getLength(head);
        int count = length/k;

        ListNode prev = null;
        ListNode current = head;

        while(count > 0){
            ListNode bforeReversalNode = prev;
            ListNode endOfReversalNode = current;

            ListNode next = null;
            if(current != null){
                next = current.next;
            }

            // reverse (3 pointer method)
            for (int i = 0; i < k; i++) {
                current.next = prev;
                prev= current;
                current = next;
                if(next != null){
                    next = next.next;
                }
            }
            // here prev means the first element of reverse data (3pre,2,1)
            if(bforeReversalNode == null){
                head = prev;
            }else{
                bforeReversalNode.next = prev;
            }
            endOfReversalNode.next = current;

            // v.v.v imp // endOfReversalNode becomes prev for the 2nd time onwards for loop iteration
            prev = endOfReversalNode;
            count--;
        }
        return head;
    }

    // https://www.geeksforgeeks.org/reverse-alternate-k-nodes-in-a-singly-linked-list/
    // reverse k group alternative nodes
    public ListNode reverseAlternateKGroup(ListNode head, int k) {
        if(k <= 1 || head == null | head.next == null){
         return head;
        }
    // 3 pointer method for reversal until current != null


        ListNode prev = null;
        ListNode current = head;

        while(current != null){ // until it reaches last
            ListNode backOfReversalNode = prev;
            ListNode endOfReversalNode = current;

            ListNode next = null;
            if(current != null){
                next = current.next;
            }

            // reverse
            for (int i = 0; current != null && i < k; i++) {
                current.next = prev;
                prev = current;
                current = next;
                if(next != null){
                    next = next.next;
                }
            }
            // here pre is the starting node of reversed data
            if(backOfReversalNode == null){
                head =  prev;
            }else{
                backOfReversalNode.next = prev;
            }

            endOfReversalNode.next = current;

            // skip k nodes

            for (int i = 0; current != null && i < k; i++) {
                prev  = current;
                current = current.next;
            }
        }

        return head;
    }

   // https://leetcode.com/problems/rotate-list/
    // rotate the linked list
   // FaceBook, Twitter, Google

    public ListNode rotateRight(ListNode head, int k) {
            if(k <= 0 || head == null || head.next == null){
                return head;
            };
            // point last element to first
             ListNode temp = head;
        int length = 1;
        while(temp.next != null){
                    length++;
                    temp = temp.next;
                }
                temp.next = head;
             /*
             if k is greater than length
             e.g k = 8 and length is 6 it means we need to rotate only 2 times,
             as 6(length) times rotating will give same list(same position)
              */
        int rotation = k%length;
        int skip = length - rotation;

        ListNode lastEnd = head; // starting from 1
        for (int i = 1; i < skip; i++) { // above is starting from 1 so skip - 1 times
            lastEnd = lastEnd.next;
        }
        head = lastEnd.next;
        lastEnd.next = null;

        return head;
    }


    public int getLength(ListNode head){
        ListNode temp = head;
        int length = 0;
        while(temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }


    public static void main(String[] args) {
        InterviewQuestions a = new InterviewQuestions();
        boolean ans = a.isHappy(10);
        System.out.println(ans);


        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;

        ListNode l1 = new ListNode(1);
        tail.next = l1;
        tail = l1;

        ListNode l2 = new ListNode(2);
        tail.next = l2;
        tail = l2;

        ListNode l3 = new ListNode(3);
        tail.next = l3;
        tail = l3;

        ListNode l4 = new ListNode(4);
        tail.next = l4;
        tail = l4;

        ListNode l5 = new ListNode(5);
        tail.next = l5;
        tail = l5;

        ListNode l6 = new ListNode(6);
        tail.next = l6;
        tail = l6;

        InterviewQuestions pal = new InterviewQuestions();
        //pal.isPalindrome(dummyHead.next);
        pal.reorderList(dummyHead.next);

        int n = (int)8.99/3;
        System.out.println(n);
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    ListNode(int x) {
        val = x;
        next = null;
    }
}
