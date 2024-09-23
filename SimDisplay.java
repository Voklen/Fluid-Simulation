import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SimDisplay extends JPanel {
	private double[][] prevFluidGrid;
	private double[][] fluidGrid;
	private BufferedImage pixelArray;
	private float fluidHue = 0.6f;
	private float fluidSaturation = 0.5f;
	private final int width = 400;
	private final int height = 400;

	public SimDisplay() {
		fluidGrid = new double[400][400];
		// Create a BufferedImage with the specified width and height
		pixelArray = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int y = 1; y < height - 1; y++) {
			fluidGrid[200][y] = 1f;
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
		prevFluidGrid = fluidGrid;
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				double selfBrightness = prevFluidGrid[x][y];
				double leftBrightness = prevFluidGrid[x - 1][y];
				double rightBrightness = prevFluidGrid[x + 1][y];
				double topBrightness = prevFluidGrid[x][y - 1];
				double bottomBrightness = prevFluidGrid[x][y + 1];
				double totalBrightness = (selfBrightness + leftBrightness + rightBrightness + topBrightness + bottomBrightness) / 5;
				fluidGrid[x][y] = totalBrightness;
				Color fluidColor = Color.getHSBColor(fluidHue, fluidSaturation, (float)totalBrightness);
				pixelArray.setRGB(x, y, fluidColor.getRGB());
			}
		}
		repaint(); // Repaint the panel after updating the pixels
	}

	// Main method to create the frame and show the panel
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pixel Array Example");
		SimDisplay panel = new SimDisplay();
		frame.add(panel);
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
