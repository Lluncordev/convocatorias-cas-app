package com.dooofinance.convocatoriascas.src.function

import android.app.Activity
import com.google.android.play.core.review.ReviewManagerFactory

fun FeedbackDialog(context: Activity){
    val manager = ReviewManagerFactory.create(context)
    manager.requestReviewFlow().addOnCompleteListener {
        if (it.isSuccessful){
            manager.launchReviewFlow(context, it.result)
        }
    }
}