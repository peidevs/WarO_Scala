
package org.peidevs.waro.casino

import scala.util.Random

class DeckFactory {
        
    def newDeck(numCards:Int):List[Int] = {
        var deck:List[Int] = List()
        
        for (i <- 1 to numCards) {
            deck ::= i
        }

        Random.shuffle(deck)
    }
        
}