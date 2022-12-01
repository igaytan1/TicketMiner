//Author: Isabella Gaytan
//Advanced Object-Oriented Programming

/*
* The Autopurchasing class deals with implementing the autopurchases derived from the given files.
*
* @author Isabella Gaytan
* 
* @since 2022-10-25
*/


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Scanner;

public class Autopurchasing {
    public static void chooseEvent(HashMap<String, Customer> customerData, HashMap<Integer, Event> eventData,
        String userFirstName, String userLastName, String userEventIDChoiceString, boolean transactionNotDone,
        Logger writerIn, String ticketTypeIn, String ticketQuantityIn) {

        int userEventIDChoice;

        try{
            userEventIDChoice = Integer.parseInt(userEventIDChoiceString);
            System.out.println(" ");
            writerIn.log("AUTOPURCHASING: User " + userFirstName + " " + userLastName + " chose to view event prices for  '" + eventData.get(userEventIDChoice).getEventName() + "' with ID " + userEventIDChoice);
            while(transactionNotDone){

                if(eventData.containsKey(userEventIDChoice)){
                    //displayTicketPrices(eventData, userEventIDChoice);
                    chooseTicket(eventData, customerData, userEventIDChoice, writerIn, userFirstName, userLastName, ticketTypeIn, ticketQuantityIn);
                    transactionNotDone = false;
                }
            }
        }catch(Exception e){
            System.out.println("There was a problem choosing the autopurchasing customer event!");
        }

    }


    // Autopurchasing Ticket Choice

    public static void  chooseTicket(HashMap<Integer, Event> eventDataIn, HashMap<String, Customer> customerDataIn, int userEventIDChoiceIn, Logger writerIn, String userFirstNameIn, String userLastNameIn, String ticketTypeIn, String ticketQuantityIn){
        
        try {

            // VIP Ticket 
            if(ticketTypeIn.equalsIgnoreCase("VIP")){
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketTypeIn, writerIn, userFirstNameIn, userLastNameIn, ticketQuantityIn);
            }
        
            // Gold Ticket
            else if(ticketTypeIn.equalsIgnoreCase(("Gold"))){
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketTypeIn, writerIn, userFirstNameIn, userLastNameIn, ticketQuantityIn);
            }

            // Silver Ticket
            else if(ticketTypeIn.equalsIgnoreCase("Silver")){
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketTypeIn, writerIn, userFirstNameIn, userLastNameIn, ticketQuantityIn);
            }

            // Bronze Ticket
            else if(ticketTypeIn.equalsIgnoreCase("Bronze")){
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketTypeIn, writerIn, userFirstNameIn, userLastNameIn, ticketQuantityIn);
            }

            // General Admission Ticket
            else if(ticketTypeIn.equalsIgnoreCase("General Admission")){
                ticketTransaction(eventDataIn, customerDataIn, userEventIDChoiceIn, ticketTypeIn, writerIn, userFirstNameIn, userLastNameIn, ticketQuantityIn);
            }
        } catch (Exception e) {
            System.out.println("AUTOPURCHASING: There was a problem with setting the ticket choice!");
            System.out.println(e);
        }
    }


    //Method prompting user about transaction

    public static void ticketTransaction(HashMap<Integer, Event> eventDataIn, HashMap<String, Customer> customerDataIn, int userEventIDChoiceIn,  String ticketTypeIn, Logger writerIn, String userFirstNameIn, String userLastNameIn, String ticketQuantityIn){
        double totalPrice = 0.0;
        double salesTax = (8.25/100);
        double thisSalesTax = 0;;
        
        try {
            int userNumOfTickets = Integer.parseInt(ticketQuantityIn);
            int ticketCounter = userNumOfTickets;
            writerIn.log("AUTOPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + " set to purchase " + userNumOfTickets + " " +  ticketTypeIn + " tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
            
            //Verifies that there are still VIP tickets available
            if(ticketTypeIn.equalsIgnoreCase("VIP")){
                if(userNumOfTickets <= eventDataIn.get(userEventIDChoiceIn).getVenueType().getVipPct()){
                    while(ticketCounter > 0){
                        totalPrice += (eventDataIn.get(userEventIDChoiceIn).getVIPPrice());
                        ticketCounter = ticketCounter - 1;
                    }

                    // Adding sales tax to total price + discount if applicable
                    thisSalesTax = (totalPrice * salesTax);
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn, writerIn);

                    // Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    // User account info 
                    writerIn.log("AUTOPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase VIP Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);

                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        writerIn.log("AUTPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + "does not have enough money to purchase tickets for Event " + eventDataIn.get(userEventIDChoiceIn));
                        return;
                    }
                }
                else{
                    writerIn.log("AUTOPURCHASING: There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
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
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn, writerIn);

                    //Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    //Displays user account info 
                    writerIn.log("AUTOPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase Gold Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
                    
                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        writerIn.log("AUTPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + "does not have enough money to purchase tickets for Event " + eventDataIn.get(userEventIDChoiceIn));
                        return;
                    }
                }
                else{
                    writerIn.log("AUTPURCHASING: There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
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
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn, writerIn);

                    //Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    //Displays user account info 
                    writerIn.log("AUTOPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase Silver Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
                    
                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        writerIn.log("AUTPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + "does not have enough money to purchase tickets for Event " + eventDataIn.get(userEventIDChoiceIn));
                        return;
                    }
                }
                else{
                    writerIn.log("There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
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
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn, writerIn);


                    //Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    //Displays user account info 
                    writerIn.log("AUTOPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase Bronze Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
                    
                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        writerIn.log("AUTPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + "does not have enough money to purchase tickets for Event " + eventDataIn.get(userEventIDChoiceIn));
                        return;
                    }
                }
                else{
                    writerIn.log("AUTOPURCHASING: There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
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
                    totalPrice = salesTaxPrint(thisSalesTax, totalPrice, customerDataIn, userFirstNameIn, userLastNameIn, eventDataIn, userEventIDChoiceIn, writerIn);


                    //Create Ticket object using constructor
                    int userConfirmationNumber = (int) (Math.random() * 1000000);
                    Ticket customerTicket = new Ticket(userNumOfTickets, totalPrice, userConfirmationNumber, ticketTypeIn);

                    //Displays user account info 
                    writerIn.log("User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " dollars available to purchase General Admission Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
                    
                    if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() >= customerTicket.getTotalPrice()){
                        processPurchase(eventDataIn, customerDataIn, customerTicket, userEventIDChoiceIn, userFirstNameIn, userLastNameIn, writerIn, ticketTypeIn, userNumOfTickets);
                    }
                    else if(customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() < customerTicket.getTotalPrice()){
                        writerIn.log("AUTPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + "does not have enough money to purchase tickets for Event " + eventDataIn.get(userEventIDChoiceIn));
                        return;
                    }
                }
                else{
                    writerIn.log("AUTOPURCHASING: There are not enough " + ticketTypeIn + "  tickets left to purchase " + userNumOfTickets + " tickets");
                }
            }
        } catch (Exception e) {
            System.out.println("");
            //System.out.print(e);
        }
    }


    //Method to Process Transactions

    public static void processPurchase(HashMap<Integer, Event> eventDataIn, HashMap<String, Customer> customerDataIn, Ticket customerTicketIn, int userEventIDChoiceIn, String userFirstNameIn, String userLastNameIn, Logger writerIn, String ticketTypeIn, int userNumOfTicketsIn){

        //Processess purchase and prints out ticket information including num of tickets, confirmation number, and total price for the transaction
        
        eventDataIn.get(userEventIDChoiceIn).ticketAPObject(customerTicketIn, eventDataIn.get(userEventIDChoiceIn), customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()));
        customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).setCustomerTicket(customerTicketIn);
        writerIn.log("AUTOPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + " has successfully purchased " + ticketTypeIn + " Tickets for '" + eventDataIn.get(userEventIDChoiceIn).getEventName() + "' with ID " + userEventIDChoiceIn);
        writerIn.log("AUTOPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + " has $" + customerDataIn.get(userFirstNameIn.toLowerCase()+userLastNameIn.toLowerCase()).getMoneyAvailable() + " available after completing transaction");

        //Update Customer info
        customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).updateConcertsPurchased();
        customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).updateTicketsPurchased(userNumOfTicketsIn);
        customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).updateTransactionCount();

        //Updating percentage of available tickets/seats

        eventDataIn.get(userEventIDChoiceIn).updatePctSeatsRemaining(eventDataIn.get(userEventIDChoiceIn), ticketTypeIn, userNumOfTicketsIn);
        
    }

     //Method that rounds the total price to the nearest decimal 
     public static double roundToDec(double totalPriceIn){
        BigDecimal instance = new BigDecimal(Double.toString(totalPriceIn));
        instance = instance.setScale(2,RoundingMode.HALF_UP);

        return instance.doubleValue();
    }


    //Method that calculates the sales tax and applies discount 
    public static double salesTaxPrint(double thisSalesTaxIn, double totalPrice, HashMap<String, Customer> customerDataIn, String userFirstNameIn, String userLastNameIn, HashMap<Integer, Event> eventDataIn, int userEventIDChoiceIn, Logger writerIn){
        double totalPriceFinal = totalPrice + thisSalesTaxIn;
        double discount = (totalPrice * 0.1);
        
        try {
            //User is a ticket miner member so we apply discount
            if(customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).isTicketMinerMember()){
                writerIn.log("AUTOPURCHASING: User " + userFirstNameIn + " " + userLastNameIn + "is a ticketminer member, they receive 10% off their order");

                writerIn.log("AUTOPURCHASING: Total Price (Before Sales Tax): $" + roundToDec(totalPrice));
                writerIn.log("AUTOPURCHASING: This Sales Tax: $" + thisSalesTaxIn);
                totalPriceFinal = roundToDec(totalPrice + thisSalesTaxIn);
                writerIn.log("AUTOPURCHASING: Total Price Including Salex Tax: $" + totalPriceFinal);

                //Update customer money saved
                customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).setMoneySaved(customerDataIn.get(userFirstNameIn.toLowerCase() + userLastNameIn.toLowerCase()).getMoneySaved() + discount);


                //Update event discount given
                eventDataIn.get(userEventIDChoiceIn).setAmountDiscounted(eventDataIn.get(userEventIDChoiceIn).getAmountDiscounted() + discount);
            }   

            //User is not a ticket miner member
            else{
                writerIn.log("AUTOPURCHASING: Total Price (Before Sales Tax): $" + roundToDec(totalPrice));
                writerIn.log("AUTOPURCHASING: This Sales Tax: $" + thisSalesTaxIn);
                totalPriceFinal = roundToDec(totalPrice + thisSalesTaxIn);
                writerIn.log("AUTOPURCHASING: Total Price Including Salex Tax: $" + totalPriceFinal);
            }
        } catch (Exception e) {
            System.out.println("AUTOPURCHASING: There was a problem calculating the price!");
        }
        return totalPriceFinal;
    }


    
    
}
