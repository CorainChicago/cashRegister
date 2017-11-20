# cashRegister

This cash register only accept $20, $10, $5, $2 and $1 bills. The class has these methods:

    When currency is added to the register or removed, the input needs to be in a TreeMap of the denominations
    takes in a TreeMap of the denominations and add the amount to the register in descending order.
    (#$20 #$10 #$5 #$2 #$1)

*showCurrentState

    Returns the total and the count of each denomination

*addToDrawer

    Takes in a TreeMap of the denominations and add the amount to the register

*removeCashFromRegister

    Takes in a TreeMap of the denominations and removes the amount from the register
    Throws an error if the transaction cannot be completed in full but does not reset the register

* makeChange

    Takes in an Integer of the total change needed and returns the amount in the available denominations
    Return "Sorry" if the change available cannot fulfill the change requested and voids the transaction

## Tests

To run the tests:
```sh
$ gradle Test
```


