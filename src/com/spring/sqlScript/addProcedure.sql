DROP PROCEDURE IF EXISTS add_movie;

DELIMITER $$
CREATE DEFINER=`admin`@`localhost` PROCEDURE add_movie(

	in new_movieTitle varchar(100),
    in new_year int(11),
    in new_director varchar(100),
    in new_banner_url varchar(200),
    in new_trailer_url varchar(200),
    
    in new_star_FN varchar(50),
    in new_star_LN varchar(50),
    in new_star_dob date,
    in new_star_photo_url varchar(200),
    
    in new_genre varchar(32),
    
    out msg varchar(500)
)
begin
	
    declare movieID int default 0;
    declare starID  int default 0;
    declare genreID  int default 0;
    
    declare countMovie int default 0;
    declare countStar int default 0;
    declare countGenre int default 0;

	declare isStarInMovie int default 0; -- check stars_in_movies is already exits
    declare isGenreInMovie int default 0;-- check genres_in_movies is already exits
    
    set @msg ='';

	SELECT movies.id, count(*) INTO movieID, countMovie FROM movies
	WHERE movies.title = new_movieTitle
        AND movies.director = new_director
        AND movies.year = new_year;
			
	SELECT stars.id, count(*) INTO starID, countStar FROM stars
	WHERE stars.first_name = new_star_FN
        AND stars.last_name = new_star_LN
        AND stars.dob = new_star_dob;
	
	SELECT genres.id, count(*) INTO genreID, countGenre FROM genres
	WHERE genres.name = new_genre;
    
    -- if this is a new star
	if countStar = 0 then
		if new_star_photo_url = '' then
			set new_star_photo_url = 'https://c1.staticflickr.com/5/4179/34574108456_1a5a4ef007.jpg';
		end if;
        
		insert into stars (first_name, last_name, dob, photo_url)
			values (new_star_FN, new_star_LN, new_star_dob, new_star_photo_url);
		set starID = LAST_INSERT_ID();
        set @msg = concat(@msg, 'New Star is successfully added into the Database\n');
	end if;
    
    -- if this is new genre
    if countGenre = 0 then
		insert into genres (name) values (new_genre);
        set genreID = LAST_INSERT_ID();
        set @msg = concat(@msg, 'New Genre is successfully added into the Database\n');
	end if;
    
    
    -- case of new movie
    if countMovie = 0 then
		begin
			if new_banner_url = '' then
				set new_banner_url ='https://c1.staticflickr.com/5/4179/34574108456_1a5a4ef007.jpg';
			end if;
			
            if new_trailer_url = '' then
				set new_trailer_url ='youtube.com';
			end if;
            
			insert into movies (title, year, director, banner_url, trailer_url)
				values (new_movieTitle, new_year, new_director, new_banner_url, new_trailer_url);
			set movieID = LAST_INSERT_ID();
            set @msg = concat(@msg, 'New Movie is successfully added into the Database\n');
            
            insert into stars_in_movies (star_id, movie_id) values (starID, movieID);
            insert into genres_in_movies (genre_id, movie_id) values (genreID, movieID);
            
            set @msg = concat(@msg, 'Genre and Star are successfully linked with the Movie\n');
        end;
    
    -- case movie is already exit    
	else
		begin
			set @msg = concat(@msg, 'Movies is already exits in the Database\n');
			-- new star
            if countStar = 0 then
				insert into stars_in_movies (star_id, movie_id) values (starID, movieID);
                set @msg = concat(@msg, 'Star is successfully linked with the Movie\n');
            end if;
            
            -- new genre
            if countGenre = 0 then
				insert into genres_in_movies (genre_id, movie_id) values (genreID, movieID);
                set @msg = concat(@msg, 'Genre is successfully linked with the Movie\n');
            end if;
            
            -- old star, old genre
            if countStar != 0 then
				set @msg = concat(@msg, 'Star is already existed in the database\n');
				select count(*) into isStarInMovie
                from stars_in_movies
                where stars_in_movies.movie_id = movieID
					and stars_in_movies.star_id = starID;
                
                if isStarInMovie = 0 then
					insert into stars_in_movies (star_id, movie_id) values (starID, movieID);
                    set @msg = concat(@msg, 'Star is successfully linked with the Movie\n');
				else
					set @msg = concat(@msg, 'No Update! Star is ALREADY linked with the Movie\n');
				end if;
            end if;
            
            if countGenre != 0 then
				set @msg = concat(@msg, 'Genre is already existed in the database\n');
				select count(*) into isGenreInMovie
                from genres_in_movies
                where genres_in_movies.movie_id = movieID
					and genres_in_movies.genre_id = genreID;
                
                if isGenreInMovie = 0 then
					set @msg = concat(@msg, 'Genre is successfully linked with the Movie\n');
					insert into genres_in_movies (genre_id, movie_id) values (genreID, movieID);
				else
					set @msg = concat(@msg, 'No Update! Genre is ALREADY linked with the Movie\n');
				end if;
            end if;
        end;
    end if;
    
    set msg = @msg;
end$$
DELIMITER ;