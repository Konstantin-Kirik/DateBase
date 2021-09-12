package Console;

import Connection.ConnectionDB;
import Person.PersonCar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Search {

    private static final String SELECT = "select * from personcar ";

    public static void selectPeople(ArrayList<PersonCar> personCarArrayList) {
        try {
            ConnectionDB connectionDB = new ConnectionDB();
            Statement statement = connectionDB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT);

            while (resultSet.next()) {

                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                String patronymic = resultSet.getString("patronymic");
                String registration = resultSet.getString("registration");
                String passport = resultSet.getString("passport");
                PersonCar personCar = new PersonCar(surname, name, patronymic, registration, passport);
                personCarArrayList.add(personCar);

            }
        } catch (ClassNotFoundException | SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void findingPrint(ArrayList<PersonCar> personCarArrayList, String str){
        for (PersonCar personCar : personCarArrayList) {
            if (personCar.getSurname().equals(str)) {
                System.out.println(personCar.toString());
                break;
            }
        }
    }

    public static boolean findSurname(ArrayList<PersonCar> personCarArrayList, String str) {
        for (PersonCar personCar : personCarArrayList) {
            if (personCar.getSurname().equals(str)) {
                return true;
            }
        }
        return false;
    }
}



