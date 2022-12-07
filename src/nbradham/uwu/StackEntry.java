package nbradham.uwu;

import java.util.HashMap;

record StackEntry(int retLine, HashMap<String, Object> locVars) {
}