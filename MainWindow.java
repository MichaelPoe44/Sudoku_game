
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;

public class MainWindow extends JFrame implements ActionListener{

    JFrame window; 
    boolean gameStarted = false;
    JButton[] buttons = new JButton[10];
    JPanel mainPanel;
    JPanel[] subPanels = new JPanel[9];
    JTextField[][] gameBoard = new JTextField[9][9];
    JTextField focusedBox;
    JPanel subPanel;
    Maker make = new Maker();
    final int[][] solvedBoard = make.getBoard();
    Color black = new Color(0,0,0);
    Color red = new Color(255,0,0);
    Color blue = new Color(0,0,255);
    Color grey = new Color(128,128,128);
    Border textBorder = BorderFactory.createLineBorder(grey, 1);
    Border focusedBorder = BorderFactory.createLineBorder(blue, 1);
    Random random = new Random();

    
    public MainWindow(){
        //constructor
        
        makeWindow();
        makeButtons();
        makeLabels();
        startingGame();
        window.setVisible(true);
        
        
        
        
        
    }
    
    private void makeWindow(){
        //creates Window
        window = new JFrame("Sudoku");
        window.setDefaultCloseOperation(2);
        window.setSize(800,800);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
    }

    private void makeButtons(){
        //creates number buttons
        for (int i=0, j=155; i < 9; i++, j+=60){
            buttons[i] = new JButton(String.format("%d",i+1));
            buttons[i].setBounds(j,700,50,50);
            buttons[i].addActionListener(this);
            window.add(buttons[i]);
        }
        // a "clear" button
        buttons[9] = new JButton("clear");
        buttons[9].setBounds(95,700,50,50);
        buttons[9].addActionListener(this);
        window.add(buttons[9]);
        

    }

    private void makeLabels(){
        

        mainPanel = new JPanel(new GridLayout(3,3,5,5));

        // creates panels and text fields in a way to get positions to match
        // the solved board
        for (int PanelRow=0; PanelRow < 3; PanelRow++){
            for (int PanelColumn=0; PanelColumn < 3; PanelColumn++){
                
                subPanel = new JPanel(new GridLayout(3,3));
                subPanel.setBorder(BorderFactory.createLineBorder(black,2));

                for (int subRow=0; subRow < 3; subRow++){
                    for (int subColumn=0; subColumn < 3; subColumn++){
                        int row = (PanelRow * 3) + subRow;
                        int column = (PanelColumn * 3) + subColumn;
                        gameBoard[row][column] = new JTextField();
                        gameBoard[row][column].setHorizontalAlignment(JTextField.CENTER);
                        gameBoard[row][column].setFont(new Font("Arial", Font.PLAIN, 24));
                        gameBoard[row][column].setBorder(textBorder);
                        
                        //gameBoard[row][column].setText(String.format("%d,%d", row,column));


                        ((AbstractDocument) gameBoard[row][column].getDocument()).setDocumentFilter(new DigitFilter());
                        gameBoard[row][column].addFocusListener(new FocusListener() {
                            public void focusGained(FocusEvent e){
                                focusedBox =  (JTextField) e.getSource();
                                focusedBox.setBorder(focusedBorder);
                            }
                            public void focusLost(FocusEvent e){
                                focusedBox.setBorder(textBorder);
                            }
                        
                        });
                        gameBoard[row][column].getDocument().addDocumentListener(new DocumentListener(){

                            public void insertUpdate(DocumentEvent e){
                                
                                checkCorrectPlacement(row, column);
                                shouldRemoveButton(gameBoard[row][column].getText());
                                
                            }
                            public void removeUpdate(DocumentEvent e){
                                
                            }
                            public void changedUpdate(DocumentEvent e){

                            }


                        });
                        subPanel.add(gameBoard[row][column]);

                    }
                }

                mainPanel.add(subPanel);

            }

        }
        mainPanel.setBounds(90,70,600,600);
        window.add(mainPanel);
    
    }
    








    static class DigitFilter extends DocumentFilter {
        private static final int MAX_LENGTH = 1;

        
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }

            if ((fb.getDocument().getLength() + string.length()) <= MAX_LENGTH && string.matches("[1-9]")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }
            if (text.equals("clear")){
                super.replace(fb, offset, length, "", attrs);
            }
            if ((fb.getDocument().getLength() - length + text.length()) <= MAX_LENGTH && text.matches("[1-9]")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }


    private void checkCorrectPlacement(int row, int column){
        if (gameStarted){
            String correctNumber = String.format("%d",solvedBoard[row][column]);
            String placedNumber = gameBoard[row][column].getText();
            if (placedNumber.equals(correctNumber)){
                gameBoard[row][column].setForeground(blue);
                gameBoard[row][column].setEditable(false);
            }
            else{
                gameBoard[row][column].setForeground(red);
            }
        }
        
    }

    private void startingGame(){
        //reveals 32 starting numbers
        
        int placed = 0;
        while (placed < 32){
            int row = random.nextInt(9);
            int column = random.nextInt(9);
            String text = gameBoard[row][column].getText();
            if (text.trim().isEmpty()){
                gameBoard[row][column].setText(String.format("%d",solvedBoard[row][column]));
                gameBoard[row][column].setEditable(false);
                placed++;
            }
            
        }
        gameStarted = true;
    }



    private void shouldRemoveButton(String number){
        int intNumber = Integer.parseInt(number);
        // int placedCorrect = 0;
        JTextField[] textFields = new JTextField[9];
        for (int row=0; row<9; row++){
            for (int column=0; column<9; column++){
                if (solvedBoard[row][column] == intNumber){
                    if (gameBoard[row][column].getText().equals(number)){
                        textFields[row] = gameBoard[row][column];
                        
                    }
                    else return;
                }
            }

        }
        for (int i=0; i < textFields.length; i++){
            textFields[i].setEditable(false);
        }
        window.remove(buttons[intNumber-1]);

        
        
        


    }



    public void actionPerformed(ActionEvent e){
        if (focusedBox!=null){
            String buttonNumber = e.getActionCommand();

            if (focusedBox.isEditable()) focusedBox.setText(buttonNumber);
            
        }
        System.out.println(e.getActionCommand());
    }




}