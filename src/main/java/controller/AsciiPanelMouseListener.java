package controller;


import controller.commands.*;
import controller.commands.shapes.CircleCommand;
import controller.commands.shapes.SquareCommand;
import view.MainPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Models the actions performed on the panel by the mouse when it's not moving.
 */
public class AsciiPanelMouseListener implements MouseListener {
    /**
     * The {@link MainPanel} object that allows to edit the image on the ascii panel when an action is performed.
     */
    private MainPanel mainPanel;
    private int initialCursorX;
    private int initialCursorY;
    private int finalCursorX;
    private int finalCursorY;


    /**
     * Class constructor from an {@link MainPanel} object.
     */
    public AsciiPanelMouseListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
//        mainPanel.setCurrentButtonPressed(e.getButton());
//        Command currentCommand = mainPanel.getCommand();
//        if (currentCommand instanceof FillCommand) mainPanel.setCommand(new FillCommand(mainPanel));
//        else if (currentCommand instanceof PickCommand) mainPanel.setCommand(new PickCommand(mainPanel));
//        else if (currentCommand instanceof PaintCommand) {
////            ((PaintCommand) currentCommand).setPreviousCommand(null);
//            PaintCommand newPaintCommand = new PaintCommand(mainPanel);
//            newPaintCommand.setPreviousCommand(currentCommand);
//            mainPanel.setCommand(newPaintCommand);
//        }
//
//
//        mainPanel.executeCommand();
    }

    /**
     * {@inheritDoc}
     * Triggers the specific action of the currently selected tool.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //Update initial mouse cursor position
       initialCursorX = mainPanel.getAsciiPanel().getMouseCursorX();
        initialCursorY = mainPanel.getAsciiPanel().getMouseCursorY();


        mainPanel.setCurrentButtonPressed(e.getButton());
        Command currentCommand = mainPanel.getCommand();
        if (currentCommand instanceof FillCommand) mainPanel.setCommand(new FillCommand(mainPanel));
        else if (currentCommand instanceof PickCommand) mainPanel.setCommand(new PickCommand(mainPanel));
        else if (currentCommand instanceof PaintCommand) {
            PaintCommand newPaintCommand = new PaintCommand(mainPanel);
            newPaintCommand.setPreviousCommand(currentCommand);
            mainPanel.setCommand(newPaintCommand);
        }
//        else if (currentCommand instanceof SquareCommand) mainPanel.setCommand(new SquareCommand(mainPanel.getShapeDimension().getValue()));
//        else if (currentCommand instanceof CircleCommand) mainPanel.setCommand(new CircleCommand(mainPanel.getShapeDimension().getValue()));

        if (!(currentCommand instanceof  SquareCommand || currentCommand instanceof CircleCommand)){
            mainPanel.executeCommand();
            mainPanel.getAsciiPanel().repaint();
        }


    }


    @Override
    public void mouseReleased(MouseEvent e) {
        Command currentCommand = mainPanel.getCommand();
        //Update final mouse cursor position
        finalCursorX = mainPanel.getAsciiPanel().getMouseCursorX();
        finalCursorY = mainPanel.getAsciiPanel().getMouseCursorY();

        int x1 = initialCursorX;
        int x2 = finalCursorX;
        int y1 = initialCursorY;
        int y2 = finalCursorY;

        int pointDistance = (int) Math.round(Math.sqrt( Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2)));

        if (currentCommand instanceof CircleCommand) {
            mainPanel.getAsciiPanel().setCursorX(initialCursorX);
            mainPanel.getAsciiPanel().setCursorY(initialCursorY);
            mainPanel.setCommand(new CircleCommand(pointDistance));
            mainPanel.getCommand().execute();
            mainPanel.getAsciiPanel().repaint();
        }
        else if (currentCommand instanceof SquareCommand){
            mainPanel.getAsciiPanel().setCursorX(initialCursorX);
            mainPanel.getAsciiPanel().setCursorY(initialCursorY);
            mainPanel.setCommand(new SquareCommand(pointDistance));
            mainPanel.getCommand().execute();
            mainPanel.getAsciiPanel().repaint();
        }

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }


    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getInitialCursorX() {
        return initialCursorX;
    }

    public void setInitialCursorX(int initialCursorX) {
        this.initialCursorX = initialCursorX;
    }

    public int getInitialCursorY() {
        return initialCursorY;
    }

    public void setInitialCursorY(int initialCursorY) {
        this.initialCursorY = initialCursorY;
    }

    public int getFinalCursorX() {
        return finalCursorX;
    }

    public void setFinalCursorX(int finalCursorX) {
        this.finalCursorX = finalCursorX;
    }

    public int getFinalCursorY() {
        return finalCursorY;
    }

    public void setFinalCursorY(int finalCursorY) {
        this.finalCursorY = finalCursorY;
    }
}
