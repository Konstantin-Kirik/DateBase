package Person;

public class Car {
    private String marka;
    private String model;
    private String color;
    private String type;
    private String platen_number;
    private Integer index;

    public Car() {

    }

    public Car(String marka, String model, String color, String type, String platen_number) {
        this.marka = marka;
        this.model = model;
        this.color = color;
        this.type = type;
        this.platen_number = platen_number;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaten_number() {
        return platen_number;
    }

    public void setPlaten_number(String platen_number) {
        this.platen_number = platen_number;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Автомобиль : " + marka + " " + model + " " + color + " " + type + " Номерной знак : " + platen_number;
    }
}
