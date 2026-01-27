
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class SwingCalc extends JFrame implements ActionListener {


    private CalculatorEngine calculatorEng; 
    JButton numbers[] = new JButton[10];
    JButton AddButton, SubtButton, DivButton, MultiButton, EqualButtonn, ModulusButton, Delete, CLR, dec;
    JTextField display;
    JPanel forDisplay, forButtons;

    public SwingCalc() {
       calculatorEng = new CalculatorEngine();
        calculatorLayout();
    }

    private void calculatorLayout() {
       
        //    layout of the calculator
        setSize(360, 550);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        forDisplay = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        add(forDisplay, BorderLayout.NORTH);
        forDisplay.setBackground(Color.BLACK);

        display = new JTextField();
        display.setEditable(false);
        display.setPreferredSize(new Dimension(280, 40));
        display.setFont(new Font("Arial", Font.BOLD, 24));
        forDisplay.add(display);
         
        //         manipulate the number buttons
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].addActionListener(this);
            numbers[i].setFont(new Font("Arial", Font.BOLD, 24));
            numbers[i].setFocusPainted(false);
            numbers[i].setBorderPainted(false);
            numbers[i].setBackground(Color.WHITE);
            numbers[i].setForeground(Color.BLACK);
        }
        
       
        forButtons = new JPanel(new GridLayout(5, 4, 5, 5));
        forButtons.setBorder(BorderFactory.createEmptyBorder());
        forButtons.setBackground(Color.BLACK);
        forButtons.setBorder(BorderFactory.createEmptyBorder(0,7,8,7));
        add(forButtons, BorderLayout.CENTER);

        AddButton = new JButton("+");
        SubtButton = new JButton("-");
        DivButton = new JButton("/");

        ModulusButton = new JButton("%");
        MultiButton = new JButton("x");
        Delete = new JButton("DEL");

        EqualButtonn = new JButton("=");
        CLR = new JButton("CLR");
        dec = new JButton(".");

        JButton empty = new JButton("");
        empty.setEnabled(false);
  
        //     manipulate the operator button
        JButton[] OperaButtons = {AddButton, SubtButton, DivButton, MultiButton, EqualButtonn, ModulusButton, Delete, CLR, dec};
        for (JButton OpeBtns : OperaButtons) {
            OpeBtns.setFont(new Font("Arial", Font.BOLD, 20));
            OpeBtns.addActionListener(this);
            OpeBtns.setFocusPainted(false);
            OpeBtns.setBorderPainted(false);
            OpeBtns.setBackground(Color.ORANGE);
            OpeBtns.setForeground(Color.BLACK);
        }
        
        forButtons.add(CLR);
        forButtons.add(ModulusButton);
        forButtons.add(Delete);
        forButtons.add(DivButton);
        forButtons.add(numbers[7]);

        forButtons.add(numbers[8]);
        forButtons.add(numbers[9]);
        forButtons.add(MultiButton);
        forButtons.add(numbers[4]);
        forButtons.add(numbers[5]);

        forButtons.add(numbers[6]);
        forButtons.add(SubtButton);
        forButtons.add(numbers[1]);
        forButtons.add(numbers[2]);
        forButtons.add(numbers[3]);

        forButtons.add(AddButton);
        forButtons.add(empty);
        forButtons.add(numbers[0]);
        forButtons.add(dec);
        forButtons.add(EqualButtonn);
        
    }

    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < numbers.length; i++) {
            if (e.getSource() == numbers[i]) {
               handleNumbers(i);
               
            }
        }
    
        if(display.getText().isEmpty()) return;
        
         if (e.getSource() == AddButton) handleOperator('+');
       else if (e.getSource() == SubtButton) handleOperator('-');
       else if (e.getSource() == DivButton) handleOperator('/');
       else if(e.getSource()== ModulusButton) handleOperator('%');
       else if (e.getSource() == MultiButton) handleOperator('*');
       else if (e.getSource() == dec) handleDecimal();
       else if (e.getSource() == CLR) handleClear();
       else  if (e.getSource() == Delete) handleDelete();
        else if (e.getSource() == EqualButtonn) handleEqual();
         
    }

    private void handleNumbers(int numbers){
        display.setText(display.getText()+ numbers);
    }
    private void handleOperator(char op){
        calculatorEng.setNum1(Double.parseDouble(display.getText()));
        calculatorEng.setOperator(op);
        display.setText("");
        
    }

     private void handleClear(){
     display.setText("");
    }

    private void handleDecimal(){
        String text= display.getText();
        if(!text.contains(".")){
            display.setText(text+ ".");
        }
    }

    private void handleDelete(){
        String text= display.getText();
        if(!text.isEmpty()){
            display.setText(text.substring(0, text.length()-1));
        }
    }

    private void handleEqual(){
        try{
        calculatorEng.setNum2(Double.parseDouble(display.getText()));
        display.setText(String.valueOf(calculatorEng.calculate()));
        }catch(ArithmeticException ex){
            display.setText("Error!");
        }
        calculatorEng.setOperator('\0');
    }
    public static void main(String[] args) {
        new SwingCalc().setVisible(true);
    }
}
