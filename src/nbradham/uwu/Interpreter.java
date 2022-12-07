package nbradham.uwu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * Core interpreter code.
 * 
 * @author Nickolas Bradham
 *
 */
final class Interpreter {

	private final HashMap<String, Object> globVarMap = new HashMap<>();
	private final HashMap<String, Integer> labelMap = new HashMap<>();
	private final Stack<StackEntry> stack = new Stack<>();
	private final Scanner in = new Scanner(System.in);
	private final String[] lines;

	private HashMap<String, Object> locVarMap = new HashMap<>();

	/**
	 * Constructs a new Interpreter and prepares it for execution.
	 * 
	 * @param file The UWU file to execute.
	 * @throws FileNotFoundException Thrown by {@link Scanner#Scanner(File)}.
	 */
	private Interpreter(File file) throws FileNotFoundException {
		Scanner s = new Scanner(file);
		ArrayList<String> t = new ArrayList<>();
		String read;
		int line = -1;
		while (s.hasNext()) {
			read = s.nextLine();
			if (read.startsWith("(^o^)") && !read.isBlank())
				continue;

			if (read.startsWith("!")) {
				labelMap.put(read.substring(1), line);
				continue;
			}

			t.add(read);
			line++;
		}
		s.close();
		lines = t.toArray(new String[0]);
	}

	/**
	 * Starts the interpreter and executes the UWU code.
	 * 
	 * @throws FileNotFoundException Thrown by {@link Scanner#Scanner(File)}.
	 */
	private void start() {
		String tmpStr;
		boolean tmpBool;
		StackEntry tmpSE;
		for (int l = 0; l < lines.length; l++) {
			try {
				String[] split = lines[l].split(" ");
				switch (split[0]) {
				case "vwar":
					tmpStr = buildString(split, 3);
					switch (split[1]) {
					case "gwobaw":
						globVarMap.put(split[2], tmpStr);
						break;

					case "wocaw":
						locVarMap.put(split[2], tmpStr);
					}
					break;

				case "pwint":
					switch (split[1]) {
					case "twext":
						System.out.println(buildString(split, 2));
						break;

					case "vwar":
						System.out.println(getVar(split[2]));
					}
					break;

				case "owp":
					switch (split[1]) {
					case "pwus":
						doOp(split[2], split[3], (a, b) -> a + b);
						break;

					case "swub":
						doOp(split[2], split[3], (a, b) -> a - b);
						break;

					case "muwlt":
						doOp(split[2], split[3], (a, b) -> a * b);
						break;

					case "dwiv":
						doOp(split[2], split[3], (a, b) -> a / b);
						break;

					case "cowpy":
						putVar(split[3], getVar(split[2]));
						break;

					case "awpend":
						putVar(split[3], (String) getVar(split[3]) + getVar(split[2]));
						break;

					case "chrawrAt":
						putVar(split[4],
								((String) getVar(split[2])).charAt(Integer.parseInt((String) getVar(split[3]))));
					}
					break;

				case "inpwut":
					putVar(split[1], in.nextLine());
					break;

				case "gowtu":
					l = labelMap.get(split[1]);
					break;

				case "wumpif":
					tmpBool = false;
					switch (split[1]) {
					case "eqwal":
						tmpBool = varStrEq(split[2], split[3]) || doOpRet(split[2], split[3], (a, b) -> a - b) == 0;
						break;

					case "gw8r":
						tmpBool = doOpRet(split[2], split[3], (a, b) -> a - b) > 0;
						break;

					case "gw8rOrEqwal":
						tmpBool = doOpRet(split[2], split[3], (a, b) -> a - b) >= 0;
						break;

					case "notEqwal":
						tmpBool = !varStrEq(split[2], split[3]) || (isVarNum(split[2]) && isVarNum(split[3])
								&& doOpRet(split[2], split[3], (a, b) -> a - b) != 0);
					}
					if (tmpBool)
						l = labelMap.get(split[4]);
					break;

				case "subwutine":
					stack.add(new StackEntry(l, new HashMap<String, Object>(locVarMap)));
					l = labelMap.get(split[1]);
					break;

				case "wetwurn":
					tmpSE = stack.pop();
					l = tmpSE.retLine();
					locVarMap = tmpSE.locVars();
					break;

				case "fwile":
					switch (split[1]) {
					case "opwen":
						putVar(split[2], new Scanner(new File(buildString(split, 3))));
						break;
					case "cwose":
						((Scanner) getVar(split[2])).close();
						break;
					case "nwext":
						putVar(split[3], ((Scanner) getVar(split[2])).next());
					}
					break;

				case "awway":
					switch (split[1]) {
					case "cweate":
						putVar(split[3], new Object[Integer.parseInt((String) getVar(split[2]))]);
						break;

					case "pwut":
						((Object[]) getVar(split[3]))[Integer.parseInt((String) getVar(split[4]))] = getVar(split[2]);
						break;

					case "gewt":
						putVar(split[4], ((Object[]) getVar(split[2]))[Integer.parseInt((String) getVar(split[3]))]);
					}
				}
			} catch (Exception e) {
				System.err.printf("Exception in \"%s\", after line %d.%n", lines[l], l);
				e.printStackTrace();
				return;
			}
		}
	}

	/**
	 * Forwards to {@link #doOpRet(String, String, OpInterface)} and stores result
	 * in {@code varB} in the interpreter variable space.
	 * 
	 * @param varA The first argument variable.
	 * @param varB The second and destination variable.
	 * @param func The OpInterface to run the input in.
	 */
	private void doOp(String varA, String varB, OpInterface func) {
		putVar(varB, Float.toString(doOpRet(varA, varB, func)));
	}

	/**
	 * Calls {@link OpInterface#doOp(float, float)} provided by {@code func} on
	 * {@code varA} and {@code varB}.
	 * 
	 * @param varA The first argument.
	 * @param varB The second argument.
	 * @param func The OpInterface to call.
	 * @return The value computed.
	 */
	private float doOpRet(String varA, String varB, OpInterface func) {
		return func.doOp(Float.parseFloat((String) getVar(varA)), Float.parseFloat((String) getVar(varB)));
	}

	/**
	 * Puts {@code value} into the local or global variable space.
	 * 
	 * @param name  The variable name.
	 * @param value The value to store.
	 */
	private void putVar(String name, Object value) {
		(locVarMap.containsKey(name) ? locVarMap : globVarMap).put(name, value);
	}

	/**
	 * Retrieves variable {@code name} from the local or global variable space.
	 * 
	 * @param name The name of the variable to retrieve.
	 * @return The Object stored at {@code name}.
	 */
	private Object getVar(String name) {
		return (locVarMap.containsKey(name) ? locVarMap : globVarMap).get(name);
	}

	/**
	 * Compares variables {@code a} and {@code b}.
	 * 
	 * @param a The first variable.
	 * @param b The second variable.
	 * @return The result of doing {@code a.equals(b)} after retrieving {@code a}
	 *         and {@code b} from the variable space.
	 */
	private boolean varStrEq(String a, String b) {
		return getVar(a).equals(getVar(b));
	}

	/**
	 * Tests if {@code v} is a number.
	 * 
	 * @param v The variable to test.
	 * @return True if the value was successfully parsed into a number.
	 */
	private boolean isVarNum(String v) {
		try {
			Float.parseFloat((String) getVar(v));
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Constructs a string from the remaining elements of {@code split}.
	 * 
	 * @param split      The String array to get data from.
	 * @param startIndex The starting index to build the String from.
	 * @return The resulting String.
	 */
	private static String buildString(String[] split, int startIndex) {
		StringBuilder sb = new StringBuilder(split[startIndex]);
		for (int i = startIndex + 1; i < split.length; i++) {
			sb.append(' ');
			sb.append(split[i]);
		}
		return sb.toString();
	}

	/**
	 * Checks args and starts interpreter.
	 * 
	 * @param args Command line arguments.
	 * @throws FileNotFoundException Thrown by {@link #Interpreter(File)} and
	 *                               {@link #start()}.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 1) {
			System.out.println("Argwuments: <uwu>\n uwu - the pwogwam fwile to run.");
			return;
		}

		File f = new File(args[0]);
		if (!f.exists()) {
			System.out.println("Sowwy, but I cawn't see that fwile. o(;.;)o");
			return;
		}

		new Interpreter(f).start();
	}
}