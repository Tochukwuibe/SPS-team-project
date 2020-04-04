package com.google.sps.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.Collection;
import com.google.appengine.api.datastore.Key;


public class LearningItemService {

    private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind("LearningItem");

    public final LearningItemService instance = new LearningItemService();
    
    private LearningItemService () {

    }

    public LearningItem create(LearningItem item, long sectionId) {                        
         Key taskKey =  keyFactory.newKey();

        Entity task = Entity.newBuilder(taskKey)
                    .set("name", item.name)
                    .set("sectionId", sectionId)
                    .build();

        datastore.put(task);

        return new LearningItem(item.name, taskKey.getId());
    }

    public Collection<LearningItem> getMany() {

        Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).build();
        QueryResults<Entity> tasks = datastore.run(query);
        Collection<LearningItem> items = new ArrayList<>();

        while (tasks.hasNext()) {
            Entity task = tasks.next();
            LearningItem path = new LearningItem(task.getProperty("name"), task.getKey().getId());
            items.add(path);
        }

        return items;

    }

    public Collection<LearningItem> getMany(long learningSectionId) {

        Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).build();
        QueryResults<Entity> tasks = datastore.run(query);
        Collection<LearningItem> items = new ArrayList<>();

        while (tasks.hasNext()) {
            Entity task = tasks.next();
            LearningItem path = new LearningItem(task.getProperty("name"), task.getKey().getId());
            items.add(path);
        }

        return items;
    }

    public LearningItem getOne(long id) {
        Key taskKey = KeyFactory.createKey(kind, id);
        Entity task = datastore.get(taskKey);

        return new LearningItem(task.getProperty("name"), task.getKey().getId());
    }

    public LearningItem update(long id, LearningItem item) {

        Key taskKey = KeyFactory.createKey(kind, id);
        Entity task = Entity.newBuilder(datastore.get(taskKey))
        .set("name", item.name)
        .build();

        datastore.update(task);
    }

    public void delete(long id) {

        Key taskKey = KeyFactory.createKey(kind, id);
        datastore.delete(taskKey);

    }



}