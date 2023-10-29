package com.driver;

import java.util.*;

public class Gmail extends Email {

    LinkedList<Mail> inbox = new LinkedList<>();
    LinkedList<Mail> trash = new LinkedList<>();
    int Inbox;
    int Trash;
    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(Inbox == inboxCapacity) {
            trash.addFirst(inbox.removeLast());
            Trash += 1;
            Inbox -= 1;
        }

        Mail mail = new Mail(date, sender, message);
        inbox.addFirst(mail);
        Inbox += 1;
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int  i = 0; i < inbox.size(); i++) {
            if(inbox.get(i).getMessage() == message) {
                trash.addFirst(inbox.remove(i));
                Inbox -= 1;
                Trash += 1;
                return;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox == null)
            return null;

        return inbox.getFirst().getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox == null)
            return null;

        return inbox.getLast().getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int numberOfMail = 0;
        int s = -1;
        int e = 0;
        boolean isSFilled = false;

        for(int i = 0; i < inbox.size(); i++) {
            if(inbox.get(i).getDate() == start && !isSFilled) {
                s = i;
                isSFilled = true;
            }
            if(inbox.get(i).getDate() == end)
                e = i;
        }

        return e - s + 1;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox;
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash;
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
