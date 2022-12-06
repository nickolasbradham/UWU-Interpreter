package nbradham.uwu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

final class Interpreter {

	private final HashMap<String, String> varMap = new HashMap<>();
	private final HashMap<String, Integer> labelMap = new HashMap<>();
	private final Scanner in = new Scanner(System.in);
	private final String[] lines;

	private Interpreter(File f) throws FileNotFoundException {
		Scanner s = new Scanner(f);
		ArrayList<String> t = new ArrayList<>();
		String read;
		short line = 0;
		while (s.hasNext()) {
			read = s.nextLine();
			if (!read.startsWith("(^o^)-") && !read.isBlank())
				continue;

			if (read.startsWith("wable:")) {
				labelMap.put(read.substring(7), line + 1);
				continue;
			}

			t.add(read);
			line++;
		}
		s.close();
		lines = t.toArray(new String[0]);
	}

	private void start() {
		for (byte l = 0; l < lines.length; l++) {
			String[] split = lines[l].split(" ");
			switch (split[0]) {
			case "vwar":
				varMap.put(split[1], buildString(split, 2));
				break;

			case "pwint":
				switch (split[1]) {
				case "twext":
					System.out.println(buildString(split, 2));
					break;

				case "vwar":
					System.out.println(varMap.get(split[2]));
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
				}
				break;

			case "inpwut":
				varMap.put(split[1], in.nextLine());
			}
		}
	}

	private void doOp(String varA, String varB, OpInterface func) {
		varMap.put(varB,
				Float.toString(func.doOp(Float.parseFloat(varMap.get(varA)), Float.parseFloat(varMap.get(varB)))));
	}

	private static String buildString(String[] split, int startIndex) {
		StringBuilder sb = new StringBuilder(split[startIndex]);
		for (int i = startIndex + 1; i < split.length; i++) {
			sb.append(' ');
			sb.append(split[i]);
		}
		return sb.toString();
	}

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