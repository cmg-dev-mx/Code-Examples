import {useEffect, useState} from 'react';
import type {Movie} from '../../core/entities/movie.entity';
import {movieDBFetcher} from '../../config/adapters/movieDB.adapter';

import * as UseCases from '../../core/use-cases';

export const useMovies = () => {
  // Se puede utilizar `tanStack query` para manejar las peticiones asíncronas hacia algún endpoint
  const [isLoading, setIsLoading] = useState(true);
  const [nowPlaying, setNowPlaying] = useState<Movie[]>([]);
  const [topRated, setTopRated] = useState<Movie[]>([]);
  const [upcoming, setUpcoming] = useState<Movie[]>([]);
  const [popular, setPopular] = useState<Movie[]>([]);

  useEffect(() => {
    initialLoad();
  }, []);

  const initialLoad = async () => {
    const nowPlayingPromise = UseCases.moviesNowPlayingUseCase(movieDBFetcher);
    const topRatedPromise = UseCases.moviesTopRatedUseCase(movieDBFetcher);
    const upcomingPromise = UseCases.moviesUpcomingUseCase(movieDBFetcher);
    const popularPromise = UseCases.moviesPopularUseCase(movieDBFetcher);

    const [nowPlaying, topRated, upcoming, popular] = await Promise.all([
      nowPlayingPromise,
      topRatedPromise,
      upcomingPromise,
      popularPromise,
    ]);

    setNowPlaying(nowPlaying);
    setTopRated(topRated);
    setUpcoming(upcoming);
    setPopular(popular);

    setIsLoading(false);
  };

  return {
    isLoading,
    nowPlaying,
    topRated,
    upcoming,
    popular,
  };
};
