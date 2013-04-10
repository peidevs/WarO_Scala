
package org.peidevs.waro.casino

import org.peidevs.waro.Config
import org.peidevs.waro.domain._

class Tourney(val config:Config) {
    val numGames:Int = config.numGames
    val numCards:Int = config.numCards
    val player1 = new Player("Brahms")
    val player2 = new Player("Beethoven")
    val player3 = new Player("Bach")
    val players:List[Player] = List(player1, player2, player3)

    def playGames() = {
        for (i <- 1 to numGames) {
            def game = new Game()
            game.playGame(numCards, players)
            players.foreach { p => p.clear() }
        }
        
        println("\nTourney summary:  ")

        players.foreach { p =>
            println(p.name + " won " + p.playerStats.numGamesWon + " times in " + numGames + " games")
        }        
        
    }
}