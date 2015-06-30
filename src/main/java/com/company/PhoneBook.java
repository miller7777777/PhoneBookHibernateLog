package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by mille_000 on 07.06.2015.
 */
public class PhoneBook extends Observable{

    private BufferedReader reader;
    private Contact contact;
    private String answer;
    private int choose;
    private DAO dao;
    private DAOlog daoLog;
    private LogGui logGui;
    private ArrayList<Log> list;
    protected ArrayList<LogGui> windows;
    int logGuiCount = 0; //счетчик открытых окон;

//    private Log log;

    public void start(){

        //выводит меню в бесконечном цикле

        reader = new BufferedReader(new InputStreamReader(System.in));
        dao = new DAO();
        daoLog = new DAOlog();
        windows = new ArrayList<LogGui>();


//        Log log = new Log();

        while (true){

            System.out.println("");
            System.out.println("Phonebook menue:");
            System.out.println("1 - add new contact");
            System.out.println("2 - delete contact by phone");
            System.out.println("3 - show all contact");
            System.out.println("4 - find contact by phone");
            System.out.println("5 - edit contact by phone");
            System.out.println("6 - show log");
            System.out.println("7 - exit");
            System.out.println("");
            System.out.println("Input command:");

            try {
                answer = reader.readLine();
                choose = Integer.parseInt(answer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (choose){

                case 1: {
                    addContact();
                    break;
                }
                case 2: {
                    deleteByPhone();
                    break;
                }
                case 3: {
                    showAll();
                    break;
                }
                case 4: {
                    findByPhone();
                    break;
                }
                case 5: {
                    editByPhone();
                    break;
                }case 6: {
                    showLog();
                    break;
                }
                case 7: {
                    close();
                    break;
                }
                default: {
                    System.out.println("Wrong input!");
                    break;
                }
            }

        }

    }

    private void showLog() {

        list = null;

        list = daoLog.showLog();
        logGuiCount++;

        LogGui logGui = new LogGui(list, logGuiCount);




        logGui.build();
        addObserver(logGui);
        windows.add(logGui);

        System.out.println("");
        System.out.println("Log:");
        System.out.println("");

        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i).toString());

        }
        System.out.println("");
        String message = "Log showed.";
        daoLog.addEvent(new Log(time(), message));
        list = daoLog.showLog();
        super.setChanged();
        notifyObservers(list);
    }

    private void close() {
        try {
            reader.close();
            dao.close();
            daoLog.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editByPhone() {

        System.out.println("Edit contact by phone");
        try {
            System.out.println("Input phone:");

            String phone = reader.readLine();
            Contact contact = dao.getContactByPhone(phone);
            if(contact == null){
                System.out.println("Contact not found!");
                return;
            }
            System.out.println("Contact:");
            System.out.println(contact.toString());
            System.out.println("");
            System.out.println("Input new name:");
            String name1 = reader.readLine();



            System.out.println("Input new phone:");
            String phone1 = reader.readLine();

            System.out.println("Input new email:");
            String email1 = reader.readLine();

            Contact c = new Contact(name1, phone1, email1);
            dao.updateContact(c, contact.getId());
            String message = "Contact id= " + contact.getId() + " updated.";
            daoLog.addEvent(new Log(time(), message));
            list = daoLog.showLog();
            super.setChanged();
            notifyObservers(list);




        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
    }

    private void findByPhone() {

        System.out.println("Find contact by phone");
        try {
            System.out.println("Input phone:");

            String phone = reader.readLine();
            Contact contact = dao.getContactByPhone(phone);
            System.out.println("Contact:");
            System.out.println(contact.toString());
            System.out.println("");
            String message = contact.toString() + " finded by phone" ;
            daoLog.addEvent(new Log(time(), message));
            list = daoLog.showLog();
            super.setChanged();
            notifyObservers(list);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void showAll() {

        ArrayList<Contact> listOfContact = null;
        listOfContact = dao.getAllContacts();

        System.out.println("All contacts:");
        System.out.println("");

        for (int i = 0; i < listOfContact.size(); i++) {

            System.out.println(listOfContact.get(i).toString());

        }
        System.out.println("");
        String message = "All contacts showed.";
        daoLog.addEvent(new Log(time(), message));
        list = daoLog.showLog();
        super.setChanged();
        notifyObservers(list);

    }

    private void deleteByPhone() {

        System.out.println("Delete contact by phone");
        try {
            System.out.println("Input phone:");

            String phone = reader.readLine();
            Contact contact = dao.getContactByPhone(phone);
            dao.deleteContact(phone);
            System.out.println("Contact:");
            System.out.println(contact.toString() + " deleted.");
            System.out.println("");
            String message = contact.toString() + " deleted.";
            daoLog.addEvent(new Log(time(), message));
            list = daoLog.showLog();
            super.setChanged();
            notifyObservers(list);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void addContact() {

        System.out.println("Add new contact");
        try {
            System.out.println("Input name:");
            String name = reader.readLine();
            System.out.println("Input phone:");
            String phone = reader.readLine();

            System.out.println("Input email:");
            String email = reader.readLine();

            dao.addContact(new Contact(name, phone, email));

            String message = "New contact (name = " + name + ") added.";
            daoLog.addEvent(new Log(time(), message));
            list = daoLog.showLog();
            super.setChanged();
            notifyObservers(list);





        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private String time(){

        long curTime = System.currentTimeMillis();
        String curStringDate = new SimpleDateFormat("dd.MM.yyyy   HH:mm:ss").format(curTime);
        return curStringDate;
    }




}
