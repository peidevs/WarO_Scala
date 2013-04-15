
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
    
    "max card" can {
        val maxCard = new MaxCard()
        val bid = maxCard.selectCard(8, List(4,5,6), 10)
        
        "select a card" in {
            6 mustEqual bid
        }
    }    
    
    "min card" can {
        val minCard = new MinCard()
        val bid = minCard.selectCard(11, List(8,2,17), 30)
        
        "select a card" in {
            2 mustEqual bid
        }
    }    

    "nearest card" can {
        val nearestCard = new NearestCard()
        val bid = nearestCard.selectCard(11, List(10,4,13,20), 30)
        
        "select a card" in {
            10 mustEqual bid
        }
    }    
    
}