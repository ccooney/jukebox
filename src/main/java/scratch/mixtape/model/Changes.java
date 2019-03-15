package scratch.mixtape.model;

import java.util.List;

import lombok.Data;

@Data
public class Changes {

	private List<AddSongToPlaylist> addSongToPlaylist;
	private List<AddUserPlaylist> addUserPlaylist;
	private List<RemovePlaylist> removePlaylist;
}
