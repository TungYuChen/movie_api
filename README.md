# movie_api
Create a movie api

Include simple CRUD and some extension function as like and search by wild card

API:
constant prefix: http://localhost:8080/api/movie/
  <ul>
    <li>/create/   POST to create a new movie with JSON -> "title": <input>, "description": <input>, "releaseYear": <input>, "duration": <input>, "rating": <input>
    <li>/id={id}   GET to find the movie by id
    <li>/title={title}   GET to find the movie by title
    <li>/update/   Put to update movie details with JSON -> "id": {input}, "title": {input}, "description": {input}, "releaseYear": {input}, "duration": {input},                                                                     "rating": {input} (except id, others are nullable, if input null will remain the old content)
    <li>/delete/id={id} DELETE to delete the movie with id
    <li>/like/ PATCH to add likes amount
    <li>/dislike/ PATCH to add dislike amount
    <li>/movies/t={input} GET to find all movies which title include the input string
  </ul>
