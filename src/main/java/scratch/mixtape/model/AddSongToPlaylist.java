package scratch.mixtape.model;

import lombok.Data;

@Data
public class AddSongToPlaylist {

	private int songId;
	private int playlistId;
}
