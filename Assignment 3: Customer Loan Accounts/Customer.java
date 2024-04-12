/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw3;
import java.util.ArrayList;
/**
 *
 * @author pacma
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String SSN;
    private ArrayList<LoanAccount> loanAccounts;
    
    public Customer(String firstName, String lastName, String SSN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        loanAccounts = new ArrayList<>();
    }
    
    public String getFirstName(){
            return firstName;
        }
    public String getLastName(){
            return lastName;
        }
    public String getSSN(){
            return SSN;
        }
    public void addLoanAccount(LoanAccount account){
        loanAccounts.add(account);
    }
    public void printMonthlyReport(){
        System.out.println("Account Report for Customer: " + firstName + " " + lastName + " with SSN " + SSN);
        System.out.println(" ");
        for (LoanAccount loan:loanAccounts) {
            System.out.print(loan.toString());
        }
        System.out.println(" ");
    }
    
    
}
