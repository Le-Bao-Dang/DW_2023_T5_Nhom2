package bean;

public class Thoigiandim {
    private int id;
    private int day;
    private int month;
    private int year;

    public Thoigiandim(int id, int day, int month, int year) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
