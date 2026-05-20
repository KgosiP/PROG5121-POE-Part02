/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp;

/**
 *
 * @author lab_services_student
 */

import java.util.ArrayList;
import java.util.Random;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.FileWriter;

public class Message {
    
    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private static int totalMessages = 0;                              //static means varibles belong to the class itself
    private static ArrayList<String> sentMessages = new ArrayList<>();
    
    //the constructor; the mesageID & hash are auto-generated when the object is created. meaning they are built in.
   public Message(int messageNumber, String recipientCell, String messageText){
       this.messageNumber = messageNumber;
       this.recipientCell = recipientCell;
       this.messageText = messageText;
       this.messageID = generateMessageID();
       this.messageHash = createMessageHash();
       
   }
    
   private String generateMessageID() {
       Random random = new Random ();
       long id = (long)(random.nextDouble() * 9000000000L) + 1000000000L;
       return String.valueOf(id);
   }
   
   // checks to ensure that the generated ID is no more than 10 characters.
   public boolean checkMessageID() {
       return messageID.length() <= 10;
       
   }
   
   // returns a string message instead of a boolean; output messge is requierd.
   public String checkRecipientCell() {
       if (recipientCell.startsWith("+") && recipientCell.length() <= 13){
           return "Cell phone number successfully captured.";
       } else {
         return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
       }
   }
   
   public String createMessageHash(){
       String idPart = messageID.substring(0, 2); // takes the first two characters of the message ID.
       String[] words = messageText.split(" "); // split the message into words using spaces.
       String firstWord = words[0]; // grabs the first words.
       String lastWord = words[words.length - 1]; // grabs the last words.
       String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord; // build the hash as well as converts everything to uppercase.
       return hash.toUpperCase();
      
   }
   
   public String sentMessage(int choice){
       switch (choice) {
           case 1: //POV: user chose to sent a message.
               totalMessages++;
               sentMessages.add("message ID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipientCell + "\nMessage: " +messageText); 
            return "Message successfully sent.";
            
           case 2: //POV: user decided to disregard the message.
               return "Press 0 to delete the message.";
               
           case 3: // POV: user decided to store the message for later.
               return "Message successfully stored.";
               
           default:
               return "Invalid option, please try again.";
               
       }
   }   
       
   public String printMessages(){
       if (sentMessages.isEmpty()) { // POV: no messages have been sent yet.
           return "No messages sent.";
           }
           
           String allMessages = ""; // POV: a single string containing all sent messages.
           for (int i = 0; i < sentMessages.size(); i++) {
               allMessages += "message " + (1 + 1) + ":\n" + sentMessages.get(i) + "\n\n";
           }
           
           return allMessages;
       }
       
   public int returnTotalMessages(){
       return totalMessages;
       }
   
   public static int getTotalMessages() {
       return totalMessages;
   }
   
   public String getMessageText() {
       return messageText;
   }
   
   public String getMessageHash() {
       return messageHash;
   }
       
   public void storeMessage() {
       JSONObject messageDetails = new JSONObject ();
       messageDetails.put("MessageID", messageID);
       messageDetails.put("MessageHash", messageHash);
       messageDetails.put("Recipient", recipientCell);
       messageDetails.put("Message", messageText);
       
       JSONArray messageList = new JSONArray();
       messageList.add(messageDetails);
       
       try(FileWriter file = new FileWriter("message.json", true)) {
           file.write(messageList.toJSONString());
           file.flush();
        } catch (Exception e) {
            System.out.println("Error dtoring message: " + e.getMessage());
            
        }
       
       
   }
       
       
       
       
       
   }


   
   
   
   


