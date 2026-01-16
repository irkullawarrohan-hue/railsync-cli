package CNFM.TICKT;

import CNFM.TICKT.Services.UserBookingService;
import CNFM.TICKT.entities.Train;
import CNFM.TICKT.entities.User;
import CNFM.TICKT.util.UserServiceUtil;
import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        try{
            userBookingService = new UserBookingService();
        }catch(IOException ex){
            System.out.println("Something went wrong");
            return;
        }
        while(option!=7){
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = scanner.nextInt();
            Train trainSelectedForBooking = new Train();
            switch (option){
                case 1:
                    System.out.println("Enter the username to signup");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = scanner.next();
                    User userToSignup = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignup);
                    break;
                case 2:
                    System.out.println("Enter the username to Login");
                    String nameToLogin = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToLogin = scanner.next();
                    User userToLogin = new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                    try{
                        userBookingService = new UserBookingService(userToLogin);
                    }catch (IOException ex){
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    System.out.println("Type your source station");
                    String source = scanner.next();
                    System.out.println("Type your destination station");
                    String dest = scanner.next();
                    List<Train> trains = userBookingService.getTrains(source, dest);

                    if (trains.isEmpty()) {
                        System.out.println("No trains found for this route.");
                        break;
                    }
                    int index = 1;
                    for (Train t : trains) {
                        System.out.println(index + ". Train id : " + t.getTrainId());
                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                            System.out.println("station " + entry.getKey() + " time: " + entry.getValue());
                        }
                        index++;
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    trainSelectedForBooking = trains.get(scanner.nextInt());
                    break;
                case 5:
                    if (trainSelectedForBooking == null) {
                        System.out.println("Please search and select a train first.");
                        break;
                    }

                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);

                    for (List<Integer> rowSeats : seats) {
                        for (Integer val : rowSeats) {
                            System.out.print(val + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Enter the row number (starting from 1):");
                    int row = scanner.nextInt() - 1;

                    System.out.println("Enter the column number (starting from 1):");
                    int col = scanner.nextInt() - 1;

                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);

                    if (booked) {
                        System.out.println("Booked! Enjoy your journey");
                    } else {
                        System.out.println("Seat already booked or invalid selection");
                    }
                    break;
            }
        }
    }
}