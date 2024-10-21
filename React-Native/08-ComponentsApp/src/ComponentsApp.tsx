import 'react-native-gesture-handler';
import '../gesture-handler';

import {NavigationContainer} from '@react-navigation/native';
import {View, Text} from 'react-native';
import {Navigator} from './presentation/navigator/Navigator';

export const ComponentsApp = () => {
  return (
    <NavigationContainer>
      <Navigator />
    </NavigationContainer>
  );
};
