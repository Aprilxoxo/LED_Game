package de.thdeg.game;

import de.thdeg.game.runtime.InternalLedGameThread;

//TODO cleanup project
//TODO add ascii render engine
public class GameMain {

    static public void ResetScreen(short[] myImage)
    {
        for(int i=0; i<myImage.length; i+=3){
            myImage[i+0]=(short)137;
            myImage[i+1]=(short)207;
            myImage[i+2]=(short)240;
        }
    }

    static public void setPixel(short[] screen,int x, int y, short r, short g, short b)
    {
        screen[(y * 48 + x) * 3] = r;
        screen[(y * 48 + x) * 3 + 1] = g;
        screen[(y * 48 + x) * 3 + 2] = b;
    }

    static public void main(String[] passedArgs) throws InterruptedException {
        
        short[] myImage=new short[24*48*3];
        int thisKey=0;
        int frame = 0;

        // This is initialization, do not change this
        InternalLedGameThread.run();

        // Now we show some introductory message and wait 1s before we switch to purple
        System.out.println("Please wait for three seconds for the game to start");
        Thread.sleep(1000);
        for(int i=0; i<myImage.length; i+=3){
            myImage[i+0]=(short)137;
            myImage[i+1]=(short)207;
            myImage[i+2]=(short)240;
        }
        System.out.println("Sending to displayThread");
        InternalLedGameThread.showImage(myImage);

        Bird playerController = new Bird();
        PipeManager pipeManager = new PipeManager();
        ScoreManager scoreHandler = new ScoreManager();

        // After 300ms we show the first green dot
        playerController.draw(myImage);
        Thread.sleep(300);
        InternalLedGameThread.showImage(myImage);

        // Now we loop with keyboard input to show the movement of the green dot
        while(true){
            thisKey= InternalLedGameThread.getKeyboard();
            if(thisKey!=-1) {
                switch (thisKey) {
                    case 0:
                        playerController.updateHeight((short) -1);
                        break;
                    case 1:
                        playerController.updateHeight((short) 1);
                        break;
                }                
            }
            playerController.draw(myImage);
            pipeManager.update(myImage, frame);
            scoreHandler.displayScore(myImage);
            scoreHandler.displayHighScore(myImage);
            if(pipeManager.checkCollision(playerController, scoreHandler))
            {
                //TODO some game over screen
                System.out.println("GAME OVER");              
                ResetScreen(myImage);
                pipeManager = new PipeManager();
                playerController = new Bird();
                frame = 0;
                scoreHandler.onGameOver();
                Thread.sleep(500);
            }
            else{
                pipeManager.setSpeed(scoreHandler);
                InternalLedGameThread.showImage(myImage);
                frame++;
            }           
            Thread.sleep(100);
        }
    }
}
