package scratch.mixtape.model;

import java.util.List;

import lombok.Data;

@Data
public class Mixtape {

	private List<User> users;
	private List<Playlist> playlists;
	private List<Song> songs;

	public void validuser(int userId) throws ChangeException {
		for (User u : getUsers()) {
			if (u.getId() == userId) {
				return;
			}
		}

		throw new ChangeException("user "+userId+" not present");
	}

	public void validSong(int songId) throws ChangeException {
        for(Song s: getSongs()) {
        	if (s.getId()==songId) {
        		return;
        	}
        }

        throw new ChangeException("song "+songId+" not present");
    }

	public void validPlaylist(Playlist pl) throws ChangeException {
		for (int songId: pl.getSongs()) {
			validSong(songId);
		}
	}
}
