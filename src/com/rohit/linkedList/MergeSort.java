package com.rohit.linkedList;

public class MergeSort {

    // merge sort
    public  ListNode sortList(ListNode head){
        // base condition
        if(head == null || head.next == null){ // 1 element present
            return head;
        }

        ListNode mid = calculateMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        return mergeBoth(left, right);

    }

    public ListNode calculateMid(ListNode head){
        // head is fast
        ListNode prevMid = null; //(acts as slow)
        while(head != null && head.next != null){
            prevMid = (prevMid == null) ? head : prevMid.next; //(slow is) one step behind from normal fast slow method
            head = head.next.next;
        }
        ListNode mid = prevMid.next;
        prevMid.next = null; // need to make last element pointing to null, orelse stackoverflow error
        return mid;
    }


    public ListNode mergeBoth(ListNode first, ListNode second){

        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead; // shallow copy

        while(first != null && second != null){
            if(first.val < second.val){
                tail.next = first;
                first = first.next;
                tail = tail.next;
            }else{
                tail.next = second;
                second = second.next;
                tail = tail.next;
            }
        }
        tail.next = (first != null) ? first : second;
        return dummyHead.next;
    }


}
