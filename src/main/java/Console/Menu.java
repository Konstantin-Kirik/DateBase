package Console;
import java.util.Scanner;

public class Menu {

    public static void MenuBase() {

        Method method = new Method();
        int flag = 0;
        do {
            System.out.println("Выберите вариант из действий:\n"
                    + "1.Показать базу данных \n"
                    + "2.Добавить в базу данных автомобиль и владельца\n"
                    + "3.Удалить из базы данных\n"
                    + "4.Поиск по базе данных\n"
                    + "5.Редактировать данные");
            System.out.println("0 : Выход");

            Scanner in = new Scanner(System.in);
            flag = in.nextInt();

            switch (flag) {
                case 1 -> method.select();
                case 2 -> {
                    method.insertCar(method.Car());
                    method.insertPersonCar(method.PersonCar());
                }
                case 3 -> method.Delete();
                case 4 -> method.Find();
                case 5 -> method.Changing();

                default -> {
                    if (flag != 0) {
                        System.out.println("Такого пункта в меню не существует, выберите из предложеных!");
                    } else {
                        System.out.println("До скорого!");
                    }
                }
            }
        } while (flag != 0);
    }
}
