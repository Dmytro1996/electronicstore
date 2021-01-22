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
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
/**
 *
 * @author dmytr
 */
@Entity
@Table(name="Accesories")
public class Accesorie extends Commodity{
    @Column
    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Wrong accesorie name.Must start with a capital letter followed by one or more lowercase letters")
    private String type;
    @Column(name="short_description")
    private String shortDescription;
    @ManyToMany(mappedBy="acc")
    private List<Order> orders;
    
    public Accesorie(){
      super();
    }   

    public String getType() {
        return type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setType(String type){        
        this.type = type;
    }

    public void setShortDescription(String shortDescription){        
        this.shortDescription = shortDescription;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public String toString(){
      return type+" "+getBrand().getName()+" "+getModel()+" Price"+getPrice()
              +" Short Description: "+shortDescription;
    }

    public String addImage(){
        return "<img src=\"\\images\\acc-"+getType().toLowerCase()+"-"
                +getBrand().getName().toLowerCase()+"-"+getModel().toLowerCase()+
                ".jpg\" alt=\""+getType()+" "+getBrand().getName()+" "
                +getModel()+"\">";
    }

    public String toHTML(){
      return "<p id=\"accType\">"+type+"</p>"
              +"<p id=\"brandModel\">"+getBrand().getName()+" "+getModel()+"</p>"
              +"<p id=\"shortDesc\">Short description::"+shortDescription+"</p>"              
              +"<p id=\"price\">Price:"+getPrice()+"</p>" 
              + "<form action=\"\\acc\\buy\\"+getId()+"\" method=\"POST\">"
              + "<input type=\"submit\" id=\"comm"+getId()+"\" class=\"buy\" value=\"Buy\">"
              + "</form>";
    }    
}
