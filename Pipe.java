package de.thdeg.game;

public class Pipe {
    private short yPos;
    private short xPos;
    private short xPosPrev;
    private final short PLAYER_X = 20;
    private boolean passed = false;

    //TODO make this doable at high speed
    Pipe()
    {
        this.yPos = (short)(8 + (int)(Math.random() * ((16 - 8) + 1)));
        this.xPos = 47;
        this.xPosPrev = 47;
    }

    public void draw(short[] screen)
    {
        this.xPosPrev = this.xPos;
        this.xPos--;

        for(int i = 0; i < 24; i++)
        {
            screen[(i * 48 + this.xPosPrev) * 3 + 0] = 137;
            screen[(i * 48 + this.xPosPrev) * 3 + 1] = 207;
            screen[(i * 48 + this.xPosPrev) * 3 + 2] = 240;
        }

        if(this.xPos < 0) return;

        for(int i = 0; i < 24; i++)
        {
            if(Math.abs(i - this.yPos) <= 1)
            {
                screen[(i * 48 + this.xPos) * 3 + 0] = 137;
                screen[(i * 48 + this.xPos) * 3 + 1] = 207;
                screen[(i * 48 + this.xPos) * 3 + 2] = 240;
                continue;
            }

            screen[(i * 48 + this.xPos) * 3 + 0] = 0;
            screen[(i * 48 + this.xPos) * 3 + 1] = 255;
            screen[(i * 48 + this.xPos) * 3 + 2] = 0;
        }
    }

    public boolean isValid()
    {
        return this.xPos >= 0;
    }

    public boolean checkCollision(Bird player, ScoreManager scoreHandler)
    {
        if(this.xPos != PLAYER_X)
        {
            return false;
        }

        if(Math.abs(player.GetHeight() - this.yPos) <= 1 && !this.passed)
        {
            this.passed = true;
            scoreHandler.onPassPipe();
        }

        return Math.abs(player.GetHeight() - this.yPos) > 1;
    }
}
