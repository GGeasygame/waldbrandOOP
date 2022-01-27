package ch.ictbz.waldbrandoop.waldbrandoop;

public class Tree extends ForestComponent {
    public boolean isBurning;
    public boolean isAsh;

    public Tree() {
        isBurning = false;
        component = "./images/tree.png";
        isAsh = false;
    }

    public void sparkFire() {
        isBurning = true;
        component = "./images/fire.png";
    }

    public void turnToAsh() {
        isAsh = true;
        isBurning = false;
        component = "./images/ash.png";
    }
}
