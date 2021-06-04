package com.sociopath.events;

import java.util.Scanner;

public class E4ArrangeBook {

    public static void main(String[] args) {
        System.out.println(new E4ArrangeBook().arrange(5, new int[]{7, 8, 7, 6, 5}));
    }

    public String arrange(int numberOfBook, int[] array) {

        LinkedList list = new LinkedList(); //create linked list
        int b, i = 0;

        for (int j = 0; j < numberOfBook; j++) {
            b = array[j]; //Sting to Int
            list.insert(b); //add to Linked List
        }

        while(list.check()){
            list.rearrange();
            i++;
        }

        String describe = "It needs " + i + "  rounds to meet the librarian’s request.\n";
        describe+=("List after " + i + " rounds: [ ");
        describe+=list.display();
        describe+=" ]";

        return describe;
    }

}

class LinkedList {
    Node head;

    public void insert(int data){   //insert book
        Node node = new Node();
        node.data = data;
        node.next = null;

        if (head == null) {
            head = node;
        }else{
            Node n = head;
            while(n.next != null){
                n = n.next;
            }
            n.next = node;
        }
    }

    public void delete(int index){  //remove the higher books
        if (index == 0) {
            head = head.next;
        }else{
            Node n = head;
            Node n1 = null;
            for (int i = 0; i < index - 1 ; i++) {
                n = n.next;
            }
            n1 = n.next;
            n.next = n1.next;
        }
    }

    public void rearrange(){ //rearrange the books
        try{
            Node n = head;
            int i = 0;
            while(n.next != null){
                if (n.next.data >= n.data) {
                    i++;
                    delete(i);
                    n = n.next;
                }else{
                    n = n.next;
                    i++;
                }
            }
        } catch(NullPointerException e){
            return;
        }
    }

    public boolean check(){ //check the book order to determine need arrange or not
        Node n = head;
        while(n.next != null){
            if (n.next.data >= n.data) {
                return true;
            }
            n = n.next;
        }
        return false;
    }

    public String display(){
        String display="";
        Node node = head;
        while(node.next != null){
            display+=node.data;
            display+=", ";
            node = node.next;
        }
        display += node.data;
        return display;
    }
}

class Node { //node class
    int data;
    Node next;
}
