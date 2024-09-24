package com.example.coffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class pastriesSelectionPage extends AppCompatActivity {
    int[] iconsId = {R.id.add_icon, R.id.add_icon2, R.id.add_icon3, R.id.add_icon4, R.id.add_icon5};
    boolean[] isAddIcon = {true, true, true, true, true};
    double[] itemPrices = {125, 145, 210, 270, 350};
    TextView orderCounter;
    orderHandle orderManager;
    private Button orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pastries_selection_page);

        orderCounter = findViewById(R.id.orderCounter);
        orderManager = orderHandle.getInstance();
        orders = findViewById(R.id.orders);

        // naka set na agad na visible yung order count nya
        updateOrderCounter(orderManager.getOrderCount());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addToCartIconToggle();
        goToCartPage();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Ensure the orderCounter visibility and icon states are updated
        updateOrderCounter(orderManager.getOrderCount());
        for (int i = 0; i < iconsId.length; i++) {
            ImageView addIcon = findViewById(iconsId[i]);
            isAddIcon[i] = orderManager.getPastriesIconState()[i];
            // Set the correct icon based on isAddIcon state
            if (isAddIcon[i]) {
                addIcon.setImageResource(R.drawable.baseline_add_24);
            } else {
                addIcon.setImageResource(R.drawable.baseline_remove_24);
            }
        }
    }

    public void addToCartIconToggle() {
        for (int i = 0; i < iconsId.length; i++) {
            int index = i;
            ImageView addIcon = findViewById(iconsId[i]);

            double itemPrice = itemPrices[index];

            addIcon.setOnClickListener(view -> {
                if(isAddIcon[index]) {
                    addIcon.setImageResource(R.drawable.baseline_remove_24);
                    orderManager.incrementOrderCount();
                    orderManager.updateOrderTotal(itemPrice);
                    orderManager.pastriesSetIconState(index, false);
                } else {
                    addIcon.setImageResource(R.drawable.baseline_add_24);
                    orderManager.decrementOrderCount();
                    orderManager.updateOrderTotal(-itemPrice);
                    orderManager.pastriesSetIconState(index, true);
                }
                updateOrderCounter(orderManager.getOrderCount());
                isAddIcon[index] = !isAddIcon[index];
            });

        }
    }
    public void updateOrderCounter(int orderCount) {
        if (orderCount == 0) {
            orderCounter.setVisibility(View.GONE);
        } else {
            orderCounter.setVisibility(View.VISIBLE);
            orderCounter.setText(String.valueOf(orderCount));
        }
    }
    public void goToCartPage() {
        orders.setOnClickListener(view -> {
            Intent goToOrderPage = new Intent(pastriesSelectionPage.this, viewCart.class);
            startActivity(goToOrderPage);
        });
    }
}