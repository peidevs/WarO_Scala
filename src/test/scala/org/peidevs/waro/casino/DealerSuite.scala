package org.peidevs.waro.casino

import org.peidevs.waro.domain._
import org.peidevs.waro.strategy._

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DealerSuite extends AnyFunSuite {
    val deckFactory = new DeckFactory()
    var dealer = new Dealer(deckFactory)
    val popCard = new PopCard()
    val maxCard = 60

    class MockDeckFactory(val mockList:List[Int]) extends DeckFactory {
        override def newDeck(numCards:Int):List[Int] = {
            mockList
        }
    }

    test("canary test for Dealer") {
        val x = 2 + 2
        assert(x == 4)
    }
    test("deal cards") {
        val mockList = (1 to maxCard).toList
        val dealer = new Dealer(new MockDeckFactory(mockList))
        val player1 = new Player("Brahms", maxCard, popCard)
        val player2 = new Player("Mozart", maxCard, popCard)
        val players = List(player1, player2)

        // test
        var table = dealer.deal(maxCard, players)

        assert(2 == table.players.size)
        assert(20 == table.kitty.size)            
        assert((1 to 20).toList == table.kitty)
        assert(20 == table.players(0).hand.size)
        assert((21 to 40).toList == table.players(0).hand)
        assert(20 == table.players(1).hand.size)
        assert((41 to 60).toList == table.players(1).hand)
    }
    test("deal cards case 1") {
        val player1 = new Player("Brahms", maxCard, popCard)
        val player2 = new Player("Mozart", maxCard, popCard)
        val players = List(player1, player2)

        // test
        var table = dealer.deal(33, players)
        
        assert(2 == table.players.size)
        assert(11 == table.players(0).hand.size)
        assert(11 == table.players(1).hand.size)
        assert(11 == table.kitty.size)
    }
    test("deal cards case 1b") {
        // test
        val hands = dealer.dealHands(21,2)
        
        assert(3 == hands.size)
        assert(7 == hands(0).size)
        assert(7 == hands(1).size)
        assert(7 == hands(2).size)
    }
    test("deal cards case 1c") {
        val player1 = new Player("Brahms", maxCard, popCard, List(9))
        val player2 = new Player("Mozart", maxCard, popCard, List(10))
        val players = List(player1, player2)
        val prizeCard:Int = 33

        // test
        val bid:Bid = dealer.findRoundWinner(prizeCard, players)
        
        assert(10 == bid.offer)
        assert("Mozart" == bid.player.name)
    }
    test("deal cards case 1d") {
        val player1 = new Player("Brahms", maxCard, popCard, List(9,15))
        val player2 = new Player("Mozart", maxCard, popCard, List(10,2))
        val players = List(player1, player2)
        val prizeCard:Int = 33
        dealer.findRoundWinner(prizeCard, players)

        // test
        val bid:Bid = dealer.findRoundWinner(prizeCard, players)
        
        assert(15 == bid.offer)
        assert("Brahms" == bid.player.name)
    }
    test("deal cards case 1e") {
        val player1 = new Player("Brahms", maxCard, popCard, List(9,15))
        val player2 = new Player("Mozart", maxCard, popCard, List(10,2))
        val players = List(player1, player2)
        val prizeCard:Int = 33

        // test
        val winner = dealer.playRound(prizeCard, players)
        
        assert("Mozart" == winner.name)
    }
    test("deal cards case 2") {
        assert(12 == dealer.getNumCardsInHand(60, 4))
        assert(10 == dealer.getNumCardsInHand(32, 2))
    }
}
