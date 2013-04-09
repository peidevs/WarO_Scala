
package org.peidevs.waro.domain

class PlayerStats(var total: Int, var numRoundsWon:Int, var numGamesWon:Int) {
	def clear() {
	    total = 0
	    numRoundsWon = 0
	    numGamesWon = 0
	}
}