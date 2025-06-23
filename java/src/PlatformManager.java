import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class PlatformManager  {
    
	// Platforms represented as rectangular areas
    private Rectangle bottomPlatform, leftPlatform, rightPlatform, topPlatform;

    // List to store all platforms for easy storage
    private List<Rectangle> platforms;

    /**
     * Constructor initializes platforms based on panel size.
     *  panelWidth width of the game panel
     *  panelHeight height of the game panel
     */
    public PlatformManager(int panelWidth, int panelHeight) {
        setupPlatforms(panelWidth, panelHeight);
    }
//test
    /**
     * Creates and positions the platforms in the game.
     * Platforms are rectangles defined by position and size.
     * 
     * panelWidth the width of the game panel
     *  panelHeight the height of the game panel
     */
    public void setupPlatforms(int panelWidth, int panelHeight) {
        // Bottom platform centered near the bottom of the panel
        bottomPlatform = new Rectangle((panelWidth - 800) / 2, panelHeight - 100, 800, 30);

        // Left elevated platform above the bottom platform
        leftPlatform = new Rectangle(panelWidth / 2 - 300, panelHeight - 250, 250, 30);

        // Right elevated platform, symmetric to the left platform
        rightPlatform = new Rectangle(panelWidth / 2 + 50, panelHeight - 250, 250, 30);

        // Top platform near the top center of the panel
        topPlatform = new Rectangle(panelWidth / 2 - 100, panelHeight - 400, 200, 20);

        // Store all platforms in a list for easy iteration/drawing/collision
        platforms = List.of(bottomPlatform, leftPlatform, rightPlatform, topPlatform);
    }

    /**
     * Draws all platforms onto the panel.
     *  g Graphics object used for drawing
     */
    public void drawPlatforms(Graphics g) {
        g.setColor(java.awt.Color.DARK_GRAY);

        // Draw each platform as a filled rectangle
        for (Rectangle platform : platforms) {
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }
    }

    /**
     * Checks and resolves collisions between the player and platforms.
     * It ensures the player can land on platforms, cannot pass through them,
     * and hits their head if jumping into a platform from below.
     * 
     *  player The player object to check collisions for
     *  panelHeight The height of the game panel (used for falling check)
     */
    public void checkPlatformCollisions(Player player, int panelHeight) {
        // Get current bounding box of the player
        Rectangle playerBounds = player.getBounds();
        
        // Flag to track if the player is standing on any platform
        boolean onAnyPlatform = false;

        // Predict the player's next vertical position based on velocity
        double nextY = player.getY() + player.getYVelocity();

        // Current and predicted bottom Y positions of the player
        int playerBottomCurrent = (int) (player.getY() + player.getHeight());
        int playerBottomNext = (int) (nextY + player.getHeight());

        // Current and predicted top Y positions of the player
        int playerTopCurrent = (int) player.getY();
        int playerTopNext = (int) nextY;

        // Check collision against each platform
        for (Rectangle platform : platforms) {
            // Check if player horizontally overlaps with platform
            boolean horizontalOverlap = playerBounds.x + playerBounds.width > platform.x &&
                                        playerBounds.x < platform.x + platform.width;

            int platformTop = platform.y;
            int platformBottom = platform.y + platform.height;

            // Landing on platform: player is falling and will cross platform top this frame
            if (horizontalOverlap &&
                playerBottomCurrent <= platformTop &&
                playerBottomNext >= platformTop) {
                
                // Snap player to platform top and reset vertical velocity (land)
                player.landOn(platformTop);
                onAnyPlatform = true;

            // Head collision: player moving up and will hit platform bottom this frame
            } else if (horizontalOverlap &&
                       playerTopCurrent >= platformBottom &&
                       playerTopNext <= platformBottom) {
                
                // Adjust player's position and velocity due to head hit
                player.hitHeadOn(platformBottom);
                //hello
                
            }
        }

        // If player is not on any platform and not on floor, let them fall
        if (!onAnyPlatform && playerBottomNext < panelHeight) {
            player.setOnGround(false);
        }
    }

    /**
     * Getter for the bottom platform (e.g. ground level).
     *  Rectangle representing the bottom platform
     */
    public Rectangle getBottomPlatform() {
        return bottomPlatform;
    }
}
