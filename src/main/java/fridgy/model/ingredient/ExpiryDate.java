package fridgy.model.ingredient;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fridgy.commons.util.AppUtil;

/**
 * Represents an Ingredient's Expiry Date in the Inventory.
 * Guarantees: immutable; is valid as declared in {@link #isValidExpiry(String)}
 */
public class ExpiryDate implements Comparable<ExpiryDate> {

    public static final String MESSAGE_CONSTRAINTS =
            "Expiry Date should be in the format DD-MM-YYYY";
    public static final String VALIDATION_REGEX = "^([0-2]\\d|3[01])-([0]\\d|1[0-2])-\\d{4}$";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final LocalDate expiryDate;

    /**
     * Constructs a {@code ExpiryDate}.
     *
     * @param date A valid date.
     */
    public ExpiryDate(String date) {
        requireNonNull(date);
        AppUtil.checkArgument(isValidExpiry(date), MESSAGE_CONSTRAINTS);
        expiryDate = LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidExpiry(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return expiryDate.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDate // instanceof handles nulls
                && expiryDate.equals(((ExpiryDate) other).expiryDate)); // state check
    }

    @Override
    public int hashCode() {
        return expiryDate.hashCode();
    }

    @Override
    public int compareTo(ExpiryDate o) {
        return expiryDate.compareTo(o.expiryDate);
    }
}
