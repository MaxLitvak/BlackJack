import java.awt.*;

public class card
{
   public int number;
   public int xpos;
   public int ypos;
   public boolean isAlive;
   public int width;
   public int height;
   public boolean dealer;
   public boolean playerHand1;
   public boolean aSplitHand1;
   public boolean aSplitHand2;
   
   public card (int tNumber)
   {
      number = tNumber;
      isAlive = false;
      width = 150;
      height = 170;
      xpos = 10;
      ypos = 10;
      dealer = false;
      playerHand1 = false;
      aSplitHand1 = false;
      aSplitHand2 = false;
   }
}