package menu;

import animation.Collection;
import animation.Menu;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuAnimation<T> class implements Menu<T>.
 * @param <T> generic
 */
public class MenuAnimation<T> implements Menu<T> {
    //members
    private List<Collection> collectionList;
    private boolean stop = false;
    private T status;
    private Collection collection;
    private boolean isAlreadyPressed = true;
    private KeyboardSensor sensor;
    private List<Collection<Menu<T>>> subMenu;

    static final int WIDTH_SCREEN = 800;
    static final int HI_SCREEN = 600;


    /**
     * @param ks Keyboard Sensor
     */
    public MenuAnimation(KeyboardSensor ks) {
        this.collectionList = new ArrayList<>();
        this.status = null;
        this.sensor = ks;
        this.subMenu = new ArrayList<>();
    }


    @Override
    public void addSelection(String key, String message, T returnVal) {
        collectionList.add(new Collection(key, message, returnVal));
    }

    @Override
    public T getStatus() {
        return this.status;
    }


    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        drawBackground(d);
        d.setColor(Color.GREEN);
        d.drawText(d.getWidth() / 2 - 50, 50, "MENU", 32);
        int w = 100;
        for (int i = 0; i < collectionList.size(); i++) {
            d.setColor(Color.RED);
            String toPrint = collectionList.get(i).getMessage();
            d.drawText(100, w * (i + 1), toPrint, 26);
            d.setColor(Color.BLUE);
            d.drawText(500, w * (i + 1), "PRESS " + collectionList.get(i).getKey(), 26);
        }
        for (int i = 0; i < collectionList.size(); i++) {
            if (this.sensor.isPressed(this.collectionList.get(i).getKey())) {
                this.status = (T) this.collectionList.get(i).getStatus();
                this.stop = true;
                this.isAlreadyPressed = true;
                return;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * initialize the Status.
     */
    public void initializeStatus() {
        this.stop = false;
        this.status = null;
    }

    @Override
    public void addSubMenu(String key1, String message1, Menu<T> subMenu1) {
        this.subMenu.add(new Collection<Menu<T>>(key1, message1, subMenu1));

    }

    /**
     * @return the sub menu
     */
    public List<Collection<Menu<T>>> getSubMenu() {
        return subMenu;
    }

    /**
     * @param d DrawSurface
     */
    public void drawBackground(DrawSurface d) {
        d.setColor(Color.PINK);
        d.fillRectangle(0, 0, WIDTH_SCREEN, HI_SCREEN);
        d.setColor(Color.BLUE);
        d.fillCircle(0, 500, 200);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(350, 500, 100);
    }
}
