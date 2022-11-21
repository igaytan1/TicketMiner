import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {
    public static void chooseEvent(HashMap<String, Customer> customerData, HashMap<Integer, Event> eventData,
        String userFirstName, String userLastName, String userEventIDChoiceString, boolean transactionNotDone,
        Logger writerIn) {
        Scanner in = new Scanner(System.in);
        System.out.println("");
        System.out.println("To view Event Prices, please enter an Event ID [1-71]");
        System.out.print("> ");
        userEventIDChoiceString = in.next();
        in.nextLine();
        int userEventIDChoice;

        try{
            userEventIDChoice = Integer.parseInt(userEventIDChoiceString);
            System.out.println(" ");
            writerIn.log("User " + userFirstName + " " + userLastName + " chose to view event prices for  '" + eventData.get(userEventIDChoice).getEventName() + "' with ID " + userEventIDChoice);
            while(transactionNotDone){

                if(eventData.containsKey(userEventIDChoice)){
                    displayTicketPrices(eventData, userEventIDChoice);
                    chooseTicket(eventData, customerData, userEventIDChoice, writerIn, userFirstName, userLastName);
                    transactionNotDone = false;
                }
            }
        }catch(Exception e){
            System.out.println(" ");
            System.out.println("The Event ID you entered does not exist! Please press any key to try again");
            System.out.print("> ");
            in.next();
            chooseEvent(customerData, eventData, userFirstName, userLastName, userEventIDChoiceString, transactionNotDone, writerIn);
        }
    }

    public static void displayTicketPrices(HashMap <Integer, Event> eventDataIn, int userEventIDChoiceIn){
        System.out.println("");
        System.out.println("Below is the Price Information for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with the Event ID entered: " + userEventIDChoiceIn);
        System.out.println("[1] VIP Ticket Price: $" + eventDataIn.get(userEventIDChoiceIn).getVIPPrice());
        System.out.println("[2] Gold Ticket Price: $" + eventDataIn.get(userEventIDChoiceIn).getGoldPrice());
        System.out.println("[3] Silver Ticket Price: $" + eventDataIn.get(userEventIDChoiceIn).getSilverPrice());
        System.out.println("[4] Bronze Ticket Price: $" + eventDataIn.get(userEventIDChoiceIn).getBronzePrice());
        System.out.println("[5] General Ticket Admission Price: $" + eventDataIn.get(userEventIDChoiceIn).getGaPrice());
        System.out.println("");
    }


    //Method that prompts the user to choose a ticket

    public static void  chooseTicket(HashMap<Integer, Event> eventDataIn, HashMap<String, Customer> customerDataIn, int userEventIDChoiceIn, Logger writerIn, String userFirstNameIn, String userLastNameIn){
        Scanner in = new Scanner(System.in);
        System.out.println("Which Ticket would you like to purchase? Please enter a number [1-5]");
        System.out.print("> ");
        String userTicketChoiceIntString = in.next();
        System.out.println(" ");
        
        //send to method
        try {
            int userTicketChoiceInt = Integer.parseInt(userTicketChoiceIntString);
            if(userTicketChoiceInt > 5 || userTicketChoiceInt < 1){
                System.out.println(" ");
                System.out.println("Please enter a valid menu option");
                System.out.println("Press any key to try again");
                System.out.print("> ");
                in.next();
                chooseTicket(eventDataIn, customerDataIn, userEventIDChoiceIn, writerIn, userFirstNameIn, userLastNameIn);
                
            } 
            System.out.println(" ");

            //User chose VIP Ticket 
            if(userTicketChoiceInt == 1){
                String ticketType = "VIP";
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketType, writerIn, userFirstNameIn, userLastNameIn);
            }
        
            //User chose Gold Ticket
            else if(userTicketChoiceInt == 2){
                String ticketType = "Gold";
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketType, writerIn, userFirstNameIn, userLastNameIn);
            }

            //User chose Silver Ticket
            else if(userTicketChoiceInt == 3){
                String ticketType = "Silver";
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketType, writerIn, userFirstNameIn, userLastNameIn);
            }

            //User chose Bronze Ticket
            else if(userTicketChoiceInt == 4){
                String ticketType = "Bronze";
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketType, writerIn, userFirstNameIn, userLastNameIn);
            }

            //User chose General Admission Ticket
            else if(userTicketChoiceInt == 5){
                String ticketType = "Ga";
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketType, writerIn, userFirstNameIn, userLastNameIn);
            }
        } catch (Exception e) {
            System.out.println(" ");
            System.out.println("Invalid Input, press any key to try again");
            System.out.println("> ");
            in.next();
            chooseTicket(eventDataIn, customerDataIn, userEventIDChoiceIn, writerIn, userFirstNameIn, userLastNameIn);
        }
    }


    //Method prompting user about transaction

    public static void ticketTransaction(HashMap<Integer, Event> eventDataIn, HashMap<String, Customer> customerDataIn, int userEventIDChoiceIn,  String ticketTypeIn, Logger writerIn, String userFirstNameIn, String userLastNameIn){
        Scanner in = new Scanner(System.in);
        //boolean transactionNotDone = true;
        double totalPrice = 0.0;
        double salesTax = (8.25/100);
        double thisSalesTax = 0;
        System.out.println("How many " + ticketTypeIn + " Tickets would you like to purchase? (Ticket Limit = 6)");
        System.out.print("> ");
        String userNumOfTicketsString = in.nextLine();
        
        try {
            int userNumOfTickets = Integer.parseInt(userNumOfTicketsString);
            if(userNumOfTickets > 6){
                System.out.println(" ");
                System.out.println("Please enter 6 tickets or less");
                System.out.println(" ");
                System.out.println("Press any key to try again");
                in.next();
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketTypeIn, writerIn, userFirstNameIn, userLastNameIn);
            }
            int ticketCounter = userNumOfTickets;
            writerIn.log("User " + userFirstNameIn + " " + userLastNameIn + " wants to purchase " + userNumOfTickets + " " +  ticketTypeIn + " tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
            
            //Verifies that there are still VIP tickets available
            if(ticketTypeIn.equalsIgnoreCase("VIP")){
                if(userNumOfTickets <= eventDataIn.get(userEventIDChoiceIn).getVenueType().getVipPct()){
                    while(ticketCounter > 0){
                        totalPrice += (eventDataIn.get(userEventIDChoiceIn).getVIPPrice());
                        ticketCounter = ticketCounter - 1;
                    }


                    //Adding sales tax to total price + discount if applicable
                    thisSalesTax = (totalPrice * salesTax);
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn);

                    //Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    //Displays user account info 
                    System.out.println("");
                    System.out.println("Your total for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' is $" + customerTicket.getTotalPrice());
                    System.out.println("You currently have $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars in your account");
                    writerIn.log("User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase VIP Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
                    
                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        System.out.println("You do not have enough money to purchase tickets!!!!");
                        return;
                    }
                }
                else{
                    System.out.println("There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
                }
            }
            else if(ticketTypeIn.equalsIgnoreCase("Gold")){
                if(userNumOfTickets <= eventDataIn.get(userEventIDChoiceIn).getVenueType().getGoldPct()){
                    while(ticketCounter > 0){
                        totalPrice += eventDataIn.get(userEventIDChoiceIn).getGoldPrice();
                        ticketCounter = ticketCounter - 1;
                    }

                    //Adding sales tax to total price + discount if applicable
                    thisSalesTax = (totalPrice * salesTax);
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn);

                    //Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    //Displays user account info 
                    System.out.println("");
                    System.out.println("Your total for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' is $" + customerTicket.getTotalPrice());
                    System.out.println("You currently have $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars in your account");
                    writerIn.log("User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase VIP Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
                    
                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        System.out.println("You do not have enough money to purchase tickets!!!!");
                        return;
                    }
                }
                else{
                    System.out.println("There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
                }
            }
            else if(ticketTypeIn.equalsIgnoreCase("Silver")){
                if(userNumOfTickets <= eventDataIn.get(userEventIDChoiceIn).getVenueType().getSilverPct()){
                    while(ticketCounter > 0){
                        totalPrice += eventDataIn.get(userEventIDChoiceIn).getSilverPrice();
                        ticketCounter = ticketCounter - 1;
                    }

                    //Adding sales tax to total price + discount if applicable
                    thisSalesTax = (totalPrice * salesTax);
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn);

                    //Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    //Displays user account info 
                    System.out.println("");
                    System.out.println("Your total for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' is $" + customerTicket.getTotalPrice());
                    System.out.println("You currently have $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars in your account");
                    writerIn.log("User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase VIP Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
                    
                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        System.out.println("You do not have enough money to purchase tickets!!!!");
                        return;
                    }
                }
                else{
                    System.out.println("There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
                }
            }
            else if(ticketTypeIn.equalsIgnoreCase("Bronze")){
                if(userNumOfTickets <= eventDataIn.get(userEventIDChoiceIn).getVenueType().getBronzePct()){
                    while(ticketCounter > 0){
                        totalPrice += eventDataIn.get(userEventIDChoiceIn).getBronzePrice();
                        ticketCounter = ticketCounter - 1;
                    }

                   //Adding sales tax to total price + discount if applicable
                    thisSalesTax = (totalPrice * salesTax);
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn);


                    //Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    //Displays user account info 
                    System.out.println("");
                    System.out.println("Your total for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' is $" + customerTicket.getTotalPrice());
                    System.out.println("You currently have $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars in your account");
                    writerIn.log("User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase VIP Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
                    
                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        System.out.println("You do not have enough money to purchase tickets!!!!");
                        return;
                    }
                }
                else{
                    System.out.println("There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
                }
            }
            else if(ticketTypeIn.equalsIgnoreCase("GA")){
                if(userNumOfTickets <= eventDataIn.get(userEventIDChoiceIn).getVenueType().getGaPct()){
                    while(ticketCounter > 0){
                        totalPrice += eventDataIn.get(userEventIDChoiceIn).getGaPrice();
                        ticketCounter = ticketCounter - 1;
                    }

                    //Adding sales tax to total price + discount if applicable
                    thisSalesTax = (totalPrice * salesTax);
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn);


                    //Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    //Displays user account info 
                    System.out.println("");
                    System.out.println("Your total for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' is $" + customerTicket.getTotalPrice());
                    System.out.println("You currently have $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars in your account");
                    writerIn.log("User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase VIP Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
                    
                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        System.out.println("You do not have enough money to purchase tickets!!!!");
                        return;
                    }
                }
                else{
                    System.out.println("There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
                }
            }
        } catch (Exception e) {
            //System.out.print(e);
            System.out.println(" ");
            System.out.println("Inavalid Input, press any key to try again");
            System.out.print("> ");
            in.next();
            ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketTypeIn, writerIn, userFirstNameIn, userLastNameIn);
        }
    }


    //Method to Process Transactions

    public static void processPurchase(HashMap<Integer, Event> eventDataIn, HashMap<String, Customer> customerDataIn, Ticket customerTicketIn, int userEventIDChoiceIn, String userFirstNameIn, String userLastNameIn, Logger writerIn, String ticketTypeIn, int userNumOfTicketsIn){
        Scanner in = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("Would you like to continue this transaction? [Yes/No]");
        System.out.print("> ");
        String userTransactionChoice = in.next();
        System.out.println(" ");

        //Processess purchase and prints out ticket information including num of tickets, confirmation number, and total price for the transaction
        
        if(userTransactionChoice.equalsIgnoreCase("Yes")){
            System.out.println(" ");
            eventDataIn.get(userEventIDChoiceIn).ticketObject(customerTicketIn, eventDataIn.get(userEventIDChoiceIn), customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()));
            customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).setCustomerTicket(customerTicketIn);
            writerIn.log("User " + userFirstNameIn + " " + userLastNameIn + " has successfully purchased " + ticketTypeIn + " Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
            writerIn.log("User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " available after completing transaction");

            //Update Customer info
            customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).updateConcertsPurchased();
            customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).updateTicketsPurchased(userNumOfTicketsIn);
            customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).updateTransactionCount();

            //Updating percentage of available tickets/seats

            eventDataIn.get(userEventIDChoiceIn).updatePctSeatsRemaining(eventDataIn.get(userEventIDChoiceIn), ticketTypeIn, userNumOfTicketsIn);
            
            //Transaction is done so we can exit loop
            return;
        }
        else if(userTransactionChoice.equalsIgnoreCase("No")){
            return;
        }
        else{
            System.out.println(" ");
            System.out.println("Invalid input, press any key to try again");
            System.out.print("> ");
            in.next();
            processPurchase(eventDataIn, customerDataIn,customerTicketIn, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTicketsIn);
        }
    }

     //Method that rounds the total price to the nearest decimal 
     public static double roundToDec(double totalPriceIn){
        BigDecimal instance = new BigDecimal(Double.toString(totalPriceIn));
        instance = instance.setScale(2,RoundingMode.HALF_UP);

        return instance.doubleValue();
    }


    //Method that calculates the sales tax and applies discount 
    public static double salesTaxPrint(double thisSalesTaxIn, double totalPrice, HashMap<String, Customer> customerDataIn, String userFirstNameIn, String userLastNameIn, HashMap<Integer, Event> eventDataIn, int userEventIDChoiceIn){
        Scanner in = new Scanner(System.in);
        double totalPriceFinal = totalPrice + thisSalesTaxIn;
        double discount = (totalPrice * 0.1);
        
        try {
            //User is a ticket miner member so we apply discount
            if(customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).isTicketMinerMember()){
                System.out.println(" ");
                System.out.println("Congrats on being a ticketminer member! You get 10% off your ticket order!!!");
                System.out.println("Your original price: $" + totalPrice);
                System.out.println(" ");
                
                totalPrice = totalPrice - discount;
                totalPrice = roundToDec(totalPrice);
                System.out.println("Price with discount: $" + totalPrice);
                System.out.println(" ");

                System.out.println("Total Price (Before Sales Tax): $" + roundToDec(totalPrice));
                System.out.println("This Sales Tax: $" + thisSalesTaxIn);
                totalPriceFinal = roundToDec(totalPrice + thisSalesTaxIn);
                System.out.println("Total Price Including Salex Tax: $" + totalPriceFinal);

                //Update customer money saved
                customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).setMoneySaved(customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).getMoneySaved() + discount);


                //Update event discount given
                eventDataIn.get(userEventIDChoiceIn).setAmountDiscounted(eventDataIn.get(userEventIDChoiceIn).getAmountDiscounted() + discount);
            }   

            //User is not a ticket miner member
            else{
                System.out.println("Total Price (Before Sales Tax): $" + roundToDec(totalPrice));
                System.out.println("This Sales Tax: $" + thisSalesTaxIn);
                totalPriceFinal = roundToDec(totalPrice + thisSalesTaxIn);
                System.out.println("Total Price Including Salex Tax: $" + totalPriceFinal);
            }
        } catch (Exception e) {
            System.out.println("There was a problem calculating the price!");
        }
        return totalPriceFinal;
    }

}
