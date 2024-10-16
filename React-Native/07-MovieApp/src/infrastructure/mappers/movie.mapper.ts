import type {FullMovie, Movie} from '../../core/entities/movie.entity';
import type {
  MovieDetailResponse,
  Result,
} from '../interfaces/movie-db.responses';

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

  static fromMovieDBToEntity(response: MovieDetailResponse): FullMovie {
    return {
      id: response.id,
      title: response.title,
      description: response.overview,
      releaseDate: response.release_date,
      rating: response.vote_average,
      poster: `${baseUrl}${response.poster_path}`,
      backdrop: `${baseUrl}${response.backdrop_path}`,
      genres: response.genres.map(genre => genre.name),
      duration: response.runtime,
      budget: response.budget,
      originalTitle: response.original_title,
      productionCompanies: response.production_companies.map(
        company => company.name,
      ),
    };
  }
}
