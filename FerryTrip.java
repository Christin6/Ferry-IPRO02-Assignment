import java.time.LocalDateTime;
import java.util.List;

enum TripType {
  ONE_WAY_TRIP, TWO_WAY_TRIP
}

public class FerryTrip {
  private String destination, startingPoint;
  private TripType tripType;
  double basePrice;
  double discount = 0;
  List <Customer> customers;
  LocalDateTime tripDateTime;
}
