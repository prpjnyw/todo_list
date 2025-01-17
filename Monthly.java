public class Monthly extends Appointment{
    public Monthly(String description, int year, int month, int day){
        super(description, year, month,day);
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        if(this.getDay() == day) return true ;
        else return false ;
    }
}

