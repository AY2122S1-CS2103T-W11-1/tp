package fridgy.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import fridgy.commons.core.GuiSettings;
import fridgy.model.base.ReadOnlyDatabase;
import fridgy.model.ingredient.Ingredient;
import fridgy.ui.TabEnum;
import javafx.collections.ObservableList;

public interface IngredientModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Ingredient> PREDICATE_SHOW_ALL_INGREDIENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getInventoryFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setInventoryFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setInventory(ReadOnlyDatabase<Ingredient> addressBook);

    /** Returns the Inventory */
    ReadOnlyDatabase<Ingredient> getInventory();

    /**
     * Returns true if n ingredient with the same identity as {@code ingredient} exists in the address book.
     */
    boolean has(Ingredient ingredient);

    /**
     * Deletes the given ingredient.
     * The ingredient must exist in the address book.
     */
    void delete(Ingredient target);

    /**
     * Adds the given ingredient.
     * {@code ingredient} must not already exist in the address book.
     */
    void add(Ingredient ingredient);

    /**
     * Sorts the inventory of ingredients using the specified comparator.
     */
    void sortIngredient(Comparator<Ingredient> comparator);

    /**
     * Replaces the given ingredient {@code target} with {@code editedIngredient}.
     * {@code target} must exist in the address book.
     * The ingredient identity of {@code editedIngredient} must not be the same as another existing ingredient in the
     * address book.
     */
    void set(Ingredient target, Ingredient editedIngredient);

    /** Returns an unmodifiable view of the filtered ingredient list */
    ObservableList<Ingredient> getFilteredIngredientList();

    /**
     * Updates the filter of the filtered ingredient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredIngredientList(Predicate<Ingredient> predicate);

    /**
     * Puts a new Ingredient under an {@code Observable}.
     *
     * @param activeIngredient to be placed under an Observable
     */
    void setActiveIngredient(Ingredient activeIngredient);

    /**
     * Makes Ingredient tab under an {@code Observable}.
     *
     * @param tabEnum to be placed under an Observable
     */
    void setActiveTab(TabEnum tabEnum);
}
