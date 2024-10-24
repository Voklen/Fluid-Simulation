public class FluidSim {
	private int width;
	private int height;
	private Cell[][] prevFluidGrid;
	private Cell[][] fluidGrid;
	private double rate = 0.75;

	public FluidSim(int width, int height) {
		this.width = width;
		this.height = height;
		fluidGrid = new Cell[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < width; y++) {
				fluidGrid[x][y] = new Cell(0);
			}
		}

		// Create line in the middle at the start of the simulation
		for (int y = 150; y < 251; y++) {
			for (int x = 150; x < 251; x++) {
				fluidGrid[x][y] = new Cell(1);
			}
		}
	}

	public Cell[][] getFluidGrid() {
		return fluidGrid;
	}

	public void step() {
		prevFluidGrid = fluidGrid;

		// Loop through each cell and update it based on the mean of the surrounding
		// cells to create diffusion
		// This takes the data from `prevFluidGrid` and writes to `fluidGrid` so written
		// data does not interfere with the diffusion
		for (int y = 1; y < height - 1; y++) {
			for (int x = 1; x < width - 1; x++) {
				double currentVal = prevFluidGrid[x][y].density;

				double leftVal = prevFluidGrid[x - 1][y].density;
				double rightVal = prevFluidGrid[x + 1][y].density;
				double topVal = prevFluidGrid[x][y - 1].density;
				double bottomVal = prevFluidGrid[x][y + 1].density;
				double targetVal = (leftVal + rightVal + topVal + bottomVal) / 4;
				double finalDensity = currentVal + rate * (targetVal - currentVal);

				fluidGrid[x][y] = new Cell(finalDensity);
			}
		}
	}
}
