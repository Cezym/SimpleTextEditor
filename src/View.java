import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

class Dot implements Icon {

    Color color;
    int marginx;

    public Dot(Color color, int marginx) {
        this.color = color;
        this.marginx = marginx;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.fillOval(marginx, 4, 10, 10);
    }

    @Override
    public int getIconWidth() {
        return 10;
    }

    @Override
    public int getIconHeight() {
        return 10;
    }
}

public class View {
    private final JTextArea jTextArea = new JTextArea();
    private final JLabel fg;
    private final JLabel bg;
    private final JLabel size;
    private final JLabel state;
    private final ArrayList<JMenuItem> fonts = new ArrayList<>();
    private final JFrame jf;
    private final ArrayList<JRadioButtonMenuItem> foregroundButtons = new ArrayList<>();
    private final ArrayList<JRadioButtonMenuItem> backgroundButtons = new ArrayList<>();
    private Color previousForegroundColor;
    private Color previousBackgroundColor;
    private File file;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem saveAs;
    private JMenuItem exit;
    private JMenuItem praca;
    private JMenuItem dom;
    private JMenuItem szkola;

    public View() {
        jf = new JFrame();
        addMenu();

        jf.setTitle("Prosty edytor - bez tytułu");

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();

        jp.setLayout(new BorderLayout());

        JScrollPane jScrollPane = new JScrollPane(jTextArea);

        jp.add(jScrollPane, BorderLayout.CENTER);

        JPanel bot = new JPanel();
        bot.setLayout(new GridLayout(1, 2));

        JPanel botLeft = new JPanel();
        botLeft.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel optionsStatePanel = new JPanel();

        fg = new JLabel("fg");

        optionsStatePanel.add(fg);

        bg = new JLabel("bg");

        optionsStatePanel.add(bg);

        size = new JLabel("" + jTextArea.getFont().getSize());

        optionsStatePanel.add(size);

        botLeft.add(optionsStatePanel);

        bot.add(botLeft);

        JPanel botRight = new JPanel();
        botRight.setLayout(new FlowLayout(FlowLayout.RIGHT));

        state = new JLabel("new");

        botRight.add(state);

        bot.add(botRight);

        jp.add(bot, BorderLayout.SOUTH);

        jf.add(jp);

        jf.setResizable(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        jf.setSize(width / 2, height / 2);

        jf.setLocation(width / 2 - jf.getWidth() / 2, height / 2 - jf.getHeight() / 2);

        jf.setVisible(true);
    }

    public JFrame getJf() {
        return jf;
    }

    public JMenuItem getOpen() {
        return open;
    }

    public JMenuItem getSave() {
        return save;
    }

    public JMenuItem getSaveAs() {
        return saveAs;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public Color getPreviousForegroundColor() {
        return previousForegroundColor;
    }

    public void setPreviousForegroundColor(Color previousForegroundColor) {
        this.previousForegroundColor = previousForegroundColor;
    }

    public Color getPreviousBackgroundColor() {
        return previousBackgroundColor;
    }

    public void setPreviousBackgroundColor(Color previousBackgroundColor) {
        this.previousBackgroundColor = previousBackgroundColor;
    }

    public JLabel getFg() {
        return fg;
    }

    public JLabel getBg() {
        return bg;
    }

    public JLabel getSize() {
        return size;
    }

    public JLabel getState() {
        return state;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public JMenuItem getPraca() {
        return praca;
    }

    public JMenuItem getDom() {
        return dom;
    }

    public JMenuItem getSzkola() {
        return szkola;
    }

    public ArrayList<JRadioButtonMenuItem> getForegroundButtons() {
        return foregroundButtons;
    }

    public ArrayList<JRadioButtonMenuItem> getBackgroundButtons() {
        return backgroundButtons;
    }

    public void addMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menu.add(fileMenu);
        open = new JMenuItem("Open", KeyEvent.VK_O);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        fileMenu.add(open);

        save = new JMenuItem("Save", KeyEvent.VK_S);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        fileMenu.add(save);

        saveAs = new JMenuItem("Save as", KeyEvent.VK_V);
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        fileMenu.add(saveAs);

        JSeparator separator = new JSeparator();
        separator.setBackground(Color.RED);
        fileMenu.add(separator);

        exit = new JMenuItem("Exit", KeyEvent.VK_X);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.ALT_MASK));
        fileMenu.add(exit);

        jf.setJMenuBar(menu);

        JMenu editMenu = new JMenu("Edit");
        menu.add(editMenu);
        JMenu adresyEditMenu = new JMenu("Adresy");
        editMenu.add(adresyEditMenu);

        praca = new JMenuItem("Praca", KeyEvent.VK_P);
        praca.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK | ActionEvent.ALT_MASK));
        adresyEditMenu.add(praca);
        dom = new JMenuItem("Dom", KeyEvent.VK_D);
        dom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK | ActionEvent.ALT_MASK));
        adresyEditMenu.add(dom);
        szkola = new JMenuItem("Szkoła", KeyEvent.VK_S);
        szkola.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK | ActionEvent.ALT_MASK));
        adresyEditMenu.add(szkola);

        JMenu optionsMenu = new JMenu("Options");
        menu.add(optionsMenu);
        JMenu foregroundOptionsMenu = new JMenu("Foreground");
        optionsMenu.add(foregroundOptionsMenu);
        ButtonGroup foregroundButtonGroup = new ButtonGroup();

        for (int i = 0; i < Model.getColors().size(); i++) {
            JRadioButtonMenuItem jRadioButtonMenuItem = new JRadioButtonMenuItem(Model.generateNameOfColors().get(i));
            jRadioButtonMenuItem.setForeground(Model.getColors().get(i));
            jRadioButtonMenuItem.setIcon(new Dot(Model.getColors().get(i), 20));
            jRadioButtonMenuItem.setIconTextGap(jRadioButtonMenuItem.getIcon().getIconWidth() / 2);
            foregroundButtonGroup.add(jRadioButtonMenuItem);
            foregroundButtons.add(jRadioButtonMenuItem);
            foregroundOptionsMenu.add(jRadioButtonMenuItem);
        }

        JMenu backgroundOptionsMenu = new JMenu("Background");
        optionsMenu.add(backgroundOptionsMenu);
        ButtonGroup backgroundButtonGroup = new ButtonGroup();
        for (int i = 0; i < Model.getColors().size(); i++) {
            JRadioButtonMenuItem jRadioButtonMenuItem = new JRadioButtonMenuItem(Model.getNameOfColors().get(i));
            jRadioButtonMenuItem.setForeground(Model.getColors().get(i));
            jRadioButtonMenuItem.setIcon(new Dot(Model.getColors().get(i), 20));
            jRadioButtonMenuItem.setIconTextGap(jRadioButtonMenuItem.getIcon().getIconWidth() / 2);
            backgroundButtonGroup.add(jRadioButtonMenuItem);
            backgroundButtons.add(jRadioButtonMenuItem);
            backgroundOptionsMenu.add(jRadioButtonMenuItem);
        }

        JMenu fontSizeOptionsMenu = new JMenu("Font size");
        optionsMenu.add(fontSizeOptionsMenu);

        for (int i = 8; i <= 24; i += 2) {
            JMenuItem font = new JMenuItem(i + " pts");
            font.setFont(new Font("Arial", Font.PLAIN, i));
            fontSizeOptionsMenu.add(font);
            fonts.add(font);
        }
    }

    public ArrayList<JMenuItem> getFonts() {
        return fonts;
    }
}