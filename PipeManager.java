package de.thdeg.game;
import java.util.LinkedList;

//class to manage pipes
public class PipeManager {
    private LinkedList<Pipe> pipes;
    private final int PIPES_ON_SCREEN = 8;
    private final int MAX_FPS = 10;
    private int realFrame = 0;
    private int lastAddFrame;
    private int fps = 2;

    PipeManager()
    {
        this.lastAddFrame = 0;
        this.pipes = new LinkedList<Pipe>();
    }

    private void draw(short[] screen)
    {
        for(int i = 0; i < pipes.size(); i++)
        {
            Pipe pipe = pipes.get(i);
            if(pipe.isValid())
            {
                pipe.draw(screen);
            }
        }
    }

    //triggers on every frame
    public void update(short[] screen, int frame)
    {
        //always draw to avoid flickering
        this.draw(screen);

        //limits the game speed
        if(frame % (MAX_FPS / fps) != 0)
        {
            return;
        }

        this.realFrame++;

        //if we dont have enough pipes yet and there is enough distance to the last pipe we add a new one
        if(this.pipes.size() < this.PIPES_ON_SCREEN && this.realFrame - this.lastAddFrame >= 6)
        {
            this.lastAddFrame = this.realFrame;
            pipes.add(new Pipe(this.fps));
        }

        //update every pipe's position or replace with a new one if needed
        for(int i = 0; i < pipes.size(); i++)
        {
            Pipe pipe = pipes.get(i);
            if(pipe.isValid())
            {
                pipe.onUpdate();
            }
            else
            {
                pipes.set(i, new Pipe(this.fps));
            }
        }
    }

    //checks if the player collides with a pipe
    public boolean checkCollision(Bird player, ScoreManager scoreHandler)
    {
        for(Pipe pipe : pipes)
        {
            if(pipe.isValid() && pipe.checkCollision(player, scoreHandler))
            {
                return true;
            }
        }
        
        return false;
    }

    //sets how often the pipes get updated based on the current score
    public void setSpeed(ScoreManager scoreHandler)
    {
        if(this.fps == 10) return;
        this.fps = scoreHandler.getScore() / 5 + 2;
    }
}
