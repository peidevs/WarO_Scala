package org.peidevs.waro.domain

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerStatsSuite extends AnyFunSuite {
    test("canary test for PlayerStats") {
        val x = 2 + 2
        assert(x == 4)
    }
    test("reset to zero") {
        var playerStats = new PlayerStats(9,9,9)

        // test
        playerStats.clear()

        assert(0 == playerStats.total)
        assert(0 == playerStats.numRoundsWon)
        assert(9 == playerStats.numGamesWon)
    }
    test("win a prize card") {
        var playerStats = new PlayerStats(1,1,1)

        // test
        playerStats.winsRound(10)

        assert(11 == playerStats.total)
        assert(2 == playerStats.numRoundsWon)
        assert(1 == playerStats.numGamesWon)
    }
    test("win a game") {
        var playerStats = new PlayerStats(1,1,1)

        // test 
        playerStats.winsGame()

        assert(1 == playerStats.total)
        assert(1 == playerStats.numRoundsWon)
        assert(2 == playerStats.numGamesWon)
    }
}
