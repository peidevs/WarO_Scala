
package org.peidevs.waro.strategy

class NearestCard extends Strategy {
    def selectCard(prizeCard:Int, hand:List[Int], maxCard:Int): Int = {
        hand.minBy( x => Math.abs(x - prizeCard) )
    }
}