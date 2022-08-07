package com.bsu;


public class Weapon extends Item {
    private int damage;
    private int range;

    public Weapon(int weight, int damage, int range) {
        super(weight);
        this.damage = damage;
        this.range = range;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
