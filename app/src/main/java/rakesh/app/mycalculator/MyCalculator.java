package rakesh.app.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class MyCalculator extends AppCompatActivity {
    public EditText display;
    public TextView expression;
    public  TextView calculatedResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calculator);

        display = findViewById(R.id.input);
        expression = findViewById((R.id.preCalculation));
        calculatedResult = findViewById(R.id.calculatedResult);
        display.setShowSoftInputOnFocus(false);



        display.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{

                  if(!display.getText().toString().isEmpty()){
                      expression.setText("");
                      display.setTextColor(Color.BLACK);
                      display.setTextSize(32.0f);
                      calculatedResult.setTextColor(Color.GRAY);
                      calculatedResult.setTextSize(28.0f);
                      String userExp = display.getText().toString();
                      Expression exp = new Expression(userExp);
                      double result = exp.calculate();


                      if(!Double.isNaN(result)){
                          if(result % 1 == 0){
                              calculatedResult.setText("= " + (int) result);
                          }else{
                              calculatedResult.setText("= " + result);
                          }
                      }

                  }
                }catch(Exception e){
System.out.println("error : " +e);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
        calculatedResult.setTextColor(Color.BLACK);
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

        if(display.getText().toString().isEmpty()){
            return;
        }else {

            display.setTextSize(28.0f);
            display.setTextColor(Color.GRAY);
            calculatedResult.setTextSize(35.0f);
            calculatedResult.setTextColor(Color.BLACK);
            expression.setText(display.getText().toString());
            display.setText("");


        }


    }
}