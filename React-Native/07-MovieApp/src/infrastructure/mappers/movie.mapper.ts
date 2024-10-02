import type {Movie} from '../../core/entities/movie.entity';
import type {Result} from '../interfaces/movie-db.responses';

const baseUrl = 'https://image.tmdb.org/t/p/w500';

export class MovieMapper {
  static fromMovieDBResultToEntity(result: Result): Movie {
    return {
      id: result.id,
      title: result.title,
      description: result.overview,
      releaseDate: result.release_date,
      rating: result.vote_average,
      poster: `${baseUrl}${result.poster_path}`,
      backdrop: `${baseUrl}${result.backdrop_path}`,
    };
  }
}
