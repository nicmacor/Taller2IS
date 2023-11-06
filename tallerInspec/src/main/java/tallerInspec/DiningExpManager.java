package tallerInspec;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DiningExpManager {
	// Private constructor to prevent instantiation
    private DiningExpManager() {
        throw new UnsupportedOperationException("No constructor allowed");
    }
    
	public static void main(String[] args) {
		
		//Menu meals and options
        Map<String, Double> menu = new HashMap<>();
        menu.put("hot dog", 10.0);
        menu.put("burger", 12.0);
        menu.put("pizza", 8.0);
        menu.put("salad", 5.0);
        

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> order = new HashMap<>(); //new order
        double totalCost = 5.0; // Base cost for every meal

        // Display menu
        System.out.println("Menu:");
        for (String item : menu.keySet()) {
            System.out.println(item + ": $" + menu.get(item));
        }

        // Get meals and amount
        boolean continuar1 = true;
        
        while (continuar1) {
            System.out.print("Enter the name of the meal you want to order (or 'done' to finish): ");
            String item = scanner.nextLine();
            if (item.equalsIgnoreCase("done")) { //NOPMD
                continuar1=false;
            }

            if (menu.containsKey(item)) {
                System.out.print("Enter the quantity: ");
                int quantity = scanner.nextInt();
                if (quantity > 0 && quantity <= 100) {
                    order.put(item, quantity);
                    totalCost += menu.get(item) * quantity;
                } else {
                    System.out.println("Quantity must be a positive integer between 1 and 100.");
                }
                scanner.nextLine();
            } 
            if (!(menu.containsKey(item)) && !(item.equalsIgnoreCase("done"))){ // NOPMD
                System.out.println("Invalid meal selection. Please select from the menu.");
            }
    
        }
        
        boolean continue2 = true;
        while (continue2) {
        	// Display the order and total cost without discount to confirm
            System.out.println("Your order:");
            for (String item : order.keySet()) {
                System.out.println(item + " x" + order.get(item));
            }
            System.out.println("Total cost without discounts: $" + totalCost);

            System.out.print("Confirm order (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) { // NOPMD
            	
            	// Apply discounts for more than 10 meals or more than 5
            	if (order.values().stream().mapToInt(Integer::intValue).sum() > 10) {
                    totalCost = totalCost- totalCost*0.2; // Additional 20% discount
                }else if (order.values().stream().mapToInt(Integer::intValue).sum() > 5) {
                    totalCost = totalCost - totalCost*0.1; // 10% discount
                }

                // Apply special offer discounts for total cost
                if (totalCost > 100.0) {
                    totalCost -= 25.0;
                }else if (totalCost > 50.0) {
                    totalCost -= 10.0;
                }
                
                System.out.println("Order confirmed. Enjoy your meal!");
                System.out.println("Total cost: " + totalCost);
                continue2 = false;
            } else {
                System.out.print("You want to cancel or change. (cancel/change): "); // Cancel or change order
                String modify = scanner.nextLine();
                if(modify.equals("change")) { // NOPMD
                	System.out.print("Which meal you want to modify? ");
                	String change = scanner.nextLine();
                	if (order.containsKey(change)) { 
                		System.out.print("Enter new cuantity: ");
                		int newQuantity = scanner.nextInt();
                		if(newQuantity>0 && newQuantity <=100) {
                			totalCost -= menu.get(change) * order.get(change);
                			order.put(change, newQuantity);
                			totalCost += menu.get(change)*newQuantity;
                		}else {
                			System.out.print("Quantity muste be a number between 1 and 100");
                		}
                		scanner.nextLine();
                	}else {
                		System.out.println("You dont have this meal");
                	}
                	
                }else { // Cancel order
                	System.out.println("Order canceled, have a great day");
                	System.out.println("-1");
                	continue2=false;
                	
                }
            }
        }
        scanner.close();
        
	}
}
