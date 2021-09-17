package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.AddressBookBuilder;

public class PersonModelManagerTest {

    private PersonModelManager personModelManager = new PersonModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), personModelManager.getUserPrefs());
        assertEquals(new GuiSettings(), personModelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(personModelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personModelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        personModelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, personModelManager.getUserPrefs());

        // Modifying userPrefs should not modify personModelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, personModelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personModelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        personModelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, personModelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personModelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        personModelManager.setAddressBookFilePath(path);
        assertEquals(path, personModelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personModelManager.has(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(personModelManager.has(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        personModelManager.add(ALICE);
        assertTrue(personModelManager.has(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> personModelManager.getFilteredList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        personModelManager = new PersonModelManager(addressBook, userPrefs);
        PersonModelManager personModelManagerCopy = new PersonModelManager(addressBook, userPrefs);
        assertTrue(personModelManager.equals(personModelManagerCopy));

        // same object -> returns true
        assertTrue(personModelManager.equals(personModelManager));

        // null -> returns false
        assertFalse(personModelManager.equals(null));

        // different types -> returns false
        assertFalse(personModelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(personModelManager.equals(new PersonModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        personModelManager.updateFilteredList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(personModelManager.equals(new PersonModelManager(addressBook, userPrefs)));

        // resets personModelManager to initial state for upcoming tests
        personModelManager.updateFilteredList(PREDICATE_SHOW_ALL);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(personModelManager.equals(new PersonModelManager(addressBook, differentUserPrefs)));
    }
}
