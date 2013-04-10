
package org.peidevs.waro.domain

import org.specs2.mutable._

class PlayerStatsSpec extends Specification {
    
    "the object" can {
        var playerStats = new PlayerStats(9,9,9)
        playerStats.clear()
        
        "reset to zero" in {
            0 mustEqual playerStats.total
            0 mustEqual playerStats.numRoundsWon
            0 mustEqual playerStats.numGamesWon
        }
    }
    
    "the object 2" can {
        var playerStats = new PlayerStats(1,1,1)
        playerStats.winsRound(10)
        
        "win a prize card" in {
            11 mustEqual playerStats.total
            2 mustEqual playerStats.numRoundsWon
            1 mustEqual playerStats.numGamesWon
        }
    }
    
}
