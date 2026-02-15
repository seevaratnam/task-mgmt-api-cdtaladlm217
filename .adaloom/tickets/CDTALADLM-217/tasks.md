## 1. Project Setup
- [ ] 1.1 Initialize Spring Boot 3.2 project with Java 21 and required dependencies (web, data-jpa, h2, springdoc, test)
- [ ] 1.2 Configure application properties for H2 in-memory database and JPA schema management
- [ ] 1.3 Add basic project structure packages (controller, service, repository, model, dto, config)

## 2. Data Model & Persistence
- [ ] 2.1 Define TaskStatus enum with TODO, IN_PROGRESS, DONE
- [ ] 2.2 Implement Task JPA entity with fields, UUID id, timestamps, and annotations
- [ ] 2.3 Create TaskRepository extending JpaRepository
- [ ] 2.4 Add JPA auditing or manual timestamp handling for createdAt/updatedAt
- [ ] 2.5 Verify H2 schema generation by running app and checking tables

## 3. DTOs & Validation
- [ ] 3.1 Create TaskCreateRequest DTO with validation annotations (title required)
- [ ] 3.2 Create TaskUpdateRequest DTO for PUT/PATCH with appropriate validation rules
- [ ] 3.3 Create TaskResponse DTO mapping entity to API response
- [ ] 3.4 Add validation error handling with @ControllerAdvice and standardized error response
- [ ] 3.5 Test validation handling manually with missing title

## 4. Service Layer
- [ ] 4.1 Implement TaskService interface with CRUD method signatures
- [ ] 4.2 Implement TaskServiceImpl with repository interactions and business logic
- [ ] 4.3 Add not-found handling and map to 404 errors
- [ ] 4.4 Add logic for partial update (PATCH) merging fields
- [ ] 4.5 Unit test service methods with in-memory repository or mocked repository

## 5. REST Controllers
- [ ] 5.1 Implement TaskController with POST /api/v1/tasks
- [ ] 5.2 Implement TaskController with GET /api/v1/tasks/{id}
- [ ] 5.3 Implement TaskController with GET /api/v1/tasks
- [ ] 5.4 Implement TaskController with PUT /api/v1/tasks/{id}
- [ ] 5.5 Implement TaskController with PATCH /api/v1/tasks/{id}
- [ ] 5.6 Implement TaskController with DELETE /api/v1/tasks/{id}
- [ ] 5.7 Verify HTTP status codes and Location header for POST

## 6. OpenAPI Documentation
- [ ] 6.1 Configure springdoc-openapi dependency and basic configuration
- [ ] 6.2 Add OpenAPI annotations for Task endpoints and schemas
- [ ] 6.3 Verify Swagger UI доступ at /swagger-ui.html or /swagger-ui/index.html

## 7. Testing
- [ ] 7.1 Create controller tests with MockMvc for all CRUD endpoints
- [ ] 7.2 Add tests for validation errors (missing title returns 400)
- [ ] 7.3 Add tests for 404 on unknown task ID
- [ ] 7.4 Add tests for PATCH partial updates and status transitions
- [ ] 7.5 Add integration test verifying H2 persistence for create/read
- [ ] 7.6 Run full test suite and ensure green

## 8. Final Verification
- [ ] 8.1 Manual smoke test: create, list, update, delete via REST client
- [ ] 8.2 Verify OpenAPI spec renders and includes all endpoints
- [ ] 8.3 Confirm code formatting and build passes without warnings