package de.thdeg.game;

public class Pipe {
    private Vector2 position;
    private boolean passed = false;

    //TODO make this doable at high speed
    Pipe()
    {
        this.position = new Vector2(47, 8 + (int)(Math.random() * ((16 - 8) + 1)));
    }

    public void onUpdate()
    {
        this.position.updateX((short)-1);
    }

    public void draw(short[] screen)
    {
        if(this.position.getX() < 0) return;

        for(int i = 0; i < 24; i++)
        {
            if(Math.abs(i - this.position.getY()) <= 1)
            {
                continue;
            }

            screen[(i * 48 + this.position.getX()) * 3 + 0] = 0;
            screen[(i * 48 + this.position.getX()) * 3 + 1] = 255;
            screen[(i * 48 + this.position.getX()) * 3 + 2] = 0;
        }
    }

    public boolean isValid()
    {
        return this.position.getX() >= 0;
    }

    public boolean checkCollision(Bird player, ScoreManager scoreHandler)
    {
        if(this.position.getX() != player.getPosition().getX())
        {
            return false;
        }

        if(Math.abs(player.getPosition().getY() - this.position.getY()) <= 1 && !this.passed)
        {
            this.passed = true;
            scoreHandler.onPassPipe();
        }

        return Math.abs(player.getPosition().getY() - this.position.getY()) > 1;
    }
}
