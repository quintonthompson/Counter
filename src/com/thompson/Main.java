package com.thompson;

/*
Thread safe class means that the developer had synchronized all the critical parts of the code.

JavaFX UI developers make JavaFX UI run only on one thread so its synchronized and has no interference
 */

public class Main {

    public static void main(String[] args) {

        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t1.setName("Thread 2");

        t1.start();
        t2.start();

    }

}

class Countdown {
    /*
    Do not use local variables as blocks to synchronize
     */
    private int i;

    //synchronized allows only one thread to run the method. prevents race
    public void doCountDown() {
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;

        }
        //the for loop is the critical portion that needs to be synchronized. Synchronizing doCountDown() results in too much synchronized code
        //using this class object as a block is a good way to synchronize threads
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
            }
        }


    }

}

class CountdownThread extends Thread{
    private Countdown threadCountDown;

    public CountdownThread(Countdown countDown){
        threadCountDown = countDown;
    }

    public void run(){
        threadCountDown.doCountDown();
    }
}













