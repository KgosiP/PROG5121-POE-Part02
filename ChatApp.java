/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.chatapp;

import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Registration
        System.out.println("=== Register ===");
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell phone number: ");
        String cellPhone = input.nextLine();

        Login user = new Login(firstName, lastName, username, password, cellPhone);
        System.out.println(user.registerUser());

        // Login
        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String enteredUsername = input.nextLine();

        System.out.print("Enter password: ");
        String enteredPassword = input.nextLine();

        String loginStatus = user.returnLoginStatus(enteredUsername, enteredPassword);
        System.out.println(loginStatus);

        // continues only when login is successful to ensure security.
        if (loginStatus.startsWith("Welcome")) {
            System.out.println("\nWelcome to QuickChat.");

            // prompts the user on the amount of messages they would like to send.
            System.out.print("How many messages would you like to send? ");
            int numMessages = Integer.parseInt(input.nextLine());

            boolean running = true;
            while (running) {
                // Display menu
                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");
                int menuChoice = Integer.parseInt(input.nextLine());

                if (menuChoice == 1) {
                    // Loop for the number of messages the user wants to send
                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- Message " + (i + 1) + " ---");
                        System.out.print("Enter recipient cell number: ");
                        String recipient = input.nextLine();

                        System.out.print("Enter message: ");
                        String messageText = input.nextLine();

                        // Create message object
                        Message msg = new Message(i, recipient, messageText);

                        // Validate recipient
                        System.out.println(msg.checkRecipientCell());

                        // Check message length
                        if (messageText.length() > 250) {
                            System.out.println("Message exceeds 250 characters by " + 
                                (messageText.length() - 250) + "; please reduce the size.");
                            i--; // Don't count this as a sent message
                            continue;
                        } else {
                            System.out.println("Message ready to send.");
                        }

                        // prompts the user on possible outcomes with the message.
                        System.out.println("\n1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");
                        System.out.print("Choose an option: ");
                        int sendChoice = Integer.parseInt(input.nextLine());

                        // Handle store separately
                        if (sendChoice == 3) {
                            msg.storeMessage();
                        }

                        System.out.println(msg.sentMessage(sendChoice));
                    }

                    // ptompts the user on the total amount of messages.
                    System.out.println("\nTotal messages sent: " + Message.getTotalMessages());

                } else if (menuChoice == 2) {
                    System.out.println("Coming Soon.");
                } else if (menuChoice == 3) {
                    running = false;
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid option, please try again.");
                }
            }
        }
        input.close();
    }
}
