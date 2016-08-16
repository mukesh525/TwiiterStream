package com.mycompany.twittertest;
import twitter4j.*;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
/**
 *
 * @author Mukesh
 */
public class Stream {

        static String consumerKeyStr = "quWY6UPGiFzF1ASIe02VXkOgo";
	static String consumerSecretStr = "WovIGIzYXPGZbIkl2iRHUH9yccjjODPpbtucdXyF5RPSt6LQjH";
	static String accessTokenStr = "95001898-EMDJoXeWWXXAy9voqWEo69cuevPLM8awXkXOzLIaU";
	static String accessTokenSecretStr = "arxcvVT26tODsfae6pUh3fFtuEugA1UTO6uZVPtOWonbM";

     public static void main(String[] args) throws TwitterException {
       ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(consumerKeyStr);
        cb.setOAuthConsumerSecret(consumerSecretStr);
        cb.setOAuthAccessToken(accessTokenStr);
        cb.setOAuthAccessTokenSecret(accessTokenSecretStr);

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        twitterStream.addListener(listener);
        twitterStream.user();
        FilterQuery fq = new FilterQuery();        
        //String keywords[] = {"sport", "politics", "health"};
        String keywords[] = {"#muk123"};
        fq.track(keywords);  
        twitterStream.filter(fq);  
        twitterStream.addListener(listener);
           
  }

    private static final UserStreamListener listener = new UserStreamListener() {
        @Override
        public void onStatus(Status status) {
            System.out.println("onStatus @" + status.getUser().getScreenName() + " - " + status.getText());
            try {
			Twitter twitter = new TwitterFactory().getInstance();
                        twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
			AccessToken accessToken = new AccessToken(accessTokenStr,accessTokenSecretStr);
                        twitter.setOAuthAccessToken(accessToken);
                        StatusUpdate st = new StatusUpdate("@"+status.getUser().getScreenName() +" hello thanks for your tweet").inReplyToStatusId(status.getId());
                        twitter.updateStatus(st);
                        System.out.println("Successfully updated the status in Twitter.");
		} catch (TwitterException te) {}
      
        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        }

        @Override
        public void onDeletionNotice(long directMessageId, long userId) {
            System.out.println("Got a direct message deletion notice id:" + directMessageId);
        }

        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            System.out.println("Got a track limitation notice:" + numberOfLimitedStatuses);
        }

        @Override
        public void onScrubGeo(long userId, long upToStatusId) {
            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        }

        @Override
        public void onStallWarning(StallWarning warning) {
            System.out.println("Got stall warning:" + warning);
        }

        @Override
        public void onFriendList(long[] friendIds) {
            System.out.print("onFriendList");
            for (long friendId : friendIds) {
                System.out.print(" " + friendId);
            }
            System.out.println();
        }

        @Override
        public void onFavorite(User source, User target, Status favoritedStatus) {
            System.out.println("onFavorite source:@"
                + source.getScreenName() + " target:@"
                + target.getScreenName() + " @"
                + favoritedStatus.getUser().getScreenName() + " - "
                + favoritedStatus.getText());
        }

        @Override
        public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
            System.out.println("onUnFavorite source:@"
                + source.getScreenName() + " target:@"
                + target.getScreenName() + " @"
                + unfavoritedStatus.getUser().getScreenName()
                + " - " + unfavoritedStatus.getText());
        }

        @Override
        public void onFollow(User source, User followedUser) {
            System.out.println("onFollow source:@"
                + source.getScreenName() + " target:@"
                + followedUser.getScreenName());
        }

        @Override
        public void onUnfollow(User source, User followedUser) {
            System.out.println("onFollow source:@"
                + source.getScreenName() + " target:@"
                + followedUser.getScreenName());
        }

        @Override
        public void onDirectMessage(DirectMessage directMessage) {
            System.out.println("onDirectMessage text:"
                + directMessage.getText());
        }

        @Override
        public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {
            System.out.println("onUserListMemberAddition added member:@"
                + addedMember.getScreenName()
                + " listOwner:@" + listOwner.getScreenName()
                + " list:" + list.getName());
        }

        @Override
        public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {
            System.out.println("onUserListMemberDeleted deleted member:@"
                + deletedMember.getScreenName()
                + " listOwner:@" + listOwner.getScreenName()
                + " list:" + list.getName());
        }

        @Override
        public void onUserListSubscription(User subscriber, User listOwner, UserList list) {
            System.out.println("onUserListSubscribed subscriber:@"
                + subscriber.getScreenName()
                + " listOwner:@" + listOwner.getScreenName()
                + " list:" + list.getName());
        }

        @Override
        public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {
            System.out.println("onUserListUnsubscribed subscriber:@"
                + subscriber.getScreenName()
                + " listOwner:@" + listOwner.getScreenName()
                + " list:" + list.getName());
        }

        @Override
        public void onUserListCreation(User listOwner, UserList list) {
            System.out.println("onUserListCreated  listOwner:@"
                + listOwner.getScreenName()
                + " list:" + list.getName());
        }

        @Override
        public void onUserListUpdate(User listOwner, UserList list) {
            System.out.println("onUserListUpdated  listOwner:@"
                + listOwner.getScreenName()
                + " list:" + list.getName());
        }

        @Override
        public void onUserListDeletion(User listOwner, UserList list) {
            System.out.println("onUserListDestroyed  listOwner:@"
                + listOwner.getScreenName()
                + " list:" + list.getName());
        }

        @Override
        public void onUserProfileUpdate(User updatedUser) {
            System.out.println("onUserProfileUpdated user:@" + updatedUser.getScreenName());
        }

        @Override
        public void onUserDeletion(long deletedUser) {
            System.out.println("onUserDeletion user:@" + deletedUser);
        }

        @Override
        public void onUserSuspension(long suspendedUser) {
            System.out.println("onUserSuspension user:@" + suspendedUser);
        }

        @Override
        public void onBlock(User source, User blockedUser) {
            System.out.println("onBlock source:@" + source.getScreenName()
                + " target:@" + blockedUser.getScreenName());
        }

        @Override
        public void onUnblock(User source, User unblockedUser) {
            System.out.println("onUnblock source:@" + source.getScreenName()
                + " target:@" + unblockedUser.getScreenName());
        }

        @Override
        public void onRetweetedRetweet(User source, User target, Status retweetedStatus) {
            System.out.println("onRetweetedRetweet source:@" + source.getScreenName()
                + " target:@" + target.getScreenName()
                + retweetedStatus.getUser().getScreenName()
                + " - " + retweetedStatus.getText());
        }

        @Override
        public void onFavoritedRetweet(User source, User target, Status favoritedRetweet) {
            System.out.println("onFavroitedRetweet source:@" + source.getScreenName()
                + " target:@" + target.getScreenName()
                + favoritedRetweet.getUser().getScreenName()
                + " - " + favoritedRetweet.getText());
        }

        @Override
        public void onQuotedTweet(User source, User target, Status quotingTweet) {
            System.out.println("onQuotedTweet" + source.getScreenName()
                + " target:@" + target.getScreenName()
                + quotingTweet.getUser().getScreenName()
                + " - " + quotingTweet.getText());
        }

        @Override
        public void onException(Exception ex) {
            ex.printStackTrace();
            System.out.println("onException:" + ex.getMessage());
        }
    };
            
    
}
