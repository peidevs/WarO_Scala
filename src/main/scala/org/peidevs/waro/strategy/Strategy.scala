
package org.peidevs.waro.strategy

trait Strategy {
    def selectCard(prizeCard:Int, hand:List[Int], maxCard:Int): Int 
}