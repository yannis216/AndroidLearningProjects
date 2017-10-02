package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 1;
    int priceOfCoffee = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /** This method is called when the increment Button is clicked */
    public void increment(View view) {
        numberOfCoffees = numberOfCoffees + 1;
        displayQuantity(numberOfCoffees);
    }

    /** This method is called when the decrement Button is clicked */
    public void decrement(View view) {
        numberOfCoffees = numberOfCoffees - 1;
        displayQuantity(numberOfCoffees);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int finalPrice = calculatePrice(numberOfCoffees, priceOfCoffee);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number2) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number2);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    /**
     * This method calculates the price for the Coffes
     * @param price is the price of the coffees
     * @param quantity is the number of coffees ordered
     */
    private int calculatePrice(int quantity, int price){
        int finalPrice= price*quantity;
        return finalPrice;
    }
}
