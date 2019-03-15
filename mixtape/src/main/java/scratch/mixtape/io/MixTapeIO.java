package scratch.mixtape.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import scratch.mixtape.model.Mixtape;

public class MixTapeIO {

	private ObjectMapper mapper;

	public MixTapeIO() {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public Mixtape read(String filename) throws JsonParseException, JsonMappingException, IOException {

		File f = new File(filename);
		return mapper.readValue(f, Mixtape.class);
	}

	public void write(String filename, Mixtape mixtape) throws JsonGenerationException, JsonMappingException, IOException {
		File f = new File(filename);

		mapper.writeValue(f, mixtape);
	}
}
