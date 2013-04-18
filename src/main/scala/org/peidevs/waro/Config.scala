
package org.peidevs.waro;

import org.peidevs.waro.casino._
import org.peidevs.waro.domain._
import org.peidevs.waro.strategy._

class Config {
    var numGames = 200
    var numCards = 16
    var deckFactory = new DeckFactory()
    val player1 = new Player("Mozart", numCards, new PopCard())
    val player2 = new Player("Saint-Saens", numCards,  new PopCard())
    var players:List[Player] = List(player1, player2)    
}