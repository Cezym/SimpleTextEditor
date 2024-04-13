import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Model {

    private final static ArrayList<Color> colors = generateColors();
    private final static ArrayList<String> nameOfColors = generateNameOfColors();
    private static final String adresPracy = "ul. Pracy 73";
    private static final String adresDomu = "ul. Mieszkaniowa 10/25";
    private static final String adresSzkoly = "ul. Nauki 6";
    private JFileChooser jFileChooser;

    public Model() {
        createJFileChooser(true);
        generateColors();
    }

    public static ArrayList<String> getNameOfColors() {
        return nameOfColors;
    }

    public static ArrayList<Color> getColors() {
        return colors;
    }

    public static void saveToFile(String text, File file) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(text);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Color> generateColors() {

        ArrayList<Color> colors = new ArrayList<>();

        Color BUFFColor = new Color(0, 0, 0);

        for (Field field :
                Color.class.getDeclaredFields()) {
            if (field.getType().toString().equals(Color.class.toString()))
                try {
                    Object colorObj = field.get(BUFFColor);
                    if (colorObj instanceof Color color && field.getName().equals(field.getName().toLowerCase())) {
                        colors.add(color);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return colors;
    }

    public static ArrayList<String> generateNameOfColors() {

        ArrayList<String> nameOfColors = new ArrayList<>();

        Color BUFFColor = new Color(0, 0, 0);

        for (Field field :
                Color.class.getDeclaredFields()) {
            if (field.getType().toString().equals(Color.class.toString()))
                try {
                    Object colorObj = field.get(BUFFColor);
                    if (colorObj instanceof Color && field.getName().equals(field.getName().toLowerCase())) {
                        nameOfColors.add((char) (field.getName().charAt(0) - 'a' + 'A') + field.getName().substring(1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return nameOfColors;
    }

    public JFileChooser getjFileChooser() {
        return jFileChooser;
    }

    private void createJFileChooser(boolean doWindowsLAF) {
        if (doWindowsLAF)
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        jFileChooser = new JFileChooser();

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getAdresPracy() {
        return adresPracy;
    }

    public String getAdresDomu() {
        return adresDomu;
    }

    public String getAdresSzkoly() {
        return adresSzkoly;
    }
}
