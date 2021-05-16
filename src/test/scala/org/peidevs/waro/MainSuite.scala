package org.peidevs.waro

import org.peidevs.waro.casino._
import org.peidevs.waro.domain._
import org.peidevs.waro.strategy._

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MainSuite extends AnyFunSuite {
    class MockDeckFactory(val mockList:List[Int]) extends DeckFactory {
        override def newDeck(numCards:Int):List[Int] = {
            mockList
        }
    }
    
    val maxCard = 9
    var config = new Config()

    val popCardStrategy = new PopCard()
    val maxCardStrategy = new MaxCard()
    val minCardStrategy = new MinCard() 
    val nearestCardStrategy = new NearestCard() 

    test("canary test for Main") {
        val x = 2 + 2
        assert(x == 4)
    }
    test("integration test") {
        val mockList = (1 to maxCard).toList
        val mockDeckFactory = new MockDeckFactory(mockList)
        val dealer = new Dealer(mockDeckFactory)
        
        val player1 = new Player("Brahms", maxCard, popCardStrategy)
        val player2 = new Player("Mozart", maxCard, popCardStrategy)
        val players = List(player1, player2)

        // test
        var table = dealer.deal(maxCard, players)
        
        assert(2 == table.players.size)
        assert(3 == table.kitty.size)
        assert(3 == table.players(0).hand.size)
        assert(3 == table.players(1).hand.size)
            
        assert((1 to 3).toList == table.kitty)
        assert((4 to 6).toList == table.players(0).hand)
        assert((7 to 9).toList == table.players(1).hand)
    }
    test("integration test for a game with PopCard strategies") {
        val mockList = (1 to maxCard).toList
        val mockDeckFactory = new MockDeckFactory(mockList)
        val dealer = new Dealer(mockDeckFactory)

        val player1 = new Player("Brahms", maxCard, popCardStrategy)
        val player2 = new Player("Mozart", maxCard, popCardStrategy)
        val players = List(player1, player2)
        config.players = players
        config.numCards = maxCard
        config.numGames = 1
        config.deckFactory = mockDeckFactory
        var tourney = new Tourney(config)
    
        // test
        tourney.playGames()
        
        assert(0 == player1.playerStats.numGamesWon)
        assert(0 == player1.playerStats.numRoundsWon)
        assert(0 == player1.playerStats.total)
        assert(1 == player2.playerStats.numGamesWon)
        assert(3 == player2.playerStats.numRoundsWon)
        assert(6 == player2.playerStats.total)
    }
    test("integration test for a game with MaxCard strategies") {
        val mockList = List(7,8,9,6,3,2,5,4,1)
        val mockDeckFactory = new MockDeckFactory(mockList)
        val dealer = new Dealer(mockDeckFactory)

        val player1 = new Player("Brahms", maxCard, maxCardStrategy)
        val player2 = new Player("Mozart", maxCard, maxCardStrategy)
        val players = List(player1, player2)
        config.players = players
        config.numCards = maxCard
        config.numGames = 1
        config.deckFactory = mockDeckFactory
        var tourney = new Tourney(config)

        // test
        tourney.playGames()

        assert(1 == player1.playerStats.numGamesWon)
        assert(2 == player1.playerStats.numRoundsWon)
        assert(16 == player1.playerStats.total)
        assert(0 == player2.playerStats.numGamesWon)
        assert(1 == player2.playerStats.numRoundsWon)
        assert(8 == player2.playerStats.total)
    }
    test("integration test for a game with MinCard strategies") {
        val mockList = List(7,8,9,6,3,2,5,4,1)
        val mockDeckFactory = new MockDeckFactory(mockList)
        val dealer = new Dealer(mockDeckFactory)

        val player1 = new Player("Brahms", maxCard, minCardStrategy)
        val player2 = new Player("Mozart", maxCard, minCardStrategy)
        val players = List(player1, player2)
        config.players = players
        config.numCards = maxCard
        config.numGames = 1
        config.deckFactory = mockDeckFactory
        var tourney = new Tourney(config)

        // test
        tourney.playGames()

        assert(1 == player1.playerStats.numGamesWon)
        assert(2 == player1.playerStats.numRoundsWon)
        assert(16 == player1.playerStats.total)
        assert(0 == player2.playerStats.numGamesWon)
        assert(1 == player2.playerStats.numRoundsWon)
        assert(8 == player2.playerStats.total)
    }
    test("integration test for a game with NearestCard strategies") {
        val mockList = List(2,6,8,1,3,7,4,5,9)
        val mockDeckFactory = new MockDeckFactory(mockList)
        val dealer = new Dealer(mockDeckFactory)

        val player1 = new Player("Brahms", maxCard, nearestCardStrategy)
        val player2 = new Player("Mozart", maxCard, nearestCardStrategy)
        val players = List(player1, player2)
        config.players = players
        config.numCards = maxCard
        config.numGames = 1
        config.deckFactory = mockDeckFactory
        var tourney = new Tourney(config)

        // test
        tourney.playGames()

        assert(0 == player1.playerStats.numGamesWon)
        assert(1 == player1.playerStats.numRoundsWon)
        assert(6 == player1.playerStats.total)
        assert(1 == player2.playerStats.numGamesWon)
        assert(2 == player2.playerStats.numRoundsWon)
        assert(10 == player2.playerStats.total)
    }
}
