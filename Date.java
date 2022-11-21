public class Date {
    //Attributes
    private int day;
    private int month;
    private int year;

    public Date(int monthIn, int dayIn, int yearIn){
        this.day = dayIn;
        this.month = monthIn;
        this.year = yearIn;
    }


    //Getters and setters

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    
}
