package seedu.scheduler.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.scheduler.testutil.TypicalPersons.getTypicalIntervieweeList;
import static seedu.scheduler.testutil.TypicalPersons.getTypicalInterviewerList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.scheduler.commons.core.GuiSettings;
import seedu.scheduler.model.IntervieweeList;
import seedu.scheduler.model.InterviewerList;
import seedu.scheduler.model.ReadOnlyList;
import seedu.scheduler.model.UserPrefs;
import seedu.scheduler.model.person.Interviewee;
import seedu.scheduler.model.person.Interviewer;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonIntervieweeListStorage intervieweeListStorage =
                new JsonIntervieweeListStorage(getTempFilePath("intervieweeList"));
        JsonInterviewerListStorage interviewerListStorage =
                new JsonInterviewerListStorage(getTempFilePath("interviewerList"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(intervieweeListStorage, interviewerListStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void intervieweeListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonIntervieweeListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonIntervieweeListStorageTest} class.
         */
        IntervieweeList original = getTypicalIntervieweeList();
        storageManager.saveIntervieweeList(original);
        ReadOnlyList<Interviewee> retrieved = storageManager.readIntervieweeList().get();
        assertEquals(original, new IntervieweeList(retrieved));
    }

    @Test
    public void interviewerListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonInterviewerListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonInterviewerListStorageTest} class.
         */
        InterviewerList original = getTypicalInterviewerList();
        storageManager.saveInterviewerList(original);
        ReadOnlyList<Interviewer> retrieved = storageManager.readInterviewerList().get();
        assertEquals(original, new InterviewerList(retrieved));
    }

    @Test
    public void getUserPrefsFilePath() {
        assertNotNull(storageManager.getUserPrefsFilePath());
    }

    @Test
    public void getIntervieweeListFilePath() {
        assertNotNull(storageManager.getIntervieweeListFilePath());
    }

    @Test
    public void getInterviewerListFilePath() {
        assertNotNull(storageManager.getInterviewerListFilePath());
    }

}
