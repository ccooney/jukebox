package scratch.mixtape.model;

import java.util.List;

import lombok.Data;

@Data
public class Mixtape {

	private List<User> users;
	private List<Playlist> playlists;
	private List<Song> songs;
}
