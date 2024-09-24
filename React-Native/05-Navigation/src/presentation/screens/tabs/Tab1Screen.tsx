import React from 'react';
import {Text, View} from 'react-native';
import {HamburguerMenu} from '../../components/shared/HamburguerMenu';

import Icon from 'react-native-vector-icons/MaterialIcons';

export const Tab1Screen = () => {
  return (
    <View>
      <HamburguerMenu />
      <Text>Tab1Screen</Text>
      <Icon name="rocket" size={30} />
    </View>
  );
};
