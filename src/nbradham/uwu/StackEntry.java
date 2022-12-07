package nbradham.uwu;

import java.util.HashMap;

/**
 * Holds stack return information.
 * 
 * @author Nickolas Bradham
 *
 */
record StackEntry(int retLine, HashMap<String, Object> locVars) {
}