package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Registration {
    static ArrayList <String> user = new ArrayList();
    public static void writeRegister(String str) {
        try{
            DataOutputStream os = new DataOutputStream(new FileOutputStream("registration.txt", true));
            os.writeUTF(str);
            os.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void readReg(){
        try{
            DataInputStream os = new DataInputStream(new FileInputStream("registration.txt"));
            while(os.available() != 0){
                user.add(os.readUTF());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
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

            System.out.print(" Enter user Type => ");
            String userType = scanner.nextLine();
            register.setUserType(userType);
            readReg();
            int size = user.size() + 1;


            String str = String.valueOf(size) + ',' + register.getName() + ',' + register.getUserName() + ',' +
                    register.getEmail() + ',' + register.getPassword() + ',' + register.getPhoneNo() +
                    ',' + register.getUserType();

            writeRegister(str);
        }


    }
}
