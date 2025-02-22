
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Main {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final String TITLE = "My Enhanced Application";
    private static final String MESSAGE = "Welcome to My Application!";
    private static Clip clip;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(TITLE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLocationRelativeTo(null);
            
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            
            JLabel label = new JLabel(MESSAGE, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            panel.add(label, BorderLayout.CENTER);
            
            frame.add(panel);
            frame.setVisible(true);
            
            new Thread(Main::monitorNetworkTable).start();
        });
    }

    private static void monitorNetworkTable() {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable table = inst.getTable("datatable");
        NetworkTableEntry playSoundEntry = table.getEntry("playSound");
        
        while (true) {
            boolean shouldPlay = playSoundEntry.getBoolean(false);
            controlSound(shouldPlay);
            
            try {
                Thread.sleep(500); // Adjust polling rate as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void controlSound(boolean shouldPlay) {
        if (shouldPlay) {
            if (clip == null || !clip.isRunning()) {
                new Thread(() -> playSound("sound.wav")).start();
            }
        } else {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
        }
    }

    private static void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
