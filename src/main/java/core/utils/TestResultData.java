package core.utils;

public class TestResultData {

    private String testId;
    private String testStatus;
    private String evidenceLink;
    private String testDate;

    public TestResultData(
            String testId,
            String testStatus,
            String evidenceLink,
            String testDate) {

        this.testId = testId;
        this.testStatus = testStatus;
        this.evidenceLink = evidenceLink;
        this.testDate = testDate;
    }

    public String getTestId() {
        return testId;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public String getEvidenceLink() {
        return evidenceLink;
    }

    public String getTestDate() {
        return testDate;
    }
}