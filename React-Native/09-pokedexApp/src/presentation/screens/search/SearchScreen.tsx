import {FlatList, View} from 'react-native';
import {globalTheme} from '../../../config/theme/global-theme';
import {useSafeAreaInsets} from 'react-native-safe-area-context';
import {ActivityIndicator, FAB, Text, TextInput} from 'react-native-paper';
import {PokemonCard} from '../../components/pokemons/PokemonCard';
import {Pokemon} from '../../../domain/entities/pokemon';
import {useQuery} from '@tanstack/react-query';
import {getPokemonNamesWithId} from '../../../actions/pokemons';

export const SearchScreen = () => {
  const {top} = useSafeAreaInsets();
  const {isLoading, data: pokemonNameList = []} = useQuery({
    queryKey: ['pokemons', 'all'],
    queryFn: () => getPokemonNamesWithId(),
  });

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
        onChangeText={value => console.log(value)}
        value={''}
      />

      <ActivityIndicator style={{paddingTop: 20}} />

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
