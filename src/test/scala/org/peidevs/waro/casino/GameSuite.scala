package org.peidevs.waro.casino

import org.peidevs.waro.domain._
import org.peidevs.waro.strategy._

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameSuite extends AnyFunSuite {
    val deckFactory = new DeckFactory()
    val game = new Game(deckFactory)
    val popCard = new PopCard()
    val maxCard = 60

    test("canary test for Game") {
        val x = 2 + 2
        assert(x == 4)
    }
    test("determine winner") {
        val playerStats1 = new PlayerStats(9,0,0)
        val player1 = new Player("Brahms", maxCard, popCard, List(), playerStats1)

        val playerStats2 = new PlayerStats(2,0,0)
        val player2 = new Player("Mozart", maxCard, popCard, List(), playerStats2)

        val players = List(player1, player2)        
        val kitty = List(3,4,5)
        
        val table = new Table(players, kitty)

        // test
        val winner = game.determineWinner(table)
        
        assert(9 == winner.playerStats.total)
        assert(1 == winner.playerStats.numGamesWon)
        assert("Brahms" == winner.name)
    }
    test("play a game") {
        val player1 = new Player("Brahms", maxCard, popCard)
        val player2 = new Player("Mozart", maxCard, popCard)

        val players = List(player1, player2)        
        
        // test
        val winner = game.playGame(6, players)
        
        assert(winner != null)
        assert(2 == (1+1))
    }
}
