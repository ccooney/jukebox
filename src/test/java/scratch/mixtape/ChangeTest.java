package scratch.mixtape;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import scratch.mixtape.model.AddUserPlaylist;
import scratch.mixtape.model.ChangeException;
import scratch.mixtape.model.Mixtape;
import scratch.mixtape.model.Playlist;
import scratch.mixtape.model.RemovePlaylist;
import scratch.mixtape.model.Song;
import scratch.mixtape.model.User;

public class ChangeTest {

	@Test
	public void testAddSongPlaylist() throws ChangeException {
		Mixtape mt = new Mixtape();
		addUser(mt, 1, "Jeff");
		addSong(mt, 2, "artist", "title");

		Playlist pl = new Playlist();
		pl.setSongs(new LinkedList<Integer>());
		pl.getSongs().add(2);
		pl.setId(1);

		AddUserPlaylist add = new AddUserPlaylist();
		add.setUserId(1);
		add.setPlaylist(pl);

		MixtapeChanges.addUserPlaylists(mt, Arrays.asList(add));

		Assert.assertEquals(1, mt.getPlaylists().size());
	}

	@Test
	public void testAddSongPlaylistNoSong() {
		Mixtape mt = new Mixtape();
		addUser(mt, 1, "Jeff");
		addSong(mt, 2, "artist", "title");

		Playlist pl = new Playlist();
		pl.setSongs(new LinkedList<Integer>());
		pl.getSongs().add(21);
		pl.setId(1);

		AddUserPlaylist add = new AddUserPlaylist();
		add.setUserId(1);
		add.setPlaylist(pl);

		try {
			MixtapeChanges.addUserPlaylists(mt, Arrays.asList(add));
			Assert.fail();
		} catch (ChangeException e) {
		}
	}

	@Test
	public void testAddUserPlaylist() throws ChangeException {
		Mixtape mt = new Mixtape();
		addUser(mt, 1, "Jeff");
		addSong(mt, 2, "artist", "title");

		Playlist pl = new Playlist();
		pl.setSongs(new LinkedList<Integer>());
		pl.getSongs().add(2);
		pl.setId(1);
		AddUserPlaylist add = new AddUserPlaylist();
		add.setUserId(1);
		add.setPlaylist(pl);

		MixtapeChanges.addUserPlaylists(mt, Arrays.asList(add));

		Assert.assertEquals(1, mt.getPlaylists().size());
	}

	@Test
	public void testAddUserEmptyPlaylist() {
		Mixtape mt = new Mixtape();
		addUser(mt, 1, "Jeff");

		AddUserPlaylist add = new AddUserPlaylist();
		add.setUserId(1);
		add.setPlaylist(new Playlist());

		try {
			MixtapeChanges.addUserPlaylists(mt, Arrays.asList(add));
			Assert.fail("expected exception");
		} catch (ChangeException e) {
		}
	}

	@Test
	public void testRemovePlaylist() throws ChangeException {
		Mixtape mt = new Mixtape();
		addUser(mt, 1, "Jeff");
		addSong(mt, 2, "artist", "title");

		Playlist pl = new Playlist();
		pl.setSongs(new LinkedList<Integer>());
		pl.getSongs().add(2);
		pl.setId(1);
		AddUserPlaylist add = new AddUserPlaylist();
		add.setUserId(1);
		add.setPlaylist(pl);

		MixtapeChanges.addUserPlaylists(mt, Arrays.asList(add));

		RemovePlaylist remove = new RemovePlaylist();
		remove.setPlaylistId(1);
		MixtapeChanges.removePlaylists(mt, Arrays.asList(remove));

		Assert.assertEquals(0, mt.getPlaylists().size());

	}

	@Test
	public void testRemovePlaylistNoList() throws ChangeException {
		Mixtape mt = new Mixtape();
		addUser(mt, 1, "Jeff");
		addSong(mt, 2, "artist", "title");

		Playlist pl = new Playlist();
		pl.setSongs(new LinkedList<Integer>());
		pl.getSongs().add(2);
		pl.setId(1);
		AddUserPlaylist add = new AddUserPlaylist();
		add.setUserId(1);
		add.setPlaylist(pl);

		MixtapeChanges.addUserPlaylists(mt, Arrays.asList(add));

		RemovePlaylist remove = new RemovePlaylist();
		remove.setPlaylistId(10);

		try {
			MixtapeChanges.removePlaylists(mt, Arrays.asList(remove));
			Assert.fail("expect exception");
		} catch (ChangeException e) {
		}

		Assert.assertEquals(1, mt.getPlaylists().size());

	}

	private void addSong(Mixtape mt, int id, String artist, String title) {
		Song s = new Song();
		s.setArtist(artist);
		s.setTitle(title);
		s.setId(id);

		mt.getSongs().add(s);
	}

	private void addUser(Mixtape mt, int userId, String name) {
		User user = new User();
		user.setId(userId);
		user.setName(name);

		mt.getUsers().add(user);
	}
}
