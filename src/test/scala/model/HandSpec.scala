package com.dallinhuff.hellno
package model

class HandSpec extends munit.FunSuite:
  private val sampleHand = Hand(MyTurn, Wrath, ChastityBelt, AllAskingMouth, Greed, SaltCircle, ChaoticEvil)

  test("deeds computed correctly"):
    assertEquals(sampleHand.deeds, List(MyTurn, AllAskingMouth, SaltCircle))

  test("immediate deeds computed correctly"):
    assertEquals(sampleHand.immediateDeeds, List(MyTurn, SaltCircle))

  test("turn deeds computed correctly"):
    assertEquals(sampleHand.turnDeeds, List(AllAskingMouth))

  test("sins computed correctly"):
    assertEquals(sampleHand.sins, List(Wrath, ChastityBelt, Greed, ChaoticEvil))

  test("inquirables computed correctly"):
    assertEquals(sampleHand.demons, List(Wrath, Greed))

  test("curses computed correctly"):
    assertEquals(sampleHand.curses, List(ChastityBelt))

  test("legions computed correctly"):
    val hand = Hand(
      McRib, McRib,
      Wrath, Wrath, Wrath, Wrath,
      Greed, Greed, Greed,
      ChaoticEvil, ChaoticEvil,
      ChastityBelt
    )
    val legions = hand.legions

    assertEquals(legions.length, 3)
    assert(legions.contains(Legion(McRib)))
    assert(legions.contains(Legion(Wrath)))
    assert(legions.contains(Legion(Greed, 1)))
