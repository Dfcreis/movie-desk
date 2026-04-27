CREATE TABLE movie_streaming(
     movie_id INTEGER,
     streaming_id INTEGER,
      CONSTRAINT fk_movie_category_movie FOREIGN KEY(movie_id) REFERENCES movie(id),
      CONSTRAINT fk_movie_category_category FOREIGN KEY(streaming_id) REFERENCES streaming(id))