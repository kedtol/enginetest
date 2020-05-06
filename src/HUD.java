import java.awt.*;

public class HUD
{
  //  private Texture heart = new Texture(ResourceLoader.getTexture("heart",1));
    static DialogBox dialogBox = new DialogBox();

    private int heartPadding = 2;
    private int heartPaddingReal = 2;
    private int heartRelativeX;
    private int heartPaddingY = 40; // y padding between hearts
    private int heartPaddingYReal;
    private int heartYEnd; // the last coord of hearts in Y

    private boolean screenFade = false;
    private Color screenFadeColor;

    public HUD()
    {
        //heart.frameSpeed = 0;
        //heart.size = 3;

        //heartRelativeX = heart.getRealWidth() / 2 + 16;
    }

    public void draw(Graphics g)
    {
        drawHearts(g);
        screenFade(g);
        dialogBox.draw(g);
    }

    public void drawHearts(Graphics g)
    {
        /*if (stats != null)
        {
            for (int i = 0; i < stats.getMaxHealth()/2; i++)
            {
                heartPaddingYReal = heartPaddingY *(Math.round(i/10)+1);
                int a = ((i%10)*1);
                heartPaddingReal = (heart.getRealWidth() * a + heartRelativeX + heartPadding * a);

                if (i * 2 % 2 == 0 && i * 2 + 2 > stats.getHealth() && i * 2 < stats.getHealth()) //half
                {
                    heart.draw(g, heartPaddingReal, heartPaddingYReal, 1);
                }
                else
                {
                    if (i * 2 >= stats.getHealth()) //empty
                    {
                        heart.draw(g, heartPaddingReal, heartPaddingYReal, 2);
                    }
                    else //full
                    {
                        heart.draw(g, heartPaddingReal, heartPaddingYReal, 0);
                    }
                }
            }
            heartYEnd = heartPaddingYReal;
        }*/
    }

    public void screenFade(Graphics g)
    {
        if (screenFade == true)
        {
            //new Color(0, 0, 0, 160);
            Color fade = screenFadeColor;
            g.setColor(fade);
            g.fillRect(0, 0, Program.screen_width, Program.screen_height);
        }
    }

}
