package com.redhat.abrt;

public enum ProblemDataKey {
    /* backtrace contains the actual problem stacktrace (if applicable)
     * it can be: kernel oops, C/C++ stacktrace, java exception stack, python exception
     */
    BACKTRACE,
    /* a string representing the type of the reported problem
     * e.g: "kerneloops", "ccpp", "python", "java", "jboss"
     */
    TYPE,
    /* OBSOLETED, only for compatibility with older ABRT
     * it should have the same value as TYPE
     */
    ANALYZER,
    /* PID of the failing application */
    PID,
    /*
     * a full path to the executable
     */
    EXECUTABLE,
    /*
     * Human readable description of the problem
     * It's used in the UI and in the created problem reports (bz tickets)
     */
    REASON;
}
