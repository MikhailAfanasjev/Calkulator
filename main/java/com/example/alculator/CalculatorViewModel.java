package com.example.alculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";
    private String result = "";

    private MutableLiveData<String> displayView = new MutableLiveData<>();

    public LiveData<String> getDisplayView() {
        return displayView;
    }

    public void appendToInput(String value) {
        currentInput += value;
        displayView.setValue(currentInput);
    }

    public void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            previousInput = currentInput;
            operator = op;
            currentInput = "";
        }
    }

    public void calculateResult() {
        if (!previousInput.isEmpty() && !currentInput.isEmpty() && !operator.isEmpty()) {
            double num1 = Double.parseDouble(previousInput);
            double num2 = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) { // Деление на 0
                        displayView.setValue("Error");
                        return;
                    } else {
                        result = num1 / num2;
                    }
                    break;
            }
            displayView.setValue(String.valueOf(result));

            currentInput = "";
            previousInput = "";
            operator = "";
        }
    }

    public void clearInput() {
        currentInput = "";
        previousInput = "";
        operator = "";
        displayView.setValue("0");
    }

    public String getResult() {
        return result;
    }
}