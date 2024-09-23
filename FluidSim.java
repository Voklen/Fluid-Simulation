
public class FluidSim {
	private int width;
	private int height;
	private double[][] prevFluidGrid;
	private double[][] fluidGrid;

	public FluidSim(int width, int height) {
		this.width = width;
		this.height = height;
		fluidGrid = new double[width][height];

		// Create line in the middle at the start of the simulation
		for (int y = 1; y < height - 1; y++) {
			fluidGrid[200][y] = 1f;
		}
	}

	public double[][] getFluidGrid() {
		return fluidGrid;
	}

	public void step() {
		prevFluidGrid = fluidGrid;

		// Loop through each cell and update it based on the mean of the surrounding
		// cells to create diffusion
		// This takes the data from `prevFluidGrid` and writes to `fluidGrid` so written
		// data does not interfear with the diffusion
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				double selfVal = prevFluidGrid[x][y];
				double leftVal = prevFluidGrid[x - 1][y];
				double rightVal = prevFluidGrid[x + 1][y];
				double topVal = prevFluidGrid[x][y - 1];
				double bottomVal = prevFluidGrid[x][y + 1];
				double totalVal = (selfVal + leftVal + rightVal + topVal + bottomVal) / 5;
				fluidGrid[x][y] = totalVal;
			}
		}
	}
}
