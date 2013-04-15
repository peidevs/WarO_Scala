
package org.peidevs.waro.strategy

import org.specs2.mutable._

class StrategySpec extends Specification {
    
    "pop card" can {
        val popCard = new PopCard()
        val bid = popCard.selectCard(8, List(4,5,6), 10)
        
        "select a card" in {
            4 mustEqual bid
        }
    }
}