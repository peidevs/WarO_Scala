package org.peidevs.waro.strategy

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StrategySuite extends AnyFunSuite {
    test("canary test for Strategy") {
        val x = 2 + 2
        assert(x == 4)
    }
    test("pop strategy") {
        val popCard = new PopCard()
        val bid = popCard.selectCard(8, List(4,5,6), 10)
        assert(bid == 4)
    }
    test("max strategy") {
        val maxCard = new MaxCard()
        val bid = maxCard.selectCard(8, List(4,5,6), 10)
        assert(bid == 6)
    }
    test("min strategy") {
        val minCard = new MinCard()
        val bid = minCard.selectCard(11, List(8,2,17), 30)
        assert(bid == 2)
    }
    test("nearest strategy") {
        val nearestCard = new NearestCard()
        val bid = nearestCard.selectCard(11, List(10,4,13,20), 30)
        assert(bid == 10)
    }
}
