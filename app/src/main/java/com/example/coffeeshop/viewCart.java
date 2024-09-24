package com.example.coffeeshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class viewCart extends AppCompatActivity {
    private RadioButton paypal;
    private RadioButton gcash;
    private RadioButton paymaya;
    private RadioButton cashondelivery;
    private TextView numOfOrders;
    private TextView totalAmount;
    private String selectedPaymentOption;
    private Button payButton;
    orderHandle orderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_cart);

        paypal = findViewById(R.id.paypal);
        gcash = findViewById(R.id.gcash);
        paymaya = findViewById(R.id.paymaya);
        cashondelivery = findViewById(R.id.cashondelivery);
        numOfOrders = findViewById(R.id.numOfOrders);
        totalAmount = findViewById(R.id.totalAmount);
        orderManager = orderHandle.getInstance();
        payButton = findViewById(R.id.payBtn);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        payButton.setOnClickListener(view -> {
            showTransactionDialog();
        });

        setPaymentOption();
        orderSummary();
    }
    public void setPaymentOption() {
        paypal.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                gcash.setChecked(false);
                paymaya.setChecked(false);
                cashondelivery.setChecked(false);
            }
        });
        gcash.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                cashondelivery.setChecked(false);
                paymaya.setChecked(false);
                paypal.setChecked(false);
            }
        });
        paymaya.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                gcash.setChecked(false);
                cashondelivery.setChecked(false);
                paypal.setChecked(false);
            }
        });
        cashondelivery.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                gcash.setChecked(false);
                paymaya.setChecked(false);
                paypal.setChecked(false);
            }
        });
    }
    public void orderSummary() {
    double amountOfOrder = orderManager.getOrderCount();
    double amount = orderManager.getOrderCalculate();

    numOfOrders.setText(String.valueOf(amountOfOrder));
    totalAmount.setText(String.valueOf(amount));
    }
    private void showTransactionDialog() {
        selectedPaymentOption = getSelectedPaymentOption();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Transaction Successful");

        String message = "Total Amount: Php " + orderManager.getOrderCalculate() + "\n" +
                "Total Items: " + orderManager.getOrderCount() + "\n" +
                "Payment Option: " + selectedPaymentOption;

        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                orderManager.resetOrderCount();
                orderManager.clearOrders();
                orderManager.clearOrderTotal();
                orderManager.resetIconState();
                Intent intent = new Intent(viewCart.this, orderPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    } public String getSelectedPaymentOption() {
        if(paypal.isChecked()) {
            return "Paypal";
        } else if (gcash.isChecked()) {
            return "Gcash";
        } else if(paymaya.isChecked()) {
            return "Paymaya";
        } else if (cashondelivery.isChecked()) {
            return "Cash on Delivery";
        } else {
            return "Unknown";
        }
    }

}