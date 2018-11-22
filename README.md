Todo
=================

With the unrelenting surge of new Todo Apps showcasing the latest front-end
trends, this exercise involves creating a RESTful API to save user's todo items
between sessions.

### Tech Stack

- Java 8
- Spring Boot, MVC, JPA
- H2 in-memory datastore
- Maven 3
- Cucumber, JUnit 4
- SpringFox Swagger

API Specification
-----------------

### Content Types

All endpoints consume and produce `Content-Type: application/json`

### JSON Types

```
TodoItem :: {
  "title" :: String  -- the title of the todo item
}

SavedTodoItem :: {
  "title"     :: String,  -- the title of the saved todo item
  "url"       :: String,  -- the URL path that the item can be fetched from
  "completed" :: Boolean  -- indicates whether the todo item has been completed
}
```

### Endpoints

#### Fetch all todo items

##### Request

Method: `GET`
Path:   `/todo`

##### Response

Status: `200`
Body:   `[SavedTodoItem]`
Example:
```
[{ "title": "First todo", "url": "/todo/1", "completed": true },
 { "title": "Second todo", "url": "/todo/2", "completed": false }]
```

#### Fetch a specific todo item

##### Request

Method: `GET`
Path:   `/todo/:id`

##### Response

Status: `200`
Body:   `SavedTodoItem`
Example:
```
{ "title": "First todo", "url": "/todo/1", "completed": true }
```

##### Errors

`404` - Item does not exist

#### Create new todo item

##### Request

Method: `POST`
Path:   `/todo`
Body:   `TodoItem`
Example:
```
{ "title": "Example title" }
```

##### Response

Status: `201`
Body:   `SavedTodoItem`
Example:
```
{ "title": "Example title", "url": "/todo/3", "completed": false }
```

#### Mark an existing todo item as completed

##### Request

Method: `PUT`
Path:   `/todo/:id/completed`
Body:   `Boolean`
Example:
```
true
```

##### Response

Status: `200`
Body:   `SavedTodoItem`
Example:
```
{ "title": "Example title", "url": "/todo/3", "completed": true }
```

##### Errors

`404` - Item does not exist

#### Delete an existing todo item

##### Request

Method: `DELETE`
Path:   `/todo/:id`

##### Response

Status: `204`

##### Errors

`404` - Item does not exist



Further Considerations
----------------------

* How might you change the API specification to support multiple users?
        -   and 'user' to domain and to the urls
        -   add Spring security and JWT tokens so we know who the user is
* How might you support high volumes of writes to the API?
        - set cloud to auto scale more instances of backend service 
        -  introduce a messaging queue to take pressure off datastore if this becomes an issue        
* How might you support high volumes of reads from the API?
        - as above for scalling, plus consider using a cache like infinispan or one of the commercial products to preload data
* How might you handle concurrent attempts to update the same item?
    - for Relational database rely on optimistic locking and alert user of failed modification attempt
    - for NoSQL datasource use versioning and compare versions and alert user if they have stale version
* Possible changes to API?
    1. I would get rid of the TodoItem and rename the SavedTodoItem (to TodoItem). Having both is clinging to an obsolete Data Transfer Object pattern which adds no value when we have JSON and Jackson and have documented our API. Sometimes you still Transfer objects for complex nested domain models or for divergent response representations, however this should be done on a needs basis, it is not needed in this instance.
    2. We are hand crafting a strictish RESTful pattern with the url field, Spring provides inbuilt support which provides automatic semantic linking, which might be more suitable, however it needs a slightly different implementation and responses are more verbose.
    3. Webflux and Netty support in Spring 5 provides a reactive, asynchronous option which probably should now be the default implementation for apps where we expect a large number of users
    4. With the Cucumber tests I'm trialling a more granular approach to see if it makes TDD easier, by making a distinction between a feature and a business capability, i.e a number of features deliver a business capability 
