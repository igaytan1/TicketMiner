//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - ReadFile Class

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
//import java.time.temporal.Temporal;
import java.util.Scanner;

import javax.sql.rowset.spi.TransactionalWriter;

public class ReadFile {
    //Attributes
    private String fileName;
    private String filePath;

    public ReadFile(){

    }

    public ReadFile(String fileNameIn, String filePathIn){
        this.fileName = fileNameIn;
        this.filePath = filePathIn;
    }

    //read the header - temp
    //store the header in a hashmap - dict [type: 3, name: 5]
    //before temp[dict.get(id)], temp[dict.get(name)]
    //store header and assign value to each element 



    //Method that creates HashMap storing data in selected file
    public HashMap<String, Customer> loadCustomerList(){
        try {
            File fileObj = new File(fileName);
            Scanner scnr = new Scanner(fileObj);
            HashMap <String, Customer> dataList = new HashMap<>();
            
            String[] tempHeader = scnr.nextLine().split(",");
            HashMap <String, Integer> headerList = new HashMap<String,Integer>();
            

            for(int i = 0; i < tempHeader.length; i++){
                headerList.put(tempHeader[i], i);
            }


            //creates hashmap of customer list 
            while(scnr.hasNextLine()){
                String [] tempValues = scnr.nextLine().split(",");
                Customer customer = new Customer(tempValues[headerList.get("First Name")], tempValues[headerList.get("Last Name")], Double.parseDouble(tempValues[headerList.get("Money Available")]), Integer.parseInt(tempValues[headerList.get("Concerts Purchased")]), Boolean.parseBoolean(tempValues[headerList.get("TicketMiner Membership")]), tempValues[headerList.get("Username")], tempValues[headerList.get("Password")], Integer.parseInt(tempValues[headerList.get("ID")]), 0);
                dataList.put(tempValues[headerList.get("First Name")].toLowerCase() + tempValues[headerList.get("Last Name")].toLowerCase(), customer);
            }
            return dataList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    //Method that creates HashMap storing data in selected file
    public HashMap<Integer, Event> loadEventList(){
        try {
            File fileObj = new File(fileName);
            Scanner scnr = new Scanner(fileObj);
            HashMap <Integer, Event> dataList = new HashMap<>();
            VenueFactory venue = new VenueFactory();
            EventFactory event = new EventFactory();

            String[] tempHeader = scnr.nextLine().split(",");
            HashMap <String, Integer> headerList = new HashMap<String,Integer>();
            

            for(int i = 0; i < tempHeader.length; i++){
                headerList.put(tempHeader[i], i);  
            }

            while(scnr.hasNextLine()){
                String[] tempValues = scnr.nextLine().split(",");

                
                if(tempValues.length == 23){
                    if((tempValues[headerList.get("Fireworks Planned")] == "") || (tempValues[headerList.get("Fireworks Planned")] == null)){
                        tempValues[headerList.get("Fireworks Planned")] = "No";
                    }
                    if((tempValues[headerList.get("Fireworks Cost")] == "") || (tempValues[headerList.get("Fireworks Cost")] == null)){
                        tempValues[headerList.get("Fireworks Cost")] = "0";
                    }


                    Venue newVenue = venue.createVenue(tempValues[headerList.get("Venue Type")], tempValues[headerList.get("Venue Name")], Double.parseDouble(tempValues[headerList.get("Seats Unavailable Pct")]), Integer.parseInt(tempValues[headerList.get("Capacity")]), Integer.parseInt(tempValues[headerList.get("Cost")]), ((Double.parseDouble(tempValues[headerList.get("VIP Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))),  ((Double.parseDouble(tempValues[headerList.get("Gold Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), 
                    ((Double.parseDouble(tempValues[headerList.get("Silver Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), ((Double.parseDouble(tempValues[headerList.get("Bronze Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), ((Double.parseDouble(tempValues[headerList.get("General Admission Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), ((Double.parseDouble(tempValues[headerList.get("Reserved Extra Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))),
                    Boolean.parseBoolean(tempValues[headerList.get("Fireworks Planned")]), Double.parseDouble(tempValues[headerList.get("Fireworks Cost")]));
                    
                    
                    
                    Event newEvent = event.createEvent(tempValues[headerList.get("Name")], tempValues[headerList.get("Date")], tempValues[headerList.get("Time")], Double.parseDouble(tempValues[headerList.get("VIP Price")]), Double.parseDouble(tempValues[headerList.get("Gold Price")]), Double.parseDouble(tempValues[headerList.get("Silver Price")]), Double.parseDouble(tempValues[headerList.get("Bronze Price")]), Double.parseDouble(tempValues[headerList.get("General Admission Price")]), newVenue, tempValues[headerList.get("Event Type")], 0.0, Integer.parseInt(tempValues[headerList.get("Event ID")]));
                    dataList.put(Integer.parseInt(tempValues[headerList.get("Event ID")]), newEvent);
                }
                else if (tempValues.length == 22){

                    Venue newVenue = venue.createVenue(tempValues[headerList.get("Venue Type")], tempValues[headerList.get("Venue Name")], Double.parseDouble(tempValues[headerList.get("Seats Unavailable Pct")]), Integer.parseInt(tempValues[headerList.get("Capacity")]), Integer.parseInt(tempValues[headerList.get("Cost")]), ((Double.parseDouble(tempValues[headerList.get("VIP Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))),  ((Double.parseDouble(tempValues[headerList.get("Gold Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), 
                    ((Double.parseDouble(tempValues[headerList.get("Silver Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), ((Double.parseDouble(tempValues[headerList.get("Bronze Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), ((Double.parseDouble(tempValues[headerList.get("General Admission Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), ((Double.parseDouble(tempValues[headerList.get("Reserved Extra Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))),
                    Boolean.parseBoolean(tempValues[headerList.get("Fireworks Planned")]), 0);

                    Event newEvent = event.createEvent(tempValues[headerList.get("Name")], tempValues[headerList.get("Date")], tempValues[headerList.get("Time")], Double.parseDouble(tempValues[headerList.get("VIP Price")]), Double.parseDouble(tempValues[headerList.get("Gold Price")]), Double.parseDouble(tempValues[headerList.get("Silver Price")]), Double.parseDouble(tempValues[headerList.get("Bronze Price")]), Double.parseDouble(tempValues[headerList.get("General Admission Price")]), newVenue, tempValues[headerList.get("Event Type")], 0.0, Integer.parseInt(tempValues[headerList.get("Event ID")]));
                    dataList.put(Integer.parseInt(tempValues[headerList.get("Event ID")]), newEvent);

                }else if(tempValues.length == 21){

                    //send null and zero for venue

                    Venue newVenue = venue.createVenue(tempValues[headerList.get("Venue Type")], tempValues[headerList.get("Venue Name")], Double.parseDouble(tempValues[headerList.get("Seats Unavailable Pct")]), Integer.parseInt(tempValues[headerList.get("Capacity")]), Integer.parseInt(tempValues[headerList.get("Cost")]), ((Double.parseDouble(tempValues[headerList.get("VIP Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))),  ((Double.parseDouble(tempValues[headerList.get("Gold Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), 
                    ((Double.parseDouble(tempValues[headerList.get("Silver Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), ((Double.parseDouble(tempValues[headerList.get("Bronze Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), ((Double.parseDouble(tempValues[headerList.get("General Admission Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))), ((Double.parseDouble(tempValues[headerList.get("Reserved Extra Pct")])/100)*(Integer.parseInt(tempValues[headerList.get("Capacity")]))),
                    false, 0);
                    Event newEvent = event.createEvent(tempValues[headerList.get("Name")], tempValues[headerList.get("Date")], tempValues[headerList.get("Time")], Double.parseDouble(tempValues[headerList.get("VIP Price")]), Double.parseDouble(tempValues[headerList.get("Gold Price")]), Double.parseDouble(tempValues[headerList.get("Silver Price")]), Double.parseDouble(tempValues[headerList.get("Bronze Price")]), Double.parseDouble(tempValues[headerList.get("General Admission Price")]), newVenue, tempValues[headerList.get("Event Type")], 0.0, Integer.parseInt(tempValues[headerList.get("Event ID")]));           
                    dataList.put(Integer.parseInt(tempValues[headerList.get("Event ID")]), newEvent);

                }
            }
            return dataList;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("There was an error reading the event file!");
            return null;
        }
    }

    public static void loadCustomerAPList(HashMap<String, Customer> customerDataIn, HashMap<Integer, Event> eventDataIn, String fileNameIn, boolean transactioNotDoneIn, Logger textLogIn){
        try {
            File fileObj = new File(fileNameIn);
            Scanner scnr = new Scanner(fileObj);
        
            //Storing the header of the file in a HashMap
            //Key: title | Value: Index             
            String[] tempHeader = scnr.nextLine().split(",");
            HashMap <String, Integer> headerList = new HashMap<String,Integer>();
            

            for(int i = 0; i < tempHeader.length; i++){
                headerList.put(tempHeader[i], i);
            }


            //Sets autopurchasing attributes to existing customer objects
            while(scnr.hasNextLine()){
                String [] tempValues = scnr.nextLine().split(",");
                Autopurchasing.chooseEvent(customerDataIn, eventDataIn, tempValues[headerList.get("First")], tempValues[headerList.get("Last")],
                tempValues[headerList.get("Event ID")], transactioNotDoneIn, textLogIn, tempValues[headerList.get("Ticket Type")],tempValues[headerList.get("Ticket Quantity")]);
            }
        } catch (Exception e) {
            System.out.println("There was an error loading the Autopurchasing customer attributes!");
            System.out.println(e);
        }
    }

}
    


