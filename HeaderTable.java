/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adsassignment;

import java.io.File;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author SUJITHA
 */
class Link{
    Link next;
    
    public void setNext(Link n){
        next=n;
    }
    public Link getNext(){
        return next;
    }
}
class Header {
   private String item;
   private int frequency;
   private Header next;
   private Node chain;
    
    
    public Header(){
        item="";
        frequency=0;
        next= null;
        chain=null;
        
    }
    public Header getNext(){
        return next;
    }
    public void setNext(Header n){
        next=n;
    }
    public int getFrequency(){
        return frequency;
    }
    public void setFreq(int n){
        frequency=n;
    }
    
    public String getItem(){
        return item;
    }
    public void setItem(String n){
        item=n;
    }
    public Node getChain(){
        return chain;
    }
    public void setChain(Node n){
        chain=n;
    }
   
    
}
public class HeaderTable{
    Header first;
    //Header second;
    int minSupportCount=2;
    int numOfItems;
    
    
     public void fillTable(String [][]s, int size1) {
       int j=0;
       if(s.length!=0){
        while(j<size1){
            //String s=in.nextLine();
           // String arr[]=s[j].split(" , ");
           
           for(int i=1;s[j][i]!=(null)&&i<s[j].length;i++){
               addItem(s[j][i],s[j][0]);
               
           }
           j++;
        }
        first=sort();
        deleteLessFreq();
        
    }
     }
    
    
    
     public void fillTable(File list) throws FileNotFoundException{
        FileReader file = new FileReader(list);
        Scanner in=new Scanner(file);
        while(in.hasNext()){
            String s=in.nextLine();
            String arr[]=s.split(" ; ");
           
           for(int i=0;i<arr.length;i++){
               addItem(arr[i]);
               
           }
        }
        first=sort();
        deleteLessFreq();
        
    }
     public void addItem(String n, String freq){
         
         if(n!=null){
         if(first==null){
             first=new Header();
               first.setItem(n);
               first.setFreq(first.getFrequency()+Integer.parseInt(freq));
         }
         else{
            Header prev=null;
         Header current=first;
         while(current!=null&&!current.getItem().equals(n)){
             prev=current;
             current=current.getNext();
         }
         if(current==null){
             Header a=new Header();
             a.setItem(n);
             a.setFreq(a.getFrequency()+Integer.parseInt(freq));
            prev.setNext(a);
         }
         else {
             current.setFreq(current.getFrequency()+Integer.parseInt(freq));
         }
     }
         }
         
}
     public void addItem(String n){
         
         if(first==null){
             first=new Header();
               first.setItem(n);
               first.setFreq(first.getFrequency()+1);
         }
         else{
            Header prev=null;
         Header current=first;
         while(current!=null&&!current.getItem().equals(n)){
             prev=current;
             current=current.getNext();
         }
         if(current==null){
             Header a=new Header();
             a.setItem(n);
             a.setFreq(a.getFrequency()+1);
            prev.setNext(a);
         }
         else {
             current.setFreq(current.getFrequency()+1);
         }
     }
}
     //insertion sort used
     public Header sort(){
         if (first == null || first.getNext() == null)//checking for empty and single linked list
			return first;
 
		Header newHead = new Header();
                newHead.setItem(first.getItem());
                newHead.setFreq(first.getFrequency());
		Header pointer = first.getNext();
 
		// loop through each element in the list
		while (pointer != null) {//pointer going to end list
			// insert this element to the new list
 
			Header innerPointer = newHead;
			Header next = pointer.getNext();
 
			if (pointer.getFrequency() >= newHead.getFrequency()) {//exchanging the values
                                                        //Adding a node in the beginning;
				Header oldHead = newHead;
				newHead = pointer;
				newHead.setNext(oldHead);
			} else {
				while (innerPointer.getNext() != null) {//checking original element with 1 value and 2 value 
                                                                    //Adding a node in between
					if (pointer.getFrequency()< innerPointer.getFrequency() && pointer.getFrequency() >= innerPointer.getNext().getFrequency()) {//exchange values 2 and 1
						Header oldNext = innerPointer.getNext();
						innerPointer.setNext(pointer);
						pointer.setNext(oldNext);
					}
 
					innerPointer = innerPointer.getNext();
				}
                                //Adding a node in the end;
				if (innerPointer.getNext() == null && pointer.getFrequency() < innerPointer.getFrequency()) {//sort has to be done
					innerPointer.setNext(pointer);
					pointer.setNext(null);
				}
			}
 
			// finally
			pointer = next;
		}
                return newHead;
     }
     
     public void displayTable(){
        if(first==null)
            System.out.println("List is empty");
        
        else{
            Header current=first;
            while(current!=null){
                System.out.println("Item "+current.getItem()+": Frequency "+current.getFrequency());
                current=current.getNext();
            }
        }
        
     }
     //this is done after sorting in decreasing manner 
     public void deleteLessFreq(){
         if(first==null)
             System.out.println("List is empty");
         else{
             Header current=first;
             //HeaderTable current1=current;
             while(current.getNext()!=null && current.getNext().getFrequency()>=minSupportCount){
                 //current1=current1.getNext();
                
                     current=current.getNext();
                 
                 
                     }
             current.setNext(null);
             
                 
             
             //first=current;
         }
     }
     
     public int getFrequency(String s){
         Header current=first;
         while(current!=null && !current.getItem().equals(s))
             current=current.getNext();
         if(current!=null)
             return current.getFrequency();
         else{
             return -1;
         }
     }
     
     public Header search(String s){
         Header curr=first;
         while(curr!=null&&!curr.getItem().equals(s))
             curr=curr.getNext();
         if(curr==null)
             return null;
         else
             return curr;
     }
}


