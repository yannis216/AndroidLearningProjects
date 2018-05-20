package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /** Hier werden die Preise definiert */
    int numberOfCoffees = 1;
    int priceOfCoffee = 3;
    int priceOfAddedWhipped = 1;
    int priceOfAddedChocolate = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /** This method is called when the increment Button is clicked */
    public void increment(View view) {
        if (numberOfCoffees<100) {
            numberOfCoffees = numberOfCoffees + 1;
        }
        else {
            Toast.makeText(getApplicationContext(), "Sie können nicht mehr als 100 Kaffee bestellen", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(numberOfCoffees);
    }

    /** This method is called when the decrement Button is clicked */
    public void decrement(View view) {
        if (numberOfCoffees>1){
            numberOfCoffees = numberOfCoffees - 1;
        }
        else{
            Toast.makeText(getApplicationContext(), "Sie können nicht weniger als einen Kaffee bestellen", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(numberOfCoffees);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText userNameEdit = (EditText) findViewById(R.id.name_input);
        String userName = userNameEdit.getText().toString();
        int finalPrice = calculatePrice(numberOfCoffees, priceOfCoffee, hasChocolate, priceOfAddedChocolate, hasWhippedCream, priceOfAddedWhipped);
        String message = createOrderSummary(finalPrice, hasWhippedCream, hasChocolate, userName);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Ihre Kaffee-Bestellung");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method calculates the price for the Coffes
     * @param price is the price of the coffees
     * @param quantity is the number of coffees ordered
     */
    private int calculatePrice(int quantity, int price, boolean hasChocolate, int priceOfAddedChocolate, boolean hasWhipped, int priceOfAddedWhipped){
        if (hasChocolate){
            price += priceOfAddedChocolate;
        }
        if (hasWhipped){
            price+= priceOfAddedWhipped;
        }
        int finalPrice= price*quantity;
        return finalPrice;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number2) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number2);
    }

    /**
     * This method displays the given text on the screen.
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String userName) {
        String message= "Vielen Dank " + userName + ", Ihre Bestellung wurde aufgenommen.";
        message += "\nIngesamt haben Sie "+ numberOfCoffees + " Kaffee";
        if (hasWhippedCream) {
            message += " mit Whipped Cream";
        }
        if (hasChocolate && hasWhippedCream){
            message += " und";
        }
        if (hasChocolate)
        {
            message += " mit Schokolade";
        }
        message += " bestellt.";
        message += "\nDer Preis für Ihre Bestellung ist " + price +" Euro.";
        return message;
    }

}
