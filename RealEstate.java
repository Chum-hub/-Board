import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Scanner;

public class RealEstate {

    public static final String Tel_Aviv = "Tel-Aviv";
    public static final String Haifa = "Haifa";
    public static final String Nazaret = "Nazaret";
    public static final String Beer_Sheva = "Beer Sheva";
    public static final String Ashkelon = "Ashkelon";
    public static final String Ashdod = "Ashdod";
    public static final String Netania = "Netania";
    public static final String Ramla = "Ramla";
    public static final String Holon = "Holon";
    public static final String Ierusalem = "Ierusalem";
    private final Scanner sc = new Scanner(System.in);

    RealEstate() {

    }

    private User[] users = {
            new User("Leon", "w1234_567%", "0548908311", true),
            new User("Vovan", "w1234567%", "0548908312", false),
            new User("Vova", "w123467%", "0548908313", true),
            new User("Simon", "w134567%", "0548908314", false),
            new User("Valera", "w2356%", "0548908315", true)
    };


    private final City[] cities = {
            new City(Tel_Aviv, ConstsClass.CENTER, new String[]{ConstsClass.AKIKAYON, ConstsClass.BAR_COHBA, ConstsClass.BIALIK, ConstsClass.ASHITA}),
            new City(Haifa, ConstsClass.NORTH, new String[]{ConstsClass.TRUMPLEDOR, ConstsClass.BAR_COHBA, ConstsClass.BIALIK, ConstsClass.DAVID_AMELEKH}),
            new City(Nazaret, ConstsClass.NORTH, new String[]{ConstsClass.ASHITA, ConstsClass.BAR_COHBA, ConstsClass.BIALIK, ConstsClass.DAVID_AMELEKH}),
            new City(Ramla, ConstsClass.CENTER, new String[]{ConstsClass.ASHITA, ConstsClass.BAR_COHBA, ConstsClass.BIALIK, ConstsClass.DAVID_AMELEKH}),
            new City(Holon, ConstsClass.CENTER, new String[]{ConstsClass.TRUMPLEDOR, ConstsClass.BAR_COHBA, ConstsClass.ASHITA, ConstsClass.DAVID_AMELEKH}),
            new City(Ierusalem, ConstsClass.NEGEV, new String[]{ConstsClass.TRUMPLEDOR, ConstsClass.BAR_COHBA, ConstsClass.BIALIK, ConstsClass.DAVID_AMELEKH}),
            new City(Beer_Sheva, ConstsClass.NEGEV, new String[]{ConstsClass.TRUMPLEDOR, ConstsClass.ASHITA, ConstsClass.BIALIK, ConstsClass.DAVID_AMELEKH}),
            new City(Ashdod, ConstsClass.SOUTH, new String[]{ConstsClass.TRUMPLEDOR, ConstsClass.BAR_COHBA, ConstsClass.BIALIK, ConstsClass.DAVID_AMELEKH}),
            new City(Ashkelon, ConstsClass.SOUTH, new String[]{ConstsClass.TRUMPLEDOR, ConstsClass.BAR_COHBA, ConstsClass.BIALIK, ConstsClass.DAVID_AMELEKH}),
            new City(Netania, ConstsClass.SARON, new String[]{ConstsClass.TRUMPLEDOR, ConstsClass.BAR_COHBA, ConstsClass.BIALIK, ConstsClass.DAVID_AMELEKH})
    };

    private Property[] properties = {
            new Property(cities[0], "Bar-Cohba", 5, 700000, 1, false, 15, 3, users[3]),
            new Property(cities[1], "Ashita", 7, 400000, 2, false, 10, 7, users[2]),
            new Property(cities[2], "Bialik", 2, 300000, 2, false, 3, 7, users[3]),
            new Property(cities[3], "David amelekh", 1, 10000, 2, true, 19, 7, users[4])
    };


    public void createUser() {
        User user = new User();
        createName(user);
        createPassword(user);
        createNumberTel(user);
        isBroker(user);

        User[] tmp = new User[users.length + 1];
        for (int i = 0; i < users.length; i++) {
            tmp[i] = users[i];
        }
        tmp[tmp.length - 1] = user;
        users = tmp;
    }

    public void userLogin() {
        User user = checkInputUser(inputNumberTel(), inputPassword());
        if (user == null) {
            System.out.println("This user is not exist");
            Main.userMenu1();
        } else {
            userMenu2(user);
        }
    }

    private void userMenu2(User user) {
        System.out.println("What action do you want to choose?: '1' - To publish, '2' - To remove adv,");
        System.out.println("'3' - Display all, '4' - Display what I posted, '5' - Search by parameters, '6' - Exit to main menu");
        switch (sc.nextInt()) {
            case 1: {
                postNewProperty(user);
                userMenu2(user);
                break;
            }
            case 2: {
                removeProperty(user);
                userMenu2(user);
                break;
            }
            case 3: {
                printAllProperties();
                userMenu2(user);
                break;
            }
            case 4: {
                printProperties(user);
                userMenu2(user);
                break;
            }
            case 5: {
                search();
                userMenu2(user);
                break;
            }
            case 6: {
                Main.userMenu1();
                break;
            }
        }
    }

    private Property[] search() {
        return searchingOfProps(PropForRent999(), chooseProperty(), chooseNumberOfRooms(), chooseMinPrice(), chooseMaxPrice());
    }

    private Property[] searchingOfProps(int rent, int type, int rooms, int min, int max) {
        Property[] newProps = new Property[0];
        boolean isRent = false;
        if (rent == 1) {
            isRent = true;
        }
        for (Property prop : properties) {
            if ((rent == -999 || prop.getRent() == isRent) &&
                    (type == -999 || prop.getType() == type) &&
                    (rooms == -999 || prop.getRooms() == rooms) &&
                    (min == -999 || prop.getPrice() >= min) &&
                    (max == -999 || prop.getPrice() <= max)
            ) {
                Property[] tmp = new Property[newProps.length + 1];
                for (int i = 0; i < newProps.length; i++) {
                    tmp[i] = newProps[i];
                }
                tmp[tmp.length - 1] = prop;
                newProps = tmp;
            }
        }
        return newProps;
    }

    private int chooseMaxPrice() {
        System.out.println("Choose the maximal price: ");
        return sc.nextInt();
    }

    private int chooseMinPrice() {
        System.out.println("Choose the minimal price: ");
        return sc.nextInt();
    }

    private int PropForRent999() {
        System.out.println("Is property to Rent or Sell?: 1 - Rent, 2 - Sell");
        int a;
        do {
            a = sc.nextInt();
        } while (a != 1 && a != 2 && a != -999);
        if (a == 1) {
            return 1;
        } else if (a == 2) {
            return 2;
        } else {
            return -999;
        }
    }

    private void printProperties(User user) {
        for (Property prop : user.getPublications()) {
            System.out.println(prop);
        }

    }

    private void printAllProperties() {
        for (User user : users) {
            System.out.println("Here's list of publications of user " + user.getName());
            for (int j = 0; j < user.getPublications().length; j++) {
                System.out.println(user.getPublications()[j]);
            }
        }
    }

    private void removeProperty(User user) {
        int publication;
        if (!isUserHavePublished(user)) {
            return;
        }
        displayPublications(user);
        publication = choosePublication(user);
        removePublication(user, publication);
    }

    private void removePublication(User user, int n) {
        Property[] newProps = new Property[user.getPublications().length];
        for (int i = 0; i < user.getPublications().length; i++) {
            if (i == n) {
                continue;
            }
            newProps[i] = user.getPublications()[i];
        }
        user.setPublications(newProps);
        System.out.println("Deleted");
    }

    private int choosePublication(User user) {
        System.out.println("Choose the number of publication");
        return sc.nextInt();
    }

    private void displayPublications(User user) {
        int i = 0;
        System.out.println("Here's your publications: ");
        for (Property prop : user.getPublications()) {
            System.out.println(prop + " - " + i);
            i++;
        }
    }

    private boolean isUserHavePublished(User user) {
        if (user.getPublications().length == 0) {
            System.out.println("Error: You have no posts");
            return false;
        } else {
            return true;
        }
    }

    private boolean postNewProperty(User user) {
        int type;
        int price;
        int rooms;
        boolean isRent;
        int numberOfBuilding;
        int numberOfFloor = 0;
        City city;
        String inputUser;
        String inputUserStreet;
        Scanner scanner = new Scanner(System.in);
        if (isUserPossibleToPublic(user)) {
            displayCity();
            System.out.print("Choose the city: ");
            inputUser = scanner.nextLine();
            if (inputUserCityCheck(inputUser) == null) {
                return false;
            } else {
                city = inputUserCityCheck(inputUser);
                inputUserStreetDisplay(inputUser);
            }
            System.out.print("Choose the street: ");
            inputUserStreet = scanner.nextLine();
            if (inputUserStreetCheck(inputUserStreet, city) == null) {
                return false;
            } else {
                type = chooseProperty();
                if (type < 1 || type > 3) {
                    System.out.println("Error: This variant is nor exist");
                    return false;
                }
                if (type == 1) numberOfFloor = chooseNumberOfFloor();
                rooms = chooseNumberOfRooms();
                numberOfBuilding = chooseNumberOfBuilding();
                isRent = isPropForRent();
                price = choosePrice();
            }
            Property prop = new Property(city, inputUserStreet, rooms, price, type, isRent, numberOfBuilding, numberOfFloor, user);
            Property[] tmp = new Property[properties.length + 1];
            for (int i = 0; i < properties.length; i++) {
                tmp[i] = properties[i];
            }
            tmp[tmp.length - 1] = prop;
            properties = tmp;
        }
        return true;
    }

    private int choosePrice() {
        System.out.println("Choose the price: ");
        return sc.nextInt();
    }

    private boolean isPropForRent() {
        System.out.println("Is property to Rent or Sell?: 1 - Rent, 2 - Sell");
        int a;
        do {
            a = sc.nextInt();
        } while (a != 1 && a != 2);
        if (a == 1) {
            return true;
        } else {
            return false;
        }
    }

    private int chooseNumberOfBuilding() {
        System.out.println("Choose number of building: ");
        return sc.nextInt();
    }

    private int chooseNumberOfRooms() {
        System.out.println("Choose number of rooms: ");
        return sc.nextInt();
    }

    private int chooseNumberOfFloor() {
        System.out.println("Choose number of floor: ");
        return sc.nextInt();
    }

    private int chooseProperty() {
        int a;
        System.out.print("Choose the type of Property: '1' - simple apartment in an apartment building, '2' - penthouse in an apartment building," +
                " '3' - private residential building: ");
        a = sc.nextInt();
        return a;
    }

    private City inputUserStreetCheck(String inputUserStreet, City city) {
        for (int i = 0; i < city.getStreets().length; i++) {
            if (inputUserStreet.matches(city.getStreets()[i])) return city;
        }
        System.out.println("This Street is not exist on system");
        return null;
    }


    private void inputUserStreetDisplay(String inputUser) {
        for (City city : cities) {
            if (inputUser.matches(city.getCity())) {
                for (int i = 0; i < city.getStreets().length; i++) {
                    System.out.println(city.getStreets()[i]);
                }
                break;
            }
        }
    }

    private City inputUserCityCheck(String inputUser) {
        for (City city : cities) {
            if (inputUser.matches(city.getCity())) {
                return city;
            }
        }
        System.out.println("This City is not exist on system");
        return null;
    }

    private void displayCity() {
        System.out.println("All Cities allowed to choose:");
        for (City city : cities) {
            System.out.println(city.getCity() + "");
        }
    }

    private boolean isUserPossibleToPublic(User user) {
        if (user.getPublications().length < 2 && !user.getBroker()) {
            return true;
        } else return user.getPublications().length < 5 && user.getBroker();
    }

    private User checkInputUser(String numberTel, String inputPassword) {
        for (User user : users) {
            if (numberTel.equals(user.getPhoneNumber()) && inputPassword.equals(user.getPassword())) return user;
        }
        return null;
    }

    private String inputNumberTel() {
        String numberTel;
        do {
            System.out.println("Enter your phone number: ");
            numberTel = sc.nextLine();
        } while (!isNumberCorrect(numberTel));
        return numberTel;
    }

    private String inputPassword() {
        String password;
        do {
            System.out.println("Enter your password: ");
            password = sc.nextLine();
        } while (!isPasswordCorrect(password));
        return password;
    }

    private void isBroker(User user) {
        int inputBroker;
        do {
            System.out.println("Are you a broker? (if yes type '1', if no type '0'): ");
            inputBroker = sc.nextInt();
        } while (!isBrokerCorrect(inputBroker));
        user.setBroker(inputBroker);
    }

    private boolean isBrokerCorrect(int inputBroker) {
        if (inputBroker == 0) {
            return true;
        } else if (inputBroker == 1) {
            return true;
        } else {
            System.out.println("Error: Type '1' or '0'");
            return false;
        }
    }

    private void createNumberTel(User user) {
        String numberTel;
        do {
            System.out.println("Enter your phone number: ");
            numberTel = sc.nextLine();
        } while (!isNumberCorrect(numberTel));
        user.setPhoneNumber(numberTel);
    }

    private boolean isNumberCorrect(String numberTel) {
        if (numberTel.matches("[0-9]+") && numberTel.length() == 10 && numberTel.startsWith("05")) {
            return true;
        }
        System.out.println("Your phone number is invalid");
        return false;
    }

    private void createPassword(User user) {
        String password;
        do {
            System.out.println("Enter your password: ");
            password = sc.nextLine();
        } while (!isPasswordCorrect(password));
        user.setPassword(password);
    }

    private boolean isPasswordCorrect(String password) {
        String symbol;
        if (password.length() >= 4) {
            if (password.contains("$") || password.contains("%")) {
                for (int i = 0; i < 9; i++) {
                    symbol = i + "";
                    if (password.contains(symbol)) {
                        return true;
                    }
                }
            }
        }
        System.out.println("Your password is too weak");
        return false;
    }

    private void createName(User user) {
        String name;
        do {
            System.out.println("Enter your user name: ");
            name = sc.nextLine();
        } while (!isNameCorrect(name));
        user.setName(name);
    }

    private boolean isNameCorrect(String name) {

        for (User user : users) {
            if (user.getName().equals(name)) {
                System.out.println("Your name is occupied, please choose another one");
                return false;
            }
        }
        return true;
    }
}
