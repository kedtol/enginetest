import java.awt.*;

public class MouseTool extends GameObject
{
    Vector2D mVector = new Vector2D(0,0);
    int speed = 3;
    GameObject target = new GameObject();
    String selectedObject = "nothing";
    GameObject selectedGameObject = null;

    private boolean inRadialMenu = false;
    private RadialMenu radialMenu = new RadialMenu();

    int tool = 0;
    Plot selectedPlot = null;

    public MouseTool()
    {
        int[] keyBinds = new int[]{87, 65, 83, 68, 81, 69, 67};
        keyInput = new KeyInput(keyBinds);

       // texture = new Texture(ResourceLoader.getTexture("editor",1));
        if (texture != null)
        {
            area = new Area(this);
        }
    }

    private void movement()
    {
       mVector.nullVector();

        if (keyInput.isButtonHold(2))
        {
            mVector.add(new Vector2D(0,speed));
        }

        if (keyInput.isButtonHold(0))
        {
            mVector.add(new Vector2D(0,-speed));
        }

        if (keyInput.isButtonHold(3))
        {
            mVector.add(new Vector2D(speed,0));
        }

        if (keyInput.isButtonHold(1))
        {
            mVector.add(new Vector2D(-speed,0));
        }

        if (mVector.getLength() > speed)
        {
            mVector.adjust(speed);
        }

        target.transform.move(mVector);
        Camera.target = target;
        Camera.sync();
    }

    private void tool()
    {
        if (keyInput.isButtonPressed(4))
        {
            if (tool > 0)
            {
                tool -= 1;
            }
        }
        if (keyInput.isButtonPressed(5))
        {
            if (tool < 5)
            {
                tool += 1;
            }
        }

        if (keyInput.isMouseButtonPressed(1))
        {

        }

        if (keyInput.isMouseButtonPressed(3))
        {
            if (Program.collision.collidePlot(area) != null)
            {
                /*ArrayList<Integer> types = new ArrayList<>();
                types.add(tool);
                //types.add(1);
                Program.collision.collidePlot(area).setUnit(new Unit(tool, "boi", "move", types));*/

                if (Program.collision.collidePlot(area).getClaimId() == 0)
                {
                    Program.collision.collidePlot(area).setClaimId(1);
                }
            }
        }
    }

    public void action()
    {
        transform.setY(Program.mouse_y-Camera.getVector().getYComponent());
        transform.setX(Program.mouse_x-Camera.getVector().getXComponent());



        area.sync(transform.getX(),transform.getY());

        tool();
        movement();
    }


    public void draw(Graphics g)
    {
        if (Program.collision.collideTransformWFlag("wall",transform,hitbox) != null)
        {
            g.setColor(Color.cyan);
        }
        else
        {
            g.setColor(Color.red);
        }

        if (texture != null)
        {
            texture.draw(g,transform.convertToLocal().getX(),transform.convertToLocal().getY(),-1);
        }

        if (area != null)
        {
            area.draw(g);
        }

        if (selectedGameObject != null)
        {
            selectedObject = selectedGameObject.flag;
        }
        else
        {
            selectedObject = "nothing";
        }


        if (selectedPlot != null)
        {
            g.drawString("Zone: " + selectedPlot.getZoneId(), 100, 200);
        }
        g.drawString("tool: "+tool,100,500);

        g.drawString("x: "+target.transform.getX(),100,400);
        g.drawString("y: "+target.transform.getY(),100,450);

    }
}
