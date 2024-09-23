import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SimDisplay extends JPanel {
	private BufferedImage pixelArray;
	private FluidSim fluidSim;
	private float fluidHue = 0.6f;
	private float fluidSaturation = 0.5f;
	private static final int width = 400;
	private static final int height = 400;

	// Main method to create the frame and show the panel
	public static void main(String[] args) {
		JFrame frame = new JFrame("Fluid Simulation");
		SimDisplay panel = new SimDisplay();
		frame.add(panel);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public SimDisplay() {
		// Create a BufferedImage with the specified width and height
		pixelArray = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		fluidSim = new FluidSim(width, height);

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
		fluidSim.step();
		double[][] fluidGrid = fluidSim.getFluidGrid();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				float brightness = (float) fluidGrid[x][y];
				Color fluidColor = Color.getHSBColor(fluidHue, fluidSaturation, brightness);
				pixelArray.setRGB(x, y, fluidColor.getRGB());
			}
		}

		// Repaint the panel after updating the pixels
		repaint();
	}

}
