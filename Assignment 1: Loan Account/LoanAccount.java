/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loanaccount;



/**
 *
 * @author pacma
 */
public class LoanAccount {
    private static double annualInterestRate;
    private final double principal;
    public LoanAccount(double initPrincipal){
        principal = initPrincipal;
    }
    public double calculateMonthlyPayment(int numberOfPayments){
        double monthlyInterest = (annualInterestRate/100)/12;
        double monthlyPayment = principal * ( monthlyInterest / (1 - Math.pow(1 + monthlyInterest, -numberOfPayments)));
        return monthlyPayment;
    }
    
    public static void setAnnualInterestRate(double interestRate) {
        annualInterestRate = interestRate;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoanAccount loan1 = new LoanAccount(5000);
        LoanAccount loan2 = new LoanAccount(31000);
        
        setAnnualInterestRate(1);
        
        System.out.println("Monthly payments for loan1 of $5000.00 and loan2 $31000.00 for 3, 5, and 6 year loans at 1% interest.");
        System.out.println("Loan    3 years 5 years 6 years");
        System.out.println("Loan1   " + String.format("%.2f", loan1.calculateMonthlyPayment(36)) + "  " +
                String.format("%.2f", loan1.calculateMonthlyPayment(60)) + "   " + String.format("%.2f", loan1.calculateMonthlyPayment(72)));
        System.out.println("Loan2   " + String.format("%.2f", loan2.calculateMonthlyPayment(36)) + "  " +
                String.format("%.2f", loan2.calculateMonthlyPayment(60)) + "  " + String.format("%.2f", loan2.calculateMonthlyPayment(72)));
        
        
        setAnnualInterestRate(5);
        
        System.out.println("Monthly payments for loan1 of $5000.00 and loan2 $31000.00 for 3, 5, and 6 year loans at 5% interest.");
        System.out.println("Loan    3 years 5 years 6 years");
        System.out.println("Loan1   " + String.format("%.2f", loan1.calculateMonthlyPayment(36)) + "  " +
                String.format("%.2f", loan1.calculateMonthlyPayment(60)) + "   " + String.format("%.2f", loan1.calculateMonthlyPayment(72)));
        System.out.println("Loan2   " + String.format("%.2f", loan2.calculateMonthlyPayment(36)) + "  " +
                String.format("%.2f", loan2.calculateMonthlyPayment(60)) + "  " + String.format("%.2f", loan2.calculateMonthlyPayment(72)));
    }
    
}
