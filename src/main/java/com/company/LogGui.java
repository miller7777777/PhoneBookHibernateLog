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

    public LogGui(ArrayList<Log> list) {
        this.list = list;
    }

    public void build(){

        JFrame frame = new JFrame("Log");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(200, 200, 400, 300);

        textArea = new JTextArea();

        frame.getContentPane().add(BorderLayout.CENTER, textArea);
        frame.setVisible(true);



            for (int i = 0; i < list.size(); i++) {

                String s = list.get(i).toString() + "\n";
                textArea.setText(s);
            }








    }
}
