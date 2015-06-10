package com.company;

import javax.persistence.*;

/**
 * Created by mille_000 on 07.06.2015.
 */

@Entity
@Table(name = "test_table")
public class Contact {

    private String name;
    private String phone;
    private String email;
    private int id;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Column(unique = true)
    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact() {
    }


    public Contact(String name, String phone, String email) {

        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString(){

        return  id + " " + name + " " + phone + " " + email;
    }
}
