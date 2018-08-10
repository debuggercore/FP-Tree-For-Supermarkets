/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adsassignment;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author SUJITHA
 */
public class conditionalbasepattern {
  ItemTable items; 
    
 pnode[] base_fp_list=new pnode[100];  
   int i=0;
   //for each item from header table 
        //extract all paths leading to the item.
        //Consider each path as a transaction and construct fp tree called conditional fp tree.
 void createfplist(HeaderTable H , ItemTable it , FPTree fp  ){
  Header h =H.first ;
  while(h!=null){
  base_fp_list[i] =new pnode();
   base_fp_list[i].setname(h.getItem());
   String s ="";
   Node curr=h.getChain();
   String arr[][]=new String[100][100];
   int j=0,k=0;
   while(curr!=null){
        Node curr1=curr;
       while(curr1.getPrev().getItem()!=fp.root.getItem()){
           s+=curr1.getPrev().getItem()+" , ";
          // arr[j][k]=curr1.getPrev().getItem();
          // k++;
            curr1=curr1.getPrev();
            
            
       }
      // j++;
      // k=0;
       //s+=curr.freq;
       if(!s.equals("")){
       base_fp_list[i].addtolist(s,curr.freq);
       base_fp_list[i].freq=curr.freq;
       }
       s=" ";
       curr=curr.getIntLink();
       
       
   }
   System.out.println("\n---Conditional Base pattern for base_fp_list "+i+" "+h.getItem()+"---"); 
   base_fp_list[i].display(); 
   
   if(base_fp_list[i].i>0){
       items=new ItemTable();
   items.fillItemTable(base_fp_list[i].list,base_fp_list[i].i);
   FPTree fp1=new FPTree();
       for(int t=0;t<items.size;t++)
           fp1.insertCondFP(items.list[t], items.h,base_fp_list[i],t);
      
      
       System.out.println("---Conditional FP Tree---");
       fp1.display();
       //extra
   }
        //c.createfplist(items.h, items, fp1);
   i++;
   h=h.getNext();
  }
   
 }
 
 
 void display(){
  for(int j=0;j<i;j++){
      System.out.println(base_fp_list[j].name);
              base_fp_list[j].display();
              System.out.println();
  }
     
 }
 
 
    
}

class pnode{
    String  name ;
    int freq;
   
  String list[][]=new String[100][100];
  int i=0;
 // list[i]="";
  String getname(){
     return name ; 
  
  }
  void setname(String s){
   name= s;  
      
  }
   
  void addtolist(String s, int f){
      String[] s1;//=new String[100];
      s1=s.split(" , ");
      int j;
      if(s==null)
          System.out.println("is null !!!");
      
      else{
      for( j=0;j<s1.length;j++){
          
          list[i][j+1]=s1[j];
      }
      
   list[i][0] = String.valueOf(f);
    
    i++;}
    
  }
  
 /* String[] returnlist(){
      
    return list ;  
  }
  */
  void display(){
      String t ;
      for(int k=0;k<i;k++)
      for(int j=0;j<list[k].length;j++){
          System.out.print(list[k][j]+", ");
         
      //t=list[j];
      }
      System.out.println();
  }
  
  
  
   int getFrequency(int i){
      return Integer.parseInt(list[i][0]);
  }
}




