package core.utils;

import org.testng.Assert;

public class AssertionHelper {

    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void fail(String message) {
        Assert.fail(message);
    }
}