package scratch.mixtape.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Playlist {
	private int id;
	@JsonProperty("song_ids")
	private List<Integer> songs;
	@JsonProperty("user_id")
	private int userId;
}