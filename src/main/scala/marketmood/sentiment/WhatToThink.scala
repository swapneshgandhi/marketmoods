package marketmood.sentiment

import java.util

import marketmood.tweet.TweetReceiver

/**
 * Created by sgandhi on 10/17/15.
 */
object WhatToThink {
  def main(args: Array[String]) {
  //  val tweets: util.ArrayList[String] = TweetReceiver.getTweets("$SPY")
    SentimentAnalyzer.init
    val tweet: String = "sell sell sell"
    System.out.println(tweet + " : " + SentimentAnalyzer.findSentiment(tweet))
  }
}
