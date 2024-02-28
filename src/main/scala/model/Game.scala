package com.dallinhuff.hellno
package model

enum PlayDirection:
  case Clockwise
  case CounterClockwise

case class Game(
    players: Seq[Player],
    river: River,
    turnIndex: Int = 0,
    playDirection: PlayDirection = PlayDirection.Clockwise,
):
  def makeMove(move: DeedInvocation): Game = ???

object Game:
  def apply(numPlayers: 4 | 5): Game =
    River.deal(numPlayers) match
      case (hands, river) =>
        this(hands.map(Player(_)), river)