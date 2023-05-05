# Book of Vile Darkness -- Testing with BCrypt via JBCrypt

The contents of this foul manuscript of ineffable wickedness are the meat and drink of those in evil’s thrall. No mortal was meant to know the secrets it contains, knowledge so horrid that to even glimpse the scrawled pages invites madness. This book is forbidden, and to unlock its contents, one must know special words of power.

The Book of Vile Darkness app is a simple demo working with BCrypt for hashing passwords to store in a Postgres database.

## Instructions

- Build from the command line inside the `bookofviledarkness` directory with `./gradlew bootRun`
    - Setup a Postgres server with a database named "bookofviledarkness".
    - The `src/main/resources/application.properties` file contains generic user/name password resources change these to match your Postgres server username and password.
    - While the app is running direct your web browser to [localhost:8080](http://localhost:8080). This will load the splash page.

### App Use

Follow the link to create an account and enter a username and password, you'll be redirected back to the login page, now use your credentials to login.

After logging in you can use the "Add a New Secret" form to add a post. After making a post it will be displayed on the page. Keep making posts and they'll populate the page.
