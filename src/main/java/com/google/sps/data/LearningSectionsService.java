package com.google.sps.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;
import java.util.Collection;

public class LearningSectionsService {

    private final String kind = "LearningSection";
    private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind(kind);

    public final LearningSectionsService instance = new LearningSectionsService();
    
    private LearningSectionsService () {

    }

    public Long create(LearningSection section, long learningPathId) {

        Entity task = new Entity(this.kind);

        task.setProperty("name", section.name);
        task.setProperty("learningPathId", learningPathId);

        Key taskKey = datastore.put(task);

        return new LearningSection(section.name, taskKey.getId());
                        
    }

    public Collection<LearningSection> getMany() {

        Query<Entity> query = Query(this.kind);

        QueryResults<Entity> tasks = datastore.run(query);
        Collection<LearningSection> paths = new ArrayList<>();

        while (tasks.hasNext()) {
            Entity task = tasks.next();

            LearningSection path = new LearningSection(task.getProperty("name"), task.getKey().getId());
            paths.add(path);
        }

        return paths;
    }

    public Collection<LearningSection> getMany(long learningPathId) {

        Query<Entity> query = Query(this.kind);
        QueryResults<Entity> tasks = datastore.run(query);
        Collection<LearningSection> paths = new ArrayList<>();

        while (tasks.hasNext()) {
            Entity task = tasks.next();

            LearningSection path = new LearningSection(task.getProperty("name"), task.getKey().getId());
            paths.add(path);
        }

        return paths;
    }

    public LearningSection getOne(long id) {
      

        Key taskKey = KeyFactory.createKey(kind, id);
        Entity task = datastore.get(taskKey);

        return new LearningSection(task.getProperty("name"), task.getKey().getId());

    }

    public LearningSection update(long id, LearningSection section) {

        Entity src = datastore.get(taskKey);

        Entity task = Entity ();
        
        task.setProperty("name", section.name)
 
        datastore.update(task);

        return new LearningSection(section.name, id);
    }

    public void delete(long id) {
        Key taskKey = KeyFactory.createKey(kind, id);
        datastore.delete(taskKey);
    }



}