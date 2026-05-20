/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp;

/**
 *
 *@Kgosi.P
 */

// adding the class variables
public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    // adding a constructor 
    public Login(String firstName, String lastName, String username, String password, String cellPhoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        
    }
    
    // adding a class that will check the username used
    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    
    }
    
    //adding a clas that will check  the validity of password
    public boolean checkPasswordComplexity() {
        if (password.length() < 8){
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        //statements that will ensure that the necessary characters are added
        for(int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            
            if (Character.isUpperCase(c)){
                hasCapital = true;
            }
            
            if(Character.isDigit(c)) {
                hasNumber = true;
            }
            
            if (!Character.isLetterOrDigit(c)){
                hasSpecial = true;
            }
        }
        
        return hasCapital && hasNumber && hasSpecial;
        
    }
    
    // adding a class that will check the validity of a cellphone number
    //regex reference: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
    public boolean checkCellPhoneNumber(){
        return cellPhoneNumber.matches("\\+\\d{1,3}\\d{10}");
        }
    
    // class checks each condition one at a time.
    public String registerUser() {
         if (!checkUserName()) {
             return "Username is not correctly formatted; please ensure that your username contains an underscore and is more than five characters in length.";
             
         }
         
         if (!checkPasswordComplexity()){
             return "password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and special characters.";
             
         }
         
         if (!checkCellPhoneNumber()){
             return "cell number is incorrectly formatted or does not contain an international code; please correct the number and try again";
             
         }
         
         return "username successfully captured. password successfully captured. cell number successfully captured";
            
     }
    
    // verification class (ensures what is stored & what user types matches).
    public boolean loginUser(String enterdUsername, String enterdPassword) {
        return enterdUsername.equals(username) && enterdPassword.equals(password);
        
    }
    
    // class builds welcome message usin the the stored firstname and lastname.
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + " " + lastName + " it is great to see you again. ";
        } else {
            return "Username or password incorrect, please try again";
            
        }
        
    }
     
    
        
   
     
     
}
