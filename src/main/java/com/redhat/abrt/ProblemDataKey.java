package com.redhat.abrt;

public enum ProblemDataKey {
    /* backtrace contains the actual problem stacktrace (if applicable)
     * it can be: kernel oops, C/C++ stacktrace, java exception stack, python exception
     */
    BACKTRACE("BACKTRACE"),
    /* a string representing the type of the reported problem
     * e.g: "kerneloops", "ccpp", "python", "java", "jboss"
     */
    TYPE("TYPE"),
    /* OBSOLETED, only for compatibility with older ABRT
     * it should have the same value as TYPE
     */
    ANALYZER("ANALYZER"),
    /* PID of the failing application */
    PID("PID"),
    /*
     * a full path to the executable
     */
    EXECUTABLE("EXECUTABLE"),
    /*
     * Human readable description of the problem
     * It's used in the UI and in the created problem reports (bz tickets)
     */
    REASON("REASON");

    private String value;

    ProblemDataKey(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
