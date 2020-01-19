package menu;

import animation.Collection;
import animation.Menu;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * SubMenuAnimation<T> class implements Menu<T>.
 * @param <T> generic
 */
public class SubMenuAnimation<T> implements Menu<T> {
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
     * @param ks KeyboardSensor
     */
    public SubMenuAnimation(KeyboardSensor ks) {
        this.collectionList = new ArrayList<>();
        this.status = null;
        this.sensor = ks;
        this.subMenu = new ArrayList<>();
    }

    /**
     * @return the list.
     */
    public List<Collection> getCollectionList() {
        return collectionList;
    }

    /**
     * @param collectionList1 the new list
     */
    public void setCollectionList(List<Collection> collectionList1) {
        this.collectionList = collectionList1;
    }

    /**
     * @return if to stop or no
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * @param stop1 the new value of stop
     */
    public void setStop(boolean stop1) {
        this.stop = stop1;
    }

    @Override
    public T getStatus() {
        return status;
    }

    /**
     * @param status1 the new status
     */
    public void setStatus(T status1) {
        this.status = status1;
    }

    /**
     * @return the collection
     */
    public Collection getCollection() {
        return collection;
    }

    /**
     * @return if is Already Pressed
     */
    public boolean isAlreadyPressed() {
        return isAlreadyPressed;
    }

    /**
     * @return the Sub Menu
     */
    public List<Collection<Menu<T>>> getSubMenu() {
        return subMenu;
    }

    /**
     * @param subMenu1 the new Sub Menu
     */
    public void setSubMenu(List<Collection<Menu<T>>> subMenu1) {
        this.subMenu = subMenu1;
    }

    @Override
    public void addSelection(String key1, String message1, T returnVal1) {
        this.collectionList.add(new Collection(key1, message1, returnVal1));
    }

    @Override
    public void addSubMenu(String key1, String message1, Menu<T> subMenu1) {
        return;

    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        drawBackground(d);
        int w = 100;
        for (int i = 0; i < collectionList.size(); i++) {
            d.setColor(Color.BLUE);
            String toPrint = collectionList.get(i).getMessage();
            d.drawText(100, w * (i + 1), toPrint, 26);
            d.setColor(Color.RED);
            d.drawText(500, w * (i + 1), "PRESS " + collectionList.get(i).getKey(), 26);
        }
        for (int i = 0; i < collectionList.size(); i++) {
            if (this.sensor.isPressed(this.collectionList.get(i).getKey())) {
                this.stop = true;
                this.status = (T) this.collectionList.get(i).getStatus();
                this.isAlreadyPressed = true;
                return;
            }
        }


    }

    @Override
    public boolean shouldStop() {
        return this.isStop();
    }

    /**
     * initialize the Status.
     */
    public void initializeStatus() {
        this.stop = false;
        this.status = null;
    }

    /**
     * @param d DrawSurface
     */
    private void drawBackground(DrawSurface d) {
        d.setColor(Color.PINK);
        d.fillRectangle(0, 0, WIDTH_SCREEN, HI_SCREEN);
        d.setColor(Color.BLUE);
        d.fillCircle(0, 500, 200);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(350, 500, 100);
        d.setColor(Color.ORANGE);
        d.fillCircle(650, 300, 100);
    }
}
