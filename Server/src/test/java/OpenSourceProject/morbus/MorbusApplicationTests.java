package OpenSourceProject.morbus;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MorbusApplicationTests {

	@Test
	public void JsonSetting() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		ClassPathResource resource = new ClassPathResource("static/data/SymptomList.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		String s = "";
		StringBuilder sb = new StringBuilder();
		while((s = br.readLine()) != null){
			sb.append(s);
		}
		ObjectMapper om = new ObjectMapper();
		JsonNode jsonNode = om.readTree(sb.toString());
		System.out.println(jsonNode);
	}

}
