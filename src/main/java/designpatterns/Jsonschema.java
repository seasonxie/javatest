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
        //String json="{\"data\":{\"id\":851,\"detail\":\"琴千线长征路-万盛南路附近\",\"area\":9480,\"province\":\"广东省\",\"parentArea\":2819,\"lng\":120.32438,\"district\":\"东阳市\",\"lat\":29.136176,\"city\":\"金华\"}}";
        //String schema="{\"type\":\"object\",\"properties\":{\"data\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"integer\",\"minimum\":0},\"detail\":{\"type\":\"string\"},\"area\":{\"type\":\"integer\"},\"province\":{\"type\":\"string\",\"pattern\":\"^(北京市|天津市|....|浙江省)$\"},\"parentArea\":{\"type\":\"integer\"},\"lng\":{\"type\":\"number\",\"minimum\":73,\"maximum\":135},\"district\":{\"type\":\"string\"},\"lat\":{\"type\":\"number\",\"minimum\":4,\"maximum\":54},\"city\":{\"type\":\"string\"}},\"required\":[\"id\",\"detail\",\"area\",\"province\",\"parentArea\",\"lng\",\"district\",\"lat\",\"city\"]}},\"required\":[\"data\"]}";
        System.out.println(validatorSchema(schema,json).isSuccess()+" ------------");;
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

    static String json="" +
            "{\n" +
            "  \"bid\": [\n" +
            "    {\n" +
            "      \"adm\": \"\",\n" +
            "      \"admnative\": {\n" +
            "        \"actiontrackers\": [\n" +
            "          {\n" +
            "            \"type\": \"ACTION_APP_DOWNLOAD_COMPLETE\",\n" +
            "            \"urls\": [\n" +
            "              \"http://api-app.meizu.com/apps/public/download/release?viewid=8F83B6E48C949E98FF54B31E89E7D8CEEE1509EB6D778C571CC1E7F204A275D4928BCA2B36DD0AB4D15740F9095EF1D3E5B6E1E57AE275500ECCA80B2A5E5DA23FBEF4CA8B6674C22C2FCBBE64487EC4\"\n" +
            "            ]\n" +
            "          }\n" +
            "        ],\n" +
            "        \"adlabel\": {\n" +
            "          \"h\": 0,\n" +
            "          \"w\": 0\n" +
            "        },\n" +
            "        \"assets\": [\n" +
            "          {\n" +
            "            \"id\": 0,\n" +
            "            \"required\": false,\n" +
            "            \"title\": {\n" +
            "              \"text\": \"软考助手_0518\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 10001001,\n" +
            "            \"img\": {\n" +
            "              \"h\": 420,\n" +
            "              \"url\": \"http://e.res.flyme.cn/fileserver/materiel/30/1449cfb0a1e54bdf9c1bcd1ef3d822b9.jpg\",\n" +
            "              \"w\": 984\n" +
            "            },\n" +
            "            \"required\": false\n" +
            "          }\n" +
            "        ],\n" +
            "        \"dsplogo\": {\n" +
            "          \"h\": 0,\n" +
            "          \"w\": 0\n" +
            "        },\n" +
            "        \"imptrackers\": [\n" +
            "          \"http://rtb.flyme.cn/dsp/track/impression?p=MTZGQThGMjkwRThCMUE0RjI3RDhFQ0U1NTFGOUJCMEVBQjY0NDZBMDkzOTRCMjRBRUM5QUQ3MDc2RTU3QjAxQTAxRDk5NDg4NTFDQTFCODlFNEI2MjAzMjgwMDQ0QTM4MDA5MTlGNUMzRkJGMkE0NkVENEExN0EyNDIwNjFBOTkwMTg0Rjc5RDdFNzk1ODhCQTU3OTNEQjY4RERBRDBGODk2QTIxMkExQjU4ODE5NEUwQjYyQjIwM0FDOUVEOUI0RkQzQURCRjQyQUFFNUYyMEVBRjQ2MTg5RDVCOTc0Nzg0RkFEM0UzQUU1NjQ5RjkwQjc0RURCRERFMjJCRDZDODFCNDE2QUYyQjg0QzNGOUI3REEyOTQwNDg0QjkwMDI5NjJFRDE1MUYzMzU5MzhGQjY1NTg5RDZDQjNCMTUwNkZFNTlDRDYxNzY3ODcwOEZENkVENUNCQjIxNkNDNDQyNjQ2MTYxOTg3M0E4MTRFQjAxMzFGMDE2NTQxNDY5NEVEQjFENThGNzI0NDlEQUQ5OUM1OUFCRDA0OUZGMUM3RkRBNUZDOUQ4RjBFRUQwRkYwRTBEQ0Y0RUFBM0MwQTY0MTQ4OTcwQ0IxMkJFRkU3QTA1QUI5MDBEMkEwMjMxQTNGNTk3MTU2NTFGOUE3OEUyODlGQUE1NEUxQjE1Nzk1MkI3QjQ1RjUzNDkzRjZCMjExMEZCREYxM0NGQzY3OTQxRUFCRjlFQzFFNTEzMDlCOTk3OEFDNjVGQTA0QTVFMERD&price=\"\n" +
            "        ],\n" +
            "        \"link\": {\n" +
            "          \"clicktrackers\": [\n" +
            "            \"http://rtb.flyme.cn/dsp/track/click?p=MTZGQThGMjkwRThCMUE0RjI3RDhFQ0U1NTFGOUJCMEVBQjY0NDZBMDkzOTRCMjRBRUM5QUQ3MDc2RTU3QjAxQTAxRDk5NDg4NTFDQTFCODlFNEI2MjAzMjgwMDQ0QTM4MDA5MTlGNUMzRkJGMkE0NkVENEExN0EyNDIwNjFBOTkwMTg0Rjc5RDdFNzk1ODhCQTU3OTNEQjY4RERBRDBGODk2QTIxMkExQjU4ODE5NEUwQjYyQjIwM0FDOUVEOUI0RkQzQURCRjQyQUFFNUYyMEVBRjQ2MTg5RDVCOTc0Nzg0RkFEM0UzQUU1NjQ5RjkwQjc0RURCRERFMjJCRDZDODFCNDE2QUYyQjg0QzNGOUI3REEyOTQwNDg0QjkwMDI5NjJFRDE1MUYzMzU5MzhGQjY1NTg5RDZDQjNCMTUwNkZFNTlDRDYxNzY3ODcwOEZENkVENUNCQjIxNkNDNDQyNjQ2MTYxOTg3M0E4MTRFQjAxMzFGMDE2NTQxNDY5NEVEQjFENThGNzI0NDlEQUQ5OUM1OUFCRDA0OUZGMUM3RkRBNUZDOUQ4RjBFRUQwRkYwRTBEQ0Y0RUFBM0MwQTY0MTQ4OTcwQ0IxMkJFRkU3QTA1QUI5MDBEMkEwMjMxQTNGNTk3MTU2NTFGOUE3OEUyODlGQUE1NEUxQjE1Nzk1MkI3QjQ1RjUzNDkzRjZCMjExMEZCREYxM0NGQzY3OTQxRUFCRjlFQzFFNTEzMDlCOTk3OEFDNjVGQTA0QTVFMERD&price=&lp=\"\n" +
            "          ],\n" +
            "          \"url\": \"{\\\"app_id\\\":\\\"com.hipu.yidian\\\",\\\"app_name\\\":\\\"一点资讯\\\",\\\"app_type\\\":1,\\\"application_id\\\":1497037,\\\"custom_lp\\\":false,\\\"download_url\\\":\\\"http://api-app.meizu.com/apps/public/download/release?viewid=8F83B6E48C949E98FF54B31E89E7D8CEEE1509EB6D778C571CC1E7F204A275D4928BCA2B36DD0AB4D15740F9095EF1D3E5B6E1E57AE275500ECCA80B2A5E5DA23FBEF4CA8B6674C22C2FCBBE64487EC4\\\",\\\"idea_id\\\":0,\\\"type\\\":2,\\\"url\\\":\\\"https://i3.mzres.com/resources/appStore/browser/views/browser-detail.html?packageName=com.hipu.yidian&app_id=1497037&imp=http%3A%2F%2Frtb.flyme.cn%2Flp%2Ftrack%2Fimpression%3Fp%3D%25s%26lp%3D%25s&download=http%3A%2F%2Frtb.flyme.cn%2Fdsp%2Ftrack%2Fdownload%3Fp%3D%25s%26lp%3D%25s&open=http%3A%2F%2Frtb.flyme.cn%2Fdsp%2Ftrack%2Fopen%3Fp%3D%25s%26lp%3D%25s&p=MTZGQThGMjkwRThCMUE0RjI3RDhFQ0U1NTFGOUJCMEVBQjY0NDZBMDkzOTRCMjRBRUM5QUQ3MDc2RTU3QjAxQTAxRDk5NDg4NTFDQTFCODlFNEI2MjAzMjgwMDQ0QTM4MDA5MTlGNUMzRkJGMkE0NkVENEExN0EyNDIwNjFBOTkwMTg0Rjc5RDdFNzk1ODhCQTU3OTNEQjY4RERBRDBGODk2QTIxMkExQjU4ODE5NEUwQjYyQjIwM0FDOUVEOUI0RkQzQURCRjQyQUFFNUYyMEVBRjQ2MTg5RDVCOTc0Nzg0RkFEM0UzQUU1NjQ5RjkwQjc0RURCRERFMjJCRDZDODFCNDE2QUYyQjg0QzNGOUI3REEyOTQwNDg0QjkwMDI5NjJFRDE1MUYzMzU5MzhGQjY1NTg5RDZDQjNCMTUwNkZFNTlDRDYxNzY3ODcwOEZENkVENUNCQjIxNkNDNDQyNjQ2MTYxOTg3M0E4MTRFQjAxMzFGMDE2NTQxNDY5NEVEQjFENThGNzI0NDlEQUQ5OUM1OUFCRDA0OUZGMUM3RkRBNUZDOUQ4RjBFRUQwRkYwRTBEQ0Y0RUFBM0MwQTY0MTQ4OTcwQ0IxMkJFRkU3QTA1QUI5MDBEMkEwMjMxQTNGNTk3MTU2NTFGOUE3OEUyODlGQUE1NEUxQjE1Nzk1MkI3QjQ1RjUzNDkzRjZCMjExMEZCREYxM0NGQzY3OTQxRUFCRjlFQzFFNTEzMDlCOTk3OEFDNjVGQTA0QTVFMERD&lp=https%3A%2F%2Fi3.mzres.com%2Fresources%2FappStore%2Fbrowser%2Fviews%2Fbrowser-detail.html%3FpackageName%3Dcom.hipu.yidian%26app_id%3D1497037\\\",\\\"version_code\\\":\\\"36001\\\"}\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"admvideo\": \"\",\n" +
            "      \"adxprice\": 4742,\n" +
            "      \"bidtype\": 0,\n" +
            "      \"impid\": \"0000000000001\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"adm\": \"\",\n" +
            "      \"admnative\": {\n" +
            "        \"actiontrackers\": [\n" +
            "          {\n" +
            "            \"type\": \"ACTION_APP_DOWNLOAD_COMPLETE\",\n" +
            "            \"urls\": [\n" +
            "              \"http://api-app.meizu.com/apps/public/download/release?viewid=F4BBA2C0FB6BA615AAF8D530639A08E0EE1509EB6D778C571CC1E7F204A275D4928BCA2B36DD0AB4D15740F9095EF1D3E5B6E1E57AE275500ECCA80B2A5E5DA2C0DC2EFEC815B5AA59B5080B257D7295\"\n" +
            "            ]\n" +
            "          }\n" +
            "        ],\n" +
            "        \"adlabel\": {\n" +
            "          \"h\": 0,\n" +
            "          \"w\": 0\n" +
            "        },\n" +
            "        \"assets\": [\n" +
            "          {\n" +
            "            \"id\": 0,\n" +
            "            \"required\": false,\n" +
            "            \"title\": {\n" +
            "              \"text\": \"恭喜发财`红包拿来~\"\n" +
            "            }\n" +
            "          },\n" +
            "          {\n" +
            "            \"id\": 10001001,\n" +
            "            \"img\": {\n" +
            "              \"h\": 198,\n" +
            "              \"url\": \"http://e.res.flyme.cn/fileserver/materiel/1/667377035b384022a33f50291bb155a2.jpg\",\n" +
            "              \"w\": 267\n" +
            "            },\n" +
            "            \"required\": false\n" +
            "          }\n" +
            "        ],\n" +
            "        \"dsplogo\": {\n" +
            "          \"h\": 0,\n" +
            "          \"w\": 0\n" +
            "        },\n" +
            "        \"imptrackers\": [\n" +
            "          \"http://rtb.flyme.cn/dsp/track/impression?p=MTZGQThGMjkwRThCMUE0RjI3RDhFQ0U1NTFGOUJCMEVBQjY0NDZBMDkzOTRCMjRBRUM5QUQ3MDc2RTU3QjAxQTAxRDk5NDg4NTFDQTFCODlFNEI2MjAzMjgwMDQ0QTM4OTI2NjNGRUJFRkRGMUFERUY4RkM5OUU0QTNCMjQwNDgwMTg0Rjc5RDdFNzk1ODhCQTU3OTNEQjY4RERBRDBGODk2QTIxMkExQjU4ODE5NEUwQjYyQjIwM0FDOUVEOUI0RkQzQURCRjQyQUFFNUYyMEVBRjQ2MTg5RDVCOTc0Nzg0RkFEM0UzQUU1NjQ5RjkwQjc0RURCRERFMjJCRDZDODFCNDE2QUYyQjg0QzNGOUI3REEyOTQwNDg0QjkwMDI5OEIyRUQ5NDk4NTQ4NkNFM0VGMEI3RTA5NzM3QjJGODM2NzZCQzRBQkI5RkQ3MEJGMjA2REE0MDI2NEU4MTM5RDhFN0I3NkQxOTc5MUJBMkIyQzVGMEQ1OTBBMTU4NjAxRUE4QUI2N0EwODQ5QUYzM0QxRDE4MjAzRjE5RjFDOEVBNUZDOUQ4RjBFRUQwRkYwRTBEQ0Y0RUFBM0MwQTY0MTQ4OTcwQ0IxMkJFRkU3QTA1QUI5MDBEMkEwMjMxQTNGNURGQ0FBMzcwNjFBNERBRjNENzQxMkI1QjhFMjgxMkRBMTMxOTEzNDc1QzQ1RTZENTk5QTFDN0VBM0Y0NzA0MDRGMjkyREIzQjlCODhFQzRCNUExNjIwRDFDRjI2QkQ2&price=\"\n" +
            "        ],\n" +
            "        \"link\": {\n" +
            "          \"clicktrackers\": [\n" +
            "            \"http://rtb.flyme.cn/dsp/track/click?p=MTZGQThGMjkwRThCMUE0RjI3RDhFQ0U1NTFGOUJCMEVBQjY0NDZBMDkzOTRCMjRBRUM5QUQ3MDc2RTU3QjAxQTAxRDk5NDg4NTFDQTFCODlFNEI2MjAzMjgwMDQ0QTM4OTI2NjNGRUJFRkRGMUFERUY4RkM5OUU0QTNCMjQwNDgwMTg0Rjc5RDdFNzk1ODhCQTU3OTNEQjY4RERBRDBGODk2QTIxMkExQjU4ODE5NEUwQjYyQjIwM0FDOUVEOUI0RkQzQURCRjQyQUFFNUYyMEVBRjQ2MTg5RDVCOTc0Nzg0RkFEM0UzQUU1NjQ5RjkwQjc0RURCRERFMjJCRDZDODFCNDE2QUYyQjg0QzNGOUI3REEyOTQwNDg0QjkwMDI5OEIyRUQ5NDk4NTQ4NkNFM0VGMEI3RTA5NzM3QjJGODM2NzZCQzRBQkI5RkQ3MEJGMjA2REE0MDI2NEU4MTM5RDhFN0I3NkQxOTc5MUJBMkIyQzVGMEQ1OTBBMTU4NjAxRUE4QUI2N0EwODQ5QUYzM0QxRDE4MjAzRjE5RjFDOEVBNUZDOUQ4RjBFRUQwRkYwRTBEQ0Y0RUFBM0MwQTY0MTQ4OTcwQ0IxMkJFRkU3QTA1QUI5MDBEMkEwMjMxQTNGNURGQ0FBMzcwNjFBNERBRjNENzQxMkI1QjhFMjgxMkRBMTMxOTEzNDc1QzQ1RTZENTk5QTFDN0VBM0Y0NzA0MDRGMjkyREIzQjlCODhFQzRCNUExNjIwRDFDRjI2QkQ2&price=&lp=\"\n" +
            "          ],\n" +
            "          \"url\": \"{\\\"app_id\\\":\\\"com.jxedt\\\",\\\"app_name\\\":\\\"驾校一点通\\\",\\\"app_type\\\":1,\\\"application_id\\\":1288903,\\\"custom_lp\\\":false,\\\"download_url\\\":\\\"http://api-app.meizu.com/apps/public/download/release?viewid=F4BBA2C0FB6BA615AAF8D530639A08E0EE1509EB6D778C571CC1E7F204A275D4928BCA2B36DD0AB4D15740F9095EF1D3E5B6E1E57AE275500ECCA80B2A5E5DA2C0DC2EFEC815B5AA59B5080B257D7295\\\",\\\"idea_id\\\":0,\\\"type\\\":2,\\\"url\\\":\\\"https://i3.mzres.com/resources/appStore/browser/views/browser-detail.html?packageName=com.jxedt&app_id=1288903&imp=http%3A%2F%2Frtb.flyme.cn%2Flp%2Ftrack%2Fimpression%3Fp%3D%25s%26lp%3D%25s&download=http%3A%2F%2Frtb.flyme.cn%2Fdsp%2Ftrack%2Fdownload%3Fp%3D%25s%26lp%3D%25s&open=http%3A%2F%2Frtb.flyme.cn%2Fdsp%2Ftrack%2Fopen%3Fp%3D%25s%26lp%3D%25s&p=MTZGQThGMjkwRThCMUE0RjI3RDhFQ0U1NTFGOUJCMEVBQjY0NDZBMDkzOTRCMjRBRUM5QUQ3MDc2RTU3QjAxQTAxRDk5NDg4NTFDQTFCODlFNEI2MjAzMjgwMDQ0QTM4OTI2NjNGRUJFRkRGMUFERUY4RkM5OUU0QTNCMjQwNDgwMTg0Rjc5RDdFNzk1ODhCQTU3OTNEQjY4RERBRDBGODk2QTIxMkExQjU4ODE5NEUwQjYyQjIwM0FDOUVEOUI0RkQzQURCRjQyQUFFNUYyMEVBRjQ2MTg5RDVCOTc0Nzg0RkFEM0UzQUU1NjQ5RjkwQjc0RURCRERFMjJCRDZDODFCNDE2QUYyQjg0QzNGOUI3REEyOTQwNDg0QjkwMDI5OEIyRUQ5NDk4NTQ4NkNFM0VGMEI3RTA5NzM3QjJGODM2NzZCQzRBQkI5RkQ3MEJGMjA2REE0MDI2NEU4MTM5RDhFN0I3NkQxOTc5MUJBMkIyQzVGMEQ1OTBBMTU4NjAxRUE4QUI2N0EwODQ5QUYzM0QxRDE4MjAzRjE5RjFDOEVBNUZDOUQ4RjBFRUQwRkYwRTBEQ0Y0RUFBM0MwQTY0MTQ4OTcwQ0IxMkJFRkU3QTA1QUI5MDBEMkEwMjMxQTNGNURGQ0FBMzcwNjFBNERBRjNENzQxMkI1QjhFMjgxMkRBMTMxOTEzNDc1QzQ1RTZENTk5QTFDN0VBM0Y0NzA0MDRGMjkyREIzQjlCODhFQzRCNUExNjIwRDFDRjI2QkQ2&lp=https%3A%2F%2Fi3.mzres.com%2Fresources%2FappStore%2Fbrowser%2Fviews%2Fbrowser-detail.html%3FpackageName%3Dcom.jxedt%26app_id%3D1288903\\\",\\\"version_code\\\":\\\"4010\\\"}\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"admvideo\": \"\",\n" +
            "      \"adxprice\": 4742,\n" +
            "      \"bidtype\": 0,\n" +
            "      \"impid\": \"0000000000002\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"code\": 200,\n" +
            "  \"id\": \"1001\"\n" +
            "}";

    static String schema="" +
            "{\n" +
            "  \"definitions\": {\n" +
            "    \n" +
            "  },\n" +
            "  \"$schema\": \"http://json-schema.org/draft-06/schema#\",\n" +
            "  \"$id\": \"http://example.com/example.json\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"bid\": {\n" +
            "      \"$id\": \"/properties/bid\",\n" +
            "      \"type\": \"array\",\n" +
            "      \"items\": {\n" +
            "        \"$id\": \"/properties/bid/items\",\n" +
            "        \"type\": \"object\",\n" +
            "        \"properties\": {\n" +
            "          \"adm\": {\n" +
            "            \"$id\": \"/properties/bid/items/properties/adm\",\n" +
            "            \"type\": \"string\",\n" +
            "            \"title\": \"The Adm Schema.\",\n" +
            "            \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "            \"default\": \"\",\n" +
            "            \"examples\": [\n" +
            "              \"\"\n" +
            "            ]\n" +
            "          },\n" +
            "          \"admnative\": {\n" +
            "            \"$id\": \"/properties/bid/items/properties/admnative\",\n" +
            "            \"type\": \"object\",\n" +
            "            \"properties\": {\n" +
            "              \"actiontrackers\": {\n" +
            "                \"$id\": \"/properties/bid/items/properties/admnative/properties/actiontrackers\",\n" +
            "                \"type\": \"array\",\n" +
            "                \"items\": {\n" +
            "                  \"$id\": \"/properties/bid/items/properties/admnative/properties/actiontrackers/items\",\n" +
            "                  \"type\": \"object\",\n" +
            "                  \"properties\": {\n" +
            "                    \"type\": {\n" +
            "                      \"$id\": \"/properties/bid/items/properties/admnative/properties/actiontrackers/items/properties/type\",\n" +
            "                      \"type\": \"string\",\n" +
            "                      \"title\": \"The Type Schema.\",\n" +
            "                      \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                      \"default\": \"\",\n" +
            "                      \"examples\": [\n" +
            "                        \"ACTION_APP_DOWNLOAD_COMPLETE\"\n" +
            "                      ]\n" +
            "                    },\n" +
            "                    \"urls\": {\n" +
            "                      \"$id\": \"/properties/bid/items/properties/admnative/properties/actiontrackers/items/properties/urls\",\n" +
            "                      \"type\": \"array\",\n" +
            "                      \"items\": {\n" +
            "                        \"$id\": \"/properties/bid/items/properties/admnative/properties/actiontrackers/items/properties/urls/items\",\n" +
            "                        \"type\": \"string\",\n" +
            "                        \"title\": \"The 0 Schema.\",\n" +
            "                        \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                        \"default\": \"\",\n" +
            "                        \"examples\": [\n" +
            "                          \"http://api-app.meizu.com/apps/public/download/release?viewid=8F83B6E48C949E98FF54B31E89E7D8CEEE1509EB6D778C571CC1E7F204A275D4928BCA2B36DD0AB4D15740F9095EF1D3E5B6E1E57AE275500ECCA80B2A5E5DA23FBEF4CA8B6674C22C2FCBBE64487EC4\"\n" +
            "                        ]\n" +
            "                      }\n" +
            "                    }\n" +
            "                  }\n" +
            "                }\n" +
            "              },\n" +
            "              \"adlabel\": {\n" +
            "                \"$id\": \"/properties/bid/items/properties/admnative/properties/adlabel\",\n" +
            "                \"type\": \"object\",\n" +
            "                \"properties\": {\n" +
            "                  \"h\": {\n" +
            "                    \"$id\": \"/properties/bid/items/properties/admnative/properties/adlabel/properties/h\",\n" +
            "                    \"type\": \"integer\",\n" +
            "                    \"title\": \"The H Schema.\",\n" +
            "                    \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                    \"default\": 0,\n" +
            "                    \"examples\": [\n" +
            "                      0\n" +
            "                    ]\n" +
            "                  },\n" +
            "                  \"w\": {\n" +
            "                    \"$id\": \"/properties/bid/items/properties/admnative/properties/adlabel/properties/w\",\n" +
            "                    \"type\": \"integer\",\n" +
            "                    \"title\": \"The W Schema.\",\n" +
            "                    \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                    \"default\": 0,\n" +
            "                    \"examples\": [\n" +
            "                      0\n" +
            "                    ]\n" +
            "                  }\n" +
            "                }\n" +
            "              },\n" +
            "              \"assets\": {\n" +
            "                \"$id\": \"/properties/bid/items/properties/admnative/properties/assets\",\n" +
            "                \"type\": \"array\",\n" +
            "                \"items\": {\n" +
            "                  \"$id\": \"/properties/bid/items/properties/admnative/properties/assets/items\",\n" +
            "                  \"type\": \"object\",\n" +
            "                  \"properties\": {\n" +
            "                    \"id\": {\n" +
            "                      \"$id\": \"/properties/bid/items/properties/admnative/properties/assets/items/properties/id\",\n" +
            "                      \"type\": \"integer\",\n" +
            "                      \"title\": \"The Id Schema.\",\n" +
            "                      \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                      \"default\": 0,\n" +
            "                      \"examples\": [\n" +
            "                        0\n" +
            "                      ]\n" +
            "                    },\n" +
            "                    \"required\": {\n" +
            "                      \"$id\": \"/properties/bid/items/properties/admnative/properties/assets/items/properties/required\",\n" +
            "                      \"type\": \"boolean\",\n" +
            "                      \"title\": \"The Required Schema.\",\n" +
            "                      \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                      \"default\": false,\n" +
            "                      \"examples\": [\n" +
            "                        false\n" +
            "                      ]\n" +
            "                    },\n" +
            "                    \"title\": {\n" +
            "                      \"$id\": \"/properties/bid/items/properties/admnative/properties/assets/items/properties/title\",\n" +
            "                      \"type\": \"object\",\n" +
            "                      \"properties\": {\n" +
            "                        \"text\": {\n" +
            "                          \"$id\": \"/properties/bid/items/properties/admnative/properties/assets/items/properties/title/properties/text\",\n" +
            "                          \"type\": \"string\",\n" +
            "                          \"title\": \"The Text Schema.\",\n" +
            "                          \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                          \"default\": \"\",\n" +
            "                          \"examples\": [\n" +
            "                            \"\\u8f6f\\u8003\\u52a9\\u624b_0518\"\n" +
            "                          ]\n" +
            "                        }\n" +
            "                      }\n" +
            "                    }\n" +
            "                  }\n" +
            "                }\n" +
            "              },\n" +
            "              \"dsplogo\": {\n" +
            "                \"$id\": \"/properties/bid/items/properties/admnative/properties/dsplogo\",\n" +
            "                \"type\": \"object\",\n" +
            "                \"properties\": {\n" +
            "                  \"h\": {\n" +
            "                    \"$id\": \"/properties/bid/items/properties/admnative/properties/dsplogo/properties/h\",\n" +
            "                    \"type\": \"integer\",\n" +
            "                    \"title\": \"The H Schema.\",\n" +
            "                    \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                    \"default\": 0,\n" +
            "                    \"examples\": [\n" +
            "                      0\n" +
            "                    ]\n" +
            "                  },\n" +
            "                  \"w\": {\n" +
            "                    \"$id\": \"/properties/bid/items/properties/admnative/properties/dsplogo/properties/w\",\n" +
            "                    \"type\": \"integer\",\n" +
            "                    \"title\": \"The W Schema.\",\n" +
            "                    \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                    \"default\": 0,\n" +
            "                    \"examples\": [\n" +
            "                      0\n" +
            "                    ]\n" +
            "                  }\n" +
            "                }\n" +
            "              },\n" +
            "              \"imptrackers\": {\n" +
            "                \"$id\": \"/properties/bid/items/properties/admnative/properties/imptrackers\",\n" +
            "                \"type\": \"array\",\n" +
            "                \"items\": {\n" +
            "                  \"$id\": \"/properties/bid/items/properties/admnative/properties/imptrackers/items\",\n" +
            "                  \"type\": \"string\",\n" +
            "                  \"title\": \"The 0 Schema.\",\n" +
            "                  \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                  \"default\": \"\",\n" +
            "                  \"examples\": [\n" +
            "                    \"http://rtb.flyme.cn/dsp/track/impression?p=MTZGQThGMjkwRThCMUE0RjI3RDhFQ0U1NTFGOUJCMEVBQjY0NDZBMDkzOTRCMjRBRUM5QUQ3MDc2RTU3QjAxQTAxRDk5NDg4NTFDQTFCODlFNEI2MjAzMjgwMDQ0QTM4MDA5MTlGNUMzRkJGMkE0NkVENEExN0EyNDIwNjFBOTkwMTg0Rjc5RDdFNzk1ODhCQTU3OTNEQjY4RERBRDBGODk2QTIxMkExQjU4ODE5NEUwQjYyQjIwM0FDOUVEOUI0RkQzQURCRjQyQUFFNUYyMEVBRjQ2MTg5RDVCOTc0Nzg0RkFEM0UzQUU1NjQ5RjkwQjc0RURCRERFMjJCRDZDODFCNDE2QUYyQjg0QzNGOUI3REEyOTQwNDg0QjkwMDI5NjJFRDE1MUYzMzU5MzhGQjY1NTg5RDZDQjNCMTUwNkZFNTlDRDYxNzY3ODcwOEZENkVENUNCQjIxNkNDNDQyNjQ2MTYxOTg3M0E4MTRFQjAxMzFGMDE2NTQxNDY5NEVEQjFENThGNzI0NDlEQUQ5OUM1OUFCRDA0OUZGMUM3RkRBNUZDOUQ4RjBFRUQwRkYwRTBEQ0Y0RUFBM0MwQTY0MTQ4OTcwQ0IxMkJFRkU3QTA1QUI5MDBEMkEwMjMxQTNGNTk3MTU2NTFGOUE3OEUyODlGQUE1NEUxQjE1Nzk1MkI3QjQ1RjUzNDkzRjZCMjExMEZCREYxM0NGQzY3OTQxRUFCRjlFQzFFNTEzMDlCOTk3OEFDNjVGQTA0QTVFMERD&price=\"\n" +
            "                  ]\n" +
            "                }\n" +
            "              },\n" +
            "              \"link\": {\n" +
            "                \"$id\": \"/properties/bid/items/properties/admnative/properties/link\",\n" +
            "                \"type\": \"object\",\n" +
            "                \"properties\": {\n" +
            "                  \"clicktrackers\": {\n" +
            "                    \"$id\": \"/properties/bid/items/properties/admnative/properties/link/properties/clicktrackers\",\n" +
            "                    \"type\": \"array\",\n" +
            "                    \"items\": {\n" +
            "                      \"$id\": \"/properties/bid/items/properties/admnative/properties/link/properties/clicktrackers/items\",\n" +
            "                      \"type\": \"string\",\n" +
            "                      \"title\": \"The 0 Schema.\",\n" +
            "                      \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                      \"default\": \"\",\n" +
            "                      \"examples\": [\n" +
            "                        \"http://rtb.flyme.cn/dsp/track/click?p=MTZGQThGMjkwRThCMUE0RjI3RDhFQ0U1NTFGOUJCMEVBQjY0NDZBMDkzOTRCMjRBRUM5QUQ3MDc2RTU3QjAxQTAxRDk5NDg4NTFDQTFCODlFNEI2MjAzMjgwMDQ0QTM4MDA5MTlGNUMzRkJGMkE0NkVENEExN0EyNDIwNjFBOTkwMTg0Rjc5RDdFNzk1ODhCQTU3OTNEQjY4RERBRDBGODk2QTIxMkExQjU4ODE5NEUwQjYyQjIwM0FDOUVEOUI0RkQzQURCRjQyQUFFNUYyMEVBRjQ2MTg5RDVCOTc0Nzg0RkFEM0UzQUU1NjQ5RjkwQjc0RURCRERFMjJCRDZDODFCNDE2QUYyQjg0QzNGOUI3REEyOTQwNDg0QjkwMDI5NjJFRDE1MUYzMzU5MzhGQjY1NTg5RDZDQjNCMTUwNkZFNTlDRDYxNzY3ODcwOEZENkVENUNCQjIxNkNDNDQyNjQ2MTYxOTg3M0E4MTRFQjAxMzFGMDE2NTQxNDY5NEVEQjFENThGNzI0NDlEQUQ5OUM1OUFCRDA0OUZGMUM3RkRBNUZDOUQ4RjBFRUQwRkYwRTBEQ0Y0RUFBM0MwQTY0MTQ4OTcwQ0IxMkJFRkU3QTA1QUI5MDBEMkEwMjMxQTNGNTk3MTU2NTFGOUE3OEUyODlGQUE1NEUxQjE1Nzk1MkI3QjQ1RjUzNDkzRjZCMjExMEZCREYxM0NGQzY3OTQxRUFCRjlFQzFFNTEzMDlCOTk3OEFDNjVGQTA0QTVFMERD&price=&lp=\"\n" +
            "                      ]\n" +
            "                    }\n" +
            "                  },\n" +
            "                  \"url\": {\n" +
            "                    \"$id\": \"/properties/bid/items/properties/admnative/properties/link/properties/url\",\n" +
            "                    \"type\": \"string\",\n" +
            "                    \"title\": \"The Url Schema.\",\n" +
            "                    \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "                    \"default\": \"\",\n" +
            "                    \"examples\": [\n" +
            "                      \"{\\\"app_id\\\":\\\"com.hipu.yidian\\\",\\\"app_name\\\":\\\"\\u4e00\\u70b9\\u8d44\\u8baf\\\",\\\"app_type\\\":1,\\\"application_id\\\":1497037,\\\"custom_lp\\\":false,\\\"download_url\\\":\\\"http://api-app.meizu.com/apps/public/download/release?viewid=8F83B6E48C949E98FF54B31E89E7D8CEEE1509EB6D778C571CC1E7F204A275D4928BCA2B36DD0AB4D15740F9095EF1D3E5B6E1E57AE275500ECCA80B2A5E5DA23FBEF4CA8B6674C22C2FCBBE64487EC4\\\",\\\"idea_id\\\":0,\\\"type\\\":2,\\\"url\\\":\\\"https://i3.mzres.com/resources/appStore/browser/views/browser-detail.html?packageName=com.hipu.yidian&app_id=1497037&imp=http%3A%2F%2Frtb.flyme.cn%2Flp%2Ftrack%2Fimpression%3Fp%3D%25s%26lp%3D%25s&download=http%3A%2F%2Frtb.flyme.cn%2Fdsp%2Ftrack%2Fdownload%3Fp%3D%25s%26lp%3D%25s&open=http%3A%2F%2Frtb.flyme.cn%2Fdsp%2Ftrack%2Fopen%3Fp%3D%25s%26lp%3D%25s&p=MTZGQThGMjkwRThCMUE0RjI3RDhFQ0U1NTFGOUJCMEVBQjY0NDZBMDkzOTRCMjRBRUM5QUQ3MDc2RTU3QjAxQTAxRDk5NDg4NTFDQTFCODlFNEI2MjAzMjgwMDQ0QTM4MDA5MTlGNUMzRkJGMkE0NkVENEExN0EyNDIwNjFBOTkwMTg0Rjc5RDdFNzk1ODhCQTU3OTNEQjY4RERBRDBGODk2QTIxMkExQjU4ODE5NEUwQjYyQjIwM0FDOUVEOUI0RkQzQURCRjQyQUFFNUYyMEVBRjQ2MTg5RDVCOTc0Nzg0RkFEM0UzQUU1NjQ5RjkwQjc0RURCRERFMjJCRDZDODFCNDE2QUYyQjg0QzNGOUI3REEyOTQwNDg0QjkwMDI5NjJFRDE1MUYzMzU5MzhGQjY1NTg5RDZDQjNCMTUwNkZFNTlDRDYxNzY3ODcwOEZENkVENUNCQjIxNkNDNDQyNjQ2MTYxOTg3M0E4MTRFQjAxMzFGMDE2NTQxNDY5NEVEQjFENThGNzI0NDlEQUQ5OUM1OUFCRDA0OUZGMUM3RkRBNUZDOUQ4RjBFRUQwRkYwRTBEQ0Y0RUFBM0MwQTY0MTQ4OTcwQ0IxMkJFRkU3QTA1QUI5MDBEMkEwMjMxQTNGNTk3MTU2NTFGOUE3OEUyODlGQUE1NEUxQjE1Nzk1MkI3QjQ1RjUzNDkzRjZCMjExMEZCREYxM0NGQzY3OTQxRUFCRjlFQzFFNTEzMDlCOTk3OEFDNjVGQTA0QTVFMERD&lp=https%3A%2F%2Fi3.mzres.com%2Fresources%2FappStore%2Fbrowser%2Fviews%2Fbrowser-detail.html%3FpackageName%3Dcom.hipu.yidian%26app_id%3D1497037\\\",\\\"version_code\\\":\\\"36001\\\"}\"\n" +
            "                    ]\n" +
            "                  }\n" +
            "                }\n" +
            "              }\n" +
            "            }\n" +
            "          },\n" +
            "          \"admvideo\": {\n" +
            "            \"$id\": \"/properties/bid/items/properties/admvideo\",\n" +
            "            \"type\": \"string\",\n" +
            "            \"title\": \"The Admvideo Schema.\",\n" +
            "            \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "            \"default\": \"\",\n" +
            "            \"examples\": [\n" +
            "              \"\"\n" +
            "            ]\n" +
            "          },\n" +
            "          \"adxprice\": {\n" +
            "            \"$id\": \"/properties/bid/items/properties/adxprice\",\n" +
            "            \"type\": \"integer\",\n" +
            "            \"title\": \"The Adxprice Schema.\",\n" +
            "            \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "            \"default\": 0,\n" +
            "            \"examples\": [\n" +
            "              4742\n" +
            "            ]\n" +
            "          },\n" +
            "          \"bidtype\": {\n" +
            "            \"$id\": \"/properties/bid/items/properties/bidtype\",\n" +
            "            \"type\": \"integer\",\n" +
            "            \"title\": \"The Bidtype Schema.\",\n" +
            "            \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "            \"default\": 0,\n" +
            "            \"examples\": [\n" +
            "              0\n" +
            "            ]\n" +
            "          },\n" +
            "          \"impid\": {\n" +
            "            \"$id\": \"/properties/bid/items/properties/impid\",\n" +
            "            \"type\": \"string\",\n" +
            "            \"title\": \"The Impid Schema.\",\n" +
            "            \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "            \"default\": \"\",\n" +
            "            \"examples\": [\n" +
            "              \"0000000000001\"\n" +
            "            ]\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    \"code\": {\n" +
            "      \"$id\": \"/properties/code\",\n" +
            "      \"type\": \"integer\",\n" +
            "      \"title\": \"The Code Schema.\",\n" +
            "      \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "      \"default\": 0,\n" +
            "      \"examples\": [\n" +
            "        200\n" +
            "      ]\n" +
            "    },\n" +
            "    \"id\": {\n" +
            "      \"$id\": \"/properties/id\",\n" +
            "      \"type\": \"string\",\n" +
            "      \"title\": \"The Id Schema.\",\n" +
            "      \"contains\": \"ssssss\",\n" +
            "      \"description\": \"An explanation about the purpose of this instance.\",\n" +
            "      \"default\": \"\",\n" +
            "      \"examples\": [\n" +
            "        \"1001\"\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            ",\n" +
            "  \"required\": [\n" +
            "    \"id\"\n" +
            "  ]" +

            "}";
}
