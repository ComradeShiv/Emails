package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store
        super(emailId, Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        if(calendar.isEmpty()) return 0;

//        Collections.sort(calendar, new Comparator<Meeting>() {
//            @Override
//            public int compare(Meeting o1, Meeting o2) {
//                return o1.getStartTime().compareTo(o2.getStartTime());
//            }
//        });

        Collections.sort(calendar, (a,b) -> a.getEndTime().compareTo(b.getEndTime()));

        int count = 0;
        Meeting preMeet = null;

        for(Meeting currMeet: calendar) {
            if(preMeet == null || currMeet.getStartTime().compareTo(preMeet.getEndTime()) > 0) {
                count++;
                preMeet = currMeet;
            }

        }
        return count;

//        PriorityQueue<Meeting> pq = new PriorityQueue<>( (a,b) -> {
//            return a.getEndTime().compareTo(b.getEndTime());
//        });
//
//        int maxMeetings = 0, i = 0, n = calendar.size();
//        LocalTime currEndTime = LocalTime.MIN;
//
//        while(!pq.isEmpty() || i < n) {
////            System.out.println("not end");
//            if(pq.isEmpty())
//                currEndTime = calendar.get(i).getEndTime();
//            pq.add(calendar.get(0));
//            while(i < n && calendar.get(i).getEndTime().isBefore(currEndTime)) {
//                pq.add(calendar.get(i));
//                i++;
//            }
//            currEndTime = pq.poll().getEndTime();
//            maxMeetings += 1;
//            while(!pq.isEmpty() && pq.peek().getEndTime().isBefore(currEndTime))
//                pq.poll();
//        }
//        return maxMeetings;
    }
}
