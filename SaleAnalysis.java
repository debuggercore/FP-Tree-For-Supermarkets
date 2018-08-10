package adsassignment;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SUJITHA
 */

import java.util.*;
import java.io.*;

public class SaleAnalysis {
   static ItemTable t=new ItemTable();
    public static void main(String args[])throws Exception{
       File f=new File("C:\\Users\\SUJITHA\\Documents\\NetBeansProjects\\ADSAssignment\\src\\adsassignment\\Shopping list.txt");
       
       t.fillItemTable(f);
       
       System.out.println("---Item table---");
       t.display();
       FPTree fp=new FPTree();
       for(int i=0;i<t.size;i++)
           fp.insert(t.list[i]);
      
       fp.setinternalLinks();
       System.out.println("---FP Tree---");
       fp.display();
       conditionalbasepattern c=new conditionalbasepattern();
       c.createfplist(t.h, t, fp);
       
       
        
        
        
    }
            
}
//Node for fp tree
class Node{
    private String item;
    private Node[] next; //array of children for parent node
    private Node prev;
    private Node internalLink;
    int i=0;    // always points to empty child
    int freq;
    Node(String i){
        item=i;
        next=new Node[6];
        prev=null;
        freq=1;
    }
    String getItem(){
        return item;
    }
    Node[] getNext(){
        return next;
    }
    void setNext(String s){
        if(i<6){
            next[i]=new Node(s);
            next[i].setPrev(this); // setting the child to point to the parent which is "this" object
            i++; //points to empty loc
        }
    }
    
    Node getPrev(){
        return prev;
    }
    void setPrev(Node n){
        prev=n;
    }
    public void setIntLink(Node n){
        internalLink=n;
    }
    public Node getIntLink(){
        return internalLink;
    }
    
    
}

class FPTree{
    Node root;
     Node n[];
    int i;
    FPTree(){
       //root=new  Node[6];
        root=new Node("root"); // root is null node for fp tree
        n=root.getNext();
        
        i=0;
    }
    void insert(ItemNode l){
      //  int numOfItems=l.;
        ItemNode curr=l;
        if(i<root.getNext().length){
           
            // if root has no children yet then add the entire linked list "l" to the fp tree
            if(i==0){
                n[i]=new Node(curr.getItem());
                n[i].setPrev(root);
               // System.out.println(root[i].getItem()+root[i].freq);
                curr=curr.getNext();
                //int k=1;
                Node []current=n;
                while(curr!=null){
                    current[i].setNext(curr.getItem());
                    current=current[i].getNext();
                    curr=curr.getNext();
                     //System.out.println(current[i].getItem()+current[i].freq);
                }
                i++;
                
            }
            //else compare first item of "l" with first child of root. if matches then compare its child and continue
            // if it doesn't match then go to its parent, and go to the parents next child and traverse
            else {
                boolean Inserted=false;
                int j=0,k=0;  
                Node []current=n;
                Node prev=null;
                while(!Inserted){
                    //continue traversing the tree till mismatch occurs
                    while(current[j]!=null && curr!=null && current[j].getItem().equals(curr.getItem())){
                        current[j].freq++;
                       //System.out.println(current[j].getItem()+current[j].freq);
                        prev=current[j];
                        
                        current=current[j].getNext();
                        j=0;
                        //k++;
                        curr=curr.getNext();
                    }
                    //if all items are inserted
                    if(curr==null)
                        Inserted=true;
                    //it reached the leaf node and there are more items to be inserted
                    else if(current[j]==null){
                        prev.setNext(curr.getItem());
                        current=prev.getNext();
                       current[prev.i-1].freq-=1;
                       Node n1=current[prev.i-1];
                        if(curr.getNext()==(null)){
                            current[prev.i-1].freq+=1;
                        Inserted=true;
                        }
                    }
                    //if first item mismatch the child of root, then create new branch from root
                    else if(prev==null){
                        while(current[j]!=null&&j<6&&!current[j].getItem().equals(curr.getItem()))
                            j++;
                        if(current[j]==null){
                        n[j]=new Node(curr.getItem());
                        n[j].freq-=1;
                       // current=root;
                       
                        i++;
                        //j=i;
                        }
                       
                       
                    }
                    // mismatch happened. Create a new branch from parent node
                    else
                    {
                       prev.setNext(curr.getItem());
                       //Inserted=true;
                       current=prev.getNext();
                       j=prev.i-1;
                       current[j].freq-=1;
                      // k++;
                    }
            }
        }
    }
      
     
        
        
}
    
    //Similarly create fp from conditional base pattern 
    void insertCondFP(ItemNode l, HeaderTable h, pnode pn, int p){
      //  int numOfItems=l.;
       int freq=pn.getFrequency(p);
        ItemNode curr=l;
        if(i<root.getNext().length){
           
            if(i==0){
                n[i]=new Node(curr.getItem());
                n[i].freq=freq;
                n[i].setPrev(root);
               // System.out.println(root[i].getItem()+root[i].freq);
                curr=curr.getNext();
                //int k=1;
                Node []current=n;
                while(curr!=null){
                    current[i].setNext(curr.getItem());
                    
                    current=current[i].getNext();
                    current[i].freq=freq;
                    curr=curr.getNext();
                     //System.out.println(current[i].getItem()+current[i].freq);
                }
                i++;
                
            }
            else {
                boolean Inserted=false;
                int j=0,k=0; 
                Node []current=n;
                Node prev=null;
                while(!Inserted){
                    

                    while(current[j]!=null && curr!=null && current[j].getItem().equals(curr.getItem())){
                        current[j].freq+=freq;
                       //System.out.println(current[j].getItem()+current[j].freq);
                        prev=current[j];
                        
                        current=current[j].getNext();
                        j=0;
                       
                        curr=curr.getNext();
                    }
                    if(curr==null)
                        Inserted=true;
                    else if(current[j]==null){
                        prev.setNext(curr.getItem());
                        current=prev.getNext();
                        current[prev.i-1].freq=freq;
                       current[prev.i-1].freq-=1;
                       Node n1=current[prev.i-1];
                        if(curr.getNext()==(null)){
                            current[prev.i-1].freq+=1;
                        Inserted=true;
                        }
                    }
                    else if(prev==null){
                        while(current[j]!=null&&j<6&&!current[j].getItem().equals(curr.getItem()))
                            j++;
                        if(current[j]==null){
                        n[j]=new Node(curr.getItem());
                        n[j].freq=freq;
                        n[j].freq-=1;
                       // current=root;
                       
                        i++;
                        //j=i;
                        }
                       
                       
                    }

                    else
                    {
                       prev.setNext(curr.getItem());
                       //Inserted=true;
                       current=prev.getNext();
                       j=prev.i-1;
                       current[j].freq=freq;
                       current[j].freq-=1;
                      // k++;
                    }
            }
        }
          
    }
       
       
}
    //depth - first traversal technique used
    public void display(){
        int j;
        if(i==0)
            System.out.println("Nothing to display");
        else
            for(j=0;j<i;j++){
                display(n[j]);
            }
    }
    private void display(Node n){
        System.out.println(n.getItem()+" "+n.freq+" "+n.i);
      if(n.i!=0){
        int j=0;
       Node current[]=n.getNext();
       Node prev=n;
       //display(current[j]);
       while(j<n.i){
       display(current[j]);
       j++;}
      }
     
      
    }
    //setting internal links for fp tree 
    //traverse fp tree depth first, get corresponding header node to create link. 
    public void setinternalLinks(){
         int j;
        if(i==0)
            System.out.println("FP tree is empty");
        else
            for(j=0;j<i;j++){
                setinternalLinks(n[j]);
            }
    }
    private void setinternalLinks(Node n){
        //System.out.println("Item: "+n.getItem());
        Header h=SaleAnalysis.t.h.search(n.getItem());
        //System.out.println(h.getChain().getItem());
        if(h.getChain()==null){
            
            h.setChain(n);
           // System.out.println(h.getChain().getItem());
        }
        else{
            Node curr=h.getChain();
            while(curr.getIntLink()!=null)
                curr=curr.getIntLink();
            curr.setIntLink(n);
           //System.out.println(curr.getIntLink().getItem());
        }
      
     
      if(n.i!=0){
        int j=0;
       Node current[]=n.getNext();
       Node prev=n;
      
       while(j<n.i){
       setinternalLinks(current[j]);
       j++;}
      }
    }
    
}




