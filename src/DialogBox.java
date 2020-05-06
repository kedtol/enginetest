import java.awt.*;
import java.util.ArrayList;

public class DialogBox
{
    String text = "Őszintén, nagyon magas IQ-dnak kell lenni hogy megértsd a szupervideósok.com munkásságát. Nagyon kifinomult a humoruk, és ha nem értesz az elméleti fizikához, a poénok nagy része csak elszállnak egy tipikus hallgató/néző fülei mellett. És akkor ott van Kedtol nihilista világnézete, amely nagy részben jelen van a rímjeiben is(pl.: télapó egy szar, utálom) - Látszódik hogy rengeteg filozófiát olvas és személyiségét és rímjeit főleg Narodnaya Volya irodalma alakította azzá ami. Az igazi rajongók tudják ezeket; elég értelmesek hogy tényleg megbecsüljék ezeknek a poénoknak a mélységét, hogy rájöjjenek nem csak viccesek -Valami mélyet mondanak az életről. Ebből következik, hogy az emberek akik utálják a szupervideósokat, azok TÉNYLEG idióták persze hogy nem becsülnék, például jossi létezés értelmén elgondolkodtató jelmondatát \"SKRRR\" ami önmagában egy rejtett utalás Turgenev orosz Apák és fiúk című művére. Csak mosolygok ahogy épp elképzelem ahogy az olyan egyszerű, buta haterek vakarják a fejüket megzavarodottságukban ahogy Jossi09 és Kedtol zsenialitása kibontakozik a képernyőjükön. Mekkora bolondok... Úgy szánom őket. És amúgy igen, VAN Szupervideósok tetkóm, és nem, nem láthatod. Az kizárólag csak a Nők szemeinek vannak, és még nekik is be kell bizonyítaniuk hogy legalább 5 IQ pontjira vannak az én intelligenciámtól (Ha lehet lentebb) még mielött megnézik. ";
    //String text = "The ISPCC's keyword BREAKFAST was featured on Cheerios boxes of cereal and in other supporting promotional materials.";
    String speaker = "adamoli03";
    int style;

    boolean boxFreeze = false;
    boolean glide = false;
    boolean active = false;

    int maxChar = 100;
    int maxRow = 4;

    int width=500;
    int height=100;
    int speakerWidth = 100;
    int speakerHeight = 100;

    int yPadding = 100;
    int xPaddingInside = 30;
    int yPaddingInside = 30;
    int xPaddingInsideSpeaker = 10;
    int yPaddingInsideSpeaker = 5;
    int glideY = 0;

    int cursorPlace = 0;
    int letterCursor = 0;
    int lineCursor = 1;

    int fontSize = 22;
    int sideSize = 5;

    Alarm letterTimer = new Alarm();
    Alarm glideTimer = new Alarm();


    Color colorInside = Color.black;
    Color colorSide = Color.white;

    ArrayList<String> brokenLines = new ArrayList<>();

    public void moveCursor()
    {
        if (lineCursor > maxRow)
        {
            glide = true;
            boxFreeze = true;
        }
        else
        {
            lineCursor = maxRow+1;
        }
    }

    private void breakLines()
    {
        letterTimer.setLength(5);
        int startChar = 0;
        int endChar;
        String tText;

        while (true)
        {
            if (startChar+maxChar > text.length())
            {
                tText = text.substring(startChar);
                endChar = tText.length()-1;
            }
            else
            {
                tText = text.substring(startChar, startChar + maxChar);

                if (tText.charAt(0) == 32)
                {
                    tText = tText.substring(1);
                }

                endChar = tText.length()-1;
                char c = tText.charAt(endChar);
                while (!String.valueOf(c).equals(" "))
                {
                    endChar--;
                    c = tText.charAt(endChar);
                }
            }

            brokenLines.add(text.substring(startChar,startChar+endChar+1));

            if (startChar+endChar+1 != text.length())
            {
                startChar = startChar+endChar+1;
            }
            else
            {
                endChar = 0;
                break;
            }
        }
    }

    void draw(Graphics g)
    {
        if (active)
        {
            breakLines();

            Font old = g.getFont();
            Font font = new Font(g.getFont().getName(), Font.PLAIN, fontSize);
            g.setFont(font);

            FontMetrics metrics = g.getFontMetrics(font);
            String longest = "";
            for (String e : brokenLines)
            {
                if (metrics.stringWidth(e + " ▼") + xPaddingInside > metrics.stringWidth(longest + " ▼") + xPaddingInside)
                {
                    longest = e;
                }
            }
            int longestX = metrics.stringWidth(longest + " ▼") + xPaddingInside;
            int longestY = metrics.getHeight() * maxRow + yPaddingInside;

            width = longestX;
            height = longestY;


            int x = Camera.center.getX() - width / 2;
            int y = Program.screen_height - yPadding - height;

            g.setColor(colorSide);
            g.fillRect(x - sideSize, y - sideSize, width + sideSize * 2, height + sideSize * 2);
            g.setColor(colorInside);
            g.fillRect(x, y, width, height);

            if (speaker != null)
            {
                speakerWidth = metrics.stringWidth(speaker) + xPaddingInsideSpeaker;
                speakerHeight = metrics.getHeight() + yPaddingInsideSpeaker;
                g.setColor(colorSide);
                g.fillRect(x - sideSize, y - sideSize * 2 - speakerHeight, speakerWidth + sideSize * 2, speakerHeight + sideSize);
                g.setColor(colorInside);
                g.fillRect(x, y - speakerHeight - sideSize, speakerWidth, speakerHeight);
                g.setColor(colorSide);
                g.drawString(speaker, x + xPaddingInsideSpeaker / 2, y - speakerHeight / 2);
            }

            g.setColor(colorSide);


            for (int i = 0; i < lineCursor; i++)
            {
                String drawString = brokenLines.get(cursorPlace + i);

                if (i == maxRow - 1 && i != brokenLines.size() - 1)
                {
                    drawString = drawString + " ▼";
                }

                if (i == lineCursor - 1)
                {
                    if (lineCursor <= maxRow && letterCursor < drawString.length())
                    {
                        g.drawString(drawString.substring(0, letterCursor), x + xPaddingInside / 2 - sideSize, y + sideSize * 2 + yPaddingInside / 2 + (longestY / maxRow) * (i));
                    }
                }
                else
                {
                    if (glide)
                    {
                        if (i != 0)
                        {
                            g.drawString(drawString, x + xPaddingInside / 2 - sideSize, y + sideSize * 2 + yPaddingInside / 2 + (longestY / maxRow) * (i) + glideY);
                        }
                    }
                    else
                    {
                        g.drawString(drawString, x + xPaddingInside / 2 - sideSize, y + sideSize * 2 + yPaddingInside / 2 + (longestY / maxRow) * (i) + glideY);
                    }
                }

                if (letterTimer.finished() && !boxFreeze)
                {
                    if (letterCursor < drawString.length() - 1 && lineCursor < maxRow + 1)
                    {
                        letterCursor++;
                        if ("aeiouöüóőúűáéíAEIOUÖÜÓŐÚŰÁÉÍ".contains(String.valueOf(drawString.charAt(letterCursor))))
                        {
                            ResourceLoader.playSound("voice_0");
                            System.out.println(drawString.charAt(letterCursor));
                        }
                    }
                    else
                    {
                        letterCursor = 0;

                        if (lineCursor < maxRow + 1)
                        {
                            lineCursor++;
                        }
                    }
                    letterTimer.start();
                }

                if (glideTimer.finished() && glide)
                {
                    if (glideY > -metrics.getHeight())
                    {
                        glideY -= 1;
                    }
                    else
                    {
                        cursorPlace++;
                        letterCursor = 0;
                        lineCursor = maxRow;
                        boxFreeze = false;
                        glideY = 0;
                        glide = false;
                    }
                }
                else
                {
                    glideY = 0;
                }
            }

            g.setFont(old);
        }
    }



}
