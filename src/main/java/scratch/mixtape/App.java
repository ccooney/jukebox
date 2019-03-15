package scratch.mixtape;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import scratch.mixtape.io.ObjectIO;
import scratch.mixtape.model.AddSongToPlaylist;
import scratch.mixtape.model.AddUserPlaylist;
import scratch.mixtape.model.Changes;
import scratch.mixtape.model.Mixtape;
import scratch.mixtape.model.RemovePlaylist;

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
		ObjectIO<Mixtape> io = new ObjectIO<Mixtape>();
		ObjectIO<Changes> changesIO = new ObjectIO<Changes>();
		Mixtape mt = null;

		try {
			 mt = io.read(inFile, Mixtape.class);
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
			Changes changes = changesIO.read(changeFile, Changes.class);
			applyChanges(mt, changes);
		} catch (JsonProcessingException e) {
			System.out.println(String.format("Error reading change file: %s", e.getMessage()));
		} catch (IOException e) {
			System.out.println(String.format("Error reading change file: %s", e.getMessage()));
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

	private void applyChanges(Mixtape mt, Changes changes) {
		addSongs(mt, changes.getAddSongToPlaylist());
		addUserPlaylists(mt, changes.getAddUserPlaylist());
		removePlaylists(mt, changes.getRemovePlaylist());

	}

	private void removePlaylists(Mixtape mt, List<RemovePlaylist> removePlaylist) {
		if (removePlaylist==null) {
			return;
		}
		// TODO Auto-generated method stub

	}

	private void addUserPlaylists(Mixtape mt, List<AddUserPlaylist> addUserPlaylist) {
		if (addUserPlaylist==null) {
			return;
		}
		// TODO Auto-generated method stub

	}

	private void addSongs(Mixtape mt, List<AddSongToPlaylist> addSongToPlaylist) {
		if (addSongToPlaylist==null) {
			return;
		}
		// TODO Auto-generated method stub

	}
}
