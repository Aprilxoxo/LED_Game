package de.thdeg.game;
import java.util.LinkedList;

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

    public void update(short[] screen, int frame)
    {
        if(frame % (MAX_FPS / fps) != 0)
        {
            //System.out.println(String.format("frame %d is being skipped", frame));
            return;
        }

        this.realFrame++;

        if(this.pipes.size() < this.PIPES_ON_SCREEN && this.realFrame - this.lastAddFrame >= 6)
        {
            this.lastAddFrame = this.realFrame;
            pipes.add(new Pipe());
        }

        for(int i = 0; i < pipes.size(); i++)
        {
            Pipe pipe = pipes.get(i);
            if(pipe.isValid())
            {
                pipe.draw(screen);
            }
            else
            {
                pipes.set(i, new Pipe());
            }
        }
    }

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

    public void setSpeed(ScoreManager scoreHandler)
    {
        if(this.fps == 10) return;
        this.fps = scoreHandler.getScore() / 5 + 2;
    }
}
