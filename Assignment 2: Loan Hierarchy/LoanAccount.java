/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw2;

/**
 *
 * @author pacma
 */
public class LoanAccount {
    private final double annualInterestRate;
    private final double principal;
    private final int months;
    public LoanAccount(double initPrincipal, double initInterestRate, int initMonths){
        principal = initPrincipal;
        annualInterestRate = initInterestRate;
        months = initMonths;
    }
    public double calculateMonthlyPayment(){
        double monthlyInterest = (annualInterestRate/100)/12;
        double monthlyPayment = principal * ( monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -months)));
        return monthlyPayment;
    }
    
    public double getAnnualInterestRate(){
        return annualInterestRate;
    }
    
    public double getPrincipal(){
        return principal;
    }
    
    public int getMonths(){
        return months;
    }
    
    @Override
    public String toString(){
        return 
        "Principal: $" + String.format("%.2f", principal) + "\n" +
        "Annual Interest Rate: " + String.format("%.2f", annualInterestRate)+"%" + "\n" +
        "Term of Loan in Months: " + months + "\n" +
        "Monthly Payment: $" + String.format("%.2f", calculateMonthlyPayment());
    }
    
}
