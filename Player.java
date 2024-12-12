package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Color {
    private int age;
    private String height;
    private String PlayerType;
    private static int id;
    private static String userType;
    static ArrayList<String> data = new ArrayList();
    private int setAge(int age){
        return this.age = age;
    }
    private String setHeight(String height){
        return  this.height = height;
    }
    private String setPlayerType(String PlayerType){
        return  this.PlayerType = PlayerType;
    }
    static String [] menu = {"--player-r", "--player-c", "--back"};

    public Player (int id, String userType){
        this.id = id;
        this.userType = userType;
        start();
    }

    private void start() {
        System.out.print("please type " + ANSI_RED + "--player-all" + ANSI_RESET + " to Check All Feature: ");
        Scanner sc = new Scanner(System.in);
        String list = sc.nextLine();
        if (list.equals("--player-all")){
            main(null);
        }else{
            start();
        }
    }
    private void write(String str) {
        try{
            DataOutputStream os = new DataOutputStream(new FileOutputStream("player.txt", true));
            os.writeUTF(str);
            os.close();
            main(null);

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readPlayer(){
        ArrayList<String> userData = new ArrayList();
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("id", "Name", "Username", "Email", "Password", "Age", "Height", "Phone Number", "player Type", "User Type");
        try{
            DataInputStream is = new DataInputStream(new FileInputStream("player.txt"));
            while(is.available() != 0){
                userData.add(is.readUTF());
            }
            for(int i = 0; i<userData.size(); i++){
                String u = userData.get(i);
                String[] users = u.split(",");
                st.addRow(users[0], users[1], users[2], users[3], users[4], users[5], users[6], users[7], users[8], users[9]);
            }
            st.print();
            is.close();
            main(null);
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    private void writePlayer() {
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

            System.out.print(" Enter player Type => ");
            String playerType = scanner.nextLine();
            setPlayerType(playerType);

            System.out.print(" Enter player Age => ");
            int playerAge = Integer.parseInt(scanner.nextLine());
            setAge(playerAge);

            System.out.print(" Enter player height => ");
            String height = scanner.nextLine();
            setHeight(height);

            System.out.print(" Enter user Type => ");
            String userType = scanner.nextLine();
            register.setUserType(userType);

            int size = data.size() + 1;


            String str = String.valueOf(size) + ',' + register.getName() + ',' + register.getUserName() + ',' + register.getEmail() + ',' +
                    register.getPassword() + ',' + age + ',' + height + ',' + register.getPhoneNo() + ',' + playerType +',' + register.getUserType();
            write(str);
        }
    }

    public void main(String[] args) {
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
        if (cMenu.equals("--player-r")){
            readPlayer();
        }else if (cMenu.equals("--player-c")){
            if (userType.equals("admin")){
                writePlayer();
            }else {
                System.out.println(ANSI_RED + "You don't have access to this option");
                main(null);
            }
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
