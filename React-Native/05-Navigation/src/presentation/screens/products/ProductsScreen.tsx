import React from 'react';
import {View, Text} from 'react-native';
import {globalStyles} from '../../theme/theme';
import {FlatList} from 'react-native-gesture-handler';
import {PrimaryButton} from '../../components/shared/PrimaryButton';
import {useNavigation} from '@react-navigation/native';

const products = [
  {
    id: 1,
    name: 'Product 1',
  },
  {
    id: 2,
    name: 'Product 2',
  },
  {
    id: 3,
    name: 'Product 3',
  },
  {
    id: 4,
    name: 'Product 4',
  },
  {
    id: 5,
    name: 'Product 5',
  },
  {
    id: 6,
    name: 'Product 6',
  },
  {
    id: 7,
    name: 'Product 7',
  },
  {
    id: 8,
    name: 'Product 8',
  },
  {
    id: 9,
    name: 'Product 9',
  },
  {
    id: 10,
    name: 'Product 10',
  },
];

export const ProductsScreen = () => {
  const navigation = useNavigation();

  return (
    <View style={globalStyles.container}>
      <Text
        style={{
          fontSize: 30,
          marginBottom: 10,
        }}>
        Productos
      </Text>

      <FlatList
        data={products}
        renderItem={({item}) => (
          <PrimaryButton
            label={item.name}
            onPress={() => {
              navigation.navigate('ProductDetail' as never);
            }}
          />
        )}
      />

      <Text
        style={{
          fontSize: 30,
          marginBottom: 10,
        }}>
        Ajustes
      </Text>
      <PrimaryButton
        label="Ajustes"
        onPress={() => {
          navigation.navigate('Settings' as never);
        }}
      />
    </View>
  );
};
