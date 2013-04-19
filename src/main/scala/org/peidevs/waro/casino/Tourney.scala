
package org.peidevs.waro.casino

import org.peidevs.waro.Config
import org.peidevs.waro.domain._

class Tourney(val config:Config) {
    val numGames:Int = config.numGames
    val numCards:Int = config.numCards
    val players:List[Player] = config.players

    def playGames() = {
        for (i <- 1 to numGames) {
            players.foreach { _.clear() }
            def game = new Game(config.deckFactory)
            game.playGame(numCards, players)
        }
        
        println("\nTourney summary:  ")

        players.foreach { p =>
            println(p.name + " won " + p.playerStats.numGamesWon + " times in " + numGames + " games")
        }        
        
    }
}