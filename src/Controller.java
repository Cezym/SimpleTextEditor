import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private final View view;
    private final Model model;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public ActionListener adresInsert(String adres) {
        return e -> {
            view.getjTextArea().insert(adres, view.getjTextArea().getCaretPosition());
            view.getState().setText("modified");
        };
    }

    public void setForegroundButtonsListeners() {
        int abi = 0;
        for (JRadioButtonMenuItem jrbmi :
                view.getForegroundButtons()) {
            int a = abi;
            jrbmi.addActionListener(e -> {
                view.setPreviousForegroundColor(view.getjTextArea().getForeground());
                view.getFg().setIcon(new Dot(view.getPreviousForegroundColor(), 0));
                view.getjTextArea().setForeground(Model.getColors().get(a));
            });
            ++abi;
        }
    }

    public void setExitListener() {
        view.getExit().addActionListener(e -> {
        });
    }

    public void setBackgroundButtonsListeners() {
        int abi = 0;
        for (JRadioButtonMenuItem jrbmi :
                view.getBackgroundButtons()) {
            int a = abi;
            jrbmi.addActionListener(e -> {
                view.setPreviousBackgroundColor(view.getjTextArea().getBackground());
                view.getBg().setIcon(new Dot(view.getPreviousBackgroundColor(), 0));
                view.getjTextArea().setBackground(Model.getColors().get(a));
            });
            ++abi;
        }
    }

    public void setOpenListener() {
        view.getOpen().addActionListener(e -> {
            if (model.getjFileChooser().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                view.setFile(model.getjFileChooser().getSelectedFile());
                try {
                    Scanner scanner = new Scanner(view.getFile());
                    StringBuilder sb = new StringBuilder();
                    while (scanner.hasNextLine())
                        sb.append(scanner.nextLine()).append('\n');
                    view.getjTextArea().setText(sb.toString());
                    view.getJf().setTitle("Prosty edytor - " + view.getFile().getAbsolutePath());
                    view.getState().setText("saved");
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

        });
    }

    public void setSaveAsListener() {
        view.getSaveAs().addActionListener(e -> {
            if (model.getjFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                view.setFile(model.getjFileChooser().getSelectedFile());
                Model.saveToFile(view.getjTextArea().getText(), view.getFile());
                view.getJf().setTitle("Prosty edytor - " + view.getFile().getAbsolutePath());
            }

        });
    }

    public void setSaveListener() {
        view.getSave().addActionListener(e -> {
            if (view.getFile() == null || !view.getFile().exists()) {
                view.getSaveAs().getActionListeners()[0].actionPerformed(e);
            } else {
                view.getState().setText("saved");
                Model.saveToFile(view.getjTextArea().getText(), view.getFile());
            }
        });
    }

    private void jTextAreaSetListener() {
        view.getjTextArea().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!(e.isAltDown() || e.isAltGraphDown() || e.isMetaDown() || e.isControlDown() || e.isShiftDown()))
                    view.getState().setText("modified");
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void setFontListeners() {
        ArrayList<JMenuItem> fontsJMI = view.getFonts();
        for (MenuElement el :
                fontsJMI) {
            if (el instanceof JMenuItem font) {
                font.addActionListener(e -> {
                    int size = Integer.parseInt(font.getText().substring(0, font.getText().length() - 4));
                    view.getSize().setText("" + size);
                    view.getjTextArea().setFont(new Font("Arial", Font.PLAIN, size));
                });
            }
        }
    }

    public void initController() {
        jTextAreaSetListener();

        setForegroundButtonsListeners();
        setBackgroundButtonsListeners();
        setFontListeners();

        setSaveAsListener();
        setSaveListener();
        setOpenListener();
        setExitListener();

        view.getPraca().addActionListener(adresInsert(model.getAdresPracy()));
        view.getDom().addActionListener(adresInsert(model.getAdresDomu()));
        view.getSzkola().addActionListener(adresInsert(model.getAdresSzkoly()));
    }
}
