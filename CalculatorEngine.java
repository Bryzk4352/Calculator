/**
* CalculatorEngine handles basic arithmetic operations
* it support +, - *, /, %
* stores num1, num2, and the operators
*/

public class CalculatorEngine {

    private double num1=0;
    private double num2=0;
    private double result=0;
    private char operator;

    public void setNum1(double n1){
        this.num1= n1;
    }

    public void setNum2(double n2){
        this.num2= n2;
    }
    
    public char getOperator(){
        return operator;
    }
    public void setOperator(char op){
      this.operator= op;
    }

    public double calculate(){
        switch(operator){
            case '+' -> result= num1 + num2;

            case '-' -> result =  num1-num2;

            case '*' -> result = num1 * num2;

            case '/' -> {
                if(num2==0) throw new ArithmeticException("Cannot divided by zero");
                else  result = num1/num2;
            }


             case '%' -> {
                 if(num2==0) throw new ArithmeticException("Modulo by zero!");
                 else result = num1 % num2;
            }

            default -> throw new IllegalArgumentException(" No operator selected");

        }
        num1= result;
        return result;
    }
}