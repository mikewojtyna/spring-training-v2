# Products RESTful API

Hello everyone!

Our goal during this training is to create a fully RESTful API to CRUD
products. By fully RESTful API we mean a level 4 REST API according to the
[Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)

![Richardson Maturity Model](richardson.png)

We are going to use [HATEAOS](https://en.wikipedia.org/wiki/HATEOAS) implemented
using [HAL](https://en.wikipedia.org/wiki/Hypertext_Application_Language)
document format.

Our product resource has the following structure

```json
{
  "id": 1,
  "name": "Implementing DDD",
  "description": "a famous red book by Vaughn Vernon",
  "quantity": 10,
  "category": "books"
}
```

## Requirements

These are the requirements we need to implement

### Create a product

1. Allow to create new products using http `POST` method using
   `/api/products` URI and by passing the product JSON as request body
2. Upon creating a new product, it should be returned as JSON response body
3. The `Location` header pointing to the just created product must be also
   included, so we can easily navigate the resources
4. `201` status should be returned upon successful creation
5. A link pointing to the created resource should be also included in the
   response body
6. Product id should be automatically generated sequentially (1, 2, 3, ...)

### Get all products

1. Users can get all products by using `GET` at `/api/products` URI. This
   should return an object containing products. The self-link to
   `/api/products` should be also included.
2. Each individual product should also include a self-link, e.g.
   `/api/products/1` for product with `id=1`
3. Results should be paginated, so users can request
   e.g. `/api/products?page=2&size=10` that will return the second page
   containing 10 elements max.
4. Results can be sorted, e.g. `/api/products?page=2&size=10&sort=quantity,
   desc` should return the second page containing 10 elements max sorted by
   quantity in descending order
5. The API should allow to search products by `name`, `category`,
   `description` and `quantity`. For `name` field we should also support
   partial match (return all products with name containing the given string).
   For
   example: `/api/products/search/findByName?page=1&size=10&name=abc&sort=quantity,desc`
   should return the first page containing max 10 products, where each
   product must be named **exactly** `abc` sorted by quantity in descending
   order. Another example returning the same results, but filtered by names
   **
   containing** `abc`: `/api/products/search/findByName?page=1&size=10&name=abc&sort=quantity,desc`
6. Finally, these results should include pagination metadata field
    ```
    "page": {
            "size": 20,
            "totalElements": 4,
            "totalPages": 1,
            "number": 0
        }
    ```
   and links to allow easy page navigation by automatically generated clients
   ```
   "_links": {
        "first": {
            "href": "http://localhost:8080/products/search/findByName?name=abc&page=0&size=1"
        },
        "prev": {
            "href": "http://localhost:8080/products/search/findByName?name=abc&page=0&size=1"
        },
        "self": {
            "href": "http://localhost:8080/products/search/findByName?name=abc&page=1&size=1"
        },
        "next": {
            "href": "http://localhost:8080/products/search/findByName?name=abc&page=2&size=1"
        },
        "last": {
            "href": "http://localhost:8080/products/search/findByName?name=abc&page=8&size=1"
        }
    }
   ```

### Get the single product

Calling `GET` on specific product URI, e.g. `/api/products/1` should return
only this product. All HATEOAS links using HAL document format should be
included. If product doesn't exist, then `404` status should be returned.

### Update the product

1. Calling `PUT` on specific product, e.g. `/api/products/1` with product
   JSON request body should **completely** replace the product. This means,
   that all fields, excluding id, should be replaced with new body.
   Specifically, if you don't pass all product fields in the request body,
   then the omitted fields should be replaced with `null`.
2. Calling `PATCH` on specific product, e.g. `api/products/1` with product
   JSON request body should **partially** update the product, only with
   given fields. So, in such case only fields explicitly passed in the
   request body would be replaced. Therefore, there's no risk of nulling
   fields by mistake.

### Delete the product

Calling `DELETE` on specific product URI, e.g. `/api/products/1` should
delete this product. The response status should be `204`.

### Persistence

We should persist all changes
using [JPA](https://pl.wikipedia.org/wiki/Java_Persistence_API) ORM and any
relational database.

### Executable

The application should be fully executable by using a single self-contained
jar. User should simply execute `java -jar app.jar` and get everything up
and running, including embedded web server and database. The **only**
requirement for the end user to execute the app, is to have Java 19 installed
on his/her machine.