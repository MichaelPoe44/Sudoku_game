
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame implements ActionListener{

    JFrame window;
    JButton button1,button2,button3,button4,button5,button6,
        button7,button8,button9;
    JLabel label1, label2, label3,label4,label5,label6,label7;
    JPanel mainPanel, subPanel1,subPanel2,subPanel3,subPanel4,subPanel5,
        subPanel6, subPanel7,subPanel8,subPanel9;

    //JTextField text1,text2,text3,text4,text5,text6,text7,
        //text8,text9;
    GridLayout grid;
    


    public MainWindow(){
        
        makeWindow();
        makeButtons();
        makeLabels();
        window.setVisible(true);
        



    }

    private void makeWindow(){
        window = new JFrame("Sudoku");
        window.setDefaultCloseOperation(2);
        window.setSize(800,800);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
    }

    private void makeButtons(){
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");
        JButton[] buttonsList = {button1,button2,button3,button4,
            button5,button6,button7,button8,button9};
        
        for (int j=0, i=125; j< buttonsList.length;j++, i+=60){
            buttonsList[j].setBounds(i,700,50,50);
            buttonsList[j].addActionListener(this);
            window.add(buttonsList[j]);
        }

    }

    private void makeLabels(){
        //grid = new GridLayout(4,4);

        mainPanel = new JPanel(new GridLayout(3,3,5,5));

        subPanel1 = new JPanel(new GridLayout(3,3));
        subPanel2 = new JPanel(new GridLayout(3,3));
        subPanel3 = new JPanel(new GridLayout(3,3));
        subPanel4 = new JPanel(new GridLayout(3,3));
        subPanel5 = new JPanel(new GridLayout(3,3));
        subPanel6 = new JPanel(new GridLayout(3,3));
        subPanel7 = new JPanel(new GridLayout(3,3));
        subPanel8 = new JPanel(new GridLayout(3,3));
        subPanel9 = new JPanel(new GridLayout(3,3));

        JPanel[] panelsList = {subPanel1,subPanel2,subPanel3,subPanel4,subPanel5,
            subPanel6, subPanel7,subPanel8,subPanel9};
        
        for (JPanel panel : panelsList){
            panel.setBorder(BorderFactory.createLineBorder(new Color(0,0,0),2));

            for (int j=0; j < 9; j++){
                JTextField text = new JTextField();
                text.setHorizontalAlignment(JTextField.CENTER);
                //Dimension textFieldSize = new Dimension(50,50);
                //text.setPreferredSize(textFieldSize);
                panel.add(text);

            }
        //Dimension subPanelSize = new Dimension(150,150);
        //panel.setPreferredSize(subPanelSize);
        mainPanel.add(panel);
        }

        mainPanel.setBounds(90,70,600,600);
        window.add(mainPanel);


    }



    public void actionPerformed(ActionEvent e){
       System.out.println(e.getActionCommand());
    }

   


}