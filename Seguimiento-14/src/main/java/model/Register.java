package model;

import java.util.Date;

public class Register implements Comparable<Register>{
    private double ammount;
    private String description;
    private RegisterType type;
    private Date date;

    public Register(double ammount, String description, RegisterType type, Date date) {
        this.ammount = ammount;
        this.description = description;
        this.type = type;
        this.date = date;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RegisterType getType() {
        return type;
    }

    public void setType(RegisterType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Register register) {
        return register.date.compareTo(this.date);
    }
}
