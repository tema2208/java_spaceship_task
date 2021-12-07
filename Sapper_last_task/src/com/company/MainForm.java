package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JButton restartButton;
    private JPanel innerPanel;
    //private JPanel timerPanel;
    private JLabel flagsLabel;
    private JLabel timerLabel;
    private Font font=new Font("Arial",Font.BOLD,12);
    private Color color=new Color(159,159,191);
    private JPanel [][]holders;
    private JButton [][]buttons;
    private JLabel [][]labels;
    private int [][]minefield;
    private int height = 9;
    private int width = 9;


    public MainForm()
    {
        setSize(600, 600);
        mainPanel.setBackground(Color.lightGray);

        innerPanel.setLayout(new GridLayout(height,width));
        innerPanel.removeAll();
        innerPanel.setPreferredSize(new Dimension(width*40,height*40));
        pack();
        innerPanel.setSize(innerPanel.getPreferredSize());

        holders=new JPanel[width][height];
        buttons=new JButton[width][height];
        labels=new JLabel[width][height];
        minefield=new int[width][height];
        for(int y=0;y<height;++y)
            for(int x=0;x<width;++x)
            {
                holders[x][y]=new JPanel(new BorderLayout());

                buttons[x][y]=new JButton();
                buttons[x][y].setFont(font);
                buttons[x][y].setFocusable(false);

                labels[x][y]=new JLabel();
                labels[x][y].setHorizontalAlignment(JLabel.CENTER);
                labels[x][y].setFont(font);

                holders[x][y].add(buttons[x][y]);
                holders[x][y].setBorder(new LineBorder(color));

                innerPanel.add(holders[x][y]);
            }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // написать что делать что бы перезапустить игру
            }
        });
        add(mainPanel);
        setLocationRelativeTo(null);
        //setContentPane(mainPanel);
        setVisible(true);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

//    public void setGameField(int height, int width){
//        this.height = height;
//        this.width = width;
//    }
}


//innerPanel.setLayout(new GridLayout(height,width)); // через это формируем таблицу
//	innerPanel.removeAll();
//	innerPanel.setPreferredSize(new Dimension(width*40,height*40));
//	pack();
//	innerPanel.setSize(innerPanel.getPreferredSize());
//	holders=new JPanel[width][height];
//	buttons=new JButton[width][height];
//	labels=new JLabel[width][height];
//	minefield=new int[width][height];
//	for(int y=0;y<height;++y)
//		for(int x=0;x<width;++x)
//		{
//			holders[x][y]=new JPanel(new BorderLayout());
//
//			buttons[x][y]=new JButton();
//			buttons[x][y].setFont(font);
//			buttons[x][y].setFocusable(false);
//
//			labels[x][y]=new JLabel();
//			labels[x][y].setHorizontalAlignment(JLabel.CENTER);
//			labels[x][y].setFont(font);
//
//			holders[x][y].add(buttons[x][y]);
//			holders[x][y].setBorder(new LineBorder(color));
//
//			innerPanel