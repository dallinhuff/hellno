package com.dallinhuff.hellno
package model

import scala.collection.immutable.Queue
import scala.util.Random

case class River(drawPile: Queue[Card], discardPile: Seq[Card] = Seq.empty):
  def draw: Option[(Card, River)] =
    drawPile.dequeueOption.map: (card, rest) =>
      (card, copy(drawPile = rest))

  def discard(card: Card): River =
    copy(discardPile = card +: discardPile)

object River:
  def apply(): River =
    River(Queue.from(Deck()))

  def deal(numPlayers: 4 | 5): (Seq[Hand], River) =
    val (hands, rest) = Deck().splitAt(numPlayers * 7)
    (hands.grouped(7).map(Hand.apply).toSeq, River(Queue.from(rest)))