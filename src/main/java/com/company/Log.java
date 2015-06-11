package com.company;



import javax.persistence.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mille_000 on 10.06.2015.
 */
@Entity
@Table(name = "log_table")
public class Log implements Observer {

    private int id;
    private String time;
    private String event;

    public Log() {
    }

    public Log(String time, String event) {
        this.time = time;
        this.event = event;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getEvent() {
        return event;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void update(Observable o, Object arg) {

    }

    @Override
    public String toString(){

        return  id + " \t" + time + " \t" + event;
    }
}
