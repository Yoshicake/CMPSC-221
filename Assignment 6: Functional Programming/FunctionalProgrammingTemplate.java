/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package functionalprogrammingtemplate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;

/**
 *
 * @author acv
 */
public class FunctionalProgrammingTemplate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // create the ArrayList of Invoices
        List<Invoice> invoices = List.of(
        new Invoice(83,"Electric sander", 7, 57.98),
        new Invoice(24,"Power saw", 18, 99.99),
        new Invoice(7,"Sledge hammer", 11, 21.50),
        new Invoice(77,"Hammer", 76, 11.99),
        new Invoice(39,"Lawn mower", 3, 79.50),
        new Invoice(68,"Screw driver", 106, 6.99),
        new Invoice(56,"Jig saw", 21, 11.00),
        new Invoice(3,"Wrench", 34, 7.50));
        
        //Display the table of invoices using Invoice toString().
        //Print table header.
        System.out.println("Part number\tPart description\tQuantity\tPrice per item\t   Value");
        invoices.stream()
                .forEach(System.out::print);
        
        //a)Use streams to sort Invoice object by partDecsription, then display the results.
        System.out.println(" ");
        Function<Invoice, String> byDescription = Invoice::getPartDescription;
        Comparator<Invoice> partDescriptionCompare = Comparator.comparing(byDescription);
        System.out.println("Invoices sorted by Part Description");
        System.out.println("Part number\tPart description\tQuantity\tPrice per item\t   Value");
        invoices.stream()
                .sorted(partDescriptionCompare)
                .forEach(System.out::print);
        
        
        //b)Use streams to sort Invoice object by price, then display the results.
        System.out.println(" ");
        Function<Invoice, Double> byPrice = Invoice::getPricePerItem;
        Comparator<Invoice> priceCompare = Comparator.comparing(byPrice);
        System.out.println("Invoices sorted by Price");
        System.out.println("Part number\tPart description\tQuantity\tPrice per item\t   Value");
        invoices.stream()
                .sorted(priceCompare)
                .forEach(System.out::print);
        
        //c)Use streams to map each Invoice to its partDescription and quantity, 
        //  then display the results.
        System.out.println(" ");
        Function<Invoice, Integer> byQuantity = Invoice::getQuantity;
        Comparator<Invoice> quantityCompare = Comparator.comparing(byQuantity);
        System.out.println("Part Description and Quantity for each Invoice");
        System.out.printf("%-20s %s%n", "Part description", "Quantity");
        
        List<Map.Entry<String, Integer>> sortedByQuantity = invoices.stream()
                .sorted(quantityCompare)
                .collect(Collectors.toList())
                .stream()
                .map(entry -> Map.entry(entry.getPartDescription(), entry.getQuantity()))
                .collect(Collectors.toList());
        
        
        sortedByQuantity.forEach(entry ->
                System.out.printf("%-20s%9d%n", entry.getKey(), entry.getValue()));               
    }
    
}
