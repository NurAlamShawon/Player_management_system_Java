package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayingTeam extends Color{
    private static String matchAgainst;
    private static String venue;
    private static String matchType;
    private static String date;
    private static String name;
    private static int age;
    private static int contact;
    private static String height;
    private static String playerType;
    private static String userType;
    private static int id;

    static String [] menu = {"--pteam-r", "--pteam-c", "--back"};

    public PlayingTeam (int id, String userType){
        this.id = id;
        this.userType = userType;
        start();
    }

    private static void start() {
        System.out.print("please type " + ANSI_RED + "--pteam-all" + ANSI_RESET + " to Check All Feature: ");
        Scanner sc = new Scanner(System.in);
        String list = sc.nextLine();
        if (list.equals("--pteam-all")){
            main(null);
        }else{
            start();
        }
    }

    private static void readMatch(){
        ArrayList<String> data = new ArrayList();
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("Id", "Match Against", "Venue", "Match Type", "Match Date");
        try{
            DataInputStream is = new DataInputStream(new FileInputStream("match.txt"));
            while(is.available() != 0){
                data.add(is.readUTF());
            }
            for(int i = 0; i<data.size(); i++){
                String u = data.get(i);
                String[] match = u.split(",");
                st.addRow(match[0], match[1], match[2], match[3], match[4]);
            }
            st.print();
            is.close();
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void readPlayer(){
        ArrayList<String> data = new ArrayList();
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("id", "Name", "Username", "Email", "Password", "Age", "Height", "Phone Number", "player Type", "User Type");
        try{
            DataInputStream is = new DataInputStream(new FileInputStream("player.txt"));
            while(is.available() != 0){
                data.add(is.readUTF());
            }
            for(int i = 0; i<data.size(); i++){
                String u = data.get(i);
                String[] users = u.split(",");
                st.addRow(users[0], users[1], users[2], users[3], users[4], users[5], users[6], users[7], users[8], users[9]);
            }
            st.print();
            is.close();
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void write(int id, int pId){
        ArrayList<String> data = new ArrayList();
        ArrayList<String> pdata = new ArrayList();
        try{
            DataInputStream is = new DataInputStream(new FileInputStream("match.txt"));
            while(is.available() != 0){
                data.add(is.readUTF());
            }
            for(int i = 0; i<data.size(); i++){
                String u = data.get(i);
                String[] match = u.split(",");
                int newId = Integer.parseInt(match[0]);
                if (newId == id){
                    matchAgainst = match[1];
                    venue = match[2];
                    matchType = match[3];
                    date = match[4];
                }
            }
            is.close();

            DataInputStream tx = new DataInputStream(new FileInputStream("player.txt"));
            while(tx.available() != 0){
                pdata.add(tx.readUTF());
            }
            for (int i = 0; i < data.size(); i++){
                String u = pdata.get(i);
                String[] users = u.split(",");
                int newId = Integer.parseInt(users[0]);
                if (newId == pId){
                    name = users[1];
                    age = Integer.parseInt(users[5]);
                    height= users[6];
                    contact = Integer.parseInt(users[7]);
                    playerType = users[8];
                }
            }
            tx.close();

        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writePlayingTeam() {
        System.out.println("All " + ANSI_BLUE + "Match " + ANSI_RESET + "List");
        readMatch();
        System.out.println("All " + ANSI_BLUE + "Player " + ANSI_RESET +"List");
        readPlayer();
        Scanner sc = new Scanner(System.in);
        System.out.print("Please Select " + ANSI_BLUE + "Match Id " + ANSI_RESET + "List : ");
        int id = sc.nextInt();
        System.out.print("Please Select " + ANSI_BLUE + "Player Id " + ANSI_RESET + "List : ");
        int pId = sc.nextInt();
        write(id, pId);

        try{
            DataOutputStream os = new DataOutputStream(new FileOutputStream("playingTeam.txt", true));
            String str = name + ',' + age + ',' + matchAgainst + ',' + venue + ',' + matchType + ',' + height + ',' + contact + ',' + playerType + ',' + date;
            os.writeUTF(str);
            os.close();
            main(null);
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void readPlayingTeam(){
        ArrayList <String> data = new ArrayList<String>();
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("Name", "Age", "Match Against", "Venue", "Match Type", "Height", "Phone Number", "player Type", "Date");
        try{
            DataInputStream is = new DataInputStream(new FileInputStream("playingTeam.txt"));
            while(is.available() != 0){
                data.add(is.readUTF());
            }
            for(int i = 0; i<data.size(); i++){
                String u = data.get(i);
                String[] users = u.split(",");
                st.addRow(users[0], users[1], users[2], users[3], users[4], users[5], users[6], users[7], users[8]);
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

    public static void main(String[] args) {
        for (int i = 0; i < menu.length; i++) {
            if (i % 3 == 0){
                System.out.println();
            }
            System.out.print(ANSI_RED + "\t" + menu[i] + ANSI_RESET);
        }
        System.out.println();
        System.out.println();
        System.out.print("please type to Check All Feature: ");
        Scanner sc = new Scanner(System.in);
        String cMenu = sc.nextLine();
        if (cMenu.equals("--pteam-r")){
            readPlayingTeam();
        }else if (cMenu.equals("--pteam-c")){
            if (userType.equals("admin")){
                writePlayingTeam();
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
        }
    }
}
