package com.company;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class Login extends Color{
    static ArrayList<String> data = new ArrayList();
    private static String userName;
    private static String password;
    private static String userType;
    private static String file;
    public Login (String file, String userType) {
        this.file = file;
        this.userType = userType;
        main(null);
    }

    public static void readRegister() {
        try{
            DataInputStream is = new DataInputStream(new FileInputStream(file));
            while(is.available() != 0){
                data.add(is.readUTF());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        readRegister();
        Scanner sc = new Scanner(System.in);
        System.out.print("Please Enter your Username: ");
        userName = sc.nextLine();
        System.out.print("Please Enter your Password: ");
        password = sc.nextLine();

        for(int i = 0; i<data.size(); i++){
            String u = data.get(i);
            String[] users = u.split(",");
            if (userType.equals("admin")){
                if (userName.equals(users[2]) && password.equals(users[4])){
                    System.out.println(ANSI_GREEN  + "You have been logged in" + ANSI_RESET);
                    Admin ad = new Admin(Integer.parseInt(users[0]), users[6]);
                }else{
                    System.out.println("Username or Password doesn't Match");
                    main(null);
                }
            }else if (userType.equals("coach")){
                if (userName.equals(users[2]) && password.equals(users[4])){
                    System.out.println(ANSI_GREEN  + "You have been logged in" + ANSI_RESET);
                    CoachMain cm = new CoachMain(Integer.parseInt(users[0]), users[7]);
                    main(null);
                }else{
                    System.out.println("Username or Password doesn't Match");
                }
            }else if (userType.equals("player")){
                if (userName.equals(users[2]) && password.equals(users[4])){
                    System.out.println(ANSI_GREEN  + "You have been logged in" + ANSI_RESET);
                    PlayerMain pm = new PlayerMain(Integer.parseInt(users[0]), users[9]);
                    main(null);
                }else{
                    System.out.println("Username or Password doesn't Match");
                }
            }

        }
    }
}
