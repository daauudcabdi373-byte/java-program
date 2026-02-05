
import java.util.Scanner;

public class SKMotorsAuction {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        
        System.out.println("=== SK Motors Vehicle Entry ===");
        System.out.print("Enter Registration Number: ");
        String regNo = input.nextLine();
        
        System.out.print("Enter Initial Vehicle Cost: ");
        double vehicleCost = input.nextDouble();
        
        System.out.print("Enter Any Existing Balance/Debt left on vehicle: ");
        double existingBalance = input.nextDouble(); 
        
        System.out.print("Enter Expenses incurred (Repairs/Admin): ");
        double expenses = input.nextDouble();

        
        System.out.println("\n=== Auction Bidding (3 Bidders) ===");
        double highestBid = 0;
        String winner = "";

        int i = 1;
        while (i <= 3) {
            System.out.print("Enter Name for Bidder " + i + ": ");
            input.nextLine(); 
            String name = input.nextLine();
            System.out.print("Enter Bid Price for " + name + ": ");
            double currentBid = input.nextDouble();

            if (currentBid > highestBid) {
                highestBid = currentBid;
                winner = name;
            }
            i++;
        }

        System.out.println("\n--- Auction Result ---");
        System.out.println("Highest Bidder: " + winner + " | Price: " + highestBid);

        
        System.out.print("\nEnter Deposit made by " + winner + ": ");
        double deposit = input.nextDouble();
        
        double bidderBalance = highestBid - deposit;

      
        double totalLiabilities = vehicleCost + expenses + existingBalance;
        double profitLoss = highestBid - totalLiabilities;

       
        System.out.println("\n=====================================");
        System.out.println("       VEHICLE SALE SUMMARY          ");
        System.out.println("=====================================");
        System.out.println("Reg Number        : " + regNo);
        System.out.println("Initial Cost      : " + vehicleCost);
        System.out.println("Existing Balance  : " + existingBalance); // Displayed as required
        System.out.println("Expenses Incurred : " + expenses);
        System.out.println("-------------------------------------");
        System.out.println("Sold to           : " + winner);
        System.out.println("Final Sale Price  : " + highestBid);
        System.out.println("Deposit Paid      : " + deposit);
        System.out.println("Bidder Balance Due: " + bidderBalance);
        System.out.println("-------------------------------------");

        if (profitLoss > 0) {
            System.out.println("FINAL STATUS      : PROFIT OF " + profitLoss);
        } else if (profitLoss < 0) {
            System.out.println("FINAL STATUS      : LOSS OF " + Math.abs(profitLoss));
        } else {
            System.out.println("FINAL STATUS      : BREAK-EVEN");
        }
        System.out.println("=====================================");

        input.close();
    }
}
