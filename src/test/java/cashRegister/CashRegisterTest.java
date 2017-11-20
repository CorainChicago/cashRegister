package cashRegister;

import emptyRegisterException.EmptyRegisterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;


public class CashRegisterTest {

    private CashRegister cashRegisterEmpty;

    private CashRegister cashRegisterA;

    private CashRegister cashRegisterB;

    private CashRegister cashRegisterC;

    private CashRegister cashRegisterD;

    private CashRegister cashRegisterE;

    @Before

    public void setUp() {
        cashRegisterEmpty = new CashRegister();

        cashRegisterA = new CashRegister();
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 13);
        cashToAdd.put(2, 0);
        cashToAdd.put(5, 0);
        cashToAdd.put(10, 0);
        cashToAdd.put(20, 0);
        cashRegisterA.addToDrawer(cashToAdd);

        cashRegisterB = new CashRegister();
        TreeMap<Integer,Integer> cashToAdd_2 = new TreeMap<Integer, Integer>();
        cashToAdd_2.put(1, 0);
        cashToAdd_2.put(2, 4);
        cashToAdd_2.put(5, 1);
        cashToAdd_2.put(10, 0);
        cashToAdd_2.put(20, 0);
        cashRegisterB.addToDrawer(cashToAdd_2);

        cashRegisterC = new CashRegister();
        TreeMap<Integer,Integer> cashToAdd_4 = new TreeMap<Integer, Integer>();
        cashToAdd_4.put(1, 1);
        cashToAdd_4.put(2, 1);
        cashToAdd_4.put(5, 0);
        cashToAdd_4.put(10, 1);
        cashToAdd_4.put(20, 0);
        cashRegisterC.addToDrawer(cashToAdd_4);

        cashRegisterD = new CashRegister();
        TreeMap<Integer,Integer> cashToAdd_5 = new TreeMap<Integer, Integer>();
        cashToAdd_5.put(1, 3);
        cashToAdd_5.put(2, 0);
        cashToAdd_5.put(5, 2);
        cashToAdd_5.put(10, 0);
        cashToAdd_5.put(20, 0);
        cashRegisterD.addToDrawer(cashToAdd_5);

        cashRegisterE = new CashRegister();
        TreeMap<Integer,Integer> cashToAdd_6 = new TreeMap<Integer, Integer>();
        cashToAdd_6.put(1, 3);
        cashToAdd_6.put(2, 0);
        cashToAdd_6.put(5, 0);
        cashToAdd_6.put(10, 1);
        cashToAdd_6.put(20, 0);
        cashRegisterE.addToDrawer(cashToAdd_6);
    }

    @Test
    public void testCashRegisterReturnsCurrentStateOfEmptyWhenEmpty() {
        cashRegisterEmpty.showCurrentState();
        Assert.assertEquals(cashRegisterEmpty.showCurrentState(), "$0 0 0 0 0 0");
    }

    @Test
    public void testCashRegisterCanFillDrawer() {
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 20);
        cashToAdd.put(2, 2);
        cashToAdd.put(5, 1);
        cashToAdd.put(10, 2);
        cashToAdd.put(20, 1);
        cashRegisterEmpty.addToDrawer(cashToAdd);
        Assert.assertEquals("$69 1 2 1 2 20", cashRegisterEmpty.showCurrentState());
        cashRegisterEmpty.addToDrawer(cashToAdd);
        Assert.assertEquals("$138 2 4 2 4 40", cashRegisterEmpty.showCurrentState());
    }

    @Test
    public void testCanRemoveCashFromRegister() throws EmptyRegisterException {
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 20);
        cashToAdd.put(2, 2);
        cashToAdd.put(5, 3);
        cashToAdd.put(10, 5);
        cashToAdd.put(20, 3);
        cashRegisterEmpty.addToDrawer(cashToAdd);
        TreeMap<Integer,Integer> cashToRemove = new TreeMap<Integer, Integer>();
        cashToRemove.put(1, 2);
        cashToRemove.put(2, 0);
        cashToRemove.put(5, 1);
        cashToRemove.put(10, 1);
        cashToRemove.put(20, 2);
        cashRegisterEmpty.removeCashFromRegister(cashToRemove);
        Assert.assertEquals("$92 1 4 2 2 18", cashRegisterEmpty.showCurrentState());

    }

    @Test(expected = Exception.class)
    public void testThrowsExceptionIfRemovingFromRegisterEmpty() throws EmptyRegisterException {
        TreeMap<Integer,Integer> cashToRemove = new TreeMap<Integer, Integer>();
        cashToRemove.put(1, 20);
        cashToRemove.put(2, 2);
        cashToRemove.put(5, 3);
        cashToRemove.put(10, 5);
        cashToRemove.put(20, 3);
        cashRegisterEmpty.removeCashFromRegister(cashToRemove);
    }

    @Test(expected = Exception.class)
    public void testThrowsExceptionIfRemoveAmountMoreThanInRegister() throws EmptyRegisterException {
        TreeMap<Integer,Integer> cashToRemove = new TreeMap<Integer, Integer>();
        cashToRemove.put(1, 20);
        cashToRemove.put(2, 4);
        cashToRemove.put(5, 5);
        cashToRemove.put(10, 2);
        cashToRemove.put(20, 3);
        cashRegisterA.removeCashFromRegister(cashToRemove);
    }


    @Test
    public void testMakeChange(){
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 0);
        cashToAdd.put(2, 4);
        cashToAdd.put(5, 3);
        cashToAdd.put(10, 0);
        cashToAdd.put(20, 1);
        cashRegisterEmpty.addToDrawer(cashToAdd);;
        Assert.assertEquals("0 0 1 3 0", cashRegisterEmpty.makeChange(11));
        Assert.assertEquals("0 0 0 0 13", cashRegisterA.makeChange(13));
        Assert.assertEquals("0 0 1 4 0", cashRegisterB.makeChange(13));
        Assert.assertEquals("0 1 0 1 1", cashRegisterC.makeChange(13));
        Assert.assertEquals("0 0 2 0 3", cashRegisterD.makeChange(13));
        Assert.assertEquals("0 1 0 0 3", cashRegisterE.makeChange(13));
        Assert.assertEquals("0 0 0 0 8", cashRegisterA.makeChange(8));
        Assert.assertEquals("0 0 0 4 0", cashRegisterB.makeChange(8));
        Assert.assertEquals("sorry", cashRegisterC.makeChange(8));
        Assert.assertEquals("0 0 1 0 3", cashRegisterD.makeChange(8));;
        Assert.assertEquals("sorry", cashRegisterE.makeChange(8));
   }

    @Test
    public void testMakeChangeReturnsErrorMessageIfChangeNotPossible(){
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 0);
        cashToAdd.put(2, 1);
        cashToAdd.put(5, 2);
        cashToAdd.put(10, 0);
        cashToAdd.put(20, 1);
        cashRegisterEmpty.addToDrawer(cashToAdd);
        Assert.assertEquals("sorry", cashRegisterEmpty.makeChange(11));
        Assert.assertEquals("sorry", cashRegisterA.makeChange(14));
        Assert.assertEquals("sorry", cashRegisterB.makeChange(14));
        Assert.assertEquals("sorry", cashRegisterC.makeChange(14));
        Assert.assertEquals("sorry", cashRegisterD.makeChange(14));
        Assert.assertEquals("sorry", cashRegisterE.makeChange(14));
    }

    @Test
    public void testMakeChangeVoidsTransactionIfChangeNotPossible(){
        cashRegisterEmpty.makeChange(15);
        Assert.assertEquals("$0 0 0 0 0 0", cashRegisterEmpty.showCurrentState());
        cashRegisterA.makeChange(15);
        Assert.assertEquals("$13 0 0 0 0 13", cashRegisterA.showCurrentState());
        cashRegisterB.makeChange(15);
        Assert.assertEquals("$13 0 0 1 4 0", cashRegisterB.showCurrentState());
    }




}