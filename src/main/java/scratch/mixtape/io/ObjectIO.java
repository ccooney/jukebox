package scratch.mixtape.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import scratch.mixtape.model.Mixtape;

public class ObjectIO<T> {

	private ObjectMapper mapper;

	public ObjectIO() {
		mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public T read(String filename, Class cls) throws JsonParseException, JsonMappingException, IOException {


		File f = new File(filename);
		return (T) mapper.readValue(f, cls);
	}

	public void write(String filename, T mixtape) throws JsonGenerationException, JsonMappingException, IOException {
		File f = new File(filename);

		mapper.writeValue(f, mixtape);
	}
}
