package com.dallinhuff.hellno
package model

class GameSpec extends munit.FunSuite:
  test("object apply method correctly initializes players, river, and turnIndex"):
    val game = Game(4)

    val players = game.players
    assertEquals(players.length, 4)
    assert(players.forall(_.hand.cards.length == 7))

    val river = game.river
    assertEquals(river.drawPile.length, Deck.DECK_SIZE - 28)
