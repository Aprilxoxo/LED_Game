package de.thdeg.game;

import java.util.ArrayList;

//7 segment display for numbers
public class NumberRenderer {
    boolean[][] segmentCache;
    final short MAX_SHORT = 255;

    //initialize the segmentCache, which specifies what pixels to color to display a given number
    NumberRenderer() 
    {
        this.segmentCache = new boolean[10][];
        this.segmentCache[0] = new boolean[] { true, true, true, true, false, true, true, false, true, true, false,
                true, true, true, true };
        this.segmentCache[1] = new boolean[] { false, false, true, false, false, true, false, false, true, false, false,
                true, false, false, true };
        this.segmentCache[2] = new boolean[] { true, true, true, false, false, true, true, true, true, true, false,
                false, true, true, true };
        this.segmentCache[3] = new boolean[] { true, true, true, false, false, true, true, true, true, false, false,
                true, true, true, true };
        this.segmentCache[4] = new boolean[] { true, false, true, true, false, true, true, true, true, false, false,
                true, false, false, true };
        this.segmentCache[5] = new boolean[] { true, true, true, true, false, false, true, true, true, false, false,
                true, true, true, true };
        this.segmentCache[6] = new boolean[] { true, true, true, true, false, false, true, true, true, true, false,
                true, true, true, true };
        this.segmentCache[7] = new boolean[] { true, true, true, false, false, true, false, false, true, false, false,
                true, false, false, true };
        this.segmentCache[8] = new boolean[] { true, true, true, true, false, true, true, true, true, true, false, true,
                true, true, true };
        this.segmentCache[9] = new boolean[] { true, true, true, true, false, true, true, true, true, false, false,
                true, true, true, true };
    }

    //converts a number to pixel data
    public boolean[] intToPixels(int number) 
    {
        if (number < 0 || number > 9) 
        {
            return this.segmentCache[0];
        }

        return this.segmentCache[number];
    }

    //renders a given digit on screen at the top right + a given offset for both x and y
    private void renderDigit(int digit, int topleftX, int topleftY, short[] screen) 
    {
        boolean[] pixels = intToPixels(digit);
        for (int y = 0; y < 5; y++) 
        {
            for (int x = 0; x < 3; x++) 
            {
                if (pixels[y * 3 + x])
                    GameMain.setPixel(screen, topleftX + x, topleftY + y, MAX_SHORT, MAX_SHORT, MAX_SHORT);
            }
        }
    }

    //renders a given digit on screen at the top right + a given y offset
    public void renderNumberTopRight(int number, short[] screen, int yNegativeOffset) 
    {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        if (number == 0) 
        {
            digits.add(0);
        } else
        {
            // First we split the number into it's digits
            while (number > 0)
            {
                digits.add(number % 10);
                number = number / 10;
            }
        }

        // Then we iterate backwards though the list and render the digits

        int currentX = 48;
        int topY = 0 + yNegativeOffset;
        int spacing = 1;
        for (int i = 0; i < digits.size(); i++)
        {
            // We move back enough space for the digit and spacing
            currentX -= 3 + spacing;
            renderDigit(digits.get(i), currentX, topY, screen);
        }
    }
}