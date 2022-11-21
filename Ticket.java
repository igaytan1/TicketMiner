//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - Ticket Class

public class Ticket {
    //Attributes
    private int numOfTickets;
    private double totalPrice;
    private int confirmationNumber;
    private String ticketType;

    //Constructors
    public Ticket(){

    }

    public Ticket(int numOfTicketsIn, double totalPriceIn, int confirmationNumberIn, String ticketTypeIn) {
        this.numOfTickets = numOfTicketsIn;
        this.totalPrice = totalPriceIn;
        this.confirmationNumber = confirmationNumberIn;
        this.ticketType = ticketTypeIn;
    }


    //Getters and Setters
    
    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(int confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }


}
