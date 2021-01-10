package de.thdeg.game;

//class to track score and highscore
public class ScoreManager {
    private int highscore;
    private int score;
    private NumberRenderer renderer;

    ScoreManager()
    {
        this.highscore = 0;
        this.score = 0;
        this.renderer = new NumberRenderer();
    }

    public void incScore()
    {
        this.score++;
    }

    public void printScore()
    {
        System.out.println(String.format("current score is %d", this.score));
    }

    public void printHighScore()
    {
        System.out.println(String.format("new highscore: %d", this.highscore));
    }

    public void resetScore()
    {
        this.score = 0;
    }

    public void updateHighScore()
    {
        this.highscore = this.score;
    }

    //triggers when the player dies
    public void onGameOver()
    {
        if(this.score > this.highscore)
        {
            this.updateHighScore();
            this.printHighScore();
        }
        this.resetScore();
    }

    //triggers when the player gets through a set of pipes
    public void onPassPipe()
    {
        this.incScore();
        this.printScore();
    }

    public int getScore()
    {
        return this.score;
    }

    //displays the current score as a seven segment number display
    public void displayScore(short[] screen)
    {
        renderer.renderNumberTopRight(this.score, screen, 0);
    }

    //displays the current highscore as a seven segment number display
    public void displayHighScore(short[] screen)
    {
        if(this.highscore == 0) return;

        renderer.renderNumberTopLeft(this.highscore, screen, 0);
    }
}
