public class Cell {
  public double density;
  public Vector2 velocity;

  Cell(double density) {
    this.density = density;
    this.velocity = new Vector2(0, 0);
  }
}
