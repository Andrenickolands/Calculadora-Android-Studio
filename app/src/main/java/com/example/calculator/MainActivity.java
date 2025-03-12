package com.example.calculator;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tv_Result;
    private String currentInput = "";
    private Double firstNumber = null;
    private Character operator = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_Result = findViewById(R.id.tv_Result);
        setButtonListeners();
    }

    private void setButtonListeners(){
        int[] numberBtns = {
                R.id.btn_zero, R.id.btn_one, R.id.btn_two, R.id.btn_three,
                R.id.btn_four, R.id.btn_five, R.id.btn_six, R.id.btn_seven,
                R.id.btn_eight, R.id.btn_nine, R.id.btn_point
        };

        for (int id : numberBtns){
            Button btn = findViewById(id);
            btn.setOnClickListener(view -> {
                currentInput += btn.getText().toString();
                tv_Result.setText(currentInput);
            });
        }

        int [][] operationBtns = {
                {R.id.btnAdd, '+'},
                {R.id.btnSubstract, '-'},
                {R.id.btnMultiply, '×'},
                {R.id.btnDivide, '÷'},
                {R.id.btnPercent, '%'}
        };

        for (int [] op : operationBtns){
            Button btn = findViewById(op[0]);
            char operation = (char) op[1];
            btn.setOnClickListener(view ->{
                if (!currentInput.isEmpty()){
                    firstNumber = Double.parseDouble(currentInput);
                    currentInput="";
                    operator = operation;
                    tv_Result.setText(String.valueOf(operation));
                }
            });
        }

        findViewById(R.id.btnEquals).setOnClickListener(view -> {
            if (firstNumber != null && !currentInput.isEmpty() && operator != null) {
                double secondNumber = Double.parseDouble(currentInput);
                double result;

                switch (operator){
                    case '+':
                        result = firstNumber + secondNumber;
                        break;
                    case '-':
                        result = firstNumber - secondNumber;
                        break;
                    case '×':
                        result = firstNumber*secondNumber;
                        break;
                    case '÷':
                        if (secondNumber != 0){
                            result = firstNumber/secondNumber;
                        }else{
                            tv_Result.setText("Error");
                            return;
                        }
                        break;
                    case '%':
                        result = secondNumber*firstNumber/100;
                        break;
                    default:
                        return;
                }

                // **Mostrar resultado en la pantalla**
                tv_Result.setText(String.valueOf(result));

                // **Restablecer valores**
                firstNumber = result;
                currentInput = "";
                operator = null;
            }
        });

        // btn delete
        findViewById(R.id.btnDelete).setOnClickListener(view -> {
            firstNumber = null;
            operator = null;
            currentInput = "";
            tv_Result.setText("0");
        });
    }
}