
# Welcome to Our Note Management Application

This application is a comprehensive solution for managing and organizing notes, offering a range of features from creation to retrieval and statistics gathering. Below is a guide to get you started.

## Initial Setup

### Creating MongoDB Container
To begin, initiate the MongoDB container with our provided shell script:
```bash
sh "projectRoot"/bash/restart.sh
```
This will set up the necessary database environment.

## Data Generation and Testing

### Generating Initial Data
For generating initial data, you can utilize our pre-written test:
```java
src/test/java/example/app/NoteRepositoryTest.java#generateContent
```
This script populates the database with starter data for immediate use.

### Note on NoteRepositoryTest
The `NoteRepositoryTest` file is primarily for data generation and functional testing. For integration tests, it is recommended to use a separate container. This can be achieved by adding the following dependency:
```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>testcontainers</artifactId>
    <version>1.15.0</version>
    <scope>test</scope>
</dependency>
```

## API Usage

### Retrieving All Notes
- Sorted by Title and Creation Date:
  ```http
  GET http://localhost:26/api/notes/summaries
  ```
- With all columns:
  ```http
  GET http://localhost:26/api/notes
  ```

### Creating a New Note
```http
POST http://localhost:26/api/notes
{
    "title": "2222",
    "text": "Your note text here...",
    "tags": ["BUSINESS", "PERSONAL"]
}
```

### Updating an Existing Note
```http
PUT http://localhost:26/api/notes
{
    "id": "unique-note-id",
    "title": "Updated Title",
    "createdDate": "2023-11-23T16:22:42.739+00:00",
    "text": "Updated note text",
    "tags": ["BUSINESS", "PERSONAL"]
}
```

### Retrieving Statistics for All Notes
```http
GET http://localhost:26/api/notes/stats
```

### Filtering Notes by Tag
- For tag `BUSINESS`:
  ```http
  GET http://localhost:26/api/notes/tag/BUSINESS
  ```

### Deleting a Note
```http
DELETE http://localhost:26/api/notes/unique-note-id
```

## Additional Resources

### Sample Tests
For examples of how to write tests for the application, refer to:
```java
src/test/java/example/app/NoteTest.java
```
