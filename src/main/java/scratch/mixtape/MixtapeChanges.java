package scratch.mixtape;

import java.util.List;

import scratch.mixtape.model.AddSongToPlaylist;
import scratch.mixtape.model.AddUserPlaylist;
import scratch.mixtape.model.ChangeException;
import scratch.mixtape.model.Mixtape;
import scratch.mixtape.model.Playlist;
import scratch.mixtape.model.RemovePlaylist;

public class MixtapeChanges {
	public static  void removePlaylists(Mixtape mt, List<RemovePlaylist> removePlaylist) throws ChangeException {
		if (removePlaylist == null) {
			return;
		}

		for (RemovePlaylist remove : removePlaylist) {
			for (Playlist playlist : mt.getPlaylists()) {
				if (playlist.getId() == remove.getPlaylistId()) {
					mt.getPlaylists().remove(playlist);
					return;

				}
				throw new ChangeException("playlist "+playlist.getId()+" not found");
			}

		}

	}

	public static void addUserPlaylists(Mixtape mt, List<AddUserPlaylist> addUserPlaylist) throws ChangeException {
		if (addUserPlaylist == null) {
			return;
		}

		for (AddUserPlaylist add : addUserPlaylist) {
			mt.validuser(add.getUserId());
			mt.validPlaylist(add.getPlaylist());

			if (add.getPlaylist().getSongs().isEmpty()) {
				throw new ChangeException("attempted to add empty playlist");
			}
			mt.getPlaylists().add(add.getPlaylist());
		}
	}

	public static void addSongs(Mixtape mt, List<AddSongToPlaylist> addSongToPlaylist) throws ChangeException {
		if (addSongToPlaylist == null) {
			return;
		}

		for (AddSongToPlaylist add : addSongToPlaylist) {
			mt.validSong(add.getSongId());
			for (Playlist pl : mt.getPlaylists()) {
				if (pl.getId() == add.getPlaylistId()) {
					pl.getSongs().add(add.getSongId());
				}

			}
		}

	}

}
