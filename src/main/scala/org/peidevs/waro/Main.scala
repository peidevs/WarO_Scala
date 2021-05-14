
package org.peidevs.waro

import org.peidevs.waro.casino._

object Main {
    def main(args: Array[String]): Unit = {
        var config = new Config()
        var tourney = new Tourney(config)
        tourney.playGames()
        
        println("done.")
    }
}
