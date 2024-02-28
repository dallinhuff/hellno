package com.dallinhuff.hellno
package model

import scala.collection.immutable.Queue

class RiverSpec extends munit.FunSuite:
  test("draw returns a Some when there are cards left in the draw pile"):
    val river = River(Queue(Pride, Wrath), Seq.empty)
    val drawn: Option[(Card, River)] = river.draw

    assert(drawn.isDefined)

  test("Some from successful draw contains correct card and remaining river"):
    val river = River(Queue(Pride, Wrath), Seq.empty)
    val drawn: Option[(Card, River)] = river.draw

    val (card, River(drawPile, discardPile)) = drawn.get
    assertEquals[Card, Card](card, Pride)

    assertEquals(drawPile, Queue(Wrath))
    assertEquals(discardPile, Seq.empty)

  test("draw returns None when there are no cards to draw"):
    val river = River(Queue.empty, Nil)
    val drawn: Option[(Card, River)] = river.draw

    assert(drawn.isEmpty)

  test("discard returns a correctly updated River"):
    val river = River(Queue.empty, Seq(Wrath))
    val River(drawPile, discardPile) = river.discard(Pride)

    assertEquals(drawPile, Queue.empty)
    assertEquals(discardPile, Seq(Pride, Wrath))
    
  test("contains all cards in the deck besides cards in play"):
    val (hands, river) = River.deal(4)
    
    val deckCardCounts = Deck().groupMapReduce(identity)(_ => 1)(_ + _)
    val handCardCounts = hands.flatMap(_.cards).groupMapReduce(identity)(_ => 1)(_ + _)
    val riverCardCounts = river.drawPile.groupMapReduce(identity)(_ => 1)(_ + _)
    
    val handsPlusRiver =
      deckCardCounts.keys
        .map: k =>
          k -> (handCardCounts.getOrElse(k, 0) + riverCardCounts.getOrElse(k, 0))
        .toMap
    
    assertEquals(deckCardCounts, handsPlusRiver)
