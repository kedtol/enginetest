import javax.swing.*;

public class InputHandler
{
    static int[] keyCodes ;//= new int[]{87, 65, 83, 68, 16};
    static JLabel bind = new JLabel();

    public InputHandler()
    {
        keyCodes = new int[500];
        for (int i = 0; i < 500; i++)
        {
            keyCodes[i] = i;
        }

    }

    public void bindSetup(Program program)
    {
        for (int i = 0; i < keyCodes.length; i++)
        {
            //press
            char key = (char) keyCodes[i];
            KeyStroke ks = KeyStroke.getKeyStroke(key,0,false);
            bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks,i);
            bind.getActionMap().put(i, new KeyAction(key,false,program));

            //release
            ks = KeyStroke.getKeyStroke(key,0,true);
            bind.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, i+"_");
            bind.getActionMap().put(i+"_", new KeyAction(key,true,program));
        }

    }

}
