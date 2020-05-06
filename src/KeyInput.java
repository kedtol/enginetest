public class KeyInput
{
    private int[] keyBinds;
    private int[] stateList;
    private boolean[] keyStateList;
    private boolean[] mouseStateList = new boolean[4];
    private boolean[] mouseStatePressedList = new boolean[4];


    public KeyInput()
    {
        keyBinds = new int[1];

        for (int i = 0; i < keyBinds.length; i++)
        {
            keyBinds[i] = 0;
        }

        generateStateList();
    }

    public KeyInput(int[] keyBinds)
    {
        this.keyBinds = keyBinds;
        generateStateList();
    }

    public void generateStateList()
    {
        stateList = new int[keyBinds.length];
        keyStateList = new boolean[keyBinds.length];
        for (int i = 0; i < keyBinds.length; i++)
        {
            stateList[i] = 0;
            keyStateList[i] = false;
        }

        for (int i = 0; i < 4; i++)
        {
            mouseStateList[i] = true;
            mouseStatePressedList[i] = false;
        }
    }

    public void buttonPressed(char key, boolean released)
    {
        for (int i = 0; i < keyBinds.length; i++)
        {
            char bind = (char) keyBinds[i];
            if (bind == key)
            {
                if (released == true)
                {
                    stateList[i] = 0;
                }
                else
                {
                    stateList[i] = 1;
                }
            }
        }
    }

    public void mouseButtonPressed(int button, boolean released)
    {
        mouseStateList[button] = released;
    }

    public boolean isButtonPressed(int button)
    {
        if (stateList[button] == 1)
        {
            keyStateList[button] = true;
            return false;
        }
        else
        {
            if (keyStateList[button] == false)
            {
                return false;
            }
            else
            {
                keyStateList[button] = false;
                return true;
            }

        }
    }

    public boolean isButtonHold(int button)
    {
        if (stateList[button] == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean isMouseButtonHold(int button)
    {
        if (button <= 3)
        {
            if (!mouseStateList[button])
            {
                return true;
            }
        }

        return false;
    }

    public boolean isMouseButtonPressed(int button)
    {
        if (button <= 3)
        {
            if (mouseStateList[button])
            {
                mouseStatePressedList[button] = true;
                return false;
            }
            else
            {
                if (mouseStatePressedList[button] == false)
                {
                    return false;
                }
                else
                {
                    mouseStatePressedList[button] = false;
                    return true;
                }

            }
        }

        return false;
    }



}
