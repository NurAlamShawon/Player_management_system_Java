package com.company;

import java.util.Scanner;

public class Welcome extends Color{
    public Welcome(){main(null);}
    public static void close() {
        System.exit(1);
    }
    public static void main(String[] args) {
        String [] menu = {"Admin(--admin)", "Coach(--coach)", "Player(--player)"};
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome To Team Management");
        System.out.println();
        for (int i = 0; i < menu.length; i++) {
            System.out.print(ANSI_RED + "\t" + menu[i] + ANSI_RESET);
        }
        System.out.println();
        System.out.println();
        System.out.print("Please Select your user: ");
        String user = sc.nextLine();
        if (user.equals("--admin")){
            Login l = new Login("registration.txt", "admin");
        }else if (user.equals("--coach")){
            Login c = new Login("coach.txt", "coach");
        }else if (user.equals("--player")){
            Login c = new Login("player.txt", "player");
        }else {
            main(null);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> close()));
        System.exit(1);
    }
}
