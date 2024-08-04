package com.example.alculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private CalculatorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.textView);
        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        viewModel.getDisplayView().observe(this, result -> resultTextView.setText(result));

        setupButtons();

        resultTextView.setText(viewModel.getResult());
    }

    private void setupButtons() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2,
                R.id.button3, R.id.button4, R.id.button5,
                R.id.button6, R.id.button7, R.id.button8,
                R.id.button9
        };

        for (int i = 0; i < buttonIds.length; i++) {
            final int digit = i; // необходимо использовать final для доступа в лямбда-выражению
            Button button = findViewById(buttonIds[i]);
            button.setOnClickListener(v -> viewModel.appendToInput(String.valueOf(digit)));
        }

        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonCh = findViewById(R.id.buttonCh);
        Button buttonR = findViewById(R.id.buttonR);
        Button buttonMultiplate = findViewById(R.id.buttonMultiplate);

        buttonPlus.setOnClickListener(v -> viewModel.setOperator("+"));
        buttonMinus.setOnClickListener(v -> viewModel.setOperator("-"));
        buttonMultiplate.setOnClickListener(v -> viewModel.setOperator("*"));
        buttonCh.setOnClickListener(v -> viewModel.setOperator("/"));
        buttonR.setOnClickListener(v -> viewModel.calculateResult());
        buttonC.setOnClickListener(v -> viewModel.clearInput());
    }
}