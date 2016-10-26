/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.utils;

/**
 * Listener for when a IHaltMonitor is halted.
 *
 * @author matta
 */
public interface IHaltListener {

    void halted(IHaltMonitor monitor);
}
