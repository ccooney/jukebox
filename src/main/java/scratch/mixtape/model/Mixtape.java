package scratch.mixtape.model;

import java.util.List;

import lombok.Data;

@Data
public class Mixtape {

	private List<User> users;
	private List<Playlist> playlists;
	private List<Song> songs;

	public boolean validuser(int userId) {
		for (User u : getUsers()) {
			if (u.getId() == userId) {
				return true;
			}
		}

		return false;
	}

	public boolean validSong(int songId) {
        for(Song s: getSongs()) {
        	if (s.getId()==songId) {
        		return true;
        	}
        }

        return false;
    }
}
