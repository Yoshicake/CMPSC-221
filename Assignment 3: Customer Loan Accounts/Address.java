/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw3;

/**
 *
 * @author pacma
 */
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipcode;
    
    public Address(String street, String city, String state, String zipcode){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        
    }
    
    public String getStreet() {
        return street;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getstate() {
        return state;
    }
    
    public String getZipCode() {
        return zipcode;
    }
    
    @Override
    public String toString() {
        return "Property Address: \n" +
                "   " + street + "\n   " + city + ", " + state + " " + zipcode;
    }
}
