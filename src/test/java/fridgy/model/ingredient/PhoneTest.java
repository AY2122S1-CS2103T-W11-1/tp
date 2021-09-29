package fridgy.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static fridgy.testutil.Assert.assertThrows;

import fridgy.model.ingredient.Quantity;
import fridgy.testutil.Assert;
import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Quantity(null));
    }

    @Test
    public void constructor_invalidQuantity_throwsIllegalArgumentException() {
        String invalidQuantity = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Quantity(invalidQuantity));
    }

    @Test
    public void isValidQuantity() {
        // null quantity number
        Assert.assertThrows(NullPointerException.class, () -> Quantity.isValidQuantity(null));

        // invalid quantity numbers
        assertFalse(Quantity.isValidQuantity("")); // empty string
        assertFalse(Quantity.isValidQuantity(" ")); // spaces only
        assertFalse(Quantity.isValidQuantity("91")); // less than 3 numbers
        assertFalse(Quantity.isValidQuantity("quantity")); // non-numeric
        assertFalse(Quantity.isValidQuantity("9011p041")); // alphabets within digits
        assertFalse(Quantity.isValidQuantity("9312 1534")); // spaces within digits

        // valid quantity numbers
        assertTrue(Quantity.isValidQuantity("911")); // exactly 3 numbers
        assertTrue(Quantity.isValidQuantity("93121534"));
        assertTrue(Quantity.isValidQuantity("124293842033123")); // long quantity numbers
    }
}
