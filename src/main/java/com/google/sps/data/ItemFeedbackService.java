package com.google.sps.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;
import java.util.Collection;

public class ItemFeedbackService {

    private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind("LearningItem");

    public final ItemFeedbackService instance = new ItemFeedbackService();
    
    private ItemFeedbackService () {

    }

    public Long create(ItemFeedback path) {

    }

    public Collection<ItemFeedback> getMany() {

    }

    public Collection<ItemFeedback> getMany(long learningSectionId) {

    }

    public ItemFeedback getOne(long id) {

    }

    public ItemFeedback update(long id, ItemFeedback path) {

    }

    public void delete(long id) {

    }

}