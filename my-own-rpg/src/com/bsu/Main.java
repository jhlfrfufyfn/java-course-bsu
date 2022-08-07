package com.bsu;

public class Main {

    public static void main(String[] args) {
        Bag<Weapon> weaponBag = new Bag<>();
        weaponBag.addItem(new Sword(5,10, 5));
        weaponBag.addItem(new Sword(3, 12, 3));
        weaponBag.addItem(new Quiver(2, 1, 30,20));
        weaponBag.addItem(new Spear(6, 7, 10));

        Bag<Food> foodBag = new Bag<>();
        foodBag.addItem(new Apple(2,1));
        foodBag.addItem(new Bread(10,10));
        foodBag.addItem(new Apple(10,5));

        int compared = weaponBag.compareTo(foodBag);
        String conclusion = "";

        if(compared > 0){
            conclusion = "weaponBag is heavier than foodBag";
        }
        else if(compared < 0){
            conclusion = "weaponBag is lighter than foodBag";
        }
        else{
            conclusion = "weaponBag is the same weight as foodBag";
        }

        System.out.println(conclusion);
    }
}
