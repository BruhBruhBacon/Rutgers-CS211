package restaurant;
/**
 * Use this class to test your Menu method. 
 * This class takes in two arguments:
 * - args[0] is the menu input file
 * - args[1] is the output file
 * 
 * This class:
 * - Reads the input and output file names from args
 * - Instantiates a new RUHungry object
 * - Calls the menu() method 
 * - Sets standard output to the output and prints the restaurant
 *   to that file
 * 
 * To run: java -cp bin restaurant.Menu menu.in menu.out
 * 
 */
public class Menu {
    public static void main(String[] args) {

	// 1. Read input files
	// Option to hardcode these values if you don't want to use the command line arguments
	   
        String inputFile =  args[0];
        //String outputFile = args[1];
        // 2. Instantiate an RUHungry object
        RUHungry rh = new RUHungry();

    // 3. Call the menu() method to read the menu
        rh.menu(inputFile);

    // 4. Set output file
    // Option to remove this line if you want to print directly to the screen
        //StdOut.setFile(outputFile);
        rh.createStockHashTable("stock.in");
        //rh.updateStock(null,109,10);
        rh.updatePriceAndProfit();
        //TEST ORDER method

        /* 
        
        StdIn.setFile("order4.in");
        int numOrders = StdIn.readInt(); // Read the number of orders

        

        // Process each order
        for (int i = 0; i < numOrders; i++) {
            int amount = StdIn.readInt();
            StdIn.readChar(); // Read space
            String item = StdIn.readLine(); // Read dish name
            rh.order(item, amount); // Place the order
        }
         
        /// TEST donations
        StdIn.setFile("donate4.in");
        int numDonations = StdIn.readInt(); // Read the number of donations

        // Process each donation
        for (int i = 0; i < numDonations; i++) {
            int amount = StdIn.readInt();
            StdIn.readChar(); // Read space
            String item = StdIn.readLine(); // Read ingredient name
            rh.donation(item, amount); // Donate ingredients
        }
        

        // Process restocks
        StdIn.setFile("restock4.in");
        int numRestocks = StdIn.readInt(); // Read the number of restocks

        for (int i = 0; i < numRestocks; i++) {
                int amount = StdIn.readInt();
                StdIn.readChar(); // Read space
                String item = StdIn.readLine(); // Read ingredient name
                rh.restock(item, amount); // Restock ingredients
            }

            */

        //trans 
        StdIn.setFile("transaction4.in");
        int num = StdIn.readInt();

        for (int i = 0; i < num; i++) {
                String type = StdIn.readString();
                StdIn.readChar();
                int amount = StdIn.readInt();
                StdIn.readChar();
                String item = StdIn.readLine();
                if(type.equals("order")){
                        rh.order(item, amount);
                }
                if(type.equals("donation")){
                        rh.donation(item,amount);
                }
                if(type.equals("restock")){
                        rh.restock(item, amount);
                }
        }



        //javac -d bin src/restaurant/*.java
        //java -cp bin restaurant.Menu menu.in stock.in order1.in

	// 4. Set output file
	// Option to remove this line if you want to print directly to the screen
        //StdOut.setFile(outputFile);

	// 5. Print restaurant
        rh.printRestaurant();
    }
}
