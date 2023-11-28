<h1>52000820 - Nguyễn Lâm Thành Long</h1>
<h1 align="center">
  <img src="https://user-images.githubusercontent.com/114124106/229993734-aca974ee-a58b-4452-86e9-8cf94bfad6df.png" alt="Markdownify" width="600px">
</h1>
<h3 align="center">Midterm Assignment</h3>
<br>

## Principles and technologies used

* Principles Used:
  - Single Responsibility (Each class should only have one responsibility. Furthermore, it should only have one reason to change).
  - Spring MVC:
    + Consumer Layer or Controller: this is the layer that communicates with the outside and handles requests from outside to the system.
    + Service Layer: Perform operations and handle logic.
    + Repository Layer:: Responsible for communicating with DBs, storage devices, query processing, and returning data types that the Service layer requires.
* Technologies Used:  
  - Java Spring Boot application (Maven project)
  - PostgreSQL (Storage data)
  - Docker (Connect to DB)
  - I perform the function of uploading product images by save image in folder static . The advantage of local folder is to retrieve images quickly when loading from the server to the client.
  - In this Assignment, I used Spring security to assign permissions for the admin and user. That is, the object logged in as an admin, can access the admin's pages such as adding, deleting, and editing products (product management). Admin will also manage the orders that customers have paid before (order management).
  - If the logged-in object is a customer then they can see the product list. If the customer finds a product that they like, they can view its
details and add it to their shopping cart and proceed to place an order.
  - Thymeleaf (Java template engine for processing and creating HTML, XML, JavaScript, CSS and text).


## Project Structure
* Project overview image
  <h1 align="center">
    <br>
      <img src="https://github.com/thanhlongqax/anhReadme/blob/main/cauTrucDuAn.png?raw=true" alt="Markdownify" width="600px">
    <br>
  </h1>
 * Folder Details
    - Folder Model: is the place where all the entity tables are stored and created and stored directly in the Database.
    - Folder Controller: methods are the final destination point that a web request can reach. After being invoked, the controller method starts to process the web request by interacting with the service layer to complete the work that needs to be done.
    - Folder Service: includes methods for performing system functions, interacting directly with the controller.
    - Folder Repository: responsible for communicating with DBs, storage devices, query processing, and returning data types requested by the Service layer.
    - Folder User: include user model and MyUserDetail to store user information which is later encapsulated into Authentication objects
    - Folder Security: The WebSecurityConfig class is annotated with @EnableWebSecurity to enable Spring Security’s web security support and provide the Spring MVC integration. Perform the function of decentralization for users and admins and configure web page redirect when accessing the path is not allowed
    - Folder Implement: CustomUserDetails is used by DaoAuthenticationProvider for retrieving a username, a password, and other attributes for authenticating with a username and password. Spring Security provides in-memory and JDBC implementations of UserDetailsService. This class implement from UserDetailsService.
    - Folder Configuration: DataLoader is data includes brand , category , color , product for my website
    - Besides, the project has Resources Folder, which includes two Folders. That is Static folder include some file css, image, and the Templates Folder include some file HTML about the user interface, error, custom error, and permission page when the user access to the page about the admin.
    

## Spring Security
![image](https://github.com/thanhlongqax/anhReadme/blob/main/Security.png?raw=true)

## Admin Permission
- With the absolute security and decentralization of Spring Security. Admin can access all the links of the system.
<h4 align="center">
    <br>
      <img src="https://github.com/thanhlongqax/anhReadme/blob/main/trangChuAdmin.png?raw=true" alt="image" width="100%">
    <br>
</h4>

![screencapture-localhost-8080-orderManagement-2023-04-09-15_14_35](https://github.com/thanhlongqax/anhReadme/blob/main/Admin_sp.png?raw=true)
![screencapture-localhost-8080-orderDetails-4-2023-04-09-15_15_06](https://github.com/thanhlongqax/anhReadme/blob/main/category_Admin.png?raw=true)
![screencapture-localhost-8080-orderDetails-4-2023-04-09-15_15_06](https://github.com/thanhlongqax/anhReadme/blob/main/color_Admin.png?raw=true)
![screencapture-localhost-8080-orderDetails-4-2023-04-09-15_15_06](https://github.com/thanhlongqax/anhReadme/blob/main/brand_Admin.png?raw=true)

### User Permission
- In contrast to admin, users can only access allowed paths, not allowed to access paths located in ROLE ADMIN
- Here are the links that users are not allowed to access
  ![screencapture-localhost-8080-orderManagement-2023-04-09-15_14_35](https://github.com/thanhlongqax/anhReadme/blob/main/kocoquyentruycapadmin.png?raw=true)
### And below are some pages that both ROLE user and admin can access:
<h4 align="center">
    <br>
      <img src="https://github.com/thanhlongqax/anhReadme/blob/main/trangchuUser.png?raw=true" alt="image" width="100%">
    <br>
</h4>
<h4 align="center">
    <br>
      <img src="https://github.com/thanhlongqax/anhReadme/blob/main/detailProduct.png?raw=true" alt="image" width="100%">
    <br>
</h4>

<h4 align="center">
    <br>
      <img src="https://github.com/thanhlongqax/anhReadme/blob/main/buttonCart.png?raw=true" alt="image" width="100%">
    <br>
</h4>
<h4 align="center">
    <br>
      <img src="https://github.com/thanhlongqax/anhReadme/blob/main/Cart.png?raw=true" alt="image" width="100%">
    <br>
</h4>

> **Note**
> > And there are some pages that are managed by admin and can only be accessed by admin such as adding, deleting, editing products, adding brand ,deleting brand , editing brand , adding color , editing color , delete color , adding category , editing category , delete category managing products as well as managing order details. You can experience the system through the steps below!!

## How To Build Project

To clone and run this application, From your command line:

```bash
# Clone this repository
$ https://github.com/thanhlongqax/Midterm.git

# Open project by IntelliJ IDE

# Open Docker , continue docker: Compose Deloyment starting and using PostgreSql by Dbeaver:


# # Don't need access database by I added data into database by dataLoader class in Configuration package

# When we clone the repository from GitHub, we will have a SQL file. What we need to do is insert this file into the database if data in configuration package is failed

# And click run project.

# Next, open your browser and access the link:
$ http://localhost:8080/

# Spring Security's login interface will appear.
# Below are two accounts that I have made available corresponding to its ROLEs, which are ADMIN and USER:

# ADMIN ACCOUNT:
$ Username: admin
$ Password: 12345678

# USER ACCOUNT:
$ Username: vovanthanh
$ Password: 12345678

# These passwords will correspond to the encrypted password of the users' table in the database
   
```

> **Note**
> Make sure you have fully implemented the functions listed above to be able to successfully run the project. but that is not a concern.Good luck !!!

## CRUD Commmad & Postman Snapshots

### Request methods

The request method is the way we distinguish what kind of action our endpoint is being "asked" to perform. Below are the request methods I used in project. Includes method name and concept.

| Method   | Description                                                 |
| -------- | ----------------------------------------------------------- |
| `GET`    | Used to retrieve a single item or a collection of items.    |
| `POST`   | Used when creating new items e.g. a new product, order,netc.|
| `PUT`    | Used to replace a whole item (all fields) with new data.    |
| `DELETE` | Used to delete an item.                                     |


### Full CURL commands

### API
#### Brand API

| Method   | URL                                       | Description                              |
| -------- | ----------------------------------------  | ---------------------------------------- |
| `GET`    | `/api/brands`                              | Retrieve all brands.                     |

#### Cart API

| Method | URL              | Description                |
|--------|------------------|----------------------------|
| `GET`  | `/api/carts`      | Retrieve all carts.        |
| `POST` | `/api/cart/save` | Save data cart in database. |

#### Category API

| Method   | URL               | Description                              |
| -------- |-------------------| ---------------------------------------- |
| `GET`    | `/api/categories` | Retrieve all categories.                 |
#### Color API

| Method   | URL           | Description                              |
| -------- |---------------| ---------------------------------------- |
| `GET`    | `/api/colors` | Retrieve all colors.                     |

#### Image API

| Method   | URL            | Description                      |
| -------- |----------------|----------------------------------|
| `GET`    | `/api/images`  | Retrieve image by name of image. |

#### Product API
| Method   | URL                    | Description                                                                                |
|----------|------------------------|--------------------------------------------------------------------------------------------|
| `GET`    | `/api/products`        | Retrieve all products.                                                                     |
| `GET`    | `/api/products/filter` | filter product by name ,category , color , brand , name , minimun price and maximum price. |
| `GET`    | `/api/products/{id}`   | Retrieve  products  by id                                                                  ||

> **Note**
> I just listed the api used in the project. Because the project has security by [Spring security](https://docs.spring.io/spring-security/reference/index.html), when testing the API we have to turn it off and switch back to [RestController](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html)

## Postman Snapshots:
#### Brand API
![image](https://github.com/thanhlongqax/anhReadme/blob/main/api/brands.png?raw=true)
#### Cart API
![image](https://github.com/thanhlongqax/anhReadme/blob/main/api/carts.png?raw=true)

#### Category API
![image](https://github.com/thanhlongqax/anhReadme/blob/main/api/categories.png?raw=true)

#### Color API
![image](https://github.com/thanhlongqax/anhReadme/blob/main/api/colors.png?raw=true)

#### Image API
![image](https://github.com/thanhlongqax/anhReadme/blob/main/api/picture.png?raw=true)

#### Product API
![image](https://github.com/thanhlongqax/anhReadme/blob/main/api/productbyid.png?raw=true)
![image](https://github.com/thanhlongqax/anhReadme/blob/main/api/products.png?raw=true)


> **Note**
> Do the same for the remaining methods and tables. The APIs are already written in the code, to avoid long lines I didn't insert any images. Thank you so much !!


## Testcase JUnit4 using Mockito
In this project I will create test cases for that is ProductController and BrandApiController.
### BrandApiControllerTest
- I have success 4 testcase for this api Controller. Details in the code:
  <h4 align="left">
  <br>
  <img src="https://github.com/thanhlongqax/anhReadme/blob/main/brandTest.png?raw=true" width="300px">
  <br>
  </h4>

### ProductControllerTest
  - I have success 4 testcase for this Controller. Details in the code:
<h4 align="left">
    <br>
      <img src="https://github.com/thanhlongqax/anhReadme/blob/main/productTest.png?raw=true" alt="image" width="500px">
    <br>
</h4>




## Diagram
### Entity-relationship diagram
<h2 align="center">
    <br>
      <img src="https://github.com/thanhlongqax/anhReadme/blob/main/ERDMidterm.png?raw=true" alt="image" width="100%">
    <br>
  </h2>

### Schema of the database
<h2 align="center">
    <br>
      <img src="https://github.com/thanhlongqax/anhReadme/blob/main/ERD.png?raw=true" alt="image" width="100%">
    <br>
  </h2>
  
## References
  - [How to draw an Entity Relatioship Diagram](https://www.gliffy.com/blog/how-to-draw-an-entity-relationship-diagram)
  - [Spring Security for Spring Boot Integration Tests](https://www.baeldung.com/spring-security-integration-tests)
  - [Spring boot home](https://spring.io/)
  - [Restfull API Tutorials](https://www.tutorialspoint.com/spring_boot/spring_boot_building_restful_web_services.html)
  - Lab lessons taught by Mr. Vo Van Thanh

