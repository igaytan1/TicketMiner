//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - Logger design Pattern Class


import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Logger {

    private static Logger logObj;

    private String action;
    private ArrayList<String> messageList = new ArrayList<>();

    private Logger(){

    }


    public static synchronized Logger getInstance(){
        if(logObj == null){
            logObj = new Logger();
        }
        return logObj;
    }


    public String getAction() {
        return action;
    }


    public void setAction(String action) {
        this.action = action;
    }


    public void log(String messageIn){
        messageList.add(messageIn);
    }


    public void writeToFile(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("TicketMinerLog.txt"))){
            for(int i = 0; i < messageList.size() ; i++){
                writer.write(messageList.get(i) + "\n");

            } 
        } catch (Exception e) {
            System.out.println("There was an error creating the file!");
        }
        
    }
    
}
