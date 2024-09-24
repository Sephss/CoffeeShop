package com.example.coffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class orderPage extends AppCompatActivity {
   private Button hotCoffeeButton;
   private Button iceCoffeeButton;
   private Button pastriesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_page);
        hotCoffeeButton = findViewById(R.id.hotCoffeeBtn);
        iceCoffeeButton = findViewById(R.id.iceCoffeeButton);
        pastriesButton = findViewById(R.id.pastriesButton);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
         goToHotCoffeeSelection();
         goToIceCoffeeSelection();
         goToPastriesSelection();
    }
    public void goToHotCoffeeSelection() {
        hotCoffeeButton.setOnClickListener(view -> {
            Intent hotCoffeePage = new Intent(orderPage.this, hotCoffeeSelectionPage.class);
            startActivity(hotCoffeePage);
        });
    }
    public void goToIceCoffeeSelection() {
        iceCoffeeButton.setOnClickListener(view -> {
            Intent iceCoffeePage = new Intent(orderPage.this, iceCoffeeSelectionPage.class);
            startActivity(iceCoffeePage);
        });
    }
    public void goToPastriesSelection() {
        pastriesButton.setOnClickListener(view -> {
            Intent pastriesPage = new Intent(orderPage.this, pastriesSelectionPage.class);
            startActivity(pastriesPage);
        });
    }
}