import java.awt.*;

import static java.lang.Math.round;

public class EditorTool extends GameObject
{
    Vector2D mVector = new Vector2D(0,0);
    int speed = 5;
    GameObject target = new GameObject();
    String selectedObject = "nothing";
    GameObject selectedGameObject = null;
    int tool = 0;

    public EditorTool()
    {
        int[] keyBinds = new int[]{87, 65, 83, 68, 81, 69, 67};
        keyInput = new KeyInput(keyBinds);

        //texture = new Texture(ResourceLoader.getTexture("editor",1));
        if (texture != null)
        {
            hitbox = new Hitbox(this);
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

        /*if (keyInput.isMouseButtonPressed(1))
        {
            switch(tool)
            {
                case 0:
                selectedGameObject = Program.collision.collideTransformWFlagGet(null,transform,hitbox);
                break;

                case 1:
                Program.instantiate(new Transform(transform.getX(),transform.getY()),selectedGameObject.texture,selectedGameObject.flag,selectedGameObject.hitbox,selectedGameObject.keyInput);
                break;

            }


        }*/
    }

    public void action()
    {
        transform.setY(Program.mouse_y-Camera.getVector().getYComponent());
        transform.setX(Program.mouse_x-Camera.getVector().getXComponent());
        tool();
        movement();
    }


    public void draw(Graphics g)
    {
        /*if (Program.collision.collideTransformWFlag("wall",transform,hitbox))
        {
            g.setColor(Color.cyan);
        }
        else
        {
            g.setColor(Color.red);
        }*/

        if (texture != null)
        {
            texture.draw(g,transform.convertToLocal().getX(),transform.convertToLocal().getY(),-1);
        }

        if (hitbox != null)
        {
            hitbox.draw(g,transform.convertToLocal());
        }

        if (selectedGameObject != null)
        {
            selectedObject = selectedGameObject.flag;
        }
        else
        {
            selectedObject = "nothing";
        }



        g.drawString("Selected tool: "+tool,100,100);
        g.drawString("Selected item: "+selectedObject,100,150);

        g.drawString("x: "+target.transform.getX(),100,400);
        g.drawString("y: "+target.transform.getY(),100,450);

    }
}
