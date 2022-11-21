//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - Event Abstract Class

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public abstract class Event {
    //Attributes
    private Venue venueType;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private double vIPPrice;
    private double goldPrice;
    private double silverPrice;
    private double bronzePrice;
    private double gaPrice;
    private HashMap<String,Ticket> listOfTickets = new HashMap<>();
    private String eventType;
    private double amountDiscounted; 
    private int eventID;

    //Constructor
    public Event(){

    }

    public Event(String eventNameIn, String eventDateIn, String eventTimeIn, double vIPPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double gaPriceIn, Venue venueTypeIn, String eventTypeIn, double amountDiscountedIn, int eventIDIn){
        this.eventName = eventNameIn;
        this.eventDate = eventDateIn;
        this.eventTime = eventTimeIn;
        this.vIPPrice = vIPPriceIn;
        this.goldPrice = goldPriceIn;
        this.silverPrice = silverPriceIn;
        this.bronzePrice = bronzePriceIn;
        this.gaPrice = gaPriceIn;
        this.venueType = venueTypeIn;
        this.eventType = eventTypeIn;
        this.amountDiscounted = amountDiscountedIn;
        this.eventID = eventIDIn;
    }

    public Event(String eventNameIn, String eventDateIn, String eventTimeIn) {
        this.eventName = eventNameIn;
        this.eventDate = eventDateIn;
        this.eventTime = eventTimeIn;
    }

    public Event(double vIPPriceIn, double goldPriceIn, double silverPriceIn, double bronzePriceIn, double gaPriceIn) {
        this.vIPPrice = vIPPriceIn;
        this.goldPrice = goldPriceIn;
        this.silverPrice = silverPriceIn;
        this.bronzePrice = bronzePriceIn;
        this.gaPrice = gaPriceIn;
    }

    //Getters and Setters
    

    public Venue getVenueType() {
        return venueType;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public double getAmountDiscounted() {
        return amountDiscounted;
    }

    public void setAmountDiscounted(double amountDiscounted) {
        this.amountDiscounted = amountDiscounted;
    }

    public void setVenueType(Venue venueType) {
        this.venueType = venueType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public HashMap<String, Ticket> getListOfTickets() {
        return listOfTickets;
    }

    public void setListOfTickets(HashMap<String, Ticket> listOfTickets) {
        this.listOfTickets = listOfTickets;
    }

    
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public double getVIPPrice() {
        return vIPPrice;
    }

    public void setVIPPrice(double vIPPrice) {
        this.vIPPrice = vIPPrice;
    }

    public double getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(double goldPrice) {
        this.goldPrice = goldPrice;
    }

    public double getSilverPrice() {
        return silverPrice;
    }

    public void setSilverPrice(double silverPrice) {
        this.silverPrice = silverPrice;
    }

    public double getBronzePrice() {
        return bronzePrice;
    }

    public void setBronzePrice(double bronzePrice) {
        this.bronzePrice = bronzePrice;
    }

    public double getGaPrice() {
        return gaPrice;
    }

    public void setGaPrice(double gaPrice) {
        this.gaPrice = gaPrice;
    }


    //Creates ticket objects

    public void ticketObject(Ticket customerTicketIn, Event eventInfo, Customer customerIn){
        //
        try {
            customerIn.setMoneyAvailable(customerIn.getMoneyAvailable()-customerTicketIn.getTotalPrice());
            System.out.println("YAY! You are going to '" + eventInfo.getEventName() + "'!!!");
            System.out.println("You now have $" + customerIn.getMoneyAvailable() + " left in your account");
            System.out.println(" ");
            System.out.println("Your ticket(s): ");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| Event: " + eventInfo.getEventName() + "    |  " + "Date: " + eventInfo.getEventDate() + " | " + " Time: " + eventInfo.getEventTime() + "                     |");
            System.out.println("| Number of tickets: " + customerTicketIn.getNumOfTickets() + "       | " + "Transaction Total: $" + customerTicketIn.getTotalPrice() + "                      |");
            System.out.println("| Confirmation Number: " + customerTicketIn.getConfirmationNumber() + "                                                      |");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(" ");
            System.out.println(" ");
            listOfTickets.put(customerTicketIn.getTicketType(), customerTicketIn);
            customerIn.addToListofEventsPurchased(customerTicketIn, eventInfo);
        } catch (Exception e) {
            System.out.println("There was an error creating the ticket object!");
        }
    }

    public void ticketAPObject(Ticket customerTicketIn, Event eventInfo, Customer customerIn){
        //
       
        try {
            customerIn.setMoneyAvailable(customerIn.getMoneyAvailable()-customerTicketIn.getTotalPrice());
            System.out.println("Customer " + customerIn.getCustomerFirstName() + " " + customerIn.getCustomerLastName() + " is going to '" + eventInfo.getEventName() + "'!!!");
            System.out.println("They now have $" + customerIn.getMoneyAvailable() + " left in their account");
            System.out.println(" ");
            System.out.println("Ticket(s): ");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("| Event: " + eventInfo.getEventName() + "                   |  " + "Date: " + eventInfo.getEventDate() + " | " + " Time: " + eventInfo.getEventTime() + "                     |");
            System.out.println("| Number of tickets: " + customerTicketIn.getNumOfTickets() + "       | " + "Transaction Total: $" + customerTicketIn.getTotalPrice() + "                           |");
            System.out.println("| Confirmation Number: " + customerTicketIn.getConfirmationNumber() + "                                                      |");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(" ");
            System.out.println(" ");
            listOfTickets.put(customerTicketIn.getTicketType(), customerTicketIn);
            customerIn.addToListofEventsPurchased(customerTicketIn, eventInfo);
        } catch (Exception e) {
            System.out.println("There was an error creating the ticket object!");
        }

    }

    //Method to update percentage of seats remaining (Integer)

    public void updatePctSeatsRemaining(Event eventInfoIn, String userTicketChoiceIn, int userNumOfTicketsIn){
        //VIP Ticket 
        if(userTicketChoiceIn.equalsIgnoreCase("VIP")){
            eventInfoIn.getVenueType().setVipPct((eventInfoIn.getVenueType().getVipPct()) - userNumOfTicketsIn);
        }
        //Gold Ticket
        else if(userTicketChoiceIn.equalsIgnoreCase("Gold")){
            eventInfoIn.getVenueType().setGoldPct((eventInfoIn.getVenueType().getGoldPct()) - userNumOfTicketsIn);
        }
        //Silver Ticket
        else if(userTicketChoiceIn.equalsIgnoreCase("Silver")){
            eventInfoIn.getVenueType().setSilverPct((eventInfoIn.getVenueType().getSilverPct()) - userNumOfTicketsIn);
        }
        //Bronze Ticket
        else if(userTicketChoiceIn.equalsIgnoreCase("Bronze")){
            eventInfoIn.getVenueType().setBronzePct((eventInfoIn.getVenueType().getBronzePct()) - userNumOfTicketsIn);
        }
        //General Admission Ticket
        else if(userTicketChoiceIn.equalsIgnoreCase("GA")){
            eventInfoIn.getVenueType().setGaPct((eventInfoIn.getVenueType().getGaPct()) - userNumOfTicketsIn);
        }
        eventInfoIn.getVenueType().setPctSeatUnavailable((eventInfoIn.getVenueType().getPctSeatUnavailable()) + (double)(userNumOfTicketsIn*100)/eventInfoIn.getVenueType().getVenueCapacity());
    }

    public int getNumberOfTicketsSold(Event eventInfoIn, String typeOfTicket){
        if(!eventInfoIn.getListOfTickets().containsKey(typeOfTicket)){
            return 0;
        }
        else{
            if(typeOfTicket.equalsIgnoreCase("VIP")){
                return eventInfoIn.getListOfTickets().get("VIP").getNumOfTickets();
            }
            else if(typeOfTicket.equalsIgnoreCase("Gold")){
                return eventInfoIn.getListOfTickets().get("Gold").getNumOfTickets();
            }
            else if(typeOfTicket.equalsIgnoreCase("Silver")){
                return eventInfoIn.getListOfTickets().get("Silver").getNumOfTickets();
            }
            else if(typeOfTicket.equalsIgnoreCase("Bronze")){
                return eventInfoIn.getListOfTickets().get("Bronze").getNumOfTickets();
            }
            else if(typeOfTicket.equalsIgnoreCase("GA")){
                return eventInfoIn.getListOfTickets().get("GA").getNumOfTickets();
            }
            return 0;
        }
    }

    // expected profit for the event
    public double expectedProfit(){

        //VIP sold out profit
        double vipSoldOut = (this.getVIPPrice() * this.venueType.getVipPct());
        //Gold sold out profit
        double goldSoldOut = (this.getGoldPrice()* this.venueType.getGoldPct());
        //Silver sold out profit
        double silverSoldOut = (this.getSilverPrice()* this.venueType.getSilverPct());
        //Bronze sold out profit
        double bronzeSoldOut = (this.getBronzePrice() * this.venueType.getBronzePct());
        //GA sold out profit
        double gaSoldOut = (this.getGaPrice() * this.venueType.getGaPct());

        return vipSoldOut+goldSoldOut+silverSoldOut+bronzeSoldOut+gaSoldOut;
    }

    // Calculates the total revenue for specific ticket 
    public double getTotalRevenueFor(String typeOfTicketIn){
        if(!this.getListOfTickets().containsKey(typeOfTicketIn)){
            return 0;
        }
        else{
            if(typeOfTicketIn.equalsIgnoreCase("VIP")){
                return (this.getListOfTickets().get("VIP").getNumOfTickets() * this.getGoldPrice());
            }
            else if(typeOfTicketIn.equalsIgnoreCase("Gold")){
                return (this.getListOfTickets().get("Gold").getNumOfTickets() * this.getGoldPrice());
            }
            else if(typeOfTicketIn.equalsIgnoreCase("Silver")){
                return (this.getListOfTickets().get("Silver").getNumOfTickets() * this.getSilverPrice());
            }
            else if(typeOfTicketIn.equalsIgnoreCase("Bronze")){
                return (this.getListOfTickets().get("Bronze").getNumOfTickets() * this.getBronzePrice());
            }
            else if(typeOfTicketIn.equalsIgnoreCase("GA")){
                return (this.getListOfTickets().get("GA").getNumOfTickets() * this.getGaPrice());
            }
        }
        return 0;
    }

    // Gets total revenue for the event 
    public double getTotalRevenue(){
        return (this.getVenueType().getVipPct() * this.getVIPPrice()) + (this.getVenueType().getGoldPct() * this.getGoldPrice()) + 
        (this.getVenueType().getSilverPct() * this.getSilverPrice()) + (this.getVenueType().getBronzePct() * this.getBronzePrice())
        + (this.getVenueType().getGaPct() * this.getGaPrice());
    }

}
