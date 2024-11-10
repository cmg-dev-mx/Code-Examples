import {View} from 'react-native';
import {ActivityIndicator, Button, Text} from 'react-native-paper';
import {getPokemons} from '../../../actions/pokemons';
import {useQuery} from '@tanstack/react-query';

export const HomeScreen = () => {
  const {isLoading, data = []} = useQuery({
    queryKey: ['pokemons'],
    queryFn: () => getPokemons(0),
    staleTime: 1000 * 60 * 60, // 1 hour
  });

  return (
    <View>
      <Text variant="displaySmall">HomeScreen</Text>
      {isLoading ? (
        <ActivityIndicator />
      ) : (
        <Button mode="contained" onPress={() => console.log('Button pressed')}>
          Press me
        </Button>
      )}
    </View>
  );
};
