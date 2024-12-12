package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Match extends Color {
    private static String userType;
    static String [] menu = {"--match-r", "--match-c", "--back"};
    private static int id;

    public Match (int id, String userType){
        this.id = id;
        this.userType = userType;
        start();
    }
    private static void start() {
        System.out.print("please type " + ANSI_RED + "--match-all" + ANSI_RESET + " to Check All Feature: ");
        Scanner sc = new Scanner(System.in);
        String list = sc.nextLine();
        if (list.equals("--match-all")){
            main(null);
        }else{
            start();
        }
    }

    private static void write(String str) {
        try{
            DataOutputStream os = new DataOutputStream(new FileOutputStream("match.txt", true));
            os.writeUTF(str);
            os.close();
            main(null);

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeMatch() {
         ArrayList<String> data = new ArrayList();
            try{
                DataInputStream os = new DataInputStream(new FileInputStream("match.txt"));
                os.readUTF(os);
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Match Against: ");
            String matchAgainst = scanner.nextLine();

            System.out.print("Venue: ");
            String venue = scanner.nextLine();

            System.out.print("Match Type: ");
            String matchType = scanner.nextLine();

            System.out.print("Match Date: ");
            String matchDate = scanner.nextLine();

            int size = data.size() + 1;

            String str =  String.valueOf(size) + ','  + matchAgainst + ',' + venue + ',' + matchType + ',' + matchDate;
            write(str);
        }
    }

    private static void readMatch(){
        ArrayList <String> matchData = new ArrayList();
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("Id", "Match Against", "Venue", "Match Type", "Match Date");
        try{
            DataInputStream is = new DataInputStream(new FileInputStream("match.txt"));
            while(is.available() != 0){
                matchData.add(is.readUTF());
            }
            for(int i = 0; i<matchData.size(); i++){
                String u = matchData.get(i);
                String[] match = u.split(",");
                st.addRow(match[0], match[1], match[2], match[3], match[4]);
            }
            st.print();
            main(null);
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        catch(Exception e){
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
        Scanner sc = new Scanner(System.in);
        System.out.print("please type to Check All Feature: ");
        String cMenu = sc.nextLine();
        if (cMenu.equals("--match-r")){
            readMatch();
        }else if (cMenu.equals("--match-c")){
            if (userType.equals("admin")){
                writeMatch();
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
