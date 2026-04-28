ALTER TABLE movie
ALTER COLUMN rating TYPE double precision
USING rating::double precision;