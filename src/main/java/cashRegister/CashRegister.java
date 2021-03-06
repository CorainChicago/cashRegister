package cashRegister;

import apple.laf.JRSUIUtils;
import emptyRegisterException.EmptyRegisterException;

import java.util.*;

public class CashRegister {

    private TreeMap<Integer, Integer> register;

    public CashRegister() {
        this.register = new TreeMap();
        register.put(20, 0);
        register.put(10, 0);
        register.put(5, 0);
        register.put(2, 0);
        register.put(1, 0);
    }

    public CashRegister(TreeMap<Integer, Integer> register) {
        this.register = register;
    }

    public String showCurrentState() {
        StringBuilder state = new StringBuilder("");
        for (Map.Entry<Integer, Integer> entry : register.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            state.insert(0, " " + value);
        }

        String total = "$" + getRegisterTotal();
        state.insert(0, total);
        return state.toString();
    }

    public void addToDrawer(TreeMap<Integer, Integer> denominationsAdded) {
        for (Map.Entry<Integer, Integer> entry : denominationsAdded.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            int balance = ((Integer) register.get(key)).intValue();
            register.put(key, balance + value);
        }

    }

    public void removeCashFromRegister(TreeMap<Integer, Integer> denominationsRemoved) throws EmptyRegisterException {
        TreeMap<Integer, Integer> startingRegister = register;
        for (Map.Entry<Integer, Integer> entry : denominationsRemoved.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            int balance = ((Integer) register.get(key)).intValue();
            if (emptyRegister() || balance < value) {
                register = startingRegister;
                throw new EmptyRegisterException("Cannot make more change. Sorry ");
            }
            register.put(key, balance - value);
        }
    }

    public String makeChange(Integer changeAmount) {
        if (changeAmount == 0 || emptyRegister() || getRegisterTotal() < changeAmount) {
            return "sorry";
        }

        TreeMap<Integer, Integer> registerCopy = new TreeMap<>();
        registerCopy.putAll(register);

        return calculateChange(registerCopy, changeAmount);

    }

    private Boolean emptyRegister() {
        return register.entrySet().stream().allMatch(entry -> {
            return entry.getValue() == 0;
        });
    }

    private Integer getRegisterTotal() {
        Integer balance = 0;
        for (Map.Entry<Integer, Integer> entry : register.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            balance = balance + (key * value);
        }
        return balance;
    }

    private String calculateChange(TreeMap<Integer, Integer> register, Integer changeAmount) {
        TreeMap<Integer, Integer> startRegister = register;
        String changeDenominations = new String();
        Iterator drawerKeysReversed = register.descendingKeySet().iterator();
        Integer changeAmountStorage = changeAmount;

        while (drawerKeysReversed.hasNext()) {
            Integer denomination = Integer.parseInt(drawerKeysReversed.next().toString());
            Integer denominationCount = ((Integer) register.get(denomination)).intValue();
            if (changeAmount / denomination > 0 && denominationCount > 0) {
                Integer count = 0;
                do {
                    count += 1;
                    changeAmount -= denomination;
                } while (count < denominationCount && changeAmount - denomination >= 0);
                changeDenominations += count + " ";
            } else {
                changeDenominations += "0 ";
            }
        }

        if (changeAmount != 0) {
            TreeMap<Integer, Integer> registerCopy = new TreeMap<>();
            registerCopy.putAll(register);
            Iterator registerKeysReversed = register.descendingKeySet().iterator();
            while (registerKeysReversed.hasNext()) {
                Integer denomination = Integer.parseInt(registerKeysReversed.next().toString());
                Integer denominationCount = ((Integer) register.get(denomination)).intValue();
                if (denominationCount> 0) {
                    registerCopy.put(denomination, denominationCount - 1);
                    return calculateChange(registerCopy, changeAmountStorage);
                }
            }
        }

        if (changeAmount != 0) {
            register = startRegister;
            return "sorry";
        } else {
            return changeDenominations.substring(0, changeDenominations.length() - 1);
        }

    }


//    private String calculateChange(TreeMap<Integer, Integer> register, Integer changeAmount) {
//        TreeMap<Integer, Integer> startRegister = register;
//        String changeDenominations = new String();
//        Iterator drawerKeysReversed = register.descendingKeySet().iterator();
//
//        while (drawerKeysReversed.hasNext()) {
//            Integer denomination = Integer.parseInt(drawerKeysReversed.next().toString());
//            Integer denominationCount = ((Integer) register.get(denomination)).intValue();
//            if (changeAmount / denomination > 0 && denominationCount > 0) {
//                Integer count = 0;
//                do {
//                    count += 1;
//                    changeAmount -= denomination;
//
//                } while (changeAmount / denomination > 0 && count <= denominationCount);
//
//                if (changeAmount != 0 && denomination >= 5 && ((Integer) register.get(1)).intValue() == 0 && changeAmount < 2 * (Integer) register.get(2).intValue() ) {
//                    count -= 1;
//                    changeAmount += denomination;
//                }
//                changeDenominations += count + " ";
//            } else {
//                changeDenominations += "0 ";
//            }
//        }
//        if (changeAmount != 0) {
//            register = startRegister;
//            return "sorry";
//        } else {
//            return changeDenominations.substring(0, changeDenominations.length() - 1);
//        }
//
//    }


}
