package com.techelevator;

import com.techelevator.log.Log;
import org.junit.Test;

public class LogTest {

    @Test
    public void checkLogFileAndAppend() {
        Log.log("Hello");
        Log.log("Hello again");
        Log.log("Bye");
        Log.log("Bye again");
        Log.log("Printed in chronological order");
    }
}
