
/**********************************************
Workshop 10 
Course: JAC444 - Semester 4
Last Name: Abdi
First Name: Tareq
ID: 123809196
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature - TA
Date: 15/04/2022
**********************************************/

import java.util.Scanner;

public class Time implements Cloneable, Comparable<Time> {
    // main variables
    private long mTime;
    private int mHour;
    private int mMinute;
    private int mSecond;
    // for input
    Scanner myObj = new Scanner(System.in);

    // no arg constructor that sets the time to our current time in mili seconds
    Time() {
        mTime = System.currentTimeMillis();
    }

    // three arg constructor that sets our variables
    // and calculates the seconds for mTime
    Time(int hour, int minute, int second) {
        mHour = hour;
        mMinute = minute;
        mSecond = second;

        calculateSeconds();
    }

    // one arg constructor that sets mTime to our elapsedTime from user
    Time(long elapsedTime) {
        mTime = elapsedTime;
    }

    // calculates mTime by adding all variables in seconds
    void calculateSeconds() {
        mTime += mHour * 3600;
        mTime += mMinute * 60;
        mTime += mSecond;
    }

    // gets hour in 24 format
    int getHour() {
        return (int) (mTime / 3600) % 24;
    }

    // gets minute in 60 minute format
    int getMinute() {
        return (int) (mTime % 3600) / 60;
    }

    // gets seconds in 60 seconds format
    int getSecond() {
        return (int) (mTime % 60);
    }

    // gets the seconds that are stored in mTime
    long getSeconds() {
        return mTime;
    }

    // outputs the hour minute and seconds in one String
    public String toString() {
        return getHour() + " hour/s " + getMinute() + " minute/s " + getSecond() + " second/s";
    }

    // validation for input hours minutes seconds
    String checkTime(String input) {
        while (!input.matches("^\\d+(?:\\s+\\d+){2}$")) {
            System.out.print("Input is invalid! Please try again: ");
            input = myObj.nextLine();
        }
        return input;
    }

    // validation for seconds only numbers
    String checkSeconds(String input) {
        while (!input.matches("^[0-9]*$") || input.length() < 2) {
            System.out.print("Input is invalid! Please try again: ");
            input = myObj.nextLine();
        }
        return input;
    }

    // main start up of our program
    void startUp() {
        String input = "";
        String[] nums = {};
        System.out.print("Enter time1 (hour minute second): ");
        input = checkTime(myObj.nextLine());
        nums = input.split("\\s+");

        Time time1 = new Time(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]),
                Integer.parseInt(nums[2]));

        System.out.println(time1.toString());
        System.out.print("Elapsed seconds in time1: ");
        System.out.println(time1.getSeconds());

        System.out.print("Enter time2 (elapsed time in seconds): ");
        input = checkSeconds(myObj.nextLine());

        Time time2 = new Time(Integer.parseInt(input));
        System.out.println(time2.toString());
        System.out.print("Elapsed seconds in time2: ");
        System.out.println(time2.getSeconds());

        System.out.print("time1.compareTo(time2)?: " + time1.compareTo(time2) + "\n");

        try {
            System.out.println("time3 is created as a clone of time1");
            Time time3 = (Time) time1.clone();
            System.out.print("time1.compareTo(time3)?: " + time1.compareTo(time3) + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        myObj.close();
    }

    // overriding the clone function from clone class so we can use it in our case
    @Override
    protected Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }

    // overriding the compare to function to work with Time class
    @Override
    public int compareTo(Time o) {
        if (mTime != o.mTime) {
            return Math.toIntExact(mTime - o.mTime);
        } else {
            return 0;
        }
    }
}
