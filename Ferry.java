public class Ferry {
  private String name;
  private int maxSeats;

  Ferry(String name, int maxSeats) {
    this.name = name;
    this.maxSeats = maxSeats;
  }

  public int getMaxSeats() {
      return maxSeats;
  }

  public String toString() {
    return "Ferry\n" +
            "name: " + name + '\n' +
            "maxSeats: " + maxSeats + '\n';
  }
}
