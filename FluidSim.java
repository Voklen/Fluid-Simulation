import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FluidSim extends JPanel {
	private BufferedImage pixelArray;
	private final int width = 400; // Width of the pixel array
	private final int height = 400; // Height of the pixel array
	private Random random = new Random(); // For updating pixels randomly

	public FluidSim() {
		// Create a BufferedImage with the specified width and height
		pixelArray = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < height; y++) {
			for (int x = 1; x < width; x++) {
				// Update each pixel with a random color for demonstration
				int color = random.nextInt(0xFFFFFF); // Random color
				pixelArray.setRGB(x, y, color);
			}
		}

		// Initialize the Timer to update the pixels every 100 milliseconds
		Timer timer = new Timer(100, e -> updatePixels());
		timer.start();
	}

	// Override the paintComponent method to draw the pixel array
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(pixelArray, 0, 0, null); // Draw the pixel array on the panel
	}

	// Method to update the pixel array based on the previous state
	private void updatePixels() {
		for (int y = 0; y < height; y++) {
			for (int x = 1; x < width; x++) {
				// Update each pixel with the color of the pixel left of it
				int color = pixelArray.getRGB(1, y);
				pixelArray.setRGB(x, y, color);
			}
		}
		repaint(); // Repaint the panel after updating the pixels
	}

	// Main method to create the frame and show the panel
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pixel Array Example");
		FluidSim panel = new FluidSim();
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
