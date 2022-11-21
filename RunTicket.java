//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - RunTicket (Main)



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class RunTicket {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int menuOption = 0;
        ReadFile customerFile = new ReadFile("CustomerListRandomPA4.csv", "./CustomerListRandomPA4.csv");
        ReadFile eventFile = new ReadFile("EventListPA4-A.csv", "./EventListPA4-A.csv");
        HashMap <String, Customer> customerData = customerFile.loadCustomerList();
        HashMap <Integer, Event> eventData = eventFile.loadEventList();
        ReadFile customerAPFile = new ReadFile("CustomerListRandomPA4.csv", "./CustomerListRandomPA4.csv");
        boolean invalidInput = true;
        boolean individualCustomer = true;
        boolean systemAdministrator = false;
        String userNameInput = " ";
        String userPasswordInput = " ";
        String userFirstName = " ";
        String userLastName = " ";
        String userEventIDChoiceString = " ";
        boolean validUserNamePass = false;
        boolean transactionNotDone = true;
        String adminChoice = " "; 
        Logger textLog = Logger.getInstance();
    


        try{
            
            //Print TicketMiner Welcome
            
            System.out.println("");
            System.out.println("----------------------------------");
            System.out.println("            TicketMiner           ");
            System.out.println("----------------------------------");
            System.out.println("");

            while(invalidInput){
                System.out.println("Are you an individual customer? Please enter [yes/no]");
                System.out.print("> ");
                String userResponse = in.next();

                if(!userResponse.equalsIgnoreCase("yes") && !userResponse.equalsIgnoreCase("no")){
                    System.out.println(" ");
                    System.out.println("Invalid input please try again!");
                    System.out.println(" ");
                }
                else if(userResponse.equalsIgnoreCase("no")){
                    individualCustomer = false;
                    invalidInput = false;
                }
                else{
                    invalidInput = false;
                }
            }
            invalidInput = true;
            System.out.println(" ");
            
            if(!individualCustomer){
                while(invalidInput){
                    System.out.println("Are you a system administrator? Please enter [yes/no]");
                    System.out.print("> ");
                    String userResponse = in.next();
                    
                    if(!userResponse.equalsIgnoreCase("yes") && !userResponse.equalsIgnoreCase("no")){
                        System.out.println(" ");
                        System.out.println("Invalid input please try again!");
                        System.out.println(" ");
                    }
                    else if(userResponse.equalsIgnoreCase("no")){
                        System.out.println("Goodbye! :-)");
                        return;
                    }
                    else{
                        systemAdministrator = true;
                        invalidInput = false;
                    }
                 
                }
            }

            invalidInput = true;

            //Menu option for system admin

            while(systemAdministrator && invalidInput){
                System.out.println("");
                System.out.println("--------------------------------");
                System.out.println("Choose an action");
                System.out.println("--------------------------------");
                System.out.println("A. Inquire event by ID");
                System.out.println("B. Inquire event by name");
                System.out.println("C. Create new event");
                System.out.println("D. Simulate Autopurchasing");
                System.out.println("E. Write Electronic Ticket Summary for a Specific Customer");
                System.out.println("F. Exit");
                System.out.print("> ");
                adminChoice = in.next();
                in.nextLine();
                if(adminChoice.equalsIgnoreCase("A")){
                    inquireEventByID(eventData);
                    displayEventList(eventData);
                }
                else if(adminChoice.equalsIgnoreCase("B")){
                    inquireEventByName(eventData);
                    displayEventList(eventData);
                }
                else if(adminChoice.equalsIgnoreCase("C")){
                    createEvent(eventData);
                }
                else if(adminChoice.equalsIgnoreCase("D")){
                    autoPurchase(in, customerData, eventData, transactionNotDone, textLog);
                    
                }
                else if(adminChoice.equalsIgnoreCase("E")){
                    electronicTicketSummaryPrompt(in, customerData);




                }
                else if(adminChoice.equalsIgnoreCase("F")){
                    writeNewEventFile(eventData);
                    writeNewCustomerFile(customerData);
                    System.out.println("Goodbye! :-)");
                    return;
                }
                else{
                    System.out.println("");
                    System.out.println("Invalid input please try again");
                    System.out.println("");
                }

            }
            invalidInput = true;
      

            //Loops until the user enters an existing customer's information that has the username and password matching 
            //the first and last name's on the csv file
            //if user is system administrator, then do not log in


            while(invalidInput && individualCustomer){
                System.out.println("");
                System.out.println("Please enter your first and last name");
                System.out.println("-------------------------------------");
                System.out.print("First Name > ");
                userFirstName = in.next();
                in.nextLine();
                System.out.println("");
                System.out.print("Last Name > ");
                userLastName = in.nextLine();
   
                System.out.println("");
                System.out.println("-----------------------------");
                System.out.println("            Login            ");
                System.out.println("-----------------------------");
                System.out.println("Username: ");
                System.out.print("> ");
                userNameInput = in.next();
                in.nextLine();
                System.out.println(" ");
                System.out.println("Password: ");
                System.out.print("> ");
                userPasswordInput = in.next();
                
                //Traverses through customer HashMap to check whether the inputs match the keys/value info in the csv file
                for(Map.Entry<String, Customer> mapElement: customerData.entrySet()){
                    String key = mapElement.getKey();
                    Customer value = mapElement.getValue();
                    if(userNameInput.equals(value.getUsername()) && userPasswordInput.equals(value.getPassword())){
                        validUserNamePass = true;
                        System.out.println("In here: " + validUserNamePass);
                        if(userFirstName.equalsIgnoreCase(value.getCustomerFirstName()) && userLastName.equalsIgnoreCase(value.getCustomerLastName())){
                            invalidInput = false;
                        }else{
                            break;
                        }
                    }
                }

                //Handles exception of invalid login by displaying message to user
                if(invalidInput == true){
                    System.out.println(" ");
                    System.out.println("Invalid Login, please try again! (Make sure first and last name match username and password on file)");
                }
            }


            //Welcomes the customer user

            if(individualCustomer){
                System.out.println("");
                System.out.println("Welcome " + userFirstName + " " + userLastName + "!");
                customerData.get(userFirstName.toLowerCase()+userLastName.toLowerCase()).setTicketsPurchased(0);
                customerData.get(userFirstName.toLowerCase()+userLastName.toLowerCase()).setTransactionCount(0);
                System.out.println(" ");  
            }

            //Displays menu until the user decides to exit the program
            while(menuOption != 2 && individualCustomer){
                displayEventList(eventData);

                //Menu
                menuOption = displayEventMenu(in);
            

                //If user enters menu option 1, the program prompts the user to specify an event to dsiplay ticket info for
                if(menuOption == 1){

                    //
                    //Displays eticket price information
                    UserInterface.chooseEvent(customerData, eventData, userFirstName, userLastName,
                            userEventIDChoiceString, transactionNotDone, textLog);
                }
                else if(menuOption == 2){
                    //Creating updated event file
                    writeNewEventFile(eventData);

                    //Creating updated customer file
                    writeNewCustomerFile(customerData);
                    textLog.writeToFile();

                    //Creating Electronic Ticket Summary for myself (Isabella Gaytan), mickey mouse, and evil queen
                    writeNewElectronicTicketSummary(customerData, "isabellagaytan");
                    writeNewElectronicTicketSummary(customerData, "mickeymouse");
                    writeNewElectronicTicketSummary(customerData, "evilqueen");

                    System.out.println("Thank you for using TicketMiner! Come back soon :-)");
                    return;
                }else{
                    System.out.println(" ");
                    System.out.println("Please enter a valid menu option");
                    System.out.println(" ");
                }
            }
        }catch(Exception e){
            System.out.println(e);
            System.out.println("An error occurred while reading the file");
        }
    }

    private static void electronicTicketSummaryPrompt(Scanner in, HashMap<String, Customer> customerData) throws FileNotFoundException {
        String adminCustomerChoice = "";


        // Prompt admin on which customer they want an electronic summary of by displaying list of customers
        System.out.println(" ");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("      Below is the list of exisiting customers to choose from:          ");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println(" ");


        for(Map.Entry<String, Customer> mapElement: customerData.entrySet()){
            String key = mapElement.getKey();
            Customer customer = mapElement.getValue();
            System.out.println("Customer Name: " + customer.getCustomerFirstName() + " " + customer.getCustomerLastName());
        }
        try {
            System.out.println("");
            System.out.println("Enter the first and last name of the customer for which you want an Electronic Ticket Summary");
            System.out.print("> ");
            adminCustomerChoice = in.nextLine();
            adminCustomerChoice = adminCustomerChoice.replaceAll(" ", "");
            adminCustomerChoice = adminCustomerChoice.toLowerCase();

            // Check if the admin customer choice exists
            if(customerData.containsKey(adminCustomerChoice)){
                writeNewElectronicTicketSummary(customerData, adminCustomerChoice);
                System.out.println(" ");
                System.out.println("Electronic Ticket Summary for " + "'" + adminCustomerChoice + "'" + " successfully created!");
            }
            else{
                System.out.println("");
                System.out.println("The customer you entered does not exist! Press any key to try again");
                System.out.print("> ");
                in.nextLine();
                electronicTicketSummaryPrompt(in, customerData);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("");
            System.out.println("Wrong Input! Press any key to try again");
            System.out.print("> ");
            in.nextLine();
            electronicTicketSummaryPrompt(in, customerData);
        }

    }

    // Writes electronic ticket summary text file for specific customer
    private static void writeNewElectronicTicketSummary(HashMap<String,Customer> customerDataIn, String customerIn) throws FileNotFoundException {
        File customerETicketSummary = new File("ElectronicTicketSummaryFor" + customerIn + ".csv");
        PrintWriter out = new PrintWriter(customerETicketSummary);

        // Traverses through customer list of tickets purchased and displays info for each 
        for(Map.Entry<Ticket,Event> mapElement: customerDataIn.get(customerIn).getListOfEventsPurchased().entrySet()){
            Ticket ticket = mapElement.getKey();
            Event customerEvent = mapElement.getValue();

            out.printf("Event Type: " + customerEvent.getEventType() + "%n");
            out.printf("Event Name: " + customerEvent.getEventName() + "%n");
            out.printf("Event Date: " + customerEvent.getEventDate() + "%n");
            out.printf("Ticket Type: " + ticket.getTicketType() + "%n");
            out.printf("Number of tickets: " + ticket.getNumOfTickets() + "%n");
            out.printf("Total Price: " + ticket.getTotalPrice() + "%n");
            out.printf("Confirmation Number: " + ticket.getConfirmationNumber()+ "%n");
            out.printf("%n");
        }

        out.close();
    }

    // Completes autopurchasing functionality
    private static void autoPurchase(Scanner in, HashMap<String, Customer> customerData, HashMap<Integer, Event> eventData,
        boolean transactionNotDone, Logger textLog) {

        try {
            System.out.println("");
            System.out.println("Choose from below: ");
            System.out.println("1. AutoPurchase10k");
            System.out.println("2. AutoPurchase100k");
            System.out.println("3. AutoPurchase400k");
            System.out.print("> ");
            String adminAPChoice = in.nextLine();

            // Read autopurchasing file and begin transactions
            if(adminAPChoice.equals("1")){
                ReadFile.loadCustomerAPList(customerData, eventData, "AutoPurchase10k.csv", transactionNotDone, textLog);
            }
            else if(adminAPChoice.equals("2")){
                ReadFile.loadCustomerAPList(customerData, eventData, "AutoPurchase100k.csv", transactionNotDone, textLog);
            }
            else if(adminAPChoice.equals(("3"))){
                ReadFile.loadCustomerAPList(customerData, eventData, "AutoPurchase400k.csv", transactionNotDone, textLog);
            }
            else{
                System.out.println("Invalid Input! Press any key to try again");
                System.out.print("> ");
                in.nextLine();
                autoPurchase(in, customerData, eventData, transactionNotDone, textLog);
            }
        } catch (Exception e) {
            System.out.println("Invalid Input! Press any key to try again");
            System.out.print("> ");
            in.nextLine();
            autoPurchase(in, customerData, eventData, transactionNotDone, textLog);
        }    

    }

    private static void displayEventList(HashMap<Integer, Event> eventData) {

        // Prints summary of all events and their information
        System.out.println("");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("The Events available are the following: ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        
        // Traverses through event hashmap to display event information to user
        for(Map.Entry<Integer, Event> mapElement: eventData.entrySet()){
            int key = mapElement.getKey();
            Event value = mapElement.getValue();
            System.out.println("Event ID: " + key + "       | "  + "      Name:       " + value.getEventName() + "                | " + "    Date: " 
            + value.getEventDate() + "      | " + "   Time: " + value.getEventTime() + "    | " + "Fireworks Inlcuded: " + value.getVenueType().getFireWorks() + "Total Event Cost: ");
        }
    }


    // Displays event list for admin user which included the event cost 
    private static void displayEventListAdmin(HashMap<Integer, Event> eventData) {
         
        //Prints summary of all events and their information
        System.out.println("");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("                       The Events available are the following:                          ");
        System.out.println("----------------------------------------------------------------------------------------");
        
        //Traverses through event hashmap to display event information to user
        for(Map.Entry<Integer, Event> mapElement: eventData.entrySet()){
            int key = mapElement.getKey();
            Event value = mapElement.getValue();
            System.out.println("Event ID: " + key + "       | "  + "      Name:       " + value.getEventName() + "                | " + "    Date: " 
            + value.getEventDate() + "      | " + "   Time: " + value.getEventTime() + "    | " + "Fireworks Inlcuded: " + value.getVenueType().getFireWorks() + "Total Event Cost: $" + (value.getVenueType().getFireworksCost() + value.getVenueType().getVenueCost()));
        }
    }


    //Method to check that displays menu for event list
    public static int displayEventMenu(Scanner in) {
        int menuOption = 0;
        String menuOptionString;
        System.out.println(" ");
        System.out.println("Choose one of the options below: ");
        System.out.println("[1] View Prices & Purchase Ticket");
        System.out.println("[2] Exit");
        System.out.print("> ");
        menuOptionString = in.next();

        try {
            menuOption = Integer.parseInt(menuOptionString);
        } catch (Exception e) {
            System.out.println(" ");
            System.out.println("Invalid input! Please enter an integer. Press any key to try again");
            System.out.print("> ");
            in.next();
            displayEventMenu(in);
        }
        return menuOption;
    }


    //Method to inquire event by ID

    public static void inquireEventByID(HashMap<Integer, Event> eventDataIn){
        Scanner in = new Scanner(System.in);
        System.out.println("");
        System.out.println("What is the ID of the event?");
        System.out.print("> ");
        try {
            int adminEventChoice = Integer.parseInt(in.next());
            System.out.println("");
            System.out.println("-----------------------------------");
            if(eventDataIn.containsKey(adminEventChoice) && !eventDataIn.get(adminEventChoice).getListOfTickets().isEmpty()){
                System.out.println("Event ID: " + adminEventChoice);
                System.out.println("Event Name: " + eventDataIn.get(adminEventChoice).getEventName());
                System.out.println("Event Date: " + eventDataIn.get(adminEventChoice).getEventDate());
                System.out.println("Event Time: " + eventDataIn.get(adminEventChoice).getEventTime());
                System.out.println("Event Type: " + eventDataIn.get(adminEventChoice).getEventType());
                System.out.println("Event Capcity: " + eventDataIn.get(adminEventChoice).getVenueType().getVenueCapacity());
                System.out.println("Total Seats Sold: ");
                System.out.println("Total VIP Seats Sold: " + eventDataIn.get(adminEventChoice).getNumberOfTicketsSold(eventDataIn.get(adminEventChoice), "VIP"));
                System.out.println("Total Gold Seats Sold: " + eventDataIn.get(adminEventChoice).getNumberOfTicketsSold(eventDataIn.get(adminEventChoice),"Gold"));
                System.out.println("Total Silver Seats Sold: " + eventDataIn.get(adminEventChoice).getNumberOfTicketsSold(eventDataIn.get(adminEventChoice),"Silver"));
                System.out.println("Total Bronze Seats Sold: " + eventDataIn.get(adminEventChoice).getNumberOfTicketsSold(eventDataIn.get(adminEventChoice),"Bronze"));
                System.out.println("Total General Admission Seats Sold: " + eventDataIn.get(adminEventChoice).getNumberOfTicketsSold(eventDataIn.get(adminEventChoice),"GA"));
                System.out.println("Total revenue for VIP tickets: " + eventDataIn.get(adminEventChoice).getTotalRevenueFor("VIP"));
                System.out.println("Total revenue for Gold tickets: " + eventDataIn.get(adminEventChoice).getTotalRevenueFor("Gold"));
                System.out.println("Total revenue for Silver tickets: " + eventDataIn.get(adminEventChoice).getTotalRevenueFor("Silver"));
                System.out.println("Total revenue for Bronze tickets: " + eventDataIn.get(adminEventChoice).getTotalRevenueFor("Bronze"));
                System.out.println("Total revenue for General Admission tickets: " + eventDataIn.get(adminEventChoice).getTotalRevenueFor("GA"));
                System.out.println("Total revenue for all tickets: " + eventDataIn.get(adminEventChoice).getTotalRevenue());
                System.out.println("Expected profit (Sell Out): " + eventDataIn.get(adminEventChoice).expectedProfit());
                System.out.println("Actual profit: " + (eventDataIn.get(adminEventChoice).getTotalRevenue() - (eventDataIn.get(adminEventChoice).getVenueType().getVenueCost() + eventDataIn.get(adminEventChoice).getVenueType().getFireworksCost())));
            }
            else if(eventDataIn.containsKey(adminEventChoice) && eventDataIn.get(adminEventChoice).getListOfTickets().isEmpty()){
                System.out.println("Event ID: " + adminEventChoice);
                System.out.println("Event Name: " + eventDataIn.get(adminEventChoice).getEventName());
                System.out.println("Event Date: " + eventDataIn.get(adminEventChoice).getEventDate());
                System.out.println("Event Time: " + eventDataIn.get(adminEventChoice).getEventTime());
                System.out.println("Event Type: " + eventDataIn.get(adminEventChoice).getEventType());
                System.out.println("Event Capacity: " + eventDataIn.get(adminEventChoice).getVenueType().getVenueCapacity());
                System.out.println("Total Seats Sold: 0");
                System.out.println("Total VIP Seats Sold: 0");
                System.out.println("Total Gold Seats Sold: 0");
                System.out.println("Total Silver Seats Sold: 0");
                System.out.println("Total Bronze Seats Sold: 0");
                System.out.println("Total General Admission Seats Sold: 0");
                System.out.println("Total revenue for VIP tickets: 0");
                System.out.println("Total revenue for Gold tickets: 0");
                System.out.println("Total revenue for Silver tickets: 0");
                System.out.println("Total revenue for Bronze tickets: 0");
                System.out.println("Total revenue for General Admission tickets: 0");
                System.out.println("Total revenue for all tickets: 0");
                System.out.println("Expected profit (Sell Out): $" + eventDataIn.get(adminEventChoice).expectedProfit());
                System.out.println("Actual profit: -" + (eventDataIn.get(adminEventChoice).getVenueType().getVenueCost() + eventDataIn.get(adminEventChoice).getVenueType().getFireworksCost()));                    
            }
        } catch (Exception e) {

            System.out.println(" ");
            System.out.println("Invalid Input, press any key to try again");
            System.out.println("> ");
            in.next();
            inquireEventByID(eventDataIn);
        }
    }



    //Method that allows admin to inquire event by name

    public static void inquireEventByName(HashMap<Integer, Event> eventDataIn){
        System.out.println("");
        System.out.println("What is the name of the event?");
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String adminChoice = in.nextLine();
        try {
            for(Map.Entry<Integer,Event> mapElement: eventDataIn.entrySet()){
                Integer key = mapElement.getKey();
                Event event = mapElement.getValue();
                String eventName = event.getEventName();
                if(eventName.equalsIgnoreCase(adminChoice) && !event.getListOfTickets().isEmpty()){
                    System.out.println("Event ID: " + key);
                    System.out.println("Event Name: " + event.getEventName());
                    System.out.println("Event Date: " + event.getEventDate());
                    System.out.println("Event Time: " + event.getEventTime());
                    System.out.println("Event Type: " + event.getEventType());
                    System.out.println("Event Capcity: " + event.getVenueType().getVenueCapacity());
                    System.out.println("Total Seats Sold: ");
                    System.out.println("Total VIP Seats Sold: " + event.getNumberOfTicketsSold(event, "VIP"));
                    System.out.println("Total Gold Seats Sold: " + event.getNumberOfTicketsSold(event, "Gold"));
                    System.out.println("Total Silver Seats Sold: " + event.getNumberOfTicketsSold(event, "Silver"));
                    System.out.println("Total Bronze Seats Sold: " + event.getNumberOfTicketsSold(event, "Bronze"));
                    System.out.println("Total General Admission Seats Sold: " + event.getNumberOfTicketsSold(event, "GA"));
                    System.out.println("Total revenue for VIP tickets: " + event.getTotalRevenueFor("VIP"));
                    System.out.println("Total revenue for Gold tickets: " + event.getTotalRevenueFor("Gold"));
                    System.out.println("Total revenue for Silver tickets: " + event.getTotalRevenueFor("Silver"));
                    System.out.println("Total revenue for Bronze tickets: " + event.getTotalRevenueFor("Bronze"));
                    System.out.println("Total revenue for General Admission tickets: " + event.getTotalRevenueFor("GA"));
                    System.out.println("Total revenue for all tickets: " + event.getTotalRevenue());
                    System.out.println("Expected profit (Sell Out): " + (event.expectedProfit() - (event.getVenueType().getVenueCost() + event.getVenueType().getFireworksCost())));
                    System.out.println("Actual profit: " + (event.getTotalRevenue() - (event.getVenueType().getVenueCost() + event.getVenueType().getFireworksCost())));
                }
                else if(eventName.equalsIgnoreCase(adminChoice) && event.getListOfTickets().isEmpty()){
                    System.out.println("Event ID: " + key);
                    System.out.println("Event Name: " + event.getEventName());
                    System.out.println("Event Date: " + event.getEventDate());
                    System.out.println("Event Time: " + event.getEventTime());
                    System.out.println("Event Type: " + event.getEventType());
                    System.out.println("Event Capacity: " + event.getVenueType().getVenueCapacity());
                    System.out.println("Total Seats Sold: 0");
                    System.out.println("Total VIP Seats Sold: 0");
                    System.out.println("Total Gold Seats Sold: 0");
                    System.out.println("Total Silver Seats Sold: 0");
                    System.out.println("Total Bronze Seats Sold: 0");
                    System.out.println("Total General Admission Seats Sold: 0");
                    System.out.println("Total revenue for VIP tickets: 0");
                    System.out.println("Total revenue for Gold tickets: 0");
                    System.out.println("Total revenue for Silver tickets: 0");
                    System.out.println("Total revenue for Bronze tickets: 0");
                    System.out.println("Total revenue for General Admission tickets: 0");
                    System.out.println("Total revenue for all tickets: 0");
                    System.out.println("Expected profit (Sell Out): " + event.expectedProfit());
                    System.out.println("Actual profit: -" + (event.getVenueType().getVenueCost() + event.getVenueType().getFireworksCost()));
                }
            }
        } catch (Exception e) {
            System.out.println("The event name you entered does not exist!");
            System.out.println("Press any key to try again");
            System.out.print("> ");
            in.next();
            inquireEventByName(eventDataIn);
        }
    }



    //Method that writes updated event file with new columns included

    public static void writeNewEventFile(HashMap<Integer,Event> eventDataIn) throws FileNotFoundException{
        File eventCsvFile = new File("EventListUpdated.csv");
        PrintWriter out = new PrintWriter(eventCsvFile);

        out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %n", "Event ID", "Event Type", "Name", "Date", "Time", "VIP Price",
        "Gold Price", "Silver Price", "Bronze Price", "General Admission Price", "Venue Name", "Pct Seats Unavailable", "Venue Type", "Capacity", "Cost", 
        "VIP Pct", "Gold Pct", "Silver Pct", "Bronze Pct", "General Admission Pct", "Reserved Extra Pct", "Fireworks", "Fireworks Cost", "VIP Seats Sold",
        "Gold Seats Sold", "Silver Seats Sold", "Bronze Seats Sold", "General Admission Seats Sold", "Total VIP Revenue", "Total Gold Revenue", "Total Silver Revenue",
        "Total Bronze Revenue", "Total General Admission Revenue");

        for(Map.Entry<Integer,Event> mapElement: eventDataIn.entrySet()){
            Integer key = mapElement.getKey();
            Event event = mapElement.getValue();

            //Converting Ticket Type seats back to percentages
            event.getVenueType().setVipPct(event.getVenueType().seatPercentage(event.getVenueType().getVenueCapacity(), event, "VIP"));
            event.getVenueType().setGoldPct(event.getVenueType().seatPercentage(event.getVenueType().getVenueCapacity(), event, "Gold"));
            event.getVenueType().setSilverPct(event.getVenueType().seatPercentage(event.getVenueType().getVenueCapacity(), event, "Silver"));
            event.getVenueType().setBronzePct(event.getVenueType().seatPercentage(event.getVenueType().getVenueCapacity(), event, "Bronze"));
            event.getVenueType().setGaPct(event.getVenueType().seatPercentage(event.getVenueType().getVenueCapacity(), event, "Ga"));

            //Adding event info and columns
            out.printf("%d, %s, %s, %s, %s, %.2f, %.2f, %.2f, %.2f, %.2f, %s, %.2f, %s, %d, %d, %.2f, %.2f, %.2f, %.2f, %.2f, %.2f, %b, %.2f, %d, %d, %d, %d, %d, %.2f, %.2f, %.2f, %.2f, %.2f, %n", key, event.getEventType(), 
            event.getEventName(), event.getEventDate(), event.getEventTime(), event.getVIPPrice(), event.getGoldPrice(), event.getSilverPrice(), event.getBronzePrice(), event.getGaPrice(),
            event.getVenueType().getVenueName(), event.getVenueType().getPctSeatUnavailable(), event.getVenueType().getVenueName(), event.getVenueType().getVenueCapacity(),
            event.getVenueType().getVenueCost(), event.getVenueType().getVipPct(), event.getVenueType().getGoldPct(), event.getVenueType().getSilverPct(),
            event.getVenueType().getBronzePct(), event.getVenueType().getGaPct(), event.getVenueType().getReservedExtraPct(), event.getVenueType().getFireWorks(), event.getVenueType().getFireworksCost(), event.getNumberOfTicketsSold(event, "VIP"), event.getNumberOfTicketsSold(event, "Gold"),
            event.getNumberOfTicketsSold(event, "Silver"), event.getNumberOfTicketsSold(event, "Bronze"), event.getNumberOfTicketsSold(event, "GA"), event.getTotalRevenueFor("VIP"),
            event.getTotalRevenueFor("Gold"), event.getTotalRevenueFor("Silver"), event.getTotalRevenueFor("Bronze"), event.getTotalRevenueFor("GA"));
           
        }

        out.close();
    }


    //Method that writes updated customer event file with new columns added
    public static void writeNewCustomerFile(HashMap<String, Customer> customerDataIn) throws FileNotFoundException{
        File eventCsvFile = new File("CustomerListUpdated.csv");
        PrintWriter out = new PrintWriter(eventCsvFile);

        out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %n", "ID", "First Name", "Last Name", "Money Available", "Events Purchased", "TicketMiner Membership",
        "Username", "Password", "Tickets Purchased", "Transaction Count" );

        for(Map.Entry<String,Customer> mapElement: customerDataIn.entrySet()){
            String key = mapElement.getKey();
            Customer customer = mapElement.getValue();

            out.printf("%d, %s, %s, %.2f, %d, %b, %s, %s, %s, %s, %n", customer.getCustomerID(), customer.getCustomerFirstName(), customer.getCustomerLastName(), customer.getMoneyAvailable(),
            customer.getConcertsPurchased(), customer.isTicketMinerMember(), customer.getUsername(), customer.getPassword(), customer.getTicketsPurchased(), customer.getTransactionCount());
        }

        out.close();
    }

    // Creates new event under admin's choices 
    public static void createEvent(HashMap<Integer, Event> eventDataIn){
        Scanner in = new Scanner(System.in);
        int largestEventID = 0;
        int newEventID;
        String newEventName = "";
        String newVenueName = "";
        String eventType = "";
        String[] newEventDate;
        String newEventTime = "";
        double generalAdmissionPrice = 0;
        EventFactory event = new EventFactory();

        for(Map.Entry<Integer,Event> mapElement: eventDataIn.entrySet()){
            Integer key = mapElement.getKey();
            Event eventValue = mapElement.getValue();
            if(key > largestEventID){
                largestEventID = key;
            }
        }
        newEventID = largestEventID++;

        try{
            System.out.println("");
            System.out.println("Enter new event name: ");
            System.out.print("> ");
            newEventName = in.nextLine();

            eventType = getEventType();

            System.out.println("");
            System.out.println("Enter new event date (Please enter as MM/DD/YYYY): ");
            System.out.print("> ");
            newEventDate = in.nextLine().split("/");
            Date newDate = new Date(Integer.parseInt(newEventDate[0]), Integer.parseInt(newEventDate[1]), Integer.parseInt(newEventDate[2]));

            System.out.println("");
            System.out.println("Enter new event time (Please enter as XX:XX AM/PM): ");
            System.out.print("> ");
            newEventTime = in.nextLine();

            Venue newVenue = createVenue();

            generalAdmissionPrice = getGeneralAdmission();
            Event newEvent = event.createEvent(newEventName, newDate.getMonth() + "/" + newDate.getDay() + "/" + newDate.getYear(), newEventTime, generalAdmissionPrice*5, generalAdmissionPrice*3, generalAdmissionPrice*2.5, generalAdmissionPrice*1.5, generalAdmissionPrice, newVenue, eventType, 0.0,newEventID);
            eventDataIn.put(newEventID, newEvent);
            
            // Display updated event list to the adin user
            System.out.println("New event Added! Here is the updated Event List:");
            displayEventListAdmin(eventDataIn);


        }catch(Exception e){
            System.out.println("");
            System.out.println("Wrong input! Press any key to try again");
            System.out.print("> ");
            createEvent(eventDataIn);
        }
    }

    // Prompts admin to choose event type to create new event 
    public static String getEventType(){
        Scanner in = new Scanner(System.in);
        String newEventType = "";

        try {
            System.out.println("");
            System.out.println("Choose your event type from the following [1-3]: ");
            System.out.println("1. Sport");
            System.out.println("2. Concert");
            System.out.println("3. Special");
            System.out.print(">");
            newEventType = in.nextLine();

            switch(newEventType){
                case "1":
                newEventType = "Sport";
                break;

                case "2":
                newEventType = "Concert";
                break;

                case "3":
                newEventType = "Special";
                break;

                default:
                System.out.println("Invalid type");
                break;
            }
        } catch (Exception e) {
            System.out.println("Wrong input! Press any key to try again");
            System.out.print("> ");
            in.next();
            getEventType();
        }  
        return newEventType;
    }

    // Prompts admin about venue choice for the new event being created
    // creates venue object 
    public static Venue createVenue(){
        Scanner in = new Scanner(System.in);
        String newVenueName = "";
        VenueFactory venue = new VenueFactory();
        String fireworksString = "";
        boolean fireworks = false;
        int fireworksCost = 0;

        try {
            System.out.println("");
            System.out.println("Choose a Venue from below (a-e): ");
            System.out.println("a. Sun Bowl Stadium");  //Stadium: 58000
            System.out.println("b. Don Haskins Center"); //Arena: 12800
            System.out.println("c. Magoffin Auditorium"); //Auditorium: 1152
            System.out.println("d. San Jacinto Plaza"); //OpenAir: 15000
            System.out.println("e. Centennial Plaza"); //OpenAir: 5000
            System.out.print("> ");
            newVenueName = in.nextLine();

            System.out.println("");
            System.out.println("Would you like to include fireworks in your new event? Enter Yes/No");
            System.out.print("> ");
            fireworksString = in.nextLine();
            
            if(fireworksString.equalsIgnoreCase("Yes")){
                fireworks = true;
            }
            else if(fireworksString.equalsIgnoreCase("No")){
                fireworks = false;
            }
            else{
                System.out.println("Please enter a valid input! Press any key to try again");
                System.out.print(">");
                in.nextLine();
                createVenue();
            }

            if(fireworks){
                System.out.println("");
                System.out.println("Enter how much you plan to spend on fireworks: ");
                System.out.print(">");
                fireworksCost = in.nextInt();
            }

            if(newVenueName.equalsIgnoreCase("a")){
                Venue newVenue = venue.createVenue("Stadium", "Sun Bowl Stadium", 0, 58000, 681500, 5, 10, 15, 20, 45, 5, fireworks, fireworksCost);
                return newVenue;
            }
            else if(newVenueName.equalsIgnoreCase("b")){
                Venue newVenue = venue.createVenue("Arena", "Don Haskins Center", 0, 58000, 681500, 5, 10, 15, 20, 45, 5, fireworks, fireworksCost);
                return newVenue;
            }
            else if(newVenueName.equalsIgnoreCase("c")){
                Venue newVenue = venue.createVenue("Auditorium", "Magoffin Auditorium", 0, 1152, 13536, 5, 10, 15, 20, 45, 5, fireworks, fireworksCost);
                return newVenue;
            }
            else if(newVenueName.equalsIgnoreCase("d")){
                Venue newVenue = venue.createVenue("Open Air", "San Jacinto Plaza", 0, 1500, 176250, 5, 10, 15, 20, 45, 5, fireworks, fireworksCost);
                return newVenue;
            }
            else if(newVenueName.equalsIgnoreCase("e")){
                Venue newVenue = venue.createVenue("Open Air", "Centennial Plaza", 0, 5000, 58750, 5, 10, 15, 20, 45, 5, fireworks, fireworksCost);
                return newVenue;
            }
        } catch (Exception e) {
            System.out.println("Wrong input! Enter any key to try again");
            System.out.print(">");
            in.next();
            createVenue();
        }
        return null; 
    }
    

    // Prompts admin about their choice for the general admission price
    public static double getGeneralAdmission(){
        Scanner in = new Scanner(System.in);
        double generalAdmissionPrice = 0;
        
        try {
            System.out.println("");
            System.out.println("Enter General Admission Price (Max: $1000): ");
            System.out.print("> ");
            generalAdmissionPrice = in.nextDouble();

            if(generalAdmissionPrice > 1000){
                System.out.println("Don't be greedy! Your price must be below $1000. Press any key to try again.");
                System.out.println("> ");
                in.next();
                getGeneralAdmission();
            }
        } catch (Exception e) {
            System.out.println("Wrong Input! Press any key to try again");
            System.out.print("> ");
            in.next();
            getGeneralAdmission();
        }
        return generalAdmissionPrice;
    }



}
