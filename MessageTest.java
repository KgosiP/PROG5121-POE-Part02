/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chatapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author lab_services_student
 */
public class MessageTest {

    // Test message length is within 250 characters
    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.getMessageText().length() <= 250);
    }

    // Test message length exceeds 250 characters
    @Test
    public void testMessageLengthFailure() {
        Message msg = new Message(0, "+27718693002", "A".repeat(251));
        assertFalse(msg.getMessageText().length() <= 250);
    }

    // ensures that the recipient number is correctly formatted.
    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    // Test recipient number incorrectly formatted
    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message(0, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    // Test message hash is correctly generated
    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.getMessageHash();
        assertTrue(hash.endsWith("HITONIGHT?"));
    }

    // Test message ID is created and not more than 10 characters
    @Test
    public void testMessageIDCreated() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID());
    }

    // Test send message
    @Test
    public void testSentMessage() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    // Test disregard message
    @Test
    public void testDisregardMessage() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    // Test store message
    @Test
    public void testStoreMessage() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}
