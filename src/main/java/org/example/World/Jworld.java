package org.example.World;

import org.example.Main;
import org.example.Player.PlayerController;
import org.example.Player.PlayerMovement;
import org.example.Util.CollisionTypes;
import org.example.Util.Direction;
import org.example.Util.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Random;

public class Jworld {
    static Random rand = new Random();
    private static Box worldBox = Main.getWorldBox();

    private static final JFrame world = generateWorld();

    private static JPanel panel;

    public static JFrame getWorld() {
        return world;
    }

    public static void putApple(){
        Position pos = null;
        List<Position> objectPos = worldBox.getObjLocations();
        while (pos == null) {
            Position textPos = new Position(CollisionTypes.Apple, rand.nextInt(worldBox.getWidth()), rand.nextInt(worldBox.getHeight()));

            if(!objectPos.stream().allMatch(position -> position.sameCord(textPos))){
                pos = textPos;
            }
        }
        worldBox.addObject(pos);
    }

    public static void repaintPanel() {
        panel.repaint();
    }

    private static JFrame generateWorld(){
        JFrame frame = new JFrame("Snake in Java");
        frame.setSize(worldBox.getWidth()*50 + 20, worldBox.getHeight()*50 + 40);
//        frame.setLayout(new GridLayout(worldBox.getWidth(), worldBox.getHeight()));

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                for (int x = 0; x < worldBox.getWidth(); x++){
                    g.drawLine(x * 50, 0, x * 50, worldBox.getHeight() * 50);
                }
                for (int y = 0; y < worldBox.getHeight(); y++){
                    g.drawLine(0, y * 50, worldBox.getWidth() * 50, y * 50);
                }
                for (Position pos : worldBox.getObjLocations()) {
                    g.setColor(pos.getCollisionType().getCollisionColor());
                    g.fillRect(pos.getX() * 50, pos.getY() * 50, 50, 50);
                }
            }
        };

        frame.add(panel);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                PlayerMovement controller = PlayerMovement.getINSTANCE();
                switch (KeyEvent.getKeyText(e.getKeyCode())){
                    case "W":
                        controller.setMovement(Direction.UP);
                        break;
                    case "S":
                        controller.setMovement(Direction.DOWN);
                        break;
                    case "A":
                        controller.setMovement(Direction.LEFT);
                        break;
                    case "D":
                        controller.setMovement(Direction.RIGHT);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        frame.setVisible(true);

        return frame;
    }
}
