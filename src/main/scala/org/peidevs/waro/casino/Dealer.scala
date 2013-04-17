
package org.peidevs.waro.casino

import org.peidevs.waro.domain._

class Dealer(val deckFactory:DeckFactory) {
    def deal(numCards:Int, players:List[Player]):Table = {
        val numPlayers = players.size

        val hands = dealHands(numCards, numPlayers)

        val kitty = hands(0)

        for (index <- 1 to numPlayers) {
            players(index - 1).hand = hands(index)
        }
        
        new Table(players, kitty)
    }
    
    def play(table:Table) = {
        table.kitty.foreach { prizeCard =>    
            val winner = playRound(prizeCard, table.players)
            println(winner.emitLog())
        }        
    }
    
    
    // ------  internal
    
    def playRound(prizeCard:Int, players:List[Player]):Player = {
        val bid = findRoundWinner(prizeCard, players)
        val winningBid = bid.offer
        val winner = bid.player

        val verbose = true
        if (verbose) {
            println("\nthis round: " + winner.name + " WINS " + prizeCard + " with " + winningBid)
        }

        winner.winsRound(prizeCard)
        
        winner        
    }
    
    def findRoundWinner(prizeCard:Int, players:List[Player]):Bid = {
        var max:Int = 0
        var winningBid:Bid = null
        
        players.map ( p => {
                val bid = p.getBid(prizeCard)

                val verbose = true
                if (verbose) {
                    println(p.name + " bids '" + bid.offer + "' against " + max)
                }
            
                if (bid.offer > max) {
                    winningBid = bid
                    max = bid.offer
                } 
            }
        )
        
        return winningBid
    }

    def partition(list:List[Int], size:Int):List[List[Int]] = {
        if (list.size > 0) {
            val sliceList:List[Int] = list.slice(0,size)
            val rest = list diff sliceList
            List(sliceList) ++ partition(rest, size)
        } else {
            Nil
        }
    }
    
    def dealHands(numCards:Int, numPlayers:Int):List[List[Int]] = {
        var hands:List[List[Int]] = List()

        val deck:List[Int] = deckFactory.newDeck(numCards)        
        val numCardsInHand:Int = getNumCardsInHand(numCards, numPlayers)
        partition(deck, numCardsInHand)
    }
        
    def getNumCardsInHand(numCards:Int, numPlayers:Int):Int = {
        (numCards / (numPlayers + 1))
    }    
    
}