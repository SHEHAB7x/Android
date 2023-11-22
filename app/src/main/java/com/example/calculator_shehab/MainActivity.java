package com.example.calculator_shehab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calculator_shehab.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onClicks();
    }

    private void onClicks(){
        binding.zero.setOnClickListener(this::onClick);
        binding.one.setOnClickListener(this::onClick);
        binding.two.setOnClickListener(this::onClick);
        binding.three.setOnClickListener(this::onClick);
        binding.four.setOnClickListener(this::onClick);
        binding.five.setOnClickListener(this::onClick);
        binding.six.setOnClickListener(this::onClick);
        binding.seven.setOnClickListener(this::onClick);
        binding.eight.setOnClickListener(this::onClick);
        binding.nine.setOnClickListener(this::onClick);



        binding.dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTwice(binding.dot);
                //twoDots(binding.dot);
            }
        });

        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTwice(binding.plus);
            }
        });
        binding.divider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTwice(binding.divider);
            }
        });
        binding.mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTwice(binding.mod);
            }
        });
        binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTwice(binding.minus);
            }
        });
        binding.multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTwice(binding.multiply);
            }
        });

        binding.ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearLastChar(binding.editTxt);
            }
        });
        binding.ac.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clear(binding.editTxt);
                return false;
            }
        });

        binding.equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String op = " ";
                if(getText().contains("-")){
                    op = "-";
                    String[] equation = equation(op);
                    if(equation.length > 1){
                        result(Double.parseDouble(equation[0]), '-', Double.parseDouble(equation[1]));
                    }
                } else if(getText().contains("/")){
                    op = "/";
                    String[] equation = equation(op);
                    if(equation.length > 1){
                        result(Double.parseDouble(equation[0]), '/', Double.parseDouble(equation[1]));
                    }
                }else if(getText().contains("+")){
                    op = "\\+";
                    String[] equation = equation(op);
                    if(equation.length > 1){
                        result(Double.parseDouble(equation[0]), '+', Double.parseDouble(equation[1]));
                    }
                }else if(getText().contains("%")){
                    op = "%";
                    String[] equation = equation(op);
                    if(equation.length > 1){
                        result(Double.parseDouble(equation[0]), '%', Double.parseDouble(equation[1]));
                    }
                }else if(getText().contains("x")){
                    op = "x";
                    String[] equation = equation(op);
                    if(equation.length > 1){
                        result(Double.parseDouble(equation[0]), 'x', Double.parseDouble(equation[1]));
                    }
                }


            }
        });

    }

    private String[] equation(String s){
        String[] equation = binding.editTxt.getText().toString().split(s);
        return equation;
    }
    private void result(double firstNum,char op, double secNum){
        if(op == '-'){
            binding.editTxt.setText(firstNum-secNum+"");
        }if(op == '/'){
            if(secNum == 0){
                Toast.makeText(this, "Can't divide by zero!", Toast.LENGTH_SHORT).show();
            }
            else{
                binding.editTxt.setText(firstNum/secNum+"");
            }
        }if(op == '+'){
            binding.editTxt.setText(firstNum+secNum+"");
        }if(op == '%'){
            binding.editTxt.setText((firstNum%secNum+""));
        }if(op == 'x'){
            binding.editTxt.setText(firstNum*secNum+"");
        }
    }

    /*private String[] splitEquation(String c){
        String[] equation = binding.editTxt.getText().toString().split(c);
        return equation;
    }*/

   /* private void result(String[] equation){
        double firstNum = Double.parseDouble(equation[0]);
        double secNum = Double.parseDouble(equation[2]);
        String op = equation[1];
        double result = 0;
        if(op == "-"){
            result = firstNum - secNum;
            binding.editTxt.setText(result+"");
        }
    }*/

    private String getText(){
        return binding.editTxt.getText().toString().trim();
    }

    private void twoDots(MaterialButton mBtn){
        if(!binding.editTxt.getText().toString().contains(".")){
            binding.editTxt.append(mBtn.getText().toString());
        }
    }

    private void clearLastChar(EditText editText){
        if(!binding.editTxt.getText().toString().isEmpty()) {
            String s = binding.editTxt.getText().toString();
            int length = s.length() - 1;
            StringBuffer sBufr = new StringBuffer(s);
            sBufr.deleteCharAt(length);
            binding.editTxt.setText(sBufr);
        }
    }

    private void clear(EditText editText){
        editText.setText("");
    }

    private void clickTwice(MaterialButton btn){
        String s = binding.editTxt.getText().toString();
        if(!s.isEmpty()){
            char c = s.charAt(s.length() - 1);
            if((c >= '0' && c <= '9')){
                binding.editTxt.append(btn.getText().toString());
            }
        }
    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        binding.editTxt.append(btn.getText().toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}