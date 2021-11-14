package com.company;

import javax.swing.*;

public class MyFrame extends JFrame {
    public MyFrame()
    {
        setTitle("JavaCalc");
        setSize(300,300);
        setBounds(0,0,300,300);
        setLocationRelativeTo(null);
        JPanel panel=new MyPanel();
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}