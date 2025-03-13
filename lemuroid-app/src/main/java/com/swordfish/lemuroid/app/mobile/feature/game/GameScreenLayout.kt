package com.swordfish.lemuroid.app.mobile.feature.game

import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

object GameScreenLayout {
    const val CONSTRAINTS_TOP_DIVIDER = "topDivider"
    const val CONSTRAINTS_LEFT_DIVIDER = "leftDivider"
    const val CONSTRAINTS_RIGHT_DIVIDER = "rightDivider"
    const val CONSTRAINTS_LEFT_PAD = "leftPad"
    const val CONSTRAINTS_RIGHT_PAD = "rightPad"
    const val CONSTRAINTS_GAME_VIEW = "gameView"
    const val CONSTRAINTS_GAME_CONTAINER = "gameContainer"

    fun buildConstraintSet(isLandscape: Boolean, allowTouchOverlay: Boolean): ConstraintSet {
        return when {
            !isLandscape -> buildConstraintSetPortrait()
            allowTouchOverlay -> buildConstraintSetLandscape()
            else -> buildConstraintSetLandscapeNoOverlay()
        }
    }

    private fun buildConstraintSetPortrait(): ConstraintSet {
        return ConstraintSet {
            val gameView = createRefFor(CONSTRAINTS_GAME_VIEW)
            val leftPad = createRefFor(CONSTRAINTS_LEFT_PAD)
            val rightPad = createRefFor(CONSTRAINTS_RIGHT_PAD)
            val gameContainer = createRefFor(CONSTRAINTS_GAME_CONTAINER)
            val topDivider = createRefFor(CONSTRAINTS_TOP_DIVIDER)

            val gamePadChain = createHorizontalChain(leftPad, rightPad, chainStyle = ChainStyle.SpreadInside)

            constrain(gameView) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                absoluteLeft.linkTo(parent.absoluteLeft)
                absoluteRight.linkTo(parent.absoluteRight)
                bottom.linkTo(leftPad.top)
            }

            constrain(topDivider) {
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
                absoluteLeft.linkTo(parent.absoluteLeft)
                absoluteRight.linkTo(parent.absoluteRight)
                bottom.linkTo(leftPad.top)
            }

            constrain(gamePadChain) {
                absoluteLeft.linkTo(parent.absoluteLeft)
                absoluteRight.linkTo(parent.absoluteRight)
            }

            constrain(rightPad) {
                width = Dimension.fillToConstraints
                bottom.linkTo(parent.bottom)
            }

            constrain(leftPad) {
                width = Dimension.fillToConstraints
                bottom.linkTo(parent.bottom)
            }

            constrain(gameContainer) {
                absoluteLeft.linkTo(gameView.absoluteLeft)
                absoluteRight.linkTo(gameView.absoluteRight)
                top.linkTo(gameView.top)
                bottom.linkTo(gameView.bottom)
            }
        }
    }

    private fun buildConstraintSetLandscape(): ConstraintSet {
        return ConstraintSet {
            val gameView = createRefFor(CONSTRAINTS_GAME_VIEW)
            val leftPad = createRefFor(CONSTRAINTS_LEFT_PAD)
            val rightPad = createRefFor(CONSTRAINTS_RIGHT_PAD)
            val gameContainer = createRefFor(CONSTRAINTS_GAME_CONTAINER)

            val gamePadChain = createHorizontalChain(leftPad, rightPad, chainStyle = ChainStyle.SpreadInside)

            constrain(gameView) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                top.linkTo(parent.top)
                absoluteLeft.linkTo(parent.absoluteLeft)
                absoluteRight.linkTo(parent.absoluteRight)
                bottom.linkTo(parent.bottom)
            }

            constrain(gamePadChain) {
                absoluteLeft.linkTo(parent.absoluteLeft)
                absoluteRight.linkTo(parent.absoluteRight)
            }

            constrain(rightPad) {
                verticalBias = 1.0f
                bottom.linkTo(parent.bottom)
                top.linkTo(parent.top)
            }

            constrain(leftPad) {
                verticalBias = 1.0f
                bottom.linkTo(parent.bottom)
                top.linkTo(parent.top)
            }

            constrain(gameContainer) {
                absoluteLeft.linkTo(gameView.absoluteLeft)
                absoluteRight.linkTo(gameView.absoluteRight)
                top.linkTo(gameView.top)
                bottom.linkTo(gameView.bottom)
            }
        }
    }

    private fun buildConstraintSetLandscapeNoOverlay(): ConstraintSet {
        return ConstraintSet {
            val gameView = createRefFor(CONSTRAINTS_GAME_VIEW)
            val leftPad = createRefFor(CONSTRAINTS_LEFT_PAD)
            val rightPad = createRefFor(CONSTRAINTS_RIGHT_PAD)
            val gameContainer = createRefFor(CONSTRAINTS_GAME_CONTAINER)
            val leftDivider = createRefFor(CONSTRAINTS_LEFT_DIVIDER)
            val rightDivider = createRefFor(CONSTRAINTS_RIGHT_DIVIDER)

            constrain(leftPad) {
                absoluteLeft.linkTo(parent.absoluteLeft)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent // Takes as much space as needed
            }

            constrain(gameView) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                absoluteLeft.linkTo(leftPad.absoluteRight)
                absoluteRight.linkTo(rightPad.absoluteLeft)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints // Expands between leftPad and rightPad
            }

            constrain(rightPad) {
                absoluteRight.linkTo(parent.absoluteRight)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent // Takes as much space as needed
            }

            constrain(gameContainer) {
                absoluteLeft.linkTo(gameView.absoluteLeft)
                absoluteRight.linkTo(gameView.absoluteRight)
                top.linkTo(gameView.top)
                bottom.linkTo(gameView.bottom)
            }

            constrain(leftDivider) {
                absoluteLeft.linkTo(leftPad.absoluteRight)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent
                height = Dimension.fillToConstraints
            }

            constrain(rightDivider) {
                absoluteRight.linkTo(rightPad.absoluteLeft)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent
                height = Dimension.fillToConstraints
            }
        }
    }
}
