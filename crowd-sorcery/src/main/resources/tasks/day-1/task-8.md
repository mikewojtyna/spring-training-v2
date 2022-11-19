# Create REST controller to register investors

The controller should allow to register and fetch registered investor.

1. Use separate DTOs to represent register investor command.
2. Upon successful creation, the controller should return `200` HTTP status
   and id of the created resource.
3. The controller should communicate using JSONs
4. Clients can fetch registered user profile by specifying the `id` of the
   profile as parameter.
5. Write automated tests using `@SpringBootTest`, `@AutoconfigureMockMvc`
   and `MockMvc`.

### If that was too easy...

1. Use right HTTP verbs to create and fetch investors
2. Make sure fetch is idempotent
3. Returns `201` status when created
4. Return link to the created resource using `Location` header instead of
   returning `id`
