
public class TestConfig {
    private static final String PROPS = "/resumes.properties";
    private static final TestConfig INSTANCE = new TestConfig();


    public static TestConfig get() {
        return INSTANCE;
    }

    private TestConfig() {
    }




    public boolean isImmutable(String uuids) {
        return true;
    }

    public void checkImmutable(String uuids) {
        /*if (immutableUuids.contains(uuids))
            throw new RuntimeException("Зарезервированные резюме нельзя менять");
         */
    }
}
