import React from 'react';
import {View, Text} from 'react-native';
import {globalStyles} from '../../theme/theme';
import {useSafeAreaInsets} from 'react-native-safe-area-context';
import {PrimaryButton} from '../../components/shared/PrimaryButton';
import {DrawerActions, useNavigation} from '@react-navigation/native';

export const ProfileScreen = () => {
  const insets = useSafeAreaInsets();
  const navigation = useNavigation();

  return (
    <View
      style={{
        flex: 1,
        paddingHorizontal: 20,
        marginTop: insets.top,
      }}>
      <Text
        style={{
          marginBottom: 10,
        }}>
        ProfileScreen
      </Text>
      <PrimaryButton
        label="Abrir Menú"
        onPress={() => navigation.dispatch(DrawerActions.toggleDrawer())}
      />
    </View>
  );
};
