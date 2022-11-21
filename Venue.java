//Author: Isabella Gaytan
//CS3331: Advanced Object-Oriented Programming
//Dr. Daniel Mejia
//PA2 - Venue Abstract Class

public abstract class Venue {
    //Attributes
    private String venueName;
    private double pctSeatUnavailable; 
    private int venueCapacity;
    private int venueCost;
    private double vipPct;
    private double goldPct;
    private double silverPct;
    private double bronzePct;
    private double gaPct;
    private double reservedExtraPct;
    private boolean fireWorks = false;
    private double fireworksCost = 0;


    //Constructors
    public Venue(){
    }

    public Venue(String venueNameIn, double pctSeatUnavailableIn, int venueCapacityIn, int venueCostIn,  double vipPctIn, double goldPctIn,
            double silverPctIn, double bronzePctIn, double gaPctIn, double reservedExtraPctIn, boolean fireWorksIn, double fireworksCostIn) {
        this.venueName = venueNameIn;
        this.pctSeatUnavailable = pctSeatUnavailableIn;
        this.venueCapacity = venueCapacityIn;
        this.venueCost = venueCostIn;
        this.vipPct = vipPctIn;
        this.goldPct = goldPctIn;
        this.silverPct = silverPctIn;
        this.bronzePct = bronzePctIn;
        this.gaPct = gaPctIn;
        this.reservedExtraPct = reservedExtraPctIn;
        this.fireWorks = fireWorksIn;
        this.fireworksCost = fireworksCostIn;
    }


    public Venue(String venueName, double pctSeatUnavailable, int venueCapacity, int venueCost, double vipPct,
            double goldPct, double silverPct, double bronzePct, double gaPct, double reservedExtraPct,
            boolean fireWorks) {
        this.venueName = venueName;
        this.pctSeatUnavailable = pctSeatUnavailable;
        this.venueCapacity = venueCapacity;
        this.venueCost = venueCost;
        this.vipPct = vipPct;
        this.goldPct = goldPct;
        this.silverPct = silverPct;
        this.bronzePct = bronzePct;
        this.gaPct = gaPct;
        this.reservedExtraPct = reservedExtraPct;
        this.fireWorks = fireWorks;
    }


    public Venue(String venueName, double pctSeatUnavailable, int venueCapacity, int venueCost, double vipPct,
            double goldPct, double silverPct, double bronzePct, double gaPct, double reservedExtraPct) {
        this.venueName = venueName;
        this.pctSeatUnavailable = pctSeatUnavailable;
        this.venueCapacity = venueCapacity;
        this.venueCost = venueCost;
        this.vipPct = vipPct;
        this.goldPct = goldPct;
        this.silverPct = silverPct;
        this.bronzePct = bronzePct;
        this.gaPct = gaPct;
        this.reservedExtraPct = reservedExtraPct;
    }

    //Getters and Setters

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public double getPctSeatUnavailable() {
        return pctSeatUnavailable;
    }

    public void setPctSeatUnavailable(double d) {
        this.pctSeatUnavailable = d;
    }

    public int getVenueCapacity() {
        return venueCapacity;
    }

    public void setVenueCapacity(int venueCapacity) {
        this.venueCapacity = venueCapacity;
    }

    public int getVenueCost() {
        return venueCost;
    }

    public void setVenueCost(int venueCost) {
        this.venueCost = venueCost;
    }

    public double getVipPct() {
        return vipPct;
    }

    public void setVipPct(double vipPct) {
        this.vipPct = vipPct;
    }

    public double getGoldPct() {
        return goldPct;
    }

    public void setGoldPct(double goldPct) {
        this.goldPct = goldPct;
    }

    public double getSilverPct() {
        return silverPct;
    }

    public void setSilverPct(double silverPct) {
        this.silverPct = silverPct;
    }

    public double getBronzePct() {
        return bronzePct;
    }

    public void setBronzePct(double bronzePct) {
        this.bronzePct = bronzePct;
    }

    public double getGaPct() {
        return gaPct;
    }

    public void setGaPct(double gaPct) {
        this.gaPct = gaPct;
    }

    public double getReservedExtraPct() {
        return reservedExtraPct;
    }

    public void setReservedExtraPct(double reservedExtraPct) {
        this.reservedExtraPct = reservedExtraPct;
    }

    public boolean getFireWorks() {
        return fireWorks;
    }

    public void setFireWorks(boolean fireWorks) {
        this.fireWorks = fireWorks;
    }

    public double getFireworksCost() {
        return fireworksCost;
    }

    public void setFireworksCost(double fireworksCost) {
        this.fireworksCost = fireworksCost;
    }


    public int seatPercentage(int venueCapacityIn, Event eventIn, String ticketType){
        //return percentage of seats available 
        if(ticketType.equalsIgnoreCase("VIP")){
            return (int)(((eventIn.getVenueType().getVipPct())/(eventIn.getVenueType().getVenueCapacity()))*(100));
        }
        else if(ticketType.equalsIgnoreCase("Gold")){
            return (int)(((eventIn.getVenueType().getGoldPct())/(eventIn.getVenueType().getVenueCapacity()))*(100));
        }
        else if(ticketType.equalsIgnoreCase("Silver")){
            return (int)(((eventIn.getVenueType().getSilverPct())/(eventIn.getVenueType().getVenueCapacity()))*(100));
        }
        else if(ticketType.equalsIgnoreCase("Bronze")){
            return (int)(((eventIn.getVenueType().getBronzePct())/(eventIn.getVenueType().getVenueCapacity()))*(100));
        }else if(ticketType.equalsIgnoreCase("Ga")){
            return (int)(((eventIn.getVenueType().getGaPct())/(eventIn.getVenueType().getVenueCapacity()))*(100));
        }
        return 0;
    }

}

