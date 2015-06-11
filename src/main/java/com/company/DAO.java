package com.company;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;


/**
 * Created by mille_000 on 09.06.2015.
 */
public class DAO {
    private SessionFactory factory;

    public DAO() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public void addContact(Contact contact){
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.save(contact);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen())
            session.close();
//            factory.close();

        }
    }

    public Contact getContactById(int id){

        Session session = null;
        Contact contact = null;

        try{
            session = factory.openSession();
//            session.beginTransaction();
            contact = (Contact) session.load(Contact.class, id);
            Hibernate.initialize(contact);
//            session.getTransaction().commit();

            //

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null && session.isOpen())
                session.close();
        }return contact;
    }

    public Contact getContactByPhone(String phone){
        Session session = null;
        Contact contact = null;
        try{
            session = factory.openSession();
            session.beginTransaction();
            Query query = (Query) session.createQuery("FROM Contact WHERE phone = :phone").setString("phone", phone);
            contact = (Contact) query.uniqueResult();
//            Hibernate.initialize(contact);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return contact;
    }

    public ArrayList<Contact> getAllContacts(){
        Session session = null;
        ArrayList<Contact> contacts = null;
        try{
            session = factory.openSession();
            session.beginTransaction();
            contacts = (ArrayList<Contact>)session.createCriteria(Contact.class).list();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return contacts;
    }

    public void deleteContact(String phone){
        Session session = null;
        Contact contact = getContactByPhone(phone);
        try{
            session = factory.openSession();
            session.beginTransaction();
            session.delete(contact);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen())
                session.close();
        }


    }

    public void close(){
        factory.close();

    }

    public void updateContact(Contact contact, int id){
        Session session = null;
        Contact updateContact = getContactById(id);
        System.out.println(updateContact);
        updateContact.setName(contact.getName());
        updateContact.setPhone(contact.getPhone());
        updateContact.setEmail(contact.getEmail());
//        Contact contact = getContactByPhone(phone);
        try{
            session = factory.openSession();
            session.beginTransaction();
            session.update(updateContact);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen())
                session.close();
        }

    }
}
