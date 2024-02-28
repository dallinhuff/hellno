package com.dallinhuff.hellno
package model

case class Player(hand: Hand, legions: List[Legion] = Nil, buffs: List[Deed] = Nil)