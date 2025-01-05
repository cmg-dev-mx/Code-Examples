import '../gesture-handler';

import {NavigationContainer} from '@react-navigation/native';
import React from 'react';
import {BottomTabNavigator} from './presentation/routes/BottomTabsNavitagor';
import {SideMenuNavigator} from './presentation/routes/SideMenuNavigator';

export const App = () => {
  return (
    <NavigationContainer>
      <SideMenuNavigator />
    </NavigationContainer>
  );
};
