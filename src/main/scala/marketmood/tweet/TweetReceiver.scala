package marketmood.tweet

import java.util.{ArrayList, List}

import twitter4j.{Query, QueryResult, Status, Twitter, TwitterException, TwitterFactory}
/**
 * Created by sgandhi on 10/17/15.
 */

object TweetReceiver {
  def getTweets(topic: String): ArrayList[String] = {
    val twitter: Twitter = new TwitterFactory().getInstance
    val tweetList: ArrayList[String] = new ArrayList[String]
    try {
      var query: Query = new Query(topic)
      var result: QueryResult = null
      do {
        result = twitter.search(query)
        val tweets: List[Status] = result.getTweets
        import scala.collection.JavaConversions._
        for (tweet <- tweets) {
          tweetList.add(tweet.getText)
        }
      } while ((({
        query = result.nextQuery; query
      })) != null)
    }
    catch {
      case te: TwitterException => {
        te.printStackTrace
        System.out.println("Failed to search tweets: " + te.getMessage)
      }
    }
    return tweetList
  }
}

