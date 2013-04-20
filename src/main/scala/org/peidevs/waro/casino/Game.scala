
package org.peidevs.waro.casino

import org.peidevs.waro.domain._

class Game(val deckFactory:DeckFactory) {
    val verbose = true
    
    def playGame(numCards:Int, players:List[Player]):Player = {
        val dealer = new Dealer(deckFactory)
        val table = dealer.deal(numCards, players)
        dealer.play(table)
        determineWinner(table)
    }
    
    // -- internal
    
    def determineWinner(table:Table):Player = {
        val kitty = table.kitty
        val players = table.players

        val winner = players.maxBy { _.playerStats.total }
        val max = winner.playerStats.total

        if (verbose) {
            players.foreach { p =>
                val numRounds = p.playerStats.numRoundsWon
                val total = p.playerStats.total
                println(p.name + " won " + numRounds + " rounds, with total : " + total)
            }                            
        }

        winner.winsGame()
        println("\nGame summary:")
        println("overall WINNER is: " + winner.name)
        
        return winner   
    }
    
}