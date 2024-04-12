/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw3;

/**
 *
 * @author pacma
 */
public class PrimaryMortgage extends LoanAccount {
    private double PMIMonthlyAmount;
    private Address address;
    
    public PrimaryMortgage(double initPrincipal, double initInterestRate, int initMonths, double initPMI, Address initAddress){
        super(initPrincipal, initInterestRate, initMonths);
        PMIMonthlyAmount = initPMI;
        address = initAddress;
    }
    
    @Override
    public String toString() {
        return "Primary Mortgage Loan with: \n" + super.toString() + "\nPMI Monthly Amount: $" + PMIMonthlyAmount + "\n" + address.toString() + "\n\n";
    }
    
    
}
