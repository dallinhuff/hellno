package com.dallinhuff.hellno
package model

sealed trait DeedInvocation(player: Player, deed: Deed)
case class ImmediateDeedInvocation(player: Player, deed: ImmediateDeed)
  extends DeedInvocation(player, deed)
case class ImmediateAttackInvocation(player: Player, deed: ImmediateAttack, target: Player)
  extends DeedInvocation(player, deed)
case class TurnDeedInvocation(player: Player, deed: TurnDeed)
  extends DeedInvocation(player, deed)
case class TurnAttackInvocation(player: Player, deed: TurnAttack, target: Player)
  extends DeedInvocation(player, deed)
case class ResponseDeedInvocation(player: Player, deed: ResponseDeed, responseTo: DeedInvocation)
  extends DeedInvocation(player, deed)