package com.omeryildizce.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView resultText;
    EditText number1Text;
    EditText number2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creator();
    }

    private void creator() {
        resultText = findViewById(R.id.resultText);
        number1Text = findViewById(R.id.number1Text);
        number2Text = findViewById(R.id.number2Text);

    }

    public void sum(View view) {
        if (number1Text.getText().toString().matches("") || number2Text.getText().toString().matches("")) {
            resultText.setText(getResources().getString(R.string.enter_number));
        } else {

            int number1 = Integer.parseInt(number1Text.getText().toString());
            int number2 = Integer.parseInt(number2Text.getText().toString());
            int result = number1 + number2;
            String text = getResources().getString(R.string.result) + " " + result;
            resultText.setText(text);
        }
    }

    public void deduct(View view) {
        if (number1Text.getText().toString().matches("") || number2Text.getText().toString().matches("")) {
            resultText.setText(getResources().getString(R.string.enter_number));
        } else {

            int number1 = Integer.parseInt(number1Text.getText().toString());
            int number2 = Integer.parseInt(number2Text.getText().toString());
            int result = number1 - number2;
            String text = getResources().getString(R.string.result) + " " + result;
            resultText.setText(text);
        }
    }

    public void multiply(View view) {
        if (number1Text.getText().toString().matches("") || number2Text.getText().toString().matches("")) {
            resultText.setText(getResources().getString(R.string.enter_number));
        } else {

            int number1 = Integer.parseInt(number1Text.getText().toString());
            int number2 = Integer.parseInt(number2Text.getText().toString());
            int result = number1 * number2;
            String text = getResources().getString(R.string.result) + " " + result;
            resultText.setText(text);
        }
    }

    public void divide(View view) {
        if (number1Text.getText().toString().matches("") || number2Text.getText().toString().matches("")) {
            resultText.setText(getResources().getString(R.string.enter_number));
        } else {

            int number1 = Integer.parseInt(number1Text.getText().toString());
            int number2 = Integer.parseInt(number2Text.getText().toString());
            int result = number1 / number2;
            String text = getResources().getString(R.string.result) + " " + result;
            resultText.setText(text);
        }
    }

    public void mod(View view) {
        if (number1Text.getText().toString().matches("") || number2Text.getText().toString().matches("")) {
            resultText.setText(getResources().getString(R.string.enter_number));
        } else {

            int number1 = Integer.parseInt(number1Text.getText().toString());
            int number2 = Integer.parseInt(number2Text.getText().toString());
            int result = number1 % number2;
            String text = getResources().getString(R.string.result) + " " + result;
            resultText.setText(text);
        }
    }
}