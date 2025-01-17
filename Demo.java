import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        Appointment.load("outfile", appointments);
//        System.exit(1);

        boolean done = false ;
        while(!done){
            int option = Demo.printOptions();
            switch (option){
                case 1 -> {
                    Scanner in = new Scanner(System.in);
                    Demo.printListAppointment(appointments);
                    System.out.print("insert any keypress to back to the main page");
                    String opt = in.next() ;
                }
                case 2 -> {
                    Scanner in = new Scanner(System.in);
                    System.out.println("select the following options : ");
                    System.out.println("1) One time upon an appointment");
                    System.out.println("2) every day as a routine");
                    System.out.println("3) every month monthly");
                    int opt = in.nextInt() ;
                    Appointment appointment = Demo.addAppointment(opt);
                    appointments.add(appointment);
                }
                case 3 -> {
                    Scanner in = new Scanner(System.in);
                    for(Appointment appt : appointments){
                        System.out.printf("- %s [%d/%d/%d]\n",appt.getDescription(), appt.getYear()
                                , appt.getMonth(), appt.getDay());
                    }
                    System.out.print("insert any keypress to back to the main page");
                    String opt = in.next() ;
                }

                case 4 -> {
                    done = true ;
                    System.out.println("the proceed has ended");
                    Appointment.save("outfile", appointments);
                }
            }
        }

    }

    public static Appointment addAppointment(int type){
        Scanner scan = new Scanner(System.in);
        System.out.print("insert the activity name : ");
        String description = scan.nextLine().trim();

        int[] date = new int[3];
        System.out.print("insert the date by following this format \"y m d\" :");
        for(int i=0; i < date.length; i++){
            date[i] = scan.nextInt() ;
        }
        switch (type){
            case 1 -> {
                return new Onetime(description,date[0], date[1], date[2]);
            }
            case 2 -> {
                return new Monthly(description,date[0], date[1], date[2]);
            }
            case 3 -> {
                return new Daily(description,date[0], date[1], date[2]);
            }
        }
        return null ;
    }

    public static void printListAppointment(ArrayList<Appointment> appointments){
        int[] date = new int[3];
        Scanner scan = new Scanner(System.in);
        System.out.print("insert the date by following this format \"y m d\" :");
        for(int i=0; i < date.length; i++){
            date[i] = scan.nextInt() ;
//            System.out.printf("date[%d] : %d\n", i, date[i]);
        }
        for(int i=0; i < appointments.size(); i++){
            if(appointments.get(i).occursOn(date[0],date[1], date[2])){
                System.out.println("- " + appointments.get(i).getDescription());
            }
        }
    }

    public static int printOptions(){
        Scanner input = new Scanner(System.in);
        System.out.println("select your option for proceeding");
        System.out.println("1) List the activity corresponding by particular date");
        System.out.println("2) Add the new appointment to the list");
        System.out.println("3) List all appointments");
        System.out.println("4) Quit");
        System.out.print("option : ");
        int option = input.nextInt() ;
        return option ;
    }
}
