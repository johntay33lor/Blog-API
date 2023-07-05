To build the project, I had to make sure I had the necessary dependencies, and 
used the initializer to create the Spring Boot project. Next I had to create the
correct classes to achieve the functionality. The Post model, Post repository,
Post service and Post controller classes are used to manipulate the blog posts,
while the classes in the Exception package is for handling validations. To test
my application I used Postman and the Post Service class to Create, Read, Update,
and Delete posts. To Create a new post I called the POST method, and inside the
body added the object with the title, content, and author. Once it was posted, I
used the GET method to return all posts. By adding {id} to the file path I was then
able to GET, PUT, and DELETE blog posts with a certain ID. Next I inserted 
validations so that if you try to GET, PUT, or DELETE a post with an invalid ID,
the "Post not found" message will pop up instead of an error.