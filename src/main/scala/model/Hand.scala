package com.dallinhuff.hellno
package model

import scala.annotation.tailrec

/**
 * A representation of a player's hand
 * with lazy getters for finding information about hand
 * @param cards the cards in the hand
 */
case class Hand(cards: Card*):
  // all deed cards in the hand
  lazy val deeds: Seq[Deed] = cards.collect:
    case deed: Deed => deed

  // all immediate deeds in the hand
  lazy val immediateDeeds: Seq[ImmediateDeed] = deeds.collect:
    case deed: ImmediateDeed => deed

  // all turn deeds in the hand
  lazy val turnDeeds: Seq[TurnDeed] = deeds.collect:
    case deed: TurnDeed => deed

  // all sin cards in the hand
  lazy val sins: Seq[LegionEligible] = cards.collect:
    case sin: LegionEligible => sin

  // all inquirable cards in the hand
  lazy val demons: Seq[Demon] = sins.collect:
    case demon: Demon => demon

  // all curse cards in the hand (chastity belt, cursed key)
  lazy val curses: Seq[Curse] = sins.collect:
    case curse: Curse => curse

  // if the hand has a curse preventing them from playing legions
  lazy val cursed: Boolean = curses.length == 1

  private lazy val groupedByLegion =
    sins.groupMapReduce(identity)(_ => 1)(_ + _)

  lazy val fullLegions: Seq[Legion] =
    groupedByLegion
      .collect:
        case (curse: Curse, count) if count >= curse.requirements.size =>
          Legion(curse)
        case (McRib, count) if count >= McRib.requirements.size =>
          Legion(McRib)
        case (demon: Demon, count) if count >= demon.requirements.size =>
          Legion(demon)
      .toSeq

  // all full (or potentially full via wild cards) legions in the hand
  lazy val legions: Seq[Legion] =
    groupedByLegion
      .foldLeft((Seq.empty[Legion], groupedByLegion.getOrElse(ChaoticEvil, 0))):
        case ((legions, numChaoticEvils), (card, count)) =>
          card match
            case curse: Curse =>
              makeLegions(curse, count, legions, numChaoticEvils)
            case mcRib: McRib.type =>
              makeLegions(mcRib, count, legions, numChaoticEvils)
            case demon: Demon =>
              makeLegions(demon, count, legions, numChaoticEvils)
            case _ =>
              (legions, numChaoticEvils)
      ._1

  @tailrec
  private def makeLegions[T <: LegionEligible](
      card: T,
      numInHand: Int,
      legions: Seq[Legion],
      numChaoticEvils: Int
  )(
      using requirements: LegionRequirements[T]
  ): (Seq[Legion], Int) =
    if numInHand >= requirements.size then
      val newLegions = Legion(card) +: legions
      val leftOver = numInHand - requirements.size
      makeLegions(card, leftOver, newLegions, numChaoticEvils)(using requirements)
    else if requirements.canUseChaoticEvil && numInHand + numChaoticEvils >= requirements.size then
      val needed = requirements.size - numInHand
      (Legion(card, needed) +: legions, numChaoticEvils - needed)
    else (legions, numChaoticEvils)
