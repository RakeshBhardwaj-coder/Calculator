package rakesh.app.mycalculator;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.mariuszgromada.math.mxparser.Expression;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    public EditText display;
    public TextView expression;
    public  TextView calculatedResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        expression = findViewById((R.id.preCalculation));
        calculatedResult = findViewById(R.id.calculatedResult);
        display.setShowSoftInputOnFocus(false);

    }
    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursurPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursurPos);
        String rightStr = oldStr.substring(cursurPos);
        display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
        display.setSelection(cursurPos + 1);

    }
    public void clearBtn(View view){
        display.setText("");
        expression.setText("");
        calculatedResult.setText("");
    }

    public void zeroBtn(View view ){
        updateText("0");
    }

    public void oneBtn(View view ){
        updateText("1");

    }
    public void twoBtn(View view ){
        updateText("2");

    }
    public void threeBtn(View view ){
        updateText("3");

    }
    public void fourBtn(View view ){
        updateText("4");

    }
    public void fiveBtn(View view ){
        updateText("5");

    }
    public void sixBtn(View view ){
        updateText("6");

    }
    public void sevenBtn(View view ){
        updateText("7");

    }
    public void eightBtn(View view ){
        updateText("8");

    }
    public void nineBtn(View view ){
        updateText("9");

    }
    public void dotBtn(View view ){
        updateText(".");
    }
    public void delBtn(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos !=0 && textLen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);}
    }
    public void parentasisBtn(View view){
        int cursurPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for (int i=0; i<cursurPos; i++){
            if(display.getText().toString().substring(i,i+1).equals( "(" )){
                openPar += 1;
            }
            if(display.getText().toString().substring(i,i+1).equals( ")" )){
                closePar += 1;
            }
        }

        if(openPar == closePar || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
        }
        else if(closePar < openPar && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursurPos + 1);

    }
    public void percentageBtn(View view){
        updateText("%");
    }
    public void divBtn(View view){
        updateText("/");
    }
    public void mulBtn(View view){
        updateText("*");
    }
    public void minBtn(View view){
        updateText("-");
    }
    public void plusBtn(View view){
        updateText("+");
    }

    public void equalsBtn(View view){
        String userExp = display.getText().toString();
//    userExp = userExp.replaceAll("%","/");
//    userExp = userExp.replaceAll("","*");

        String preCalcuation = userExp; //this will take previous expression to show.

        Expression exp = new Expression(preCalcuation);
        expression.setText(preCalcuation);

        double result = exp.calculate();
//        DecimalFormat format = new DecimalFormat();
//        format.setDecimalSeparatorAlwaysShown(false);
        if(result % 1 == 0 ){
            calculatedResult.setText("= " + (int) result);
        }else{
            calculatedResult.setText("= " + result);
        }

        display.setText(" ");
//            display.setSelection(String.valueOf(result).length());


    }

}
