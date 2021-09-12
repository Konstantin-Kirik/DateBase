package Console;

import Connection.ConnectionDB;
import Person.Car;
import Person.PersonCar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Method {

    private static final String SELECTTWO = "select surname, personcar.name, patronymic, registration, passport, marka, model, color, car.type , platen_number  from personcar \n" +
            "\t\t\t\tleft join car on personcar.car_id = car.id\n" +
            "union\n" +
            "select surname, personcar.name, patronymic, registration, passport, marka, model, color, car.type , platen_number from personcar \n" +
            "\t\t\t\tinner join car on personcar.car_id = car.id\n" +
            "                 WHERE personcar.id is null;";

    private static final String SELECT = "select * from personcar \n" +
            "\t\t\t\tinner join car on personcar.car_id = car.id;";
    private static final String UPDATEPERSON = "insert into personcar (id, surname, name, patronymic, registration, passport, car_id) value(?,?,?,?,?,?,?)";
    private static final String UPDATECAR = "insert into car (id, platen_number, marka, model, color, type) value(?,?,?,?,?,?)";
    private static final String UPDATELAST = "SELECT id FROM car ORDER BY id DESC LIMIT 1";
    private static final String DELETE = "DELETE personcar, car FROM personcar INNER JOIN car ON personcar.car_id = car.id WHERE personcar.surname = ?";
    private static final String UPCHANGING = "update personcar set registration = ? where surname = ? ";

    public void select() {
        try {
            ConnectionDB connectionDB = new ConnectionDB();
            Statement statement = connectionDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECTTWO);

            while (resultSet.next()) {

                PersonCar personCar = new PersonCar();
                Car car = new Car();
                personCar.setSurname(resultSet.getString("surname"));
                personCar.setName(resultSet.getString("name"));
                personCar.setPatronymic(resultSet.getString("patronymic"));
                personCar.setRegistration(resultSet.getString("registration"));
                personCar.setPassport(resultSet.getString("passport"));
                car.setMarka(resultSet.getString("marka"));
                car.setModel(resultSet.getString("model"));
                car.setColor(resultSet.getString("color"));
                car.setType(resultSet.getString("type"));
                car.setPlaten_number(resultSet.getString("platen_number"));
                System.out.println(personCar);
                System.out.println(car);
                System.out.println();

            }

            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertCar(Car car) {
        try {
            ConnectionDB connectionDB = new ConnectionDB();
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(UPDATECAR);

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, car.getPlaten_number());
            preparedStatement.setString(3, car.getMarka());
            preparedStatement.setString(4, car.getModel());
            preparedStatement.setString(5, car.getColor());
            preparedStatement.setString(6, car.getType());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertPersonCar(PersonCar personCar) {

        try {
            Car carIndex = new Car();
            ConnectionDB connectionDB = new ConnectionDB();
            Statement statement = connectionDB.getConnection().createStatement();
            PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(UPDATEPERSON);
            ResultSet resultSet = statement.executeQuery(UPDATELAST);
            while (resultSet.next()) {
                carIndex.setIndex(resultSet.getInt("id"));
            }
            int index = carIndex.getIndex();

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, personCar.getSurname());
            preparedStatement.setString(3, personCar.getName());
            preparedStatement.setString(4, personCar.getPatronymic());
            preparedStatement.setString(5, personCar.getRegistration());
            preparedStatement.setString(6, personCar.getPassport());
            preparedStatement.setInt(7, index);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            resultSet.close();
            statement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public Car Car() {

        Scanner scannerMarka = new Scanner(System.in);
        Scanner scannerModel = new Scanner(System.in);
        Scanner scannerColor = new Scanner(System.in);
        Scanner scannerType = new Scanner(System.in);
        Scanner scannerPlaten_number = new Scanner(System.in);

        System.out.println("Введите марку :");
        String marka = scannerMarka.nextLine();
        System.out.println("Введите модель :");
        String model = scannerModel.nextLine();
        System.out.println("Введите цвет :");
        String color = scannerColor.nextLine();
        System.out.println("Введите тип модели :");
        String type = scannerType.nextLine();
        System.out.println("Введите номер автомобиля :");
        String platen_number = scannerPlaten_number.nextLine();

        return new Car(marka, model, color, type, platen_number);
    }

    public PersonCar PersonCar() {

        Scanner scannerSurname = new Scanner(System.in);
        Scanner scannerName = new Scanner(System.in);
        Scanner scannerPatronymic = new Scanner(System.in);
        Scanner scannerRegistration = new Scanner(System.in);
        Scanner scannerPassword = new Scanner(System.in);

        System.out.println("Введите фамилию :");
        String surname = scannerSurname.nextLine();
        System.out.println("Введите имя :");
        String name = scannerName.nextLine();
        System.out.println("Введите отчество :");
        String patronymic = scannerPatronymic.nextLine();
        System.out.println("Введите регестрацию :");
        String registration = scannerRegistration.nextLine();
        System.out.println("Введите паспортные данные :");
        String password = scannerPassword.nextLine();

        return new PersonCar(surname, name, patronymic, registration, password);
    }

    public void Delete() {
        ArrayList<PersonCar> personCarArrayList = new ArrayList<>();
        System.out.println("Введите фамилию владельца которого хотите удалить с базы данных!");
        Scanner scannerDelete = new Scanner(System.in);
        String str = scannerDelete.nextLine();
        Search.selectPeople(personCarArrayList);

        if (Search.findSurname(personCarArrayList, str)) {
            try {
                ConnectionDB connectionDB = new ConnectionDB();
                PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(DELETE);
                preparedStatement.setString(1, str);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Объект удален успешно!");
        } else {
            System.out.println("С такой фамилией данных в базе не существует!");
        }
    }

    public void Find() {
        ArrayList<PersonCar> personCarArrayList = new ArrayList<>();
        System.out.println("Введите фамилию владельца которого хотите найти в базы данных!");
        Scanner scannerFind = new Scanner(System.in);
        String str = scannerFind.nextLine();
        Search.selectPeople(personCarArrayList);
        if (Search.findSurname(personCarArrayList, str)) {
            Search.findingPrint(personCarArrayList, str);
        } else {
            System.out.println("Под такой фамилией не чего не найдено!");
        }
    }

    public void Changing() {
        ArrayList<PersonCar> personCarArrayList = new ArrayList<>();
        System.out.println("Введите фамилию владельца которого хотите изменить!");
        Scanner scannerChanging = new Scanner(System.in);
        String str = scannerChanging.nextLine();
        Search.selectPeople(personCarArrayList);

        if (Search.findSurname(personCarArrayList, str)) {
            Search.findingPrint(personCarArrayList, str);

            System.out.println("Введите новую регистрацию :");
            Scanner scannerNewRegistration = new Scanner(System.in);
            String str1 = scannerNewRegistration.nextLine();

            try {
                ConnectionDB connectionDB = new ConnectionDB();
                PreparedStatement preparedStatement = connectionDB.getConnection().prepareStatement(UPCHANGING);

                preparedStatement.setString(1, str1);
                preparedStatement.setString(2, str);

                preparedStatement.executeUpdate();
                preparedStatement.close();

            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Изменение прошло успешно!");

        } else {
            System.out.println("Под такой фамилией не чего не найдено!");
        }
    }
}



