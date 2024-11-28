package org.example;

import org.example.Exceptions.OutOfBounds;
import org.example.Exceptions.SelfContact;
import org.example.Player.PlayerController;
import org.example.Player.PlayerMovement;
import org.example.Util.CollisionTypes;
import org.example.Util.Direction;
import org.example.World.Box;
import org.example.World.Jworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int score = 0;
    static Box worldBox = new Box(14,14);

    public static Box getWorldBox() {
        return worldBox;
    }

    static PlayerController controller = new PlayerController();

    public static void main(String[] args) {
        Jworld.getWorld();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleAtFixedRate(
                Main::tick,
                0, 100, TimeUnit.MILLISECONDS
        );

        // Shutdown the executor on program termination (optional)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executor.shutdown();
            System.out.println("Game loop stopped.");
        }));
    }
    static JDialog gameOverDialog = null;
    private static void showGameOverPopup(JFrame parentFrame, String cause) {
        gameOverDialog = new JDialog(parentFrame, "Game Over", true);
        gameOverDialog.setSize(300, 150);
        gameOverDialog.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Game Over With Score: " + score + " Cause: " + cause, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 10));
        gameOverDialog.add(messageLabel, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Game has been reset");
                worldBox.getObjLocations().clear();

                PlayerMovement.getINSTANCE().setMovement(Direction.RIGHT);
                controller.getPlayerPos().setX(3);
                controller.getPlayerPos().setY(7);

                score = 0;
                gameOverDialog.dispose();
            }
        });

        gameOverDialog.add(resetButton, BorderLayout.SOUTH);

        gameOverDialog.setLocationRelativeTo(parentFrame);

        gameOverDialog.setVisible(true);
    }

    public static void tick(){
        try {
            if(gameOverDialog == null || !gameOverDialog.isActive()) {
                controller.doPlayerMovement();
                if(worldBox.getObjLocations().stream().noneMatch(position -> position.getCollisionType() == CollisionTypes.Apple)){
                    Jworld.putApple();
                }
            }
        } catch (Exception e) {
            if(e instanceof OutOfBounds || e instanceof SelfContact) {
                System.out.println("Gameover " + e.getMessage());
                showGameOverPopup(Jworld.getWorld(), e.getMessage());
            }else{
                throw e;
            }
        }
    }
}