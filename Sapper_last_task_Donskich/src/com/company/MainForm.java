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
    private int height = 9;
    private int width = 9;
    private int maxmins = 10;
    private int gameStatus1; // До начала  -2, в игре = -1, выиграно = 1, проиграно = 0
    private int activeCells;
    private int flagsCount = maxmins;
    private int secondCount = 0;
    private Timer timer;
    private GameStatus gameStatus;

    public MainForm() {
        gameStatus1 = -2;
        gameStatus = GameStatus.BEFORE;

        setContentPane(mainPanel);

        mainPanel.setBackground(Color.lightGray);
        setResizable(false);

        innerPanel.setLayout(new GridLayout(height, width));
        innerPanel.removeAll();
        innerPanel.setPreferredSize(new Dimension(width * 40, height * 40));
        pack();
        innerPanel.setSize(innerPanel.getPreferredSize());

        holders = new JPanel[width][height]; // holders - состоит из панелей
        buttons = new JButton[width][height];
        labels = new JLabel[width][height];
        minefield = new int[width][height]; // массив заполненный числами от -1 до 8, где -1 - бомба. от 0 до 8 - количество бомб вокруг

        ButtonFlagger buttonFlagger = new ButtonFlagger(); // ставит флажок на кнопку
        ButtonOpener buttonOpener = new ButtonOpener(); // Открывает кнопку

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

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }


    private void createFiled(int[][] filed, int maxmins, int origin_x, int origin_y) { //нужно заполнять только при нажатии, и первая пустая + что бы не накладывались друг на друга
        int k = 0;
        while (k < maxmins){
            // заполнили поле бомбами
            k += 1;
            int bomb_x = (int) (Math.random() * 9);
            int bomb_y = (int) (Math.random() * 9);
            if (filed[bomb_x][bomb_y] == -1) k--;
            filed[bomb_x][bomb_y] = -1;
            if (bomb_x == origin_x && bomb_y == origin_y) { //первая открытая клетка всегда не содержит бомбы
                filed[origin_x][origin_y] = 0;
                k--;
            }
        }
        activeCells = width * height - maxmins;
        System.out.println(activeCells);
        for (int i = 0; i < 9; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                if (filed[i][j] != -1) filed[i][j] = bombsNeighbors(filed, i, j);
            }
        }
    }

    private int bombsNeighbors(int[][] filed, int i, int j) { // сделал
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
        if (i != 0 && j != 0 && i != 8 && j != 8){
            array[0] = -1;
            array[1] = 2;
        }
        if(i == 0){
            //array[0] = 0;
            array[1] = 2;
        }
        if(i == 8){
            array[0] = -1;
            array[1] = 1;
        }
        if(j == 0 || j == 8){
            if(i > 0 && i < 8){
                array[0] = -1;
                array[1] = 2;
            }
        }
        return array;
    }

    private int[] smartCheckForСolumns(int i, int j){
        int[] array = {0, 0};
        if (i != 0 && j != 0 && i != 8 && j != 8){
            array[0] = -1;
            array[1] = 2;
        }
        if(i == 0){
            if (j == 0){
                //array[0] = 0;
                array[1] = 2;
            }
            else if (j == 8){
                array[0] = -1;
                array[1] = 1;
            }
            else{
                array[0] = -1;
                array[1] = 2;
            }
        }
        if (i == 8){
            if(j == 0){
               // array[0] = 0;
                array[1] = 2;
            }
            else if(j == 8){
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
               // array[0] = 0;
                array[1] = 2;
            }
        }
        if(j == 8){
            if(i > 0 && i < 8){
                array[0] = -1;
                array[1] = 1;
            }
        }
        return array;
    }

    private void startOpenButtons(int x, int y){
        // нажимаем на кнопку, открывает все рядом стоящие пустые кнопки
        int[] str_array = smartCheckForSting(x, y);
        int[] clmn_array = smartCheckForСolumns(x, y);
        int str_bot = str_array[0];
        int str_top = str_array[1];
        int clmn_bot = clmn_array[0];
        int clmn_top = clmn_array[1];
        for (int i = str_bot; i < str_top; i ++) {
            for (int j = clmn_bot; j < clmn_top; j++) {
                if (minefield[x + i][y + j] != -1) {
                    openEmptyButton(x + i, y + j);
                }
            }
        }
    }

    private void openEmptyButton(int x, int y) {
        if(minefield[x][y] == 0 && buttons[x][y].getParent()!=null){ // если нет поблизости мин и мы эту кнопку не открывали
            open(x,y);
            int[] str_array = smartCheckForSting(x, y);
            int[] clmn_array = smartCheckForСolumns(x, y);
            int str_bot = str_array[0];
            int str_top = str_array[1];
            int clmn_bot = clmn_array[0];
            int clmn_top = clmn_array[1];

            for (int i = str_bot; i < str_top; i++) {
                for (int j = clmn_bot; j < clmn_top; j++) {
                    if (i != 0 || j != 0) openEmptyButton(x + i, y + j);
                }
            }
        }
        if (buttons[x][y].getParent()!=null)open(x,y);
    }

    private void open(int x, int y){
        labels[x][y].setText(Integer.toString(minefield[x][y]));
        if (minefield[x][y] == 0) labels[x][y].setText("");
        if (minefield[x][y] == -1) labels[x][y].setText("*");
        if (minefield[x][y] == -1 && gameStatus1 == -1) { // если наступили на мину - проигрыш
            gameStatus1 = 0;
            labels[x][y].setBackground(Color.RED);
            labels[x][y].setOpaque(true);
        }
        holders[x][y].remove(buttons[x][y]);
        holders[x][y].add(labels[x][y]);
        if (gameStatus1 == -1 || gameStatus1 == -2) activeCells -= 1;

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
        switch (gameStatus1){
            case -2:
                System.out.println("The game has not started yet");
                break;
            case 0:
                lose();
                timer.cancel();
                break;
            case 1:
                win();
                timer.cancel();
                break;
        }
    }

    private class ButtonFlagger extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            JPanel p = (JPanel) (((JButton) (e.getSource())).getParent());
            int Cx = p.getX() / 40, Cy = p.getY() / 40;
            if ((gameStatus1 == -2 || gameStatus1 == -1) && e.getButton() == 3) { // если нажали на парвую кнопку мыши
                if (Objects.equals(buttons[Cx][Cy].getText(), "!")) {
                    buttons[Cx][Cy].setText(""); // если захотим убрать флажок
                    flagsCount--;
                }
                else{
                    buttons[Cx][Cy].setText("!");
                    flagsCount--;
                }
                flagsLabel.setText(String.format("%03d",flagsCount));
                innerPanel.revalidate();
                innerPanel.repaint();
            }
            checkGameStatus(); // что бы выводило что нельзя ставить флажки после завершения игры
        }
    }

    private class ButtonOpener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel p = (JPanel) (((JButton) (e.getSource())).getParent());
            int Cx = p.getX() / 40, Cy = p.getY() / 40;
            if(gameStatus1 == -2 && !Objects.equals(buttons[Cx][Cy].getText(), "!")){
                createFiled(minefield, maxmins, Cx, Cy);
                startOpenButtons(Cx, Cy);
                timer = new Timer();
                timer.schedule(new MyTimer(), 0, 1000);
                gameStatus1 = -1; // меняем статус игры на "В игре"
            }
            if (!Objects.equals(buttons[Cx][Cy].getText(), "!") && gameStatus1 == -1) {
                openEmptyButton(Cx, Cy);
                if (activeCells == 0) gameStatus1 = 1;
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
        //игровое поле должно заполниться нулями, нужно создать новый таймер,
        @Override
        public void actionPerformed(ActionEvent e) {
            setNulls(); // Зануляем
            gameStatus1 = -2; // переводим в режим "до начала"
            timer.cancel();
            timer = new Timer();
            secondCount = 0;
            timerLabel.setText(String.format("%03d", secondCount));
            for (int x = 0; x < height; x++) {
                for (int y = 0; y < height; y++) {
                    if (buttons[x][y].getParent() == null) {
                        holders[x][y].remove(labels[x][y]);
                        holders[x][y].add(buttons[x][y]);
                    }
                    buttons[x][y].setText("");
                }
            }
            flagsCount = maxmins;
            innerPanel.revalidate();
            innerPanel.repaint();
        }
    }

    private void setNulls(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < height; j++){
                minefield[i][j] = 0;
            }
        }
    }

    private void win(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < height; j++){
                if (minefield[i][j] == -1) buttons[i][j].setText("!");
            }
        }
    }

    private void lose(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < height; j++){
                if (minefield[i][j] == -1 && !Objects.equals(buttons[i][j].getText(), "!")) open(i, j);
                if (minefield[i][j] == -1 && Objects.equals(buttons[i][j].getText(), "!")){
                    buttons[i][j].setBackground(Color.YELLOW);
                    buttons[i][j].setOpaque(true);
                }
            }
        }
    }
}

