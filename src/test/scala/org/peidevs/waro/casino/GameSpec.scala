
package org.peidevs.waro.casino

import org.peidevs.waro.domain._
import org.peidevs.waro.strategy._

import org.specs2.mutable._

class GameSpec extends Specification {
    val deckFactory = new DeckFactory()
    val game = new Game(deckFactory)
    val popCard = new PopCard()
    val maxCard = 60
    
    "a game" can {
        val playerStats1 = new PlayerStats(9,0,0)
        val player1 = new Player("Brahms", maxCard, popCard, List(), playerStats1)

        val playerStats2 = new PlayerStats(2,0,0)
        val player2 = new Player("Mozart", maxCard, popCard, List(), playerStats2)

        val players = List(player1, player2)        
        val kitty = List(3,4,5)
        
        val table = new Table(players, kitty)
        val winner = game.determineWinner(table)
        
        "determine a winner" in {
            9 mustEqual winner.playerStats.total
            1 mustEqual winner.playerStats.numGamesWon
            "Brahms" mustEqual winner.name
        }
    }
    
    "a game" can {
        val player1 = new Player("Brahms", maxCard, popCard)
        val player2 = new Player("Mozart", maxCard, popCard)

        val players = List(player1, player2)        
        val winner = game.playGame(6, players)
        
        "play a game" in {
            println("winner is " + winner.name)
        }
    }
    
}
