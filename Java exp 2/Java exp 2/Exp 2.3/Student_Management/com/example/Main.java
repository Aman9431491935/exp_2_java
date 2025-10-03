package com.example;

public class Main {
    public static void main(String[] args) {
        MathOps math = new MathOps();
        int x = 5, y = 3;

        System.out.println("Add: " + x + " + " + y + " = " + math.add(x, y));
        System.out.println("Multiply: " + x + " * " + y + " = " + math.multiply(x, y));
    }
}
