package com.google.sps.data;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Key;
import java.util.Collection;


public class LearningPathService {

    private final String kind = "LearningPath";
    private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService(); 
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind(kind);
  
    public final LearningPathService instance = new LearningPathService();

    private LearningPathService() {

    }

    public LearningPath create(LearningPath path) {
        Entity task = new Entity(this.kind)
                    .setProperty("name", path.name)

        Key taskKey =  datastore.put(task);

        return new LearningPath(path.name, taskKey.getId());
    }

    public Collection<LearningPath> getMany() {

        Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).build();
        QueryResults<Entity> tasks = datastore.run(query);
        Collection<LearningPath> paths = new ArrayList<>();

        while (tasks.hasNext()) {
            Entity task = tasks.next();

            LearningPath path = new LearningPath(task.getProperty("name"), task.getKey().getId());
            paths.add(path);
        }

        return paths;
    }

    public LearningPath getOne(long id) {

        Key taskKey = KeyFactory.createKey(kind, id);
        Entity task = datastore.get(taskKey);

        return new LearningPath(task.getProperty("name"), task.getKey().getId());
    }

    public LearningPath update(long id, LearningPath path) {

        Key taskKey = KeyFactory.createKey(this.kind, id);

        Entity src = datastore.get(taskKey);

        Entity task = Entity ()
        task.setPropertiesFrom(src);

        task.setProperty("name", )


        datastore.update(task);
    }

    public void delete(long id) {

        Key taskKey = KeyFactory.createKey(kind, id);
        datastore.delete(taskKey);
    }



}