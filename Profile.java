package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Profile extends Color{
    private static int id ;
    private static String userType;
    public Profile(int id, String userType){
        this.id = id;
        this.userType = userType;
        start();
    }

    private void start() {
        main(null);
    }
    private static void main(String[] args) {
        ArrayList<String> data = new ArrayList();
        if (userType.equals("admin")){
            String name = null;
            String userName = null;
            String emailAddress = null;
            String phoneNumber = null;
            String userType = null;
            try {
                DataInputStream tx = new DataInputStream(new FileInputStream("registration.txt"));
                while(tx.available() != 0){
                    data.add(tx.readUTF());
                }
                for (int i = 0; i < data.size(); i++){
                    String u = data.get(i);
                    String[] users = u.split(",");
                    int newId = Integer.parseInt(users[0]);
                    if (newId == id){
                        name = users[1];
                        userName = users[2];
                        emailAddress = users[3];
                        phoneNumber = users[5];
                        userType = users[6];
                    }
                }
                String str = "Name: " + ANSI_BLUE + name + ANSI_RESET + "\tUsername: " + ANSI_BLUE + userName + ANSI_RESET + "\tEmail: " + ANSI_BLUE + emailAddress + ANSI_RESET +
                        "\nPhone Number: " + ANSI_BLUE + phoneNumber + ANSI_RESET +  "\tUser Type: " + ANSI_BLUE + userType + ANSI_RESET;
                System.out.println(str);
                tx.close();
                Admin ad = new Admin(id, userType);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (userType.equals("coach")){
            String name = null;
            String userName = null;
            String emailAddress = null;
            String phoneNumber = null;
            String userType = null;
            String currentPosition =  null;
            try {
                DataInputStream tx = new DataInputStream(new FileInputStream("coach.txt"));
                while(tx.available() != 0){
                    data.add(tx.readUTF());
                }
                for (int i = 0; i < data.size(); i++){
                    String u = data.get(i);
                    String[] users = u.split(",");
                    int newId = Integer.parseInt(users[0]);
                    if (newId == id){
                        name = users[1];
                        userName = users[2];
                        emailAddress = users[3];
                        phoneNumber = users[5];
                        currentPosition = users[6];
                        userType = users[7];
                    }
                }
                String str = "Name: " + ANSI_BLUE + name + ANSI_RESET + "\tUsername: " + ANSI_BLUE + userName + ANSI_RESET + "\tEmail: " + ANSI_BLUE + emailAddress + ANSI_RESET +
                        "\nPhone Number: " + ANSI_BLUE + phoneNumber + ANSI_RESET + "\tCurrent Position :" + ANSI_BLUE + currentPosition + ANSI_RESET + "\tUser Type: " + ANSI_BLUE + userType + ANSI_RESET;
                System.out.println(str);
                tx.close();
                CoachMain ad = new CoachMain(id, userType);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (userType.equals("player")){
            String name = null;
            String userName = null;
            String emailAddress = null;
            String phoneNumber = null;
            String userType = null;
            String height = null;
            String PlayerType = null;
            int age = 0;
            try {
                DataInputStream tx = new DataInputStream(new FileInputStream("player.txt"));
                while(tx.available() != 0){
                    data.add(tx.readUTF());
                }
                for (int i = 0; i < data.size(); i++){
                    String u = data.get(i);
                    String[] users = u.split(",");
                    int newId = Integer.parseInt(users[0]);
                    if (newId == id){
                        name = users[1];
                        userName = users[2];
                        emailAddress = users[3];
                        age = Integer.parseInt(users[5]);
                        height = users[6];
                        phoneNumber = users[7];
                        PlayerType = users[8];
                        userType = users[9];
                    }
                }
                String str = "Name: " + ANSI_BLUE + name + ANSI_RESET + "\tUsername: " + ANSI_BLUE + userName + ANSI_RESET + "\tEmail: " + ANSI_BLUE + emailAddress + ANSI_RESET +
                        "\nPhone Number: " + ANSI_BLUE + phoneNumber + ANSI_RESET + "\tAge :" + ANSI_BLUE + age + ANSI_RESET + "\tHeight: " + ANSI_BLUE + height + ANSI_RESET +
                        "\nPlayer Type: " + ANSI_BLUE + PlayerType + ANSI_RESET  + "\tUser Type: " + ANSI_BLUE + userType + ANSI_RESET ;
                System.out.println(str);
                tx.close();
                CoachMain ad = new CoachMain(id, userType);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
