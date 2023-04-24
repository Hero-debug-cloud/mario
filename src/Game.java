import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable {

    @Override
    public void run() {
        final JFrame frame = new JFrame("Super Mario");
        frame.setLocation(300, 200);

        // Status panel contains the Score/Coins/Lives
        final JPanel status_panel = new JPanel();
        status_panel.setLayout(new GridLayout());
        frame.add(status_panel, BorderLayout.NORTH);

        final JLabel score_label = new JLabel("Score: 0", SwingConstants.CENTER);
        status_panel.add(score_label);

        final JLabel coins_label = new JLabel("Coins: 0", SwingConstants.CENTER);
        status_panel.add(coins_label);

        final JLabel lives_label = new JLabel("Lives: 3", SwingConstants.CENTER);
        status_panel.add(lives_label);

        // Card Layout for the Welcome Screen & Game Screen
        final CardLayout cl = new CardLayout();
        final JPanel contentPanel = new JPanel(cl);
        frame.add(contentPanel, BorderLayout.CENTER);

        // Welcome Screen
        final JPanel welcomeScreen = new JPanel();
        welcomeScreen.setLayout(new BoxLayout(welcomeScreen, BoxLayout.Y_AXIS));
        welcomeScreen.setBackground(new Color(107, 140, 255));

        // Welcome Screen: Title Image
        final ImageIcon titleImage = new ImageIcon("TitleScreen.png");
        final JLabel title = new JLabel(titleImage);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeScreen.add(title);
        contentPanel.add(welcomeScreen, "Welcome Screen"); // add Welcome Screen to contentPanel

        // Main playing area
        final GameCourt court = new GameCourt(score_label, coins_label, lives_label);
        court.setBackground(new Color(107, 140, 255));
        contentPanel.add(court, "Game");

        // Let's Play button
        ActionListener toGame = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cl.show(contentPanel, "Game");
                GameCourt.playing = true;
                court.requestFocus();
            }
        };

        final JButton letsPlay = new JButton("Let's Play!");
        letsPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
        letsPlay.addActionListener(toGame);
        welcomeScreen.add(letsPlay);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        // Start game
        court.reset();
    }

    /*
     * Main method run to start and run the game Initializes the GUI elements
     * specified in Game and runs it.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
