import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyAction extends AbstractAction
{

    private char key;
    private boolean released;
    private Program program;

    KeyAction(char _key, boolean _released,Program _program)
    {
        program = _program;
        key = _key;
        released = _released;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        program.inputReceive(key,released);
    }
}