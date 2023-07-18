package org.example.machine;

import org.junit.Test;
import static org.junit.Assert.*;

public class MachineTest {
    @Test
    public void testAcceptValidCoins() {
        Machine machine = new Machine();
        assertTrue(machine.insertCoin("quarter"));
        assertTrue(machine.insertCoin("dime"));
        assertTrue(machine.insertCoin("nickel"));
    }

    @Test
    public void testRejectInvalidCoins() {
        Machine machine = new Machine();
        assertFalse(machine.insertCoin("penny"));
    }
    @Test
    public void testSelectProduct() {
        Machine machine = new Machine();
        machine.insertCoin("quarter");
        machine.insertCoin("quarter");
        machine.insertCoin("quarter");
        machine.insertCoin("quarter");
        assertEquals("THANK YOU", machine.selectProduct("cola"));
        assertEquals("INSERT COIN", machine.checkDisplay());
    }

    @Test
    public void testSelectProductInsufficientFunds() {
        Machine machine = new Machine();
        machine.insertCoin("quarter");
        assertEquals("PRICE: 1.00", machine.selectProduct("cola"));
    }
    @Test
    public void testReturnChange() {
        Machine machine = new Machine();
        machine.insertCoin("quarter");
        machine.insertCoin("quarter");
        machine.insertCoin("quarter");
        machine.insertCoin("quarter");
        assertEquals("THANK YOU", machine.selectProduct("chips"));
        assertEquals(50, machine.getChange());
    }
    @Test
    public void testReturnCoins() {
        Machine machine = new Machine();
        machine.insertCoin("quarter");
        machine.insertCoin("dime");
        assertEquals(35, machine.returnCoins());
        assertEquals("INSERT COIN", machine.checkDisplay());
    }

}
