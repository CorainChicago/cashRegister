package cashRegister;

import emptyRegisterException.EmptyRegisterException;

import java.lang.reflect.Array;
import java.util.*;

public class CashRegister {

    private int purchasePrice;

    private int moneyGiven;

    private int change;

    private TreeMap<Integer, Integer> drawer;

    public CashRegister() {
        this.drawer= new TreeMap();
        drawer.put(20, 0);
        drawer.put(10, 0);
        drawer.put(5, 0);
        drawer.put(2, 0);
        drawer.put(1, 0);
    }

    public String showCurrentState(){
        StringBuilder state = new StringBuilder("");
        Integer balance = 0;
        for(Map.Entry<Integer,Integer> entry : drawer.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            balance  = balance + (key * value);
            state.insert(0, " " + value);
        }
        String total = "$" + balance.toString();
        state.insert(0, total);
        return state.toString();
    }

    public void addToDrawer(TreeMap<Integer, Integer> denominationsAdded){
        for(Map.Entry<Integer,Integer> entry : denominationsAdded.entrySet()) {
            Integer key = entry.getKey();
            Integer  value = entry.getValue();

            int balance = ((Integer)drawer.get(key)).intValue();
            drawer.put(key, balance + value);
        }

    }

    public void removeCashFromRegister(TreeMap<Integer, Integer> denominationsRemoved) throws EmptyRegisterException {
        if (emptyDrawer()) {
            throw new EmptyRegisterException("The register is empty.");
        }
        for(Map.Entry<Integer,Integer> entry : denominationsRemoved.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            int balance = ((Integer) drawer.get(key)).intValue();

            drawer.put(key, balance - value);
        }
    }

    public String makeChange(Integer changeAmount){
        if (changeAmount == 0 || emptyDrawer()){
            return "sorry";
        };

        String changeDenominations = new String();
        Iterator drawerKeysReversed = drawer.descendingKeySet().iterator();

        while(drawerKeysReversed.hasNext()){
            Integer denomination = Integer.parseInt(drawerKeysReversed.next().toString());
            Integer denominationCount =  ((Integer) drawer.get(denomination)).intValue();
            if (changeAmount / denomination > 0 && denominationCount > 0 ){
                Integer count = 0;
                do {
                    count +=1;
                    changeAmount -= denomination;

                }while(changeAmount / denomination > 0 && count <= denominationCount);
                if(denomination == 5 && ((Integer) drawer.get(1)).intValue() == 0){
                    count -= 1;
                    changeAmount += denomination;
                }
                changeDenominations += count + " ";
            } else {
                changeDenominations += "0 ";
            }
        }
        if (changeAmount != 0){
            return "sorry";
        }

        return changeDenominations.substring(0, changeDenominations.length() - 1);

    }

    private Boolean emptyDrawer(){
         return drawer.entrySet().stream().allMatch(entry -> {
             return entry.getValue() == 0;
         });
    }
}
