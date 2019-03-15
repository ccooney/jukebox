package scratch.mixtape;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import scratch.mixtape.io.MixTapeIO;
import scratch.mixtape.model.Mixtape;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		if (args.length != 3) {
			syntax();
			System.exit(1);
		}

		int rc = new App().run(args[0], args[1], args[2]);
		System.exit(rc);

	}

	private static void syntax() {
		System.out.println("syntax: java -jar mixtape.jar <input json> <changes.json> <output.json>");

	}

	private int run(String inFile, String changeFile, String outFile) {
		MixTapeIO io = new MixTapeIO();
		Mixtape mt = null;

		try {
			 mt = io.read(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("unable to find file "+inFile);
			return 1;
		} catch (JsonParseException | JsonMappingException e) {

			System.out.println(String.format("Error parsing mixtape %s: %s", inFile, e.getMessage()));
			return 1;
		} catch (IOException e) {
			System.out.println(String.format("Error reading mixtape %s: %s", inFile, e.getMessage()));
			return 1;

		}


		try {
			io.write(outFile, mt);
		} catch (JsonProcessingException e) {
			System.out.println(String.format("Error writing mixtape: %s", e.getMessage()));
		} catch (IOException e) {
			System.out.println(String.format("Error writing mixtape: %s", e.getMessage()));
		}
		return 0;
	}
}
