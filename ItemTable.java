/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adsassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author SUJITHA
 */
class ItemNode{
    private String item;
    private ItemNode next;
    
        
   public void setNext(ItemNode n){
       next=n;
   }
   
    public void setItem(String s){
        item=s;
    }
    
    public  String getItem(){
        return item;
    }
    
    public ItemNode getNext(){
        return next;
    }
}

public class ItemTable {
    ItemNode list[] = new ItemNode[100];
    int size=0;
     HeaderTable h= new HeaderTable();
     
     //first header table needs to be filled. Items that do not belong to header table are neglected
    public void fillItemTable(File f) throws FileNotFoundException{
        int i=0;
        FileReader file = new FileReader(f);
        Scanner in=new Scanner(file);
       
        h.fillTable(f);
        System.out.println("----Header Table----");
        h.displayTable();
        while(in.hasNext()){
           
            ItemNode curr1=new ItemNode();
            ItemNode curr=curr1;
            int j=1;
            String s=in.nextLine();
            String arr[]=s.split(" ; ");
            curr.setItem(arr[0]);
            while(j<arr.length){
                if(h.getFrequency(arr[j])!=-1){
                    //list[i].setNextNext(arr[j]);
                    curr.setNext(new ItemNode());
                    curr=curr.getNext();
                    curr.setItem(arr[j]);
                   
                    
                }
                j++;
            }
            list[i]=curr1;
           list[i]=sort(list[i]);
            i++;
            size++;
        }
    }
     public void fillItemTable(String [][]s , int size1){
        int i=0;
        
       
        h.fillTable(s, size1);
        System.out.println("----Header Table of Conditional Base Pattern----");
        h.displayTable();
        //int k=0;
        while(i<size1){
           
            ItemNode curr1=new ItemNode();
            ItemNode curr=curr1;
            ItemNode prev=new ItemNode();
            int j=1;
            //String s=in.nextLine();
           // String arr[]=s.split(" ; ");
           
            while(s[i][j]!=null){
                if(h.getFrequency(s[i][j])!=-1){
                   // System.out.println(h.getFrequency(s[i][j]));
                    
                    //list[i].setNextNext(arr[j]);
                    
                    curr.setItem(s[i][j]);
                    curr.setNext(new ItemNode());
                    prev=curr ;
                    curr=curr.getNext();
                    
                    //curr.setItem(s[i][j]);
                   
                    
                }
                
                j++;
            }
           
                 prev.setNext(null);
                
            //curr.setItem(s[i][j]);
            list[i]=curr1;
           list[i]=sort(list[i]);
            i++;
            size++;
        }
        System.out.println("---Item Table---");
        display();
    }
    
    public ItemNode sort(ItemNode n){
        if (n == null || n.getNext() == null)//checking for empty and single linked list
			return n;
 
		ItemNode newHead = new ItemNode();
                newHead.setItem(n.getItem());
                
		ItemNode pointer = n.getNext();
 
		// loop through each element in the list
		while (pointer != null) {//pointer going to end list
			// insert this element to the new list
 
			ItemNode innerPointer = newHead;
			ItemNode next = pointer.getNext();
 
			if (h.getFrequency(pointer.getItem()) >= h.getFrequency(newHead.getItem())) {//exchanging the values
                                                        //Adding a node in the beginning;
				ItemNode oldHead = newHead;
				newHead = pointer;
				newHead.setNext(oldHead);
			} else {
				while (innerPointer.getNext() != null) {//checking original element with 1 value and 2 value 
                                                                    //Adding a node in between
					if (h.getFrequency(pointer.getItem())< h.getFrequency(innerPointer.getItem()) && h.getFrequency(pointer.getItem()) >= h.getFrequency(innerPointer.getNext().getItem())) {//exchange values 2 and 1
						ItemNode oldNext = innerPointer.getNext();
						innerPointer.setNext(pointer);
						pointer.setNext(oldNext);
					}
 
					innerPointer = innerPointer.getNext();
				}
                                //Adding a node in the end;
				if (innerPointer.getNext() == null && h.getFrequency(pointer.getItem()) < h.getFrequency(innerPointer.getItem())) {//sort has to be done
					innerPointer.setNext(pointer);
					pointer.setNext(null);
				}
			}
 
			// finally
			pointer = next;
		}
                return newHead;
    }
    
    public void display(){
        for(int i=0;i<size;i++){
            ItemNode curr=list[i];
            while(curr!=null){
                System.out.print(curr.getItem()+"\t\t");
                curr=curr.getNext();
            }
            System.out.println();
        }
    }
}