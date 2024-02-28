package com.dallinhuff.hellno
package model

/** ADT for all card types */
sealed trait Card

/** ADT for all Sin/non-deed cards */
sealed trait LegionEligible extends Card
extension [T <: LegionEligible : LegionRequirements](l: T)
  def requirements: LegionRequirements[T] = LegionRequirements[T]

/** ADTs for all non-Inquirable Sins */
case object ChaoticEvil extends LegionEligible
sealed trait Curse extends LegionEligible
case object ChastityBelt extends Curse
case object CursedKey extends Curse

/** ADTs for all Inquirable Sins */
sealed trait Demon extends LegionEligible
case object Pride extends Demon
case object Wrath extends Demon
case object Gluttony extends Demon
case object Greed extends Demon
case object Envy extends Demon
case object Lust extends Demon
case object Sloth extends Demon
case object McRib extends Demon

/** ADT for all Deed cards */
sealed trait Deed extends Card

/** ADTs for all ImmediateDeeds (can be played at any time) */
sealed trait ImmediateDeed extends Deed
case object MyTurn extends ImmediateDeed
case object ChangeOfFate extends ImmediateDeed
case object Exorcism extends ImmediateDeed
case object SaltCircle extends ImmediateDeed

/** specialized ImmediateDeeds that attack another player */
sealed trait ImmediateAttack extends ImmediateDeed
case object SkipYou extends ImmediateAttack
case object DemonicRepossession extends ImmediateAttack

/** ADTs for all TurnDeeds (can only be played on your turn) */
sealed trait TurnDeed extends Deed
case object MakeItADouble extends TurnDeed
case object PickYourPoison extends TurnDeed
case object AllAskingMouth extends TurnDeed
case object Repent extends TurnDeed

/** specialized TurnDeeds that attack another player */
sealed trait TurnAttack extends TurnDeed
case object PeepShow extends TurnAttack
case object SwapSpit extends TurnAttack
case object PickPocket extends TurnAttack

/** ADTs for all ResponseDeeds (can only be played in response to another action or deed) */
sealed trait ResponseDeed extends Deed
case object HolyCross extends ResponseDeed
case object Burn extends ResponseDeed
case object DivineIntervention extends ResponseDeed
