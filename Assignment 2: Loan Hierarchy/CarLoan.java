/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw2;

/**
 *
 * @author pacma
 */
public class CarLoan extends LoanAccount {
    private final String vehicleVIN;
    
    
    
    public CarLoan(double initPrincipal, double initInterestRate, int initMonths, String initVIN){
        super(initPrincipal, initInterestRate, initMonths);
        vehicleVIN = initVIN;
        
    }
    
    @Override
    public String toString() {
        
        return "Car Loan with: \n" + super.toString() + "\n" + "VehicleVIN: " + vehicleVIN + "\n\n";
    }
}
