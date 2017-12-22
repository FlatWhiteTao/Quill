# Quill
The Quill is an online news content provider that allows subscribers to browse news articles that are customised according to their interests and publisher choices. Quill is a web application that enables freelance journalists as well as employees of the organization to publish contents.

It is a Spring MVC+Hibernate+MySQL+Maven intergration project. 

The architecture of this project consists of 6 layers, and suitable design patterns and technologies are applied in each layer:
	
	1. Client Layer:  HTML, AJAX, JavaScript, JQuery
	
	2. Spring Security Layer: Built-in Spring Security Framework, Authentication Enforcer, Authorisation enforcer, Intercepting Validator, Filter Chain 
	
	3. Presentation Layer: MVC pattern, Front Controller pattern, dependency injection pattern, Authorisation enforcer (render the UI based on the Authorisation) 
	
	4. Business Layer: Domain model design pattern, dependency injection pattern, Authorisation enforcer (Preauthorise the access to protected services) 
	
	5. Data Source Layer: Repository pattern, data Mapper pattern, identity field pattern, identity map, dependency injection pattern, Lazy Load
	
	6. Concurrency Control: Optimistic Locking (Versioning) 
