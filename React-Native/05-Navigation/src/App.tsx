import 'react-native-gesture-handler';

import {NavigationContainer} from '@react-navigation/native';
import React from 'react';
import {StackNavigator} from './presentation/routes/StackNavigator';

export const App = () => {
  return (
    <NavigationContainer>
      <StackNavigator />
    </NavigationContainer>
  );
};
