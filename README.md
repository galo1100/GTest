# GTest

Task

In order to work you need to create a `moviedb.properties` file at the root project with the next line:
```
moviedb.token = "access_token"
```
`access_token` is a token that you should obtain from the Movie Database API
(https://developers.themoviedb.org/3/getting-started/introduction)

Specs
1.- TV Shows list
- Create a view that contains an infinite scroll list with the most popular tv shows.
- Use the following endpoint: Most Popular Tv Shows
(https://developers.themoviedb.org/3/tv/get-popular-tv-shows)
- Each item of the list should contain at least an image, the tv show title and the vote average
fields.
- The list should be paginated.

2.- Show Detail
- When a show is clicked on the list, create a show detail view that shows extra info for the
show in a separate screen.
- This should contain at least: A big hero image, the title, the overview... (you can get that info
exploring the provided API).
- The user should be able to navigate between similar tv shows by swiping horizontally or by
adding a section to the detail view with a horizontal view that includes the similar shows.
- Use this endpoint to get Similar Shows
(https://developers.themoviedb.org/3/tv/get-similar-tv-shows)
