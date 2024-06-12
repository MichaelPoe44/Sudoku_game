
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener{

    JFrame window; 
    JButton[] buttons = new JButton[9];
    JPanel mainPanel;
    JPanel[] subPanels = new JPanel[9];
    JTextField[][] texts = new JTextField[9][9];
    JTextField focusedBox;
    JPanel subPanel;

    
    public MainWindow(){
        //constructor
        
        makeWindow();
        makeButtons();
        makeLabels();
        window.setVisible(true);
        Maker make = new Maker();
        int[][] completedBoard = make.getBoard();
        
        
        
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
        //creates buttons
        for (int i=0, j=125; i < buttons.length; i++, j+=60){
            buttons[i] = new JButton(String.format("%d",i+1));
            buttons[i].setBounds(j,700,50,50);
            buttons[i].addActionListener(this);
            window.add(buttons[i]);

        }
        

    }

    private void makeLabels(){
        

        mainPanel = new JPanel(new GridLayout(3,3,5,5));

        
        for (int PanelRow=0; PanelRow < 3; PanelRow++){
            for (int PanelColumn=0; PanelColumn < 3; PanelColumn++){
                //int num = subPanelColumn + subPanelRow;
                subPanel = new JPanel(new GridLayout(3,3));
                subPanel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),2));

                for (int subRow=0; subRow < 3; subRow++){
                    for (int subColumn=0; subColumn < 3; subColumn++){
                        int row = (PanelRow * 3) + subRow;
                        int column = (PanelColumn * 3) + subColumn;
                        texts[row][column] = new JTextField();
                        texts[row][column].setHorizontalAlignment(JTextField.CENTER);
                        ///////////
                        texts[row][column].setText(String.format("(%d,%d)", row,column));
                        ////////////
                        ((AbstractDocument) texts[row][column].getDocument()).setDocumentFilter(new DigitFilter());
                        texts[row][column].addFocusListener(new FocusListener() {
                            public void focusGained(FocusEvent e){
                                focusedBox =  (JTextField) e.getSource();
                            }
                            public void focusLost(FocusEvent e){
                                
                            }
                        
                        });
                        subPanel.add(texts[row][column]);

                    }
                }

                mainPanel.add(subPanel);

            }

        }
        mainPanel.setBounds(90,70,600,600);
        window.add(mainPanel);
    
    }
    //     for (int i=0; i < 9; i++){
    //         subPanels[i] = new JPanel(new GridLayout(3,3));
    //         subPanels[i].setBorder(BorderFactory.createLineBorder(new Color(0,0,0),2));

    //         for (int j=0; j < 9; j++){
                
    //             texts[i][j] = new JTextField();
    //             texts[i][j].setHorizontalAlignment(JTextField.CENTER);


    //             //test
                
    //             int poo = 6;
    //             int num;
    //             if (j < 3){
    //                 num = j + (i*3);
    //             }
    //             else if (3 <= j && j < 6) {
    //                 num = poo+j+(i * 3);
    //             }
    //             else{
    //                 num = (2*poo) + j + (i*3);
    //             }

    //             texts[i][j].setText(String.format("(%d,%d)", i,j));

            
    //             // end
                
    //             ((AbstractDocument) texts[i][j].getDocument()).setDocumentFilter(new DigitFilter());
    //             texts[i][j].addFocusListener(new FocusListener() {
    //                 public void focusGained(FocusEvent e){
    //                     focusedBox =  (JTextField) e.getSource();
    //                 }
    //                 public void focusLost(FocusEvent e){
                        
    //                 }

                    
    //             });
    //             subPanels[i].add(texts[i][j]);

    //         }
        
    //         mainPanel.add(subPanels[i]);
    //     }
        

    //     mainPanel.setBounds(90,70,600,600);
    //     window.add(mainPanel);


    // }

    static class DigitFilter extends DocumentFilter {
        private static final int MAX_LENGTH = 1;

        
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }

            if ((fb.getDocument().getLength() + string.length()) <= MAX_LENGTH && string.matches("\\d")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }
            if ((fb.getDocument().getLength() - length + text.length()) <= MAX_LENGTH && text.matches("\\d")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }



    public void actionPerformed(ActionEvent e){
        if (focusedBox!=null){
            focusedBox.setText(e.getActionCommand());
            focusedBox=null;
        }
        System.out.println(e.getActionCommand());
    }




}