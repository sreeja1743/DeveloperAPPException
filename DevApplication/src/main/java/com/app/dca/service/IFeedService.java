package com.app.dca.service;

import java.util.List;

import com.app.dca.entity.Feed;
import com.app.dca.exception.*;
public interface IFeedService {

	Feed addFeed(Feed feed);
	
	Feed editFeed(Feed feed);
	
	Feed likeFeed(int feedId);
	
	Feed getFeed(int feedId);
	
	Feed removeFeed(int feedId);
	//jpql
	List<Feed> getFeedsByDeveloper(int devId) throws UnknownDeveloperException;
	
	List<Feed> getFeedsByKeyword(String keyword);
	
	List<Feed> getFeedsByTopic(String topic);
}