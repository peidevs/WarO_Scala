
package org.peidevs.waro

import org.peidevs.waro.casino._
import org.peidevs.waro.domain._
import org.peidevs.waro.strategy._

import org.specs2.mutable._

class MainSpec extends Specification {    
    class MockDeckFactory(val mockList:List[Int]) extends DeckFactory {
        override def newDeck(numCards:Int):List[Int] = {
            mockList
        }
    }
    
    val maxCard = 9
    var config = new Config()

    val popCardStrategy = new PopCard()
    val maxCardStrategy = new MaxCard()
    
    "integration test" can {
        val mockList = (1 to maxCard).toList
        val mockDeckFactory = new MockDeckFactory(mockList)
        val dealer = new Dealer(mockDeckFactory)
        
        val player1 = new Player("Brahms", maxCard, popCardStrategy)
        val player2 = new Player("Mozart", maxCard, popCardStrategy)
        val players = List(player1, player2)
        var table = dealer.deal(maxCard, players)
        
        "test simple deal" in {
            2 mustEqual table.players.size
            3 mustEqual table.kitty.size            
            3 mustEqual table.players(0).hand.size
            3 mustEqual table.players(1).hand.size
            
            (1 to 3).toList mustEqual table.kitty
            (4 to 6).toList mustEqual table.players(0).hand
            (7 to 9).toList mustEqual table.players(1).hand
        }
    }
        
    "integration test for a game with PopCard strategies" can {
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
        tourney.playGames()
        
        "test the results" in {
            0 mustEqual player1.playerStats.numGamesWon
            0 mustEqual player1.playerStats.numRoundsWon
            0 mustEqual player1.playerStats.total
            1 mustEqual player2.playerStats.numGamesWon
            3 mustEqual player2.playerStats.numRoundsWon
            6 mustEqual player2.playerStats.total
        }
    }
    
    "integration test for a game with MaxCard strategies" can {
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
        tourney.playGames()
        
        "test the results" in {
            1 mustEqual player1.playerStats.numGamesWon
            2 mustEqual player1.playerStats.numRoundsWon
            16 mustEqual player1.playerStats.total
            0 mustEqual player2.playerStats.numGamesWon
            1 mustEqual player2.playerStats.numRoundsWon
            8 mustEqual player2.playerStats.total
        }
    }    
}