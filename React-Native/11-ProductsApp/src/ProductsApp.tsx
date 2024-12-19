import './gesture-handler';
import {NavigationContainer} from '@react-navigation/native';
import {Text, View} from 'react-native';
import {StackNavigator} from './presentation/navigation/StackNavigator';

export const ProductsApp = () => {
  return (
    <NavigationContainer>
      <StackNavigator />
    </NavigationContainer>
  );
};
