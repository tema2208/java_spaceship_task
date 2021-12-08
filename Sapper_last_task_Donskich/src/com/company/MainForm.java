package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JButton restartButton;
    private JLabel flagsLabel;
    private JLabel timerLabel;
    private JPanel innerPanel;
    private Font font = new Font("Arial", Font.BOLD, 12);
    private Color color = new Color(159, 159, 191);
    private JPanel[][] holders;
    private JButton[][] buttons;
    private JLabel[][] labels;
    private int[][] minefield;
    private int height = 16;
    private int width = 16;
    private int maxmins = 40;
    private int activeCells;
    private int flagsCount = maxmins;
    private int secondCount = 0;
    private Timer timer;
    private GameStatus gameStatus;
    private Color buttonColor;
    private Color labelColor;

    public MainForm() {
        gameStatus = GameStatus.BEFORE;

        setContentPane(mainPanel);

        setResizable(false);

        innerPanel.setLayout(new GridLayout(height, width));
        innerPanel.removeAll();
        innerPanel.setPreferredSize(new Dimension(width * 40, height * 40));
        pack();
        innerPanel.setSize(innerPanel.getPreferredSize());

        holders = new JPanel[width][height];
        buttons = new JButton[width][height];
        labels = new JLabel[width][height];
        minefield = new int[width][height];

        ButtonFlagger buttonFlagger = new ButtonFlagger();
        ButtonOpener buttonOpener = new ButtonOpener();
        flagsLabel.setText(String.format("%03d",flagsCount));

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                holders[x][y] = new JPanel(new BorderLayout());

                buttons[x][y] = new JButton();
                buttons[x][y].setFont(font);
                buttons[x][y].setFocusable(false);

                labels[x][y] = new JLabel();
                labels[x][y].setHorizontalAlignment(JLabel.CENTER);
                labels[x][y].setFont(font);

                buttons[x][y].addMouseListener(buttonFlagger);
                buttons[x][y].addActionListener(buttonOpener);

                holders[x][y].add(buttons[x][y]);
                holders[x][y].setBorder(new LineBorder(color));

                innerPanel.add(holders[x][y]);
            }
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        restartButton.addActionListener(new buttonRestarter());

        buttonColor = buttons[0][0].getBackground();
        labelColor = labels[0][0].getBackground();

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void createFiled(int[][] filed, int origin_x, int origin_y) {
        int bomb_x;
        int bomb_y;
        for (int k = 0; k < maxmins; k++){
            do{
                bomb_x = (int) (Math.random() * height);
                bomb_y = (int) (Math.random() * width);
            }while(filed[bomb_x][bomb_y] ==- 1 || (bomb_x==origin_x && bomb_y==origin_y));
            filed[bomb_x][bomb_y] = -1;
        }
        activeCells = width * height - maxmins;
        for (int i = 0; i < height; i += 1) {
            for (int j = 0; j < width; j += 1) {
                if (filed[i][j] != -1) filed[i][j] = bombsNeighbors(filed, i, j);
            }
        }
    }

    private int bombsNeighbors(int[][] filed, int i, int j) {
        int count = 0;
        int[] str_array = smartCheckForSting(i, j);
        int[] clmn_array = smartCheckForСolumns(i, j);
        int str_bot = str_array[0];
        int str_top = str_array[1];
        int clmn_bot = clmn_array[0];
        int clmn_top = clmn_array[1];

        for (int k = str_bot; k < str_top; k++) {
            for (int v = clmn_bot; v < clmn_top; v++) {
                if (filed[i + k][j + v] == -1) {
                        count += 1;
                    }
                }
            }
        return count;
    }

    private int[] smartCheckForSting(int i, int j){
        int[] array = {0, 0};
        if (i != 0 && j != 0 && i != (height-1) && j != (width-1)){
            array[0] = -1;
            array[1] = 2;
        }
        if(i == 0){
            array[1] = 2;
        }
        if(i == (height-1)){
            array[0] = -1;
            array[1] = 1;
        }
        if(j == 0 || j == (width-1)){
            if(i > 0 && i < (height-1)){
                array[0] = -1;
                array[1] = 2;
            }
        }
        return array;
    }

    private int[] smartCheckForСolumns(int i, int j){
        int[] array = {0, 0};
        if (i != 0 && j != 0 && i != (height - 1)  && j != (width-1)){
            array[0] = -1;
            array[1] = 2;
        }
        if(i == 0){
            if (j == 0){
                array[1] = 2;
            }
            else if (j == (width-1)){
                array[0] = -1;
                array[1] = 1;
            }
            else{
                array[0] = -1;
                array[1] = 2;
            }
        }
        if (i == (height - 1)){
            if(j == 0){
                array[1] = 2;
            }
            else if(j == (width-1)){
                array[0] = -1;
                array[1] = 1;
            }
            else{
                array[0] = -1;
                array[1] = 2;
            }
        }
        if(j == 0){
            if(i > 0 && i < 8){
                array[1] = 2;
            }
        }
        if(j == (width-1)){
            if(i > 0 && i < (height - 1)){
                array[0] = -1;
                array[1] = 1;
            }
        }
        return array;
    }

    private void openEmptyButton(int x, int y) {
        if(minefield[x][y] == 0 && buttons[x][y].getParent()!=null){
            open(x,y);
            int[] str_array = smartCheckForSting(x, y);
            int[] clmn_array = smartCheckForСolumns(x, y);
            int str_bot = str_array[0];
            int str_top = str_array[1];
            int clmn_bot = clmn_array[0];
            int clmn_top = clmn_array[1];

            for (int i = str_bot; i < str_top; i++) {
                for (int j = clmn_bot; j < clmn_top; j++) {
                    if ((i != 0 || j != 0) && !buttons[x + i][y + j].getText().equals("!")) openEmptyButton(x + i, y + j);
                }
            }
        }
        if (buttons[x][y].getParent()!=null)open(x,y);
    }

    private void open(int x, int y){
        labels[x][y].setText(Integer.toString(minefield[x][y]));
        if (minefield[x][y] == 0) labels[x][y].setText("");
        if (minefield[x][y] == -1){
            labels[x][y].setText("*");
            labels[x][y].setForeground(Color.BLACK);
        }
        if (minefield[x][y] == -1 && gameStatus == GameStatus.DURING) {
            gameStatus = GameStatus.LOSE;
            labels[x][y].setBackground(Color.RED);
            labels[x][y].setOpaque(true);
        }
        holders[x][y].remove(buttons[x][y]);
        holders[x][y].add(labels[x][y]);
        if ((gameStatus == GameStatus.DURING || gameStatus == GameStatus.BEFORE) && minefield[x][y] != -1) activeCells -= 1;

        switch (minefield[x][y])
        {
            case 1: labels[x][y].setForeground(Color.BLUE);break;
            case 2: labels[x][y].setForeground(new Color(0,127,0));break;
            case 3: labels[x][y].setForeground(Color.RED);break;
            case 4: labels[x][y].setForeground(new Color(0,0,63));break;
            case 5: labels[x][y].setForeground(new Color(63,0,63));break;
            case 6: labels[x][y].setForeground(new Color(0,127,127));break;
            case 7: labels[x][y].setForeground(Color.BLACK);break;
            case 8: labels[x][y].setForeground(new Color(63,0,0));break;
        }
    }

    private void checkGameStatus(){
        switch (gameStatus){
            case LOSE:
                lose();
                timer.cancel();
                break;
            case WIN:
                win();
                timer.cancel();
                break;
        }
    }

    private class ButtonFlagger extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            JPanel p = (JPanel) (((JButton) (e.getSource())).getParent());
            int Cx = p.getX() / 40, Cy = p.getY() / 40;
            if ((gameStatus == GameStatus.BEFORE || gameStatus == GameStatus.DURING) && e.getButton() == 3) {
                if (buttons[Cx][Cy].getText().equals("!")) {
                    buttons[Cx][Cy].setText("");
                    flagsCount++;
                }
                else{
                    buttons[Cx][Cy].setText("!");
                    flagsCount--;
                }
                flagsLabel.setText(String.format("%03d",flagsCount));
            }
            checkGameStatus();
        }
    }

    private class ButtonOpener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel p = (JPanel) (((JButton) (e.getSource())).getParent());
            int Cx = p.getX() / 40, Cy = p.getY() / 40;
            if(gameStatus == GameStatus.BEFORE && !buttons[Cx][Cy].getText().equals("!")){
                createFiled(minefield, Cx, Cy);
                openEmptyButton(Cx, Cy);
                timer = new Timer();
                timer.schedule(new MyTimer(), 0, 1000);
                gameStatus = GameStatus.DURING;
            }
            if (!buttons[Cx][Cy].getText().equals("!") && gameStatus == GameStatus.DURING) {
                openEmptyButton(Cx, Cy);
                if (activeCells == 0) gameStatus = GameStatus.WIN;
            }
            checkGameStatus();
            innerPanel.revalidate();
            innerPanel.repaint();
        }
    }

    private class MyTimer extends TimerTask{
        @Override
        public void run() {
            if (secondCount == 999) cancel();
            secondCount += 1;
            timerLabel.setText(String.format("%03d",secondCount));
        }
    }

    private class buttonRestarter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setNulls();
            gameStatus = GameStatus.BEFORE;
            timer.cancel();
            timer = new Timer();
            secondCount = 0;
            timerLabel.setText(String.format("%03d", secondCount));
            for (int x = 0; x < height; x++) {
                for (int y = 0; y < width; y++) {
                    labels[x][y].setBackground(labelColor);
                    buttons[x][y].setBackground(buttonColor);
                    if (buttons[x][y].getParent() == null) {
                        holders[x][y].remove(labels[x][y]);
                        holders[x][y].add(buttons[x][y]);
                    }
                    buttons[x][y].setText("");
                }
            }
            flagsCount = maxmins;
            flagsLabel.setText(String.format("%03d",flagsCount));
            innerPanel.revalidate();
            innerPanel.repaint();
        }
    }

    private void setNulls(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                minefield[i][j] = 0;
            }
        }
    }

    private void win(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (minefield[i][j] == -1) buttons[i][j].setText("!");
            }
        }
    }

    private void lose(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (minefield[i][j] == -1 && !buttons[i][j].getText().equals("!")) open(i, j);
                if (minefield[i][j] != -1 && buttons[i][j].getText().equals("!")){
                    buttons[i][j].setBackground(Color.YELLOW);
                    buttons[i][j].setOpaque(true);
                }
            }
        }
    }
}

