package game;


import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection class.
 */
public class SpriteCollection {
    //member
    private List<Sprite> spriteList = new ArrayList<Sprite>();

    /**
     * add the Sprite to the list.
     *
     * @param s sprite to add to the list
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * returns the sprite list.
     *
     * @return the sprite list.
     */
    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed(double dt) {
        //if there are sprites in the list
        if (this.spriteList.size() > 0) {
            for (int i = 0; i < this.spriteList.size(); i++) {
                this.spriteList.get(i).timePassed(dt);
            }
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d a DrawSurface to draw with
     */
    public void drawAllOn(DrawSurface d) {
        if (this.spriteList.size() > 0) {
            for (int i = 0; i < this.spriteList.size(); i++) {
                this.spriteList.get(i).drawOn(d);
            }
        }
    }

    /**
     * @param s the sprite to remove from the list
     */
    public void removeFromSpriteList(Sprite s) {
        if (this.spriteList != null) {
            if (spriteList.contains(s)) {
                spriteList.remove(s);
            }
        }
    }
}
