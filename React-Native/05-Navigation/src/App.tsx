import '../gesture-handler';

import {NavigationContainer} from '@react-navigation/native';
import React from 'react';
import {StackNavigator} from './presentation/routes/StackNavigator';
import {createDrawerNavigator} from '@react-navigation/drawer';
import {SideMenuNavigator} from './presentation/routes/SideMenuNavigator';

export const App = () => {
  return (
    <NavigationContainer>
      <SideMenuNavigator />
    </NavigationContainer>
  );
};
