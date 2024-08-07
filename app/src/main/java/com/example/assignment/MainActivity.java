package com.example.assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private Spinner spinner;
    private List<Integer> numbers;
    private Set<Integer> highlightNumbers;
    private NumberAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        spinner = findViewById(R.id.spinner);

        numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numbers.add(i);
        }

        highlightNumbers = new HashSet<>();
        adapter = new NumberAdapter(this,numbers,highlightNumbers);
        gridView.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String rule = (String) parent.getItemAtPosition(position);
                highlightNumbers(rule);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void highlightNumbers(String rule) {
        highlightNumbers.clear();
        switch (rule){
            case "Odd Numbers":
                for (int number : numbers){
                    if (number % 2 != 0){
                        highlightNumbers.add(number);
                    }
                }
                break;

            case "Even Numbers":
                for (int number : numbers){
                    if (number % 2 == 0){
                        highlightNumbers.add(number);
                    }
                }
                break;

            case "Prime Numbers":
                for (int number : numbers){
                    if (isPrime(number)){
                        highlightNumbers.add(number);
                    }
                }
                break;

            case "Fibonacci Sequence":
                Set<Integer> fibonacciNumbers = generateFibonacciNuber(100);
                highlightNumbers.addAll(fibonacciNumbers);
                break;
        }

        adapter.notifyDataSetChanged();
    }

    // input : 10
    //output: (0,1,1,2,3,5,8,13)...
    private Set<Integer> generateFibonacciNuber(int i) {
        Set<Integer> fibonacciNumbers = new HashSet<>();
        int a = 0, b = 1;
        while (a <= i){
            fibonacciNumbers.add(a);
            int next = a;
            a = b;
            b = next + b;
        }
        return fibonacciNumbers;
    }

    //input: 5
    //output: 0,1,2,3,5,7...
    private boolean isPrime(int number) {
        if (number <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(number); i++){
        if (number % i == 0)
            return false;

    }
        return true;

    }


}