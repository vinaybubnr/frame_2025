package Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.logging.FileHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

public class DataReader {

	public List<HashMap<String, String>> getJsonData() throws IOException {
		// convert JSON to String

		String JsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\Data\\purchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Convert String To Hash MAP - JacksonDataBind ( Maven Repo )

		ObjectMapper mapper = new ObjectMapper();
		

		List<HashMap<String, String>> data = mapper.readValue(JsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				}); // Default IMP

		return data;

	}

}
