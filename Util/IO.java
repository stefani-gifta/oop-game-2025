package Util;

import java.util.Scanner;

public class IO {
	
	public static Scanner scan = new Scanner(System.in);

	public static void PRESS_ENTER() {
		System.out.println("\nPress ENTER to continue");
		scan.nextLine();
	}
	
	public static void CLEAR_CONSOLE() {
		//System.out.println("CLEAR");
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public static void LOADING() {
		for (int i = 0; i <= 5; i++) {
			System.out.print("[");
            for (int j = 0; j <= i; j++) {
                System.out.print("===");
            }
            for (int j = 0; j < 5-i; j++) {
                System.out.print("   ");
            }
            System.out.println("]");
            System.out.printf("Loading... %d%%\n", i*20);
            try {
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException e) {
            	System.out.println("Loading failed");
                // e.printStackTrace();
            }
            IO.CLEAR_CONSOLE();
        }
        System.out.println("Loading complete!");
        IO.PRESS_ENTER();
	}

}
