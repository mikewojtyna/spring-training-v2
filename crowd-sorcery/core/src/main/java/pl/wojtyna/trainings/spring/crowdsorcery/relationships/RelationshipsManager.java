package pl.wojtyna.trainings.spring.crowdsorcery.relationships;

import java.util.concurrent.ConcurrentLinkedQueue;

public class RelationshipsManager {

    private final ConcurrentLinkedQueue<Friends> friends = new ConcurrentLinkedQueue<>();

    public void makeFriends(String firstUserId, String secondUserId) {
        friends.add(new Friends(firstUserId, secondUserId));
    }

    public boolean areFriends(String firstUserId, String secondUserId) {
        return friends.stream().anyMatch(friends -> friends.contains(firstUserId) && friends.contains(secondUserId));
    }

    private record Friends(String firstUserId, String secondUserId) {

        private boolean contains(String userId) {
            return firstUserId.equals(userId) || secondUserId.equals(userId);
        }
    }
}
