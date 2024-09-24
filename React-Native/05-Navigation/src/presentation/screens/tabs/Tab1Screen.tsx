import React from 'react';
import {Text, View} from 'react-native';
import {HamburguerMenu} from '../../components/shared/HamburguerMenu';
import {CustomIcon} from '../../components/shared/CustomIcon';

export const Tab1Screen = () => {
  return (
    <View>
      <HamburguerMenu />
      <Text>Tab1Screen</Text>
      <CustomIcon name="rocket" />
    </View>
  );
};
