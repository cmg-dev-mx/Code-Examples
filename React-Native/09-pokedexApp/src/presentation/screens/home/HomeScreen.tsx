import {View} from 'react-native';
import {Button, Text} from 'react-native-paper';
import {getPokemons} from '../../../actions/pokemons';

export const HomeScreen = () => {
  return (
    <View>
      <Text variant="displaySmall">HomeScreen</Text>
      <Button mode="contained" onPress={() => console.log('Button pressed')}>
        Press me
      </Button>
    </View>
  );
};
