# ARCHITECTURE:
Our BookEase application is designed with 3-tier architecture , it has 'Business', 'Presentation'and 'Persistence' layers. The application   has been split into 4  java packages : 'business' , 'models' , 'persistence' and 'presentation'

### Business layer:
There are 6 classes in the business layer of our application. the classes are : 'AccountManagement' ,
'Authenticate' ,  'AuthenticatedUser' 'AuthenticationManager' , 'BookManagement' 'RandomGenerator'. <br />

- **AccountManagement**: This class lets an user create an account along with setting their password for their account. <br />
- **Authenticate**: This an interface  for  user authentication. <br/>
- **AuthenticatedUser**: This class verifies singleton user object and unlocks certain actions for an user to carry out  in our system . <br />
- **AuthenticationManager**: This class implements the authenticate interface and checks the password of an authenticated user. <br />
- **BookManagement**: This  class allows to view  books and find books by their name and  by their ID from our database. <br />
- **RandomGenerator**: This  class randomly generates datas for this iteration. <br />


### Presentation layer:
There are  6 classes in our presentation layer: 'GlobalActivity' ,'BookInfoActivity', 'AccountActivity' ,'ChangeAccount' ,'LoginActivity' 'HomePageActivity' 'LoggedinActivity'.
- **GlobalActivity**: This class shows a common footer for every page and it is used for navigation. <br>
- **BookInfoActivity** : This class displays the information of a book you have selected <br />
- **AccountActivity**: This class displays  the account of an user <br />
- **ChangeAccount**: This class displays  the page for changing the account of an user. <br />
- **LoginActivity**: This class displays the login page for an user.<br />
- **HomePageActivity**: This class displays the homepage of our application. <br />
- **LoggedinActivity**: This class displays the page once an user has logged in. <br />


### Persistence layer:
We have two classes in our persistence layer :  'Database' , 'DummyDatabase' <br>
- **Database**: This class in an interface for our DummyDatabase.
- **DummyDatabase**: This class contains hardcoded data or predefined data sets that are returned when queried.


### Domain Specific Object

- **Book** <br>

- **BookProperties** <br>

- **Rating** <br>

- **User** <br>

[Architecture Diagram](https://lucid.app/lucidchart/3f768bf7-be86-4a72-b46e-df55a6d8a3ec/edit?viewport_loc=-879%2C-49%2C2504%2C1394%2C0_0&invitationId=inv_3b5102fd-87c5-4ca4-8559-ae03f9ab1c68)
