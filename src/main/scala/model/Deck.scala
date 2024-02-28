package com.dallinhuff.hellno
package model

import scala.util.Random

type Deck = Seq[Card]

extension (d: Deck)
  def shuffle: Deck =
    Random.shuffle(d)

object Deck:
  def apply(): Deck =
    cardCounts
      .view
      .flatMap((card, count) => Seq.fill(count)(card))
      .toSeq
      .shuffle
    
  private val cardCounts: Map[Card, Int] = Map(
    Pride -> 8,
    Wrath -> 8,
    Gluttony -> 8,
    Greed -> 8,
    Envy -> 8,
    Lust -> 8,
    Sloth -> 8,
    McRib -> 4,
    ChastityBelt -> 2,
    ChaoticEvil -> 4,
    MyTurn -> 4,
    SkipYou -> 4,
    ChangeOfFate -> 4,
    DemonicRepossession -> 4,
    Exorcism -> 4,
    SaltCircle -> 4,
    MakeItADouble -> 4,
    PeepShow -> 4,
    PickYourPoison -> 4,
    AllAskingMouth -> 4,
    Repent -> 4,
    SwapSpit -> 4,
    PickPocket -> 4,
    HolyCross -> 4,
    Burn -> 4,
    DivineIntervention -> 4
  )

  final val DECK_SIZE = cardCounts.values.sum


