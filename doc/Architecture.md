# Architecture

## Iteration 2 architecture diagram
![architecture2](Architecture2.png)

## Presentation Layer
---
[AccountActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/AccountActivity.java?ref_type=heads)
* The AccountActivity class manages account creation, allowing users to input details and select a role before registering.
  
[AddBookPopupActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/AddBookPopupActivity.java?ref_type=heads)
* The AddBookPopupActivity class facilitates adding books to courses by displaying a list of available books and enabling users to select and add them to a specific course(Partially implemented and still under construction).
  
[BookInfoActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/BookInfoActivity.java?ref_type=heads)
* e BookInfoActivity displays a book's details, enables saving it as a favorite, and simulates purchasing, alongside showing user ratings and comments.

[BooksForSaleActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/BooksForSaleActivity.java?ref_type=heads)
* The BooksForSaleActivity class is designed to display a list of books for sale by the authenticated user, featuring a layout for each book's details such as name, author, and price. It also includes a back navigation button to return to the LibraryActivity.

[ChangeAccount](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/ChangeAccount.java?ref_type=heads)
* The ChangeAccount class allows users to update their account details, including username, address, and password.

[CoursesBooksActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/CoursesBooksActivity.java?ref_type=heads)
* The CoursesBooksActivity class displays courses and their required books, allowing users to add new courses and books, and providing functionalities to delete books from courses.(Partially implemented and still under construction)

[HomePageActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/HomePageActivity.java?ref_type=heads)
* The HomePageActivity class displays and allows searching and sorting of books, and facilitates navigation to detailed book information.

[LibraryActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/LibraryActivity.java?ref_type=heads)
* The LibraryActivity class provides navigation options within the app to sell books and view books for sale, each through its respective activity.

[LoggedinActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/LoggedinActivity.java?ref_type=heads)
* The LoggedinActivity class provides logged-in users with options to view and edit their account information via the ChangeAccount activity, and to view required course books through the CoursesBooksActivity, facilitating navigation.

[LoginActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/LoginActivity.java?ref_type=heads)
* The LoginActivity class handles user login, offering options to log in, sign up, and navigate back to the home page

[SellBooksActivity](https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9/-/blob/dev/app/src/main/java/com/example/myapplication/presentation/SellBooksActivity.java?ref_type=heads)
* The SellBooksActivity class enables users to sell books by entering details and managing the sale through SellBooksManagement, with navigation to the LibraryActivity upon successful operation.


## Application Layer
---
Main
* The Main class configures the database name for the BookEase application and initializes a JDBC driver.

Services
* The Services class provides singleton access to various databases (book, user, sell books, and favorite books) within the application, initializing them with the database path name obtained from the Main class.

## Business Layer

### authentication
Authenticate
* The Authenticate interface specifies a method for user authentication by username and password.

AuthenticatedUser
* The AuthenticatedUser class implements a singleton pattern to manage the currently authenticated user's information within the application, allowing access to and modification of the user details.

### management
AccountManagement
* The AccountManagement class handles account creation and updates, including validation checks, for users within the application, utilizing a UserDatabase.

AuthenticationManager
* The AuthenticationManager class implements user authentication against a UserDatabase and manages the session of the authenticated user.

BookManagement
* The BookManagement class manages book retrieval and sorting operations using a BookDatabase.

CourseManagement
* The CourseManagement class handles course creation, book assignment, and provides course details, with validations for course and book operations(Unfinished still under construction).

FavouriteBookManagement
* The FavouriteBookManagement class handles adding, removing, and retrieving a user's favorite books using a FavoriteBooksDatabase.

FindableBook
* The FindableBook interface defines methods for viewing, searching books by name, ID, and author, facilitating book retrieval operations.

SellBooksManagement
* The SellBooksManagement class facilitates the sale of books by verifying book existence and adding them to a sales database with specified conditions and price.

Sortable
* The Sortable interface defines methods for sorting lists of books by price, book name, and rating.

### utils
FooterUtility
* The FooterUtility class initializes footer buttons for navigation to profile, home, and library activities in an app, based on user authentication.

RandomGenerator
* The RandomGenerator class provides methods to generate random names, addresses, book names, prices, editions, author names, ratings, and comments for testing or placeholder content.


## Persistence Layer
---
Database

### Implementation

BookDatabaseImpl
* The BookDatabaseImpl class implements the BookDatabase interface, managing book-related operations such as retrieval, addition, and search in a database.

FavouriteBooksDatabaseImpl
* The FavoriteBooksDatabaseImpl manages favorite books in a database, handling additions, deletions, and queries with transaction support and integrity checks.

RatingDatabaseImpl
* The RatingDatabaseImpl class manages ratings in a database, enabling the addition of ratings with comments and retrieval of ratings for a specific book, with transaction support.

SellBooksDatabaseImpl
* The SellBooksDatabaseImpl handles database transactions for adding, deleting, and listing books for sale, ensuring data integrity through managed transactions.

UserDatabaseImpl
* The UserDatabaseImpl handles database operations for users, including search, add, and update actions, with SQL transaction management for data integrity.

### stub

DummyDatabase
* The DummyDatabase is an in-memory stub for user and book data operations, supporting basic add and find functionalities, and used for testing without real database interaction.

### subinterfaces
BookDatabase
* The BookDatabase interface outlines methods for managing and querying book data within a database.

FavouriteBooksDatabase
* The FavoriteBooksDatabase interface defines methods for managing a user's favorite books, including retrieving, adding, and deleting favorites.

RatingDatabase
* The RatingDatabase interface specifies methods for adding ratings with comments to books and retrieving all ratings for a specific book.

SellBooksDatabase
* The SellBooksDatabase interface defines methods for managing books for sale, including retrieving a user's sale books, adding, and deleting sale books.

UserDatabase
* The UserDatabase interface specifies operations for finding, adding, and updating user information in a database.

### utils
DBHelper
* The DBHelper class copies the app's database from assets to internal storage and supports resetting the database, updating its path via the Main class.

## Domain Specific Objects
Book
* The Book class models book entities with properties like ID, name, price, author, and condition, along with functionality to add and manage ratings.

BookProperties
* The BookProperties class encapsulates the descriptive attributes of a book, including condition, edition, and ratings, along with methods to manage and calculate the overall rating.

Course
* The Course class represents a course with attributes like ID and name, and manages a set of required book IDs.

Rating
* The Rating class represents a book rating with a score, comment, and author ID, including validation for rating values and ownership checks for modifications.

User
* The User class models a user with attributes like name, password, type, and address, supporting operations like password check, update, and managing a list of books for sale.


