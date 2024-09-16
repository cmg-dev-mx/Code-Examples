import {
  type RouteProp,
  useNavigation,
  useRoute,
} from '@react-navigation/native';
import {View, Text} from 'react-native';
import {RootStackParams} from '../../routes/StackNavigator';
import {globalStyles} from '../../theme/theme';
import {useEffect} from 'react';

export const ProductScreen = () => {
  const params = useRoute<RouteProp<RootStackParams, 'ProductDetail'>>().params;
  const navigation = useNavigation();

  // Para poner el nombre del producto en el header
  useEffect(() => {
    navigation.setOptions({
      title: params.name,
    });
  }, []);

  return (
    <View style={globalStyles.container}>
      <Text>Product screen</Text>

      <Text
        style={{
          fontSize: 20,
          textAlign: 'center',
          marginTop: 20,
        }}>
        {params.id} - {params.name}
      </Text>
    </View>
  );
};
function RouteProp<T, U>() {
  throw new Error('Function not implemented.');
}
