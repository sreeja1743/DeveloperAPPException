package com.app.dca.restcontroller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.dca.dto.FeedDTO;
import com.app.dca.entity.Feed;
import com.app.dca.service.IFeedService;
import com.app.dca.service.IFeedServiceImpl;
import com.app.dca.exception.GlobalExceptionHandler;
import com.app.dca.exception.UnknownDeveloperException;
import com.app.dca.exception.UnknownFeedException;

@Validated
@RestController
@RequestMapping("/api")
public class FeedRestController {
     
	@Autowired
	private IFeedServiceImpl service;
	
	@PostMapping("/feed")
	public FeedDTO addFeed(@RequestBody @Valid Feed f) {
		 Feed dup = service.addFeed(f);
		 FeedDTO dupFeed = new FeedDTO(dup.getFeedId(),dup.getQuery(),dup.getFeedDate(),dup.getFeedTime(),dup.getTopic(),dup.getRelevance(),dup.getTotalComments());
		 return dupFeed;
	}
	
	@PutMapping("/updatefeed")
	public Feed updateFeed(@RequestBody Feed f) {
	return service.editFeed(f);
	}
	
	@GetMapping("/feedId/{id}")
	public Feed getFeed(@PathVariable int id)throws UnknownFeedException{
		if(service.getFeed(id).equals(null))
			throw new UnknownFeedException();
		return service.getFeed(id);
	}
	
	
	@DeleteMapping("/deletefeed/{id}")
	public Feed removeFeed(@PathVariable int id) throws UnknownFeedException{
		return service.removeFeed(id);
	}
	
	@GetMapping("/DevelopersFeed/{id}")
	public List<Feed> getFeedsByDeveloper(@PathVariable int id) throws UnknownDeveloperException{
		return service.getFeedsByDeveloper(id);
	}
	
	@GetMapping("/topic/{topic}")
	public List<Feed> getFeedsByTopic(@PathVariable String topic){
		return service.getFeedsByTopic(topic);
	}
	
	@GetMapping("/keyword/{keyword}")
	public List<Feed> getFeedsByKeyword(@PathVariable String keyword){
		return service.getFeedsByKeyword(keyword);
	}
	
}