import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class ResourceLoader
{
    static ArrayList<ArrayList<TextureImage>> textureImages = new ArrayList<>(1);
    static ArrayList<String> textureImageKeys = new ArrayList<>();
    static ArrayList<ArrayList<File>> sounds = new ArrayList<>(1);
    static ArrayList<String> soundKeys = new ArrayList<>();
    static Clip clip;
    static ArrayList<String> mapKeys = new ArrayList<>();
    static ArrayList<Map> maps = new ArrayList<>(1);

    public void fillKeys()
    {
        addKey("heart",0);
        addKey("editor",0);
        addKey("radialSpell",0);
        addKey("claim",0);
        addKey("zone",0);
        addKey("selector",0);
        addKey("player",0);
        addKey("pistol",0);

        addKey("voice",1);
        addKey("selector",1);
        addKey("hitwood",1);
        addKey("spellCharge",1);
        addKey("spellHit",1);
        addKey("spellShoot",1);
    }

    public void addKey(String key, int type)
    {
        switch (type)
        {
            case 0:
            textureImageKeys.add(key);
            textureImages.add(new ArrayList<>(1));
            break;

            case 1:
            soundKeys.add(key);
            sounds.add(new ArrayList<>(1));
            break;
        }
    }

    public boolean loadTextures()
    {
        fillKeys();
        File path = new File("img/");

        for (File file : path.listFiles())
        {

            String format = "\\.png";

            for (String string : textureImageKeys)
            {
                if (file.getName().matches(string + "\\d_\\d" + format))
                {
                    String[] parts = file.getName().split("_");
                    String frameNumber = "";

                    int imageN = Integer.parseInt(parts[0].substring(string.length()));

                    for (int i = 0; i < parts[1].length(); i++)
                    {
                        String current = String.valueOf(parts[1].charAt(i));

                        if (!current.equals("."))
                        {
                            frameNumber += current;
                        }
                        else
                        {
                            break;
                        }
                    }

                    int frameN = Integer.parseInt(frameNumber);

                    try
                    {
                        BufferedImage frame = ImageIO.read(getClass().getResource(file.getName()));
                        String a = file.getName();

                        if (imageN > textureImages.get(findTextureImageKey(string)).size()-1)
                        {

                            ArrayList<BufferedImage> frames = new ArrayList<>();
                            frames.add(frameN, frame);
                            TextureImage textureImage = new TextureImage(frames);

                            textureImages.get(findTextureImageKey(string)).ensureCapacity(imageN);
                            while (textureImages.get(findTextureImageKey(string)).size() < imageN + 1)
                            {
                                textureImages.get(findTextureImageKey(string)).add(null);
                            }


                            textureImages.get(findTextureImageKey(string)).set(imageN, textureImage);
                        }
                        else
                        {
                            TextureImage textureImage = textureImages.get(findTextureImageKey(string)).get(imageN);
                            ArrayList<BufferedImage> frames = textureImage.getFrames();
                            frames.add(frameN, frame);
                            textureImage.setFrames(frames);
                        }
                    }
                    catch (IOException e)
                    {
                        System.out.println("Image load error (" + string + " , " + imageN + " , " + frameN + ")");
                    }


                }
            }
        }

        return true;
    }

    public boolean loadSounds()
    {
        fillKeys();
        File path = new File("snd/");

        for (File file : path.listFiles())
        {

            String format = "\\.wav";

            for (String string : soundKeys)
            {
                if (file.getName().matches(string + "_\\d" + format))
                {
                    String[] parts = file.getName().split("_");
                    int clipNumber = Integer.parseInt(parts[1].substring(0,parts[1].length()-4));

                    sounds.get(findSoundKey(string)).ensureCapacity(clipNumber);
                    while (sounds.get(findSoundKey(string)).size() < clipNumber + 1)
                    {
                        sounds.get(findSoundKey(string)).add(null);
                    }

                    sounds.get(findSoundKey(string)).set(clipNumber, file);

                }
            }
        }

        return true;
    }

    public boolean loadMaps()
    {
        try
        {
            FileReader fr = new FileReader("maps.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            Map map;

            String name = "none";

            while (line != null)
            {
                ArrayList<Plot[]> plots = new ArrayList<>();
                int k = 0;
                while (!line.startsWith("end"))
                {

                    if (line.startsWith("<"))
                    {
                        mapKeys.add(line.substring(1));
                    }

                    if (line.startsWith(">"))
                    {
                        Plot[] row = new Plot[line.length()-1];

                        for (int i = 0; i < line.length()-1; i++)
                        {
                            Area area = new Area(32*i,32*k,32,32);
                            row[i] = new Plot(32,area);
                            row[i].setZoneId(Integer.parseInt(String.valueOf(line.charAt(i+1))));
                        }
                        k++;
                        plots.add(row);
                    }

                    line = br.readLine();
                }
                Plot[][] plotsf = new Plot[plots.size()][plots.get(0).length];

                for (int i = 0; i < plots.size(); i++)
                {
                    Plot[] booleans = plots.get(i);
                    for (int j = 0; j < booleans.length; j++)
                    {
                        plotsf[i][j] = booleans[j];
                    }
                }

                map = new Map(plotsf,plots.size(),plots.get(0).length);
                maps.add(map);
                line = br.readLine();
            }

            return true;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    public static int findTextureImageKey(String key)
    {
        for (int i = 0; i < textureImageKeys.size(); i++)
        {
            if (textureImageKeys.get(i).equals(key))
            {
                return i;
            }
        }

        return -1;
    }

    public static int findSoundKey(String key)
    {
        for (int i = 0; i < soundKeys.size(); i++)
        {
            if (soundKeys.get(i).equals(key))
            {
                return i;
            }
        }

        return -1;
    }

    public static void playSound(String keyId)
    {
        int id = Integer.parseInt(keyId.split("_")[1]);
        String key = keyId.split("_")[0];

        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sounds.get(findSoundKey(key)).get(id));
            ais.reset();
            clip = AudioSystem.getClip();
            clip.setMicrosecondPosition(0);
            clip.open(ais);
            clip.start();
        }
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e)
        {
            e.printStackTrace();
        }
    }

    public static TextureImage getTextureImage(String key)
    {
        if (ResourceLoader.findTextureImageKey(key) != -1 && textureImages.get(findTextureImageKey(key)).size() != 0)
        {
            return ResourceLoader.textureImages.get(findTextureImageKey(key)).get(1);
        }
        else
        {
            return new TextureImage();
        }
    }

    public static TextureImage getTextureImage(String key, int number)
    {
        if (ResourceLoader.findTextureImageKey(key) != -1 && textureImages.get(findTextureImageKey(key)).size() != 0 && number < ResourceLoader.textureImages.get(findTextureImageKey(key)).size())
        {
            return ResourceLoader.textureImages.get(findTextureImageKey(key)).get(number);
        }
        else
        {
            return new TextureImage();
        }
    }

    public static Map findMap(String key)
    {
        for (int i = 0; i < mapKeys.size(); i++)
        {
            if (mapKeys.get(i).equals(key))
            {
                return maps.get(i);
            }
        }
        return null;
    }

}


