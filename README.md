Coverage: 94.7%
# Hobby Web Application - Reading List

```

A full stack website designed to allow users to keep and maintain a list of books that are currently reading, plan to read, and have read.
Through the use of CRUD functions in a API, and its interaction with a front-end, users are able to create, update, read, and delete items from said list.
The front-end being written in HTML/CSS/JavaScript, and the API being built using the Java framework Springboot.

```
## Getting Started

These instructions will get you a copy of the Application up and running on your local machine for development and testing purposes. 

### Prerequisites
Project created using
- MySQL workbench Local Server-  https://dev.mysql.com/downloads/mysql/ - 
- H2 database for testing
- Java JDK
- Git Bash - Terminal for interacting with, as well as version control software. - 
- Eclipse - IDE - Integrated Development Environment - https://www.eclipse.org/downloads/ 
- Or Spring Tool Suite instead of eclpise
- VSCode

```

### Installation

A step by step series of instructions that tell you how to get the application running

1. Fork  repository to your own github account.
2. Clone repository on to local system. Open git bash  terminal in local folder, and run 'git clone' followed by the SSH code that can be copied from github. Alternatively the HTTPS ur can be used from the same interface on github to smart import the repository through the Eclipse IDE. 

3.If cloned using the Git bash terminal then, select file, open projects from file system and then navigate to the local folder where the repository was cloned.
4. Once repo has been cloned, open it in Spring Suite, all the packages and tests should load in, as well as two SQL test files.
5.In the resources folder you will find three files.

- applicaton.properties
- applicaton-prod.properties
- applicaton-test.properties

Application-test is the H2 databse that is used for testing, and it automatically set when tests are run which require it.
Application-prod is allows the API to connect to a local instance of MySQL, where it automatically will create an entity table.

To run the pplication prior to compiling, it can be run from directly inside Spring Suite. Right clicking and selecting "run as springboot application"
THen browse to localhost:8080 in your web browser of choice.

If you wish to make changes to either the front end or API, the API will need to be tested which can be done by selecting "run as Junit test/coverage as Junit test", 
while any changes to the front end in src/main/resources/static wont be live until the project is refreshed or the edited code(VSCode) is reimported into the project.

```

###Compiling and Deployment

Once ready to compile
If any changes are made to the HTML/CSS/JavaScript then they must reflected in the files stored in src/main/resources/static, ptherwise any changes to the front-end wont be reflected 
in the deployed application.
When you're ahppy with any changes, then right click on the project and select "run as maven build...",  then in the box titled Goals: type "clean package" and select run.
The application will then start building, it may take some time expecially if it's your first time. If there aren't any issues with the code then the application will build with no issues.


![Maven Build success](https://user-images.githubusercontent.com/107992051/185708080-bfffed9a-72f1-4afa-8f02-ca1b0a259748.png)


The application will no be accessible from a .jar file inside the target folder in the root directory.
You should move the ReadingList.jar file into the root folder.

In either command line or gitbash, navigate in to the root folder of your project and run "java -jar ReadingList.jar", this will spin up the API without the need for spring suite. 
You will see the following:-




![Launching springboot API from cmd](https://user-images.githubusercontent.com/107992051/185709707-58d57b82-3d0b-41d8-88f2-8e5281e47c95.png)


The landing page of the website can now be accessed from localhost:8080.


```
### Testing
Coverage: 94.7%

Tests can be found under 

- sc/test/java
	-com.qa.main.controllers
			-BookControllerIntegrationTest
			-BookControllerUnitTest
	-com.qa.main.services
			-BookServicesUnitTest

Before any tests are run, make sure to check the testdata.sql matches the test objects being passed through the tests.


### Unit Tests 

Units tests is a testing approach that targets the fundamental bulding blocks of an appliction. It proves an application works by checking it unit by unit.
Each unit is compared against a mock test of itself to see if it returns what is expected.


Below you can see the method for create is compared against a mock of itself, servce.create depsite this being the controller test as the service is called through dependecy injection.

eg.

@Test
	void createTest() throws Exception {
		// Create an object for posting
		Book entry = new Book("Book", "Author", "02/02/2000", 10);
		String entryAsJSON = mapper.writeValueAsString(entry);
		
		// Create an object for checking the result
		Book result = new Book(1L, "Book", "Author", "02/02/2000", 10);
		String resultAsJSON = mapper.writeValueAsString(result);
		
		Mockito.when(service.create(entry)).thenReturn(result);
		
		mvc.perform(post("/book/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(status().isCreated());
	}
	

### Integration Tests 

Integration tests, tests all components of an application together to see how if it functions correctly in combination.
This application used integration testing for testing the controller layer of the API.

eg.

@Test
	public void readAllTest() throws Exception {
		// Create a list to check the output of readAll
		List<Book> result = new ArrayList<>();
		// Add the single entry to the list
		result.add(new Book(1L, "example book", "example author", "01/01/2000", 10));
		// Convert the list to JSON (The API responds in JSON)
		String resultAsJSON = mapper.writeValueAsString(result);
		
		mvc.perform(get("/book/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(resultAsJSON));
	}


```
## Built With

* Jirra - Kanban Board/
* Git - Version control system
* Github - Management of code
* Java/Springboot framework = API
* MySQL - Database management/backend
* Junit/Mockito - Testing
* VSCode - writing HTML/CSS(Bootstrap)/Javascript
* Post man - mimicing the frontend
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Syed Zaidi** - (https://github.com/namvarzaidi
* )

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 


## Acknowledgments

Huge thanks to all my trainers at QA especially Anoush Lowton for his support while while working on this project. Your help and support was invaluable.
