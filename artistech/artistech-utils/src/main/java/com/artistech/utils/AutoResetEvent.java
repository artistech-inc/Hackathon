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
public class AutoResetEvent {

    private final Object _monitor = new Object();
    private volatile boolean _isOpen = false;

    /**
     * Constructor.
     *
     * @param open
     */
    public AutoResetEvent(boolean open) {
        _isOpen = open;
    }

    /**
     * Wait until signaled.
     *
     * @throws InterruptedException
     */
    public void waitOne() throws InterruptedException {
        synchronized (_monitor) {
            while (!_isOpen) {
                _monitor.wait();
            }
            _isOpen = false;
        }
    }

    /**
     * Wait until signaled.
     *
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    public boolean waitOne(long timeout) throws InterruptedException {
        boolean ret = true;
        synchronized (_monitor) {
            long t = System.currentTimeMillis();
            while (!_isOpen) {
                _monitor.wait(timeout);
                // Check for timeout
                if (System.currentTimeMillis() - t >= timeout) {
                    ret = false;
                    break;
                }
            }
            _isOpen = false;
        }
        return ret;
    }

    /**
     * Signal.
     */
    public void set() {
        synchronized (_monitor) {
            _isOpen = true;
            _monitor.notify();
        }
    }

    /**
     * Reset.
     */
    public void reset() {
        _isOpen = false;
    }
}
