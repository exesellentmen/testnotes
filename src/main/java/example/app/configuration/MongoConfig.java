package example.app.configuration;

import example.app.entities.Note;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.IndexOperations;

@Configuration
public class MongoConfig {
    private final MongoTemplate mongoTemplate;

    public MongoConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void initIndices() {
        IndexOperations indexOps = mongoTemplate.indexOps(Note.class);
        indexOps.ensureIndex(new Index().on("createdDate", Sort.Direction.DESC));
        indexOps.ensureIndex(new Index().on("tags", Sort.Direction.ASC));
    }
}
