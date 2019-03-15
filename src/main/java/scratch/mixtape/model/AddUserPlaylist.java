package scratch.mixtape.model;

import lombok.Data;

@Data
public class AddUserPlaylist {
	private int userId;
	private Playlist playlist;
}
