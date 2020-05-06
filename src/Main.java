import javax.swing.*;
import java.awt.*;

public class Main
{
    static JFrame mainFrame;
    static ProgramPanel programPanel;
    private static boolean game = false;

    public static void main(String[] args)
    {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Plot claimer");

        if (args.length > 0 && args[0].equals("editormode"))
        {
            game = false;
        }
        else
        {
            game = true;
        }

        //game = false; //override

        programPanel = new ProgramPanel(1920,1080,game);
       // mainFrame.setUndecorated(true);
        mainFrame.add(programPanel,BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setSize(new Dimension(1920,1080));
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
       //
        mainFrame.setVisible(true);

        try {
            programPanel.Loop();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
