package ch.ictbz.waldbrandoop.waldbrandoop;

public class Tree extends ForestComponent {
    public boolean isBurning;
    public boolean isAsh;

    public Tree() {
        isBurning = false;
        // component = "/Users/jonaskaufmann/Desktop/BLJ/OOP/waldbrandOOP/src/main/resources/ch/ictbz/waldbrandoop/waldbrandoop/tree.png";
        component = "./images/tree.png";
        isAsh = false;
    }

    public void sparkFire() {
        isBurning = true;
        // component = "/Users/jonaskaufmann/Desktop/BLJ/OOP/waldbrandOOP/src/main/resources/ch/ictbz/waldbrandoop/waldbrandoop/fire.png";
        component = "./images/fire.png";
    }

    public void turnToAsh() {
        isAsh = true;
        isBurning = false;
        // component = "/Users/jonaskaufmann/Desktop/BLJ/OOP/waldbrandOOP/src/main/resources/ch/ictbz/waldbrandoop/waldbrandoop/ash.jpg";
        component = "./images/ash.png";
    }
}
