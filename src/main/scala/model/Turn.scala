package com.dallinhuff.hellno
package model

case class Turn(player: Player, inquiries: Seq[Inquiry], deeds: Seq[TurnDeed])

case class Inquiry(player: Player, target: Player, demon: Demon, status: InquiryStatus)

enum InquiryStatus:
  case Pending
  case Success
  case Reject
  case BlockedByDeed(deed: ResponseDeed)
