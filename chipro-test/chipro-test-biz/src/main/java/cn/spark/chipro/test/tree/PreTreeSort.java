package cn.spark.chipro.test.tree;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PreTreeSort {

    @Data
    static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        Node head = createTree(new int[]{1, 2, 3, 4, 5, 6, 7});
        prePrintTree(head);
        midPrintTree(head);
        nextPrintTree(head);
        nextTwoPrintTree(head);
    }

    public static void nextTwoPrintTree(Node head){
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node h = head;
        Node c ;

        while (!stack.empty()){
            c=stack.peek();
            if(c.left!=null&&c.left!=h&&c.right!=h){
                stack.push(c.left);
            }else if(c.right!=null&&c.right!=h){
                stack.push(c.right);
            }else{
                h = stack.pop();
                System.out.printf(h.value+" ");
            }
        }
    }


    public static void nextPrintTree(Node head){
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        Node cur = head;
        s1.push(head);

        while (!s1.empty()){
            cur = s1.pop();
            s2.push(cur);
            if(cur.left!=null)s1.push(cur.left);
            if(cur.right!=null)s1.push(cur.right);
        }

        while (!s2.empty()){
            cur = s2.pop();
            System.out.printf(cur.value+" ");
        }
        System.out.println();

    }

    public static void midPrintTree(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;

        while (!stack.empty()||cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            Node pop = stack.pop();
            System.out.print(pop.value+" ");
            cur=pop.right;
        }
        System.out.println();
    }


    public static void prePrintTree(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur;
        stack.push(head);
        while (!stack.empty()){
            cur = stack.pop();
            System.out.print(cur.value+" ");
            if(cur.right!=null)stack.push(cur.right);
            if(cur.left!=null)stack.push(cur.left);
        }
        System.out.println();
    }

    public static Node createTree(int[] values){
        Queue<Node> queue = new LinkedList<>();
        Node head = new Node(values[0]);
        queue.offer(head);
        for(int i=1;i<values.length;i++){
            Node peek = queue.peek();
            if(peek.left!=null&&peek.right!=null){
                queue.poll();
                peek=queue.peek();
            }
            Node node = new Node(values[i]);
            queue.offer(node);
            if(peek.left==null)peek.left=node;
            else peek.right=node;

        }
        return head;
    }




}
