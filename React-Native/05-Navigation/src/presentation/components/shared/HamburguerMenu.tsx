import {DrawerActions, useNavigation} from '@react-navigation/native';
import React, {useEffect} from 'react';
import {Pressable, Text} from 'react-native';
import {CustomIcon} from './CustomIcon';

export const HamburguerMenu = () => {
  const navigation = useNavigation();

  useEffect(() => {
    navigation.setOptions({
      headerLeft: () => (
        <Pressable
          onPress={() => navigation.dispatch(DrawerActions.toggleDrawer)}>
          <CustomIcon name="menu" />
        </Pressable>
      ),
    });
  }, []);

  return <></>;
};
