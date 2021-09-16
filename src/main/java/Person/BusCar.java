package Person;

public class BusCar extends Car{
    private int seats;

    public BusCar() {

    }

    public BusCar(int seats) {
        this.seats = seats;
    }

    public BusCar(String marka, String model, String color, String type, String platen_number, int seats) {
        super(marka, model, color, type, platen_number);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Автобус : " + super.getMarka() + " " + super.getModel() + " " + super.getColor() + " " + super.getType()
                + " Номерной знак : " + super.getPlaten_number() + "  " +  "Количество мест =" + seats;

    }
}
