package com.dallinhuff.hellno
package model

case class Legion(sin: LegionEligible, numChaoticEvil: Int = 0)

trait LegionRequirements[A <: LegionEligible]:
  def size: Int
  def canUseChaoticEvil: Boolean
  
object LegionRequirements:
  given LegionRequirements[Curse] with
    val size = 2
    val canUseChaoticEvil = false
  given LegionRequirements[McRib.type] with
    val size = 2
    val canUseChaoticEvil = true
  given LegionRequirements[Demon] with
    val size = 4
    val canUseChaoticEvil = true
    
  def apply[A <: LegionEligible : LegionRequirements]: LegionRequirements[A]
    = summon[LegionRequirements[A]]
    