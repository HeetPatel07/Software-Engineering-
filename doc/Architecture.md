# Architecture

## Presentation Layer
---
AccountActivity
* The AccountActivity class manages account creation in an Android application, allowing users to input details and select a role before registering.
  
AddBookPopupActivity
* The AddBookPopupActivity class facilitates adding books to courses by displaying a list of available books and enabling users to select and add them to a specific course within an Android application.
  
BookInfoActivity
* The BookInfoActivity class showcases detailed book information, user ratings, and comments, and provides buttons for buying or saving a book, with actions leading to a placeholder alert for construction.

BooksForSaleActivity
* The BooksForSaleActivity class is designed to display a list of books for sale by the authenticated user within an Android application, featuring a layout for each book's details such as name, author, and price. It also includes a back navigation button to return to the LibraryActivity.

ChangeAccount
* The ChangeAccount class allows users to update their account details, including username, address, and password, within an Android application.

CoursesBooksActivity
* The CoursesBooksActivity class displays courses and their required books, allowing users to add new courses and books, and providing functionalities to delete books from courses within an Android application.

HomePageActivity
* The HomePageActivity class displays and allows searching and sorting of books, and facilitates navigation to detailed book information within an Android application.

LibraryActivity
* The LibraryActivity class provides navigation options within the app to sell books and view books for sale, each through its respective activity, in an Android application.

LoggedinActivity
* The LoggedinActivity class provides logged-in users with options to view and edit their account information via the ChangeAccount activity, and to view required course books through the CoursesBooksActivity, facilitating navigation within an Android application.

LoginActivity
* The LoginActivity class handles user login, offering options to log in, sign up, and navigate back to the home page

SellBooksActivity
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
* The CourseManagement class manages courses, including adding, deleting, and querying courses and their required books, utilizing a singleton pattern for instance management.

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
The BookDatabaseImpl class implements the BookDatabase interface, managing book-related operations such as retrieval, addition, and search in a database.

FavouriteBooksDatabaseImpl

RatingDatabaseImpl

SellBooksDatabaseImpl

UserDatabaseImpl

### stub

DummyDatabase

### subinterfaces
BookDatabase

BuyBookDatabase

FavouriteBooksDatabase

RatingDatabase

SellBooksDatabase

UserDatabase

### utils
DBHelper
* The DBHelper class copies the app's database from assets to internal storage and supports resetting the database, updating its path via the Main class.

## Domain Specific Objects
Book
* The Book class models book entities with properties like ID, name, price, author, and condition, along with functionality to add and manage ratings.

BookProperties
* The BookProperties class encapsulates the descriptive attributes of a book, including condition, edition, and ratings, along with methods to manage and calculate the overall rating.

Course

Rating
* The Rating class represents a book rating with a score, comment, and author ID, including validation for rating values and ownership checks for modifications.

User
* The User class models a user with attributes like name, password, type, and address, supporting operations like password check, update, and managing a list of books for sale.


