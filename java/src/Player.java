import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
    // Basic player properties
    private String name;
    private double x, y;               // Player's position
    private int width = 40, height = 40;

    // Velocity and physics-related properties
    private double xVelocity = 0, yVelocity = 0;
    private final double moveAcceleration = 1.2;
    private final double maxSpeed = 8;
    private final double friction = 0.89;
    private final double jumpVelocity = -14;
    private final double gravity = 0.6;
    private final double maxFallSpeed = 12;

    private boolean onGround = false;      // Whether player is on the ground
    private boolean isTagger = false;      // Whether this player is "it"
    private boolean phaseMode = false;     // Can pass through platforms (if enabled)
    private int score = 0;

    private Color color = Color.BLUE;      // Default color
    private Color originalColor;           // Stores original spawn color

    /**
     * Constructor to create a player with a name and starting position
     */
    public Player(String playerName, int startX, int groundY) {
        this.name = playerName;
        this.x = startX;
        this.y = groundY - height; // Position the player on top of the ground
        this.onGround = true;
    }

    // ------------------ Getters ------------------

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getYVelocity() {
        return yVelocity;
    }

    public double getXVelocity() {
        return xVelocity;
    }

    public int getBottom() {
        return (int) (y + height);
    }

    public int getTop() {
        return (int) y;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isTagger() {
        return isTagger;
    }

    public boolean isPhaseMode() {
        return phaseMode;
    }

    public boolean isOnGround() {
        return onGround;
    }

    // ------------------ Setters & Actions ------------------

    public void addScore(int points) {
        score += points;
    }

    public void setTagger(boolean value) {
        this.isTagger = value;
    }

    public void setPhaseMode(boolean value) {
        this.phaseMode = value;
    }

    public void setOnGround(boolean value) {
        onGround = value;
    }

    public void setYVelocity(double velocity) {
        this.yVelocity = velocity;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setOriginalColor(Color color) {
        this.originalColor = color;
    }

    public Color getOriginalColor() {
        return originalColor;
    }

    // ------------------ Movement Controls ------------------

    /**
     * Accelerates the player left until the max speed is reached
     */
    public void moveLeft() {
        xVelocity -= moveAcceleration;
        if (xVelocity < -maxSpeed) xVelocity = -maxSpeed;
    }

    /**
     * Accelerates the player right until the max speed is reached
     */
    public void moveRight() {
        xVelocity += moveAcceleration;
        if (xVelocity > maxSpeed) xVelocity = maxSpeed;
    }

    /**
     * Causes the player to jump if they are currently on the ground
     */
    public void jump() {
        if (onGround) {
            yVelocity = jumpVelocity;
            onGround = false;
        }
    }

    // ------------------ Physics & Collision Handling ------------------

    /**
     * Applies gravity, movement, and friction to update player’s position
     * @param panelWidth width of the game area
     * @param panelHeight height of the game area
     */
    public void applyPhysics(int panelWidth, int panelHeight) {
        // Gravity affects player when not grounded
        if (!onGround) {
            yVelocity += gravity;
            if (yVelocity > maxFallSpeed) yVelocity = maxFallSpeed;
        }

        // Update player position
        x += xVelocity;
        y += yVelocity;

        // Apply horizontal friction when on the ground
        if (onGround) {
            if (xVelocity > 0) {
                xVelocity -= friction;
                if (xVelocity < 0) xVelocity = 0;
            } else if (xVelocity < 0) {
                xVelocity += friction;
                if (xVelocity > 0) xVelocity = 0;
            }
        }

        // Stop player from going through the left or right edges of the panel
        if (x < 0) x = 0;
        if (x + width > panelWidth) x = panelWidth - width;

        // Stop player from falling through the floor
        if (y + height > panelHeight) {
            y = panelHeight - height;
            yVelocity = 0;
            onGround = true;
        }
    }

    /**
     * Called when the player lands on a platform
     * @param platformY the y-coordinate of the top of the platform
     */
    public void landOn(int platformY) {
        y = platformY - height;
        yVelocity = 0;  // Stop falling
        onGround = true;
    }

    /**
     * Called when the player hits their head on the bottom of a platform
     * @param platformBottomY the y-coordinate of the bottom of the platform
     */
    public void hitHeadOn(int platformBottomY) {
        y = platformBottomY;
        yVelocity = 0;  // Stop upward movement
    }

    // ------------------ Rendering ------------------

    /**
     * Returns a rectangle representing the player’s position and size
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    /**
     * Draws the player to the screen
     */
    public void draw(Graphics g) {
        // Red if tagger, otherwise original color
        if (isTagger) {
            g.setColor(Color.RED);
        } else {
            g.setColor(originalColor);
        }

        // Draw player as a circle
        g.fillOval((int) x, (int) y, width, height);

        // Outline if player is the tagger
        if (isTagger) {
            g.setColor(Color.BLACK);
            g.drawOval((int) x - 2, (int) y - 2, width + 4, height + 4);
        }

        // Draw the player’s name above their head
        g.setColor(Color.BLACK);
        g.drawString(name, (int) x, (int) y - 10);
    }
}
