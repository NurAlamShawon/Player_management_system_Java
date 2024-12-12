package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Coach extends Color{
    static String [] menu = {"--coach-r", "--coach-c", "--back"};
    static ArrayList<String> data = new ArrayList();
    private static int id;
    private static String userType;
    public Coach(int id, String userType){
        this.id = id;
        this.userType = userType;
        start();
    }
    private static void start() {
        System.out.print("please type " + ANSI_RED + "--coach-all" + ANSI_RESET + " to Check All Feature: ");
        Scanner sc = new Scanner(System.in);
        String list = sc.nextLine();
        if (list.equals("--coach-all")){
            main(null);
        }else{
            start();
        }
        main(null);
    }

    private static void write(String str) {
        try{
            DataOutputStream os = new DataOutputStream(new FileOutputStream("coach.txt", true));
            os.writeUTF(str);
            os.close();
            main(null);

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readCoach(){
        ArrayList <String> userItems = new ArrayList();
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("Id", "Name", "Username", "Email", "Password", "Phone Number", "Coach Type", "User Type");
        try{
            DataInputStream is = new DataInputStream(new FileInputStream("coach.txt"));
            while(is.available() != 0){
                userItems.add(is.readUTF());
            }
            for(int i = 0; i<userItems.size(); i++){
                String u = userItems.get(i);
                String[] users = u.split(",");
                st.addRow(users[0], users[1], users[2], users[3], users[4], users[5], users[6], users[7]);
            }
            st.print();
            is.close();
            main(null);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    private static void writeCoach() {
        Register register = new Register();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(" Enter Name => ");
            String name = scanner.nextLine();
            register.setName(name);

            System.out.print(" Enter Username => ");
            String userName = scanner.nextLine();
            register.setUserName(userName);

            System.out.print(" Enter email => ");
            String email = scanner.nextLine();
            register.setEmail(email);

            System.out.print(" Enter password => ");
            String password = scanner.nextLine();
            register.setPassword(password);

            System.out.print(" Enter PhoneNo => ");
            String pNumber = scanner.nextLine();
            register.setPhoneNo(pNumber);

            System.out.print(" Enter Coach Type => ");
            String coachType = scanner.nextLine();

            System.out.print(" Enter user Type => ");
            String userType = scanner.nextLine();
            register.setUserType(userType);

            int size = data.size() + 1;

            String str =  String.valueOf(size) + ',' + register.getName() + ',' + register.getUserName() + ',' + register.getEmail() + ',' +
                    register.getPassword() + ',' + register.getPhoneNo() + ',' + coachType +',' + register.getUserType();
            write(str);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < menu.length; i++) {
            if (i % 3 == 0){
                System.out.println();
            }
            System.out.print(ANSI_RED + "\t" + menu[i] + ANSI_RESET);
        }
        System.out.println();
        System.out.println();
        System.out.print("please type to Check All Feature: ");
        String cMenu = sc.nextLine();
        if (cMenu.equals("--coach-r")){
            readCoach();
        }else if (cMenu.equals("--coach-c")){
            writeCoach();
        }else if(cMenu.equals("--back")){
            if (userType.equals("admin")){
                Admin ad = new Admin(id, userType);
            }else if (userType.equals("coach")){
                CoachMain ad = new CoachMain(id, userType);
            }else if (userType.equals("player")){
                PlayerMain ad = new PlayerMain(id, userType);
            }
        }else {
            main(null);
        }


    }

}
