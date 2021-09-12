package Person;

public class PersonCar {
   private String surname;
   private String name;
   private String patronymic;
   private String registration;
   private String passport;

    public PersonCar(){

    }
    public PersonCar(String surname, String name, String patronymic, String registration, String passport) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.registration = registration;
        this.passport = passport;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Владелец автомобиля: " + surname + " " + name + " " + patronymic + " [" + registration + "] " + "Паспорт : " + passport;
    }
}
