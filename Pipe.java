package de.thdeg.game;

//class holding info about a pipe
public class Pipe {
    private Vector2 position;
    private boolean passed = false;

    //constructs a pipe at the right side of the screen and with semi-randomly placed opening
    Pipe(int speed)
    {
        //this.position = new Vector2(47, 8 + (int)(Math.random() * ((16 - 8) + 1)));
        this.position = new Vector2(47, this.generateOpeningPosition(speed));
    }

    //generate the opening's height level according to speed so that the game is playable at higher speeds
    private int generateOpeningPosition(int speed)
    {
        int mod = (speed - 2) / 2;
        int min = 8 + mod;
        int max = 16 - mod;
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    //moves pipe one to the left
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

    //returns true if the pipe is still on screen
    public boolean isValid()
    {
        return this.position.getX() >= 0;
    }

    //returns true if the player is within the opening
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
