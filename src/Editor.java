import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Editor extends Program
{
    InputStreamReader irs = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(irs);
    FileInputStream fis;
    ObjectInputStream ois;

    public Editor()
    {
        map = null;
        System.out.println("Waiting for command");

        while (true)
        {
            try
            {
                String input = br.readLine();

                if (input.startsWith("load "))
                {
                    fis = new FileInputStream(input.substring(5));
                    System.out.println("trying to load: "+input.substring(5));
                    ois = new ObjectInputStream(fis);
                    gameObjectList = (ArrayList<GameObject>) ois.readObject();

                    break;
                }

                if (input.equals("new"))
                {
                    System.out.println("New map created!");
                    gameObjectList = new ArrayList<>();
                    gameObjectList.add(new GameObject("wall"));
                    gameObjectList.add(new GameObject("wall"));
                    gameObjectList.add(new EditorTool());
                    break;
                }

            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
                System.out.println("Error! try again");
            }

        }
    }

    public void inputReceive(char key,boolean released)
    {
        for (int i = 0; i < gameObjectList.size(); i++)
        {
            gameObjectList.get(i).keyAction(key,released);
        }
    }

    public void mainLoop()
    {
        if (map != null)
        {
            time.tickMove();

            for (int i = 0; i < gameObjectList.size(); i++)
            {
                gameObjectList.get(i).action();
            }
        }

    }

    public void drawCycle(Graphics g)
    {
        g.drawString(String.valueOf(Time.fullTick),10,10);
        g.drawString(String.valueOf(Time.tick),10,20);

        for (GameObject i : gameObjectList)
        {
            i.draw(g);
        }

    }

}
