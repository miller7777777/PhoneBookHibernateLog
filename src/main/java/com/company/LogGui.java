package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mille_000 on 10.06.2015.
 */
public class LogGui {

    JTextArea textArea;
    ArrayList<Log> list;
    JScrollPane scrollBar;

    public LogGui(ArrayList<Log> list) {
        this.list = list;
    }

    public void build(){

        JFrame frame = new JFrame("Log");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(200, 200, 600, 400);

        textArea = new JTextArea();
        scrollBar = new JScrollPane(textArea);


        frame.getContentPane().add(BorderLayout.CENTER, scrollBar);
//        frame.getContentPane().add(scrollBar);
        frame.setVisible(true);

        String s = "";



            for (int i = 0; i < list.size(); i++) {

                String s1 = list.get(i).toString() + "\n";
                s = s + s1;
            }

        textArea.setText(s);











    }
}
