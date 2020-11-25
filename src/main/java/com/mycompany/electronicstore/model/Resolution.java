/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

/**
 *
 * @author dmytr
 */
public enum Resolution {
   QHD{
     public String getResolution(){return "QHD";}
   },
   HD{
     public String getResolution(){return "HD";}
   },
   FULL_HD{
     public String getResolution(){return "FULL_HD";}
   } ,
   WQHD{
     public String getResolution(){return "WQHD";}
   },
   UHD{
     public String getResolution(){return "UHD";}
   },
   EIGHT_K{
     public String getResolution(){return "EIGHT_K";}
   };
   public abstract String getResolution();
}
