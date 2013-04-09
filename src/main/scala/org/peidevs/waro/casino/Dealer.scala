
package org.peidevs.waro.casino

import org.peidevs.waro.domain._

import scala.util.Random

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConverters._

import com.google.common.collect.Lists

class Dealer {
    def deal(numCards:Int, players:List[Player]):Table = {
        val numPlayers = players.size

        val hands = dealHands(numCards, numPlayers)

        val kitty = hands(0)

        for (index <- 1 to numPlayers) {
            players(index - 1).hand = hands(index)
        }
        
        val table = new Table(players, kitty)
        
        return table        
    }
    
    def play(table:Table) = {
        table.kitty.foreach { prizeCard =>    
            playRound(prizeCard, table.players)
        }        
    }
    
    
    // ------  internal
    
    def playRound(prizeCard:Int, players:List[Player]):Player = {
        val bid = findRoundWinner(prizeCard, players)
        val winningBid = bid.offer
        val winner = bid.player

        // if (verbose) { println "\nthis round: ${winner.name} WINS $prizeCard with ${winningBid}" }

        winner.playerStats.numRoundsWon += 1
        winner.playerStats.total += prizeCard
        
        return winner        
    }
    
    def findRoundWinner(prizeCard:Int, players:List[Player]):Bid = {
        var max:Int = 0
        var winningBid:Bid = null
        
        players.map ( p => {
                val bid = p.getBid(prizeCard)
            
                if (bid.offer > max) {
                    winningBid = bid
                    max = bid.offer
                } 
            }
        )
        
        return winningBid
    }
    
    def dealHands(numCards:Int, numPlayers:Int):List[List[Int]] = {
        var hands:List[List[Int]] = List()

        val deck:List[Int] = newDeck(numCards)        
        val numCardsInHand:Int = getNumCardsInHand(numCards, numPlayers)

        val deckJavaList:java.util.List[Int] = ListBuffer(deck: _*)

        val javaHands:java.util.List[java.util.List[Int]] = 
                Lists.partition(deckJavaList, numCardsInHand)
        
        val iter = javaHands.iterator
        
        while (iter.hasNext()) {
            val javaHand:java.util.List[Int] = iter.next()
            val hand = javaHand.asScala.toList
            hands ::= hand
        }
        
        return hands
    }
    
    def newDeck(numCards:Int):List[Int] = {
        var deck:List[Int] = List()
        
        for (i <- 1 to numCards) {
            deck ::= i
        }

        return Random.shuffle(deck)
    }
    
    def getNumCardsInHand(numCards:Int, numPlayers:Int):Int = {
        return (numCards / (numPlayers + 1))
    }    
    
}