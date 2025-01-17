import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Appointment {
    private String description;
    private int year;
    private int month;
    private int day;

    public Appointment(){
    }

    public Appointment(String description, int year, int month, int day){
        this.description = description ;
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int getYear(){
        return this.year;
    }

    public int getDay(){
        return this.day ;
    }

    public int getMonth(){
        return this.month ;
    }

    public boolean occursOn(int year, int month, int day){
        if(this.year == year && this.month == month && this.day == day){
            return true;
        }
        else{
            return false;
        }
    }

    public String getDescription(){
        return this.description ;
    }

    public static void save(String fileName, ArrayList<Appointment> appointments){
        try (PrintWriter out = new PrintWriter(fileName)){
            for(Appointment appointment : appointments){
                if(appointment instanceof Onetime){
                    out.printf("%s %d %d %d %s\n",appointment.getDescription(), appointment.getYear(),
                            appointment.getMonth(), appointment.getDay(), "one");
                }
                else if(appointment instanceof Daily){
                    out.printf("%s %d %d %d %s\n",appointment.getDescription(), appointment.getYear(),
                            appointment.getMonth(), appointment.getDay(), "day");
                }
                else if(appointment instanceof Monthly){
                    out.printf("%s %d %d %d %s\n",appointment.getDescription(), appointment.getYear(),
                            appointment.getMonth(), appointment.getDay(), "month");
                }
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void load(String fileName, ArrayList<Appointment> appointments){
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while(scanner.hasNext()){
                int index = 0 ;
                String tempt = scanner.nextLine() ;
                for(;!Character.isDigit(tempt.charAt(index));index++);
                String description = tempt.substring(0,index).trim();
//                System.out.println(description);

                String other = tempt.substring(index).trim();
//                System.out.println(other);

                Scanner line = new Scanner(other);
                int year = line.nextInt();
                int month = line.nextInt();
                int day = line.nextInt();
                String option = line.next().trim();
//                System.out.println(option);

                if(option.equals("one")){
                    appointments.add(new Onetime(description, year, month, day));
                }
                else if(option.equals("day")){
                    appointments.add(new Daily(description, year, month, day));
                }
                else if(option.equals("month")){
                    appointments.add(new Monthly(description, year, month, day));
                }

            }

        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
}

