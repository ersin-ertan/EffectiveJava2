package com.nullcognition.effectivejava2.chapter02;

/**
 * Created by ersin on 18/04/15 at 8:02 PM
 */

// avoid finalizers

// finalizers are unpredictable, dangerous, unnecessary
// finalizers are not for time critical, persistent state update critical, operations
// finalizers have a performance penalty
// provide explicity termination methods for files/threads in combiniation with try-finally
// finalizers should log a warning if it finds that a resource has not been terminated


public class Item07 {}
