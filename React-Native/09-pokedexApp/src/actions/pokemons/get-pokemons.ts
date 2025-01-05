import {pokeApi} from '../../config/api/pokeApi';
import {Pokemon} from '../../domain/entities/pokemon';
import type {
  PokeAPIPaginatedResponse,
  PokeAPIPokemon,
} from '../../infrastructure/interfaces/pokeapi.interfaces';
import {PokemonMapper} from '../../infrastructure/mappers/pokemon.mapper';

export const getPokemons = async (
  page: number,
  limit: number = 20,
): Promise<Pokemon[]> => {
  try {
    const url = `/pokemon?limit=${limit}&offset=${page * limit}`;
    const {data} = await pokeApi.get<PokeAPIPaginatedResponse>(url);

    const pokemonPromises = data.results.map(info => {
      return pokeApi.get<PokeAPIPokemon>(info.url);
    });

    const pokeAPIPokemons = await Promise.all(pokemonPromises);
    const pokemonsPromise = pokeAPIPokemons.map(pokemon =>
      PokemonMapper.pokemonApiToPokemonEntity(pokemon.data),
    );

    return await Promise.all(pokemonsPromise);
  } catch (error) {
    throw new Error('Error on get pokemons');
  }
};
