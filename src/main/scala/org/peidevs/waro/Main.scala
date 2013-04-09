
package org.peidevs.waro

import org.peidevs.waro.casino._

object Main {
	def main(args: Array[String]) {
	    var config = new Config()
	    var tourney = new Tourney(config)
	    tourney.playGames()
	    var dealer = new Dealer()
	    dealer.dealHands(30,2)
	    
		println("done.")
	}
}