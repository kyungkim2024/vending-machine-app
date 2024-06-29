package com.techelevator;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ItemsTest {
    @Test
    public void testInitializeItemsDetails() {
        List<Items> itemDetails = Items.initializieItemsDetails();
        assertEquals(16, itemDetails.size());
        // Check if all items are loaded
    }

    @Test
    public void testGetItemBySlotLocation() {
        Items item = Items.getItemBySlotLocation("A1");
        assertNotNull(item); // Check if item exists
        assertEquals("Potato Crisps", item.getItemName());
        // Check if the correct item is returned
    }
}
