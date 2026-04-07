package core.utils;

import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import com.google.auth.oauth2.GoogleCredentials;

/**
 * GoogleSheetService.java
 * ✅ Tích hợp Google Sheets API
 */
public class GoogleSheetService {
    private static final Logger logger = LoggerFactory.getLogger(GoogleSheetService.class);

    private static final String SPREADSHEET_ID = "1jI-okFExo8SWSbHhwEqbRizhs-RtsbQUhC45WssomZs";
    private static final String SHEET_NAME = "Sheet1";
    private static final String CREDENTIALS_PATH = "src/main/resources/credentials/google-service-account.json";

    private static Sheets sheetsService;

    /**
     * ✅ Initialize Google Sheets Service
     */
    public static Sheets getSheetService() throws IOException, GeneralSecurityException {

        if (sheetsService == null) {

            GoogleCredentials credentials = GoogleCredentials
                    .fromStream(new FileInputStream(CREDENTIALS_PATH))
                    .createScoped(Arrays.asList(
                            "https://www.googleapis.com/auth/spreadsheets",
                            "https://www.googleapis.com/auth/drive"
                    ));

            sheetsService = new Sheets.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    new HttpCredentialsAdapter(credentials)
            )
                    .setApplicationName("ChefMate Automation")
                    .build();

            logger.info("✅ Google Sheets Service initialized");
        }

        return sheetsService;
    }
    /**
     * ✅ Update specific cell
     */
    public static void updateCell(String cellRange, String value) {
        try {
            Sheets sheets = getSheetService();

            ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays.asList(value)));

            sheets.spreadsheets().values()
                    .update(SPREADSHEET_ID, SHEET_NAME + "!" + cellRange, body)
                    .setValueInputOption("USER_ENTERED")
                    .execute();

            logger.info("✅ Cell updated: " + cellRange);

        } catch (Exception e) {
            logger.error("❌ Failed to update cell: " + e.getMessage());
        }
    }

    /**
     * ✅ Get all values from sheet
     */
    public static List<List<Object>> getAllValues() {
        try {
            Sheets sheets = getSheetService();

            ValueRange response = sheets.spreadsheets().values()
                    .get(SPREADSHEET_ID, SHEET_NAME + "!A11:I")
                    .execute();

            return response.getValues();

        } catch (Exception e) {
            logger.error("❌ Failed to get values: " + e.getMessage());
            return null;
        }
    }

    /**
     * ✅ Clear sheet (for testing)
     */
    public static void clearSheet() {
        try {
            Sheets sheets = getSheetService();

            sheets.spreadsheets().values()
                    .clear(SPREADSHEET_ID, SHEET_NAME + "!A11:I", new com.google.api.services.sheets.v4.model.ClearValuesRequest())
                    .execute();

            logger.info("✅ Sheet cleared");

        } catch (Exception e) {
            logger.error("❌ Failed to clear sheet: " + e.getMessage());
        }
    }
    public static void updateTestResultById(TestResultData data) {

        try {

            Sheets sheets = getSheetService();

            List<List<Object>> rows = sheets.spreadsheets().values()
                    .get(SPREADSHEET_ID, SHEET_NAME + "!A11:I")
                    .execute()
                    .getValues();

            if (rows == null || rows.isEmpty()) {
                logger.error("Sheet is empty");
                return;
            }

            int rowIndex = -1;

            for (int i = 0; i < rows.size(); i++) {

                List<Object> row = rows.get(i);

                if (row.size() > 0 &&
                        row.get(0).toString().trim()
                                .equals(data.getTestId().trim())) {

                    rowIndex = i + 11;
                    break;
                }
            }

            if (rowIndex == -1) {
                logger.error("❌ Test ID not found: " + data.getTestId());
                return;
            }

            logger.info("✅ Found Test ID at row: " + rowIndex);

            String statusCell = "G" + rowIndex;
            String evidenceCell = "H" + rowIndex;
            String dateCell = "I" + rowIndex;

            updateCell(statusCell, data.getTestStatus());
            updateCell(evidenceCell, data.getEvidenceLink());
            updateCell(dateCell, data.getTestDate());

            logger.info("✅ Google Sheet Updated: " + data.getTestId());

            AllureHelper.attachLog("Google Sheet updated for TestID: " + data.getTestId());

        } catch (Exception e) {

            logger.error("❌ Update failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}