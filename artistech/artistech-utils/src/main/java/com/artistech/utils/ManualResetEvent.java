/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.utils;

/**
 * This is a wrapper class around an object's wait and notify methods. This is
 * used as a class for familiarity with .NET's event classes.
 *
 * @author matta
 */
public class ManualResetEvent {

    private final Object monitor = new Object();
    private volatile boolean open = false;

    /**
     * Constructor.
     *
     * @param open
     */
    public ManualResetEvent(boolean open) {
        this.open = open;
    }

    /**
     * Wait for signal.
     *
     * @throws InterruptedException
     */
    public void waitOne() throws InterruptedException {
        synchronized (monitor) {
            while (open == false) {
                monitor.wait();
            }
        }
    }

    /**
     * Wait for signal.
     *
     * @param milliseconds
     * @return
     * @throws InterruptedException
     */
    public boolean waitOne(long milliseconds) throws InterruptedException {
        synchronized (monitor) {
            if (open) {
                return true;
            }
            monitor.wait(milliseconds);
            return open;
        }
    }

    /**
     * Signal.
     */
    public void set() {//open start
        synchronized (monitor) {
            open = true;
            monitor.notifyAll();
        }
    }

    /**
     * Reset.
     */
    public void reset() {//close stop
        open = false;
    }

    /**
     * Is the event open?
     *
     * @return
     */
    public boolean isOpen() {
        return open;
    }
}
