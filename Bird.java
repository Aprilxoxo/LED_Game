package de.thdeg.game;

public class Bird {
    private short yPos;
    private short xPos;

    Bird()
    {
        this.yPos = 12;
        this.xPos = 20;
    }

    public void updateHeight(short val)
    {
        if(this.yPos == 0 && val == -1 || this.yPos == 23 && val == 1) return;

        this.yPos += val;
    }

    public void draw(short[] screen)
    {
        screen[(this.yPos * 48 + this.xPos) * 3 + 0] = 255;
        screen[(this.yPos * 48 + this.xPos) * 3 + 1] = 255;
        screen[(this.yPos * 48 + this.xPos) * 3 + 2] = 0;
    }

    public short GetHeight()
    {
        return this.yPos;
    }
}
