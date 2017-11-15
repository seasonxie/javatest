package designpatterns;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jackson.JsonNodeReader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Jsonschema {
    private final static JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
    @Test
    public void validate_driverSet_schema2() {

        //some code to create Json schema, which we want to use data to validate

        JsonNode data = readJSONfile("src/test/resources/json/DataBadExample.json");
        JsonNode schema = readJSONfile("src/test/resources/json/Schema.json");

        ProcessingReport report =
                factory.getValidator().validateUnchecked(schema, data);
        Assert.assertFalse(report.isSuccess());

        // assert error message
        Iterator<ProcessingMessage> it = report.iterator();
        Assert.assertEquals(
                "instance type (string) does not match any allowed primitive type (allowed: [\"integer\"])",
                it.next().getMessage());

    }

    public static void main(String[] args) throws IOException, ProcessingException {
        String json="{\"data\":{\"id\":851,\"detail\":\"琴千线长征路-万盛南路附近\",\"area\":9480,\"province\":\"广东省\",\"parentArea\":2819,\"lng\":120.32438,\"district\":\"东阳市\",\"lat\":29.136176,\"city\":\"金华\"}}";
        String schema="{\"type\":\"object\",\"properties\":{\"data\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"integer\",\"minimum\":0},\"detail\":{\"type\":\"string\"},\"area\":{\"type\":\"integer\"},\"province\":{\"type\":\"string\",\"pattern\":\"^(北京市|天津市|....|浙江省)$\"},\"parentArea\":{\"type\":\"integer\"},\"lng\":{\"type\":\"number\",\"minimum\":73,\"maximum\":135},\"district\":{\"type\":\"string\"},\"lat\":{\"type\":\"number\",\"minimum\":4,\"maximum\":54},\"city\":{\"type\":\"string\"}},\"required\":[\"id\",\"detail\",\"area\",\"province\",\"parentArea\",\"lng\",\"district\",\"lat\",\"city\"]}},\"required\":[\"data\"]}";
        validatorSchema(schema,json);
    }

    @Test
    public static ProcessingReport validatorSchema(String mainSchema, String instance) throws IOException, ProcessingException {
        JsonNode mainNode = JsonLoader.fromString(mainSchema);
        JsonNode instanceNode = JsonLoader.fromString(instance);
        JsonSchema schema = factory.getJsonSchema(mainNode);
        System.out.println(schema);
        ProcessingReport processingReport = schema.validate(instanceNode);
        System.out.println(processingReport);
        return processingReport;
    }


    private JsonNode readJSONfile(String filePath) {
        JsonNode instance = null;
        try {
            instance = new JsonNodeReader().fromReader(new FileReader(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
