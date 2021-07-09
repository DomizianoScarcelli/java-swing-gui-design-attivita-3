package controller;

import model.AsciiFont;
import model.AsciiPanel;
import view.MainPanel;

import javax.swing.*;
import java.awt.*;


public class ToolsPanelController {
    private static ToolsPanelController instance;

    private static MainPanel mainPanel = MainPanel.getInstance();

    public static ToolsPanelController getInstance() {
        if (instance == null) instance = new ToolsPanelController();
        return instance;
    }

    private ToolsPanelController(){}

    public void updatePreview() {
        mainPanel.getCharPreviewPanel().clear();
        mainPanel.getCharPreviewPanel().write((char) mainPanel.getSelectedChar(), 1, 1, mainPanel.getDefaultForegroundColor(), mainPanel.getDefaultBackgroundColor());
        SwingUtilities.updateComponentTreeUI(mainPanel); //Prevents a weird bug when a character was selected
    }

    public static void selectPaintButton(){
        mainPanel.getPaint().setBackground(Color.GRAY);
        mainPanel.getPick().setBackground(Color.WHITE);
        mainPanel.getFill().setBackground(Color.WHITE);
    }

    public static void selectPickButton(){
        mainPanel.getPaint().setBackground(Color.WHITE);
        mainPanel.getPick().setBackground(Color.GRAY);
        mainPanel.getFill().setBackground(Color.WHITE);
    }

    public static void selectFillButton(){
        mainPanel.getPaint().setBackground(Color.WHITE);
        mainPanel.getPick().setBackground(Color.WHITE);
        mainPanel.getFill().setBackground(Color.GRAY);
    }
    
    public static void reset(int sx, int sy){
        mainPanel.getMainContainer().remove(mainPanel.getAsciiPanel());
        mainPanel.setAsciiPanel(new AsciiPanel(sx, sy, AsciiFont.CP437_16x16));
        mainPanel.getMainContainer().add((mainPanel.getAsciiPanel()), BorderLayout.CENTER);
        mainPanel.getAsciiPanel().clear();
        mainPanel.getAsciiPanel().setCursorX(0);
        mainPanel.getAsciiPanel().setCursorY(0);
        mainPanel.getAsciiPanel().write("Empty");
        mainPanel.getAsciiPanel().addMouseMotionListener(new AsciiPanelMouseMotionListener(mainPanel));
        SwingUtilities.updateComponentTreeUI(mainPanel); //Prevents a weird bug when a character was selected
    }


}
