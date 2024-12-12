package com.company;

import java.util.Scanner;

public class CoachMain extends Color{
    private static int id;
    private static String userType;
    static String [] menu = {"Player(--player)", "Match(--match)", "Playing-Team(--pteam)", "Profile(--profile)", "Logout(--logout)"};

    public CoachMain (int id, String userType) {
        this.id = id;
        this.userType = userType;
        System.out.print("please type " + ANSI_RED + "--list" + ANSI_RESET + " to Check All Feature: ");
        main(null);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String list = sc.nextLine();
        if (list.equals("--list")){
            for (int i = 0; i < menu.length; i++) {
                if (i % 3 == 0){
                    System.out.println();
                }
                System.out.print(ANSI_RED + "\t" + menu[i] + ANSI_RESET);
            }
            System.out.println();
            System.out.println();
            System.out.print("please type " + ANSI_RED + "menu item" + ANSI_RESET + " to Check it's Sub-Feature: ");
            String text = sc.nextLine();
            if (text.equals("--pteam")){
                PlayingTeam p = new PlayingTeam(id, userType);
            }else if (text.equals("--match")){
                Match m = new Match(id, userType);
            }else if (text.equals("--player")){
                Player p = new Player(id, userType);
            }else if (text.equals("--profile")){
                Profile p = new Profile(id, userType);
            }else if(text.equals("--logout")){
                Welcome wc = new Welcome();
            }else {
                main(null);
            }
        }else {
            main(null);
        }
    }

}
