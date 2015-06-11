package com.company;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

/**
 * Created by mille_000 on 10.06.2015.
 */
public class DAOlog {

    private SessionFactory factory;

    public DAOlog() {factory = new Configuration().configure().buildSessionFactory();}

    public void addEvent(Log log){

        Session session = null;

        try {
            session = factory.openSession();
            session.beginTransaction();
            session.save(log);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            factory.close();
        }
    }

    public ArrayList<Log> showLog(){

        Session session = null;
        ArrayList<Log> logList = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            logList = (ArrayList<Log>)session.createCriteria(Log.class).list();


        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return logList;
    }

    public void close(){
        factory.close();

    }
}
