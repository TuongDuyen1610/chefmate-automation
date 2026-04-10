package core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.data.LoginData;

import java.io.File;

public class JsonHelper {

    private static final String path =
            "src/test/resources/test-data/loginData.json";

    public static LoginData readLoginData() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(
                    new File(path),
                    LoginData.class
            );

        } catch (Exception e) {
            throw new RuntimeException("Không đọc được JSON", e);
        }
    }

    public static void writeLoginData(LoginData data) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(
                            new File(path),
                            data
                    );

        } catch (Exception e) {
            throw new RuntimeException("Không ghi được JSON", e);
        }
    }
}