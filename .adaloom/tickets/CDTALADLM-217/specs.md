### Requirement: Task entity representation MUST include required and optional fields with specified types.
#### Scenario:
GIVEN a task is created with title, description, status, and dueDate  
WHEN the task is retrieved via GET by id  
THEN the response MUST include id (UUID), title (String), description (String or null), status (TODO/IN_PROGRESS/DONE), dueDate (ISO-8601 LocalDate or null), createdAt (Instant), and updatedAt (Instant)

#### Scenario:
GIVEN a task is created with only the required title  
WHEN the task is retrieved  
THEN description, status, and dueDate MAY be null or defaulted according to implementation, and createdAt/updatedAt MUST be present

---

### Requirement: The API MUST provide a POST /api/v1/tasks endpoint to create tasks.
#### Scenario:
GIVEN a valid task payload with a non-empty title  
WHEN the client POSTs to /api/v1/tasks  
THEN the API MUST return HTTP 201 and the created task representation

#### Scenario:
GIVEN a payload missing the title field or with an empty title  
WHEN the client POSTs to /api/v1/tasks  
THEN the API MUST return HTTP 400 with validation error details

#### Scenario:
GIVEN a payload with an invalid status value outside TODO/IN_PROGRESS/DONE  
WHEN the client POSTs to /api/v1/tasks  
THEN the API MUST return HTTP 400 with validation error details

---

### Requirement: The API MUST provide a GET /api/v1/tasks/{id} endpoint to retrieve a task by id.
#### Scenario:
GIVEN an existing task id  
WHEN the client GETs /api/v1/tasks/{id}  
THEN the API MUST return HTTP 200 and the task representation

#### Scenario:
GIVEN a non-existent task id  
WHEN the client GETs /api/v1/tasks/{id}  
THEN the API MUST return HTTP 404

---

### Requirement: The API MUST provide a GET /api/v1/tasks endpoint to list tasks.
#### Scenario:
GIVEN multiple tasks exist  
WHEN the client GETs /api/v1/tasks  
THEN the API MUST return HTTP 200 and a list containing all tasks

#### Scenario:
GIVEN no tasks exist  
WHEN the client GETs /api/v1/tasks  
THEN the API MUST return HTTP 200 and an empty list

---

### Requirement: The API MUST provide a PUT /api/v1/tasks/{id} endpoint to replace a task.
#### Scenario:
GIVEN an existing task id and a valid full task payload with a non-empty title  
WHEN the client PUTs /api/v1/tasks/{id}  
THEN the API MUST return HTTP 200 and the updated task representation

#### Scenario:
GIVEN a non-existent task id and a valid full task payload  
WHEN the client PUTs /api/v1/tasks/{id}  
THEN the API MUST return HTTP 404

#### Scenario:
GIVEN an existing task id and a payload missing required title  
WHEN the client PUTs /api/v1/tasks/{id}  
THEN the API MUST return HTTP 400 with validation error details

---

### Requirement: The API MUST provide a PATCH /api/v1/tasks/{id} endpoint to update a task partially.
#### Scenario:
GIVEN an existing task id and a payload with a subset of fields  
WHEN the client PATCHes /api/v1/tasks/{id}  
THEN the API MUST return HTTP 200 and the updated task representation with unchanged fields preserved

#### Scenario:
GIVEN an existing task id and a payload with an invalid status value  
WHEN the client PATCHes /api/v1/tasks/{id}  
THEN the API MUST return HTTP 400 with validation error details

#### Scenario:
GIVEN a non-existent task id  
WHEN the client PATCHes /api/v1/tasks/{id}  
THEN the API MUST return HTTP 404

---

### Requirement: The API MUST provide a DELETE /api/v1/tasks/{id} endpoint to delete a task.
#### Scenario:
GIVEN an existing task id  
WHEN the client DELETEs /api/v1/tasks/{id}  
THEN the API MUST return HTTP 204 and the task MUST be removed from persistence

#### Scenario:
GIVEN a non-existent task id  
WHEN the client DELETEs /api/v1/tasks/{id}  
THEN the API MUST return HTTP 404

---

### Requirement: The API MUST persist tasks in an H2 in-memory database.
#### Scenario:
GIVEN a task is created  
WHEN the application restarts with a fresh in-memory database  
THEN previously created tasks MUST NOT be present

#### Scenario:
GIVEN a task is created and then retrieved within the same runtime  
WHEN the client GETs /api/v1/tasks/{id}  
THEN the task MUST be returned from persistence

---

### Requirement: The API SHOULD publish OpenAPI/Swagger documentation at a standard endpoint.
#### Scenario:
GIVEN the application is running  
WHEN the client GETs /swagger-ui.html or /swagger-ui/index.html  
THEN the Swagger UI SHOULD be accessible and load the API documentation

#### Scenario:
GIVEN the OpenAPI JSON is generated  
WHEN the client GETs the OpenAPI endpoint provided by the framework  
THEN it SHOULD describe all CRUD endpoints and their request/response schemas

---

### Requirement: Validation errors MUST return HTTP 400 with error details.
#### Scenario:
GIVEN a create or update request with missing title  
WHEN the request is processed  
THEN the API MUST return HTTP 400 and include a validation error message referencing the title field

#### Scenario:
GIVEN a request with an invalid date format for dueDate  
WHEN the request is processed  
THEN the API MUST return HTTP 400 with validation error details

---

### Requirement: The API MUST use UUID identifiers for tasks.
#### Scenario:
GIVEN a task is created  
WHEN the API returns the created task  
THEN the id field MUST be a valid UUID string

#### Scenario:
GIVEN a request uses a non-UUID id format  
WHEN the client calls GET/PUT/PATCH/DELETE /api/v1/tasks/{id}  
THEN the API MUST return HTTP 400 or HTTP 404 according to framework behavior, and MUST NOT return a successful response