package com.example.coffeeshop;

import java.util.ArrayList;

public class orderHandle {
    private static orderHandle instance;
    private boolean[] iconStates = {true, true, true, true, true};
    private boolean[] iceCOffeeIconStates = {true, true, true, true, true};
    private boolean[] pastriesIconStates = {true, true, true, true, true};
    private ArrayList<String> hotCoffeeOrder = new ArrayList<>();
    private ArrayList<String> iceCoffeeOrder = new ArrayList<>();
    private ArrayList<String> pastriesOrder = new ArrayList<>();
    private int orderCount = 0;
    private double orderCalculate = 0;

    private orderHandle() {}

    public static synchronized orderHandle getInstance() {
        if (instance == null) {
            instance = new orderHandle();
        }
        return instance;
    }

    public void addHotCoffee(String hotCoffeeOrdered) {
        hotCoffeeOrder.add(hotCoffeeOrdered);
    }
    public void addIceCoffee(String iceCoffeeOrdered) {
        iceCoffeeOrder.add(iceCoffeeOrdered);
    }
    public void pastriesOrder(String pastriesOrdered) {
        pastriesOrder.add(pastriesOrdered);
    }

    public ArrayList<String> getHotCoffeeOrders() {
        return hotCoffeeOrder;
    }
    public ArrayList<String> getIceCoffeeOrders() {
        return iceCoffeeOrder;
    }
    public ArrayList<String> getPastriesOrders() {
        return pastriesOrder;
    }

    public void clearOrders() {
        hotCoffeeOrder.clear();
        iceCoffeeOrder.clear();
        pastriesOrder.clear();
    }
    public void incrementOrderCount() {
        orderCount++;
    }
    public void decrementOrderCount() {
        if(orderCount > 0) {
            orderCount--;
        }
    }
    public int getOrderCount() {
        return orderCount;
    }
    public void resetOrderCount() {
        orderCount = 0;
    }
    public boolean[] getIconStates() {
        return iconStates;
    }
    public void setIconState(int index, boolean state) {
        iconStates[index] = state;
    }
    public boolean[] getIceCoffeeIconState() {
        return iceCOffeeIconStates;
    }
    public void iceCoffeeSetIconState(int index, boolean state) {
        iceCOffeeIconStates[index] = state;
    }
    public boolean[] getPastriesIconState() {
        return pastriesIconStates;
    }
    public void pastriesSetIconState(int index, boolean state) {
        pastriesIconStates[index] = state;
    }
    public void updateOrderTotal(double itemPrice) {
        orderCalculate += itemPrice;
    }
    public double getOrderCalculate() {
        return orderCalculate; // Method to retrieve the total
    }
    public void clearOrderTotal() {orderCalculate = 0;}
    public void resetIconState() {
        for (int i = 0; i < iconStates.length; i++) {
            iconStates[i] = true;
        }
        for (int i = 0; i < iceCOffeeIconStates.length; i++) {
            iceCOffeeIconStates[i] = true;
        }
        for (int i = 0; i < pastriesIconStates.length; i++) {
            pastriesIconStates[i] = true;
        }
    }
}
