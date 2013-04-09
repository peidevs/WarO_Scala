
package org.peidevs.waro.casino

import org.peidevs.waro.domain._
import org.specs2.mutable._

class DealerSpec extends Specification {
    var dealer = new Dealer()
    
    "a dealer 1" can {
        val player1 = new Player("Brahms")
        val player2 = new Player("Mozart")
        val players = List(player1, player2)
        var table = dealer.deal(33, players)
        
        "deal cards" in {
            2 mustEqual table.players.size
            11 mustEqual table.players(0).hand.size
            11 mustEqual table.players(1).hand.size
            11 mustEqual table.kitty.size
        }
    }
    
    "a dealer 1b" can {
        val hands = dealer.dealHands(21,2)
        
        "deal hands" in {
            3 mustEqual hands.size
            7 mustEqual hands(0).size
            7 mustEqual hands(1).size
            7 mustEqual hands(2).size
        }
    }
    
    "a dealer 1c" can {
        val player1 = new Player("Brahms", List(9))
        val player2 = new Player("Mozart", List(10))
        val players = List(player1, player2)
        val prizeCard:Int = 33
        val bid:Bid = dealer.findRoundWinner(prizeCard, players)
        
        "find round winner for 1 round" in {
            10 mustEqual bid.offer
            "Mozart" mustEqual bid.player.name
        }
    }

    "a dealer 1d" can {
        val player1 = new Player("Brahms", List(9,15))
        val player2 = new Player("Mozart", List(10,2))
        val players = List(player1, player2)
        val prizeCard:Int = 33
        dealer.findRoundWinner(prizeCard, players)
        val bid:Bid = dealer.findRoundWinner(prizeCard, players)
        
        "find round winner for 2 rounds" in {
            15 mustEqual bid.offer
            "Brahms" mustEqual bid.player.name
        }
    }

    "a dealer 1e" can {
        val player1 = new Player("Brahms", List(9,15))
        val player2 = new Player("Mozart", List(10,2))
        val players = List(player1, player2)
        val prizeCard:Int = 33
        val winner = dealer.playRound(prizeCard, players)
        
        "play one round" in {
            "Mozart" mustEqual winner.name
        }
    }
    
    "a dealer 2" can {        
        "calc # hands" in {
            12 mustEqual dealer.getNumCardsInHand(60, 4)
        }
        "calc # hands v2" in {
            10 mustEqual dealer.getNumCardsInHand(32, 2)
        }
    }
    "a dealer 3" can {
        var deck = dealer.newDeck(20)
        println("deck is : " + deck)
        "make a deck" in {
            20 mustEqual deck.size
        }
    }
}
