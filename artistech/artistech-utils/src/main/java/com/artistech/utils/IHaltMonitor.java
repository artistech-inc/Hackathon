/*
 * Copyright 2015-2016 ArtisTech, Inc.
 */
package com.artistech.utils;

/**
 * IHaltMonitor interface.
 *
 * Allows access to know if the current thread is halted.
 *
 * @author matta
 */
public interface IHaltMonitor {

    boolean isHalted();

    String getName();
}
