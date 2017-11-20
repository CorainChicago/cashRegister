package cashRegister;

import emptyRegisterException.EmptyRegisterException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.TreeMap;



public class CashRegisterTest {

    private CashRegister cashRegisterEmpty;

    private CashRegister cashRegisterA;

    private CashRegister cashRegisterB;

    private CashRegister cashRegisterC;

    private CashRegister cashRegisterD;

    private CashRegister cashRegisterE;

    private CashRegister cashRegisterF;

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
        TreeMap<Integer,Integer> cashToAdd_3 = new TreeMap<Integer, Integer>();
        cashToAdd_3.put(1, 0);
        cashToAdd_3.put(2, 3);
        cashToAdd_3.put(5, 1);
        cashToAdd_3.put(10, 0);
        cashToAdd_3.put(20, 0);
        cashRegisterC.addToDrawer(cashToAdd_2);

        cashRegisterD = new CashRegister();
        TreeMap<Integer,Integer> cashToAdd_4 = new TreeMap<Integer, Integer>();
        cashToAdd_4.put(1, 1);
        cashToAdd_4.put(2, 2);
        cashToAdd_4.put(5, 0);
        cashToAdd_4.put(10, 1);
        cashToAdd_4.put(20, 0);
        cashRegisterD.addToDrawer(cashToAdd_2);

        cashRegisterE = new CashRegister();
        TreeMap<Integer,Integer> cashToAdd_5 = new TreeMap<Integer, Integer>();
        cashToAdd_5.put(1, 3);
        cashToAdd_5.put(2, 0);
        cashToAdd_5.put(5, 2);
        cashToAdd_5.put(10, 0);
        cashToAdd_5.put(20, 0);
        cashRegisterD.addToDrawer(cashToAdd_2);

        cashRegisterF = new CashRegister();
        TreeMap<Integer,Integer> cashToAdd_6 = new TreeMap<Integer, Integer>();
        cashToAdd_6.put(1, 3);
        cashToAdd_6.put(2, 0);
        cashToAdd_6.put(5, 2);
        cashToAdd_6.put(10, 0);
        cashToAdd_6.put(20, 0);
        cashRegisterF.addToDrawer(cashToAdd_2);


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
    public void testThrowExceptionIfRegisterEmpty() throws EmptyRegisterException {
        TreeMap<Integer,Integer> cashToRemove = new TreeMap<Integer, Integer>();
        cashToRemove.put(1, 20);
        cashToRemove.put(2, 2);
        cashToRemove.put(5, 3);
        cashToRemove.put(10, 5);
        cashToRemove.put(20, 3);
        cashRegisterEmpty.removeCashFromRegister(cashToRemove);
    }


    @Test
    public void testMakeChange(){
        TreeMap<Integer,Integer> cashToAdd = new TreeMap<Integer, Integer>();
        cashToAdd.put(1, 0);
        cashToAdd.put(2, 4);
        cashToAdd.put(5, 3);
        cashToAdd.put(10, 0);
        cashToAdd.put(20, 1);
        cashRegisterEmpty.addToDrawer(cashToAdd);
        cashRegisterEmpty.makeChange(11);
        Assert.assertEquals("0 0 1 3 0", cashRegisterEmpty.makeChange(11));
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
//        Assert.assertEquals("sorry", cashRegisterEmpty.makeChange(11));
        Assert.assertEquals("sorry", cashRegisterA.makeChange(14));
//        Assert.assertEquals("sorry", cashRegisterB.makeChange(14));
//        Assert.assertEquals("sorry", cashRegisterC.makeChange(14));
//        Assert.assertEquals("sorry", cashRegisterD.makeChange(14));
//        Assert.assertEquals("sorry", cashRegisterE.makeChange(14));
//        Assert.assertEquals("sorry", cashRegisterF.makeChange(14));
    }




}