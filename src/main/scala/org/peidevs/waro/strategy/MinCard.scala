
package org.peidevs.waro.strategy

class MinCard extends Strategy {
    def selectCard(prizeCard:Int, hand:List[Int], maxCard:Int): Int = {
        hand.min
    }
}