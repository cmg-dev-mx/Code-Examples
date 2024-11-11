import {FlatList, View} from 'react-native';
import {globalTheme} from '../../../config/theme/global-theme';
import {useSafeAreaInsets} from 'react-native-safe-area-context';
import {ActivityIndicator, Text, TextInput} from 'react-native-paper';
import {PokemonCard} from '../../components/pokemons/PokemonCard';
import {Pokemon} from '../../../domain/entities/pokemon';
import {useQuery} from '@tanstack/react-query';
import {getPokemonNamesWithId} from '../../../actions/pokemons';
import {useMemo, useState} from 'react';

export const SearchScreen = () => {
  const {top} = useSafeAreaInsets();
  const {isLoading, data: pokemonNameList = []} = useQuery({
    queryKey: ['pokemons', 'all'],
    queryFn: () => getPokemonNamesWithId(),
  });

  const [term, setTerm] = useState('');

  // Todo: Aplicar debounce
  const pokemonNameIdList = useMemo(() => {
    // Es un número
    if (!isNaN(Number(term))) {
      const pokemon = pokemonNameList.find(
        pokemon => pokemon.id === Number(term),
      );
      return pokemon ? [pokemon] : [];
    }

    if (term.length === 0) return [];
    if (term.length < 3) return [];

    return pokemonNameList.filter(pokemon =>
      pokemon.name.includes(term.toLowerCase()),
    );
  }, [term]);

  return (
    <View
      style={[
        globalTheme.globalMargin,
        {
          paddingTop: top + 20,
        },
      ]}>
      <TextInput
        placeholder="Buscar Pokemon"
        mode="flat"
        autoFocus
        autoCorrect={false}
        onChangeText={setTerm}
        value={term}
      />

      <ActivityIndicator style={{paddingTop: 20}} />

      <Text>{JSON.stringify(pokemonNameIdList, null, 2)}</Text>

      <FlatList
        data={[] as Pokemon[]}
        keyExtractor={(pokemon, index) => `${pokemon.id}-${index}`}
        numColumns={2}
        style={{paddingTop: top + 20}}
        renderItem={({item}) => <PokemonCard pokemon={item} />}
        onEndReachedThreshold={0.7}
        showsVerticalScrollIndicator={false}
      />
    </View>
  );
};
