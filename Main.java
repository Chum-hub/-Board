import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        userMenu1();
    }

    public static void userMenu1(){
        RealEstate program = new RealEstate();
        Scanner sc = new Scanner(System.in);

        int input;
        do {
            System.out.println("Register an account - 1");
            System.out.println("Log in - 2");
            System.out.println("Close program - 3");
            input = sc.nextInt();
            if (input == 1){
                program.createUser();
            } else if (input == 2){
                program.userLogin();
            } else if (input == 3){
                return;
            }
        } while(input != 3);

    }
}
