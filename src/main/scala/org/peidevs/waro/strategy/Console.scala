
package org.peidevs.waro.strategy

import java.util.Scanner

class Console extends Strategy {
    def selectCard(prizeCard:Int, hand:List[Int], maxCard:Int): Int = {
        println("\nCard in play: " + prizeCard)
        println("\nYour hand: " + hand)
        println("")

        var bid = 0
        var ok = false
        var scanner = new Scanner(System.in)

        while (!ok) {
            println("Enter your bid: ")
            bid = scanner.nextInt()

            if (hand.contains(bid)) {
                ok = true
            }            
        }

        return bid
    }
}

