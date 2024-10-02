import {HttpAdapter} from '../../../config/adapters/http/http.adapter';
import {MoviesResponse} from '../../../infrastructure/interfaces/movie-db.responses';
import {MovieMapper} from '../../../infrastructure/mappers/movie.mapper';
import type {Movie} from '../../entities/movie.entity';

export const moviesPopularUseCase = async (
  fetcher: HttpAdapter,
): Promise<Movie[]> => {
  try {
    const popular = await fetcher.get<MoviesResponse>('/popular');

    return popular.results.map(MovieMapper.fromMovieDBResultToEntity);
  } catch (error) {
    console.error(error);
    throw new Error('Error fetching movies - Popular');
  }
};
