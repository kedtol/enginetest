import java.awt.*;

import static Engine.Geometry.*;
import static java.lang.Math.round;

public class Player extends GameObject
{
    private Vector2D mVector= new Vector2D(0,0);
    int speed = 2;
    private boolean inRadialMenu = false;
    private RadialMenu radialMenu = new RadialMenu();
    int angle = 0;

    public Player()
    {
        flag = "player";
        int[] keyBinds = new int[]{87, 65, 83, 68, 81, 69, 67};
        keyInput = new KeyInput(keyBinds);
        transform.parent = this;
        Camera.target = this;

        if (ResourceLoader.findTextureImageKey("player") != -1)
        {
            texture = new Texture(ResourceLoader.textureImages.get(ResourceLoader.findTextureImageKey("player")).get(0));
            texture.setSize(5);
            hitbox = new Hitbox(this);
            hitbox.size = 5;
            texture.frameSpeed = 0;
        }

        texture = new Texture(ResourceLoader.getTextureImage("player"));
        texture.setSize(5);
        texture.frameSpeed = 0;
        if (texture != null)
        {
            hitbox = new Hitbox(this);
            //hitbox.size = 5;
        }
    }

    public void action()
    {
        if (System.currentTimeMillis()%5 == 0)
        {
            movement();
        }

        if (keyInput.isMouseButtonPressed(1))
        {
        }

        if (keyInput.isButtonPressed(5))
        {
           /* if (stats.getMana() < stats.getMaxMana())
            {
                stats.setMana(stats.getMana()+1);
            }*/
           //ResourceLoader.playSound("voice",0);
           // HUD.dialogBox.moveCursor();
        }


        if (keyInput.isButtonPressed(4))
        {
            /*if (stats.getMana() > 0)
            {
                stats.setMana(stats.getMana()-1);
            }*/

            ResourceLoader.playSound("voice_0");
        }

        if (keyInput.isButtonHold(6))
        {
            inRadialMenu = true;
            radialMenu.boot = true;
            radialMenu.start = true;
        }
        else
        {
            radialMenu.start = false;

            //inRadialMenu = false;
        }

        angle = round(closedAngle(transform.convertToLocal().getX(),transform.convertToLocal().getY(),Program.mouse_x,Program.mouse_y));
        angle += 5;

        if (angle == 360)
        {
            angle = 0;
        }


    }

    public void movement()
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



        if (Program.collision.collideAreaFlag("wall",area) == null)
        {
            if (!mVector.isNullVector())
            {
                transform.move(mVector);
            }

        }


    }



    public void draw(Graphics g)
    {
        int angle = round(closedAngle(transform.convertToLocal().getX(), transform.convertToLocal().getY(), Program.mouse_x, Program.mouse_y));

        if (texture != null)
        {
            texture.angle = angle;
            texture.draw(g,transform.convertToLocal().getX(),transform.convertToLocal().getY(),0);
        }

        if (inRadialMenu)
        {
            radialMenu.draw(g, transform.convertToLocal(), angle);
        }

        if (hitbox != null)
        {
            hitbox.draw(g,transform.convertToLocal());
        }
        g.drawString(transform.getX()+" x",100,400);
        g.drawString(transform.getY()+" y",100,420);
        int camfullx = transform.convertToLocal().getX();
        int camfully = transform.convertToLocal().getY();
        g.drawString("X: "+camfullx,200,400);
        g.drawString("Y: "+camfully,200,420);


        //int angle = closedAngle(Camera.center.getX(),Camera.center.getY(),Program.mouse_x,Program.mouse_y);
        g.drawString("angle: "+angle,100,200);
        //g.drawString("frame: "+round((angle)/36),100,150);
        g.drawLine(Camera.center.getX(),Camera.center.getY(),Program.mouse_x,Program.mouse_y);
        hitbox.lineInside(10,40,500,300,g);
    }
}
