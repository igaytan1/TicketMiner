import java.util.HashMap;

//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - Customer Class

public class Customer {
    //Attributes
    private int customerID;
    private String customerFirstName;
    private String customerLastName;
    private double moneyAvailable;
    private int concertsPurchased;
    private boolean ticketMinerMember;
    private String username;
    private String password;
    private Ticket customerTicket;
    private int ticketsPurchased;
    private int transactionCount;
    private double moneySaved;
    private String action;
    private String customerEventID;
    private String customerEventName;
    private String customerTicketQuantity;
    private String customerTicketType;
    private HashMap<Ticket,Event> listOfEventsPurchased = new HashMap<>(); 

    
    //Constructors
    public Customer(){

    }

    public Customer(String customerFirstNameIn, String customerLastNameIn, double moneyAvailableIn,
            int concertsPurchasedIn, boolean ticketMinerMemberIn, String usernameIn, String passwordIn, int customerIDIn, double moneySaved) {
        this.customerFirstName = customerFirstNameIn;
        this.customerLastName = customerLastNameIn;
        this.moneyAvailable = moneyAvailableIn;
        this.concertsPurchased = concertsPurchasedIn;
        this.ticketMinerMember = ticketMinerMemberIn;
        this.username = usernameIn;
        this.password = passwordIn;
        this.customerID = customerIDIn;
        this.moneySaved = 0;
    }

     public Customer(String customerFirstName, String customerLastName, double moneyAvailable, int concertsPurchased,
            boolean ticketMinerMember, String username, String password, Ticket customerTicket, double moneySaved) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.moneyAvailable = moneyAvailable;
        this.concertsPurchased = concertsPurchased;
        this.ticketMinerMember = ticketMinerMember;
        this.username = username;
        this.password = password;
        this.customerTicket = customerTicket;
        this.moneySaved = 0;
    }

    public Customer(String customerFirstNameIn, String customerLastNameIn, String actionIn){
        this.customerFirstName = customerFirstNameIn;
        this.customerLastName = customerLastNameIn;
        this.action = actionIn;
    }

    //Getters and Setters

    

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCustomerEventID() {
        return customerEventID;
    }

    public void setCustomerEventID(String customerEventID) {
        this.customerEventID = customerEventID;
    }

    public String getCustomerEventName() {
        return customerEventName;
    }

    public void setCustomerEventName(String customerEventName) {
        this.customerEventName = customerEventName;
    }

    public String getCustomerTicketQuantity() {
        return customerTicketQuantity;
    }

    public void setCustomerTicketQuantity(String customerTicketQuantity) {
        this.customerTicketQuantity = customerTicketQuantity;
    }

    public String getCustomerTicketType() {
        return customerTicketType;
    }

    public void setCustomerTicketType(String customerTicketType) {
        this.customerTicketType = customerTicketType;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public double getMoneyAvailable() {
        return moneyAvailable;
    }

    public void setMoneyAvailable(double moneyAvailable) {
        this.moneyAvailable = moneyAvailable;
    }

    public int getConcertsPurchased() {
        return concertsPurchased;
    }

    public void setConcertsPurchased(int concertsPurchased) {
        this.concertsPurchased = concertsPurchased;
    }

    public boolean isTicketMinerMember() {
        return ticketMinerMember;
    }

    public void setTicketMinerMember(boolean ticketMinerMember) {
        this.ticketMinerMember = ticketMinerMember;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ticket getCustomerTicket() {
        return customerTicket;
    }

    public void setCustomerTicket(Ticket customerTicket) {
        this.customerTicket = customerTicket;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void updateConcertsPurchased(){
        this.concertsPurchased = concertsPurchased + 1;
    }


    public void updateTicketsPurchased(int userNumOfTickets){
        this.ticketsPurchased = ticketsPurchased + userNumOfTickets;
    }

    public int getTicketsPurchased() {
        return ticketsPurchased;
    }

    public void setTicketsPurchased(int ticketsPurchased) {
        this.ticketsPurchased = ticketsPurchased;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public void updateTransactionCount(){
        this.transactionCount = transactionCount + 1;
    }

    public double getMoneySaved() {
        return moneySaved;
    }

    public void setMoneySaved(double moneySaved) {
        this.moneySaved = moneySaved;
    }

    public HashMap<Ticket, Event> getListOfEventsPurchased() {
        return listOfEventsPurchased;
    }

    public void setListOfEventsPurchased(HashMap<Ticket, Event> listOfEventsPurchased) {
        this.listOfEventsPurchased = listOfEventsPurchased;
    }

    public void addToListofEventsPurchased(Ticket customerTicketIn, Event eventInfo){
        listOfEventsPurchased.put(customerTicketIn, eventInfo);
    }
}
