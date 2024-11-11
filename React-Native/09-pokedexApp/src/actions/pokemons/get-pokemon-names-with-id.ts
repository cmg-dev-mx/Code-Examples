import {pokeApi} from '../../config/api/pokeApi';
import {PokeAPIPaginatedResponse} from '../../infrastructure/interfaces/pokeapi.interfaces';
import {getPokemons} from './get-pokemons';

export const getPokemonNamesWithId = async () => {
  const url = `/pokemon?limit=1600`;
  const {data} = await pokeApi.get<PokeAPIPaginatedResponse>(url);

  return data.results.map(info => ({
    id: Number(info.url.split('/').slice(-2)[0]),
    name: info.name,
  }));
};
