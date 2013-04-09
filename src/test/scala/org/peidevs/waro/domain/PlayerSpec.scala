
package org.peidevs.waro.domain

import org.specs2.mutable._

class PlayerSpec extends Specification {
    var player = new Player("Chopin", List(2,3,4))
    
    "player" can {
                
        "make a bid" in {
            val bid = player.getBid(10)
            2 mustEqual bid.offer
            player mustEqual bid.player
        }
    }
}
