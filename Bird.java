package de.thdeg.game;

public class Bird {
   private Vector2 position;

    Bird()
    {
        this.position = new Vector2(20, 12);
    }

    public void updateHeight(short value)
    {
        if(this.position.getY() == 0 && value == -1 || this.position.getY() == 23 && value == 1) return;

        this.position.updateY(value);
    }

    public void draw(short[] screen)
    {
        screen[(this.position.getY() * 48 + this.position.getX()) * 3 + 0] = 255;
        screen[(this.position.getY() * 48 + this.position.getX()) * 3 + 1] = 255;
        screen[(this.position.getY() * 48 + this.position.getX()) * 3 + 2] = 0;
    }

    public short getHeight()
    {
        return this.position.getY();
    }

    public Vector2 getPosition()
    {
        return this.position;
    }
}
