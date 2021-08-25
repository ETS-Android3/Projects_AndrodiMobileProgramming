 package com.example.a04_calcolatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {

    TextView tvinput, tvoutput, tvmemory;
    float memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvinput = (TextView) findViewById(R.id.tv_input);
        tvoutput = (TextView) findViewById(R.id.tv_output);
        tvmemory = (TextView) findViewById(R.id.tv_memory);

        tvmemory.setText("Memory: " + memory);

    }

    public void buttonPressed(View v) {
        Button pressed = (Button) v;
        switch (pressed.getId()) {
            case R.id.buttonC:
                tvinput.setText("");
                break;

            case R.id.buttonMC:
                memory = 0;
                tvmemory.setText("Memory: " + memory);
                break;

            case R.id.buttonMS:
                if (!tvoutput.getText().toString().equals("")) {
                    memory = Float.parseFloat(tvoutput.getText().toString());
                }
                else {
                    memory = 0;
                }
                tvmemory.setText("Memory: " + memory);
                break;

            case R.id.buttonMR:
                tvinput.setText(tvinput.getText().toString().concat(memory + ""));
                break;

            case R.id.buttonResult:
                try {
                    tvoutput.setText(eval(tvinput.getText().toString()) + "");
                } catch (Exception e) {
                    tvoutput.setText(0.0 + "");
                }
                tvinput.setText("");
                break;

            default:
                tvinput.setText(tvinput.getText().toString().concat(pressed.getText().toString()));
                break;
        }

    }

     public static double eval(final String str) {
         return new Object() {
             int pos = -1, ch;

             void nextChar() {
                 ch = (++pos < str.length()) ? str.charAt(pos) : -1;
             }

             boolean eat(int charToEat) {
                 while (ch == ' ') nextChar();
                 if (ch == charToEat) {
                     nextChar();
                     return true;
                 }
                 return false;
             }

             double parse() {
                 nextChar();
                 double x = parseExpression();
                 if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                 return x;
             }

             // Grammar:
             // expression = term | expression `+` term | expression `-` term
             // term = factor | term `*` factor | term `/` factor
             // factor = `+` factor | `-` factor | `(` expression `)`
             //        | number | functionName factor | factor `^` factor

             double parseExpression() {
                 double x = parseTerm();
                 for (;;) {
                     if      (eat('+')) x += parseTerm(); // addition
                     else if (eat('-')) x -= parseTerm(); // subtraction
                     else return x;
                 }
             }

             double parseTerm() {
                 double x = parseFactor();
                 for (;;) {
                     if      (eat('*')) x *= parseFactor(); // multiplication
                     else if (eat('/')) x /= parseFactor(); // division
                     else return x;
                 }
             }

             double parseFactor() {
                 if (eat('+')) return parseFactor(); // unary plus
                 if (eat('-')) return -parseFactor(); // unary minus

                 double x;
                 int startPos = this.pos;
                 if (eat('(')) { // parentheses
                     x = parseExpression();
                     eat(')');
                 } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                     while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                     x = Double.parseDouble(str.substring(startPos, this.pos));
                 } else if (ch >= 'a' && ch <= 'z') { // functions
                     while (ch >= 'a' && ch <= 'z') nextChar();
                     String func = str.substring(startPos, this.pos);
                     x = parseFactor();
                     if (func.equals("sqrt")) x = Math.sqrt(x);
                     else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                     else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                     else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                     else throw new RuntimeException("Unknown function: " + func);
                 } else {
                     throw new RuntimeException("Unexpected: " + (char)ch);
                 }

                 if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                 return x;
             }
         }.parse();
     }
}