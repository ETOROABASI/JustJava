package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import static android.R.id.message;


// this app displays an order form to order coffee
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int numberOfCoffees;


    //this method displays the given qty value on the screen

    private void display(int number){

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }

    private void displayPrice(int number){

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void increment(View view){
        if(numberOfCoffees == 10){
            return;
        }

        numberOfCoffees+=1;
        display(numberOfCoffees);

    }

    public void decrement(View view){
        if (numberOfCoffees == 0){
            return;
        }


        numberOfCoffees -= 1;

        display(numberOfCoffees);



    }

    // this method is called when the button is clicked

    public void submitOrder(View view){

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        Log.v("MainActivity", "Has whipped cream is "+ hasWhippedCream);

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("this", "Name: "+ name);


        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();
        Log.v("MainActivity", "To get Chocolate is "+ hasChocolate);


        int price = calculatePrice(hasChocolate, hasWhippedCream);

       // Log.v("MainActivity", "The Price is "+ price);  //this is to display this line in the logcat. just used to ensure it is working
                                                        // not necessary afterwards

       // displayMessage(createOrderSummary(price));

        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
      displayMessage(priceMessage);
        //displayPrice(price);
    }

    private void displayMessage(String message){

        TextView textView = (TextView) findViewById(R.id.price_text_view);
        textView.setText(message);
    }


    /**
    * @param addWhippedCream wheather the user needs whipped cream
     * @param addChocolate whether the user needs chocolate
    * @return total price

     */
    private int calculatePrice(boolean addChocolate, boolean addWhippedCream){
        int basePrice = 5;

        if(addChocolate){
            basePrice+=2;

        }

        if(addWhippedCream){
            basePrice+=1;
        }

        return numberOfCoffees * basePrice;
    }


    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name){
        // price = calculatePrice(hasChocolate, hasWhippedCream);
        String priceMessage = "Name: "+ name;
        priceMessage += "\n Has Whipped Cream? "+ hasWhippedCream;
        priceMessage += "\n Has Chocolate? "+ hasChocolate;

        priceMessage += "\n Quantity: "+ numberOfCoffees+ "\n Price: " + price +".00";
        priceMessage += "\nThank you";
        return priceMessage;


    }



}
