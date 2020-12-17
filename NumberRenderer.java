package de.thdeg.game;

import java.util.ArrayList;

public class NumberRenderer {
    boolean[][] segmentCache;

    NumberRenderer()
    {
        this.segmentCache = new boolean[10][];
        this.segmentCache[0] = new boolean[]{true,true,true,true,false,true,true,false,true,true,false,true,true,true,true};
        this.segmentCache[1] = new boolean[]{false,false,true,false,false,true,false,false,true,false,false,true,false,false,true};
        this.segmentCache[2] = new boolean[]{true,true,true,false,false,true,true,true,true,true,false,false,true,true,true};
        this.segmentCache[3] = new boolean[]{true,true,true,false,false,true,true,true,true,false,false,true,true,true,true};
        this.segmentCache[4] = new boolean[]{true,false,true,true,false,true,true,true,true,false,false,true,false,false,true};
        this.segmentCache[5] = new boolean[]{true,true,true,true,false,false,true,true,true,false,false,true,true,true,true};
        this.segmentCache[6] = new boolean[]{true,true,true,true,false,false,true,true,true,true,false,true,true,true,true};
        this.segmentCache[7] = new boolean[]{true,true,true,false,false,true,false,false,true,false,false,true,false,false,true};
        this.segmentCache[8] = new boolean[]{true,true,true,true,false,true,true,true,true,true,false,true,true,true,true};
        this.segmentCache[9] = new boolean[]{true,true,true,true,false,true,true,true,true,false,false,true,true,true,true};
    }

    public boolean[] intToPixels(int number)
    {
        if (number < 0 || number > 9)
        {
            //Dafuq are u doin
            return this.segmentCache[0];
        }
        
       return this.segmentCache[number];
    }

    //TODO change render engine to dispose of frame
   private void renderDigit(int digit, int topleftX, int topleftY, short[] screen)
   {
       boolean[] pixels = intToPixels(digit);
       for (int y = 0; y < 5; y++)
       {
           for(int x = 0; x < 3; x++)
           {
               short r = pixels[y * 3 + x] ? (short)255 : (short)137;
               short g = pixels[y * 3 + x] ? (short)255 : (short)207;
               short b = pixels[y * 3 + x] ? (short)255 : (short)240;
               GameMain.setPixel(screen, topleftX + x, topleftY + y, r, g, b);
           }
       }
   }

   public void renderNumberTopRight(int number, short[] screen, int yNegativeOffset)
   {
       ArrayList<Integer> digits = new ArrayList<Integer>();
       if(number == 0)
       {
           digits.add(0);
       }
       else{
       //First we split the number into it's digits
           while (number > 0) {
               digits.add(number%10);
               number = number / 10;
           }
       }

       //Then we iterate backwards though the list and render the digits
       
       int currentX = 48;
       int topY = 0 + yNegativeOffset;
       int spacing = 1;
       for (int i = 0; i < digits.size(); i++)
       {
           //We move back enough space for the digit and spacing
           currentX -= 3 + spacing;
           renderDigit(digits.get(i), currentX, topY, screen);
       }
   }
}