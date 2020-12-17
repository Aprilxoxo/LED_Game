package de.thdeg.game;

public class Vector2 {
    private short y;
    private short x;

    Vector2(short x, short y)
    {
        this.x = x;
        this.y = y;
    }

    Vector2(int x, int y)
    {
        this.x = (short)x;
        this.y = (short)y;
    }

    public short getX()
    {
        return x;
    }

    public void setX(short x)
    {
        this.x = x;
    }

    public short getY()
    {
        return y;
    }

    public void setY(short y)
    {
        this.y = y;
    }

    public void updateX(short value)
    {
        this.x += value;
    }

    public void updateY(short value)
    {
        this.y += value;
    }
}
