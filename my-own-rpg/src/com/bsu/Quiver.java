package com.bsu;

public class Quiver extends Weapon{
    private int countArrows;
    public Quiver(int weight, int damage, int range, int countArrows) {
        super(weight, damage, range);
        this.countArrows = countArrows;
    }

    public int getCountArrows() {
        return countArrows;
    }

    public void setCountArrows(int countArrows) {
        this.countArrows = countArrows;
    }
}
