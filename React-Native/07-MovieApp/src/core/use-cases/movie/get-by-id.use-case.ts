import {HttpAdapter} from '../../../config/adapters/http/http.adapter';
import {MovieDetailResponse} from '../../../infrastructure/interfaces/movie-db.responses';
import {MovieMapper} from '../../../infrastructure/mappers/movie.mapper';
import {FullMovie} from '../../entities/movie.entity';

export const getMovieById = async (
  fetcher: HttpAdapter,
  movieId: number,
): Promise<FullMovie> => {
  try {
    const movieDetail = await fetcher.get<MovieDetailResponse>(`/${movieId}`);
    return MovieMapper.fromMovieDBToEntity(movieDetail);
  } catch (error) {
    console.error(error);
    throw new Error('Cannot get movie by id: ${movieId}');
  }
};
