import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;       // List is like a resizable array (interface), it is a collection of data where you can store many. The order matters, and you can have duplicates. You can change the data by getting its position (index)
import java.util.ArrayList;  // ArrayList is a specific kind of List, it behaves like a resizable array, and you use it for data that can grow and shrink very fast like position.
import java.util.Set;        // Set holds unique items, in this case its values only
import java.util.HashSet;    // HashSet is a specific kind of Set, it doesn't allow duplicate data ( used in keystrokes to make sure the keyboard inputs arent overlayed)
import java.util.Random;    



public class BackgroundPanel extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L; // don't know what this line means, yellow bulb shows up when trying to fix this, and it was the quick fix. 

    private int numPlayers = 0;
    private List<Player> players = new ArrayList<>(); // This is like an array that can grow/shrink automatically (an array is like a way to store data, in this context we can use the array to store the data of the players.
    private Timer timer;
    private boolean gameOver = false;

    private PlatformManager platformManager;
    private Set<Integer> pressedKeys = new HashSet<>(); // HashSet stores key codes being pressed, no duplicates

    private long lastTagTime = System.currentTimeMillis();
    private final int TAG_COOLDOWN = 2000; // Minimum time (in ms) between tag switches
    private long gameStartTime;
    private final int GAME_DURATION = 45; // in seconds


    public BackgroundPanel() {
        setLayout(null);
        setFocusable(true);
        addKeyListener(this);

        platformManager = new PlatformManager(getWidth(), getHeight());

        setupUI();

        timer = new Timer(16, this); // Calls actionPerformed ~60 times per second (1000 / 16 ≈ 60 FPS)
    }

    private void setupUI() {
        JLabel title = new JLabel("Choose Number of Players", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(300, 80, 600, 60);
        add(title);

        // Buttons for selecting number of players
        String[] labels = {"1 Player", "2 Players", "3 Players"}; // array, which stores the strings on the main page. 
        for (int i = 0; i < 3; i++) {
            int playerCount = i + 1;
            JButton button = new JButton(labels[i]);
            button.setBounds(400, 180 + i * 100, 200, 50);
            button.setFont(new Font("SansSerif", Font.BOLD, 20));
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.addActionListener(e -> startGame(playerCount));
            add(button);
        }
    }

    private void startGame(int playersCount) {
    	gameStartTime = System.currentTimeMillis(); // Start countdown timer
        platformManager.setupPlatforms(getWidth(), getHeight());

        numPlayers = playersCount;
        removeAll();

        players.clear(); // Clear old players from the list if any

        int taggerIndex = 0; // Index of the player who starts as the tagger
        if (numPlayers ==3 ) {
            taggerIndex = new Random().nextInt(3); // Randomly pick tagger for 2 or more players 
        }

        // Create each player with name input and color
        for (int i = 0; i < numPlayers; i++) {
            String playerName;
            do {
                playerName = JOptionPane.showInputDialog("Enter name for Player " + (i + 1) + ":");
                if (playerName == null || playerName.trim().isEmpty()) {
                    playerName = "Player " + (i + 1);
                }
            } while (playerName == null);

            int startX = 300 + i * 150;
            Player player = new Player(playerName, startX, platformManager.getBottomPlatform().y);

            player.setTagger(i == taggerIndex); // Only the selected index becomes tagger

            // Assign color based on who is the tagger
            Color assignedColor;
            if (numPlayers == 3) {
                if (i == taggerIndex) {
                    assignedColor = Color.RED;  // Tagger
                } else if ((i == 0 && taggerIndex != 0) || (i == 1 && taggerIndex == 2) || (i == 2 && taggerIndex == 1)) {
                    assignedColor = Color.GREEN; // Player 2
                } else {
                    assignedColor = Color.BLUE;  // Player 3
                }
            } else {
                if (i == 0) {
                    assignedColor = player.isTagger() ? Color.RED : Color.BLUE;
                } else {
                    assignedColor = Color.GREEN;
                }
            }

            player.setOriginalColor(assignedColor); // Remember the original color
            players.add(player); // Add player to the list
        }

        timer.start(); // Start game loop

        revalidate();
        repaint();
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
     // Draw remaining time at the top
        long elapsedTime = (System.currentTimeMillis() - gameStartTime) / 1000;
        long remainingTime = Math.max(0, GAME_DURATION - elapsedTime);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.drawString("Time Left: " + remainingTime + "s", getWidth() / 2 - 100, 40);


        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            g.drawString("Game Over!", getWidth() / 2 - 120, getHeight() / 2);
        } else {
            platformManager.drawPlatforms(g);

            for (Player p : players) {
                p.draw(g); // Draw each player
            }

            // Display scores for each player
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                g.drawString(p.getName() + ": " + p.getScore(), 50, 50 + i * 30);
            }
        }
    }

    private void handleMovement(Player p) {
        int index = players.indexOf(p) + 1;
        String controls = switch (index) {
            case 1 -> "WASD";
            case 2 -> "IJKL";
            case 3 -> "Arrow Keys";
            default -> "";
        };

        // pressedKeys is a HashSet that keeps track of currently pressed keys
        // Checks which movement keys are active and move the player accordingly
        if (controls.equals("WASD")) {
            if (pressedKeys.contains(KeyEvent.VK_A)) p.moveLeft();
            if (pressedKeys.contains(KeyEvent.VK_D)) p.moveRight();
            if (pressedKeys.contains(KeyEvent.VK_W)) p.jump();
        } else if (controls.equals("IJKL")) {
            if (pressedKeys.contains(KeyEvent.VK_J)) p.moveLeft();
            if (pressedKeys.contains(KeyEvent.VK_L)) p.moveRight();
            if (pressedKeys.contains(KeyEvent.VK_I)) p.jump();
        } else if (controls.equals("Arrow Keys")) {
            if (pressedKeys.contains(KeyEvent.VK_LEFT)) p.moveLeft();
            if (pressedKeys.contains(KeyEvent.VK_RIGHT)) p.moveRight();
            if (pressedKeys.contains(KeyEvent.VK_UP)) p.jump();
        }
    }

    private void checkTagging() {
        // Check if tagger touches another player
        for (Player p1 : players) {
            if (!p1.isTagger()) continue; // Only the tagger can tag others

            for (Player p2 : players) {
                if (p1 != p2 && p1.getBounds().intersects(p2.getBounds())) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastTagTime > TAG_COOLDOWN) {
                        p1.setTagger(false);
                        p2.setTagger(true);
                        p1.addScore(10);
                        lastTagTime = currentTime;

                        if (p1.getScore() >= 100) {
                            endGame(p1);
                        }
                        return;
                    }
                }
            }
        }
    }

    private void endGame(Player winner) {
        timer.stop();
        gameOver = true;
        
        // message to whoever wins the game, after the 45 seconds are over, whoever was still it loses, and whoever wasn't wins. 
        
        JOptionPane.showMessageDialog(this, winner.getName() + " was still 'it'! Game Over.", "Time's Up", JOptionPane.INFORMATION_MESSAGE);


        removeAll();
        setupUI(); // Go back to the main menu (player selection)

        players.clear();

        revalidate();
        repaint();
        requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        for (Player p : players) {
            handleMovement(p);
            p.applyPhysics(getWidth(), getHeight());
            platformManager.checkPlatformCollisions(p, getHeight());
        }

        checkTagging();
        
        long elapsed = (System.currentTimeMillis() - gameStartTime) / 1000;
        if (elapsed >= GAME_DURATION) {
            for (Player p : players) {
                if (p.isTagger()) {
                    endGame(p); // The tagger loses
                    break;
                }
            }
            return;
        }


        repaint();
    }

    // When a key is pressed, we add it to the pressedKeys set
    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    // When a key is released, we remove it from the pressedKeys set
    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
