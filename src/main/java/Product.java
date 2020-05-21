/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author weizhang
 */
public class Product {

    private String pid;
    private double price;
    private String name;
    
    public Product(String pid, double price, String name){
        this.pid = pid;
        this.price = price;
        this.name = name;
    }
    
    public String getPid(){
        return this.pid;
    }
    
    public double getPrice(){
        return this.price;
    }
    
    public String getName(){
        return this.name;
    }
}
