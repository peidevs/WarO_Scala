
package org.peidevs.waro.domain

import org.specs2.mutable._

class PlayerStatsSpec extends Specification {
    var playerStats = new PlayerStats(9,9,9)
    
    "the object" can {
        playerStats.clear()
        
        "reset to zero" in {
            0 mustEqual playerStats.total
            0 mustEqual playerStats.numRoundsWon
            0 mustEqual playerStats.numGamesWon
        }
    }
}
