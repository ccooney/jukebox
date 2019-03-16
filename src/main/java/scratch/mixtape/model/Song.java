package scratch.mixtape.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Song {
	private int id;
	private String artist;
	private String title;
}
