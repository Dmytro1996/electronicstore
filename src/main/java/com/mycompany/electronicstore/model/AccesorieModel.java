/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

/*import electronicstore.exceptions.BooleanException;
import electronicstore.exceptions.NumericValueException;
import electronicstore.exceptions.ResolutionException;
import electronicstore.exceptions.StringFieldException;*/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
/**
 *
 * @author dmytr
 */
@Entity
@Table(name="Accesories")
public class AccesorieModel extends CommodityModel{
    @Column
    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Wrong accesorie name.Must start with a capital letter followed by one or more lowercase letters")
    private String name;
    @Column(name="short_description")
    private String shortDescription;
    
    public AccesorieModel(){
      super();
    }   

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setName(String name){        
        this.name = name;
    }

    public void setShortDescription(String shortDescription){        
        this.shortDescription = shortDescription;
    }
    
    public String toString(){
      return name+" "+getBrand().getName()+" "+getModel()+" Price"+getPrice()
              +" Short Description: "+shortDescription;
    }

    public String addImage(){
        return "<img src=\"\\images\\acc-"+getName().toLowerCase()+
              ".jpg\" alt=\""+getName()+" "+getBrand().getName()+" "
              +getModel()+"\">";
    }

    public String toHTML(){
      return "<p id=\"accType\">"+name+"</p>"
              +"<p id=\"brandModel\">"+getBrand().getName()+" "+getModel()+"</p>"
              +"<p id=\"shortDesc\">Short description::"+shortDescription+"</p>"              
              +"<p id=\"price\">Price:"+getPrice()+"</p>" 
              + "<input type=\"button\" id=\"comm"+getId()+"\" class=\"buy\" value=\"Buy\">";
    }    
}
