package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel
{
    private JTextField textField = new JTextField(25);
    private JButton[] buttons = new JButton[10];
    private ActionListener textListener = new DigitListener();
    private ActionListener commandListener = new CommandListener();

    public MyPanel()
    {
        setBackground(new Color(127, 223, 127));
        add(textField);
        for (int i = 0; i < 10; i++)
        {
            buttons[i] = new JButton(Integer.toString(i));
            buttons[i].addActionListener(textListener);
            add(buttons[i]);
        }
        addCommandButtons();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        String str = "Java Swing App";
        Font font = new Font("Arial", Font.BOLD, 25);
        setFont(font);
        g.drawString(str, 10, 200);
    }

    private void addCommandButtons()
    {
        JButton button = new JButton("+");
        add(button);
        button = new JButton("-");
        add(button);
        button = new JButton("*");
        add(button);
        button = new JButton("/");
        add(button);
        button = new JButton("=");
        add(button);
        button = new JButton("C");
        button.addActionListener(commandListener);
        add(button);
    }

    private class DigitListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            textField.setText(textField.getText() + e.getActionCommand());
        }
    }

    private class CommandListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equals("C"))
                textField.setText("0x");
        }
    }

}