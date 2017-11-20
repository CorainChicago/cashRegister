package cashRegister;

import cashRegister.CashRegister;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TreeMap;

public class CashRegisterTest {

    private CashRegister cashRegister;

    @Before

    public void setUp() {
        cashRegister = new CashRegister();
    }

    @Test
    public void testCashRegisterReturnsCurrentStateOfEmptyWhenEmpty() {
        cashRegister.showCurrentState();
        Assert.assertEquals(cashRegister.showCurrentState(), "$0 0 0 0 0 0");
    }

    @Test
    public void testCashRegisterCanFillDrawer() {
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 20);
        cashToAdd.put(2, 2);
        cashToAdd.put(5, 1);
        cashToAdd.put(10, 2);
        cashToAdd.put(20, 1);
        cashRegister.addToDrawer(cashToAdd);
        Assert.assertEquals("$69 1 2 1 2 20", cashRegister.showCurrentState());
    }

    @Test
    public void testCanRemoveCashFromRegister() {
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 20);
        cashToAdd.put(2, 2);
        cashToAdd.put(5, 3);
        cashToAdd.put(10, 5);
        cashToAdd.put(20, 3);
        cashRegister.addToDrawer(cashToAdd);
        TreeMap<Integer,Integer> cashToRemove = new TreeMap<Integer, Integer>();
        cashToRemove.put(1, 2);
        cashToRemove.put(2, 0);
        cashToRemove.put(5, 1);
        cashToRemove.put(10, 1);
        cashToRemove.put(20, 2);
        cashRegister.removeCashFromRegister(cashToRemove);
        Assert.assertEquals("$92 1 4 2 2 18", cashRegister.showCurrentState());
    }


    @Test
    public void testMakeChange(){
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 0);
        cashToAdd.put(2, 4);
        cashToAdd.put(5, 3);
        cashToAdd.put(10, 0);
        cashToAdd.put(20, 1);
        cashRegister.addToDrawer(cashToAdd);
        cashRegister.makeChange(11);
        Assert.assertEquals("0 0 1 3 0", cashRegister.makeChange(11));


    }

    @Test
    public void tesMakeChangeReturnsErrorIfChangeNotPossible(){
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 0);
        cashToAdd.put(2, 1);
        cashToAdd.put(5, 2);
        cashToAdd.put(10, 0);
        cashToAdd.put(20, 1);
        cashRegister.addToDrawer(cashToAdd);
        cashRegister.makeChange(11);
        Assert.assertEquals("sorry", cashRegister.makeChange(11));
        Assert.assertEquals("sorry", cashRegister.makeChange(0));

    }




}