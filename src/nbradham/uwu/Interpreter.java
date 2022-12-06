package nbradham.uwu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

final class Interpreter {

	private final HashMap<String, String> varMap = new HashMap<>();
	private final String[] lines;

	private Interpreter(File f) throws FileNotFoundException {
		Scanner s = new Scanner(f);
		ArrayList<String> t = new ArrayList<>();
		while (s.hasNext())
			t.add(s.nextLine());
		s.close();
		lines = t.toArray(new String[0]);
	}

	private void start() {
		for (byte l = 0; l < lines.length; l++) {
			String[] split = lines[l].split(" ");
			switch (split[0]) {
			case "vrawr":
				switch (split[1]) {
				case "stowre":
					varMap.put(split[2], buildString(split, 3));
					break;
				case "owp":
					//TODO Continue.
				}
				break;
			case "pwint":
				switch (split[1]) {
				case "twext":
					System.out.println(buildString(split, 2));
					break;
				case "vrawr":
					System.out.println(varMap.get(split[2]));
				}
			}
		}
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