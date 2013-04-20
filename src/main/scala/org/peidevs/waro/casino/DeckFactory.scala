
package org.peidevs.waro.casino

import scala.util.Random

class DeckFactory {
        
    def newDeck(numCards:Int):List[Int] = {
        var deck:List[Int] = (1 to numCards).toList
        Random.shuffle(deck)
    }
        
}