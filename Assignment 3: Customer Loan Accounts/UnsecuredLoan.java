/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw3;

/**
 *
 * @author pacma
 */
public class UnsecuredLoan extends LoanAccount {
    public UnsecuredLoan(double initPrincipal, double initInterestRate, int initMonths) {
        super(initPrincipal, initInterestRate, initMonths);
    }
    
    @Override
    public String toString(){
        return "Unsecured Loan with: \n" + super.toString() + "\n\n";
    }
}
